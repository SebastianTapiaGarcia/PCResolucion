package com.mycompany.pc.vista;

import com.mycompany.pc.controlador.TransferenciaController;
import com.mycompany.pc.modelo.Cuenta;
import java.util.List;
import javax.swing.*;
import java.awt.*;

public class TransferenciaPanelView extends JPanel {
    private final JComboBox<Cuenta> comboOrigen, comboDestino;
    private final JTextField montoField;
    private final JButton transferirBtn;

    public TransferenciaPanelView() {
        setBorder(BorderFactory.createTitledBorder("Transferencia"));
        setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(4,4,4,4);
        c.anchor = GridBagConstraints.WEST;

        // Origen
        c.gridx=0; c.gridy=0;
        add(new JLabel("Cuenta Origen:"), c);
        c.gridx=1;
        comboOrigen = new JComboBox<>();
        comboOrigen.setPreferredSize(new Dimension(150, 25));
        add(comboOrigen, c);

        // Destino
        c.gridx=0; c.gridy=1;
        add(new JLabel("Cuenta Destino:"), c);
        c.gridx=1;
        comboDestino = new JComboBox<>();
        comboDestino.setPreferredSize(new Dimension(150, 25));
        add(comboDestino, c);

        // Monto
        c.gridx=0; c.gridy=2;
        add(new JLabel("Monto:"), c);
        c.gridx=1;
        montoField = new JTextField(8);
        add(montoField, c);

        // Botón
        c.gridx=0; c.gridy=3; c.gridwidth=2;
        c.anchor = GridBagConstraints.CENTER;
        transferirBtn = new JButton("Transferir");
        add(transferirBtn, c);
    }

    public JComboBox<Cuenta> getComboOrigen() {
        return comboOrigen;
    }

    public JComboBox<Cuenta> getComboDestino() {
        return comboDestino;
    }

    public JTextField getMontoField() {
        return montoField;
    }

    public JButton getTransferirBtn() {
        return transferirBtn;
    }
    
    public void llenarComboCuentas(List<Cuenta> listaCuentas) {
        comboOrigen.removeAllItems();
        comboDestino.removeAllItems();
        for (Cuenta c : listaCuentas) {
            comboOrigen.addItem(c);
            comboDestino.addItem(c);
        }
    }
    
    public void recargarCombos(List<Cuenta> listaCuentas){
        llenarComboCuentas(listaCuentas);
    }
}

