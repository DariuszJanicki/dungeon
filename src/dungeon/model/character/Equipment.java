package dungeon.model.character;

import dungeon.exception.DungeonException;
import dungeon.model.item.Item;
import dungeon.model.recipe.Recipe;
import dungeon.model.types.ItemSubType;
import dungeon.utils.For;
import dungeon.utils.Option;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public final class Equipment {

    /* ========== ATTRIBUTES ========== */
    private final List<Item> items = new LinkedList<>();
    private final Equipped equipped = new Equipped();

    /* ========== SERVICES ========== */
    public List<Item> addLoot(List<Item> loot) {
        if (loot.isEmpty()) {
            throw new DungeonException("There is nothing to be pillaged.");
        }

        items.addAll(loot);

        return loot;
    }

    public List<Item> pillage() {
        List<Item> itemsArray = new ArrayList<>(items);
        items.clear();
        return itemsArray;
    }

    public String search() {
        return items.isEmpty() ? " equipment is empty" : " equipment contains " + For.options(items);
    }

    public String craft(String result) {
        List<Recipe> availableRecipes = Recipe.canCreate(result, items);
        checkIfCanBeCrafted(result, availableRecipes);

        Recipe recipe = availableRecipes.get(0);
        ItemSubType type = recipe.craft(items);

        items.add(new Item(type));

        return "crafted a new " + type + " from " + recipe.getParts();
    }

    public String equip(int option) {
        return equipped.equip(items.get(Option.validate(option, items)));
    }

    /* ========== PRIVATE ========== */
    private void checkIfCanBeCrafted(String result, List<Recipe> availableRecipes) {
        if (availableRecipes.isEmpty()) {
            throw new DungeonException("Can not create " + result + " from possessed items!");
        }
    }
}