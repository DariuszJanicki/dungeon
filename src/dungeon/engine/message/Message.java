package dungeon.engine.message;

public abstract class Message {

    /* ========== ATTRIBUTES ========== */
    protected String performer;
    protected String message;
    private boolean isError;

    /* ========== CONSTRUCTORS ========== */
    public Message(String performer, String message) {
        this.performer = performer;
        this.message = message;
    }

    public Message(String message, boolean isError) {
        this(null, message);
        this.isError = isError;
    }

    /* ========== SERVICES ========== */
    public abstract String getMessage();

    @Override
    public String toString() {
        return getMessage();
    }

    public boolean isError() {
        return isError;
    }

    public static Message error(String message) {
        return new ErrorMessage(message);
    }

    public static Message action(String name, String message) {
        return new ActionMessage(name, message);
    }

    public static Message voice(String name, String message) {
        return new VoiceMessage(name, message);
    }

    public static Message sound(String message) {
        return new SoundMessage(message);
    }

    public static Message description(String message) {
        return new DescriptionMessage(message);
    }
}