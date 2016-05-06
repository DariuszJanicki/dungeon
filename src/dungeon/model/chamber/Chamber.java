package dungeon.model.chamber;

import dungeon.engine.message.Message;
import dungeon.model.Attribute;
import dungeon.model.chamber.object.ChamberObject;
import dungeon.model.chamber.object.ChamberObjects;
import dungeon.model.chamber.object.DeadBody;
import dungeon.model.chamber.object.PieceOfFurniture;
import dungeon.model.chamber.passage.Passage;
import dungeon.model.chamber.passage.Passages;
import dungeon.model.character.Character;
import dungeon.model.enums.Direction;
import dungeon.model.item.Item;
import dungeon.model.types.parser.AttributeTypeParser;

import java.util.List;
import java.util.Optional;

public final class Chamber {

    /* ========== ATTRIBUTES ========== */
    private final ChamberObjects chamberObjects = new ChamberObjects();
    private final Characters characters = new Characters();
    private final Passages passages = new Passages();

    private final ChamberDescription description;

    /* ========== CONSTRUCTOR  ========== */
    public Chamber(ChamberDescription description) {
        this.description = description;
    }

    /* ========== LIST OBJECTS ========== */
    public void characterEnters(Character character, Direction direction) {
        passages.hear(this, 2);
        characters.characterEnters(character, direction);
    }

    public void characterLeaves(Character character, Direction direction) {
        characters.characterLeaves(character, direction);
    }

    public void sayToOtherCharacters(Message message) {
        characters.message(message);
    }

    public void addPassage(Passage passage, Direction direction) {
        passages.addPassage(passage, direction);
    }

    public Passage getPassage(Direction direction) {
        return passages.getPassage(direction);
    }

    public ChamberObject getChamberObject(int option) {
        return chamberObjects.getChamberObject(option);
    }

    public void addFurniture(PieceOfFurniture furniture) {
        this.chamberObjects.add(furniture);
    }

    public String search(int option) {
        return chamberObjects.search(option);
    }

    public List<Item> pillage(int option) {
        return chamberObjects.pillage(option);
    }

    public void distributeNoise(String action) {
        characters.message(Message.sound(action));
    }

    /* ========== SERVICES ========== */
    public Character getCharacter(int option, Character other) {
        return characters.getCharacter(option, other);
    }

    public Character characterIsDead(Character dead) {
        characters.characterIsDead(dead);

        Optional<Attribute> attribute = AttributeTypeParser.singleton().find(AttributeTypeParser.singleton().chamberAttributes, "DEAD_CORPSE");
        if (attribute.isPresent()) {
            description.addAttribute(attribute.get());
        }

        chamberObjects.add(new DeadBody(dead));

        return dead;
    }

    public String lookup(Character character) {
        StringBuilder builder = new StringBuilder();

        builder.append(characters.lookup(character));
        builder.append("\n");
        builder.append(toString());
        builder.append("\n");
        builder.append(chamberObjects);
        builder.append("\n");
        builder.append(passages);

        return builder.toString();
    }

    @Override
    public String toString() {
        return description.toString();
    }

    public void makeNoise(String s, int i) {
        passages.hear(this, i);
    }

    public Direction[] getPossibleDirections() {
        return passages.getPossibleDirections();
    }

    public int getPossibleObjects() {
        return chamberObjects.getPossibleObjects();
    }
}