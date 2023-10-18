import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;

// A custom cell renderer for "Noble?" and "Choose?" columns

// TableCellRenderers give instructions on how to render the Cells inside of a JTable...
// Its my first time ever to work with this, and i dont like it honestly but ill still use it


class CustomCellRenderer extends DefaultTableCellRenderer {
    Font font = new Font("Arial", Font.BOLD, 15); // Font properties

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