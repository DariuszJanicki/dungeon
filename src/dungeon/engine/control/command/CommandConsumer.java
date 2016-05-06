package dungeon.engine.control.command;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.function.Consumer;

public final class CommandConsumer implements Command {

    /* ========== ATTRIBUTES ========== */
    private Consumer consumer;
    private List<String> arguments = new ArrayList<>();

    /* ========== CONSTRUCTORS ========== */
    public CommandConsumer(Consumer consumer, List<String> arguments) {
        this.consumer = consumer;
        this.arguments = arguments;
    }

    /* ========== SERVICES ========== */
    @Override
    public void execute(Scanner scanner) {
        consumer.accept(arguments);
    }
}