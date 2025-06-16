package com.mycompany.pc.modelo;

public abstract class Cuenta {
    protected int id;
    protected String tipo;
    protected double saldo;
    protected double limite; // Solo para cuentas Corrientes

    public Cuenta(int id, String tipo, double saldo, double limite) {
        this.id = id;
        this.tipo = tipo;
        this.saldo = saldo;
        this.limite = limite;
    }

    public abstract boolean retirar(double monto);
    public abstract void depositar(double monto);

    public int getId() { return id; }
    public String getTipo() { return tipo; }
    public double getSaldo() { return saldo; }
    public double getLimite() { return limite; }

    public void setSaldo(double saldo) { this.saldo = saldo; }
    public void setLimite(double limite) { this.limite = limite; }

    @Override
    public String toString() {
        return tipo + " - ID: " + id + " - S/: " + saldo;
    }
}
