package de.gamelos.scoreboard;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Score;
import org.bukkit.scoreboard.Scoreboard;
import de.gamelos.skywars.Main;

public class LobbyBoard {

	
	public static void createScoreboard(){
		for(Player p:Bukkit.getOnlinePlayers()){
		Scoreboard board = Bukkit.getScoreboardManager().getNewScoreboard();
		Objective obj = board.registerNewObjective("aaa", "bbb");
		obj.setDisplayName(ChatColor.BOLD+"Jaylos.net");
		obj.setDisplaySlot(DisplaySlot.SIDEBAR);

//					
					String kit = "";
					if(!Main.kit.containsKey(p)){
						kit = ChatColor.GREEN+"Starter";
					}else{
						kit = Main.kit.get(p);
					}
					String game = "";
						game = Main.mapname;
					
//					
					Score five = obj.getScore("§7Kit:");
					Score four = obj.getScore("§c"+kit);
					Score space = obj.getScore(" ");
					Score tree = obj.getScore("§7Map:");
					Score two = obj.getScore("§c"+game);
					
					tree.setScore(4);
					two.setScore(3);
					space.setScore(2);
					five.setScore(1);
					four.setScore(0);
					
					p.setScoreboard(board);
//					
		}
	}
	
	
	
}
