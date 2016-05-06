package dungeon.engine.control.command;

import java.util.Scanner;

public final class CommandEmpty implements Command {

    /* ========== ATTRIBUTES ========== */
    private EmptyMethod method;

    /* ========== CONSTRUCTORS ========== */
    public CommandEmpty(EmptyMethod method) {
        this.method = method;
    }

    /* ========== SERVICES ========== */
    @Override
    public void execute(Scanner scanner) {
        method.action();
    }
}