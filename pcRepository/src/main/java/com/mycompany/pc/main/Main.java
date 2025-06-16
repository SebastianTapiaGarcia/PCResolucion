package com.mycompany.pc.main;

import javax.swing.SwingUtilities;
import com.mycompany.pc.vista.BancoVista;
import com.mycompany.pc.configuracion.ConexionBD; 
import com.mycompany.pc.controlador.BancoVistaController;
import java.sql.Connection;

public class Main {
    public static void main(String[] args) {
        try (Connection conn = ConexionBD.getConnection()) {
        if (conn != null) {
            System.out.println("Conexión exitosa");
        }
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        new BancoVistaController();
    }
}
