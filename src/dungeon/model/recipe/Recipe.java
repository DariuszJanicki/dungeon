package dungeon.model.recipe;

import com.fasterxml.jackson.core.type.TypeReference;
import dungeon.Main;
import dungeon.constants.ResourcesContainer;
import dungeon.model.item.Item;
import dungeon.model.types.ItemSubType;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Recipe {

    /* ========== STATIC ========== */
    public static List<Recipe> recipes;

    static {
        try {
            recipes = Main.mapper.readValue(ResourcesContainer.recipes, new TypeReference<List<Recipe>>() {
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /* ========== ATTRIBUTES ========== */
    private List<ItemSubType> parts;
    private ItemSubType result;

    /* ========== SERVICES ========== */
    public void setParts(List<ItemSubType> parts) {
        this.parts = parts;
    }

    public List<ItemSubType> getParts() {
        return parts;
    }

    public void setResult(ItemSubType result) {
        this.result = result;
    }

    public ItemSubType getResult() {
        return result;
    }

    @Override
    public String toString() {
        return "Recipe - construct " + result + " using " + parts;
    }

    public static List<Recipe> canCreate(String result, List<Item> items) {
        return getRecipesThatCanBeCreated(getFittingRecipes(result), items);
    }

    /* ========== PRIVATE ========== */
    private static List<Recipe> getFittingRecipes(String result) {
        return recipes.stream()
                .filter(r -> r.getResult().getName().equals(result))
                .collect(Collectors.toList());
    }

    private static List<Recipe> getRecipesThatCanBeCreated(List<Recipe> recipes, List<Item> items) {
        return recipes.stream()
                .filter(r -> check(r.getParts(), items))
                .collect(Collectors.toList());
    }

    private static boolean check(List<ItemSubType> parts, List<Item> items) {
        List<ItemSubType> collected = items.stream().map(Item::getType).collect(Collectors.toList());
        return collected.containsAll(parts);
    }

    public ItemSubType craft(List<Item> items) {
        List<Item> itemsToRemove = new ArrayList<>();
        List<ItemSubType> craftParts = new ArrayList<>(parts);

        items.forEach(i -> {
            if (craftParts.contains(i.getType())) {
                craftParts.remove(i.getType());
                itemsToRemove.add(i);
            }
        });

        items.removeAll(itemsToRemove);

        return result;
    }
}