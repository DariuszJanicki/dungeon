package dungeon.model.types.parser;

import dungeon.constants.ResourcesContainer;
import dungeon.model.types.ItemSubType;

import java.util.List;

public final class ItemTypeParser extends AbstractTypeParser {

    /* ========== STATIC ========== */
    private static final ItemTypeParser singleton = new ItemTypeParser();

    public static ItemTypeParser singleton() {
        return singleton;
    }

    /* ========== ATTRIBUTES ========== */
    public List<ItemSubType> types = readItem(ResourcesContainer.items);
}