package dungeon.model.character;

import dungeon.model.character.attributes.CharacterAttributeType;
import dungeon.model.enums.CharacterStatus;
import dungeon.model.enums.Gender;
import dungeon.model.enums.Race;
import dungeon.utils.Dice;

import static dungeon.model.character.attributes.CharacterAttributeType.CON;
import static dungeon.model.enums.CharacterStatus.ALIVE;
import static dungeon.model.enums.CharacterStatus.DEAD;

public final class CharacterDescription {

    /* ========== ATTRIBUTES ========== */
    private final CharacterAttributes attributes;
    private final Gender gender;
    private final String name;
    private final Race race;
    private final int maxHealth;
    private int health;
    private CharacterStatus status = ALIVE;

    /* ========== CONSTRUCTOR ========== */
    public CharacterDescription(Gender gender, Race race, String name) {
        this.gender = gender;
        this.race = race;
        this.name = name;

        attributes = CharacterAttributes.calculateFrom(gender, race);

        this.health = attributes.getValueOfType(CON);
        this.maxHealth = health;
    }

    /* ========== SERVICES ========== */
    public String stats() {
        return attributes.toString();
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return name + " " + race + " " + gender;
    }

    public boolean test(CharacterAttributeType type, int modifier) {
        int dice = Dice.k100();
        int attribute = attributes.getValueOfType(type);

        System.err.println("\t" + dice + " vs " + attribute + " (" + type + ") + " + modifier);

        return dice <= attribute + modifier;
    }

    public void modifyHealth(int value) {
        health += value;

        if (health > maxHealth) {
            health = maxHealth;
        } else if (health <= 0) {
            health = 0;
            this.status = DEAD;
        }

        System.out.println(health);
    }

    public boolean isAlive() {
        return status.isAlive();
    }

    public boolean isDead() {
        return status.isDead();
    }

    public int getValueOfType(CharacterAttributeType dex) {
        return attributes.getValueOfType(dex);
    }
}
