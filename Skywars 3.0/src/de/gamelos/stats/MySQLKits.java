package de.gamelos.stats;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class MySQLKits {

	public static boolean playerExists(String uuid){
		
		
		try {
			@SuppressWarnings("static-access")
			ResultSet rs = de.gamelos.skywars.Main.mysql.querry("SELECT * FROM Kits WHERE UUID = '"+ uuid + "'");
			
			if(rs.next()){
				return rs.getString("UUID") != null;
			}
			return false;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public static void createPlayer(String uuid, String kits){
		if(!(playerExists(uuid))){
			de.gamelos.skywars.Main.mysql.update("INSERT INTO Kits(UUID, Kits) VALUES ('" +uuid+ "', '"+kits+"');");
		}
	}
	
	//get-----------------------------------------------------------------------------------------------------------------------------------
	public static String getKits(String uuid){
		String i = null;
		if(playerExists(uuid)){
			try {
				@SuppressWarnings("static-access")
				ResultSet rs = de.gamelos.skywars.Main.mysql.querry("SELECT * FROM Kits WHERE UUID = '"+ uuid + "'");
				
				if((!rs.next()) || (String.valueOf(rs.getString("Kits")) == null));
				
				i = rs.getString("Kits");
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return i;
	}
	//set-----------------------------------------------------------------------------------------------------------------------------------
	
	public static void setKits(String uuid, String kits){
		
		if(playerExists(uuid)){
			de.gamelos.skywars.Main.mysql.update("UPDATE Kits SET Kits= '" + kits+ "' WHERE UUID= '" + uuid+ "';");
		}else{
			createPlayer(uuid, kits);
			setKits(uuid, kits);
		}
	}
	//add-----------------------------------------------------------------------------------------------------------------------------------
	public static void addKit(String uuid,String kit){
		if(playerExists(uuid)){
			String s = getKits(uuid);
			String b = s+","+kit;
			setKits(uuid, b);
		}else{
			createPlayer(uuid, kit);
		}
	}
	//get-----------------------------------------------------------------------------------------------------------------------------------
	public static ArrayList<String> getplayerkits(String uuid){
		ArrayList<String> list = new ArrayList<>();
		String s = getKits(uuid);
		String kits[] = s.split(",");
		for(int i = 0;kits.length > i;i++){
			list.add(kits[i]);
		}
		return list;
	}
}
