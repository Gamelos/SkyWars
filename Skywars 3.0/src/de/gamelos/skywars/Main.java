package de.gamelos.skywars;

import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Locale;
import java.util.Random;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.output.ByteArrayOutputStream;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.event.player.PlayerLoginEvent.Result;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;
import de.dytanic.cloudnet.bridge.CloudServer;
import de.gamelos.PermissionsAPI.MySQLRang;
import de.gamelos.jaylosapi.JaylosAPI;
import de.gamelos.stats.MySQL;
import net.md_5.bungee.api.ChatColor;



public class Main extends JavaPlugin implements Listener{

	public static String Prefix = "§7[§eSkyWars§7] §8";
	public static Boolean ingame = false;
	public static Boolean schutzzeit = false;
	public static File locations;
	public static FileConfiguration loc;
	public static String mapname = null;
	public static ArrayList<Player>playeringame= new ArrayList<>();
	public static ArrayList<Player>spectatorplayer= new ArrayList<>();
	public static HashMap<Player,String> kit = new HashMap<Player,String>();
	public static HashMap<Player,Integer> kills = new HashMap<Player,Integer>();
	
	
	
	public static int maxplayer = 0;
	
	@Override
	public void onEnable() {
		Bukkit.getPluginManager().registerEvents(this, this);
		Main.locations = new File("plugins/Skywars", "data.yml");
		Main.loc = YamlConfiguration.loadConfiguration(Main.locations);
		loadConfig();
		Events.antiwater();
//		====
		getCommand("promote").setExecutor(new Commands());
		getCommand("stats").setExecutor(new StatsCommand());
		getCommand("start").setExecutor(new Start());
		getCommand("forcemap").setExecutor(new Forcemap());
		Bukkit.getMessenger().registerOutgoingPluginChannel(this, "info");
        this.getServer().getMessenger().registerOutgoingPluginChannel(this, "BungeeCord");
//        ===
		System.out.println("[Skywars] Das Plugin wurde geladen");
		Events.spielerzahl();
		Bukkit.getPluginManager().registerEvents(new Events(), this);
		Bukkit.getPluginManager().registerEvents(new Kisten(), this);
		Bukkit.getPluginManager().registerEvents(new Kommpass(), this);
		Bukkit.getPluginManager().registerEvents(new Zaubertisch(), this);
		Bukkit.getPluginManager().registerEvents(new Forcemap(), this);
		Bukkit.getPluginManager().registerEvents(new Chat(), this);
		getCommand("sw").setExecutor(new Commands());
//		Zaubertisch.level1.add(Enchantment.DAMAGE_ALL);
//		Zaubertisch.level1.add(Enchantment.PROTECTION_ENVIRONMENTAL);
//		Zaubertisch.level2.add(Enchantment.DAMAGE_ALL);
//		Zaubertisch.level2.add(Enchantment.PROTECTION_ENVIRONMENTAL);
		getCommand("sw").setExecutor(new Commands());
		Main.locations = new File("plugins/Skywars", "data.yml");
		Main.loc = YamlConfiguration.loadConfiguration(Main.locations);
		loadConfig();
		mapname = getRandomMap();
//		
		String deletworld = "gameworld";
		Bukkit.getServer().unloadWorld(deletworld, true);
		try {
		FileUtils.deleteDirectory(new File(deletworld));
		}catch (IOException e1){
			e1.printStackTrace();
		}
		
		Bukkit.getScheduler().scheduleSyncDelayedTask(this, new Runnable(){
			@Override
			public void run() {
				for(org.bukkit.entity.Entity ent : Bukkit.getWorld(Main.loc.getString("spawn.world")).getEntities()){
					if(!(ent instanceof Player)){
						ent.remove();
					}
				}
			}
		}, 50);
//
		ConnectMySQL();
		maxplayer = Integer.parseInt(Main.loc.getString(Main.mapname + ".maxplayers"));
//		
		
//		
		Bukkit.getScheduler().scheduleSyncDelayedTask(this, new Runnable(){

			@Override
			public void run() {
				CloudServer.getInstance().setMotd(Main.mapname+" "+getspawnanzahl(mapname)+"x1");
				CloudServer.getInstance().setMaxPlayersAndUpdate(maxplayer);
			}
			
		}, 3);
//		
		JaylosAPI.showrang(true);
		Bukkit.getScheduler().scheduleSyncDelayedTask(this, new Runnable(){
			@Override
			public void run() {
				restartcheck();
			}
		}, 20*60);
		super.onEnable();
	}

	
	
	
	public static boolean test1 = false;
	
	public static MySQL mysql;
	private void ConnectMySQL(){
		mysql = new MySQL(JaylosAPI.gethost(), JaylosAPI.getuser(), JaylosAPI.getdatabase(), JaylosAPI.getpassword());
		mysql.update("CREATE TABLE IF NOT EXISTS Skywars(UUID varchar(64), KILLS int, TODE int, WINS int, GAMES int, NAME varchar(64), COINS int);");
		mysql.update("CREATE TABLE IF NOT EXISTS Kit(UUID varchar(64), Kitname varchar(64));");
		mysql.update("CREATE TABLE IF NOT EXISTS Kits(UUID varchar(64), Kits varchar(64));");
	}
	
	@Override
	public void onDisable() {
		System.out.println("[Skywars] Das Plugin wurde deaktiviert");
//		
		String deletworld = "gameworld";
		Bukkit.getServer().unloadWorld(deletworld, true);
		try {
		FileUtils.deleteDirectory(new File(deletworld));
		}catch (IOException e){
			e.printStackTrace();
		}
//		
		super.onDisable();
	}
	
	public void loadConfig(){
		getConfig().options().copyDefaults(true);
		saveConfig();
		}
	
	public static int getmapanzahl() {
		int i = 0;
		int ii = 1;
		while (loc.getString("Mapnames." + ii) != null) {
			i++;
			ii++;
		}
		return i;
	}

	public static int getspawnanzahl(String mapname) {
		int i = 0;
		int ii = 1;
		while (loc.getString(mapname + ".spawns." + ii) != null) {
			i++;
			ii++;
		}
		return i;
	}
	
	public static String getRandomMap(){
		String mapname = "";
		int i;
		int ii = getmapanzahl()-1;
		
		Random random = new Random();
		i = random.nextInt(ii+(1))+1;
		mapname = Main.loc.getString("Mapnames."+i);
//		
//		
		
		return mapname;
	}
	
	@SuppressWarnings("resource")
	@EventHandler
	public void onlog(PlayerLoginEvent e){
		Player p = e.getPlayer();
		if(ingame == false){
			int maxplayer = Main.maxplayer;
				if(Bukkit.getOnlinePlayers().size()>=maxplayer){
					if(MySQLRang.playerExists(p.getUniqueId().toString())){
						if(MySQLRang.getRangname(p.getUniqueId().toString()).equals("Admin")||MySQLRang.getRangname(p.getUniqueId().toString()).equals("Mod")||MySQLRang.getRangname(p.getUniqueId().toString()).equals("Sup")||MySQLRang.getRangname(p.getUniqueId().toString()).equals("Youtuber")||MySQLRang.getRangname(p.getUniqueId().toString()).equals("Premium")||MySQLRang.getRangname(p.getUniqueId().toString()).equals("Builder")||MySQLRang.getRangname(p.getUniqueId().toString()).equals("Dev")||MySQLRang.getRangname(p.getUniqueId().toString()).equals("Contant")||MySQLRang.getRangname(p.getUniqueId().toString()).equals("Prem+")){
					for(Player pp:Bukkit.getOnlinePlayers()){
						if(!pp.hasPermission("cloudnet.fulljoin")){
							pp.sendMessage(ChatColor.GRAY+"Du wurdest gekickt um Platz für ein "+ChatColor.GOLD+"Premium Spieler §7/ §5Youtuber §7/ §cTeammitglied §7zu machen! Kaufe dir Premium unter §eweb.jaylos.net §7 um nicht mehr gekickt zu werden!");
//							
							ByteArrayOutputStream b = new ByteArrayOutputStream();
							DataOutputStream out = new DataOutputStream(b);
							try{
								out.writeUTF("Connect");
								out.writeUTF("Lobby-1");
							}catch(IOException ex){
								System.err.println("Es gab einen Fehler:");
								ex.printStackTrace();
							}
							Plugin pl = Bukkit.getPluginManager().getPlugin("Skywars");
							pp.sendPluginMessage(pl, "BungeeCord", b.toByteArray());
//							
							e.allow();
							return;
						}
					}
					e.disallow(Result.KICK_OTHER, ChatColor.RED+"Dieser Server ist voll! Keiner kann den Server mehr betreten");
					}else{
						e.disallow(Result.KICK_FULL, ChatColor.RED+"Dieser Server ist voll!"+ChatColor.GRAY+" Kaufe dir Premium unter "+ChatColor.YELLOW+"web.jaylos.net"+ChatColor.GRAY+" um trotzdem Joinen zu können!");
					}
					}else{
						e.disallow(Result.KICK_FULL, ChatColor.RED+"Dieser Server ist voll!"+ChatColor.GRAY+" Kaufe dir Premium unter "+ChatColor.YELLOW+"web.jaylos.net"+ChatColor.GRAY+" um trotzdem Joinen zu können!");
					}
					}
		}
	}
	
//	@EventHandler
//	public void unnick(unNickEvent e){
////		Player p = e.getplayer();
//		if(ingame == false){
//			de.gamelos.scoreboard.LobbyBoard.createScoreboard();
//		}else{
//			for(Player pp:Bukkit.getOnlinePlayers()){
//				if(!Main.spectatorplayer.contains(pp)){
//			de.gamelos.scoreboard.ingameboard.set(pp);
//				}
//			}
//		}
//	}
	public void restartcheck(){
		Bukkit.getScheduler().scheduleSyncRepeatingTask(this, new Runnable() {
			
			@Override
			public void run() {
				Calendar c = Calendar.getInstance();
				c.setTime(new Timestamp(System.currentTimeMillis()));
				SimpleDateFormat sdf = new SimpleDateFormat("HH:mm", Locale.GERMAN);
				String s = sdf.format(c.getTime());
				if(s.equals("03:30")){
					Bukkit.shutdown();
				}
			}
		}, 0, 20*10);
	}
}
