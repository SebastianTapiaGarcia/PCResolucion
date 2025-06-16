package com.mycompany.pc.util;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ManejadorArchivos {

    public static List<String> leerArchivo(String ruta) {
        List<String> lineas = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(ruta))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                lineas.add(linea);
            }
        } catch (IOException e) {
            System.out.println("Error leyendo archivo: " + ruta);
        }
        return lineas;
    }

    public static void escribirArchivo(String ruta, List<String> lineas) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(ruta))) {
            for (String linea : lineas) {
                bw.write(linea);
                bw.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error escribiendo archivo: " + ruta);
        }
    }
}
