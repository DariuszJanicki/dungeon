package dungeon.utils;

import dungeon.engine.control.command.EmptyMethod;

import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

public final class For {

    /* ========== CONSTRUCTORS ========== */
    private For() {
    }

    /* ========== SERVICES ========== */
    public static void each(int times, Consumer function, Object argument) {
        for (int i = 0; i < times; ++i) {
            function.accept(argument);
        }
    }

    public static void each(int times, EmptyMethod function) {
        for (int i = 0; i < times; ++i) {
            function.action();
        }
    }

    public static String options(List list) {
        StringBuilder builder = new StringBuilder();

        for (int i = 0; i < list.size(); ++i) {
            builder.append(" [").append(i + 1).append("] ").append(list.get(i));
        }

        return builder.toString();
    }

    public static String options(Map map) {
        StringBuilder builder = new StringBuilder();

        for (Object key : map.keySet()) {
            builder.append(" [").append(key).append("] ").append(map.get(key));
        }

        return builder.toString();
    }
}