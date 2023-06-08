import java.time.LocalDate;

public class BibliotecaTest {
    public static void main(String[] args) {
        Biblioteca biblioteca = new Biblioteca();

        // Crear algunos libros
        Libro libro1 = new Libro("El código Da Vinci", "Dan Brown", "L001", "Aventuras", 12);
        Libro libro2 = new Libro("1984", "George Orwell", "Ciencia Ficción", "L002", 16);
        Libro libro3 = new Libro("Romeo y Julieta", "William Shakespeare", "Romántica","L003", 14);

        // Agregar los libros a la biblioteca
        biblioteca.agregarLibro(libro1);
        biblioteca.agregarLibro(libro2);
        biblioteca.agregarLibro(libro3);

        // Crear algunos usuarios
        Usuario usuario1 = new Usuario("Juan", "Pérez", "Gómez", LocalDate.of(1990, 5, 10), "12345678A");
        Usuario usuario2 = new Usuario("María", "López", "Fernández", LocalDate.of(1995, 8, 20), "87654321B");

        // Agregar los usuarios a la biblioteca
        biblioteca.agregarUsuario(usuario1);
        biblioteca.agregarUsuario(usuario2);

        // Prestar un libro al usuario 1
        biblioteca.prestarLibro(libro1, usuario1);

        // Listar los libros disponibles
        System.out.println("Libros disponibles:");
        biblioteca.listarLibrosDisponibles();

        // Listar los libros prestados al usuario 1
        System.out.println("Libros prestados al usuario 1:");
        biblioteca.listarLibrosPrestados();

        // Devolver el libro prestado por el usuario 1
        biblioteca.devolverLibro(libro1, usuario1);

        // Listar los libros disponibles después de la devolución
        System.out.println("Libros disponibles después de la devolución:");
        biblioteca.listarLibrosDisponibles();

        // Guardar los libros y usuarios en archivos
        biblioteca.guardarLibrosEnArchivo("libros.dat");
        biblioteca.guardarUsuariosEnArchivo("usuarios.dat");

        // Cargar los libros y usuarios desde los archivos
        biblioteca.cargarLibrosDesdeArchivo("libros.dat");
        biblioteca.cargarUsuariosDesdeArchivo("usuarios.dat");

        // Listar los libros disponibles después de cargar desde los archivos
        System.out.println("Libros disponibles después de cargar desde los archivos:");
        biblioteca.listarLibrosDisponibles();

        // Listar los usuarios cargados desde los archivos
        System.out.println("Usuarios cargados desde los archivos:");
        biblioteca.listarUsuarios();
    }
}
