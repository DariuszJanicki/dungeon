package dungeon.engine.message;

public final class SoundMessage extends Message {

    /* ========== CONSTRUCTORS ========== */
    public SoundMessage(String message) {
        super(message, true);
    }

    /* ========== SERVICES ========== */
    @Override
    public String getMessage() {
        return "(( " + message + " ))";
    }
}
