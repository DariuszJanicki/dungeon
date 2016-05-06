package dungeon.model.character.attributes;

public class CharacterAttribute {

    /* ========== ATTRIBUTES ========== */
    private final CharacterAttributeType type;
    private int value;

    /* ========== CONSTRUCTOR ========== */
    public CharacterAttribute(CharacterAttributeType type, int value) {
        this.value = value;
        this.type = type;
    }

    /* ========== SERVICES ========== */
    public CharacterAttributeType getType() {
        return type;
    }

    public void updateValue(int value) {
        this.value += value;
    }

    public int getValue() {
        return value;
    }

    @Override
    public String toString() {
        return "[" + type + "] " + value;
    }
}