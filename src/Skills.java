public enum Skills {
    IRONFLESH(0),
    POWERSTRIKE(1),
    POWERTHROW(2),
    POWERDRAW(3),
    WEAPONMASTER(4),
    SHIELD(5),
    ATHLETICS(6),
    RIDING(7),
    HORSEARCHERY(8),
    LOOTING(9),
    TRAINER(10),
    TRACKING(11),
    TACTICS(12),
    PATHFINDING(13),
    SPOTTING(14),
    INVENTORYMANAGEMENT(15),
    WOUNDTREATMENT(16),
    SURGERY(17),
    FIRSTAID(18),
    ENGINEER(19),
    PERSUASION(20),
    PRISONERMANAGEMENT(21),
    LEADERSHIP(22),
    TRADE(23);

    private final int value;

    Skills(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
