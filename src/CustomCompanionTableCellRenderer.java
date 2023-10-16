import javax.swing.table.DefaultTableCellRenderer;
import java.awt.Color;
import java.awt.Component;
import javax.swing.JTable;

public class CustomCompanionTableCellRenderer extends DefaultTableCellRenderer {
    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

        // Retrieve the value at the specified column (column 8) for the current row
        Object cellValue = table.getValueAt(row, 9); // 8 is the column index

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
                c.setBackground(Color.WHITE); // Default to blue if no matching color is found
            }
        } else {
            // Handle the case when the cell value is null or not a valid color
            c.setBackground(Color.WHITE); // Default to white
        }

        return c;
    }
}
