 /*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.pc.repository;
import com.mycompany.pc.configuracion.ConexionBD;
import com.mycompany.pc.modelo.Movimiento;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author alani
 */
public class MovimientoRepositoryImpl implements MovimientoRepository {

    private Connection conexion = null;
    private List<Movimiento> movimientos;

    public MovimientoRepositoryImpl() {
        movimientos = new ArrayList<>();
        conexion = ConexionBD.getConnection();
        cargarDesdeBD();
    }

    @Override
    public void guardar(Movimiento movimiento) {
        String sql = "INSERT INTO movimiento (fecha, descripcion, monto, cuentaid) VALUES (?, ?, ?, ?)";
        try (PreparedStatement stmt = conexion.prepareStatement(sql)) {
            // Conversión segura de fecha
            stmt.setDate(1, new java.sql.Date(movimiento.getFecha().getTime()));
            stmt.setString(2, movimiento.getDescripcion());
            stmt.setDouble(3, movimiento.getMonto());
            stmt.setInt(4, movimiento.getCuentaId());
            stmt.executeUpdate();
            movimientos.add(movimiento);  // También agregamos al cache local
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Movimiento> cargarDesdeBD() {
        movimientos.clear(); // Evitar duplicados al recargar

        String sql = "SELECT * FROM movimiento";
        try (Statement stmt = conexion.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Movimiento movimiento = new Movimiento(
                    rs.getDate("fecha"),
                    rs.getString("descripcion"),
                    rs.getDouble("monto"),
                    rs.getInt("cuentaid")
                );
                movimientos.add(movimiento);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return movimientos;
    }

    @Override
    public List<Movimiento> buscarPorCuenta(int cuentaId) {
        List<Movimiento> resultado = new ArrayList<>();
        String sql = "SELECT * FROM movimiento WHERE cuentaid = ?";
        try (PreparedStatement stmt = conexion.prepareStatement(sql)) {
            stmt.setInt(1, cuentaId);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Movimiento movimiento = new Movimiento(
                        rs.getDate("fecha"),
                        rs.getString("descripcion"),
                        rs.getDouble("monto"),
                        rs.getInt("cuentaid")
                    );
                    resultado.add(movimiento);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultado;
    }
}