package dungeon.model.chamber.object;

import dungeon.exception.DungeonException;
import dungeon.model.chamber.passage.Lock;
import dungeon.model.item.Item;

import java.util.List;
import java.util.Optional;

public abstract class ChamberObject {

    /* ========== ATTRIBUTES ========== */
    protected Optional<Lock> lock = Optional.empty();

    /* ========== SERVICES ========== */
    public abstract String search();

    public abstract List<Item> pillage();

    public Lock getLock() {
        return lock.orElseThrow(() -> new DungeonException("There is no lock here"));
    }
}