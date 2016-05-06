package dungeon.model.dungeon;

import dungeon.model.chamber.Chamber;
import dungeon.utils.Dice;

public final class Dungeon {

    /* ========== ATTRIBUTES ========== */
    private Chamber[][][] map;

    /* ========== CONSTRUCTOR ========== */
    public Dungeon(Chamber[][][] map) {
        this.map = map;
    }

    /* ========== SERVICES ========== */
    public Chamber getRandomChamber() {
        int length = Dice.k(map.length);
        int width = Dice.k(map[length].length);
        int depth = Dice.k(map[length][width].length);
        return map[length][width][depth];
    }
}