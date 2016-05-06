package dungeon.model.character.attributes;

public enum CharacterAttributeType {

    STR("strength"), CON("condition"), DEX("dexterity"), INT("intellect"), MEN("mentality"), VIT("vitality");

    /* ========== PRIVATE ========== */
    CharacterAttributeType(String name) {
        this.name = name;
    }

    private String name;

    /* ========== SERVICES ========== */
    @Override
    public String toString() {
        return name;
    }
}
