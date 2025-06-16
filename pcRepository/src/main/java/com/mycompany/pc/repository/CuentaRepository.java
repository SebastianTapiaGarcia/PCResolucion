package com.mycompany.pc.repository;

import com.mycompany.pc.modelo.*;
import java.util.List;

public interface CuentaRepository {
    void guardar(Cuenta cuenta);
    List<Cuenta> obtenerTodas();
    Cuenta buscarPorId(int id);
}

