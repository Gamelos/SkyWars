package de.gamelos.skywars;

import java.io.DataOutputStream;
import java.io.IOException;

import org.apache.commons.io.output.ByteArrayOutputStream;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import net.md_5.bungee.api.ChatColor;

public class Commands implements CommandExecutor {

	@SuppressWarnings("deprecation")
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(cmd.getName().equals("sw")){
			Player p = (Player)sender;
			if(p.isOp()){
				if(args.length>=1){
				if(args[0].equals("setspawn")){
				Location loc = p.getLocation();
				Main.loc.set("spawn.x", loc.getX());
				Main.loc.set("spawn.y", loc.getY());
				Main.loc.set("spawn.z", loc.getZ());
				Main.loc.set("spawn.yaw", loc.getYaw());
				Main.loc.set("spawn.pitch", loc.getPitch());
				Main.loc.set("spawn.world", loc.getWorld().getName());
				try {
					Main.loc.save(Main.locations);
					p.sendMessage(ChatColor.GREEN+"Der Spawn wurde erfolgreich gesetzt");	
				} catch (IOException e) {
					e.printStackTrace();
				}
				}else if(args[0].equals("addmap")){
					if(args.length == 4){
						int i = Main.getmapanzahl() + 1;
						Main.loc.set("Mapnames." + i, args[1]);
						Main.loc.set(args[1] + ".maxplayers", args[2]);
						Main.loc.set(args[1] + ".minplayers", args[3]);
						try {
							Main.loc.save(Main.locations);
							Bukkit.broadcastMessage(ChatColor.GREEN + "Die Map wurde erfolgreich gespeichert");
						} catch (IOException e) {
							e.printStackTrace();
						}
						}else{
							p.sendMessage(Main.Prefix+ChatColor.RED+"/sw addmap <mapname> <maxplayers> <minplayers>");
						}
				}else if (args[0].equalsIgnoreCase("maplist")) {
					// TODO /sw maplist
					if(args.length == 1){
					int ii = 1;
					while (Main.loc.getString("Mapnames." + ii) != null) {
						Bukkit.broadcastMessage(Main.loc.getString("Mapnames." + ii));
						ii++;
					}
					}else{
						p.sendMessage(Main.Prefix+ChatColor.RED+"/sw maplist");
					}

				} else if (args[0].equalsIgnoreCase("addspawn")) {
					// TODO /sw setspawn <map>
					int i = Main.getspawnanzahl(args[1]) + 1;

					Main.loc.set(args[1] + ".spawns." + i + ".x", p.getLocation().getX());
					Main.loc.set(args[1] + ".spawns." + i + ".y", p.getLocation().getY());
					Main.loc.set(args[1] + ".spawns." + i + ".z", p.getLocation().getZ());
					Main.loc.set(args[1] + ".spawns." + i + ".yaw", p.getLocation().getYaw());
					Main.loc.set(args[1] + ".spawns." + i + ".pitch", p.getLocation().getPitch());

					try {
						Main.loc.save(Main.locations);
						Bukkit.broadcastMessage(
								ChatColor.GREEN + "Der spawn " + i + " der Map " + args[1] + " wurde erfolgreich gesetzt!");
					} catch (IOException e) {
						e.printStackTrace();
					}

				} else if (args[0].equalsIgnoreCase("setcenter")) {
					// /sw setcenter <Map> <CenterSize>
					Main.loc.set(args[1] + ".center.x", p.getLocation().getX());
					Main.loc.set(args[1] + ".center.y", p.getLocation().getY());
					Main.loc.set(args[1] + ".center.z", p.getLocation().getZ());
					double size = Double.parseDouble(args[2]);
					Main.loc.set(args[1] + ".center.size", size);

					try {
						Main.loc.save(Main.locations);
						Bukkit.broadcastMessage(
								ChatColor.GREEN + "Das Zentrum der map " + args[1] + " wurde erfolgreich gesetzt!");
					} catch (IOException e) {
						e.printStackTrace();
					}
				} else if (args[0].equalsIgnoreCase("tp")) {
					p.teleport(Bukkit.getWorld(args[1]).getSpawnLocation());
				}else{
					p.sendMessage(ChatColor.GRAY+"===============================");
					p.sendMessage(ChatColor.GOLD+"/sw addmap <mapname> <maxplayers> <minplayers>");
					p.sendMessage(ChatColor.GOLD+"/sw maplist");
					p.sendMessage(ChatColor.GOLD+"/sw addspawn <map>");
					p.sendMessage(ChatColor.GOLD+"/sw setcenter <Map> <CenterSize>");
					p.sendMessage(ChatColor.GOLD+"/sw setspawn");
					p.sendMessage(ChatColor.GOLD+"/sw tp <mapname>");
					p.sendMessage(ChatColor.GRAY+"===============================");
				}
				}else{
					p.sendMessage(ChatColor.GRAY+"===============================");
					p.sendMessage(ChatColor.GOLD+"/sw addmap <mapname> <maxplayers> <minplayers>");
					p.sendMessage(ChatColor.GOLD+"/sw maplist");
					p.sendMessage(ChatColor.GOLD+"/sw addspawn <map>");
					p.sendMessage(ChatColor.GOLD+"/sw setcenter <Map> <CenterSize>");
					p.sendMessage(ChatColor.GOLD+"/sw setspawn");
					p.sendMessage(ChatColor.GOLD+"/sw tp <mapname>");
					p.sendMessage(ChatColor.GRAY+"===============================");
				}
			}else{
				p.sendMessage(Main.Prefix+ChatColor.RED+"Du hast keine Rechte dazu");
			}
		}else if(cmd.getName().equals("promote")){
			Player p = (Player)sender;
			if(p.hasPermission("rang.premium")){
				if(promote){
			p.sendMessage(Main.Prefix+ChatColor.GREEN+"Du hast die Runde erfolgreich promotet");
			sendmsgtobungee("promote", p);
			promote = false;
			Bukkit.getScheduler().scheduleAsyncDelayedTask(Bukkit.getPluginManager().getPlugin("Skywars"), new Runnable(){
				@Override
				public void run() {
					promote = true;
				}
			}, 6000);
				}else{
					p.sendMessage(Main.Prefix+ChatColor.RED+"Die Runde wurde erst vor kurzem promotet");
				}
			}else{
				p.sendMessage(Main.Prefix+ChatColor.RED+"Du brauchst Premium um dieses Feature nutzen zu können");
			}
		}
		return false;
	}
	
	boolean promote = true;
	
    public void sendmsgtobungee(String msg, Player p) {
    	ByteArrayOutputStream b = new ByteArrayOutputStream();
		DataOutputStream out = new DataOutputStream(b);
		try {
			out.writeUTF("data");
			out.writeUTF(msg);
		} catch (IOException e) {
			e.printStackTrace();
		}
		p.sendPluginMessage(Bukkit.getPluginManager().getPlugin("Skywars"), "BungeeCord", b.toByteArray());
    }

}
