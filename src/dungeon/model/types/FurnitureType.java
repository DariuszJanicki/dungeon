package dungeon.model.types;

public final class FurnitureType {

    /* ========== ATTRIBUTES ========== */
    private String name;
    private int capacity;

    /* ========== SERVICES ========== */
    public String getName() {
        return name;
    }

    void setName(String name) {
        this.name = name;
    }

    public int getCapacity() {
        return capacity;
    }

    void setCapacity(int capacity) {
        this.capacity = capacity;
    }
}