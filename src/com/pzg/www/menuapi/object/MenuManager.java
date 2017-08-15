/*      This is a free resource      */
/*  Please give credit to TJPlaysNow */
/*     Do not delete this header     */
package com.pzg.www.menuapi.object;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.plugin.Plugin;

public class MenuManager implements Listener {
	
	private static List<Menu> menus = new ArrayList<Menu>();
	
	public MenuManager(Plugin plugin) {
		Bukkit.getPluginManager().registerEvents(this, plugin);
	}
	
	public void addMenu(Menu menu) {
		menus.add(menu);
	}
	
	@EventHandler
	public void onClick(InventoryClickEvent event) {
		for (Menu menu : menus) {
			if (event.getInventory().getName().equals(menu.inv.getName())) {
				menu.onClick(event);
			}
		}
	}
}