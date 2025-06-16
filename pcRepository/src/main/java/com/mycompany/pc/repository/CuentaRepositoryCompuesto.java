/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.pc.repository;

import com.mycompany.pc.modelo.Cuenta;
import java.util.List;
import java.util.ArrayList;

public class CuentaRepositoryCompuesto implements CuentaRepository {
    private final List<CuentaRepository> repositorios;

    public CuentaRepositoryCompuesto(CuentaRepository... repos) {
        this.repositorios = new ArrayList<>();
        for (CuentaRepository r : repos) {
            this.repositorios.add(r);
        }
    }

    @Override
    public void guardar(Cuenta cuenta) {
        for (CuentaRepository r : repositorios) {
            r.guardar(cuenta);
        }
    }

    @Override
    public List<Cuenta> obtenerTodas() {
        // Puedes decidir devolver de uno solo (ej: el primero)
        return repositorios.get(1).obtenerTodas();
    }
    
    @Override
    public Cuenta buscarPorId(int id) {
        return repositorios.get(1).buscarPorId(id);
    }
}