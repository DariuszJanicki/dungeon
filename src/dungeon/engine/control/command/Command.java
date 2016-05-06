package dungeon.engine.control.command;

import java.util.Scanner;

@FunctionalInterface
public interface Command {
    void execute(Scanner scanner);
}