package dungeon.engine.message;

public final class DescriptionMessage extends Message {

    /* ========== CONSTRUCTORS ========== */
    public DescriptionMessage(String message) {
        super(null, message);
    }

    /* ========== SERVICES ========== */
    @Override
    public String getMessage() {
        return message;
    }
}
