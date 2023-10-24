package dominio;

import java.util.ArrayList;
import java.util.List;

public class Provincia {
    private String nombre;
    private List<Municipio> municipios;

    public Provincia(String nombre) {
        this.nombre = nombre;
        municipios = new ArrayList<>();
    }

    public void agregarMunicipio(Municipio municipio) {
        municipios.add(municipio);
    }

    public int contarHabitantes() {
        int totalHabitantes = 0;

        for (Municipio municipio : municipios) {
            totalHabitantes += municipio.contarHabitantes();
        }

        return totalHabitantes;
    }
    public List<Municipio> getMunicipios() {
        return municipios;
    }
    public String getNombre() {
        return nombre;
    }


    @Override
    public String toString() {
        return "Provincia: " + nombre + " Municipios: "+ municipios +", Total habitantes: " + contarHabitantes();
    }
}