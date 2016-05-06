package dungeon.engine.control.context;

import dungeon.engine.control.command.EmptyMethod;
import dungeon.engine.control.command.Command;
import dungeon.engine.control.command.CommandConsumer;
import dungeon.engine.control.command.CommandEmpty;
import dungeon.engine.control.command.CommandListener;
import dungeon.engine.control.command.CommandNoArgs;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.function.Consumer;
import java.util.regex.Pattern;

public final class KeyContext {

    /* ========== ATTRIBUTES ========== */
    protected Scanner scanner = new Scanner(System.in);
    private Map<String, Command> keys = new HashMap<>();

    /* ========== CONSTRUCTORS ========== */
    public KeyContext() {
        Pattern delimiters = Pattern.compile(System.getProperty("line.separator")+"|\\s");
        scanner.useDelimiter(delimiters);
    }

    /* ========== SERVICES ========== */
    public void action() {
        String word = scanner.next();

        Command command = keys.get(word);

        if (commandExists(command)) {
            command.execute(scanner);
        } else {
            logError(word);
        }
    }

    public void register(String command, Consumer consumer, String... arguments) {
        keys.put(command, new CommandConsumer(consumer, Arrays.asList(arguments)));
    }

    public void register(String command, Consumer consumer, Integer numberOfArguments) {
        keys.put(command, new CommandListener(consumer, numberOfArguments));
    }

    public void register(String command, EmptyMethod consumer) {
        keys.put(command, new CommandEmpty(consumer));
    }

    public void register(String command, Consumer consumer) {
        keys.put(command, new CommandNoArgs(consumer));
    }

    /* ========== PRIVATE ========== */
    private boolean commandExists(Command command) {
        return command != null;
    }

    private void logError(String string) {
        System.err.println("* unknown action '" + string + "' *");
    }
}