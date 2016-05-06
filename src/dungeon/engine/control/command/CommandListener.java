package dungeon.engine.control.command;

import dungeon.utils.For;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.function.Consumer;

public final class CommandListener implements Command {

    /* ========== ATTRIBUTES ========== */
    private final Consumer consumer;
    private int numberOfArguments = 0;

    /* ========== CONSTRUCTORS ========== */
    public CommandListener(Consumer consumer, int numberOfArguments) {
        this.consumer = consumer;
        this.numberOfArguments = numberOfArguments;
    }

    /* ========== SERVICES ========== */
    @Override
    public void execute(Scanner scanner) {
        List<String> arguments = new ArrayList<>();
        For.each(numberOfArguments, () -> arguments.add(scanner.next()));

        consumer.accept(arguments);
    }
}