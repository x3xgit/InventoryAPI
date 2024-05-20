package xyndex.inventoryapi;

import org.bukkit.plugin.java.JavaPlugin;
import xyndex.inventoryapi.menu.MenuListener;

public class InventoryAPI {

    public static JavaPlugin plugin;
    public static boolean blockDefaultInteractions = true;

    public InventoryAPI(JavaPlugin plugin) {
        plugin.getServer().getPluginManager().registerEvents(
                new MenuListener(plugin, this), plugin
        );
    }
}
