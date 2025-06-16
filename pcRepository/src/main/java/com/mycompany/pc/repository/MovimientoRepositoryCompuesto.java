/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.pc.repository;

import com.mycompany.pc.modelo.Movimiento;
import java.util.List;
import java.util.ArrayList;

public class MovimientoRepositoryCompuesto implements MovimientoRepository {
    private final List<MovimientoRepository> repositorios;

    public MovimientoRepositoryCompuesto(MovimientoRepository... repos) {
        this.repositorios = new ArrayList<>();
        for (MovimientoRepository r : repos) {
            this.repositorios.add(r);
        }
    }

    @Override
    public void guardar(Movimiento movimiento) {
        for (MovimientoRepository r : repositorios) {
            r.guardar(movimiento);
        }
    }

    @Override
    public List<Movimiento> buscarPorCuenta(int cuentaId) {
        return repositorios.get(1).buscarPorCuenta(cuentaId);
    }
}