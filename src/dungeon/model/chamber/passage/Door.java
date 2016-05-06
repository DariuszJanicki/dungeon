package dungeon.model.chamber.passage;

import dungeon.model.chamber.object.PieceOfFurniture;
import dungeon.model.item.Item;
import dungeon.model.types.parser.FurnitureParser;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public final class Door extends PieceOfFurniture {

    /* ========== CONSTRUCTOR ========== */
    public Door() {
        super(FurnitureParser.singleton().find("doors"), new ArrayList<>());
        lock = Optional.of(new Lock(true));
    }

    @Override
    public String search() {
        return null;
    }

    @Override
    public List<Item> pillage() {
        return null;
    }

    /* ========== SERVICES ========== */
    public void checkIfCanGetThrough() {
        lock.ifPresent(Lock::checkIsUnlocked);
    }

    @Override
    public String toString() {
        return (lock.isPresent() ? lock.get().isLocked() ? "closed" : "opened" : "") + " doors";
    }
}