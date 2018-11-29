package de.gamelos.skywars;


import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerChatEvent;
import de.gamelos.PermissionsAPI.PermissionsAPI;
import de.gamelos.jaylosapi.JaylosAPI;

@SuppressWarnings("deprecation")
public class Chat implements Listener {
	
	
	
	
	@EventHandler
	public void onchat(PlayerChatEvent e){
		Player p = e.getPlayer();
		e.setCancelled(true);
//		
		String Massage = e.getMessage();
//		
		if(Events.onendgame){			
			for(Player pp:Bukkit.getOnlinePlayers()){
				pp.sendMessage(JaylosAPI.getchatname(p, pp)+ChatColor.DARK_GRAY+" >> "+ChatColor.GRAY+Massage);
			}
		}else{
		if(Main.spectatorplayer.contains(p)){
			for(Player pp:Main.spectatorplayer){
			String speczeichen = ChatColor.GRAY+"["+ChatColor.RED+"✘"+ChatColor.GRAY+"]";
			pp.sendMessage(speczeichen+ChatColor.GRAY+p.getName()+ChatColor.DARK_GRAY+" >> "+ChatColor.GRAY+Massage);
			}
			}else{
				for(Player pp:Bukkit.getOnlinePlayers()){
					pp.sendMessage(JaylosAPI.getchatname(p, pp)+ChatColor.DARK_GRAY+" >> "+ChatColor.GRAY+Massage);
				}
			}
		}
	}
	
	
}
