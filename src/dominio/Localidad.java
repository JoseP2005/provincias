package dominio;

public class Localidad
{
    private String nombre;
    private String numeroDeHabitantes;
    
    public void setnombre(String nombre){
    this.nombre = nombre;
    }
    public void setnumeroDeHabitantes(String numeroDeHabitantes){
    this.numeroDeHabitantes = numeroDeHabitantes;
    }
    public String getnombre(){
        return nombre;
    }
    public String getnumeroDeHabitantes(){
        return numeroDeHabitantes;
    }
}
