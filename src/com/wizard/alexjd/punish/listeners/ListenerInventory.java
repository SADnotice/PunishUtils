package com.wizard.alexjd.punish.listeners;

import com.wizard.alexjd.punish.Core;
import com.wizard.alexjd.punish.api.PunishAPI;
import com.wizard.alexjd.punish.utils.MakeItem;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;

public class ListenerInventory extends PunishAPI implements Listener {

    @EventHandler
    public void onClick(InventoryClickEvent e){

        Player p = (Player) e.getWhoClicked();

        if(e.getInventory().getName().equals("§cSelecionar tipo:")){
            e.setCancelled(true);
            if(e.getSlot() == 12){

                Inventory inv = Bukkit.createInventory(null, 9*3, "§cSelecionar banimento:");

                for(String item : Core.getInstance().getConfig().getConfigurationSection("Ban.type").getKeys(false)) {
                    ItemStack mute = new MakeItem(Core.getInstance().getConfig().getString("Ban.type." + item + ".id"))
                            .setName(Core.getInstance().getConfig().getString("Ban.type." + item + ".name"))
                            .addLore((ArrayList<String>) Core.getInstance().getConfig().getStringList("Ban.type." + item + ".lore"))
                            .build();

                    inv.setItem(Core.getInstance().getConfig().getInt("Ban.type." + item + ".slot"), mute);
                }

                p.openInventory(inv);
                return;
            }
            if(e.getSlot() == 14){

                Inventory inv = Bukkit.createInventory(null, 9*3, "§cSelecionar mute:");

                for(String item : Core.getInstance().getConfig().getConfigurationSection("Mute.type").getKeys(false)) {
                    ItemStack mute = new MakeItem(Core.getInstance().getConfig().getString("Mute.type." + item + ".id"))
                            .setName(Core.getInstance().getConfig().getString("Mute.type." + item + ".name"))
                            .addLore((ArrayList<String>) Core.getInstance().getConfig().getStringList("Mute.type." + item + ".lore"))
                            .build();

                    inv.setItem(Core.getInstance().getConfig().getInt("Mute.type." + item + ".slot"), mute);
                }

                p.openInventory(inv);
            }
            return;
        }
        if(e.getInventory().getName().equals("§cSelecionar banimento:")){
            e.setCancelled(true);
            for(String item : Core.getInstance().getConfig().getConfigurationSection("Ban.type").getKeys(false)) {
                if(e.getSlot() == Core.getInstance().getConfig().getInt("Ban.type." + item + ".slot")) {
                    if(p.hasPermission(Core.getInstance().getConfig().getString("Ban.type." + item + ".permission"))) {
                        String cmd = Core.getInstance().getConfig().getString("Ban.type." + item + ".command");
                        Player t = alvo.get(p);
                        Bukkit.dispatchCommand(p, cmd.replace("%player%", t.getName()).replace("%prova%", prova.get(p)));
                    } else {
                        p.closeInventory();
                        p.sendMessage("§cVocê não tem permissão para aplicar essa punição.");
                    }
                }
            }
            return;
        }
        if(e.getInventory().getName().equals("§cSelecionar mute:")){
            e.setCancelled(true);
            for(String item : Core.getInstance().getConfig().getConfigurationSection("Mute.type").getKeys(false)) {
                if(e.getSlot() == Core.getInstance().getConfig().getInt("Mute.type." + item + ".slot")) {
                    if(p.hasPermission(Core.getInstance().getConfig().getString("Mute.type." + item + ".permission"))) {
                        String cmd = Core.getInstance().getConfig().getString("Mute.type." + item + ".command");
                        Player t = alvo.get(p);
                        Bukkit.dispatchCommand(p, cmd.replace("%player%", t.getName()).replace("%prova%", prova.get(p)));
                    } else {
                        p.closeInventory();
                        p.sendMessage("§cVocê não tem permissão para aplicar essa punição.");
                    }
                }
            }
            return;
        }
    }

}
