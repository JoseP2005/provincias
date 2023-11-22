package dominio;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Municipio implements Serializable {
    private String nombre;
    private List<Localidad> localidades;

    public Municipio(String nombre) {
        this.nombre = nombre;
        localidades = new ArrayList<>();
    }

    public void agregarLocalidad(Localidad localidad) {
        localidades.add(localidad);
    }

    public int contarHabitantes() {
        int totalHabitantes = 0;

        for (Localidad localidad : localidades) {
            totalHabitantes += localidad.getNumeroDeHabitantes();
        }

        return totalHabitantes;
    }
    public List<Localidad> getLocalidades() {
        return localidades;
    }
    public String getNombre() {
        return nombre;
    }

    @Override
    public String toString() {
        return "Municipio: " + nombre + " Localidades: "+ localidades + ", Total habitantes: " + contarHabitantes();
    }
}