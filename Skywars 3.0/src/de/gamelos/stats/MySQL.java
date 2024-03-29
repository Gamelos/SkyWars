package de.gamelos.stats;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.bukkit.Bukkit;

public class MySQL {
	
	private static String HOST = "";
	private static String DATABASE = "";
	private static String USER = "";
	private static String PASSWORD = "";
	
	private static Connection con;
	
	public MySQL(String host, String database, String user, String password){
		MySQL.HOST = host;
		MySQL.DATABASE = user;
		MySQL.USER = user;
		MySQL.PASSWORD = password;
		
		connect();
		recon();
	}

	public static void connect() {
		try{
			con = DriverManager.getConnection("jdbc:mysql://" + HOST + ":3306/" + DATABASE + "?autoReconnect=true", USER, PASSWORD);
			System.out.println("[Mysql] Die Verbindung wurde hergestellt!");
		}catch(SQLException e){
			System.out.println("[Mysql] Die Verbindung ist fehlgeschlagen! Feheler:" +e.getMessage());
			e.printStackTrace();
		}
		
	}
	public void close(){
		try {
			if(con != null){
				con.close();
				System.out.println("[Mysql] Die verbindung wurde erfolgreich beendet");
			}
		} catch (SQLException e) {
			System.out.println("[Mysql] Feheler beim beenden der Verbindung! Feheler:" +e.getMessage());
		}
	}
	
	public void update(String qry){
		try {
			Statement st = con.createStatement();
			st.executeUpdate(qry);
			st.close();
		} catch (SQLException e) {
			connect();
			System.err.println(e);
		}
	}
	
	public static ResultSet querry(String qry){
		ResultSet rs = null;
		try {
			Statement st = con.createStatement();
			rs = st.executeQuery(qry);
		} catch (SQLException e) {
			connect();
			System.err.println(e);
		}
		return rs;
	}
	
	public void recon(){
		Bukkit.getScheduler().scheduleSyncRepeatingTask(Bukkit.getPluginManager().getPlugin("Skywars"), new Runnable(){
			@Override
			public void run() {
				if(Bukkit.getOnlinePlayers().size() == 0){
					close();
					connect();
				}
			}
		}, 20*60*60*6, 20*60*60*6);
	}
	
}

