// An abridged class of the original CompanionVanilla Class with the only necessary
// Attributes for the calculation of the covered skills and the amount...
// This class gives instruction on how to initiate A Companion but in small

public class CompanionVanillaSmol {
    private boolean vIsChoosen;
    private String vName;
    private String vSkills;

    public CompanionVanillaSmol(boolean vIsChoosen, String vName, String vSkills) {
        this.vIsChoosen = vIsChoosen;
        this.vName = vName;
        this.vSkills = vSkills;
    }

    public boolean isvIsChoosen() {
        return vIsChoosen;
    }

    public void setvIsChoosen(boolean vIsChoosen) {
        this.vIsChoosen = vIsChoosen;
    }

    public String getvName() {
        return vName;
    }

    public void setvName(String vName) {
        this.vName = vName;
    }

    public String getvSkills() {
        return vSkills;
    }

    public void setvSkills(String vSkills) {
        this.vSkills = vSkills;
    }

    public String toString() {
        return
                "vIsChoosen: " + vIsChoosen +
                ", vName: " + vName + '\'' +
                ", vSkills: '" + vSkills + '\'' +
                '}';
    }


}