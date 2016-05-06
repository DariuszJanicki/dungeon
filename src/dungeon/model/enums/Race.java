package dungeon.model.enums;

import dungeon.model.character.CharacterAttributes;

public enum Race {

    HUMAN("human", 40, 40, 20, 30, 40, 30),
    ELF("elf", 30, 20, 50, 30, 20, 50),
    DWARF("dwarf", 30, 50, 20, 30, 50, 20),

    CAT("cat", 5, 10, 20, 15, 5, 15),
    DOG("dog", 10, 15, 10, 20, 10, 5),

    MOUSE("mouse", 5, 5, 10, 10, 5, 5);

    /* ========== ATTRIBUTES ========== */
    private final String name;
    private CharacterAttributes baseRaceAttributes;

    /* ========== CONSTRUCTOR ========== */
    Race(String name, int str, int con, int dex, int inte, int men, int vit) {
        this.name = name;
        this.baseRaceAttributes = CharacterAttributes.prepareAttributes(str, con, dex, inte, men, vit);
    }

    /* ========== SERVICES ========== */
    public CharacterAttributes getBaseRaceAttributes() {
        return baseRaceAttributes;
    }

    @Override
    public String toString() {
        return name;
    }
}