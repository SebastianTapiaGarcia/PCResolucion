
package com.mycompany.pc.repository;

import com.mycompany.pc.modelo.Movimiento;
import java.util.List;


public interface MovimientoRepository {
    void guardar(Movimiento movimiento);
    List<Movimiento> buscarPorCuenta(int cuentaId);
}
