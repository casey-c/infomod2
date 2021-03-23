package InfoMod2.data;

public enum ActiveDuringAct {
    ANY,
    ACT1,
    ACT2,
    ACT3,
    ACT1_AND_ACT2,
    ACT1_AND_ACT3,
    ACT2_AND_ACT3;

    // TODO: double check these numbers (just off the top of my head right now, but should verify)
    private boolean isAct1(int floor) { return floor >= 1 && floor <= 17; }
    private boolean isAct2(int floor) { return floor >= 18 && floor <= 34; }
    private boolean isAct3(int floor) { return floor >= 35 && floor <= 51; }

    public boolean isFloorActive(int floor) {
        if (this == ANY)
            return true;
        else if (this == ACT1)
            return isAct1(floor);
        else if (this == ACT2)
            return isAct2(floor);
        else if (this == ACT3)
            return isAct3(floor);
        else if (this == ACT1_AND_ACT2)
            return isAct1(floor) || isAct2(floor);
        else if (this == ACT1_AND_ACT3)
            return isAct1(floor) || isAct3(floor);
        else if (this == ACT2_AND_ACT3)
            return isAct2(floor) || isAct3(floor);
        else
            return false;
    }

    public String toString() {
        if (this == ANY)
            return "Any";
        else if (this == ACT1)
            return "Act 1";
        else if (this == ACT2)
            return "Act 2";
        else if (this == ACT3)
            return "Act 3";
        else if (this == ACT1_AND_ACT2)
            return "Act 1 or Act 2";
        else if (this == ACT1_AND_ACT3)
            return "Act 1 or Act 3";
        else if (this == ACT2_AND_ACT3)
            return "Act 2 or Act 3";
        else
            return "ERROR";
    }
}
