package dungeon.model.enums;

import dungeon.exception.DungeonException;

import java.util.Objects;
import java.util.Random;

public enum Direction {

    NORTH("N", "north"),
    EAST("E", "east"),
    SOUTH("S", "south"),
    WEST("W", "west"),
    DOWN("D", "down"),
    UP("U", "up");

    static  {
        NORTH.opposite = SOUTH;
        SOUTH.opposite = NORTH;
        WEST.opposite = EAST;
        EAST.opposite = WEST;
    }

    /* ========== PRIVATE ========== */
    Direction(String code, String name) {
        this.code = code;
        this.name = name;
    }

    private String name;
    private String code;
    private Direction opposite;

    /* ========== SERVICES ========== */
    public static Direction random(Direction[] directions) {
        if (directions.length > 0) {
            return directions[new Random().nextInt(directions.length)];
        } else return null;
    }

    public static Direction fromCode(String code) {
        for (Direction direction : values()) {
            if (Objects.equals(direction.code, code)) {
                return direction;
            }
        }

        throw new DungeonException("No such direction found!");
    }

    @Override
    public String toString() {
        return name;
    }

    public Direction opposite() {
        return opposite;
    }
}