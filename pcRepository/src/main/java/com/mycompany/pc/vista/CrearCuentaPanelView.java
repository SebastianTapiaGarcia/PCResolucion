package com.mycompany.pc.vista;

import javax.swing.*;
import java.awt.*;

public class CrearCuentaPanelView extends JPanel {
    private final JTextField idField;
    private final JComboBox<String> tipoCombo;
    private final JTextField saldoField;
    private final JTextField limiteField;
    private final JButton crearButton;

    public CrearCuentaPanelView() {
        setBorder(BorderFactory.createTitledBorder("Crear Cuenta"));
        setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(4, 4, 4, 4);
        c.anchor = GridBagConstraints.WEST;

        // --- Fila 0: ID ---
        c.gridx = 0; c.gridy = 0;
        add(new JLabel("ID:"), c);
        c.gridx = 1;
        idField = new JTextField(8);
        add(idField, c);

        // --- Fila 1: Tipo ---
        c.gridx = 0; c.gridy = 1;
        add(new JLabel("Tipo de cuenta:"), c);
        c.gridx = 1;
        tipoCombo = new JComboBox<>(new String[] {"Ahorro","Corriente"});
        tipoCombo.setPreferredSize(new Dimension(100, 24));  
        add(tipoCombo, c);

        // --- Fila 2: Saldo inicial ---
        c.gridx = 0;  c.gridy = 2;
        add(new JLabel("Saldo inicial:"), c);
        c.gridx = 1;
        saldoField = new JTextField(8);
        add(saldoField, c);

        // --- Fila 3: Límite (etiqueta + campo en un panel) ---
        JPanel limitePanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
        limitePanel.add(new JLabel("Límite:"));
        limiteField = new JTextField(8);
        limitePanel.add(limiteField);

        c.gridx = 0; c.gridy = 3; c.gridwidth = 2;
        add(limitePanel, c);

        // Ocultar todo el panel si es Ahorro
        limitePanel.setVisible(false);
        tipoCombo.addActionListener(e -> {
            boolean mostrar = !"Ahorro".equals(tipoCombo.getSelectedItem());
            limitePanel.setVisible(mostrar);
            revalidate();
        });

        // --- Fila 4: Botón ---
        c.gridx = 0; c.gridy = 4; c.gridwidth = 2;
        c.anchor = GridBagConstraints.CENTER;
        crearButton = new JButton("Crear Cuenta");
        add(crearButton, c);   
    }
    
    public JTextField getIdField() {
        return idField;
    }

    public JComboBox<String> getTipoCombo() {
        return tipoCombo;
    }

    public JTextField getSaldoField() {
        return saldoField;
    }

    public JTextField getLimiteField() {
        return limiteField;
    }

    public JButton getCrearButton() {
        return crearButton;
    }

    // — MÉTODO AUXILIAR para limpiar los campos:
    public void limpiarCampos() {
        idField.setText("");
        tipoCombo.setSelectedIndex(0);
        saldoField.setText("");
        limiteField.setText("");
    }
}
