package dungeon.model.character;

import dungeon.engine.message.Message;
import dungeon.exception.DungeonException;
import dungeon.model.chamber.Chamber;
import dungeon.model.chamber.object.ChamberObject;
import dungeon.model.chamber.passage.Door;
import dungeon.model.chamber.passage.Lock;
import dungeon.model.chamber.passage.Passage;
import dungeon.model.enums.Direction;
import dungeon.model.enums.Gender;
import dungeon.model.enums.Race;

import static dungeon.model.character.attributes.CharacterAttributeType.DEX;
import static dungeon.model.character.attributes.CharacterAttributeType.STR;

public abstract class Character {

    /* ========== ATTRIBUTES ========== */
    private final MessageLog messageLog;
    private final Equipment equipment = new Equipment();
    private final CharacterDescription description;

    private Chamber chamber;

    /* ========== CONSTRUCTORS ========== */
    public Character(String name, Gender gender, Race race, Chamber chamber) {
        this.chamber = chamber;
        description = new CharacterDescription(gender, race, name);
        messageLog = new MessageLog(name);
        chamber.characterEnters(this, Direction.DOWN);
    }

    /* ========== LIST OBJECTS ========== */
    public void message(Message message) {
        messageLog.add(message);
    }

    public void readLogs() {
        messageLog.readLogs();
    }

    public String getName() {
        return description.getName();
    }

    public boolean isAlive() {
        return description.isAlive();
    }

    public Equipment getEquipment() {
        return equipment;
    }

    /* ========== SERVICES ========== */
    public void moveTo(Direction direction) {
        checkIfIsAlive();
        moveTo(direction, getPassage(direction));
    }

    public void pillage(int option) {
        checkIfIsAlive();
        messageLog.action("pillaged " + equipment.addLoot(chamber.pillage(option)));
    }

    public void fight(int option) {
        checkIfIsAlive();
        fight(chamber.getCharacter(option, this));
    }

    public void lock(Direction direction) {
        checkIfIsAlive();
        lock(checkIfIsDoors(getPassage(direction)));
    }

    public void lock(int option) {
        checkIfIsAlive();
        lock(getChamberObject(option));
    }

    public void bash(Direction direction) {
        checkIfIsAlive();
        bash(checkIfIsDoors(getPassage(direction)));
    }

    public void bash(int option) {
        checkIfIsAlive();
        bash(getChamberObject(option));
    }

    public void lockpick(Direction direction) {
        checkIfIsAlive();
        lockpick(checkIfIsDoors(getPassage(direction)));
    }

    public void lockpick(int option) {
        checkIfIsAlive();
        lockpick(getChamberObject(option));
    }

    public void search(Integer option) {
        checkIfIsAlive();
        messageLog.description(chamber.search(option));
    }

    public void say(String message) {
        checkIfIsAlive();
        chamber.sayToOtherCharacters(Message.voice(getName(), message));
    }

    public void lookup() {
        checkIfIsAlive();
        messageLog.description(chamber.lookup(this));
    }

    public void craft(String result) {
        checkIfIsAlive();
        messageLog.action(equipment.craft(result));
    }

    public void inventory() {
        checkIfIsAlive();
        messageLog.description(equipment.search());
    }

    public void stats() {
        checkIfIsAlive();
        messageLog.description(description.stats());
    }

    public void equip(int option) {
        checkIfIsAlive();
        messageLog.action(equipment.equip(option));
    }

    @Override
    public String toString() {
        return description.toString();
    }

    protected Direction[] getPossibleDirections() {
        return chamber.getPossibleDirections();
    }

    protected int getPossibleObjects() {
        return chamber.getPossibleObjects();
    }

    /* ========== PRIVATE ========== */
    private void moveTo(Direction direction, Passage passage) {
        passage.checkIfCanGetThrough();
        chamber.characterLeaves(this, direction);
        chamber = passage.getOtherThan(chamber);
        chamber.characterEnters(this, direction.opposite());
    }

    private void fight(Character opponent) {
        chamber.makeNoise("the sound of fight taking place in neighbouring chamber.", 2);
        if (description.test(DEX, 10 - opponent.description.getValueOfType(DEX))) {
            tryToDealDamage(this, opponent);
            tryToDealDamage(opponent, this);
        } else {
            tryToDealDamage(opponent, this);
            tryToDealDamage(this, opponent);
        }
    }

    private static void tryToDealDamage(Character dealer, Character receiver) {
        dealer.checkIfIsAlive();
        receiver.checkIfIsAlive();

        boolean isHit = dealer.description.test(DEX, -receiver.description.getValueOfType(DEX));

        if (isHit) {
            dealer.messageLog.action(" hits " + receiver.getName() + "!");
            receiver.messageLog.action(" is hit by " + dealer.getName() + "!");
            receiver.description.modifyHealth(-dealer.description.getValueOfType(STR)/10);
            checkIfReceiverIsDead(dealer, receiver);
        } else {
            receiver.messageLog.action("avoids an attack from " + dealer.getName() + "!");
            dealer.messageLog.action("misses " + receiver.getName() + "!");
        }
    }

    private static void checkIfReceiverIsDead(Character dealer, Character receiver) {
        if (receiver.description.isDead()) {
            dealer.chamber.characterIsDead(receiver);
            receiver.messageLog.action("is killed by " + dealer.getName() + "!");
            dealer.messageLog.action("kills " + receiver.getName() + "!");
        }
    }

    private void bash(ChamberObject chamberObject) {
        Lock lock = checkIfHasLock(chamberObject);
        boolean test = description.test(STR, 0);
        chamber.makeNoise("sound of " + chamberObject.toString() + " being bashed.", 2);
        messageLog.action(test ? lock.breakLock() : "failed to break lock");
    }

    private void lockpick(ChamberObject chamberObject) {
        Lock lock = checkIfHasLock(chamberObject);
        boolean test = description.test(DEX, -10);
        chamber.makeNoise("sound of " + chamberObject.toString() + " being lockpicked.", 1);
        messageLog.action(test ? lock.unlock() : "failed to unlock.");
    }

    private void lock(ChamberObject chamberObject) {
        checkIfHasLock(chamberObject).lock();
        chamber.makeNoise("hears the sound of locked " + chamberObject.toString(), 1);
        messageLog.action("locks the " + chamberObject.toString());
    }

    private Door checkIfIsDoors(Passage passage) {
        return passage.getDoor().orElseThrow(() -> new DungeonException("There are no doors here."));
    }

    private Lock checkIfHasLock(ChamberObject chamberObject) {
        return chamberObject.getLock();
    }

    private void checkIfIsAlive() {
        if (description.isDead()) {
            throw new DungeonException("Cannot perform action - " + getName() + " is dead!");
        }
    }

    private Passage getPassage(Direction direction) {
        return chamber.getPassage(direction);
    }

    private ChamberObject getChamberObject(int option) {
        return chamber.getChamberObject(option);
    }
}