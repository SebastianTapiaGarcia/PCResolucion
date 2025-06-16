package com.mycompany.pc.controlador;

import com.mycompany.pc.modelo.BancoFacade;
import com.mycompany.pc.modelo.Cuenta;
import com.mycompany.pc.modelo.Movimiento;
import com.mycompany.pc.vista.VerMovimientoPanelView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JOptionPane;

public class VerMovimientoController {

    private final BancoFacade modelo;
    private final VerMovimientoPanelView vista;

    public VerMovimientoController(BancoFacade modelo, VerMovimientoPanelView vista) {
        this.modelo = modelo;
        this.vista  = vista;
        inicializar();
    }

    private void inicializar() {
        // 1) Lleno el combo al inicializar el controlador
        recargarCombo();

        // 2) Registro el listener del botón “Ver Movimientos”
        vista.getVerButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                manejarVerMovimientos();
            }
        });
    }

    public void recargarCombo() {
        List<Cuenta> lista = modelo.listarCuentas();
        vista.recargarCombo(lista);
    }

    private void manejarVerMovimientos() {
        // 1) Obtengo la Cuenta seleccionada del combo
        Cuenta seleccionada = (Cuenta) vista.getComboCuentas().getSelectedItem();
        if (seleccionada == null) {
            JOptionPane.showMessageDialog(
                vista,
                "Seleccione primero una cuenta.",
                "Atención",
                JOptionPane.WARNING_MESSAGE
            );
            return;
        }

        int idCuenta = seleccionada.getId();
        // 2) Pido al modelo la lista de movimientos
        List<Movimiento> movs = modelo.verMovimientos(idCuenta);

        if (movs.isEmpty()) {
            vista.limpiarTabla();
            JOptionPane.showMessageDialog(
                vista,
                "No hay movimientos para la cuenta " + idCuenta,
                "Sin Movimientos",
                JOptionPane.INFORMATION_MESSAGE
            );
        } else {
            // 3) Paso la lista a la vista para que la muestre en la JTable
            vista.llenarTablaMovimientos(movs);
        }
    }
}