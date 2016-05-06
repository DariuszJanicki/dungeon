package dungeon.model.chamber.passage;

import dungeon.exception.DungeonException;

public final class Lock {

    /* ========== ATTRIBUTES ========== */
    private boolean broken;
    private boolean locked;

    /* ========== CONSTRUCTOR ========== */
    public Lock(boolean initialLockState) {
        this.locked = initialLockState;
    }

    /* ========== SERVICES ========== */
    public String lock() {
        checkIsUnlocked();
        checkIsNotBroken();
        locked = true;
        return "successfully locked the lock";
    }

    public String unlock() {
        checkIsLocked();
        locked = false;
        return "successfully unlocked the lock";
    }

    public String breakLock() {
        checkIsLocked();
        checkIsNotBroken();
        broken = true;
        locked = false;
        return "successfully broken the lock";
    }

    private void checkIsNotBroken() {
        if (broken) {
            throw new DungeonException("Lock is already broken.");
        }
    }

    private void checkIsLocked() {
        if (!locked) {
            throw new DungeonException("Lock is already unlocked.");
        }
    }

    public void checkIsUnlocked() {
        if (locked) {
            throw new DungeonException("Lock is locked.");
        }
    }

    public boolean isLocked() {
        return locked;
    }
}