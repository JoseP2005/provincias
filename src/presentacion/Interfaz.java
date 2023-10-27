package presentacion;

import dominio.Localidad;
import dominio.Municipio;
import dominio.Provincia;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import java.util.*;


public class Interfaz {

    private List<Provincia> provincias;
    private Scanner sc;

    public Interfaz() {
        provincias = new ArrayList<>();
        sc = new Scanner(System.in);
    }

    public void iniciarPrograma() {
        int opcion = 0;

        do {
            mostrarMenu();
            opcion = sc.nextInt();
            sc.nextLine();

            switch (opcion) {
                case 1:
                    crearLocalidades();
                    break;
                case 2:
                    crearMunicipios();
                    break;
                case 3:
                    crearProvincias();
                    break;
                case 4:
                    mostrarInformacion();
                    break;
                case 5:
                    guardarInformacionEnArchivo();
                    break;
                case 6:
                    System.out.println("Gracias por utilizar el programa.");
                default:
                    System.out.println("Opción no válida. Intente nuevamente.");
                    break;
            }

        } while (opcion != 6);
    }

    private void mostrarMenu() {
        System.out.println("----------- MENÚ -----------");
        System.out.println("1. Crear localidades");
        System.out.println("2. Crear municipios");
        System.out.println("3. Crear provincias");
        System.out.println("4. Mostrar información");
        System.out.println("5. Guardar la informacion en Archivo");
        System.out.println("6. Salir");
        System.out.print("Ingrese una opción: ");
    }

    private void crearLocalidades() {
        System.out.print("\nIngrese la cantidad de localidades que desea crear: ");
        int cantidad = sc.nextInt();
        sc.nextLine();

        for (int i = 0; i < cantidad; i++) {
            System.out.println("\n--- CREANDO LOCALIDAD " + (i + 1) + " ---");
            System.out.print("Ingrese el nombre de la localidad: ");
            String nombre = sc.nextLine();
            System.out.print("Ingrese el número de habitantes de la localidad: ");
            int habitantes = sc.nextInt();
            sc.nextLine();

            Localidad localidad = new Localidad();
            localidad.setNombre(nombre);
            localidad.setNumeroDeHabitantes(habitantes);

            System.out.println("Localidad creada: " + localidad);

            agregarLocalidad(localidad);
        }
    }

    private void crearMunicipios() {
        System.out.print("\nIngrese la cantidad de municipios que desea crear: ");
        int cantidad = sc.nextInt();
        sc.nextLine();

        for (int i = 0; i < cantidad; i++) {
            System.out.println("\n--- CREANDO MUNICIPIO " + (i + 1) + " ---");
            System.out.print("Ingrese el nombre del municipio: ");
            String nombre = sc.nextLine();

            Municipio municipio = new Municipio(nombre);

            System.out.print("Ingrese la cantidad de localidades que desea agregar al municipio: ");
            int cantidadLocalidades = sc.nextInt();
            sc.nextLine();

            for (int j = 0; j < cantidadLocalidades; j++) {
                System.out.println("\n--- AGREGANDO LOCALIDAD " + (j + 1) + " AL MUNICIPIO " + (i + 1) + " ---");
                System.out.print("Ingrese el nombre de la localidad: ");
                String nombreLocalidad = sc.nextLine();
                System.out.print("Ingrese el número de habitantes de la localidad: ");
                int habitantes = sc.nextInt();
                sc.nextLine();

                Localidad localidad = new Localidad();
                localidad.setNombre(nombreLocalidad);
                localidad.setNumeroDeHabitantes(habitantes);

                municipio.agregarLocalidad(localidad);

                System.out.println("Localidad agregada al municipio: " + localidad);
            }

            agregarMunicipio(municipio);
        }
    }

    private void crearProvincias() {
        System.out.print("\nIngrese la cantidad de provincias que desea crear: ");
        int cantidad = sc.nextInt();
        sc.nextLine();

        for (int i = 0; i < cantidad; i++) {
            System.out.println("\n--- CREANDO PROVINCIA " + (i + 1) + " ---");
            System.out.print("Ingrese el nombre de la provincia: ");
            String nombre = sc.nextLine();

            Provincia provincia = new Provincia(nombre);

            System.out.print("Ingrese la cantidad de municipios que desea agregar a la provincia: ");
            int cantidadMunicipios = sc.nextInt();
            sc.nextLine();

            for (int j = 0; j < cantidadMunicipios; j++) {
                System.out.println("\n--- AGREGANDO MUNICIPIO " + (j + 1) + " A LA PROVINCIA " + (i + 1) + " ---");
                System.out.print("Ingrese el nombre del municipio: ");
                String nombreMunicipio = sc.nextLine();

                Municipio municipio = new Municipio(nombreMunicipio);

                System.out.print("Ingrese la cantidad de localidades que desea agregar al municipio: ");
                int cantidadLocalidades = sc.nextInt();
                sc.nextLine();

                for (int k = 0; k < cantidadLocalidades; k++) {
                    System.out.println("\n--- AGREGANDO LOCALIDAD " + (k + 1) + " AL MUNICIPIO " + (j + 1) + " DE LA PROVINCIA " + (i + 1) + " ---");
                    System.out.print("Ingrese el nombre de la localidad: ");
                    String nombreLocalidad = sc.nextLine();
                    System.out.print("Ingrese el número de habitantes de la localidad: ");
                    int habitantes = sc.nextInt();
                    sc.nextLine();

                    Localidad localidad = new Localidad();
                    localidad.setNombre(nombreLocalidad);
                    localidad.setNumeroDeHabitantes(habitantes);

                    municipio.agregarLocalidad(localidad);

                    System.out.println("Localidad agregada al municipio: " + localidad);
                }

                provincia.agregarMunicipio(municipio);
            }

            agregarProvincia(provincia);
        }
    }

    private void agregarLocalidad(Localidad localidad) {
        for (Provincia provincia : provincias) {
            System.out.println("--- AGREGANDO LOCALIDAD A LA PROVINCIA " + provincia.getNombre() + " ---");
            System.out.print("¿Desea agregar la localidad a esta provincia? (S/N): ");
            String respuesta = sc.nextLine();

            if (respuesta.equalsIgnoreCase("S")) {
                Municipio municipio = elegirMunicipio(provincia);

                if (municipio != null) {
                    municipio.agregarLocalidad(localidad);
                    System.out.println("Localidad agregada al municipio " + municipio.getNombre() +
                            " de la provincia " + provincia.getNombre() + ".");
                }

                return;
            }
        }

        System.out.println("No se ha encontrado una provincia a la cual agregar la localidad. Se ha descartado.");
    }

    private void agregarMunicipio(Municipio municipio) {
        for (Provincia provincia : provincias) {
            System.out.println("--- AGREGANDO MUNICIPIO A LA PROVINCIA " + provincia.getNombre() + " ---");
            System.out.print("¿Desea agregar el municipio a esta provincia? (S/N): ");
            String respuesta = sc.nextLine();

            if (respuesta.equalsIgnoreCase("S")) {
                provincia.agregarMunicipio(municipio);
                System.out.println("Municipio agregado a la provincia " + provincia.getNombre() + ".");
                return;
            }
        }

        System.out.println("No se ha encontrado una provincia a la cual agregar el municipio. Se ha descartado.");
    }

    private void agregarProvincia(Provincia provincia) {
        provincias.add(provincia);
        System.out.println("Provincia agregada.");
    }

    private Municipio elegirMunicipio(Provincia provincia) {
        System.out.print("Ingrese el nombre del municipio al que desea agregar la localidad: ");
        String nombreMunicipio = sc.nextLine();

        for (Municipio municipio : provincia.getMunicipios()) {
            if (municipio.getNombre().equalsIgnoreCase(nombreMunicipio)) {
                return municipio;
            }
        }

        return null;
    }

    private void mostrarInformacion() {
        System.out.println("\n--- INFORMACIÓN ---");

        for (Provincia provincia : provincias) {
            System.out.println("Provincia: " + provincia.getNombre());
            System.out.println("Total habitantes: " + provincia.contarHabitantes());
            System.out.println("Municipios:");

            for (Municipio municipio : provincia.getMunicipios()) {
                System.out.println("- " + municipio.getNombre());
                System.out.println("  Habitantes: " + municipio.contarHabitantes());
                System.out.println("  Localidades:");

                for (Localidad localidad : municipio.getLocalidades()) {
                    System.out.println("  - " + localidad.getNombre());
                    System.out.println("    Habitantes: " + localidad.getNumeroDeHabitantes());
                }
            }

            System.out.println("--------------------------------------");
        }
    }
    private void guardarInformacionEnArchivo() {
    try {
        File archivo = new File("informacion.txt"); // Nombre del archivo de salida
        FileWriter fileWriter = new FileWriter(archivo);
        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

        for (Provincia provincia : provincias) {
            bufferedWriter.write("Provincia: " + provincia.getNombre());
            bufferedWriter.newLine();
            bufferedWriter.write("Total habitantes: " + provincia.contarHabitantes());
            bufferedWriter.newLine();
            bufferedWriter.write("Municipios:");

            for (Municipio municipio : provincia.getMunicipios()) {
                bufferedWriter.newLine();
                bufferedWriter.write("- " + municipio.getNombre());
                bufferedWriter.newLine();
                bufferedWriter.write("  Habitantes: " + municipio.contarHabitantes());
                bufferedWriter.newLine();
                bufferedWriter.write("  Localidades:");

                for (Localidad localidad : municipio.getLocalidades()) {
                    bufferedWriter.newLine();
                    bufferedWriter.write("  - " + localidad.getNombre());
                    bufferedWriter.newLine();
                    bufferedWriter.write("    Habitantes: " + localidad.getNumeroDeHabitantes());
                }
            }
        }

        // Cierra el BufferedWriter para asegurarte de que los cambios se escriban en el archivo
        bufferedWriter.close();
        System.out.println("Información guardada en el archivo 'informacion.txt'.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
}






/*package presentacion;
import dominio.*;
import java.util.*;
import java.io.*;

public class Interfaz{
 public static void main(String args[]) {
    Scanner sc=new Scanner(System.in);
    String[] Provincias = {"1. Localidad", "2. Municipios", "3. Provincias"};

        System.out.println("Opciones  disponibles:");
        for (int i = 0; i < Provincias.length; i++) {
            System.out.println((i + 1) + ". " + Provincias[i]);
        }
        int[] nopc1iones = new int[Provincias.length];
    while (true) {
            System.out.print("Ingrese el numero de la opcion que quiera crear(0 para finalizar): ");
            int seleccion = sc.nextInt();

            if (seleccion == 0) {
                break;
            } else if (seleccion < 1 || seleccion > Provincias.length) {
                System.out.println("Selección inválida. Por favor, elija un número válido.");
                continue;
            }
            System.out.print("Ingrese la cantidad de " + Provincias[seleccion - 1] + ": ");
            int cantidad = sc.nextInt();

            if (cantidad < 0) {
                System.out.println("La cantidad no puede ser negativa.");
            } else {
                for (int i = 0; i < cantidad; i++) {
                    Localidad localidad = new Localidad();
                    System.out.print("Ingrese el nombre de la localidad: ");
                    String l1 = sc.next();
                    localidad.setNombre(l1);
                    System.out.println("Ingrese el numero de habitantes: ");
                    int nh1 = sc.nextInt();
                    localidad.setNumeroDeHabitantes(nh1);
                }
                for (int i = 1; i < cantidad; i++) {
                    
                    System.out.println("Ingrese el nombre del municipio al que pertenecen las localidades: ");
                    String m1 =sc.nextLine();
                    Municipio municipio = new Municipio(m1);
                    municipio.agregarLocalidad(null);
                }
                for (int i = 2; i < cantidad; i++) {
                    System.out.println("Ingrese el nombre de la provincia a la que pertenece ");
                    String p1 =sc.nextLine();
                    Provincia provincia1 = new Provincia(p1);
                    provincia1.agregarMunicipio(null);
                    System.out.println(provincia1);
                }
        }
       
    }
}
}   

 /*Localidad localidad1 = new Localidad();
        System.out.println("Ingrese el nombre de la localidad: ");
        String l1 = sc.nextLine();
        localidad1.setNombre(l1);
        System.out.println("Ingrese el numero de habitantes: ");
        int nh1 = sc.nextInt();
        localidad1.setNumeroDeHabitantes(nh1);

		Localidad localidad2 = new Localidad();
        System.out.println(" Ingrese el nombre de la segunda localiadad: ");
        String l2 =sc.nextLine();
		localidad2.setNombre(l2);
        System.out.println("Ingrese el numero de habitantes de la segunda localidad: ");
        int nh2 = sc.nextInt();
		localidad2.setNumeroDeHabitantes(nh2);

        System.out.println("Ingrese el nombre del municipio al que pertenecen las localidades: ");
        String m1 =sc.nextLine();
        Municipio municipio1 = new Municipio(m1);
        municipio1.agregarLocalidad(localidad1);
		municipio1.agregarLocalidad(localidad2);
       
        System.out.println("Ingrese el nombre de la provincia a la que pertenece ");
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
 {
        */