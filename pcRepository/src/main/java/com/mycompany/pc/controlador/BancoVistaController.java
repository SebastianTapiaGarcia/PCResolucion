package com.mycompany.pc.controlador;

import com.mycompany.pc.modelo.BancoFacade;
import com.mycompany.pc.vista.BancoVista;
import com.mycompany.pc.vista.CrearCuentaPanelView;
import com.mycompany.pc.vista.TransferenciaPanelView;
import com.mycompany.pc.vista.VerMovimientoPanelView;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class BancoVistaController {

    private final BancoFacade modelo;

    private final BancoVista vista;
    private final CrearCuentaController    crearCtrl;
    private final TransferenciaController  transferenciaCtrl;
    private final VerMovimientoController  verMovimientoCtrl;

    public BancoVistaController() {
        // 1) Creamos el modelo (BancoFacade)
        this.modelo = new BancoFacade();

        // 2) Creamos las vistas (paneles)
        CrearCuentaPanelView   crearView  = new CrearCuentaPanelView();
        TransferenciaPanelView transfView = new TransferenciaPanelView();
        VerMovimientoPanelView verView    = new VerMovimientoPanelView();

        // 3) Creamos los subcontroladores, inyectándoles el modelo y la vista correspondiente
        this.crearCtrl          = new CrearCuentaController(modelo, crearView);
        this.transferenciaCtrl  = new TransferenciaController(modelo, transfView);
        this.verMovimientoCtrl  = new VerMovimientoController(modelo, verView);

        // 4) Creamos la vista principal, pasándole los tres paneles
        this.vista = new BancoVista(crearView, transfView, verView);

        // 5) Registramos el listener para detectar cambio de pestaña
        this.vista.addTabChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                int idx   = vista.getSelectedTabIndex();
                String title = vista.getTabTitleAt(idx);
                if (title.equals("2. Transferencia")) {
                    transferenciaCtrl.recargarCombos();
                } else if (title.equals("3. Ver Movimientos")) {
                    verMovimientoCtrl.recargarCombo();
                }
                // La pestaña “1. Crear Cuenta” no requiere recargas especiales  
            }
        });

        // 6) Finalmente, hacemos visible la ventana
        this.vista.setVisible(true);
    }
}