/*      This is a free resource      */
/*  Please give credit to TJPlaysNow */
/*     Do not delete this header     */
package com.pzg.www.menuapi.object;

import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;

public class Menu implements Listener {
	
	Inventory inv;
	HashMap<ItemStack, Item> events = new HashMap<ItemStack, Item>();
	
	public Menu(String name, InventoryType type, Plugin plugin) {
		inv = Bukkit.createInventory(null, type, name);
		Bukkit.getPluginManager().registerEvents(this, plugin);
	}
	
	public Menu(String name, int rows, Plugin plugin) {
		inv = Bukkit.createInventory(null, rows * 9, name);
		Bukkit.getPluginManager().registerEvents(this, plugin);
	}
	
	public void setItem(int slot, ItemStack item, Item event) {
		inv.setItem(slot, item);
		events.put(item, event);
	}
	
	public void addItem(ItemStack item, Item event) {
		inv.addItem(item);
		events.put(item, event);
	}
	
	public void removeItem(ItemStack item) {
		events.remove(item);
		inv.remove(item);
	}
	
	public void show(Player player) {
		player.openInventory(inv);
	}
	
	@EventHandler
	public void onClick(InventoryClickEvent event) {
		if (event.getCurrentItem() != null || event.getCurrentItem().getType() == Material.AIR) {
			if (event.getInventory().getName().equalsIgnoreCase(inv.getName())) {
				if (events.containsKey(event.getCurrentItem())) events.get(event.getCurrentItem()).clickEvent(event);
			}
		}
	}
}