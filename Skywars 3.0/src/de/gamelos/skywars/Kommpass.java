package de.gamelos.skywars;

import java.util.ArrayList;
import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractAtEntityEvent;

public class Kommpass implements Listener {

@SuppressWarnings("deprecation")
public static void startkompass(){
	Bukkit.getScheduler().scheduleAsyncRepeatingTask(Bukkit.getPluginManager().getPlugin("Skywars"), new Runnable(){
		@Override
		public void run() {
			for(Player p : Bukkit.getOnlinePlayers()){
				if(!Main.spectatorplayer.contains(p)){
					Player pp = getnearestPlayer(p);
					p.setCompassTarget(pp.getLocation());
				}
			}
		}
	}, 20*3,20*3);
}

	@SuppressWarnings("rawtypes")
	public static HashMap<Player,ArrayList> notintarget = new HashMap<>();

	@SuppressWarnings({ "rawtypes" })
	public static Player getnearestPlayer(Player p){
		double i = 100000;
		Player Player = null;
		ArrayList list  = new ArrayList<>();
		if(notintarget.containsKey(p)){
			list = notintarget.get(p);
		}
		for(Player pp: Bukkit.getOnlinePlayers()){
			if(pp!=p){
				if(!Main.spectatorplayer.contains(pp)&&!list.contains(pp)){
				double b = p.getLocation().distance(pp.getLocation());
				if(b<i){
					i = b;
					Player = pp;
				}
				}
			}
		}
		return Player;
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@EventHandler
	public void oni(PlayerInteractAtEntityEvent e){
		if(e.getRightClicked() instanceof Player){
			Player pp = (Player)e.getRightClicked();
			Player p = e.getPlayer();
			if(p.getItemInHand().getType() == Material.COMPASS){
			ArrayList list = new ArrayList<>();
			if(notintarget.containsKey(p)){
				list = notintarget.get(p);
			}
			if(list.contains(pp)){
				p.sendMessage(Main.Prefix+"Der Spieler "+pp.getName()+" wird nun wieder vom Tracker erkannt!");
				list.remove(pp);
				notintarget.put(p, list);	
			}else{
			p.sendMessage(Main.Prefix+"Der Spieler "+pp.getName()+" wird nun nicht mehr vom Tracker erkannt!");
			list.add(pp);
			notintarget.put(p, list);
			}
			}
		}
	}
	
}