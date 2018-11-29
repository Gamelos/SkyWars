package de.gamelos.stats;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Kits {

	public static boolean playerExists(String uuid){
		
		
		try {
			@SuppressWarnings("static-access")
			ResultSet rs = de.gamelos.skywars.Main.mysql.querry("SELECT * FROM Kit WHERE UUID = '"+ uuid + "'");
			
			if(rs.next()){
				return rs.getString("UUID") != null;
			}
			return false;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public static void createPlayer(String uuid, String name){
		if(!(playerExists(uuid))){
			de.gamelos.skywars.Main.mysql.update("INSERT INTO Kit(UUID, Kitname) VALUES ('" +uuid+ "', 'null');");
		}
	}
	
	//get-----------------------------------------------------------------------------------------------------------------------------------
	public static String getKit(String uuid, String name){
		String i = null;
		if(playerExists(uuid)){
			try {
				@SuppressWarnings("static-access")
				ResultSet rs = de.gamelos.skywars.Main.mysql.querry("SELECT * FROM Kit WHERE UUID = '"+ uuid + "'");
				
				if((!rs.next()) || (String.valueOf(rs.getString("Kitname")) == null));
				
				i = rs.getString("Kitname");
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}else{
			createPlayer(uuid, name);
			getKit(uuid, name);
		}
		return i;
	}
	//set-----------------------------------------------------------------------------------------------------------------------------------
	
	public static void setKit(String uuid, String kitname){
		
		if(playerExists(uuid)){
			de.gamelos.skywars.Main.mysql.update("UPDATE Kit SET Kitname= '" + kitname+ "' WHERE UUID= '" + uuid+ "';");
		}else{
			createPlayer(uuid, kitname);
			setKit(uuid, kitname);
		}
		
	}
}
