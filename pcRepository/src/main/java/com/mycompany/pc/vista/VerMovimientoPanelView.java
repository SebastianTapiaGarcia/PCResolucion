 package com.mycompany.pc.vista;

import com.mycompany.pc.modelo.Cuenta;
import com.mycompany.pc.modelo.Movimiento;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import javax.swing.table.DefaultTableModel;

public class VerMovimientoPanelView extends JPanel {

    private JComboBox<Cuenta> comboCuentas;
    private JButton verButton;
    private JTable tablaMovimientos;
    private DefaultTableModel tableModel;

    public VerMovimientoPanelView() {
        setLayout(new BorderLayout(10,10));

        // Panel superior: combo + botón
        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 10));
        topPanel.add(new JLabel("Seleccione Cuenta:"));

        comboCuentas = new JComboBox<>();
        topPanel.add(comboCuentas);

        verButton = new JButton("Ver Movimientos");
        topPanel.add(verButton);

        add(topPanel, BorderLayout.NORTH);

        // Panel central: tabla con scroll
        String[] columnas = { "Fecha", "Descripcion", "Monto", "CuentaOrigen" };
        tableModel = new DefaultTableModel(columnas, 0) {
            // Evitar edición de celdas por parte del usuario
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        tablaMovimientos = new JTable(tableModel);
        JScrollPane scroll = new JScrollPane(tablaMovimientos);
        add(scroll, BorderLayout.CENTER);
    }

    public JComboBox<Cuenta> getComboCuentas() {
        return comboCuentas;
    }

    public JButton getVerButton() {
        return verButton;
    }

    /**
     * Ahora recargarCombo recibe la lista de cuentas desde el controlador
     * y la pasa a llenarComboCuentas(...) para poblar el JComboBox<Cuenta>.
     */
    public void recargarCombo(List<Cuenta> listaCuentas) {
        llenarComboCuentas(listaCuentas);
    }

    public void llenarComboCuentas(List<Cuenta> listaCuentas) {
        comboCuentas.removeAllItems();
        for (Cuenta c : listaCuentas) {
            comboCuentas.addItem(c);
        }
    }
    public void limpiarTabla() {
        tableModel.setRowCount(0);
    }

    /**
     * Recibe la lista de movimientos desde el controlador y los agrega al modelo de la tabla.
     */
    public void llenarTablaMovimientos(List<Movimiento> movList) {
        tableModel.setRowCount(0); // borra filas previas
        for (Movimiento m : movList) {
            Object[] fila = {
                m.getFecha(),
                m.getDescripcion(),
                m.getMonto(),
                m.getCuentaId()
            };
            tableModel.addRow(fila);
        }
    }
}