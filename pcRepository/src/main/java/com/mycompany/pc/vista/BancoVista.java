package com.mycompany.pc.vista;


import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;

public class BancoVista extends JFrame {
    private final CrearCuentaPanelView    crearPane;
    private final TransferenciaPanelView  transfPane;
    private final VerMovimientoPanelView  verPane;
    private final JTabbedPane tabs;

    /**
     * Constructor: recibe las tres vistas (paneles) ya asociadas a sus controladores correspondientes.
     */
    public BancoVista(
            CrearCuentaPanelView crearPane,
            TransferenciaPanelView transfPane,
            VerMovimientoPanelView verPane
    ) {
        super("Sistema Bancario PeruBank");

        this.crearPane  = crearPane;
        this.transfPane = transfPane;
        this.verPane    = verPane;

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setLocationRelativeTo(null);

        tabs = new JTabbedPane();
        tabs.add("1. Crear Cuenta",    crearPane);
        tabs.add("2. Transferencia",   transfPane);
        tabs.add("3. Ver Movimientos", verPane);

        add(tabs, BorderLayout.CENTER);
    }

    /**
     * Permite al controlador registrar un ChangeListener en el JTabbedPane.
     * El controlador debe usar este método y, al detectar el índice de la pestaña,
     * llamar a recargarCombos() o recargarCombo(...) en los controladores correspondientes.
     */
    public void addTabChangeListener(ChangeListener listener) {
        tabs.addChangeListener(listener);
    }

    /**
     * Devuelve el índice de la pestaña actualmente seleccionada.
     */
    public int getSelectedTabIndex() {
        return tabs.getSelectedIndex();
    }

    /**
     * Devuelve el título de la pestaña en la posición dada.
     */
    public String getTabTitleAt(int index) {
        return tabs.getTitleAt(index);
    }
}