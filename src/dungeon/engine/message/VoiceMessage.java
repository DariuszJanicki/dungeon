package dungeon.engine.message;

public final class VoiceMessage extends Message {

    /* ========== CONSTRUCTORS ========== */
    public VoiceMessage(String performer, String message) {
        super(performer, message);
    }

    /* ========== SERVICES ========== */
    @Override
    public String getMessage() {
        return performer + " says: '" + message + "'";
    }
}