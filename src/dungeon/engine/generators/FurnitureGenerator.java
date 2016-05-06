package dungeon.engine.generators;

import dungeon.model.Attribute;
import dungeon.model.chamber.object.PieceOfFurniture;
import dungeon.model.types.FurnitureType;
import dungeon.model.types.parser.AttributeTypeParser;
import dungeon.model.types.parser.FurnitureParser;
import dungeon.utils.Dice;
import dungeon.utils.For;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public final class FurnitureGenerator {

    /* ========== CONSTRUCTORS ========== */
    private FurnitureGenerator() {
    }

    /* ========== SERVICES ========== */
    public static PieceOfFurniture generate() {
        List<FurnitureType> types = FurnitureParser.singleton().types;
        FurnitureType type = types.get(Dice.k(types.size()));

        List<Attribute> attributes = getAttributes();

        PieceOfFurniture furniture = new PieceOfFurniture(type, attributes);

        For.each(Dice.k2(), FurnitureGenerator::addItem, furniture);

        return furniture;
    }

    private static List<Attribute> getAttributes() {
        List<Attribute> attributeTypes = AttributeTypeParser.singleton().furnitureAttributes;
        Attribute attribute = attributeTypes.get(Dice.k(attributeTypes.size()));

        List<Attribute> attributes = new ArrayList<>();
        attributes.add(attribute);
        if (Dice.test(6)) {
            Optional<Attribute> locked = AttributeTypeParser.singleton().find(attributeTypes, "LOCKED");
            if (locked.isPresent()) {
                attributes.add(locked.get());
            }
        }

        return attributes;
    }

    /* ========== PRIVATE ========== */
    private static void addItem(Object o) {
        PieceOfFurniture furniture = (PieceOfFurniture) o;

        if (furniture.canAddItem()) {
            furniture.addItem(ItemGenerator.generate());
        }
    }
}