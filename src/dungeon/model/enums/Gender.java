package dungeon.model.enums;

import dungeon.model.character.CharacterAttributes;

public enum Gender {

    MALE("male", 5, 5, -10, 10, -5, -5),
    FEMALE("female", -5, -5, 10, -10, 5, 5);

    /* ========== ATTRIBUTES ========== */
    private final String name;
    private final CharacterAttributes genderAttributeModificators;

    /* ========== CONSTRUCTOR ========== */
    Gender(String name, int str, int con, int dex, int inte, int men, int vit) {
        this.name = name;
        this.genderAttributeModificators = CharacterAttributes.prepareAttributes(str, con, dex, inte, men, vit);
    }

    /* ========== SERVICES ========== */
    public CharacterAttributes getGenderAttributeModificators() {
        return genderAttributeModificators;
    }

    @Override
    public String toString() {
        return name;
    }
}
