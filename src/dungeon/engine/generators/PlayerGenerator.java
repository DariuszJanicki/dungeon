package dungeon.engine.generators;

import dungeon.model.chamber.Chamber;
import dungeon.model.character.types.PlayableCharacter;
import dungeon.model.enums.Gender;
import dungeon.model.enums.Race;

import java.util.Scanner;

import static dungeon.model.enums.Gender.FEMALE;
import static dungeon.model.enums.Gender.MALE;
import static dungeon.model.enums.Race.DWARF;
import static dungeon.model.enums.Race.ELF;
import static dungeon.model.enums.Race.HUMAN;

public final class PlayerGenerator {

    /* ========== ATTRIBUTES ========== */
    private static Scanner scanner = new Scanner(System.in);

    /* ========== CONSTRUCTORS ========== */
    private PlayerGenerator() {
    }

    /* ========== SERVICES ========== */
    public static PlayableCharacter generate(Chamber chamber) {
        return pickGender(chamber);
    }

    public static PlayableCharacter createVaness(Chamber chamber) {
        return new PlayableCharacter("Vaness", FEMALE, ELF, chamber);
    }

    public static PlayableCharacter createNetial(Chamber chamber) {
        return new PlayableCharacter("Netial", MALE, HUMAN, chamber);
    }

    /* ========== PRIVATE ========== */
    private static PlayableCharacter pickGender(Chamber entrance) {
        while (true) {
            System.out.println("What is your gender? ([male], [female])");
            switch (scanner.next()) {
                case "male":
                    return pickRace(entrance, MALE);
                case "female":
                    return pickRace(entrance, FEMALE);
                default:
                    System.err.println("Unrecognized argument. Try again.");
            }
        }
    }

    private static PlayableCharacter pickRace(Chamber entrance, Gender gender) {
        while (true) {
            System.out.println("What is your race? ([human], [elf], [dwarf])");
            switch (scanner.next()) {
                case "human":
                    return pickName(entrance, gender, HUMAN);
                case "elf":
                    return pickName(entrance, gender, ELF);
                case "dwarf":
                    return pickName(entrance, gender, DWARF);
                default:
                    System.err.println("Unrecognized argument. Try again.");
            }
        }
    }

    private static PlayableCharacter pickName(Chamber entrance, Gender gender, Race race) {
        System.out.println("What is your name? (feel free to pick any)");
        String name = scanner.next();

        return new PlayableCharacter(name, gender, race, entrance);
    }
}