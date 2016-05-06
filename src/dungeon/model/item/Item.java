package dungeon.model.item;

import dungeon.model.types.ItemSubType;

import static dungeon.model.enums.ItemType.ARMOR;
import static dungeon.model.enums.ItemType.WEAPON;

public final class Item {

    /* ========== ATTRIBUTES ========== */
    private ItemSubType type;

    /* ========== CONSTRUCTORS ========== */
    public Item(ItemSubType type) {
        this.type = type;
    }

    /* ========== SERVICES ========== */
    public boolean isArmor() {
        return type.getType() == ARMOR;
    }

    public boolean isWeapon() {
        return type.getType() == WEAPON;
    }

    public ItemSubType getType() {
        return type;
    }

    @Override
    public String toString() {
        return type.getName();
    }
}