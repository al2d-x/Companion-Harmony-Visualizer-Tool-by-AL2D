// Original which is the basis for the VanillaTableModel
// cellColor is a hidden attribute, that is relevant for the coloring of a row
// This class gives instruction on how to initiate A Companion

public class CompanionVanilla {
    private boolean vIsChoosen;
    private String vName;
    private String vLikes;
    private String vDislikesOne;
    private String vDislikesTwo;
    private String vHates;
    private boolean vIsNoble;
    private String vSkills;
    private int vCost;
    private String cellColor;


    public CompanionVanilla(boolean vIsChoosen, String vName, String vLikes, String vDislikesOne, String vDislikesTwo, String vHates, boolean vIsNoble, String vSkills, int vCost, String cellColor) {
        this.vIsChoosen = vIsChoosen;
        this.vName = vName;
        this.vLikes = vLikes;
        this.vDislikesOne = vDislikesOne;
        this.vDislikesTwo = vDislikesTwo;
        this.vHates = vHates;
        this.vIsNoble = vIsNoble;
        this.vSkills = vSkills;
        this.vCost = vCost;
        this.cellColor = cellColor;
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

    public String getvLikes() {
        return vLikes;
    }

    public void setvLikes(String vLikes) {
        this.vLikes = vLikes;
    }

    public String getvDislikesOne() {
        return vDislikesOne;
    }

    public void setvDislikesOne(String vDislikesOne) {
        this.vDislikesOne = vDislikesOne;
    }

    public String getvDislikesTwo() {
        return vDislikesTwo;
    }

    public void setvDislikesTwo(String vDislikesTwo) {
        this.vDislikesTwo = vDislikesTwo;
    }

    public String getvHates() {
        return vHates;
    }

    public void setvHates(String vHates) {
        this.vHates = vHates;
    }

    public boolean isvIsNoble() {
        return vIsNoble;
    }

    public void setvIsNoble(boolean vIsNoble) {
        this.vIsNoble = vIsNoble;
    }

    public String getvSkills() {
        return vSkills;
    }

    public void setvSkills(String vSkills) {
        this.vSkills = vSkills;
    }

    public int getvCost() {
        return vCost;
    }

    public void setvCost(int vCost) {
        this.vCost = vCost;
    }

    public String getCellColor() {
        return cellColor;
    }

    public void setCellColor(String cellColor) {
        this.cellColor = cellColor;
    }
}
