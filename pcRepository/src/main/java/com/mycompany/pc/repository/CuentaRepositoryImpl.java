/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.pc.repository;

import com.mycompany.pc.configuracion.ConexionBD;
import com.mycompany.pc.modelo.Cuenta;
import com.mycompany.pc.modelo.CuentaBuilder;


import java.sql.*;
import java.util.*;
import java.util.stream.Collectors;

/**
 *
 * @author alani
 */
public class CuentaRepositoryImpl implements CuentaRepository {

    private Connection conexion = null;
    private List<Cuenta> cuentas;

    public CuentaRepositoryImpl() {
        cuentas = new ArrayList<>();
        conexion = ConexionBD.getConnection();
        cargarDesdeBD();
    }

    @Override
    public void guardar(Cuenta cuenta) {
        String sql = "INSERT INTO cuenta (id, tipo, saldo, limite) VALUES (?, ?, ?, ?)";
        try (PreparedStatement stmt = conexion.prepareStatement(sql)) {
            stmt.setInt(1, cuenta.getId());
            stmt.setString(2, cuenta.getTipo());
            stmt.setDouble(3, cuenta.getSaldo());
            stmt.setDouble(4, cuenta.getLimite());
            int filas = stmt.executeUpdate();

            if (filas > 0) {
                cuentas.add(cuenta); // Sincronizar cache local
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Puedes lanzar excepción o manejarla de otra forma según diseño
        }
    }

    public List<Cuenta> cargarDesdeBD() {
        cuentas.clear(); // evitar duplicados
        String sql = "SELECT * FROM cuenta";

        try (Statement stmt = conexion.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Cuenta cuenta = new CuentaBuilder()
                    .conId(rs.getInt("id"))
                    .conTipo(rs.getString("tipo"))
                    .conSaldo(rs.getDouble("saldo"))
                    .conLimite(rs.getDouble("limite"))
                    .build();
                cuentas.add(cuenta);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return cuentas;
    }

    @Override
    public List<Cuenta> obtenerTodas() {
        return new ArrayList<>(cuentas);
    }

    @Override
    public Cuenta buscarPorId(int id) {
        String sql = "SELECT * FROM cuenta WHERE id = ?";
        try (PreparedStatement stmt = conexion.prepareStatement(sql)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new CuentaBuilder()
                        .conId(rs.getInt("id"))
                        .conTipo(rs.getString("tipo"))
                        .conSaldo(rs.getDouble("saldo"))
                        .conLimite(rs.getDouble("limite"))
                        .build();
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}

    
