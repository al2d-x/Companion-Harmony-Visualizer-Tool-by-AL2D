import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class VanillaTableModel extends AbstractTableModel {
    private String[] columnNames = { "Chosen?",
            "Name", "Likes", "Dislikes", "Dislikes", "Hates", "Noble?", "Skills", "Cost", "Color"};

    private List<CompanionVanilla> compVList = new ArrayList<>();

    public VanillaTableModel() {
        compVList.add(new CompanionVanilla(false, "Deshavi", "Klethi", "Borcha", "Rolf", "Hunger, Heavy Casualties, Bunduk as Emissary", false, "Pathfinding, Spotting, Tracking", 0, "standart"));
        compVList.add(new CompanionVanilla(false, "Firentis", "Jeremus", "Nizar", "Katrin", "Robbing Villages, Failing Quests, Rolf as Emissary", true, "Athletics", 0, "standart"));
        compVList.add(new CompanionVanilla(false, "Jeremus", "Firentis", "Artimenner", "Matheld", "Robbing Villages, Returning Serfs, Klethi as Emissary", false, "Wound Treatment, Trade, First Aid, Surgery", 0, "standart"));
        compVList.add(new CompanionVanilla(false, "Marnid", "Borcha", "Alayen", "Baheshtur", "Robbing Villages or Caravans, Failing Quests, Returning Serfs, Matheld as Emissary", false, "Trade", 0, "standart"));
        compVList.add(new CompanionVanilla(false, "Ymira", "Alayen", "Matheld", "Lezalit", "Robbing Villages, Returning Serfs, Kill instead of mercy in kill local merchant quest, Deshavi as Emissary", false, "Riding, First Aid, Trade", 0, "standart"));
        compVList.add(new CompanionVanilla(false, "Katrin", "Bunduk", "Firentis", "Baheshtur", "Hunger, Not Paid, Heavy Casualties, Lezalit as Emissary", false, "Ironflesh, Trade", 100, "standart"));
        compVList.add(new CompanionVanilla(false, "Bunduk", "Katrin", "Lezalit", "Rolf", "Robbing Villages, Heavy Casualties, Nizar as Emissary", false, "Ironflesh, Weapon Master, Power Strike", 200, "standart"));
        compVList.add(new CompanionVanilla(false, "Klethi", "Deshavi", "Borcha", "Artimenner", "Retreating, Marnid as Emissary", false, "Power Throw, Path Finding, Spotting", 200, "standart"));
        compVList.add(new CompanionVanilla(false, "Alayen", "Ymira", "Marnid", "Nizar", "Failing Quests, Retreating, Baheshtur as Emissary", true, "Power Strike, Weapon Master, Riding", 300, "standart"));
        compVList.add(new CompanionVanilla(false, "Artimenner", "Lezalit", "Jeremus", "Klethi", "Hunger, Failing Quests, Heavy Casualties, Katrin as Emissary", false, "Engineer, Trade, Tactics", 300, "standart"));
        compVList.add(new CompanionVanilla(false, "Borcha", "Marnid", "Deshavi", "Klethi", "Hunger, Not Paid, Heavy Casualties, Alayen as Emissary", false, "Tracking, Pathfinding, Spotting", 300, "standart"));
        compVList.add(new CompanionVanilla(false, "Nizar", "Matheld", "Firentis", "Alayen", "Retreating, Jeremus as Emissary", false, "Ironflesh, Power Strike, Weapon Master, Athletics", 300, "standart"));
        compVList.add(new CompanionVanilla(false, "Rolf", "Baheshtur", "Deshavi", "Bunduk", "Retreating, Artimenner as Emissary", true, "Athletics, Tactics", 300, "standart"));
        compVList.add(new CompanionVanilla(false, "Baheshtur", "Rolf", "Katrin", "Marnid", "Hunger, Not Paid, Heavy Casualties, Ymira as Emissary", true, "Power Draw, Weapon Master, Horse Archery", 400, "standart"));
        compVList.add(new CompanionVanilla(false, "Lezalit", "Artimenner", "Ymira", "Bunduk", "Retreating, Borcha as Emissary", true, "Power Strike, Weapon Master, Trainer", 400, "standart"));
        compVList.add(new CompanionVanilla(false, "Matheld", "Nizar", "Ymira", "Jeremus", "Retreating, Firentis as Emissary", true, "Weapon Master, Athletics", 500, "standart"));
    }

    @Override
    public int getRowCount() {
        return compVList.size();
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        CompanionVanilla companion = compVList.get(rowIndex);

        switch (columnIndex) {
            case 0:

                 if(companion.isvIsChoosen()){
                    return "X";
            } else {
                     return "\u00A0";
                 }


                // return companion.isvIsChoosen();

            case 1:
                return companion.getvName();
            case 2:
                return companion.getvLikes();
            case 3:
                return companion.getvDislikesOne();
            case 4:
                return companion.getvDislikesTwo();
            case 5:
                return companion.getvHates();
            case 6:

                 if(companion.isvIsNoble()){
                    return "X";
            } else {
                     return "\u00A0";
                 }


                // return companion.isvIsNoble();

            case 7:
                return companion.getvSkills();
            case 8:
                return companion.getvCost();
            case 9:
                return companion.getCellColor();
            default:
                return null;
        }
    }

    public int getRowHeight(int rowIndex) {
        return 30;
    }


    public boolean isCellEditable(int rowIndex, int columnIndex) {
        // Allow editing of the "Is Choosen" column (first column) and make all others non-editable
        return columnIndex == 0;
    }

    public String getColumnName(int columnIndex){
        return columnNames[columnIndex];
    }

    public CompanionVanilla getCompanionByName(String name) {
        for (CompanionVanilla companion : compVList) {
            if (companion.getvName().equals(name)) {
                return companion;
            }
        }
        return null;
    }

    public void setRowBackgroundColor() {
        // First, set all cell colors to "standart" for all companions
        for (CompanionVanilla companion : compVList) {
            companion.setCellColor("standart");
        }

        // Now, apply the new colorings based on your conditions
        for (CompanionVanilla companion : compVList) {
            if (companion.isvIsChoosen()) {
                String likes = companion.getvLikes();
                String hates1 = companion.getvDislikesOne();
                String hates2 = companion.getvDislikesTwo();
                String color = companion.getCellColor();

                for (CompanionVanilla companion2 : compVList) {
                    String otherName = companion2.getvName();

                    if (likes.equals(otherName) && color != "red") {
                        companion2.setCellColor("green");
                    }else if(likes.equals(otherName) && color == "red"){
                            companion2.setCellColor("red");
                    } else if (hates1.equals(otherName) || hates2.equals(otherName)) {
                        companion2.setCellColor("red");
                    }
                }
            }
        }
    }

}