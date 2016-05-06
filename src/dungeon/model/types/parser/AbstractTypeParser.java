package dungeon.model.types.parser;

import com.fasterxml.jackson.core.type.TypeReference;
import dungeon.Main;
import dungeon.model.Attribute;
import dungeon.model.types.FurnitureType;
import dungeon.model.types.ItemSubType;

import java.io.File;
import java.io.IOException;
import java.util.Collections;
import java.util.List;

public abstract class AbstractTypeParser {

    /* ========== PROTECTED ========== */
    protected List<Attribute> readAttribute(File file) {
        try {
            return Main.mapper.readValue(file, new TypeReference<List<Attribute>>() {
            });
        } catch (IOException e) {
            e.printStackTrace();
        }

        return Collections.emptyList();
    }

    protected List<FurnitureType> readFurniture(File file) {
        try {
            return Main.mapper.readValue(file, new TypeReference<List<FurnitureType>>() {
            });
        } catch (IOException e) {
            e.printStackTrace();
        }

        return Collections.emptyList();
    }

    protected List<ItemSubType> readItem(File file) {
        try {
            return Main.mapper.readValue(file, new TypeReference<List<ItemSubType>>() {
            });
        } catch (IOException e) {
            e.printStackTrace();
        }

        return Collections.emptyList();
    }
}