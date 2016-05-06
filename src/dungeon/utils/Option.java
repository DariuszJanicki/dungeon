package dungeon.utils;

import dungeon.exception.DungeonException;

import java.util.List;

public final class Option {

    /* ========== CONSTRUCTORS ========== */
    private Option() {
    }

    /* ========== SERVICES ========== */
    public static int validate(int option, List list) {
        if (option <= 0 || option > list.size()) {
            throw new DungeonException("There is no such option!");
        }

        return option - 1;
    }
}