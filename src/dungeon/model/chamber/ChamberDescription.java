package dungeon.model.chamber;

import dungeon.model.Attribute;

import java.util.List;

public final class ChamberDescription {

    /* ========== ATTRIBUTES ========== */
    private final List<Attribute> attributes;
    private String description;

    /* ========== CONSTRUCTOR ========== */
    public ChamberDescription(List<Attribute> attributes) {
        this.attributes = attributes;
        updateDescription();
    }

    public void addAttribute(Attribute attribute) {
        attributes.add(attribute);
        updateDescription();
    }

    @Override
    public String toString() {
        return description;
    }

    /* ========== PRIVATE ========== */
    private void updateDescription() {
        StringBuilder builder = new StringBuilder();
        attributes.stream().forEach(a -> builder.append(a.getDescription()).append(" "));
        description = builder.toString();
    }
}