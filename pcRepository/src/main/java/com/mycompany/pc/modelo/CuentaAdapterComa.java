package com.mycompany.pc.modelo;

public class CuentaAdapterComa implements CuentaAdapter{

    @Override
    public Cuenta adapt(String linea) {
        String[] p = linea.split(",");
        return new CuentaBuilder()
                .conId(Integer.parseInt(p[0].trim()))
                .conTipo(p[1].trim())
                .conSaldo(Double.parseDouble(p[2].trim()))
                .conLimite(Double.parseDouble(p[3].trim()))
                .build();    
    }
    
}
