package dungeon.model.chamber.object;

import dungeon.model.item.Item;
import dungeon.utils.For;
import dungeon.utils.Option;

import java.util.ArrayList;
import java.util.List;

public final class ChamberObjects {

    /* ========== ATTRIBUTES ========== */
    private List<ChamberObject> objects = new ArrayList<>();

    /* ========== SERVICES ========== */
    public void add(ChamberObject object) {
        objects.add(object);
    }

    public String search(int option) {
        return objects.get(validate(option)).search();
    }

    public List<Item> pillage(int option) {
        return objects.get(validate(option)).pillage();
    }

    @Override
    public String toString() {
        return objects.isEmpty() ? "There is nothing here." : "Found" + For.options(objects) + ".";
    }

    /* ========== PRIVATE ========== */
    private int validate(int option) {
        return Option.validate(option, objects);
    }

    public ChamberObject getChamberObject(int option) {
        return objects.get(validate(option));
    }

    public int getPossibleObjects() {
        return objects.size();
    }
}