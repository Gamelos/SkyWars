
package de.gamelos.skywars;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import org.apache.commons.io.FileUtils;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.World;
import org.bukkit.WorldCreator;
import org.bukkit.block.Block;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftArrow;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftEgg;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftSnowball;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockDamageEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntitySpawnEvent;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.event.entity.ProjectileLaunchEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractAtEntityEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerItemHeldEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.event.player.PlayerToggleSneakEvent;
import org.bukkit.event.weather.WeatherChangeEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;
import org.bukkit.permissions.PermissionAttachment;
import org.bukkit.plugin.Plugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import com.connorlinfoot.titleapi.TitleAPI;

import de.dytanic.cloudnet.bridge.CloudServer;
import de.gamelos.PermissionsAPI.PermissionsAPI;
import de.gamelos.jaylosapi.JaylosAPI;
import de.gamelos.nick.unNickEvent;
import de.gamelos.scoreboard.Spectator;
import de.gamelos.scoreboard.ingameboard;
import de.gamelos.stats.MySQLKits;
import de.gamelos.stats.SQLStats;
import net.md_5.bungee.api.ChatColor;
import net.minecraft.server.v1_8_R3.EntityPlayer;
import net.minecraft.server.v1_8_R3.PacketPlayOutCamera;

public class Events implements Listener {

	public static Plugin pl = Bukkit.getPluginManager().getPlugin("Skywars");
	static int splzcount;
	
	@SuppressWarnings("deprecation")
	public static void spielerzahl(){
		splzcount = Bukkit.getScheduler().scheduleAsyncRepeatingTask(pl, new Runnable(){
			@Override
			public void run() {
				for(Player pp: Bukkit.getOnlinePlayers()){
					int spielerzahl = Bukkit.getOnlinePlayers().size();
					int maxplayer = Integer.parseInt(Main.loc.getString(Main.mapname + ".maxplayers"));
					ActionBar.sendActionBar(pp, ChatColor.GRAY+"Spielerzahl: "+ChatColor.YELLOW+spielerzahl+ChatColor.GRAY+"/"+ChatColor.YELLOW+maxplayer);
				}
			}
		}, 40, 40);
	}
	public static boolean ranking = true;
	
	public void setPermission(String type,Player target, String permission){
		if(type.equalsIgnoreCase("add")){
			PermissionAttachment attachment = target.addAttachment(pl);
			attachment.setPermission(permission, true);
		}else if(type.equalsIgnoreCase("remove")){
			PermissionAttachment attachment = target.addAttachment(pl);
			attachment.setPermission(permission, false);
		}
	}
	
	@EventHandler
	public void onJoin(PlayerJoinEvent e){
		e.getPlayer().setFireTicks(0);
		if(Main.ingame == false){
			
			if(Main.test1){
				Bukkit.broadcastMessage("test1");
			}
			
		e.setJoinMessage(null);
		Bukkit.getScheduler().scheduleSyncDelayedTask(Bukkit.getPluginManager().getPlugin("Skywars"), new Runnable(){
			@Override
			public void run() {
				for(Player pp:Bukkit.getOnlinePlayers()){
					pp.sendMessage(Main.Prefix+JaylosAPI.getchatname(e.getPlayer(), pp)+ChatColor.DARK_GRAY+" ist dem Spiel beigetreten!");
				}
			}
		}, 10);
		//		
		
		if(de.gamelos.stats.Kits.getKit(e.getPlayer().getUniqueId().toString(), e.getPlayer().getName())!=null){
		Main.kit.put(e.getPlayer(), de.gamelos.stats.Kits.getKit(e.getPlayer().getUniqueId().toString(), e.getPlayer().getName()));
		}
		Player p = e.getPlayer();
		setPermission("add", p, "autolapis.autofill");
		p.getInventory().clear();
		p.setFoodLevel(20);
		p.setHealth(20);
		p.setLevel(0);
		p.setExp(0);
		p.getInventory().setHelmet(new ItemStack(Material.AIR));
		p.getInventory().setLeggings(new ItemStack(Material.AIR));
		p.getInventory().setBoots(new ItemStack(Material.AIR));
		p.getInventory().setChestplate(new ItemStack(Material.AIR));
		TitleAPI.sendTabTitle(p, "§8===================§9 Jaylos.net §7- §eSkywars §8===================",
				ChatColor.GRAY + "Reporte Spieler mit " + ChatColor.RED + "/report " + ChatColor.GRAY
						+ "oder erstelle eine Party mit" + ChatColor.DARK_PURPLE + " /party");
//		
		ItemStack item = new ItemStack(Material.CHEST);
		ItemMeta meta = item.getItemMeta();
		meta.setDisplayName(ChatColor.YELLOW+"Kit Auswahl §8(§7Rechtsklick§8)");
		item.setItemMeta(meta);
		p.getInventory().setItem(0, item);
//		
		for (PotionEffect effect : p.getActivePotionEffects()) {
			p.removePotionEffect(effect.getType());
		}
		Double x1 = Main.loc.getDouble("spawn.x");
		Double y1 = Main.loc.getDouble("spawn.y");
		Double z1 = Main.loc.getDouble("spawn.z");
		Float yaw1 = (float) Main.loc.getDouble("spawn.yaw");
		Float pitch1 = (float) Main.loc.getDouble("spawn.pitch");
		World w1 = Bukkit.getWorld(Main.loc.getString("spawn.world"));
		Location lobbyspawn = new Location(w1,x1,y1,z1,yaw1,pitch1);
		p.teleport(lobbyspawn);
		p.setGameMode(GameMode.SURVIVAL);
//		
		if(Bukkit.getOnlinePlayers().size() >= Integer.parseInt(Main.loc.getString(Main.mapname + ".minplayers"))){
			startcount();
		}
		de.gamelos.scoreboard.LobbyBoard.createScoreboard();
		if(ranking){
		Bukkit.getScheduler().scheduleSyncDelayedTask(Bukkit.getPluginManager().getPlugin("Skywars"), new Runnable(){

			@Override
			public void run() {
				Ranking.updateranking();
			}
			
		}, 20*2);
		ranking = false;
		}
//		??????????????????????????????????????????
		if(!de.gamelos.stats.SQLStats.playerExists(p.getUniqueId().toString())){
			p.sendMessage(ChatColor.RED+"Das Führen eines Waterfights ist auf diesem Server verboten und wird durch eine Pluginfunktion erschwert!");
		}
//		??????????????????????????????????????????
		}else{
			Bukkit.getScheduler().scheduleSyncDelayedTask(pl, new Runnable(){
				@Override
				public void run() {
					for(Player pp:Bukkit.getOnlinePlayers()){
						if(!Main.spectatorplayer.contains(pp)){
							pp.hidePlayer(e.getPlayer());
						}
					}
				}
			},20);
			e.setJoinMessage(null);
			e.getPlayer().setHealth(20);
			e.getPlayer().setFoodLevel(20);
			for(Player pp : Main.playeringame){
				pp.hidePlayer(e.getPlayer());
			}
			TitleAPI.sendTabTitle(e.getPlayer(), "§8===================§9 Jaylos.net §7- §eSkywars §8===================",
					ChatColor.GRAY + "Reporte Spieler mit " + ChatColor.RED + "/report " + ChatColor.GRAY
							+ "oder erstelle eine Party mit" + ChatColor.DARK_PURPLE + " /party");
			setspectator(e.getPlayer());
		}
	}
	
	public static void join(Player p){
		p.setFireTicks(0);
		if(Main.ingame == false){
			for(Player pp:Bukkit.getOnlinePlayers()){
				pp.sendMessage(Main.Prefix+JaylosAPI.getchatname(p, pp)+ChatColor.DARK_GRAY+" ist dem Spiel beigetreten!");
			}
//		
		
		if(de.gamelos.stats.Kits.getKit(p.getUniqueId().toString(), p.getName())!=null){
		Main.kit.put(p, de.gamelos.stats.Kits.getKit(p.getUniqueId().toString(), p.getName()));
		}
		p.getInventory().clear();
		p.setFoodLevel(20);
		p.setHealth(20);
		p.setLevel(0);
		p.setExp(0);
		p.getInventory().setHelmet(new ItemStack(Material.AIR));
		p.getInventory().setLeggings(new ItemStack(Material.AIR));
		p.getInventory().setBoots(new ItemStack(Material.AIR));
		p.getInventory().setChestplate(new ItemStack(Material.AIR));
		TitleAPI.sendTabTitle(p, "§8===================§9 Jaylos.net §7- §eSkywars §8===================",
				ChatColor.GRAY + "Reporte Spieler mit " + ChatColor.RED + "/report " + ChatColor.GRAY
						+ "oder erstelle eine Party mit" + ChatColor.DARK_PURPLE + " /party");
//		
		ItemStack item = new ItemStack(Material.CHEST);
		ItemMeta meta = item.getItemMeta();
		meta.setDisplayName(ChatColor.YELLOW+"Kit Auswahl §8(§7Rechtsklick§8)");
		item.setItemMeta(meta);
		p.getInventory().setItem(0, item);
//		
		for (PotionEffect effect : p.getActivePotionEffects()) {
			p.removePotionEffect(effect.getType());
		}
		Double x1 = Main.loc.getDouble("spawn.x");
		Double y1 = Main.loc.getDouble("spawn.y");
		Double z1 = Main.loc.getDouble("spawn.z");
		Float yaw1 = (float) Main.loc.getDouble("spawn.yaw");
		Float pitch1 = (float) Main.loc.getDouble("spawn.pitch");
		World w1 = Bukkit.getWorld(Main.loc.getString("spawn.world"));
		Location lobbyspawn = new Location(w1,x1,y1,z1,yaw1,pitch1);
		p.teleport(lobbyspawn);
		p.setGameMode(GameMode.SURVIVAL);
//		
		if(Bukkit.getOnlinePlayers().size() >= Integer.parseInt(Main.loc.getString(Main.mapname + ".minplayers"))){
			startcount();
		}
		de.gamelos.scoreboard.LobbyBoard.createScoreboard();
		}else{
			p.setHealth(20);
			p.setFoodLevel(20);
			for(Player pp : Main.playeringame){
				pp.hidePlayer(p);
			}
			TitleAPI.sendTabTitle(p, "§8===================§9 Jaylos.net §7- §eSkywars §8===================",
					ChatColor.GRAY + "Reporte Spieler mit " + ChatColor.RED + "/report " + ChatColor.GRAY
							+ "oder erstelle eine Party mit" + ChatColor.DARK_PURPLE + " /party");
			setspectator(p);
		}
	}
	
	@EventHandler
	public void onquit(PlayerQuitEvent e){
	
	if(Main.spectatorplayer.contains(e.getPlayer())){
		e.setQuitMessage(null);
	}else if(damage.containsKey(e.getPlayer())){
//			
			if(Main.playeringame.contains(e.getPlayer())){
				if(onendgame == false){
				de.gamelos.stats.SQLStats.addTode(e.getPlayer().getUniqueId().toString(), 1, e.getPlayer().getName());
				}
			Player p = e.getPlayer();
			String killer2 = damage.get(p);
			if(Bukkit.getPlayer(killer2)!=null){
			Player killer1 = Bukkit.getPlayer(killer2);
//			=====================
			e.setQuitMessage(null);
			if(onendgame == false){
			for(Player pp:Bukkit.getOnlinePlayers()){
				pp.sendMessage(JaylosAPI.getchatname(p, pp)+ChatColor.GRAY+" wurde von "+JaylosAPI.getchatname(killer1, pp)+ChatColor.GRAY+" getötet");
			}
			de.gamelos.stats.SQLStats.addKills(killer1.getUniqueId().toString(), 1, killer1.getName());
			de.gamelos.stats.SQLStats.addCoins(killer1.getUniqueId().toString(), 100, killer1.getName());
			killer1.playSound(killer1.getLocation(), Sound.LEVEL_UP, 1F, 1F);
			if(Main.kills.containsKey(killer1)){
			int i = Main.kills.get(killer1);
			i++;
			Main.kills.remove(killer1);
			Main.kills.put(killer1, i);
			}else{
			Main.kills.put(killer1, 1);
			}
			p.sendMessage(ChatColor.RED+"Du wurdest von "+JaylosAPI.getchatname(killer1, p)+" getötet");
//			
			double r = killer1.getHealth()/2;
			double b = Math.round(r*100)/100.0; 
			p.sendMessage(ChatColor.RED+"Du wurdest von "+JaylosAPI.getchatname(killer1, p)+" getötet");
			p.sendMessage(ChatColor.DARK_GRAY+"Er hatte noch: "+ChatColor.GRAY+b+ChatColor.RED+"❤");
//		
			removeplayer(e.getPlayer());
			de.gamelos.scoreboard.ingameboard.set(killer1);
			checkend();
			for(Player pp:Main.playeringame){
				de.gamelos.scoreboard.ingameboard.updateSpielerzahl(pp);
				}
			}else{
				for(Player pp:Bukkit.getOnlinePlayers()){
					pp.sendMessage(Main.Prefix+JaylosAPI.getchatname(e.getPlayer(), pp)+ChatColor.DARK_GRAY+" hat das Spiel verlassen!");
				}
			}
			}else{
				e.setQuitMessage(null);
				for(Player pp:Bukkit.getOnlinePlayers()){
					pp.sendMessage(Main.Prefix+JaylosAPI.getchatname(e.getPlayer(), pp)+ChatColor.DARK_GRAY+" hat das Spiel verlassen!");
				}
				removeplayer(e.getPlayer());
				checkend();
				for(Player pp:Main.playeringame){
					de.gamelos.scoreboard.ingameboard.updateSpielerzahl(pp);
				}
			}
//			
		}else{
			e.setQuitMessage(null);
		}
		for(Player pp:Main.playeringame){
		de.gamelos.scoreboard.ingameboard.updateSpielerzahl(pp);
		}
		if(onendgame == false){
			e.setQuitMessage(null);
		removeplayer(e.getPlayer());
		checkend();
		}else{
			e.setQuitMessage(null);
//			for(Player pp:Bukkit.getOnlinePlayers()){
//				pp.sendMessage(Main.Prefix+JaylosAPI.getchatname(e.getPlayer(), pp)+ChatColor.DARK_GRAY+" hat das Spiel verlassen!");
//			}
		}
		}else{
			e.setQuitMessage(null);
			for(Player pp:Bukkit.getOnlinePlayers()){
				pp.sendMessage(Main.Prefix+JaylosAPI.getchatname(e.getPlayer(), pp)+ChatColor.DARK_GRAY+" hat das Spiel verlassen!");
			}
			removeplayer(e.getPlayer());
			checkend();
			for(Player pp:Main.playeringame){
				de.gamelos.scoreboard.ingameboard.updateSpielerzahl(pp);
				}
		}
	if(Main.playeringame.contains(e.getPlayer())){
		removeplayer(e.getPlayer());
		}
	
	if(damage.containsKey(e.getPlayer())){
		damage.remove(e.getPlayer());
	}
	
	if(Main.ingame == false && onendgame == false){
		if(Bukkit.getOnlinePlayers().size() <= Integer.parseInt(Main.loc.getString(Main.mapname + ".minplayers"))){
			if(startcount == true) {
			Bukkit.broadcastMessage(Main.Prefix+ChatColor.RED+"Der Countdown wurde abgebrochen");
			Bukkit.getScheduler().cancelTask(count);
			startcount = false;
			}
		}
	}
	}
	
	@EventHandler
	public void oninteract(PlayerInteractEntityEvent e){
		if(e.getPlayer() == ((Player)e.getRightClicked())){
			e.setCancelled(true);
		}
	}
	
	
	@SuppressWarnings("null")
	@EventHandler
	public void onkill(PlayerDeathEvent e){
		removeplayer(e.getEntity());
		checkend();
		
		for(Player pp:Main.playeringame){
			de.gamelos.scoreboard.ingameboard.updateSpielerzahl(pp);
			}
		
		for(Player pp:Main.playeringame){
			de.gamelos.scoreboard.ingameboard.updateSpielerzahl(pp);
		}
		for(Player pp : Main.spectatorplayer){
			de.gamelos.scoreboard.Spectator.update(pp);	
		}
		
		for(Player pp: Main.playeringame){
			pp.hidePlayer(e.getEntity());
		}
		
		Player p = e.getEntity();
		Player killer = p.getKiller();
		de.gamelos.stats.SQLStats.addTode(p.getUniqueId().toString(), 1, p.getName());
		if(killer == null){
			if(damage.containsKey(p)){
				String killer2 = damage.get(p);
				if(Bukkit.getPlayer(killer2)!=null){
				Player killer1 = Bukkit.getPlayer(killer2);
				if(!Main.spectatorplayer.contains(killer1)){
//				=====================
				e.setDeathMessage(null);
				for(Player pp:Bukkit.getOnlinePlayers()){
					pp.sendMessage(Main.Prefix+JaylosAPI.getchatname(p, pp)+ChatColor.GRAY+" wurde von "+JaylosAPI.getchatname(killer1, pp)+ChatColor.GRAY+" getötet");
				}
				killer.playSound(killer.getLocation(), Sound.LEVEL_UP, 1F, 1F);
				de.gamelos.stats.SQLStats.addKills(killer.getUniqueId().toString(), 1, killer.getName());
				de.gamelos.stats.SQLStats.addCoins(killer.getUniqueId().toString(), 100, killer.getName());
				killer1.playSound(killer1.getLocation(), Sound.LEVEL_UP, 1F, 1F);
				if(Main.kills.containsKey(killer1)){
				int i = Main.kills.get(killer1);
				i++;
				Main.kills.remove(killer1);
				Main.kills.put(killer1, i);
				}else{
				Main.kills.put(killer1, 1);
				}
				double r = killer1.getHealth()/2;
				double b = Math.round(r*100)/100.0; 
				p.sendMessage(ChatColor.RED+"Du wurdest von "+JaylosAPI.getchatname(killer, p)+" getötet");
				p.sendMessage(ChatColor.DARK_GRAY+"Er hatte noch: "+getherzen(killer1.getHealth()));
	//
				de.gamelos.scoreboard.ingameboard.set(killer1);
				}else{
					e.setDeathMessage(null);
					for(Player pp:Bukkit.getOnlinePlayers()){
						pp.sendMessage(Main.Prefix+JaylosAPI.getchatname(e.getEntity(), pp)+ChatColor.GRAY+" ist gestorben");
					}
				}
				}else{
					e.setDeathMessage(null);
					for(Player pp:Bukkit.getOnlinePlayers()){
						pp.sendMessage(Main.Prefix+JaylosAPI.getchatname(e.getEntity(), pp)+ChatColor.GRAY+" ist gestorben");
					}
				}
//				=====================
			}else{
				e.setDeathMessage(null);
				for(Player pp:Bukkit.getOnlinePlayers()){
					pp.sendMessage(Main.Prefix+JaylosAPI.getchatname(e.getEntity(), pp)+ChatColor.GRAY+" ist gestorben");
				}
			}
		}else{
			e.setDeathMessage(null);
			for(Player pp:Bukkit.getOnlinePlayers()){
				pp.sendMessage(Main.Prefix+JaylosAPI.getchatname(e.getEntity(), pp)+ChatColor.GRAY+" wurde von "+JaylosAPI.getchatname(killer, pp)+ChatColor.GRAY+" getötet");
			}
			killer.playSound(killer.getLocation(), Sound.LEVEL_UP, 1F, 1F);
			de.gamelos.stats.SQLStats.addKills(killer.getUniqueId().toString(), 1, killer.getName());
			de.gamelos.stats.SQLStats.addCoins(killer.getUniqueId().toString(), 100, killer.getName());
			killer.playSound(killer.getLocation(), Sound.LEVEL_UP, 1F, 1F);
			if(Main.kills.containsKey(killer)){
			int i = Main.kills.get(killer);
			i++;
			Main.kills.remove(killer);
			Main.kills.put(killer, i);
			}else{
			Main.kills.put(killer, 1);
			}
			double r = killer.getHealth()/2;
			double b = Math.round(r*100)/100.0; 
			p.sendMessage(ChatColor.RED+"Du wurdest von "+JaylosAPI.getchatname(killer, p)+" getötet");
			p.sendMessage(ChatColor.DARK_GRAY+"Er hatte noch: "+getherzen(killer.getHealth()));
//	
			de.gamelos.scoreboard.ingameboard.set(killer);
		}
		if(damage.containsKey(e.getEntity())){
			damage.remove(e.getEntity());
		}	
	}
	
	
	static int count;
	public static int num = 30;
	public static boolean startcount = false;
	public static int bbbb = 3;
	public static int cccc = 0;
	public static int icount = 30;
	@SuppressWarnings("deprecation")
	public static void startcount(){
		
		
		if(Main.ingame == false){
			if(startcount == false){
				startcount = true;
		Bukkit.broadcastMessage(Main.Prefix+"Der Countdown wurde gestartet");
		
		count = Bukkit.getScheduler().scheduleAsyncRepeatingTask(pl, new Runnable(){
			
			@Override
			public void run() {
				if(icount>1){
					
					icount--;
					
					float exp = (float) ((double) icount / (double) num);
					
					for(Player pp:Bukkit.getOnlinePlayers()){
						pp.setLevel(icount);
						pp.setExp(exp);
					}
					
					
					
					if(icount==50){
						Bukkit.broadcastMessage(Main.Prefix+"Die Runde Startet in "+ChatColor.YELLOW+icount+ChatColor.DARK_GRAY+" Sekunden");
						for(Player pp:Bukkit.getOnlinePlayers()){
							pp.playSound(pp.getLocation(), Sound.NOTE_BASS, 1F, 1F);
						}
					}
					if(icount==40){
						Bukkit.broadcastMessage(Main.Prefix+"Die Runde Startet in "+ChatColor.YELLOW+icount+ChatColor.DARK_GRAY+" Sekunden");
						for(Player pp:Bukkit.getOnlinePlayers()){
							pp.playSound(pp.getLocation(), Sound.NOTE_BASS, 1F, 1F);
						}
					}
					if(icount==30){
						Bukkit.broadcastMessage(Main.Prefix+"Die Runde Startet in "+ChatColor.YELLOW+icount+ChatColor.DARK_GRAY+" Sekunden");
						for(Player pp:Bukkit.getOnlinePlayers()){
							pp.playSound(pp.getLocation(), Sound.NOTE_BASS, 1F, 1F);
						}
					}
					if(icount==20){
						Bukkit.broadcastMessage(Main.Prefix+"Die Runde Startet in "+ChatColor.YELLOW+icount+ChatColor.DARK_GRAY+" Sekunden");
						for(Player pp:Bukkit.getOnlinePlayers()){
							pp.playSound(pp.getLocation(), Sound.NOTE_BASS, 1F, 1F);
						}
					}
					
					if(icount==5){
						for(Player pp:Bukkit.getOnlinePlayers()){
						TitleAPI.sendFullTitle(pp, 20, 60, 20, ChatColor.YELLOW+"SkyWars", Main.mapname);
						}
					}
					
					if(icount==10){
						Bukkit.broadcastMessage(Main.Prefix+"Die Runde Startet in "+ChatColor.YELLOW+icount+ChatColor.DARK_GRAY+" Sekunden");
						for(Player pp:Bukkit.getOnlinePlayers()){
							pp.playSound(pp.getLocation(), Sound.NOTE_BASS, 1F, 1F);
						}
					}
					if(icount<=3){
						Bukkit.broadcastMessage(Main.Prefix+"Die Runde Startet in "+ChatColor.YELLOW+icount+ChatColor.DARK_GRAY+" Sekunden");
						for(Player pp:Bukkit.getOnlinePlayers()){
							pp.playSound(pp.getLocation(), Sound.NOTE_BASS, 1F, 1F);
						}
					}
					
					if(icount==15){
						String deletworld = "gameworld";
						Bukkit.getServer().unloadWorld(deletworld, true);
						try {
						FileUtils.deleteDirectory(new File(deletworld));
						}catch (IOException e){
							e.printStackTrace();
						}
					}
					
					if(icount==5){
						kopyWorld("Maps/Skywars/"+Main.mapname, "gameworld");
					}
					
					if(icount==2){
						Bukkit.getScheduler().scheduleSyncDelayedTask(pl, new Runnable(){
							@Override
							public void run() {
								for(org.bukkit.entity.Entity ent : Bukkit.getWorld("gameworld").getEntities()){
									if(!(ent instanceof Player)){
										ent.remove();
									}
								}
								Bukkit.getWorld("gameworld").setTime(1000);
							}
						},0);
					}
					
				}else{
					CloudServer.getInstance().changeToIngame();
					CloudServer.getInstance().setMotdAndUpdate("ingame");
					Bukkit.getScheduler().cancelTask(count);
					Bukkit.getScheduler().cancelTask(splzcount);
					JaylosAPI.showrang(false);
					Bukkit.broadcastMessage(Main.Prefix+ChatColor.GREEN+"Die Runde Startet");
					for(Player pp:Bukkit.getOnlinePlayers()){
						pp.playSound(pp.getLocation(), Sound.LEVEL_UP, 1F, 1F);
					}
					Main.ingame = true;
					
					for(Player pp:Bukkit.getOnlinePlayers()){
						Main.playeringame.add(pp);
					}
					
					Bukkit.getScheduler().scheduleSyncDelayedTask(pl, new Runnable(){
						@Override
						public void run() {
							int i = 1;
							for(Player pp:Bukkit.getOnlinePlayers()){
								pp.setLevel(0);
								pp.setExp(0);
								pp.getInventory().clear();
								pp.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 20*5, 100));
								
								
								if(Main.loc.getString(Main.mapname+".spawns."+i+".x")!= null){
									pp.setFallDistance(0);
									Double x1 = Main.loc.getDouble(Main.mapname+".spawns."+i+".x");
									Double y1 = Main.loc.getDouble(Main.mapname+".spawns."+i+".y");
									Double z1 = Main.loc.getDouble(Main.mapname+".spawns."+i+".z");
									Float yaw1 = (float) Main.loc.getDouble(Main.mapname+".spawns."+i+".yaw");
									Float pitch1 = (float) Main.loc.getDouble(Main.mapname+".spawns."+i+".pitch");
									World w1 = Bukkit.getWorld("gameworld");
									Location lobbyspawn = new Location(w1,x1,y1,z1,yaw1,pitch1);
									pp.teleport(lobbyspawn);
									Kits.giveGame1Kits(pp);
									de.gamelos.scoreboard.ingameboard.set(pp);
									de.gamelos.stats.SQLStats.addGames(pp.getUniqueId().toString(), 1, pp.getName());
								}
								i++;
//								
							}
						}
					}, 0);
					startschutzzeit();
					nomove=true;
					cccc = Bukkit.getScheduler().scheduleSyncRepeatingTask(pl, new Runnable(){
						@Override
						public void run() {
							if(bbbb>=0){
								bbbb--;
								if(bbbb == 3){
									for(Player pp : Bukkit.getOnlinePlayers()){
									TitleAPI.sendTitle(pp, 5, 28, 5, ChatColor.YELLOW+"3");
									}
								}else if(bbbb == 2){
									for(Player pp : Bukkit.getOnlinePlayers()){
										TitleAPI.sendTitle(pp, 5, 28, 5, ChatColor.GREEN+"2");
										}
								}else if(bbbb == 1){
									for(Player pp : Bukkit.getOnlinePlayers()){
										TitleAPI.sendTitle(pp, 5, 28, 5, ChatColor.RED+"1");
										}
								}
							}else{
								Bukkit.getScheduler().cancelTask(cccc);
								nomove = false;
								Kommpass.startkompass();
								Bukkit.broadcastMessage(Main.Prefix+ChatColor.GREEN+"Du kannst dich absofort Bewegen");
								for(Player pp:Bukkit.getOnlinePlayers()){
									for(PotionEffect ee:pp.getActivePotionEffects()){
										pp.removePotionEffect(ee.getType());
									}
								
								}
							}
						}
					}, 20, 20);
					
				}
			}
		}, 20, 20);
		}
		}
		
	}
	public static boolean onendgame = false;
	
	public static void endgame(){
		if(onendgame == false){
			onendgame = true;
		Bukkit.getScheduler().scheduleSyncRepeatingTask(pl, new Runnable(){
			int i = 30;
			@Override
			public void run() {
				
				if(i>=-3){
					
					if(i == 30){
						Bukkit.broadcastMessage(Main.Prefix+"Der Server startet in "+ChatColor.YELLOW+i+ChatColor.DARK_GRAY+" Sekunden neu");
					}
					
					if(i==20){
						Bukkit.broadcastMessage(Main.Prefix+"Der Server startet in "+ChatColor.YELLOW+i+ChatColor.DARK_GRAY+" Sekunden neu");
					}
					
					if(i<=10){
						Bukkit.broadcastMessage(Main.Prefix+"Der Server startet in "+ChatColor.YELLOW+i+ChatColor.DARK_GRAY+" Sekunden neu");
					}
					
					if(i==0){
						for(Player pp:Bukkit.getOnlinePlayers()){
							pp.kickPlayer("");
						}
					}
					
				}else{
					Bukkit.shutdown();
				}
				
				
				i--;
			}
		}, 20, 20);
		}
	}
	
	
	@EventHandler
	public void onbrak(BlockBreakEvent e){
		if(Main.ingame == false||Main.spectatorplayer.contains(e.getPlayer())||isend){
			e.setCancelled(true);
		}
	}
	
	@EventHandler
	public void onplace(BlockPlaceEvent e){
		if(Main.ingame == false||Main.spectatorplayer.contains(e.getPlayer())||isend){
			e.setCancelled(true);
		}
	}
	
	@EventHandler
	public void onfood(FoodLevelChangeEvent e){
		if(Main.ingame == false||Main.spectatorplayer.contains(e.getEntity())||isend||Main.schutzzeit){
			e.setCancelled(true);
		}
	}
	
	@EventHandler
	public void onhealth(EntityDamageEvent e){
		if(Main.ingame == false||Main.spectatorplayer.contains(e.getEntity())||isend||Main.schutzzeit){
			e.setCancelled(true);
		}
	}
	
	@EventHandler
	public void ondrop(PlayerDropItemEvent e){
		if(Main.ingame == false||Main.spectatorplayer.contains(e.getPlayer())||isend){
			e.setCancelled(true);
		}
	}
	
	@EventHandler
	public void oninv(InventoryClickEvent e){
		if(Main.ingame == false){
			e.setCancelled(true);
		}
	}
	
	
public static void kopyWorld(String worldtocopyname, String newname){
		
		Bukkit.getServer().unloadWorld(worldtocopyname, true);
		
		try {
			FileUtils.copyDirectory(new File(worldtocopyname), new File(newname));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		Bukkit.createWorld(new WorldCreator(newname));
		
	}


public static void removeplayer(Player p){
	Main.playeringame.remove(p);
}

public static boolean isend = false;

@SuppressWarnings("deprecation")
public static void checkend(){
	if(Main.ingame == true&&isend == false){
	if(Main.playeringame.size() <= 1){
		isend = true;
		endgame();
		Player winner = null;
		for(Player pp: Main.playeringame){
			winner = pp;
			de.gamelos.stats.SQLStats.addWins(pp.getUniqueId().toString(), 1, winner.getName());
			de.gamelos.stats.SQLStats.addCoins(pp.getUniqueId().toString(), 500, pp.getName());
		}
		JaylosAPI.endunnick();
		for(Player p: Bukkit.getOnlinePlayers()){
		TitleAPI.sendFullTitle(p, 20, 30, 20, PermissionsAPI.getchatprefix(winner.getUniqueId().toString())+winner.getName(), "hat die Runde Gewonnen");
		}
		Bukkit.getScheduler().scheduleSyncDelayedTask(Bukkit.getPluginManager().getPlugin("Skywars"), new Runnable() {	
			@Override
			public void run() {
				for(Player pp:Bukkit.getOnlinePlayers()) {
					if(Main.spectatorplayer.contains(pp)) {
						Spectator.update(pp);
					}else {
					ingameboard.set(pp);
					}
				}
			}
		}, 20);
	}
	}
}

@EventHandler
public void blockdamage(BlockDamageEvent e){
	if(Main.spectatorplayer.contains(e.getPlayer())){
		e.setCancelled(true);
	}
}

@EventHandler
public void onplace1(BlockPlaceEvent e){
	Location loc = e.getBlock().getLocation();
	for(Player pp :Main.spectatorplayer){
		if(e.getPlayer()!=pp){
		if(pp.getLocation().getBlock().getLocation().equals(loc)||pp.getLocation().getBlock().getLocation().subtract(0, 1, 0).equals(loc)||pp.getLocation().getBlock().getLocation().add(0, 1, 0).equals(loc)){
			pp.teleport(pp.getLocation().add(2, 0, 0));
		}
		}
	}
}

@EventHandler
public void onklick1(PlayerInteractEvent e){
	if(e.getAction()==Action.RIGHT_CLICK_BLOCK){
		for(Player pp :Main.spectatorplayer){
			if(e.getPlayer()!=pp){
			if(e.getClickedBlock().getLocation().distance(pp.getLocation().getBlock().getLocation())<=1.5){
				pp.teleport(e.getPlayer().getLocation());	
			}
			}
		}
	}
}

@EventHandler
public void onSpecSpeed(PlayerItemHeldEvent e){
	Player p = e.getPlayer();
	if(Main.spectatorplayer.contains(p)){
		int slot = e.getNewSlot()+1;
		float speed = (float)slot/10;
		p.setLevel(slot);
		p.setFlySpeed(speed);
	}
}

@EventHandler
public void onrespawn(PlayerRespawnEvent e){
	Player p = e.getPlayer();
	
	p.getInventory().clear();
	p.getInventory().setHelmet(new ItemStack(Material.AIR));
	p.getInventory().setLeggings(new ItemStack(Material.AIR));
	p.getInventory().setBoots(new ItemStack(Material.AIR));
	p.getInventory().setChestplate(new ItemStack(Material.AIR));
	p.spigot().setCollidesWithEntities(false);
	e.setRespawnLocation(Bukkit.getWorld("gameworld").getSpawnLocation());
	
	for (PotionEffect effect : p.getActivePotionEffects()) {
		p.removePotionEffect(effect.getType());
	}
	
	ItemStack item = new ItemStack(Material.COMPASS);
	ItemMeta meta = item.getItemMeta();
	meta.setDisplayName(ChatColor.YELLOW+"Spieler Teleporter "+ChatColor.GRAY+"(Rechtsklick)");
	item.setItemMeta(meta);
	p.getInventory().setItem(0, item);
	Main.spectatorplayer.add(p);
	p.setAllowFlight(true);
	Bukkit.getScheduler().scheduleSyncDelayedTask(pl, new Runnable(){
		@Override
		public void run() {
			p.addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY, Integer.MAX_VALUE, 0));
				de.gamelos.scoreboard.Spectator.update(p);	
		}
	}, 0);
	for(Player pp:Bukkit.getOnlinePlayers()){
		p.showPlayer(pp);
	}
}

public static void setspectator(Player p){
	p.getInventory().clear();
	p.getInventory().setHelmet(new ItemStack(Material.AIR));
	p.getInventory().setLeggings(new ItemStack(Material.AIR));
	p.getInventory().setBoots(new ItemStack(Material.AIR));
	p.getInventory().setChestplate(new ItemStack(Material.AIR));
	p.teleport(Bukkit.getWorld("gameworld").getSpawnLocation());
	p.spigot().setCollidesWithEntities(false);
	
	for (PotionEffect effect : p.getActivePotionEffects()) {
		p.removePotionEffect(effect.getType());
	}
	
	Bukkit.getScheduler().scheduleSyncDelayedTask(pl, new Runnable(){
		@Override
		public void run() {
			p.addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY, Integer.MAX_VALUE, 0));
			de.gamelos.scoreboard.Spectator.update(p);	
		}
	}, 0);
	
	ItemStack item = new ItemStack(Material.COMPASS);
	ItemMeta meta = item.getItemMeta();
	meta.setDisplayName(ChatColor.YELLOW+"Spieler Teleporter "+ChatColor.GRAY+"(Rechtsklick)");
	item.setItemMeta(meta);
	p.getInventory().setItem(0, item);
	Main.spectatorplayer.add(p);
	p.setAllowFlight(true);
}

@EventHandler
public void onklick(PlayerInteractEvent e){
	Player p = e.getPlayer();
	if(e.getAction() == Action.RIGHT_CLICK_AIR||e.getAction() == Action.RIGHT_CLICK_BLOCK){
		if(e.getPlayer().getItemInHand().getType() == Material.COMPASS){
		if(Main.spectatorplayer.contains(p)){
			Inventory inv = Bukkit.createInventory(null, 9*3, ChatColor.YELLOW+"Teleporter");
			
			for(Player pp : Main.playeringame){
				ItemStack skull = new ItemStack(Material.SKULL_ITEM, 1, (short) 3);
				SkullMeta skullmeta = (SkullMeta) skull.getItemMeta();
				skullmeta.setDisplayName(ChatColor.AQUA+pp.getName());
				skullmeta.setOwner(pp.getName());
				skull.setItemMeta(skullmeta);
				inv.addItem(skull);
				
			}
			p.openInventory(inv);
		}
		}else if(e.getPlayer().getItemInHand().getType() ==Material.CHEST){
			if(Main.ingame==false&&Main.schutzzeit==false){
			Kits.openkitsmenü(p);
			}
		}
	}
}


@EventHandler
public void onklick(InventoryClickEvent e){
	Player p = (Player) e.getWhoClicked();
	if(e.getInventory().getTitle().equals(ChatColor.YELLOW+"Teleporter")){
		if(e.getCurrentItem().getItemMeta().getDisplayName()!=null){
		String s = ChatColor.stripColor(e.getCurrentItem().getItemMeta().getDisplayName());
		Player pp = Bukkit.getPlayer(s);
		p.teleport(pp);
		p.closeInventory();
		}
	}else if(e.getClickedInventory().getName().equals(ChatColor.YELLOW+"Kits")){
		e.setCancelled(true);
		if(e.getCurrentItem() !=null){
		String displaynamename = ChatColor.stripColor(e.getCurrentItem().getItemMeta().getDisplayName());
		
		if(displaynamename.contains("(Gekauft)")){
		String name = displaynamename.substring(0, displaynamename.length()-10);
		
//		
		String kit = name.replace(""+ChatColor.YELLOW, "");
		if(kit != null){
		de.gamelos.stats.Kits.setKit(p.getUniqueId().toString(), kit);
		}
		if(Main.kit.containsKey(p)){
			if(name.equals("Starter-Kit")){
				Main.kit.remove(p);
			}else{
			Main.kit.replace(p, kit);
			}
		}else{
			if(name.equals("Starter-Kit")){
				Main.kit.remove(p);
			}else{
			Main.kit.put(p, kit);
			}
		}
		p.sendMessage(Main.Prefix+ChatColor.GREEN+"Du hast das Kit "+name+ChatColor.GREEN+" erfolgreich ausgewählt!");
		p.closeInventory();
		de.gamelos.scoreboard.LobbyBoard.createScoreboard();
		JaylosAPI.updaterang();
//		
		}else{
			p.closeInventory();
			int coins = SQLStats.getCoins(p.getUniqueId().toString(), p.getName());
			String[] tt = displaynamename.split(":");
			String kit = tt[0].substring(0, tt[0].length()-8);
			String s = tt[1].substring(1, tt[1].length()-1);
			int preis = Integer.parseInt(s.replace( ".", "" ));
			if(preis > coins){
				p.sendMessage(Main.Prefix+ChatColor.RED+"Du hast zu wenig Coins um dieses Kit zu kaufen");
			}else{
				SQLStats.removeCoins(p.getUniqueId().toString(), preis, p.getName());
				MySQLKits.addKit(p.getUniqueId().toString(), kit);
				p.sendMessage(Main.Prefix+ChatColor.GREEN+"Du hast das Kit erfolgreich gekauft");
			}
		}
	}
	}
}
public static ArrayList<Player>ansicht = new ArrayList<>();

//@EventHandler
//public void onInteract(PlayerInteractEntityEvent e){
//	if(e.getRightClicked()!=e.getPlayer()){
//	if(Main.spectatorplayer.contains(e.getPlayer())){
//    if(e.getRightClicked() instanceof Player){
//    	if(e.getPlayer() != e.getRightClicked()){
//        Player p = e.getPlayer();
//        Player pp = (Player)e.getRightClicked();
//        p.sendMessage(ChatColor.YELLOW+"Du bist jetzt in der Ansicht von "+pp.getName());
//        ansicht.add(p);
//        p.teleport(pp);
//        EntityPlayer ep = ((CraftPlayer)pp).getHandle();
//		PacketPlayOutCamera cam = new PacketPlayOutCamera(ep);
//		EntityPlayer spec = ((CraftPlayer)p).getHandle();
//		spec.playerConnection.sendPacket(cam);
//    	}
//    }
//	}
//	}
//}

@EventHandler
public void onsneak(PlayerToggleSneakEvent e){
	if(ansicht.contains(e.getPlayer())){
		Player p = e.getPlayer();
//		
		EntityPlayer spec = ((CraftPlayer)p).getHandle();
		PacketPlayOutCamera cam = new PacketPlayOutCamera(spec);
		spec.playerConnection.sendPacket(cam);
//		
		ansicht.remove(p);
		p.sendMessage(ChatColor.YELLOW+"Du hast die Ansicht erfolgreich beendet");
	}
}
static int schutzcount;
public static void startschutzzeit(){
	Main.schutzzeit = true;
	schutzcount = Bukkit.getScheduler().scheduleSyncRepeatingTask(pl, new Runnable(){
		int i = 40;
		@Override
		public void run() {
			
			if(i>=1){
				
				if(i==30){
					Bukkit.broadcastMessage(Main.Prefix+"Der Schutzzeit endet in "+ChatColor.YELLOW+i+ChatColor.DARK_GRAY+" Sekunden");
				}
				
				if(i==20){
					Bukkit.broadcastMessage(Main.Prefix+"Der Schutzzeit endet in "+ChatColor.YELLOW+i+ChatColor.DARK_GRAY+" Sekunden");
				}
				
				if(i==10){
					Bukkit.broadcastMessage(Main.Prefix+"Der Schutzzeit endet in "+ChatColor.YELLOW+i+ChatColor.DARK_GRAY+" Sekunden");
					for(Player pp:Bukkit.getOnlinePlayers()){
					pp.playSound(pp.getLocation(), Sound.NOTE_BASS, 1F, 1F);
					}
				}
				
				if(i<=3){
					Bukkit.broadcastMessage(Main.Prefix+"Der Schutzzeit endet in "+ChatColor.YELLOW+i+ChatColor.DARK_GRAY+" Sekunden");
					for(Player pp:Bukkit.getOnlinePlayers()){
					pp.playSound(pp.getLocation(), Sound.NOTE_BASS, 1F, 1F);
					}
				}
				
			}else{
				Bukkit.getScheduler().cancelTask(schutzcount);
				Bukkit.broadcastMessage(ChatColor.GREEN+"Die Schutzzeit ist vorbei");
				for(Player pp:Bukkit.getOnlinePlayers()){
					pp.playSound(pp.getLocation(), Sound.NOTE_PIANO, 1F, 1F);
				}
				Main.schutzzeit = false;
			}
			
			
			i--;
		}
	}, 20, 20);
}

public static boolean nomove = false;

@EventHandler
public static void onmove(PlayerMoveEvent e){
		if(nomove){
			World w = e.getPlayer().getWorld();
			float locyaw = e.getFrom().getYaw();
			float locpitch = e.getFrom().getPitch();
			int locy = e.getFrom().getBlockY();
			int locx = e.getFrom().getBlockX();
			int locz = e.getFrom().getBlockZ();
			int lox = e.getTo().getBlockX();
			int loz = e.getTo().getBlockZ();
			if(locx != lox || locz != loz){
			Location loc = new Location(w, locx, locy, locz, locyaw, locpitch);
			e.getPlayer().teleport(loc);
			}
		}
}


public static HashMap<Player,String> damage = new HashMap<Player,String>();
@EventHandler
public void ondeath11(EntityDamageByEntityEvent e){
	if(e.getEntity() instanceof Player){
	Player p = (Player) e.getEntity();
	if(Main.spectatorplayer.contains(p)||Main.ingame==false){
		e.setCancelled(true);
		
	}else if(e.getDamager() != null){
		if(e.getDamager() instanceof Player){
	Player killer = (Player) e.getDamager();
	if(p!=killer){
	if(damage.containsKey(p)){
		damage.remove(p);
		damage.put(p, killer.getName());
	}else{
		damage.put(p, killer.getName());
	}
	}
	}
	}
	}
}

@EventHandler
public static void playerin(PlayerInteractEvent e){
	if(ansicht.contains(e.getPlayer())){
		e.setCancelled(true);
	}
}

@SuppressWarnings("deprecation")
@EventHandler
public void ondestroy(BlockBreakEvent e) {
	if (e.getPlayer().getLocation().getWorld().getName().equals("JaylosLobby")) {
		e.setCancelled(true);
	}else if(e.getBlock().getType() == Material.SNOW){
		e.getBlock().setTypeId(0);
	}else if(e.getBlock().getType() == Material.SNOW_BLOCK){
		e.getBlock().setTypeId(0);
	}
}

public static HashMap<Entity,Player>shoot =  new HashMap<>();
@SuppressWarnings("deprecation")
@EventHandler
public void ond(ProjectileHitEvent e){
	Player p = (Player) e.getEntity().getShooter();
	Entity ent = e.getEntity();
	shoot.put(ent, p);
	Bukkit.getScheduler().scheduleAsyncDelayedTask(pl, new Runnable(){
		@Override
		public void run() {
			shoot.remove(p);
		}
	},20*10);
}

@SuppressWarnings("deprecation")
@EventHandler
public void ondama(EntityDamageByEntityEvent e){
	if(e.getDamager().getType() == EntityType.FISHING_HOOK){
		Entity ent = e.getDamager();
		Player p = shoot.get(ent);
		Player p1 = (Player) e.getEntity();
		if(p!=null){
			if(Main.spectatorplayer.contains(p)||Main.ingame==false){
			}else{
			if(Main.playeringame.contains(p)&&Main.playeringame.contains(p1)){
				if(p!=p1){
					if(damage.containsKey(p1)){
						damage.remove(p1);
						damage.put(p1, p.getName());
					}else{
					damage.put(p1, p.getName());
					}
				}
			}
			}
		}
	}
	if(e.getDamager() instanceof CraftSnowball || e.getDamager() instanceof CraftEgg || e.getDamager() instanceof CraftArrow ){
		Entity ent = e.getDamager();
		Bukkit.getScheduler().scheduleAsyncDelayedTask(pl, new Runnable(){
			@Override
			public void run() {
				if(shoot.size() > 0){
					Player p = shoot.get(ent);
					Player p1 = (Player) e.getEntity();
					if(p != null){
						if(p != p1){
							if(Main.spectatorplayer.contains(p)||Main.ingame==false){	
							}else{
								if(p!=p1){
							if(damage.containsKey(p1)){
								damage.remove(p1);
								damage.put(p1, p.getName());
							}else{
							damage.put(p1, p.getName());
							}
							}
							}
						}
					}
				}
			}
		},5);
	}
}

@EventHandler
public void wheater(WeatherChangeEvent e){
	e.setCancelled(true);
}

@EventHandler
public void da(EntityDamageByEntityEvent e){
	if(Main.spectatorplayer.contains(e.getDamager())){
		e.setCancelled(true);
	}
}

@EventHandler
public void invk(InventoryClickEvent e){
	if(Main.spectatorplayer.contains(e.getWhoClicked())){
		e.setCancelled(true);
	}
}

@EventHandler
public void oin(PlayerInteractEvent e){
	if(e.getAction() == Action.RIGHT_CLICK_BLOCK){
		if(e.getClickedBlock().getType() ==Material.ENCHANTMENT_TABLE){
	if(Main.spectatorplayer.contains(e.getPlayer())){
		e.setCancelled(true);
	}
		}
	}
}

@EventHandler
public void oncommand(PlayerCommandPreprocessEvent e){
	Player p = e.getPlayer();
	String msg = e.getMessage();
	if(msg.equalsIgnoreCase("/pl")||msg.equalsIgnoreCase("/help")||msg.equalsIgnoreCase("/?")||msg.equalsIgnoreCase("/plugins")||msg.equalsIgnoreCase("/me")||msg.equalsIgnoreCase("/bukkit:plugins")||msg.equalsIgnoreCase("/bukkit:pl")||msg.equalsIgnoreCase("/bukkit:?")||msg.equalsIgnoreCase("/bukkit:help")||msg.equalsIgnoreCase("/tell")){
		e.setCancelled(true);
		p.sendMessage(ChatColor.RED+"Du hast keine Rechte dazu");
	}
}

@EventHandler
public void onfire(PlayerInteractEvent e){
	if(e.getClickedBlock()!=null){
	Location loc = e.getClickedBlock().getLocation();
	loc.add(0, 1, 0);
	if(loc.getBlock().getType()==Material.FIRE){
		if(Main.spectatorplayer.contains(e.getPlayer())){
		e.setCancelled(true);
		loc.getBlock().setType(Material.FIRE);
		}
	}
	}
}



@EventHandler
public void onbuild(BlockPlaceEvent e){
	if(!Main.spectatorplayer.contains(e.getPlayer())){
		e.setBuild(true);
	}
}

@EventHandler
public void ondamage(EntityDamageByEntityEvent e){
	if(e.getEntityType()==EntityType.PLAYER){
		if(e.getDamager().getType() == EntityType.ENDER_PEARL){
			e.setCancelled(true);
		}
	}
}

@EventHandler
public void onintera(PlayerInteractEvent e){
	if(e.getAction() == Action.RIGHT_CLICK_BLOCK||e.getAction() == Action.PHYSICAL){
	if(Main.spectatorplayer.contains(e.getPlayer())){
		e.setCancelled(true);
	}
	}
}

@EventHandler
public void onspw(EntitySpawnEvent e){
	if(e.getEntityType() == EntityType.BAT ||e.getEntityType() == EntityType.CREEPER||e.getEntityType() == EntityType.ENDERMAN||e.getEntityType() == EntityType.SKELETON||e.getEntityType() == EntityType.SLIME||e.getEntityType() == EntityType.SPIDER||e.getEntityType() == EntityType.ZOMBIE||e.getEntityType() == EntityType.ENDERMITE){
		e.setCancelled(true);
	}
}

@EventHandler
public void ontrow(PlayerInteractEvent e){
	if(e.getAction() == Action.RIGHT_CLICK_BLOCK||e.getAction() == Action.RIGHT_CLICK_AIR){
		if(nomove){
			if(e.getPlayer().getItemInHand().getType() == Material.ENDER_PEARL){
			e.setCancelled(true);
			e.getPlayer().setItemInHand(new ItemStack(Material.ENDER_PEARL));
			e.getPlayer().sendMessage(Main.Prefix+ChatColor.RED+"Du darfst noch keine Enderperle werfen!");
			}
		}
	}
}

@EventHandler
public void onthrow(ProjectileLaunchEvent e){
	if(e.getEntityType() == EntityType.ENDER_PEARL){
	Entity pearl = e.getEntity();
	pearl.setVelocity(pearl.getVelocity().multiply(1.05));
	}
}

public static ArrayList<Player> foodl = new ArrayList<>();

@EventHandler
public void onffoo(FoodLevelChangeEvent e){
	Player p = (Player) e.getEntity();
	if(foodl.contains(p)){
		foodl.remove(p);
		e.setCancelled(true);
	}else{
		foodl.add(p);
	}
}

@EventHandler
public void oneat(PlayerInteractEvent e){
	Player p = e.getPlayer();
	if(e.getAction() == Action.RIGHT_CLICK_AIR||e.getAction()==Action.RIGHT_CLICK_BLOCK){
		if(p.getItemInHand().getType() == Material.APPLE||p.getItemInHand().getType() == Material.COOKED_BEEF||p.getItemInHand().getType() == Material.RAW_BEEF||p.getItemInHand().getType() == Material.COOKED_CHICKEN||p.getItemInHand().getType() == Material.RAW_CHICKEN||p.getItemInHand().getType() == Material.RAW_FISH||p.getItemInHand().getType() == Material.COOKED_FISH||p.getItemInHand().getType() == Material.PUMPKIN_PIE||p.getItemInHand().getType() == Material.PUMPKIN_PIE){
			if(foodl.contains(p)){
				foodl.remove(p);
			}
		}
		if(e.getAction() == Action.RIGHT_CLICK_BLOCK){
			if(e.getClickedBlock().getType() == Material.CAKE_BLOCK){
				if(foodl.contains(p)){
					foodl.remove(p);
				}
			}
		}
	}
}
public static String getherzen(Double health){
	double b = health/2;
	int i;
	double rest = 0;
	String halbherzen = "";
	int zeichen = 0;
	String restherzen = "";
	     i = (int) b;
	     rest = (b-i);
	     if(rest>0){
	    		halbherzen = ChatColor.RED+"❥";  
	    		zeichen++;
	     }
	String ganzeherzen = "";    
	   for(int ii = 0; ii<i;ii++){
		   ganzeherzen = ganzeherzen+ChatColor.RED+"❤";
		   zeichen++;
	   }
	   for(int ii = 0; ii<10-zeichen;ii++){
		   restherzen = restherzen+ChatColor.GRAY+"❤";
	   }
	   return ganzeherzen+halbherzen+restherzen;
}
@EventHandler
public void onblock(BlockPlaceEvent e){
	if(e.getBlock().getLocation().getBlockY()>=115){
		e.setCancelled(true);
		e.getPlayer().sendMessage(ChatColor.RED+"Du darfst dich nicht höher Stacken");
	}
}

public static HashMap<Player,Integer> antiw = new HashMap<>();
public static void antiwater(){
	Bukkit.getScheduler().scheduleSyncRepeatingTask(Bukkit.getPluginManager().getPlugin("Skywars"), new Runnable(){
		@Override
		public void run() {
			if(Main.ingame){
			for(Player p : Bukkit.getOnlinePlayers()){
				Block b1 = p.getLocation().add(1, 0, 0).getBlock();
				Block b2 = p.getLocation().add(0, 0, 1).getBlock();
				Block b3 = p.getLocation().add(-1, 0, 0).getBlock();
				Block b4 = p.getLocation().add(0, 0, -1).getBlock();
				Block b5 = p.getLocation().getBlock();
				if(p.getLocation().add(0, -1, 0).getBlock().getType() == Material.STATIONARY_WATER&&p.getLocation().add(0, -1, 0).getBlock().getType() == Material.WATER){	
				}else{
					if(inwater(b1)||inwater(b2)||inwater(b3)||inwater(b4)||inwater(b5)){
						if(antiw.containsKey(p)){
							int i = antiw.get(p);
							i++;
							antiw.remove(p);
							antiw.put(p, i);
						}else{
							if(p.getLocation().getBlockY() <= (Main.loc.getDouble(Main.mapname+".spawns."+1+".y")-10)){
							antiw.put(p, 1);
							}
						}
						if(antiw.get(p)>=40.0){
							if(isend == false){
							p.damage(1.0);
							}
						}
						
					}else{
						if(p.getFallDistance()<2.0){
						if(antiw.containsKey(p)){
							antiw.remove(p);
							}
						}
					}
				}
				
			}
		}
		}
	}, 20, 20);
}

public static boolean inwater(Block b){
	if(b.getType() == Material.STATIONARY_WATER){
		return true;
	}else{
		return false;
	}
}

@EventHandler
public void onspawn(EntitySpawnEvent e){
	if(e.getEntityType() == EntityType.BAT||e.getEntityType() == EntityType.BLAZE||e.getEntityType() == EntityType.CAVE_SPIDER||e.getEntityType() == EntityType.CHICKEN||e.getEntityType() == EntityType.COW||e.getEntityType() == EntityType.CREEPER||e.getEntityType() == EntityType.ENDERMAN||e.getEntityType() == EntityType.ENDERMITE||e.getEntityType() == EntityType.HORSE||e.getEntityType() == EntityType.HORSE||e.getEntityType() == EntityType.OCELOT||e.getEntityType() == EntityType.PIG||e.getEntityType() == EntityType.RABBIT||e.getEntityType() == EntityType.SHEEP||e.getEntityType() == EntityType.ZOMBIE||e.getEntityType() == EntityType.SILVERFISH||e.getEntityType() == EntityType.SKELETON||e.getEntityType() == EntityType.WITCH){
		e.setCancelled(true);
	}
}

@EventHandler
public void antigoasthit(EntityDamageByEntityEvent e){
	if(e.getEntity() instanceof Player && e.getDamager() instanceof Player){
		if(e.getDamager().isDead()){
			e.setCancelled(true);
		}
	}
}

@EventHandler
public void oninter(PlayerInteractEvent e){
	if(Main.ingame == false){
		if(e.getAction() == Action.RIGHT_CLICK_BLOCK){
			if(e.getClickedBlock().getType() !=Material.WOOD_BUTTON &&e.getClickedBlock().getType() !=Material.STONE_BUTTON){
				e.setCancelled(true);
			}
		}else{
		if(e.getAction() != Action.PHYSICAL) {
			e.setCancelled(true);		
		}
		}
	}
}

 @EventHandler
 public void oni(PlayerInteractAtEntityEvent e) {
	 if(e.getRightClicked().getType() == EntityType.ARMOR_STAND) {
		 e.setCancelled(true);
	 }
 }

@EventHandler
public void onunnikc(unNickEvent e) {
	Player p = e.getplayer();
	Bukkit.getScheduler().scheduleSyncDelayedTask(Bukkit.getPluginManager().getPlugin("Skywars"), new Runnable() {
	@Override
		public void run() {
		if(Main.ingame) {
			for(Player pp:Bukkit.getOnlinePlayers()) {
				if(Main.spectatorplayer.contains(pp)) {
					Spectator.update(pp);
				}else {
				ingameboard.set(pp);
				}
			}
		}else {
			JaylosAPI.updaterang();
		}
		}
	}, 20);
}

}
