package dungeon.model.types.parser;

import dungeon.constants.ResourcesContainer;
import dungeon.model.Attribute;

import java.util.List;
import java.util.Optional;

public final class AttributeTypeParser extends AbstractTypeParser {

    /* ========== STATIC ========== */
    private static final AttributeTypeParser singleton = new AttributeTypeParser();

    public static AttributeTypeParser singleton() {
        return singleton;
    }

    /* ========== ATTRIBUTES ========== */
    public List<Attribute> chamberAttributes = readAttribute(ResourcesContainer.chamberAttributes);
    public List<Attribute> furnitureAttributes = readAttribute(ResourcesContainer.furnitureAttributes);
    public List<Attribute> attributes;

    /* ========== PUBLIC ========== */
    public Optional<Attribute> find(List<Attribute> attributes,String code) {
        return attributes.stream().filter(a -> a.getCode().equals(code)).findFirst();
    }

    /* ========== PRIVATE ========== */
    private AttributeTypeParser() {
    }
}