package dungeon.engine.generators;

import dungeon.model.Attribute;
import dungeon.model.chamber.Chamber;
import dungeon.model.chamber.ChamberDescription;
import dungeon.model.types.parser.AttributeTypeParser;
import dungeon.utils.Dice;
import dungeon.utils.For;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public final class ChamberGenerator {

    /* ========== CONSTRUCTORS ========== */
    private ChamberGenerator() {
    }

    /* ========== SERVICES ========== */
    public static Chamber generate() {
        Chamber chamber = new Chamber(createDescription());
        For.each(Dice.k6(), ChamberGenerator::addFurniture, chamber);
        return chamber;
    }

    /* ========== PRIVATE ========== */
    private static ChamberDescription createDescription() {
        List<Attribute> attributes = AttributeTypeParser.singleton().chamberAttributes;
        Attribute attribute = attributes.get(Dice.k(attributes.size()));
        return new ChamberDescription(new ArrayList<>(Arrays.asList(attribute)));
    }

    private static void addFurniture(Object o) {
        ((Chamber) o).addFurniture(FurnitureGenerator.generate());
    }
}