package dungeon.model.types.parser;

import dungeon.constants.ResourcesContainer;
import dungeon.model.types.FurnitureType;

import java.util.List;

public final class FurnitureParser extends AbstractTypeParser {

    /* ========== STATIC ========== */
    private static final FurnitureParser singleton = new FurnitureParser();

    public static FurnitureParser singleton() {
        return singleton;
    }

    /* ========== ATTRIBUTES ========== */
    public List<FurnitureType> types = readFurniture(ResourcesContainer.furniture);

    public FurnitureType find(String name) {
        return types.stream().filter(a -> a.getName().equals(name)).findFirst().get();
    }
}