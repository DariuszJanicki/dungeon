package dungeon.engine.message;

public final class ErrorMessage extends Message {

    /* ========== CONSTRUCTORS ========== */
    public ErrorMessage(String message) {
        super(message, true);
    }

    /* ========== SERVICES ========== */
    @Override
    public String getMessage() {
        return "* " + message + " *";
    }
}