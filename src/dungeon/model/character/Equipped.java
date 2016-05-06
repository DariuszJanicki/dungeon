package dungeon.model.character;

import dungeon.model.item.Item;

import java.util.Optional;

public final class Equipped {

    /* ========== ATTRIBUTES ========== */
    private Optional<Item> armor = Optional.empty();
    private Optional<Item> weapon = Optional.empty();

    /* ========== SERVICES ========== */
    public String equip(Item item) {
        if (item.isArmor()) {
            armor = Optional.ofNullable(item);
            return "equipped a new armor - " + item;
        } else if (item.isWeapon()) {
            weapon = Optional.ofNullable(item);
            return "equipped a new weapon - " + item;
        }

        return "";
    }
}