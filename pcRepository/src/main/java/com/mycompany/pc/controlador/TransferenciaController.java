package com.mycompany.pc.controlador;

import com.mycompany.pc.modelo.BancoFacade;
import com.mycompany.pc.modelo.Cuenta;
import com.mycompany.pc.vista.TransferenciaPanelView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JOptionPane;

public class TransferenciaController {
     private final BancoFacade modelo;
    private final TransferenciaPanelView vista;

    public TransferenciaController(BancoFacade modelo) {
        this.modelo = modelo;
        this.vista  = null; // Se inyectará en el constructor de la vista
    }
    
    public TransferenciaController(BancoFacade modelo, TransferenciaPanelView vista) {
        this.modelo = modelo;
        this.vista  = vista;
        inicializar();
    }

    private void inicializar() {
        List<Cuenta> listaCuentas = modelo.listarCuentas();
        vista.recargarCombos(listaCuentas); 

        // 2. Registrar el listener del botón “Transferir”
        vista.getTransferirBtn().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                manejarTransferencia();
            }
        });
    }

    private void manejarTransferencia() {
        try {
            // 1. Obtener la cuenta seleccionada directamente
            Cuenta cuentaOrigen  = (Cuenta) vista.getComboOrigen().getSelectedItem();
            Cuenta cuentaDestino = (Cuenta) vista.getComboDestino().getSelectedItem();

            // 2. Extraer sus IDs
            int idOrigen  = cuentaOrigen.getId();
            int idDestino = cuentaDestino.getId();

            // 3. Parsear monto
            double monto  = Double.parseDouble(vista.getMontoField().getText().trim());

            // 4. Validaciones y llamada al modelo
            if (idOrigen == idDestino) {
                JOptionPane.showMessageDialog(vista,
                    "La cuenta de origen y destino no pueden ser la misma.",
                    "Error", JOptionPane.WARNING_MESSAGE);
                return;
            }
            if (modelo.transferir(idOrigen, idDestino, monto)) {
                JOptionPane.showMessageDialog(vista,
                    String.format("Transferencia exitosa.%nOrigen: %d%nDestinatario: %d%nMonto: %.2f",
                                  idOrigen, idDestino, monto),
                    "Éxito", JOptionPane.INFORMATION_MESSAGE);
                vista.getMontoField().setText("");
            } else {
                JOptionPane.showMessageDialog(vista,
                    "No fue posible realizar la transferencia. Revisa saldos y cuentas.",
                    "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(vista,
                "Monto inválido. Asegúrate de ingresar un número válido.",
                "Error de formato", JOptionPane.WARNING_MESSAGE);
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(vista,
                "Error inesperado al intentar transferir fondos.",
                "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public void recargarCombos(){
        List<Cuenta> cuentas = modelo.listarCuentas();
        vista.llenarComboCuentas(cuentas);
    }
}
