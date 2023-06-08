import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Usuario {
    private String nombre;
    private String apellido1;
    private String apellido2;
    private LocalDate fechaNacimiento;
    private String dni;
    private List<Libro> librosPrestados;

    public Usuario(String nombre, String apellido1, String apellido2, LocalDate fechaNacimiento, String dni) {
        this.nombre = nombre;
        this.apellido1 = apellido1;
        this.apellido2 = apellido2;
        this.fechaNacimiento = fechaNacimiento;
        this.dni = dni;
        this.librosPrestados = new ArrayList<>();
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido1() {
        return apellido1;
    }

    public void setApellido1(String apellido1) {
        this.apellido1 = apellido1;
    }

    public String getApellido2() {
        return apellido2;
    }

    public void setApellido2(String apellido2) {
        this.apellido2 = apellido2;
    }

    public LocalDate getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(LocalDate fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public int getEdad() {
        LocalDate now = LocalDate.now();
        int edad = now.getYear() - fechaNacimiento.getYear();
        if (now.getMonthValue() < fechaNacimiento.getMonthValue()
                || (now.getMonthValue() == fechaNacimiento.getMonthValue() && now.getDayOfMonth() < fechaNacimiento.getDayOfMonth())) {
            edad--;
        }
        return edad;
    }

    public List<Libro> getLibrosPrestados() {
        return librosPrestados;
    }

    public void agregarLibroPrestado(Libro libro) {
        librosPrestados.add(libro);
    }

    public void eliminarLibroPrestado(Libro libro) {
        librosPrestados.remove(libro);
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "nombre='" + nombre + '\'' +
                ", apellido1='" + apellido1 + '\'' +
                ", apellido2='" + apellido2 + '\'' +
                ", fechaNacimiento=" + fechaNacimiento +
                ", dni='" + dni + '\'' +
                ", librosPrestados=" + librosPrestados +
                '}';
    }
    
    public String getFormatoArchivo() {
        return String.format("%s;%s;%s;%s;%s", nombre, apellido1, apellido2, fechaNacimiento, dni);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Usuario usuario = (Usuario) o;
        return dni.equals(usuario.dni);
    }

    @Override
    public int hashCode() {
        return Objects.hash(dni);
    }

    public String getNombreCompleto() {
        return nombre + " " + apellido1 + " " + apellido2;
    }
}
