package dungeon.model.types;

import dungeon.model.enums.ItemType;

public final class ItemSubType {

    /* ========== ATTRIBUTES ========== */
    private String name;
    private ItemType type;

    /* ========== SERVICES ========== */

    @Override
    public String toString() {
        return name;
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof ItemSubType && name.equals(((ItemSubType) obj).name);
    }

    /* ========== PROPERTIES ========== */
    public String getName() {
        return name;
    }

    void setName(String name) {
        this.name = name;
    }

    public ItemType getType() {
        return type;
    }

    void setType(ItemType type) {
        this.type = type;
    }
}