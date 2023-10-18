import javax.swing.table.DefaultTableCellRenderer;
import java.awt.Color;
import java.awt.Component;
import javax.swing.JTable;

// This TableCellRenderer Class colors the rows in the right color which is dictated by the last attribute in CompanionVanilla

// TableCellRenderers give instructions on how to render the Cells inside of a JTable...
// Its my first time ever to work with this, and i dont like it honestly but ill still use it

public class CustomCompanionTableCellRenderer extends DefaultTableCellRenderer {
    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

        // Retrieve the value at the specified column (column 9) for the current row
        Object cellValue = table.getValueAt(row, 9);

        if (cellValue != null) {
            String color = cellValue.toString(); // Convert the cell value to a String

            // Set the background color based on the companion's cellColor
            if ("standart".equals(color)) {
                c.setBackground(Color.WHITE);
            } else if ("red".equals(color)) {
                c.setBackground(new Color(255, 160, 160)); // Light Red
            } else if ("green".equals(color)) {
                c.setBackground(new Color(160, 255, 160)); // Light Green
            } else {
                c.setBackground(Color.WHITE); // Default to white if no matching color is found
            }
        } else {
            // Handle the case when the cell value is null or not a valid color
            c.setBackground(Color.WHITE); // Default to white (build in for safety purpose only)
        }

        return c;
    }
}
