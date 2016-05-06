package dungeon.engine.message;

public final class ActionMessage extends Message {

    /* ========== CONSTRUCTORS ========== */
    public ActionMessage(String performer, String message) {
        super(performer, message);
    }

    /* ========== SERVICES ========== */
    @Override
    public String getMessage() {
        return "* " + performer + " " + message + " *";
    }
}