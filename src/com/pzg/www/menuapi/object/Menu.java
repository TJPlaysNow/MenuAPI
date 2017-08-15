/*      This is a free resource      */
/*  Please give credit to TJPlaysNow */
/*     Do not delete this header     */
package com.pzg.www.menuapi.object;

import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class Menu {
	
	Inventory inv;
	HashMap<ItemStack, Item> events = new HashMap<ItemStack, Item>();
	
	public Menu(String name, InventoryType type, MenuManager man) {
		inv = Bukkit.createInventory(null, type, name);
		man.addMenu(this);
	}
	
	public Menu(String name, int rows, MenuManager man) {
		inv = Bukkit.createInventory(null, rows * 9, name);
		man.addMenu(this);
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
	
	public void onClick(InventoryClickEvent event) {
		if (event.getCurrentItem() != null || event.getCurrentItem().getType() != Material.AIR) {
			if (events.containsKey(event.getCurrentItem())) events.get(event.getCurrentItem()).clickEvent(event);
		}
	}
}