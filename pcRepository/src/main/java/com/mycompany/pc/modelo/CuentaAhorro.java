package com.mycompany.pc.modelo;

public class CuentaAhorro extends Cuenta {
    public CuentaAhorro(int id, double saldo) {
        super(id, "Ahorro", saldo, 0.0);
    }

    @Override
    public boolean retirar(double monto) {
        if (saldo >= monto) {
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
