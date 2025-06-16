package com.mycompany.pc.modelo;

import com.mycompany.pc.repository.CuentaRepository;
import com.mycompany.pc.repository.MovimientoRepositoryArchivo;
import com.mycompany.pc.repository.MovimientoRepository;
import com.mycompany.pc.repository.CuentaRepositoryArchivo;
import com.mycompany.pc.repository.CuentaRepositoryCompuesto;
import com.mycompany.pc.repository.CuentaRepositoryImpl;
import com.mycompany.pc.repository.MovimientoRepositoryCompuesto;
import com.mycompany.pc.repository.MovimientoRepositoryImpl;

import java.util.Date;
import java.util.List;

public class BancoFacade {
    private final CuentaRepository cuentaRepo;
    private final MovimientoRepository movRepo;

    public BancoFacade() {
        CuentaRepository repoArchivo = new CuentaRepositoryArchivo();
        CuentaRepository repoImpl    = new CuentaRepositoryImpl(); // tu otra implementación
        this.cuentaRepo = new CuentaRepositoryCompuesto(repoArchivo, repoImpl);

        MovimientoRepository movArchivo = new MovimientoRepositoryArchivo();
        MovimientoRepository movImpl    = new MovimientoRepositoryImpl(); // tu otra implementación
        this.movRepo = new MovimientoRepositoryCompuesto(movArchivo, movImpl);
    }
    public List<Cuenta> listarCuentas() {
        return cuentaRepo.obtenerTodas();
    }

    public Cuenta crearCuenta(int id, String tipo, double saldo, double limite) {
        Cuenta c = new CuentaBuilder()
                       .conId(id)
                       .conTipo(tipo)
                       .conSaldo(saldo)
                       .conLimite(limite)
                       .build();
        cuentaRepo.guardar(c);          // persiste en archivo
        return c;
    }

    public boolean transferir(int origenId, int destinoId, double monto) {
        Cuenta o = cuentaRepo.buscarPorId(origenId);
        Cuenta d = cuentaRepo.buscarPorId(destinoId);
        if (o == null || d == null || monto <= 0 || o.getSaldo() + o.getLimite() < monto) {
            return false;
        }
        // Ajusta saldos y persiste automáticamente
        o.setSaldo(o.getSaldo() - monto);
        d.setSaldo(d.getSaldo() + monto);
        cuentaRepo.guardar(o);  // podrías añadir un método actualizar si no quieres duplicar
        cuentaRepo.guardar(d);

        // Crear movimientos y persistirlos
        Date ahora = new Date();
        Movimiento m1 = new Movimiento(ahora, "Transferencia a " + destinoId, -monto, origenId);
        Movimiento m2 = new Movimiento(ahora, "Transferencia de " + origenId, monto, destinoId);
        movRepo.guardar(m1);
        movRepo.guardar(m2);
        return true;
    }

    public List<Movimiento> verMovimientos(int cuentaId) {
        return movRepo.buscarPorCuenta(cuentaId);
    }
    
    public Cuenta buscarPorId(int id){
        return cuentaRepo.buscarPorId(id);
    }
}
