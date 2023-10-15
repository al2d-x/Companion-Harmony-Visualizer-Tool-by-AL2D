import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;

public class CompositeCellRenderer extends DefaultTableCellRenderer {
    private CustomCellRenderer customCellRenderer = new CustomCellRenderer();
    private CustomCompanionTableCellRenderer customCompanionTableCellRenderer = new CustomCompanionTableCellRenderer();

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        Component customCell = customCellRenderer.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
        Component customCompanionCell = customCompanionTableCellRenderer.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

        // You can decide the logic for when to use which renderer
        if (column == 0 || column == 6) {
            return customCell;
        } else {
            return customCompanionCell;
        }
    }
}
