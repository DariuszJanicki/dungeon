package dungeon.model.chamber.passage;

import dungeon.model.chamber.Chamber;

import java.util.Optional;

public class Passage {

    /* ========== ATTRIBUTES ========== */
    private Optional<Door> door = Optional.empty();
    private final Chamber from;
    private final Chamber to;

    /* ========== CONSTRUCTOR ========== */
    public Passage(Chamber from, Chamber to) {
        this.from = from;
        this.to = to;
    }

    public Passage(Chamber from, Chamber to, Door door) {
        this(from, to);
        to.addFurniture(door);
        from.addFurniture(door);
        this.door = Optional.of(door);
    }

    /* ========== SERVICES ========== */
    public Optional<Door> getDoor() {
        return door;
    }

    public Chamber getOtherThan(Chamber chamber) {
        return from.equals(chamber) ? to : from;
    }

    public void checkIfCanGetThrough() {
        if (door.isPresent()) {
            door.get().checkIfCanGetThrough();
        }
    }

    @Override
    public String toString() {
        return door.isPresent() ? door.get().toString() : "passage";
    }

    public void hear(Chamber source, String action, int i) {
        if (source.equals(from)) {
            to.makeNoise(action, i);
            to.distributeNoise(action);
        } else if (source.equals(to)) {
            from.makeNoise(action, i);
            from.distributeNoise(action);
        }
    }
}