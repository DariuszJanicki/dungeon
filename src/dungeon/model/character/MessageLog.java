package dungeon.model.character;

import dungeon.engine.message.Message;

import java.util.ArrayList;
import java.util.List;

final class MessageLog {

    /* ========== ATTRIBUTES ========== */
    private List<Message> readLogs = new ArrayList<>();
    private List<Message> unreadLogs = new ArrayList<>();
    private final String name;

    /* ========== CONSTRUCTOR ========== */
    public MessageLog(String name) {
        this.name = name;
    }

    /* ========== SERVICES ========== */
    public void add(Message message) {
        unreadLogs.add(message);
    }

    public void action(String message) {
        add(Message.action(name, message));
    }

    public void description(String message) {
        add(Message.description(message));
    }

    public void readLogs() {
        unreadLogs.forEach(m -> {
            if (m.isError()) System.err.println(m);
            else System.out.println(m);
        });
        readLogs.addAll(unreadLogs);
        unreadLogs.clear();
    }
}