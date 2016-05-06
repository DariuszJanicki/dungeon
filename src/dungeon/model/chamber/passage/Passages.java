package dungeon.model.chamber.passage;

import dungeon.exception.DungeonException;
import dungeon.model.chamber.Chamber;
import dungeon.model.enums.Direction;
import dungeon.utils.For;

import java.util.HashMap;
import java.util.Map;

public final class Passages {

    /* ========== ATTRIBUTES ========== */
    private Map<Direction, Passage> passages = new HashMap<>();

    /* ========== SERVICES ========== */
    public void addPassage(Passage passage, Direction direction) {
        checkIfChamberIsPresent(direction);
        passages.put(direction, passage);
    }

    public Passage getPassage(Direction direction) {
        Passage passage = passages.get(direction);

        if (passage == null) {
            throw new DungeonException("there is no passage to the " + direction + ".");
        }

        return passage;
    }

    @Override
    public String toString() {
        return passages.isEmpty() ? "There is nowhere to go." : "There are passages to the" + For.options(passages) + ".";
    }

    public void hear(Chamber source, int i) {
        // character may be quiet
        if (i == 2) {
            passages.values().forEach(p -> p.hear(source, "noise in neighbouring chamber.", 1));
        } else if (i == 1) {
            passages.values().forEach(p -> p.hear(source, "noise in distant chamber.", 0));
        }
    }

    /* ========== PRIVATE ========== */
    private void checkIfChamberIsPresent(Direction direction) {
        if (passages.get(direction) != null) {
            throw new DungeonException(direction + " chamber is already present!");
        }
    }

    public Direction[] getPossibleDirections() {
        return passages.keySet().toArray(new Direction[passages.keySet().size()]);
    }
}