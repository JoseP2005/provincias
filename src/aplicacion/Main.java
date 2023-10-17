package aplicacion;

import dominio.Localidad;
import dominio.Municipio;
import dominio.Provincia;

public class Main {
    public static void main(String[] args) {
        Localidad localidad1 = new Localidad();
        localidad1.setNombre("Pueblo Jose Luis");
        localidad1.setNumeroDeHabitantes(1000);

		Localidad localidad2 = new Localidad();
		localidad2.setNombre("Pueblo San Juan");
		localidad2.setNumeroDeHabitantes(2000);

        Municipio municipio1 = new Municipio("Boadilla del Monte");
        municipio1.agregarLocalidad(localidad1);
		municipio1.agregarLocalidad(localidad2);
       
        Provincia provincia1 = new Provincia("Madrid");
        provincia1.agregarMunicipio(municipio1);

        System.out.println(provincia1);

		Localidad localidad3 = new Localidad();
        localidad3.setNombre("Granollers");
        localidad3.setNumeroDeHabitantes(3000);

		Localidad localidad4 = new Localidad();
		localidad4.setNombre("Cardedeu");
		localidad4.setNumeroDeHabitantes(1000);

		Municipio municipio2 = new Municipio("Barcelona");
		 municipio2.agregarLocalidad(localidad3);
		 municipio2.agregarLocalidad(localidad4);

		Provincia provincia2 = new Provincia("Cataluña");
        provincia2.agregarMunicipio(municipio2);

		System.out.println(provincia2);
    }
}