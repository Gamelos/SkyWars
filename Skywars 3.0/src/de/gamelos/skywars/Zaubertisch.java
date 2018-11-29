package de.gamelos.skywars;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.enchantment.EnchantItemEvent;
import org.bukkit.event.enchantment.PrepareItemEnchantEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import net.md_5.bungee.api.ChatColor;

public class Zaubertisch implements Listener {
//
//	@EventHandler
//	public void onklick(PlayerInteractEvent e){
//		if(e.getAction() == Action.RIGHT_CLICK_BLOCK){
//			if(e.getClickedBlock().getType() == Material.ENCHANTMENT_TABLE){
//				if(!Main.spectatorplayer.contains(e.getPlayer())){
//				e.setCancelled(true);
//				Inventory inv = Bukkit.createInventory(null, 9*3, ChatColor.YELLOW+"Enchanting");
//				
//				ItemStack item = new ItemStack(Material.STAINED_GLASS_PANE);
//				ItemMeta meta = item.getItemMeta();
//				meta.setDisplayName(" ");
//				item.setItemMeta(meta);
//				
//				for(int i = 0;i < 9*3;i++){
//					inv.setItem(i, item);
//				}
//				
//				ItemStack item1 = new ItemStack(Material.EXP_BOTTLE);
//				ItemMeta meta1 = item1.getItemMeta();
//				meta1.setDisplayName(ChatColor.YELLOW+"Level 1");
//				item1.setItemMeta(meta1);
//				
//				ItemStack item11 = new ItemStack(Material.EXP_BOTTLE);
//				ItemMeta meta11 = item11.getItemMeta();
//				meta11.setDisplayName(ChatColor.YELLOW+"Level 2");
//				item11.setItemMeta(meta11);
//				
//				ItemStack item111 = new ItemStack(Material.EXP_BOTTLE);
//				ItemMeta meta111 = item111.getItemMeta();
//				meta111.setDisplayName(ChatColor.YELLOW+"Level 3");
//				item111.setItemMeta(meta111);
//				
//				ItemStack item1111 = new ItemStack(Material.HOPPER);
//				ItemMeta meta1111 = item1111.getItemMeta();
//				meta1111.setDisplayName(ChatColor.YELLOW+"Hier Item reinlegen");
//				item1111.setItemMeta(meta1111);
//				
//				inv.setItem(10, new ItemStack(Material.AIR));
//				inv.setItem(12, item1);
//				inv.setItem(14, item11);
//				inv.setItem(16, item111);
//				inv.setItem(1, item1111);
//				
//				e.getPlayer().openInventory(inv);
//				
//			}
//		}
//		}
//	}
//	
//	public static List<Enchantment> level1 = new ArrayList<>();
//	public static List<Enchantment> level2 = new ArrayList<>();
//	
//	@EventHandler
//	public static void onklick(InventoryClickEvent e){
//		if(e.getInventory().getTitle().equals(ChatColor.YELLOW+"Enchanting")){
//			if(e.getClickedInventory().getType()!=InventoryType.PLAYER&&(e.getCurrentItem().getType() == Material.STAINED_GLASS_PANE|| e.getCurrentItem().getType()== Material.EXP_BOTTLE||e.getCurrentItem().getType()==Material.HOPPER)){
//			e.setCancelled(true);
//			}
//			if(e.getCurrentItem().getType() != Material.AIR&&e.getCurrentItem().getItemMeta().getDisplayName()!=null){
//				Player p = (Player) e.getWhoClicked();
//				if(e.getCurrentItem().getItemMeta().getDisplayName().equals(ChatColor.YELLOW+"Level 1")){
//					if(e.getInventory().getItem(10)!=null){
//						if(p.getLevel() >= 1){
//							if(e.getInventory().getItem(10).getType() == Material.DIAMOND_SWORD||e.getInventory().getItem(10).getType() == Material.IRON_SWORD||e.getInventory().getItem(10).getType() == Material.GOLD_SWORD||e.getInventory().getItem(10).getType() == Material.STONE_SWORD||e.getInventory().getItem(10).getType() == Material.WOOD_SWORD){
//							e.getInventory().getItem(10).addEnchantment(Enchantment.DURABILITY, 1);
//							}else{
//								e.getInventory().getItem(10).addEnchantment(Enchantment.PROTECTION_FALL, 1);	
//							}
//							int l = p.getLevel();
//							l--;
//							p.setLevel(l);
//						}else{
//							p.sendMessage(ChatColor.RED+"Du hast nicht genügend Level dafür");
//						}
//							}else{
//								p.sendMessage(ChatColor.RED+"Du musst ein Item einlegen");	
//							}	
//				}else if(e.getCurrentItem().getItemMeta().getDisplayName().equals(ChatColor.YELLOW+"Level 2")){
//					if(e.getInventory().getItem(10)!=null){
//						if(p.getLevel() >= 3){
//							if(e.getInventory().getItem(10).getType() == Material.DIAMOND_SWORD||e.getInventory().getItem(10).getType() == Material.IRON_SWORD||e.getInventory().getItem(10).getType() == Material.GOLD_SWORD||e.getInventory().getItem(10).getType() == Material.STONE_SWORD||e.getInventory().getItem(10).getType() == Material.WOOD_SWORD){
//						e.getInventory().getItem(10).addEnchantment(Enchantment.DAMAGE_ALL, 1);
//							}else{
//								e.getInventory().getItem(10).addEnchantment(Enchantment.PROTECTION_PROJECTILE, 1);
//							}
//						int l = p.getLevel();
//						int b = l-3;
//						p.setLevel(b);
//						}else{
//							p.sendMessage(ChatColor.RED+"Du hast nicht genügend Level dafür");
//						}
//					}else{
//						p.sendMessage(ChatColor.RED+"Du musst ein Item einlegen");	
//					}
//				}else if(e.getCurrentItem().getItemMeta().getDisplayName().equals(ChatColor.YELLOW+"Level 3")){
//					if(e.getInventory().getItem(10)!=null){
//						if(p.getLevel() >= 6){
//							if(e.getInventory().getItem(10).getType() == Material.DIAMOND_SWORD||e.getInventory().getItem(10).getType() == Material.IRON_SWORD||e.getInventory().getItem(10).getType() == Material.GOLD_SWORD||e.getInventory().getItem(10).getType() == Material.STONE_SWORD||e.getInventory().getItem(10).getType() == Material.WOOD_SWORD){
//						e.getInventory().getItem(10).addEnchantment(Enchantment.KNOCKBACK, 1);
//							}else{
//								e.getInventory().getItem(10).addEnchantment(Enchantment.PROTECTION_PROJECTILE, 2);
//							}
//						int l = p.getLevel();
//						int b = l-6;
//						p.setLevel(b);
//						}else{
//							p.sendMessage(ChatColor.RED+"Du hast nicht genügend Level dafür");
//						}
//					}else{
//						p.sendMessage(ChatColor.RED+"Du musst ein Item einlegen");	
//					}
//				}
//			}
//		}
//	}
//	
//	@EventHandler
//	public void close(InventoryCloseEvent e){
//		if(e.getInventory().getTitle().equals(ChatColor.YELLOW+"Enchanting")){
//			if(e.getInventory().getItem(10).getType()!=Material.AIR){
//				e.getPlayer().getInventory().addItem(e.getInventory().getItem(10));
//			}
//		}
//	}
	
	
	public static ArrayList<Player> hasench = new ArrayList<>();
	
	@EventHandler
	public void onench(EnchantItemEvent e){
		Player p = e.getEnchanter();
		
		
		if(e.getExpLevelCost()==6){
			if(!hasench.contains(p)){
			if(p.getLevel()>=6){
			p.setLevel(p.getLevel()-6);
			ItemStack item = e.getItem();
			if(item.getType() == Material.DIAMOND_SWORD||item.getType() == Material.IRON_SWORD||item.getType() == Material.WOOD_SWORD||item.getType() == Material.STONE_SWORD){
			e.getEnchantsToAdd().clear();
			item.addEnchantment(Enchantment.KNOCKBACK, 1);
			item.addEnchantment(Enchantment.DAMAGE_ALL, 1);
			hasench.add(p);
			}
			}
			}
		}
	}
	
	@EventHandler
	public void onpreen(PrepareItemEnchantEvent e){
		if(!hasench.contains(e.getEnchanter())){
		e.getExpLevelCostsOffered()[0] = 1;
		e.getExpLevelCostsOffered()[1] = 3;
		e.getExpLevelCostsOffered()[2] = 6;
		}
	}
	
}
