package dungeon.model.chamber.object;

import dungeon.exception.DungeonException;
import dungeon.model.Attribute;
import dungeon.model.chamber.passage.Lock;
import dungeon.model.item.Item;
import dungeon.model.types.FurnitureType;
import dungeon.model.types.parser.AttributeTypeParser;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class PieceOfFurniture extends ChamberObject {

    /* ========== ATTRIBUTES ========== */
    private List<Attribute> attributes = new ArrayList<>();
    private List<Item> items = new ArrayList<>();
    private FurnitureType type;

    /* ========== CONSTRUCTORS ========== */
    public PieceOfFurniture(FurnitureType type, List<Attribute> attributes) {
        this.type = type;
        this.attributes.addAll(attributes);

        Optional<Attribute> locked = AttributeTypeParser.singleton().find(attributes, "LOCKED");
        if (locked.isPresent()) {
            lock = Optional.of(new Lock(true));
        }
    }

    /* ========== SERVICES ========== */
    public String search() {
        checkIfLocked();
        return this + (items.isEmpty() ? " is empty" : " contains " + items);
    }

    public List<Item> pillage() {
        checkIfLocked();
        List<Item> itemsArray = new ArrayList<>(items);
        items.clear();
        return itemsArray;
    }

    public void addItem(Item item) {
        items.add(item);
    }

    public boolean canAddItem() {
        return type.getCapacity() > items.size();
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        attributes.forEach(i -> builder.append(i.getDescription()).append(" "));
        return builder.append(type.getName()).toString();
    }

    /* ========== PRIVATE ========== */
    private void checkIfLocked() {
        if (lock.isPresent() && lock.get().isLocked()) {
            throw new DungeonException("Lock is locked!");
        }
    }
}