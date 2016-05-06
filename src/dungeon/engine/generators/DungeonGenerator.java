package dungeon.engine.generators;

import dungeon.model.chamber.Chamber;
import dungeon.model.chamber.passage.Door;
import dungeon.model.chamber.passage.Passage;
import dungeon.model.chamber.passage.Stairs;
import dungeon.model.dungeon.Dungeon;
import dungeon.utils.Dice;

import static dungeon.model.enums.Direction.DOWN;
import static dungeon.model.enums.Direction.EAST;
import static dungeon.model.enums.Direction.NORTH;
import static dungeon.model.enums.Direction.SOUTH;
import static dungeon.model.enums.Direction.UP;
import static dungeon.model.enums.Direction.WEST;

public final class DungeonGenerator {

    /* ========== CONSTRUCTORS ========== */
    private DungeonGenerator() {
    }

    /* ========== SERVICES ========== */
    public static Dungeon generate(int width, int height, int depth) {
        Chamber[][][] map = new Chamber[width][height][depth];

        createChambers(width, height, depth, map);

        return new Dungeon(map);
    }

    /* ========== PRIVATE ========== */
    private static void createChambers(int width, int height, int depth, Chamber[][][] map) {
        for (int i = 0; i < width; ++i) {
            for (int j = 0; j < height; ++j) {
                for (int k = 0; k < depth; ++k) {
                    map[i][j][k] = ChamberGenerator.generate();

                    switch (Dice.k6()) {
                        case 1:
                            connectToTheUpDownChamber(map, i, j, k);
                        case 2:
                            connectNorthSouthChamber(map, i, j, k);
                            break;
                        case 3:
                            connectNorthSouthChamber(map, i, j, k);
                        case 4:
                            connectWestEastChamber(map, i, j, k);
                        case 5:
                            connectToTheUpDownChamber(map, i, j, k);
                    }
                }
            }
        }
    }

    private static void connectNorthSouthChamber(Chamber[][][] map, int i, int j, int k) {
        if (i > 0) {
            Chamber createdChamber = map[i][j][k];
            Chamber chamberToConnect = map[i - 1][j][k];

            Passage passage = getPassage(createdChamber, chamberToConnect);
            createdChamber.addPassage(passage, NORTH);
            chamberToConnect.addPassage(passage, SOUTH);
        }
    }

    private static void connectWestEastChamber(Chamber[][][] map, int i, int j, int k) {
        if (j > 0) {
            Chamber createdChamber = map[i][j][k];
            Chamber chamberToConnect = map[i][j - 1][k];

            Passage passage = getPassage(createdChamber, chamberToConnect);
            createdChamber.addPassage(passage, WEST);
            chamberToConnect.addPassage(passage, EAST);
        }
    }

    private static void connectToTheUpDownChamber(Chamber[][][] map, int i, int j, int k) {
        if (k > 0) {
            Chamber createdChamber = map[i][j][k];
            Chamber chamberToConnect = map[i][j][k - 1];

            Passage passage = getStairs(createdChamber, chamberToConnect);
            createdChamber.addPassage(passage, DOWN);
            chamberToConnect.addPassage(passage, UP);
        }
    }

    private static Passage getPassage(Chamber createdChamber, Chamber chamberToConnect) {
        return Dice.test(3) ?
                new Passage(createdChamber, chamberToConnect, new Door()) :
                new Passage(createdChamber, chamberToConnect);
    }

    private static Passage getStairs(Chamber createdChamber, Chamber chamberToConnect) {
        return new Stairs(createdChamber, chamberToConnect);
    }
}