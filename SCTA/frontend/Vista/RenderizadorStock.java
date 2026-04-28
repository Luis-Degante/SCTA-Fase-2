package Vista;

import java.awt.Color;
import java.awt.Component;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

public class RenderizadorStock extends DefaultTableCellRenderer {

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, 
            boolean isSelected, boolean hasFocus, int row, int column) {
        
        Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

        // Variables para guardar los índices de las columnas
        int colStockActual = -1;
        int colStockMinimo = -1;

        // Buscamos dinámicamente el índice por el nombre de la columna
        for (int i = 0; i < table.getColumnCount(); i++) {
            String nombreCol = table.getColumnName(i);
            if (nombreCol.equalsIgnoreCase("Stock Actual")) {
                colStockActual = i;
            } else if (nombreCol.equalsIgnoreCase("Stock Minimo")) {
                colStockMinimo = i;
            }
        }

        // Solo si encontramos ambas columnas, aplicamos la lógica de color
        if (colStockActual != -1 && colStockMinimo != -1) {
            try {
                int actual = Integer.parseInt(table.getValueAt(row, colStockActual).toString());
                int minimo = Integer.parseInt(table.getValueAt(row, colStockMinimo).toString());

                if (actual <= minimo) {
                    c.setBackground(new Color(255, 102, 102)); // Rojo suave
                    c.setForeground(Color.WHITE); // Texto blanco para contraste
                } else {
                    // Colores por defecto según si la fila está seleccionada o no
                    if (isSelected) {
                        c.setBackground(table.getSelectionBackground());
                        c.setForeground(table.getSelectionForeground());
                    } else {
                        c.setBackground(Color.WHITE);
                        c.setForeground(Color.BLACK);
                    }
                }
            } catch (NumberFormatException e) {
                // Si el valor no es numérico, dejamos el color por defecto
            }
        }

        return c;
    }
}
