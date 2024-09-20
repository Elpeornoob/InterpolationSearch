package bryan.interpolationSearch.component;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class Table extends javax.swing.JPanel {
    private JTable table;
    private DefaultTableModel model;

    public Table() {
        setLayout(new BorderLayout());
        setBackground(Color.WHITE);

        model = new DefaultTableModel();
        model.setColumnIdentifiers(new String[]{"Tamaño", "Tiempo (ns)"});
        table = new JTable(model) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        table.getTableHeader().setReorderingAllowed(false);

        ScrollBar verticalScrollBar = new ScrollBar();
        verticalScrollBar.setPreferredSize(new Dimension(10,5));
        ScrollBar horizontalScrollBar = new ScrollBar();

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setVerticalScrollBar(verticalScrollBar);
        scrollPane.setHorizontalScrollBar(horizontalScrollBar);
        add(scrollPane, BorderLayout.CENTER);
    }

    public void añadirFila(Object[] datos) {
        model.addRow(datos);
    }

    public void eliminarDatos() {
        model.setRowCount(0);
    }
}

