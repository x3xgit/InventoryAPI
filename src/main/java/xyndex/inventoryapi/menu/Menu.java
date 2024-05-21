package xyndex.inventoryapi.menu;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.plugin.java.JavaPlugin;
import xyndex.inventoryapi.InventoryAPI;
import xyndex.inventoryapi.buttons.Button;

import java.util.HashMap;
import java.util.Map;

public class Menu implements InventoryHolder {
    private final JavaPlugin owner;
    private final InventoryAPI inventoryAPI;

    private String name;
    private final int rows;
    private final Map<Integer, Button> items;

    private boolean blockDefaultInteractions;

    public Menu(JavaPlugin owner, InventoryAPI inventoryAPI, int rows, String name) {
        this.owner = owner;
        this.inventoryAPI = inventoryAPI;
        this.name = ChatColor.translateAlternateColorCodes('&', name);
        this.rows = rows;
        this.items = new HashMap<>();
    }

    public void setBlockDefaultInteractions(boolean blockDefaultInteractions) {
        this.blockDefaultInteractions = blockDefaultInteractions;
    }

    public Boolean areDefaultInteractionsBlocked() {
        return blockDefaultInteractions;
    }

    public JavaPlugin getOwner() {
        return owner;
    }

    public void setName(String name) {
        this.name = ChatColor.translateAlternateColorCodes('&', name);
    }

    public String getName() {
        return name;
    }

    public void setButton(int slot, Button button) {
        items.put(slot, button);
    }

    public void setButton(int page, int slot, Button button) {
        if (slot < 0)
            return;

        items.remove(slot, button);
    }

    public void removeButton(int slot) {
        if (slot < 0)
            return;

        removeButton(slot);
    }

    public Button getButton(int slot) {
        if (slot < 0)
            return null;

        return getButton(slot);
    }

    public int getRows() {
        return rows;
    }

    @Override
    public Inventory getInventory() {
        Inventory inventory = Bukkit.createInventory(this, rows * 9, name);

        for (int slot = 0; slot < getRows() * 9; slot++)
            if (items.containsKey(slot))
                inventory.setItem(slot, items.get(slot).getIcon());

        return inventory;
    }
}

