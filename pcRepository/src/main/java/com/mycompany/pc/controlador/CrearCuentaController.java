package com.mycompany.pc.controlador;

import com.mycompany.pc.modelo.BancoFacade;
import com.mycompany.pc.modelo.Cuenta;
import com.mycompany.pc.vista.CrearCuentaPanelView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;

public class CrearCuentaController {
    private final BancoFacade modelo;
    private final CrearCuentaPanelView vista;
    
    public CrearCuentaController(BancoFacade modelo) {
        this.modelo = modelo;
        this.vista  = null; // Se inyectará en el constructor de la vista
    }
    
    public CrearCuentaController(BancoFacade modelo, CrearCuentaPanelView vista) {
        this.modelo = modelo;
        this.vista  = vista;
        inicializar();
    }
    
    private void inicializar() {
        // El botón “Crear” dispara este ActionListener
        vista.getCrearButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                manejarCrearCuenta();
            }
        });
    }
    
    private void manejarCrearCuenta() {
        try {
            int    id     = Integer.parseInt(vista.getIdField().getText().trim());
            String tipo   = (String) vista.getTipoCombo().getSelectedItem();
            double saldo  = Double.parseDouble(vista.getSaldoField().getText().trim());
            double limite = 0.0;
            if(tipo.equalsIgnoreCase("Corriente")){
                limite = Double.parseDouble(vista.getLimiteField().getText().trim());
            }
            Cuenta nueva = modelo.crearCuenta(id, tipo, saldo, limite);
            
            if (nueva != null) {
                JOptionPane.showMessageDialog(
                    vista,
                    "Cuenta creada exitosamente:\nID = " + nueva.getId()
                    + "\nTipo = " + nueva.getTipo()
                    + "\nSaldo = " + nueva.getSaldo(),
                    "Éxito",
                    JOptionPane.INFORMATION_MESSAGE
                );
                vista.limpiarCampos();
            } else {
                JOptionPane.showMessageDialog(
                    vista,
                    "No se pudo crear la cuenta. Verifica que el ID no exista ya.",
                    "Error",
                    JOptionPane.ERROR_MESSAGE
                );
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(
                vista,
                "Datos inválidos. Asegúrate de ingresar números correctos.",
                "Error de formato",
                JOptionPane.WARNING_MESSAGE
            );
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(
                vista,
                "Ocurrió un error inesperado al crear la cuenta.",
                "Error",
                JOptionPane.ERROR_MESSAGE
            );
        }
    }

    public CrearCuentaPanelView getVista() {
        return vista;
    }
}
