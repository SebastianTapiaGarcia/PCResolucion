package com.mycompany.pc.modelo;

// para poder convertir lineas de texto en objetos
public interface CuentaAdapter {
    Cuenta adapt(String linea); //lo que hará adapt es convertir una linea del archivo en un objeto Cuenta
}
