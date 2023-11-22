package presentacion;

import dominio.Localidad;
import dominio.Municipio;
import dominio.Provincia;

import java.io.*;
import java.util.*;


public class Interfaz {

    private List<Provincia> provincias;
    private Scanner sc;

    public Interfaz() {
        provincias = new ArrayList<>();
        sc = new Scanner(System.in);
        cargarInformacionDeArchivo();
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
            BufferedWriter writer = new BufferedWriter(new FileWriter("informacion.csv"));

            for (Provincia provincia : provincias) {
                writer.write(provincia.getNombre() + "," + provincia.contarHabitantes());

                for (Municipio municipio : provincia.getMunicipios()) {
                    writer.newLine();
                    writer.write(municipio.getNombre() + "," + municipio.contarHabitantes());

                    for (Localidad localidad : municipio.getLocalidades()) {
                        writer.newLine();
                        writer.write(localidad.getNombre() + "," + localidad.getNumeroDeHabitantes());
                    }
                }
                writer.newLine();
            }

            writer.close();
            System.out.println("Información guardada en el archivo 'informacion.csv'.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private void cargarInformacionDeArchivo() {
        try {
            Scanner fileScanner = new Scanner(new File("informacion.csv"));
            Provincia provincia = null;
            Municipio municipio = null;

            while (fileScanner.hasNextLine()) {
                String linea = fileScanner.nextLine();
                String[] partes = linea.split(",");
                String nombre = partes[0];
                int habitantes = Integer.parseInt(partes[1]);

                if (partes.length == 2) {
                    provincia = new Provincia(nombre);
                    provincias.add(provincia);
                } else if (partes.length == 3) {
                    municipio = new Municipio(nombre);
                    provincia.agregarMunicipio(municipio);
                } else if (partes.length == 4) {
                    Localidad localidad = new Localidad();
                    localidad.setNombre(nombre);
                    localidad.setNumeroDeHabitantes(habitantes);
                    municipio.agregarLocalidad(localidad);
                }
            }

            fileScanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("No se encontró un archivo de datos. Se iniciará con una lista vacía.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
