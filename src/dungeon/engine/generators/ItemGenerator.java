package dungeon.engine.generators;

import dungeon.model.types.parser.ItemTypeParser;
import dungeon.utils.Dice;
import dungeon.model.item.Item;
import dungeon.model.types.ItemSubType;

import java.util.List;

public final class ItemGenerator {

    /* ========== CONSTRUCTOR ========== */
    private ItemGenerator() {
    }

    /* ========== CONSTRUCTOR ========== */
    public static Item generate() {
        List<ItemSubType> types = ItemTypeParser.singleton().types;
        return new Item(types.get(Dice.k(types.size())));
    }
}