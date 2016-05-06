package dungeon.model.character.types;

import dungeon.engine.control.context.KeyContext;
import dungeon.engine.message.Message;
import dungeon.exception.DungeonException;
import dungeon.model.chamber.Chamber;
import dungeon.model.character.Character;
import dungeon.model.enums.Direction;
import dungeon.model.enums.Gender;
import dungeon.model.enums.Race;

import java.util.List;
import java.util.function.Consumer;

public final class PlayableCharacter extends Character {

    /* ========== ATTRIBUTES ========== */
    private final KeyContext mainContext = new KeyContext();

    /* ========== SERVICES ========== */
    public PlayableCharacter(String name, Gender gender, Race race, Chamber chamber) {
        super(name, gender, race, chamber);

        defineKeyContext();
    }

    /* ========== SERVICES ========== */
    public void action() {
        try {
            mainContext.action();
        } catch (DungeonException e) {
            message(Message.error(e.getMessage()));
        }
    }

    /* ========== PRIVATE ========== */
    private void defineKeyContext() {

        mainContext.register("lockpick", (Consumer<List<String>>) this::lockpickLock, 1);
        mainContext.register("pillage", (Consumer<List<String>>) this::pillage, 1);
        mainContext.register("lock", (Consumer<List<String>>) this::lock, 1);
        mainContext.register("bash", (Consumer<List<String>>) this::bashLock, 1);
        mainContext.register("search", (Consumer<List<String>>) this::search, 1);
        mainContext.register("craft", (Consumer<List<String>>) this::craft, 1);
        mainContext.register("fight", (Consumer<List<String>>) this::fight, 1);
        mainContext.register("equip", (Consumer<List<String>>) this::equip, 1);

        mainContext.register("say", (Consumer<List<String>>) this::say);

        mainContext.register("equipment", this::inventory);
        mainContext.register("lookup", this::lookup);
        mainContext.register("stats", this::stats);

        Consumer<List<String>> moveTo = this::moveTo;
        mainContext.register("N", moveTo, "N");
        mainContext.register("S", moveTo, "S");
        mainContext.register("E", moveTo, "E");
        mainContext.register("W", moveTo, "W");
        mainContext.register("D", moveTo, "D");
        mainContext.register("U", moveTo, "U");
    }

    private void search(List<String> arguments) {
        search(checkAndReturnInteger(arguments));
    }

    private void pillage(List<String> arguments) {
        pillage(checkAndReturnInteger(arguments));
    }

    private void say(List<String> arguments) {
        StringBuilder builder = new StringBuilder();
        arguments.stream().forEach(s -> builder.append(s).append(" "));
        say(builder.toString());
    }

    private void fight(List<String> arguments) {
        fight(checkAndReturnInteger(arguments));
    }

    private void craft(List<String> arguments) {
        craft(checkArgument(arguments, 1).get(0));
    }

    private void equip(List<String> arguments) {
        equip(checkAndReturnInteger(arguments));
    }

    private void moveTo(List<String> arguments) {
        moveTo(checkAndReturnDirection(arguments));
    }

    private void lock(List<String> arguments) {
        try {
            lock(checkAndReturnDirection(arguments));
        } catch (DungeonException e) {
            lock(checkAndReturnInteger(arguments));
        }
    }

    private void bashLock(List<String> arguments) {
        Direction direction;

        try {
            direction = checkAndReturnDirection(arguments);
        } catch (DungeonException e) {
            bash(checkAndReturnInteger(arguments));
            return;
        }

        bash(direction);
    }

    private void lockpickLock(List<String> arguments) {
        Direction direction;

        try {
             direction = checkAndReturnDirection(arguments);
        } catch (DungeonException e) {
            lockpick(checkAndReturnInteger(arguments));
            return;
        }

        lockpick(direction);
    }

    private Direction checkAndReturnDirection(List<String> arguments) {
        return Direction.fromCode(checkArgument(arguments, 1).get(0));
    }

    private Integer checkAndReturnInteger(List<String> arguments) {
        return Integer.parseInt(checkArgument(arguments, 1).get(0));
    }

    private List<String> checkArgument(List<String> arguments, int number) {
        if (arguments.size() != number) {
            throw new DungeonException("Wrong number of arguments");
        }
        return arguments;
    }
}