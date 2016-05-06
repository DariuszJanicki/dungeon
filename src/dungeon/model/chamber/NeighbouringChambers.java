package dungeon.model.chamber;

import dungeon.exception.DungeonException;
import dungeon.model.enums.Direction;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

final class NeighbouringChambers {

    /* ========== ATTRIBUTES ========== */
    private Map<Direction, Chamber> chambers = new HashMap<>();

    /* ========== SERVICES ========== */
    public void addChamber(Chamber chamber, Direction direction) {
        checkIfChamberIsPresent(direction);
        chambers.put(direction, chamber);
    }

    private void checkIfChamberIsPresent(Direction direction) {
        if (chambers.get(direction) != null) {
            throw new DungeonException(direction + " chamber is already present!");
        }
    }

    public Optional<Chamber> getChamber(Direction direction) {
        return Optional.ofNullable(chambers.get(direction));
    }

    public String lookup() {
        return "There are doors to the " + chambers.keySet() + " of this chamber";
    }
}