
package com.mycompany.pc.repository;


import com.mycompany.pc.modelo.Movimiento;
import com.mycompany.pc.repository.MovimientoRepository;
import com.mycompany.pc.util.ManejadorArchivos;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class MovimientoRepositoryArchivo implements MovimientoRepository{
    private static final String RUTA = "movimientos.txt";
    private final SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
    private List<Movimiento> movimientos = new ArrayList<>();

    public MovimientoRepositoryArchivo() {
        cargarDesdeArchivo();
    }

    private void cargarDesdeArchivo() {
        movimientos.clear();
        for (String linea : ManejadorArchivos.leerArchivo(RUTA)) {
            try {
                String[] p = linea.split("\\|");
                Date fecha = sdf.parse(p[0].trim());
                String desc = p[1].trim();
                double monto = Double.parseDouble(p[2].trim());
                int cuenta = Integer.parseInt(p[3].trim());
                movimientos.add(new Movimiento(fecha, desc, monto, cuenta));
            } catch (Exception ignored) { }
        }
    }

    private void almacenar() {
        List<String> lineas = new ArrayList<>();
        for (Movimiento m : movimientos) {
            lineas.add(
                sdf.format(m.getFecha()) + "|" +
                m.getDescripcion()   + "|" +
                m.getMonto()         + "|" +
                m.getCuentaId()
            );
        }
        ManejadorArchivos.escribirArchivo(RUTA, lineas);
    }

    @Override
    public void guardar(Movimiento movimiento) {
        movimientos.add(movimiento);
        almacenar();
    }

    @Override
    public List<Movimiento> buscarPorCuenta(int cuentaId) {
        return movimientos.stream()
                .filter(m -> m.getCuentaId() == cuentaId)
                .collect(Collectors.toList());
    }
}
