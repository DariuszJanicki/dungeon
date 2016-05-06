package dungeon.engine.control.command;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.function.Consumer;

public final class CommandNoArgs implements Command {

    /* ========== ATTRIBUTES ========== */
    private Consumer consumer;
    private List<String> arguments = new ArrayList<>();

    /* ========== CONSTRUCTORS ========== */
    public CommandNoArgs(Consumer consumer) {
        this.consumer = consumer;
    }

    /* ========== SERVICES ========== */
    @Override
    public void execute(Scanner scanner) {
        Scanner argScanner = new Scanner(scanner.nextLine());
        while(argScanner.hasNext()) {
            arguments.add(argScanner.next());
        }

        consumer.accept(arguments);
        arguments.clear();
    }
}