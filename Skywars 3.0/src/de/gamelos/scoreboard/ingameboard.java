package de.gamelos.scoreboard;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Score;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;

import de.gamelos.jaylosapi.JaylosAPI;
import de.gamelos.skywars.Main;

public class ingameboard implements Listener{
	
	
	
	public static void updateSpielerzahl(Player p){
//		
		Scoreboard board = p.getScoreboard();
		if(Main.playeringame.contains(p)){
		int playerzahl = Main.playeringame.size();
		
		if(board != null){
		if(board.getTeam("player") != null){
		board.getTeam("player").setSuffix(ChatColor.AQUA+""+playerzahl);
		}
		}
		}
	}
	
	
	public static void set(Player p){
		Scoreboard board = Bukkit.getScoreboardManager().getNewScoreboard();
		Objective obj = board.registerNewObjective("asfd", "bbsb");
		obj.setDisplayName(ChatColor.BOLD+"Jaylos.net");
		obj.setDisplaySlot(DisplaySlot.SIDEBAR);
		
//		
		Team spieler = board.registerNewTeam("spieler");
		spieler.setPrefix(""+ChatColor.GREEN); 
		
		Team spectator = board.registerNewTeam("spectator");
		spectator.setPrefix(""+ChatColor.GRAY);
		spectator.setCanSeeFriendlyInvisibles(true);
//		
		
		Team playerz = board.registerNewTeam("player");
		playerz.addEntry(ChatColor.AQUA.toString());
		
		int playerzahl = Main.playeringame.size();
		
		for(Player pp : Bukkit.getOnlinePlayers()){
					JaylosAPI.setteam(spieler, pp, p);
		}
		
		int kamount;
		if(Main.kills.containsKey(p)){
		kamount = Main.kills.get(p);
		}else{
			kamount = 0;
		}
		
		playerz.setSuffix(ChatColor.AQUA+""+playerzahl);
		String mname = Main.mapname;
		
		Score spieler1 = obj.getScore("§7Spieler:");
		Score spielerzahl = obj.getScore(ChatColor.AQUA.toString());
		Score space1 = obj.getScore(ChatColor.BLUE+"  ");
		Score map = obj.getScore("§7Map:");
		Score mapname = obj.getScore(""+ChatColor.YELLOW+mname);
		Score space = obj.getScore(" ");
		Score kills = obj.getScore("§7Kills:");
		Score killamount = obj.getScore(""+ChatColor.RED+kamount);
		
		
		spieler1.setScore(7);
		spielerzahl.setScore(6);
		space1.setScore(5);
		map.setScore(4);
		mapname.setScore(3);
		space.setScore(2);
		kills.setScore(1);
		killamount.setScore(0);
		
		p.setScoreboard(board);
		
		}
		}
