package xyndex.inventoryapi.buttons;

import org.bukkit.inventory.ItemStack;

public class Button {
    private ItemStack icon;

    public Button(ItemStack icon){
        this.icon = icon;
    }

    public ItemStack getIcon() {
        return icon;
    }

    public void setIcon(ItemStack icon) {
        this.icon = icon;
    }
}
