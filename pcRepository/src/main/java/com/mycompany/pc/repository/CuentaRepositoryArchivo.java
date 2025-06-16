package com.mycompany.pc.repository;

import com.mycompany.pc.modelo.Cuenta;
import com.mycompany.pc.modelo.CuentaAdapter;
import com.mycompany.pc.modelo.CuentaAdapterComa;
import com.mycompany.pc.modelo.CuentaAdapterPipe;
import com.mycompany.pc.util.ManejadorArchivos;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class CuentaRepositoryArchivo implements CuentaRepository {
    private static final String RUTA = "cuentas.txt";
    private List<Cuenta> cuentas;

    public CuentaRepositoryArchivo() {
        cuentas = new ArrayList<>();
        cargarDesdeArchivo();
    }

    private void cargarDesdeArchivo() {
        cuentas.clear();
        for (String linea : ManejadorArchivos.leerArchivo(RUTA)) {
            CuentaAdapter adapter = linea.contains(",")
                ? new CuentaAdapterComa()
                : new CuentaAdapterPipe();
            cuentas.add(adapter.adapt(linea));
        }
    }

    private void almacenarEnArchivo() {
        List<String> lineas = cuentas.stream()
            .map(c -> c.getId() + "|" + c.getTipo() + "|" + c.getSaldo() + "|" + c.getLimite())
            .collect(Collectors.toList());
        ManejadorArchivos.escribirArchivo(RUTA, lineas);
    }

    @Override
    public void guardar(Cuenta cuenta) {
        // Si existe, la reemplazamos; si no, la añadimos
        cuentas.removeIf(c -> c.getId() == cuenta.getId());
        cuentas.add(cuenta);
        almacenarEnArchivo();
    }

    @Override
    public List<Cuenta> obtenerTodas() {
        return new ArrayList<>(cuentas);
    }

    @Override
    public Cuenta buscarPorId(int id) {
        return cuentas.stream()
            .filter(c -> c.getId() == id)
            .findFirst()
            .orElse(null);
    }
}

