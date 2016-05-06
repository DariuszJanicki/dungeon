package dungeon.model.chamber;

import dungeon.engine.message.Message;
import dungeon.model.character.Character;
import dungeon.model.enums.Direction;
import dungeon.utils.For;
import dungeon.utils.Option;

import java.util.ArrayList;
import java.util.List;

final class Characters {

    /* ========== ATTRIBUTES ========== */
    private final List<Character> characters = new ArrayList<>();

    /* ========== SERVICES ========== */
    public void characterLeaves(Character character, Direction direction) {
        characters.remove(character);
        message(Message.action(character.getName(), "leaves chamber in the " + direction + " direction."));
    }

    public void characterEnters(Character character, Direction direction) {
        characters.add(character);
        message(Message.action(character.getName(), "enters chamber from the " + direction + " direction."));
        character.lookup();
    }

    public String lookup(Character character) {
        List<Character> others = getOtherCharacters(character);
        return others.isEmpty() ? "There is nobody here." : "In chamber are: " + For.options(others) + ".";
    }

    public Character getCharacter(int option, Character other) {
        List<Character> copiedCharacters = getOtherCharacters(other);
        return copiedCharacters.get(Option.validate(option, copiedCharacters));
    }

    public void characterIsDead(Character dead) {
        characters.remove(dead);
    }

    public void message(Message message) {
        characters.forEach(c -> c.message(message));
    }

    /* ========== PRIVATE ========== */
    private List<Character> getOtherCharacters(Character killer) {
        List<Character> copy = new ArrayList<>(characters);
        copy.remove(killer);
        return copy;
    }
}