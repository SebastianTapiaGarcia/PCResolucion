package com.mycompany.pc.modelo;

public class CuentaBuilder{
    private int id;
    private String tipo;
    private double saldo;
    private double limite;
    
    public CuentaBuilder conId(int id){
        this.id=id;
        return this;
    }
    
    public CuentaBuilder conTipo(String tipo){
        this.tipo=tipo;
        return this;
    }
    
        public CuentaBuilder conSaldo(double saldo){
        this.saldo=saldo;
        return this;
    }
        
        public CuentaBuilder conLimite(double limite){
        this.limite=limite;
        return this;
    }
        
    public Cuenta build(){
        if ("Ahorro".equalsIgnoreCase(tipo)) {
            return new CuentaAhorro(id, saldo);
        } else if ("Corriente".equalsIgnoreCase(tipo)) {
            return new CuentaCorriente(id, saldo, limite);
        } else {
            throw new IllegalArgumentException("Tipo de cuenta desconocido: " + tipo);
        }
    }
    
}