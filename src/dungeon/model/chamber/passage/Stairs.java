package dungeon.model.chamber.passage;

import dungeon.model.chamber.Chamber;

public final class Stairs extends Passage {

    /* ========== ATTRIBUTES ========== */

    /* ========== CONSTRUCTOR ========== */
    public Stairs(Chamber from, Chamber to) {
        super(from, to);
    }

    /* ========== SERVICES ========== */
    @Override
    public String toString() {
        return "stairs";
    }
}