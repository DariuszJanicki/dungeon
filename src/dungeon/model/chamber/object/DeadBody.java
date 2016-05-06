package dungeon.model.chamber.object;

import dungeon.model.character.Character;
import dungeon.model.item.Item;

import java.util.List;

public final class DeadBody extends ChamberObject {

    /* ========== ATTRIBUTES ========== */
    private final Character deadCharacter;

    /* ========== CONSTRUCTORS ========== */
    public DeadBody(Character deadCharacter) {
        this.deadCharacter = deadCharacter;
    }

    /* ========== SERVICES ========== */
    @Override
    public String search() {
        return deadCharacter.getName() + "'s" + deadCharacter.getEquipment().search();
    }

    @Override
    public List<Item> pillage() {
        return deadCharacter.getEquipment().pillage();
    }

    @Override
    public String toString() {
        return "dead body of " + deadCharacter;
    }
}