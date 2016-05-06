package dungeon.model.character.types;

import dungeon.engine.message.Message;
import dungeon.exception.DungeonException;
import dungeon.model.chamber.Chamber;
import dungeon.model.character.Character;
import dungeon.model.enums.Direction;
import dungeon.model.enums.Gender;
import dungeon.model.enums.Race;
import dungeon.utils.Dice;

public final class CPUCharacter extends Character {

    /* ========== CONSTRUCTORS ========== */
    public CPUCharacter(String name, Gender gender, Race race, Chamber chamber) {
        super(name, gender, race, chamber);
    }

    /* ========== SERVICES ========== */
    public void randomizeMovement() {
        if (isAlive()) {
            try {
                randomAction();
            } catch (DungeonException e) {
                message(Message.error(e.getMessage()));
            }
        }
    }

    /* ========== PRIVATE ========== */
    private void randomAction() {
        int random = Dice.k(getPossibleObjects());

        switch(Dice.k4()) {
            case 0:
                pillage(random);
                break;
            case 1:
                moveTo(Direction.random(getPossibleDirections()));
                break;
            case 2:
                lockpick(random);
                break;
            case 3:
                bash(random);
                break;
        }
    }
}