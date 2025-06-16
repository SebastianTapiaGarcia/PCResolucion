package com.mycompany.pc.modelo;

public class CuentaCorriente extends Cuenta {
    public CuentaCorriente(int id, double saldo, double limite) {
        super(id, "Corriente", saldo, limite);
    }

    @Override
    public boolean retirar(double monto) {
        if ((saldo + limite) >= monto) {
            saldo -= monto;
            return true;
        }
        return false;
    }

    @Override
    public void depositar(double monto) {
        saldo += monto;
    }
}
