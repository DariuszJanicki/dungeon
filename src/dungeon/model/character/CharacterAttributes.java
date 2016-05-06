package dungeon.model.character;

import dungeon.model.character.attributes.CharacterAttribute;
import dungeon.model.character.attributes.CharacterAttributeType;
import dungeon.model.enums.Gender;
import dungeon.model.enums.Race;

import java.util.ArrayList;
import java.util.List;

import static dungeon.model.character.attributes.CharacterAttributeType.CON;
import static dungeon.model.character.attributes.CharacterAttributeType.DEX;
import static dungeon.model.character.attributes.CharacterAttributeType.INT;
import static dungeon.model.character.attributes.CharacterAttributeType.MEN;
import static dungeon.model.character.attributes.CharacterAttributeType.STR;
import static dungeon.model.character.attributes.CharacterAttributeType.VIT;

public class CharacterAttributes {

    /* ========== ATTRIBUTES ========== */
    private final List<CharacterAttribute> attributes;

    /* ========== CONSTRUCTOR ========== */
    public CharacterAttributes(List<CharacterAttribute> attributes) {
        this.attributes = attributes;
    }

    /* ========== SERVICES ========== */
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        attributes.forEach(a -> builder.append(a).append("\n"));
        return builder.toString();
    }

    /* ========== STATIC ========== */
    public static CharacterAttributes prepareAttributes(int str, int con, int dex, int inte, int men, int vit) {
        List<CharacterAttribute> list = new ArrayList<>();
        list.add(new CharacterAttribute(STR, str));
        list.add(new CharacterAttribute(CON, con));
        list.add(new CharacterAttribute(DEX, dex));
        list.add(new CharacterAttribute(INT, inte));
        list.add(new CharacterAttribute(MEN, men));
        list.add(new CharacterAttribute(VIT, vit));
        return new CharacterAttributes(list);
    }

    public static CharacterAttributes calculateFrom(Gender gender, Race race) {
        CharacterAttributes attributes = new CharacterAttributes(new ArrayList<>(race.getBaseRaceAttributes().attributes));
        attributes.attributes
                .forEach(a -> a.updateValue(find(gender.getGenderAttributeModificators().attributes, a.getType())));
        return attributes;
    }

    private static int find(List<CharacterAttribute> baseRaceAttributes, CharacterAttributeType type) {
        return baseRaceAttributes.stream()
                .filter(a -> a.getType() == type)
                .findFirst().get().getValue();
    }

    public int getValueOfType(CharacterAttributeType type) {
        return attributes.stream().filter(a -> a.getType() == type).findAny().get().getValue();
    }
}