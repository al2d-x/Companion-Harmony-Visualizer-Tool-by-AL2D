import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellRenderer;
import java.awt.*;

// ...

// Define a custom cell renderer for "Noble?" and "Choose?" columns
class CustomCellRenderer extends DefaultTableCellRenderer {
    Font font = new Font("Arial", Font.BOLD, 15); // You can adjust the font properties here

    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

        // Check the column index for "Noble?" or "Choose?" and set the custom font
        if (column == 0 || column == 6) {
            c.setFont(font);
        }

        // Center-align the text in all cells
        ((JLabel) c).setHorizontalAlignment(SwingConstants.CENTER);

        return c;
    }


}

// ...

// Set the custom cell renderer for the table
