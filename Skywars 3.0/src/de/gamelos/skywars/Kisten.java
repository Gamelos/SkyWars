package de.gamelos.skywars;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class Kisten implements Listener {

	private HashMap<Location, Inventory> chest = new HashMap<Location, Inventory>();

	@EventHandler
	public void onchest(PlayerInteractEvent e) {
		if (e.getClickedBlock() != null) {
			Player p = e.getPlayer();
			Block b = e.getClickedBlock();
			if (b.getType() == Material.CHEST) {

				if (e.getAction() == Action.RIGHT_CLICK_BLOCK) {
					if(!Main.spectatorplayer.contains(p)){
					e.setCancelled(true);
					p.playSound(p.getLocation(), Sound.CHEST_OPEN, 1F, 1F);
					// =======================================================
					if (chest.containsKey(e.getClickedBlock().getLocation())) {
						p.openInventory((Inventory) chest.get(e.getClickedBlock().getLocation()));
					} else {
						if(isnormalchest(b.getLocation())){
						Inventory inv = createnormalchest();

						p.openInventory(inv);
						chest.put(e.getClickedBlock().getLocation(), inv);
					}else{
						Inventory extra = createextrachest();
						p.openInventory(extra);
						chest.put(e.getClickedBlock().getLocation(), extra);
					}
					}
					// =======================================================
				}else{
					e.setCancelled(true);
				}
				}
			}
		}
	}

	@EventHandler
	public void onplace(BlockPlaceEvent e) {
		if (e.getBlock().getType() == Material.CHEST) {
			Inventory inv = Bukkit.createInventory(null, 27, "Truhe");
			chest.put(e.getBlock().getLocation(), inv);
		}
	}


	// ===============================================================================
	// ===============================================================================
	public static Inventory createnormalchest() {
		Inventory inv = Bukkit.createInventory(null, 27, "Truhe");
		ArrayList<ItemStack> standartstuff = new ArrayList<>();
		ArrayList<ItemStack> normal = new ArrayList<>();
		ArrayList<ItemStack> op = new ArrayList<>();
		ArrayList<ItemStack> extremeop = new ArrayList<>();
		// standart____________________________________________________________________________________________________________________
		standartstuff.add(new ItemStack(Material.COMPASS, 1));
		standartstuff.add(new ItemStack(Material.STONE, new Random().nextInt(63)+1));
		standartstuff.add(new ItemStack(Material.STONE, new Random().nextInt(63)+1));
		standartstuff.add(new ItemStack(Material.WOOD, new Random().nextInt(63)+1));
		standartstuff.add(new ItemStack(Material.WOOD, new Random().nextInt(63)+1));
		standartstuff.add(new ItemStack(Material.GLASS, new Random().nextInt(63)+1));
		standartstuff.add(new ItemStack(Material.BRICK, new Random().nextInt(63)+1));
		standartstuff.add(new ItemStack(Material.STONE, new Random().nextInt(63)+1));
		standartstuff.add(new ItemStack(Material.WOOD, new Random().nextInt(63)+1));
		standartstuff.add(new ItemStack(Material.GLASS, new Random().nextInt(63)+1));
		standartstuff.add(new ItemStack(Material.BRICK, new Random().nextInt(63)+1));
		standartstuff.add(new ItemStack(Material.WATER_BUCKET, 1));
		standartstuff.add(new ItemStack(Material.WATER_BUCKET, 1));
		standartstuff.add(new ItemStack(Material.STONE_AXE, 1));
		standartstuff.add(new ItemStack(Material.WOOD_AXE, 1));
		standartstuff.add(new ItemStack(Material.LEATHER_BOOTS, 1));
		standartstuff.add(new ItemStack(Material.LEATHER_HELMET, 1));
		standartstuff.add(new ItemStack(Material.LEATHER_CHESTPLATE, 1));
		standartstuff.add(new ItemStack(Material.LEATHER_LEGGINGS, 1));
		standartstuff.add(new ItemStack(Material.CHAINMAIL_BOOTS, 1));
		standartstuff.add(new ItemStack(Material.CHAINMAIL_HELMET, 1));
		standartstuff.add(new ItemStack(Material.CHAINMAIL_CHESTPLATE, 1));
		standartstuff.add(new ItemStack(Material.CHAINMAIL_LEGGINGS, 1));
		standartstuff.add(createitem(1,Material.GOLD_CHESTPLATE, Enchantment.PROTECTION_ENVIRONMENTAL, 1, 0, null));
		standartstuff.add(createitem(1,Material.GOLD_BOOTS, Enchantment.PROTECTION_ENVIRONMENTAL, 1, 0, null));
		standartstuff.add(createitem(1,Material.GOLD_LEGGINGS, Enchantment.PROTECTION_ENVIRONMENTAL, 1, 0, null));
		standartstuff.add(createitem(1,Material.GOLD_HELMET, Enchantment.PROTECTION_ENVIRONMENTAL, 1, 0, null));
		standartstuff.add(createitem(1,Material.WOOD_SWORD, Enchantment.DAMAGE_ALL, 2, 0, null));
		standartstuff.add(createitem(1,Material.STONE_SWORD, Enchantment.DAMAGE_ALL, 1, 0, null));
		standartstuff.add(createitem(1,Material.GOLD_SWORD, Enchantment.DAMAGE_ALL, 1, 0, null));
		standartstuff.add(createitem(1,Material.STONE_SWORD, Enchantment.DAMAGE_ALL, 1, 0, null));
		standartstuff.add(createitem(1,Material.GOLD_SWORD, Enchantment.DAMAGE_ALL, 1, 0, null));
		standartstuff.add(new ItemStack(Material.STONE_PICKAXE, 1));
		standartstuff.add(new ItemStack(Material.WOOD_PICKAXE, 1));
		standartstuff.add(createitem(1,Material.GOLD_PICKAXE, Enchantment.DIG_SPEED, 1, 0, null));
		standartstuff.add(new ItemStack(Material.WEB, new Random().nextInt(10)+1));
		standartstuff.add(new ItemStack(Material.WEB, new Random().nextInt(10)+1));
		standartstuff.add(new ItemStack(Material.WEB, new Random().nextInt(10)+1));
		standartstuff.add(new ItemStack(Material.LAVA_BUCKET, 1));
		standartstuff.add(new ItemStack(Material.COOKED_BEEF, new Random().nextInt(20)+1));
		standartstuff.add(new ItemStack(Material.RAW_BEEF, new Random().nextInt(20)+1));
		standartstuff.add(new ItemStack(Material.CAKE, 1));
		standartstuff.add(new ItemStack(Material.PUMPKIN_PIE, new Random().nextInt(7)+1));
//		====================================================================================================================================
		// normal____________________________________________________________________________________________________________________
		normal.add(new ItemStack(Material.IRON_INGOT, new Random().nextInt(4)+1));
		normal.add(new ItemStack(Material.GOLDEN_APPLE, 1));
		normal.add(createitem(1,Material.IRON_BOOTS, Enchantment.PROTECTION_ENVIRONMENTAL, 1, 0, null));
		normal.add(createitem(1,Material.IRON_HELMET, Enchantment.PROTECTION_ENVIRONMENTAL, 1, 0, null));
		normal.add(new ItemStack(Material.EXP_BOTTLE, new Random().nextInt(7)+1));
		normal.add(new ItemStack(Material.TNT, new Random().nextInt(4)+1));
		normal.add(new ItemStack(Material.IRON_INGOT, new Random().nextInt(4)+1));
		normal.add(new ItemStack(Material.COOKED_BEEF, new Random().nextInt(20)+1));
		normal.add(new ItemStack(Material.RAW_BEEF, new Random().nextInt(20)+1));
		normal.add(new ItemStack(Material.CAKE, 1));
		normal.add(new ItemStack(Material.PUMPKIN_PIE, new Random().nextInt(7)+1));
//		====================================================================================================================================
		// op____________________________________________________________________________________________________________________
		op.add(new ItemStack(Material.DIAMOND, new Random().nextInt(4)+1));
		op.add(createitem(1,Material.IRON_CHESTPLATE, Enchantment.PROTECTION_ENVIRONMENTAL, 1, 0, null));
		op.add(createitem(1,Material.IRON_LEGGINGS, Enchantment.PROTECTION_ENVIRONMENTAL, 1, 0, null));
		op.add(createitem(1,Material.DIAMOND_HELMET, Enchantment.PROTECTION_ENVIRONMENTAL, 1, 0, null));
		op.add(createitem(1,Material.DIAMOND_BOOTS, Enchantment.PROTECTION_ENVIRONMENTAL, 1, 0, null));
		op.add(new ItemStack(Material.ENDER_PEARL, 1));
//		====================================================================================================================================
		// extremeop____________________________________________________________________________________________________________________
		extremeop.add(createitem(1,Material.DIAMOND_HELMET, Enchantment.PROTECTION_ENVIRONMENTAL, 1, 0, null));
		extremeop.add(createitem(1,Material.DIAMOND_BOOTS, Enchantment.PROTECTION_ENVIRONMENTAL, 1, 0, null));
		extremeop.add(createitem(1,Material.DIAMOND_LEGGINGS, Enchantment.PROTECTION_ENVIRONMENTAL, 1, 0, null));
		extremeop.add(createitem(1,Material.DIAMOND_CHESTPLATE, Enchantment.PROTECTION_ENVIRONMENTAL, 1, 0, null));
		
		
		for(int i = 0;i<27;i++){
		Random r = new Random();
		int ra = r.nextInt(25);
		
		if(ra==0&&standartstuff.size()>-1){
			Random r1 = new Random();
			int item = r1.nextInt(standartstuff.size());
			inv.setItem(i, standartstuff.get(item));
			standartstuff.remove(item);
		}else if(ra==1&&standartstuff.size()>-1){
			Random r1 = new Random();
			int item = r1.nextInt(standartstuff.size());
			inv.setItem(i, standartstuff.get(item));
			standartstuff.remove(item);
		}else if(ra==2&&standartstuff.size()>-1){
			Random r1 = new Random();
			int item = r1.nextInt(standartstuff.size());
			inv.setItem(i, standartstuff.get(item));
			standartstuff.remove(item);
		}else if(ra==3&&standartstuff.size()>-1){
			Random r1 = new Random();
			int item = r1.nextInt(standartstuff.size());
			inv.setItem(i, standartstuff.get(item));
			standartstuff.remove(item);
		}else if(ra==4&&normal.size()>-1){
			Random r1 = new Random();
			int item = r1.nextInt(normal.size());
			inv.setItem(i, normal.get(item));
			normal.remove(item);
		}else if(ra==5&&normal.size()>-1){
			Random r1 = new Random();
			int item = r1.nextInt(normal.size());
			inv.setItem(i, normal.get(item));
			normal.remove(item);
		}else if(ra==6&&op.size()>-1){
			Random rrr = new Random();
			int rrrr = rrr.nextInt(3);
			if(rrrr==0){
			Random r1 = new Random();
			int item = r1.nextInt(op.size());
			inv.setItem(i, op.get(item));
			op.remove(item);
			}else{
				Random r1 = new Random();
				int item = r1.nextInt(op.size());
				inv.setItem(i, op.get(item));
				op.remove(item);	
			}
		}else if(ra==7&&standartstuff.size()>-1){
			Random r1 = new Random();
			int item = r1.nextInt(standartstuff.size());
			inv.setItem(i, standartstuff.get(item));
			standartstuff.remove(item);
		}else if(ra==8&&standartstuff.size()>-1){
			Random r1 = new Random();
			int item = r1.nextInt(standartstuff.size());
			inv.setItem(i, standartstuff.get(item));
			standartstuff.remove(item);
		}else if(ra==9&&standartstuff.size()>-1){
			Random r1 = new Random();
			int item = r1.nextInt(standartstuff.size());
			inv.setItem(i, standartstuff.get(item));
			standartstuff.remove(item);
		}else if(ra==10&&normal.size()>-1){
			Random r1 = new Random();
			int item = r1.nextInt(normal.size());
			inv.setItem(i, normal.get(item));
			normal.remove(item);
		}else if(ra==11&&normal.size()>-1){
			Random r1 = new Random();
			int item = r1.nextInt(normal.size());
			inv.setItem(i, normal.get(item));
			normal.remove(item);
		}
		}
		return inv;
	}
	// ===============================================================================
	public static Inventory createextrachest() {
		Inventory inv = Bukkit.createInventory(null, 27, "Truhe");
		ArrayList<ItemStack> standartstuff = new ArrayList<>();
		ArrayList<ItemStack> normal = new ArrayList<>();
		ArrayList<ItemStack> op = new ArrayList<>();
		ArrayList<ItemStack> extremeop = new ArrayList<>();
		// standart____________________________________________________________________________________________________________________
		standartstuff.add(new ItemStack(Material.COMPASS, 1));
		standartstuff.add(new ItemStack(Material.STONE, new Random().nextInt(63)+1));
		standartstuff.add(new ItemStack(Material.WOOD, new Random().nextInt(63)+1));
		standartstuff.add(new ItemStack(Material.STONE, new Random().nextInt(63)+1));
		standartstuff.add(new ItemStack(Material.WOOD, new Random().nextInt(63)+1));
		standartstuff.add(new ItemStack(Material.GLASS, new Random().nextInt(63)+1));
		standartstuff.add(new ItemStack(Material.STONE, new Random().nextInt(63)+1));
		standartstuff.add(new ItemStack(Material.WOOD, new Random().nextInt(63)+1));
		standartstuff.add(new ItemStack(Material.GLASS, new Random().nextInt(63)+1));
		standartstuff.add(new ItemStack(Material.BRICK, new Random().nextInt(63)+1));
		standartstuff.add(new ItemStack(Material.BRICK, new Random().nextInt(63)+1));
		standartstuff.add(new ItemStack(Material.WATER_BUCKET, 1));
		standartstuff.add(new ItemStack(Material.WATER_BUCKET, 1));
		standartstuff.add(new ItemStack(Material.STONE_AXE, 1));
		standartstuff.add(new ItemStack(Material.WOOD_AXE, 1));
		standartstuff.add(new ItemStack(Material.LEATHER_BOOTS, 1));
		standartstuff.add(new ItemStack(Material.LEATHER_HELMET, 1));
		standartstuff.add(new ItemStack(Material.LEATHER_CHESTPLATE, 1));
		standartstuff.add(new ItemStack(Material.LEATHER_LEGGINGS, 1));
		standartstuff.add(new ItemStack(Material.CHAINMAIL_BOOTS, 1));
		standartstuff.add(new ItemStack(Material.CHAINMAIL_HELMET, 1));
		standartstuff.add(new ItemStack(Material.CHAINMAIL_CHESTPLATE, 1));
		standartstuff.add(new ItemStack(Material.CHAINMAIL_LEGGINGS, 1));
		standartstuff.add(createitem(1,Material.GOLD_CHESTPLATE, Enchantment.PROTECTION_ENVIRONMENTAL, 1, 0, null));
		standartstuff.add(createitem(1,Material.GOLD_BOOTS, Enchantment.PROTECTION_ENVIRONMENTAL, 1, 0, null));
		standartstuff.add(createitem(1,Material.GOLD_LEGGINGS, Enchantment.PROTECTION_ENVIRONMENTAL, 1, 0, null));
		standartstuff.add(createitem(1,Material.GOLD_HELMET, Enchantment.PROTECTION_ENVIRONMENTAL, 1, 0, null));
		standartstuff.add(createitem(1,Material.WOOD_SWORD, Enchantment.DAMAGE_ALL, 2, 0, null));
		standartstuff.add(createitem(1,Material.STONE_SWORD, Enchantment.DAMAGE_ALL, 1, 0, null));
		standartstuff.add(createitem(1,Material.GOLD_SWORD, Enchantment.DAMAGE_ALL, 1, 0, null));
		standartstuff.add(createitem(1,Material.STONE_SWORD, Enchantment.DAMAGE_ALL, 1, 0, null));
		standartstuff.add(createitem(1,Material.GOLD_SWORD, Enchantment.DAMAGE_ALL, 1, 0, null));
		standartstuff.add(new ItemStack(Material.STONE_PICKAXE, 1));
		standartstuff.add(new ItemStack(Material.WOOD_PICKAXE, 1));
		standartstuff.add(createitem(1,Material.GOLD_PICKAXE, Enchantment.DIG_SPEED, 1, 0, null));
		standartstuff.add(new ItemStack(Material.WEB, new Random().nextInt(10)+1));
		standartstuff.add(new ItemStack(Material.WEB, new Random().nextInt(10)+1));
		standartstuff.add(new ItemStack(Material.WEB, new Random().nextInt(10)+1));
		standartstuff.add(new ItemStack(Material.WEB, new Random().nextInt(10)+1));
		standartstuff.add(new ItemStack(Material.LAVA_BUCKET, 1));
		standartstuff.add(new ItemStack(Material.COOKED_BEEF, new Random().nextInt(20)+1));
		standartstuff.add(new ItemStack(Material.RAW_BEEF, new Random().nextInt(20)+1));
		standartstuff.add(new ItemStack(Material.CAKE, 1));
		standartstuff.add(new ItemStack(Material.PUMPKIN_PIE, new Random().nextInt(7)+1));
//		====================================================================================================================================
		// normal____________________________________________________________________________________________________________________
		normal.add(new ItemStack(Material.IRON_INGOT, new Random().nextInt(4)+1));
		normal.add(new ItemStack(Material.IRON_INGOT, new Random().nextInt(4)+1));
		normal.add(new ItemStack(Material.GOLDEN_APPLE, 1));
		normal.add(createitem(1,Material.IRON_CHESTPLATE, Enchantment.PROTECTION_ENVIRONMENTAL, 1, 0, null));
		normal.add(createitem(1,Material.IRON_BOOTS, Enchantment.PROTECTION_ENVIRONMENTAL, 1, 0, null));
		normal.add(createitem(1,Material.IRON_LEGGINGS, Enchantment.PROTECTION_ENVIRONMENTAL, 1, 0, null));
		normal.add(createitem(1,Material.IRON_HELMET, Enchantment.PROTECTION_ENVIRONMENTAL, 1, 0, null));
		normal.add(new ItemStack(Material.EXP_BOTTLE, new Random().nextInt(7)+1));
		normal.add(new ItemStack(Material.TNT, new Random().nextInt(4)+1));
		normal.add(new ItemStack(Material.IRON_INGOT, new Random().nextInt(4)+1));
		normal.add(new ItemStack(Material.COOKED_BEEF, new Random().nextInt(20)+1));
		normal.add(new ItemStack(Material.RAW_BEEF, new Random().nextInt(20)+1));
		normal.add(new ItemStack(Material.CAKE, 1));
		normal.add(new ItemStack(Material.PUMPKIN_PIE, new Random().nextInt(7)+1));
//		====================================================================================================================================
		// op____________________________________________________________________________________________________________________
		op.add(new ItemStack(Material.DIAMOND, new Random().nextInt(4)+1));
		op.add(new ItemStack(Material.DIAMOND, new Random().nextInt(4)+1));
		op.add(new ItemStack(Material.DIAMOND, new Random().nextInt(4)+1));
		op.add(new ItemStack(Material.DIAMOND, new Random().nextInt(4)+1));
		op.add(createitem(1,Material.DIAMOND_LEGGINGS, Enchantment.PROTECTION_ENVIRONMENTAL, 1, 0, null));
		op.add(createitem(1,Material.DIAMOND_HELMET, Enchantment.PROTECTION_ENVIRONMENTAL, 1, 0, null));
		op.add(createitem(1,Material.DIAMOND_CHESTPLATE, Enchantment.PROTECTION_ENVIRONMENTAL, 1, 0, null));
		op.add(createitem(1,Material.DIAMOND_BOOTS, Enchantment.PROTECTION_ENVIRONMENTAL, 1, 0, null));
		op.add(createitem(1,Material.DIAMOND_HELMET, Enchantment.PROTECTION_ENVIRONMENTAL, 1, 0, null));
		op.add(new ItemStack(Material.ENDER_PEARL, 1));
//		====================================================================================================================================
		// extremeop____________________________________________________________________________________________________________________
		extremeop.add(new ItemStack(Material.SNOW_BALL, new Random().nextInt(7)+1));
		extremeop.add(new ItemStack(Material.EGG, new Random().nextInt(7)+1));
		
		
		for(int i = 0;i<27;i++){
		Random r = new Random();
		int ra = r.nextInt(15);
		
		if(ra==0&&standartstuff.size()>0){
			Random r1 = new Random();
			int item = r1.nextInt(standartstuff.size());
			inv.setItem(i, standartstuff.get(item));
			standartstuff.remove(item);
		}else if(ra==1&&standartstuff.size()>0){
			Random r1 = new Random();
			int item = r1.nextInt(standartstuff.size());
			inv.setItem(i, standartstuff.get(item));
			standartstuff.remove(item);
		}else if(ra==2&&op.size()>0){
			Random r1 = new Random();
			int item = r1.nextInt(op.size());
			inv.setItem(i, op.get(item));
			op.remove(item);
		}else if(ra==3&&extremeop.size()>0){
			Random r1 = new Random();
			int item = r1.nextInt(extremeop.size());
			inv.setItem(i, extremeop.get(item));
			extremeop.remove(item);
		}else if(ra==4&&normal.size()>0){
			Random r1 = new Random();
			int item = r1.nextInt(normal.size());
			inv.setItem(i, normal.get(item));
			normal.remove(item);
		}else if(ra==5&&normal.size()>0){
			Random r1 = new Random();
			int item = r1.nextInt(normal.size());
			inv.setItem(i, normal.get(item));
			normal.remove(item);
		}else if(ra==6&&standartstuff.size()>0){
			Random r1 = new Random();
			int item = r1.nextInt(standartstuff.size());
			inv.setItem(i, standartstuff.get(item));
			standartstuff.remove(item);
		}else if(ra==7&&extremeop.size()>0){
			Random r1 = new Random();
			int item = r1.nextInt(extremeop.size());
			inv.setItem(i, extremeop.get(item));
			extremeop.remove(item);
		}else if(ra==8&&extremeop.size()>0){
			Random r1 = new Random();
			int item = r1.nextInt(extremeop.size());
			inv.setItem(i, extremeop.get(item));
			extremeop.remove(item);
		}
		}
		return inv;
	}
	// ===============================================================================

	public static boolean isnormalchest(Location loc) {
		boolean i = true;
		//
		String mapname = Main.mapname;
		Double centerx = Main.loc.getDouble(mapname + ".center.x");
		Double centery = Main.loc.getDouble(mapname + ".center.y");
		Double centerz = Main.loc.getDouble(mapname + ".center.z");
		Double size = Main.loc.getDouble(mapname + ".center.size");
		//
		Location center = new Location(Bukkit.getWorld("gameworld"), centerx, centery, centerz);
		Double distance = center.distance(loc);
		if (distance <= size) {
			i = false;
		}
		return i;
	}

	
	public static ItemStack createitem(int Amount,Material m, Enchantment Entchantment1,int ench1,int ench2,Enchantment Entschantment2){
		ItemStack item = new ItemStack(m,Amount);
		ItemMeta meta = item.getItemMeta();
		meta.addEnchant(Entchantment1, ench1, true);
		if(Entschantment2 != null){
		meta.addEnchant(Entschantment2, ench2, true);
		}
		item.setItemMeta(meta);
		return item;
	}
	

	@EventHandler
	public void onplace1(BlockPlaceEvent e) {
		if (e.getBlock().getType() == Material.CHEST) {
			Inventory inv = Bukkit.createInventory(null, 27, "Truhe");
			chest.put(e.getBlock().getLocation(), inv);
		}
	}

	@EventHandler
	public void ondestroy1(BlockBreakEvent e) {
		if (e.getBlock().getType() == Material.CHEST) {
			e.setCancelled(true);
			e.getBlock().setType(Material.AIR);
			e.getBlock().getWorld().dropItem(e.getBlock().getLocation(), new ItemStack(Material.CHEST));
			if (chest.containsKey(e.getBlock().getLocation())) {
				
				Location loc = e.getBlock().getLocation();
				for(Player pp : Bukkit.getOnlinePlayers()){
					if(pp.getOpenInventory().equals(chest.get(loc))){
						pp.closeInventory();
					}
				}
				for (ItemStack is : chest.get(loc)) {
					if (is != null) {
						loc.getWorld().dropItem(loc, is);
					}
				}

			} else {
				if(isnormalchest(e.getBlock().getLocation())){
				Inventory inv = createnormalchest();
				chest.put(e.getBlock().getLocation(), inv);
				Location loc = e.getBlock().getLocation();
				for (ItemStack is : chest.get(loc)) {
					if (is != null) {
						loc.getWorld().dropItem(loc, is);
					}
				}
				}else{
					Inventory inv = createextrachest();
					chest.put(e.getBlock().getLocation(), inv);
					Location loc = e.getBlock().getLocation();
					for (ItemStack is : chest.get(loc)) {
						if (is != null) {
							loc.getWorld().dropItem(loc, is);
						}
					}
				}
			}
		}
	}
	
}
