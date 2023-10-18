import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;

// Combines the both Renderes into one
// 1. Font and center alligntment for column isChoosen and isNooble
// 2. Coloring of Rows based on the last Attribute from CompanionVanilla
//    Combining both renders in a class itself caused some problems, which is why i splitted them, which still caused another minor problem
//    And this problem is, that no color is applied to 2 columns, which is fine

// TableCellRenderers give instructions on how to render the Cells inside of a JTable...
// Its my first time ever to work with this, and i dont like it honestly but ill still use it

public class CompositeCellRenderer extends DefaultTableCellRenderer {
    private CustomCellRenderer customCellRenderer = new CustomCellRenderer();
    private CustomCompanionTableCellRenderer customCompanionTableCellRenderer = new CustomCompanionTableCellRenderer();

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        Component customCell = customCellRenderer.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
        Component customCompanionCell = customCompanionTableCellRenderer.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

        if (column == 0 || column == 6) {
            return customCell;
        } else {
            return customCompanionCell;
        }
    }
}
