package dungeon.constants;

import java.io.File;

public final class ResourcesContainer {

    /* ========== STATIC ========== */
    public static File furnitureAttributes = new File("res/json/furnitureAttributes.txt");
    public static File chamberAttributes = new File("res/json/chamberAttributes.txt");
    public static File furniture = new File("res/json/furnitureTypes.txt");
    public static File recipes = new File("res/json/recipe.txt");
    public static File items = new File("res/json/itemTypes.txt");

    /* ========== CONSTRUCTORS ========== */
    private ResourcesContainer() {
    }
}