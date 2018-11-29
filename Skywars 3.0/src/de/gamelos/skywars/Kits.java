package de.gamelos.skywars;

import java.util.ArrayList;

import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.DyeColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.LeatherArmorMeta;

import de.gamelos.stats.MySQLKits;

public class Kits {
	public static void openkitsmenü(Player p) {
		Inventory kits = p.getPlayer().getServer().createInventory(null, 27, ChatColor.YELLOW + "Kits");
		// ______________________________________
		ArrayList<String> playerlist = new ArrayList<>();
		
		if(MySQLKits.playerExists(p.getUniqueId().toString())){
		for(String ss:MySQLKits.getplayerkits(p.getUniqueId().toString())){
			playerlist.add(ss);
		}
		}
		
		ArrayList<String> starter = new ArrayList<String>();
		starter.add(ChatColor.DARK_GREEN + "1x Eisenschwert");
		starter.add(ChatColor.DARK_GREEN + "1x Eisenspitzhacke");
		starter.add(ChatColor.DARK_GREEN + "1x Eisenaxt");
		// ______________________________________
		ArrayList<String> Assasine = new ArrayList<String>();
		Assasine.add(ChatColor.DARK_GREEN + "1x Diamantschwert mit Sharpness");
		Assasine.add(ChatColor.DARK_GREEN + "1x Diamantstiefel mit Federfall");
		// ______________________________________
		ArrayList<String> Enderman = new ArrayList<String>();
		Enderman.add(ChatColor.DARK_GREEN + "1x Enderperle");
		Enderman.add(ChatColor.DARK_GREEN + "1x Lederbrust mit Schutz 4");
		Enderman.add(ChatColor.DARK_GREEN + "32x Endsteine");
		// ______________________________________
		ArrayList<String> Nomade = new ArrayList<String>();
		Nomade.add(ChatColor.DARK_GREEN + "3x Stacks Blöcke");
		Nomade.add(ChatColor.DARK_GREEN + "1x Lederhut");
		// ______________________________________
		ArrayList<String> Glumanda = new ArrayList<String>();
		Glumanda.add(ChatColor.DARK_GREEN + "1x Bogen mit Flame 1");
		Glumanda.add(ChatColor.DARK_GREEN + "3x Pfeile");
		Glumanda.add(ChatColor.DARK_GREEN + "1x Lederbrust mit Feuerschutz 3");
		Glumanda.add(ChatColor.DARK_GREEN + "32x Feuerkugeln");
		// ______________________________________
		ArrayList<String> Eismann = new ArrayList<String>();
		Eismann.add(ChatColor.DARK_GREEN + "1x Eisenhelm");
		Eismann.add(ChatColor.DARK_GREEN + "16x Schneebälle");
		Eismann.add(ChatColor.DARK_GREEN + "1x Farbstoff mit Knockback");
		// ______________________________________
		ArrayList<String> König = new ArrayList<String>();
		König.add(ChatColor.DARK_GREEN + "1x Goldschwert mit Schärfe 1");
		König.add(ChatColor.DARK_GREEN + "1x Goldapfel");
		König.add(ChatColor.DARK_GREEN + "64x Goldblöcke");
		König.add(ChatColor.DARK_GREEN + "1x Goldbrust");
		// ______________________________________
		ArrayList<String> Bomber = new ArrayList<String>();
		Bomber.add(ChatColor.DARK_GREEN + "16x TNT");
		Bomber.add(ChatColor.DARK_GREEN + "3x Hebel");
		Bomber.add(ChatColor.DARK_GREEN + "3x Druckplatten");
		Bomber.add(ChatColor.DARK_GREEN + "1x Eisenbrust mit Explosionsschutz 4");
		// ______________________________________
		ArrayList<String> Pyro = new ArrayList<String>();
		Pyro.add(ChatColor.DARK_GREEN + "1x Goldschwert mit Verbrennung");
		Pyro.add(ChatColor.DARK_GREEN + "1x Lavaeimer");
		Pyro.add(ChatColor.DARK_GREEN + "1x Trank der Feuerresistenz");
		Pyro.add(ChatColor.DARK_GREEN + "1x Lederbrust mit Feuerschutz");
		Pyro.add(ChatColor.DARK_GREEN + "1x Feuerzeug");
		// ______________________________________
		ArrayList<String> Seemann = new ArrayList<String>();
		Seemann.add(ChatColor.DARK_GREEN + "1x Lederrüstung");
		Seemann.add(ChatColor.DARK_GREEN + "1x Holzschwert");
		Seemann.add(ChatColor.DARK_GREEN + "1x Boot");
		Seemann.add(ChatColor.DARK_GREEN + "3x Wassereimer");
		// ______________________________________
		ArrayList<String> Tank = new ArrayList<String>();
		Tank.add(ChatColor.DARK_GREEN + "1x Trank der Feuerresistenz");
		Tank.add(ChatColor.DARK_GREEN + "1x Diamanthelm");
		Tank.add(ChatColor.DARK_GREEN + "1x Diamantschuhe");
		Tank.add(ChatColor.DARK_GREEN + "1x Eisenbrust");
		Tank.add(ChatColor.DARK_GREEN + "1x Eisenhose");
		// ______________________________________
		ArrayList<String> Sonic = new ArrayList<String>();
		Sonic.add(ChatColor.DARK_GREEN + "32x Spinnenweben");
		Sonic.add(ChatColor.DARK_GREEN + "2x Trank der Schnelligkeit");
		Sonic.add(ChatColor.DARK_GREEN + "1x Diamanthelm");
		// ______________________________________
		ArrayList<String> Rusher = new ArrayList<String>();
		Rusher.add(ChatColor.DARK_GREEN + "1x Eisenschwert");
		Rusher.add(ChatColor.DARK_GREEN + "3x Spinnenweben");
		Rusher.add(ChatColor.DARK_GREEN + "64x Blöcke");
		Rusher.add(ChatColor.DARK_GREEN + "1x Goldrüstung");
		Rusher.add(ChatColor.DARK_GREEN + "1x Eisenbrust");
		// ______________________________________
		ArrayList<String> Crafter = new ArrayList<String>();
		Crafter.add(ChatColor.DARK_GREEN + "64x Werkbank");
		Crafter.add(ChatColor.DARK_GREEN + "32x Amboss");
		Crafter.add(ChatColor.DARK_GREEN + "32x Blöcke");
		Crafter.add(ChatColor.DARK_GREEN + "32x Stock");
		Crafter.add(ChatColor.DARK_GREEN + "5x Eisenbarren");
		Crafter.add(ChatColor.DARK_GREEN + "5x Diamanten");
		// ______________________________________
		ArrayList<String> Zauberer = new ArrayList<String>();
		Zauberer.add(ChatColor.DARK_GREEN + "32x Erfahrungsflaschen");
		Zauberer.add(ChatColor.DARK_GREEN + "1x Zaubertisch");
		// ______________________________________
		ArrayList<String> Spleefer = new ArrayList<String>();
		Spleefer.add(ChatColor.DARK_GREEN + "1x Diamantspitzhacke mit Effizienz 10");
		Spleefer.add(ChatColor.DARK_GREEN + "16x Spinnenweben");
		Spleefer.add(ChatColor.DARK_GREEN + "64x Blöcke");
		// _______
		// ....................................
		if(p.hasPermission("skywars.allkits")){
			kits.setItem(0, createitem(Material.WOOD_AXE, ChatColor.YELLOW + "Starter-Kit §7(§aGekauft§7)", starter));
			kits.setItem(1, createitem(Material.DIAMOND_SWORD, ChatColor.YELLOW+"Assassine §7(§aGekauft§7)", Assasine));
			kits.setItem(2, createitem(Material.ENDER_PEARL, ChatColor.YELLOW + "Enderman §7(§aGekauft§7)", Enderman));
			kits.setItem(3, createitem(Material.LEATHER_HELMET, ChatColor.YELLOW + "Nomade §7(§aGekauft§7)", Nomade));
			kits.setItem(5, createitem(Material.SNOW_BALL, ChatColor.YELLOW + "Eismann §7(§aGekauft§7)", Eismann));
			kits.setItem(4, createitem(Material.BOW, ChatColor.YELLOW + "Glumanda §7(§aGekauft§7)", Glumanda));
			kits.setItem(6, createitem(Material.GOLD_HELMET, ChatColor.YELLOW + "König §7(§aGekauft§7)", König));
			kits.setItem(7, createitem(Material.TNT, ChatColor.YELLOW + "Bomber §7(§aGekauft§7)", Bomber));
			kits.setItem(8, createitem(Material.FLINT_AND_STEEL, ChatColor.YELLOW + "Pyro §7(§aGekauft§7)", Pyro));
			kits.setItem(9, createitem(Material.BOAT, ChatColor.YELLOW + "Seemann §7(§aGekauft§7)", Seemann));
			kits.setItem(10, createitem(Material.IRON_CHESTPLATE, ChatColor.YELLOW + "Tank §7(§aGekauft§7)", Tank));
			kits.setItem(11, createitem(Material.DIAMOND_HELMET, ChatColor.YELLOW + "Sonic §7(§aGekauft§7)", Sonic));
			kits.setItem(12, createitem(Material.IRON_SWORD, ChatColor.YELLOW + "Rusher §7(§aGekauft§7)", Rusher));
			kits.setItem(13, createitem(Material.WORKBENCH, ChatColor.YELLOW + "Crafter §7(§aGekauft§7)", Crafter));
			kits.setItem(14, createitem(Material.ENCHANTMENT_TABLE, ChatColor.YELLOW + "Zauberer §7(§aGekauft§7)", Zauberer));
			kits.setItem(15, createitem(Material.DIAMOND_PICKAXE, ChatColor.YELLOW + "Spleefer §7(§aGekauft§7)", Spleefer));
		}else{
//		
		kits.setItem(0, createitem(Material.WOOD_AXE, ChatColor.YELLOW + "Starter-Kit §7(§aGekauft§7)", starter));
//		
		if(playerlist.contains("Asassine")){
		kits.setItem(1, createitem(Material.DIAMOND_SWORD, ChatColor.YELLOW+"Assassine §7(§aGekauft§7)", Assasine));
		}else{
			kits.setItem(1, createitem(Material.DIAMOND_SWORD, ChatColor.YELLOW+"Assassine §7(§cKosten: 30.000§7)", Assasine));
		}
//		
		if(playerlist.contains("Enderman")){
		kits.setItem(2, createitem(Material.ENDER_PEARL, ChatColor.YELLOW + "Enderman §7(§aGekauft§7)", Enderman));
		}else{
			kits.setItem(2, createitem(Material.ENDER_PEARL, ChatColor.YELLOW + "Enderman §7(§cKosten: 30.000§7)", Enderman));
		}
//		
		if(playerlist.contains("Nomade")){
		kits.setItem(3, createitem(Material.LEATHER_HELMET, ChatColor.YELLOW + "Nomade §7(§aGekauft§7)", Nomade));
		}else{
			kits.setItem(3, createitem(Material.LEATHER_HELMET, ChatColor.YELLOW + "Nomade §7(§cKosten: 2.500§7)", Nomade));
		}
//		
		if(playerlist.contains("Glumanda")){
		kits.setItem(4, createitem(Material.BOW, ChatColor.YELLOW + "Glumanda §7(§aGekauft§7)", Glumanda));
		}else{
			kits.setItem(4, createitem(Material.BOW, ChatColor.YELLOW + "Glumanda §7(§cKosten: 20.000§7)", Glumanda));
		}
//		
		if(playerlist.contains("Eismann")){
		kits.setItem(5, createitem(Material.SNOW_BALL, ChatColor.YELLOW + "Eismann §7(§aGekauft§7)", Eismann));
		}else{
			kits.setItem(5, createitem(Material.SNOW_BALL, ChatColor.YELLOW + "Eismann §7(§cKosten: 20.000§7)", Eismann));	
		}
//		
		if(playerlist.contains("König")){
		kits.setItem(6, createitem(Material.GOLD_HELMET, ChatColor.YELLOW + "König §7(§aGekauft§7)", König));
		}else{
			kits.setItem(6, createitem(Material.GOLD_HELMET, ChatColor.YELLOW + "König §7(§cKosten: 15.000§7)", König));
		}
//		
		if(playerlist.contains("Bomber")){
		kits.setItem(7, createitem(Material.TNT, ChatColor.YELLOW + "Bomber §7(§aGekauft§7)", Bomber));
		}else{
			kits.setItem(7, createitem(Material.TNT, ChatColor.YELLOW + "Bomber §7(§cKosten: 10.000§7)", Bomber));	
		}
//		
		if(playerlist.contains("Pyro")){
			kits.setItem(8, createitem(Material.FLINT_AND_STEEL, ChatColor.YELLOW + "Pyro §7(§aGekauft§7)", Pyro));
			}else{
				kits.setItem(8, createitem(Material.FLINT_AND_STEEL, ChatColor.YELLOW + "Pyro §7(§cKosten: 25.000§7)", Pyro));	
			}
//		
		if(playerlist.contains("Seemann")){
			kits.setItem(9, createitem(Material.BOAT, ChatColor.YELLOW + "Seemann §7(§aGekauft§7)", Seemann));
			}else{
				kits.setItem(9, createitem(Material.BOAT, ChatColor.YELLOW + "Seemann §7(§cKosten: 15.000§7)", Seemann));	
			}
//		
		if(playerlist.contains("Tank")){
			kits.setItem(10, createitem(Material.IRON_CHESTPLATE, ChatColor.YELLOW + "Tank §7(§aGekauft§7)", Tank));
			}else{
				kits.setItem(10, createitem(Material.IRON_CHESTPLATE, ChatColor.YELLOW + "Tank §7(§cKosten: 30.000§7)", Tank));	
			}
//		
		if(playerlist.contains("Sonic")){
			kits.setItem(11, createitem(Material.DIAMOND_HELMET, ChatColor.YELLOW + "Sonic §7(§aGekauft§7)", Sonic));
			}else{
				kits.setItem(11, createitem(Material.DIAMOND_HELMET, ChatColor.YELLOW + "Sonic §7(§cKosten: 15.000§7)", Sonic));	
			}
//		
		if(playerlist.contains("Rusher")){
			kits.setItem(12, createitem(Material.IRON_SWORD, ChatColor.YELLOW + "Rusher §7(§aGekauft§7)", Sonic));
			}else{
				kits.setItem(12, createitem(Material.IRON_SWORD, ChatColor.YELLOW + "Rusher §7(§cKosten: 5.000§7)", Sonic));	
			}
//		
		if(playerlist.contains("Crafter")){
			kits.setItem(13, createitem(Material.WORKBENCH, ChatColor.YELLOW + "Crafter §7(§aGekauft§7)", Crafter));
			}else{
				kits.setItem(13, createitem(Material.WORKBENCH, ChatColor.YELLOW + "Crafter §7(§cKosten: 10.000§7)", Crafter));	
			}
		if(playerlist.contains("Zauberer")){
			kits.setItem(14, createitem(Material.ENCHANTMENT_TABLE, ChatColor.YELLOW + "Zauberer §7(§aGekauft§7)", Zauberer));
			}else{
				kits.setItem(14, createitem(Material.ENCHANTMENT_TABLE, ChatColor.YELLOW + "Zauberer §7(§cKosten: 15.000§7)", Zauberer));	
			}
		
		if(playerlist.contains("Spleefer")){
			kits.setItem(15, createitem(Material.DIAMOND_PICKAXE, ChatColor.YELLOW + "Spleefer §7(§aGekauft§7)", Spleefer));
			}else{
				kits.setItem(15, createitem(Material.DIAMOND_PICKAXE, ChatColor.YELLOW + "Spleefer §7(§cKosten: 10.000§7)", Spleefer));	
			}
		}
//		
		// ....................................
		p.openInventory(kits);
	}
	// =============================================================================
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static ItemStack createitem(Material m, String displayname, ArrayList lore) {
		ItemStack item = new ItemStack(m);
		ItemMeta itemmeta = item.getItemMeta();
		itemmeta.setDisplayName(displayname);
		if (lore != null) {
			itemmeta.setLore(lore);
		}
		item.setItemMeta(itemmeta);
		return item;
	}
	
	public static ItemStack createenchitem(Material m, Enchantment Entchantment1,int ench1){
		ItemStack item = new ItemStack(m);
		if(Entchantment1 != null){
		ItemMeta meta = item.getItemMeta();
		meta.addEnchant(Entchantment1, ench1, true);
		item.setItemMeta(meta);
		}
		return item;
	}
	
	public static ItemStack creatdyeitem(Material m, Enchantment Entchantment1,int ench1, short s){
		ItemStack item = new ItemStack(m,1,s);
		if(Entchantment1 != null){
		ItemMeta meta = item.getItemMeta();
		meta.addEnchant(Entchantment1, ench1, true);
		item.setItemMeta(meta);
		}
		return item;
	}
	
	public static ItemStack createdoubleenchitem(Material m, Enchantment Entchantment1,int ench1, Enchantment Entchantment2,int ench2){
		ItemStack item = new ItemStack(m);
		if(Entchantment1 != null){
		ItemMeta meta = item.getItemMeta();
		meta.addEnchant(Entchantment1, ench1, true);
		item.setItemMeta(meta);
		}
		if(Entchantment2 != null){
			ItemMeta meta = item.getItemMeta();
			meta.addEnchant(Entchantment2, ench2, true);
			item.setItemMeta(meta);
			}
		return item;
	}
	
	public static ItemStack createcoloreddoubleenchitem(Material m, Enchantment Entchantment1,int ench1, Enchantment Entchantment2,int ench2,Color c){
		ItemStack item = new ItemStack(m);
		LeatherArmorMeta meta = (LeatherArmorMeta)item.getItemMeta();
		meta.setColor(c);
		if(Entchantment1 != null){
		meta.addEnchant(Entchantment1, ench1, true);
		item.setItemMeta(meta);
		}
		if(Entchantment2 != null){
			meta.addEnchant(Entchantment2, ench2, true);
			item.setItemMeta(meta);
			}
		return item;
	}
	
	@SuppressWarnings("deprecation")
	public static void giveGame1Kits(Player p){
		if(!Main.kit.containsKey(p)){
			//starterkit
			ItemStack schwert = new ItemStack(Material.IRON_SWORD);
			ItemStack axt = new ItemStack(Material.IRON_AXE);
			ItemStack spitzhacke = new ItemStack(Material.IRON_PICKAXE);
			p.getInventory().setItem(0, schwert);
			p.getInventory().setItem(1, axt);
			p.getInventory().setItem(2, spitzhacke);
		}else{
			if(Main.kit.get(p).equals("Assassine")){
				//assasine
				ItemStack diaschwert = createenchitem(Material.DIAMOND_SWORD, Enchantment.DAMAGE_ALL, 1);
				ItemStack schuhe = createenchitem(Material.DIAMOND_BOOTS, Enchantment.PROTECTION_FALL, 3);
				p.getInventory().setItem(0, diaschwert);
				p.getInventory().setBoots(schuhe);
			}else if(Main.kit.get(p).equals("Enderman")){
				//Enderman
				ItemStack enderperle = new ItemStack(Material.ENDER_PEARL);
				ItemStack chestplate = createdoubleenchitem(Material.LEATHER_CHESTPLATE, Enchantment.PROTECTION_ENVIRONMENTAL, 4, Enchantment.DURABILITY, 3);
				ItemStack blocks = new ItemStack(Material.ENDER_STONE, 32);
				p.getInventory().setItem(0, enderperle);
				p.getInventory().setItem(1, blocks);
				p.getInventory().setChestplate(chestplate);
			}else if(Main.kit.get(p).equals("Nomade")){
				//Nomanden
				ItemStack enderperle = new ItemStack(Material.BRICK, 64);
				ItemStack enderperle1 = new ItemStack(Material.BRICK, 64);
				ItemStack enderperle2 = new ItemStack(Material.BRICK, 64);
				ItemStack chestplate = createenchitem(Material.LEATHER_HELMET, Enchantment.PROTECTION_ENVIRONMENTAL, 4);
				p.getInventory().setItem(0, enderperle);
				p.getInventory().setItem(1, enderperle1);
				p.getInventory().setItem(2, enderperle2);
				p.getInventory().setHelmet(chestplate);
			}else if(Main.kit.get(p).equals("Glumanda")){
				//Glumanda
				ItemStack enderperle = createenchitem(Material.BOW, Enchantment.ARROW_FIRE, 1);
				ItemStack chestplate = createcoloreddoubleenchitem(Material.LEATHER_CHESTPLATE, Enchantment.PROTECTION_FIRE, 3, Enchantment.DURABILITY, 3, Color.RED);
				ItemStack arrow = new ItemStack(Material.ARROW, 3);
				ItemStack feuerkugel = new ItemStack(Material.FIREBALL, 32);
				p.getInventory().setItem(0, enderperle);
				p.getInventory().setItem(1, arrow);
				p.getInventory().setItem(2, feuerkugel);
				p.getInventory().setChestplate(chestplate);
			}else if(Main.kit.get(p).equals("Eismann")){
				//Eismann
				ItemStack enderperle = new ItemStack(Material.SNOW_BALL, 16);
				ItemStack chestplate = createenchitem(Material.IRON_HELMET, Enchantment.PROTECTION_ENVIRONMENTAL, 4);
				p.getInventory().setItem(1, enderperle);
				p.getInventory().setHelmet(chestplate);
				p.getInventory().setItem(0, creatdyeitem(Material.INK_SACK, Enchantment.KNOCKBACK, 1, DyeColor.LIGHT_BLUE.getData()));
			}else if(Main.kit.get(p).equals("König")){
				//König
				ItemStack enderperle = new ItemStack(Material.GOLDEN_APPLE);
				ItemStack chestplate = createenchitem(Material.GOLD_SWORD, Enchantment.DAMAGE_ALL, 1);
				p.getInventory().setItem(1, enderperle);
				p.getInventory().setItem(0, chestplate);
				p.getInventory().setItem(2, new ItemStack(Material.GOLD_BLOCK, 64));
				p.getInventory().setChestplate(new ItemStack(Material.GOLD_CHESTPLATE));
				p.getInventory().setBoots(creatdyeitem(Material.LEATHER_BOOTS, null, 0, DyeColor.RED.getData()));
				p.getInventory().setHelmet(creatdyeitem(Material.LEATHER_HELMET, null, 0, DyeColor.RED.getData()));
				p.getInventory().setLeggings(creatdyeitem(Material.LEATHER_LEGGINGS, null, 0, DyeColor.RED.getData()));
			}else if(Main.kit.get(p).equals("Bomber")){
				//König
				ItemStack tnt = new ItemStack(Material.TNT, 16);
				ItemStack hebel = new ItemStack(Material.LEVER,3);
				ItemStack feuernzeug = new ItemStack(Material.STONE_PLATE,3);
				p.getInventory().setItem(0, tnt);
				p.getInventory().setItem(1, hebel);
				p.getInventory().setItem(2, feuernzeug);
				p.getInventory().setChestplate(createenchitem(Material.IRON_CHESTPLATE, Enchantment.PROTECTION_EXPLOSIONS, 4));
			}else if(Main.kit.get(p).equals("Pyro")){
				//König
				ItemStack schwert = createdoubleenchitem(Material.GOLD_SWORD, Enchantment.DAMAGE_ALL, 2, Enchantment.FIRE_ASPECT, 1);
				ItemStack eimer = new ItemStack(Material.LAVA_BUCKET);
				ItemStack trank = new ItemStack(Material.POTION, 1, (short) 16419);
				ItemStack feuernzeug = new ItemStack(Material.FLINT_AND_STEEL);
				p.getInventory().setItem(0, schwert);
				p.getInventory().setItem(1, eimer);
				p.getInventory().setItem(2, trank);
				p.getInventory().setItem(3, feuernzeug);
				p.getInventory().setChestplate(createcoloreddoubleenchitem(Material.LEATHER_CHESTPLATE, Enchantment.PROTECTION_FIRE, 4, Enchantment.DURABILITY, 3, Color.RED));
			}else if(Main.kit.get(p).equals("Tank")){
				//König
				ItemStack trank = new ItemStack(Material.POTION, 1, (short) 16419);
				p.getInventory().setItem(0, trank);
				p.getInventory().setHelmet(createenchitem(Material.DIAMOND_HELMET, Enchantment.PROTECTION_ENVIRONMENTAL, 1));
				p.getInventory().setChestplate(createenchitem(Material.IRON_CHESTPLATE, Enchantment.PROTECTION_ENVIRONMENTAL, 1));
				p.getInventory().setLeggings(createenchitem(Material.IRON_LEGGINGS, Enchantment.PROTECTION_ENVIRONMENTAL, 1));
				p.getInventory().setBoots(createenchitem(Material.DIAMOND_BOOTS, Enchantment.PROTECTION_ENVIRONMENTAL, 1));
			}else if(Main.kit.get(p).equals("Sonic")){
				//König
				ItemStack trank = new ItemStack(Material.POTION, 1, (short) 16386);
				ItemStack webs = new ItemStack(Material.WEB,32);
				p.getInventory().setItem(0, webs);
				p.getInventory().setItem(1, trank);
				p.getInventory().setItem(2, trank);
				p.getInventory().setHelmet(createenchitem(Material.DIAMOND_HELMET, Enchantment.PROTECTION_ENVIRONMENTAL, 2));
			}else if(Main.kit.get(p).equals("Rusher")){
				//König
				ItemStack schwert = new ItemStack(Material.IRON_SWORD);
				ItemStack webs = new ItemStack(Material.WEB,3);
				ItemStack blocks = new ItemStack(Material.WOOD,64);
				p.getInventory().setItem(0, schwert);
				p.getInventory().setItem(1, webs);
				p.getInventory().setItem(2, blocks);
				p.getInventory().setHelmet(new ItemStack(Material.GOLD_HELMET));
				p.getInventory().setChestplate(new ItemStack(Material.IRON_CHESTPLATE));
				p.getInventory().setLeggings(new ItemStack(Material.GOLD_LEGGINGS));
				p.getInventory().setBoots(new ItemStack(Material.GOLD_BOOTS));
				
			}else if(Main.kit.get(p).equals("Zauberer")){
				//König
				ItemStack schwert = new ItemStack(Material.EXP_BOTTLE,32);
				ItemStack webs = new ItemStack(Material.ENCHANTMENT_TABLE);
				p.getInventory().setItem(0, schwert);
				p.getInventory().setItem(1, webs);
			}else if(Main.kit.get(p).equals("Crafter")){
				//König
				ItemStack schwert = new ItemStack(Material.WORKBENCH,64);
				ItemStack webs = new ItemStack(Material.ANVIL,32);
				ItemStack webs1 = new ItemStack(Material.WOOD,32);
				ItemStack webs2 = new ItemStack(Material.STICK,32);
				ItemStack webs3 = new ItemStack(Material.IRON_INGOT,5);
				ItemStack webs4 = new ItemStack(Material.DIAMOND,5);
				p.getInventory().setItem(0, schwert);
				p.getInventory().setItem(1, webs);
				p.getInventory().setItem(2, webs1);
				p.getInventory().setItem(3, webs2);
				p.getInventory().setItem(4, webs3);
				p.getInventory().setItem(5, webs4);
				
			}else if(Main.kit.get(p).equals("Spleefer")){
				//König
				ItemStack spizhacke = createenchitem(Material.DIAMOND_PICKAXE, Enchantment.DIG_SPEED, 10);
				ItemStack webs = new ItemStack(Material.WEB,16);
				ItemStack blöcke = new ItemStack(Material.STONE,64);
				p.getInventory().setItem(0, spizhacke);
				p.getInventory().setItem(1, webs);
				p.getInventory().setItem(2, blöcke);
				
			}else if(Main.kit.get(p).equals("Seemann")){
				//König
				ItemStack schwert = createenchitem(Material.WOOD_SWORD, Enchantment.DAMAGE_ALL, 3);
				ItemStack boot = new ItemStack(Material.BOAT);
				ItemStack eimer = new ItemStack(Material.WATER_BUCKET);
				
				
				p.getInventory().setItem(0, schwert);
				p.getInventory().setItem(1, boot);
				p.getInventory().setItem(2, eimer);
				p.getInventory().setItem(3, eimer);
				p.getInventory().setItem(4, eimer);
				p.getInventory().setChestplate(createcoloreddoubleenchitem(Material.LEATHER_CHESTPLATE, Enchantment.PROTECTION_ENVIRONMENTAL, 2, null, 3, Color.BLUE));
				p.getInventory().setBoots(createcoloreddoubleenchitem(Material.LEATHER_BOOTS, Enchantment.PROTECTION_ENVIRONMENTAL, 2, null, 3, Color.BLUE));
				p.getInventory().setLeggings(createcoloreddoubleenchitem(Material.LEATHER_LEGGINGS, Enchantment.PROTECTION_ENVIRONMENTAL, 2, null, 3, Color.BLUE));
				p.getInventory().setHelmet(createcoloreddoubleenchitem(Material.LEATHER_HELMET, Enchantment.PROTECTION_ENVIRONMENTAL, 2, null, 3, Color.BLUE));
			}else{
				ItemStack schwert = new ItemStack(Material.IRON_SWORD);
				ItemStack axt = new ItemStack(Material.IRON_AXE);
				ItemStack spitzhacke = new ItemStack(Material.IRON_PICKAXE);
				p.getInventory().setItem(0, schwert);
				p.getInventory().setItem(1, axt);
				p.getInventory().setItem(2, spitzhacke);
			}
		}
	}
}
