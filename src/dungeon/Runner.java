package dungeon;

import dungeon.engine.generators.DungeonGenerator;
import dungeon.engine.generators.PlayerGenerator;
import dungeon.model.character.types.CPUCharacter;
import dungeon.model.character.types.PlayableCharacter;
import dungeon.model.dungeon.Dungeon;
import dungeon.model.enums.Gender;
import dungeon.model.enums.Race;

import java.util.LinkedList;
import java.util.List;

final class Runner implements Runnable {

    /* ========== ATTRIBUTES ========== */
    private List<CPUCharacter> cpuCharacters = new LinkedList<>();

    /* ========== SERVICES ========== */
    @Override
    public void run() {
        Dungeon dungeon = DungeonGenerator.generate(2, 2, 1);

        PlayableCharacter player = PlayerGenerator.createVaness(dungeon.getRandomChamber());
//        cpuCharacters.add(new CPUCharacter("Cat", FEMALE, CAT, dungeon.getRandomChamber()));
//        cpuCharacters.add(new CPUCharacter("George", MALE, DWARF, dungeon.getRandomChamber()));
//        cpuCharacters.add(new CPUCharacter("Alice", FEMALE, HUMAN, dungeon.getRandomChamber()));
//        cpuCharacters.add(new CPUCharacter("Manny", MALE, DOG, dungeon.getRandomChamber()));
//        cpuCharacters.add(new CPUCharacter("Mouse", MALE, MOUSE, dungeon.getRandomChamber()));
        cpuCharacters.add(new CPUCharacter("Sheia", Gender.FEMALE, Race.ELF, dungeon.getRandomChamber()));

        while (true) {
            player.readLogs();
            player.action();
            cpuCharacters.forEach(CPUCharacter::randomizeMovement);
        }
    }
}