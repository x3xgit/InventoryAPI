package xyndex.inventoryapi.menu;

import org.bukkit.event.Event;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryAction;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.plugin.java.JavaPlugin;
import xyndex.inventoryapi.InventoryAPI;

import java.util.Arrays;

public class MenuListener implements Listener {
    private static final ClickType[] PERMITTED_MENU_CLICK_TYPES = new ClickType[]{
            ClickType.LEFT,
            ClickType.RIGHT,
    };

    private static final InventoryAction[] BLOCKED_MENU_ACTIONS = new InventoryAction[] {
            InventoryAction.MOVE_TO_OTHER_INVENTORY,
            InventoryAction.COLLECT_TO_CURSOR,
    };

    private final JavaPlugin owner;
    private final InventoryAPI inventoryAPI;

    public MenuListener(JavaPlugin owner, InventoryAPI inventoryAPI) {
        this.owner = owner;
        this.inventoryAPI = inventoryAPI;
    }

    private static boolean shouldIgnoreInventoryEvent(Inventory inventory) {
        return !(inventory != null &&
                inventory.getHolder() != null &&
                inventory.getHolder() instanceof Menu);
    }


    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        event.getWhoClicked().sendMessage("You clicked!");

        if (shouldIgnoreInventoryEvent(event.getClickedInventory())) return;

        if (Arrays.stream(PERMITTED_MENU_CLICK_TYPES).noneMatch(type -> type == event.getClick())) {
            event.setResult(Event.Result.DENY);
            return;
        }

        if (Arrays.stream(BLOCKED_MENU_ACTIONS).anyMatch(action -> action == event.getAction())) {
            event.setResult(Event.Result.DENY);
            return;
        }

        Menu clickedGui = (Menu) event.getClickedInventory().getHolder();

        if (!clickedGui.getOwner().equals(owner)) return;

        boolean shouldCancel = (clickedGui.areDefaultInteractionsBlocked() != null && clickedGui.areDefaultInteractionsBlocked());

        if (shouldCancel) {
            event.setResult(Event.Result.DENY);
        }
    }
}