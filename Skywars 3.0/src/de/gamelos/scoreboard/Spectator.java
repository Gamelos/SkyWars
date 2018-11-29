package de.gamelos.scoreboard;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Score;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;

import de.gamelos.jaylosapi.JaylosAPI;
import de.gamelos.skywars.Main;

public class Spectator {
	public static void update(Player p){
		for(Player ppp :Main.spectatorplayer){
		Scoreboard board = Bukkit.getScoreboardManager().getNewScoreboard();
		Objective obj = board.registerNewObjective("asfd", "bbsb");
		obj.setDisplayName(ChatColor.BOLD+"Jaylos.net");
		obj.setDisplaySlot(DisplaySlot.SIDEBAR);
		int playerzahl = 1;
		playerzahl = Main.playeringame.size();
		
		Team spieler = board.registerNewTeam("001spieler");
		spieler.setPrefix(""+ChatColor.GREEN); 
		
		Team spectator = board.registerNewTeam("002spectator");
		spectator.setPrefix(""+ChatColor.GRAY);
		spectator.setCanSeeFriendlyInvisibles(true);
		
		for(Player pp : Bukkit.getOnlinePlayers()){
			if(Main.spectatorplayer.contains(pp)){
				JaylosAPI.setteam(spectator, pp, ppp);
			}else{
				JaylosAPI.setteam(spieler, pp, ppp);
			}
		}
		
		
//		
//				
				Score spieler1 = obj.getScore("§7Spieler:");
				Score spielerzahl = obj.getScore(""+ChatColor.RED+playerzahl);
				
				
				spieler1.setScore(2);
				spielerzahl.setScore(1);
				
				ppp.setScoreboard(board);
				
		}
		
		}
}
