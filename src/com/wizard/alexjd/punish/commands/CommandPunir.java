package com.wizard.alexjd.punish.commands;

import com.wizard.alexjd.punish.Core;
import com.wizard.alexjd.punish.api.PunishAPI;
import com.wizard.alexjd.punish.utils.MakeItem;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;

public class CommandPunir extends PunishAPI implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {

        if(!(sender instanceof Player)) return false;

        Player p = (Player) sender;

        if(!sender.hasPermission("server.mod")){
            sender.sendMessage("§cVocê não tem permissão para executar esse comando.");
            return false;
        }

        if(args.length <= 1){
            sender.sendMessage("§cUtilize: /punir <player> <prova>");
            return false;
        }

        Player t = Bukkit.getPlayerExact(args[0]);
        if(t == null){
            sender.sendMessage("§cJogador não encontrado.");
            return false;
        }

        if(t == p){
            sender.sendMessage("§cVocê não pode punir sí mesmo.");
            return false;
        }

        Inventory inv = Bukkit.createInventory(null, 9*3, "§cSelecionar tipo:");

        ItemStack ban = new MakeItem(Core.getInstance().getConfig().getString("Select.ban.id"))
                .setName(Core.getInstance().getConfig().getString("Select.ban.name"))
                .addLore((ArrayList<String>) Core.getInstance().getConfig().getStringList("Select.ban.lore"))
                .build();

        ItemStack mute = new MakeItem(Core.getInstance().getConfig().getString("Select.mute.id"))
                .setName(Core.getInstance().getConfig().getString("Select.mute.name"))
                .addLore((ArrayList<String>) Core.getInstance().getConfig().getStringList("Select.mute.lore"))
                .build();

        inv.setItem(12, ban);
        inv.setItem(14, mute);

        p.openInventory(inv);

        prova.put(p, args[1]);
        alvo.put(p, t);

        return false;
    }
}
