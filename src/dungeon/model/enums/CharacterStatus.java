package dungeon.model.enums;

public enum CharacterStatus {

    ALIVE, DEAD;

    /* ========== SERVICES ========== */
    public boolean isAlive() {
        return this == ALIVE;
    }

    public boolean isDead() {
        return this == DEAD;
    }
}