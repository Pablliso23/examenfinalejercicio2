import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Biblioteca {
    private List<Libro> libros;
    private List<Usuario> usuarios;

    public Biblioteca() {
        libros = new ArrayList<>();
        usuarios = new ArrayList<>();
    }

    public void agregarLibro(Libro libro) {
        libros.add(libro);
    }

    public void eliminarLibro(Libro libro) {
        libros.remove(libro);
    }

    public void agregarUsuario(Usuario usuario) {
        usuarios.add(usuario);
    }

    public void eliminarUsuario(Usuario usuario) {
        usuarios.remove(usuario);
    }

    public void prestarLibro(Libro libro, Usuario usuario) {
        if (libro.isPrestado()) {
            System.out.println("El libro ya está prestado.");
        } else if (libro.getEdadRecomendada() > usuario.getEdad()) {
            System.out.println("El usuario no cumple con la edad recomendada para este libro.");
        } else {
            libro.setPrestado(true);
            libro.setUsuario(usuario);
            usuario.agregarLibroPrestado(libro);
            System.out.println("El libro \"" + libro.getTitulo() + "\" ha sido prestado a " + usuario.getNombre());
        }
    }

    public void devolverLibro(Libro libro, Usuario usuario) {
        if (!libro.isPrestado() || libro.getUsuario() == null || !libro.getUsuario().equals(usuario)) {
            System.out.println("El libro no está prestado por este usuario.");
        } else {
            libro.setPrestado(false);
            libro.setUsuario(null);
            usuario.eliminarLibroPrestado(libro);
            System.out.println("El libro \"" + libro.getTitulo() + "\" ha sido devuelto por " + usuario.getNombre());
        }
    }

    public List<Libro> listarLibrosPorTitulo(String titulo) {
        List<Libro> librosEncontrados = new ArrayList<>();
        for (Libro libro : libros) {
            if (libro.getTitulo().equalsIgnoreCase(titulo)) {
                librosEncontrados.add(libro);
            }
        }
        return librosEncontrados;
    }

    public List<Libro> listarLibrosPorCategoria(String categoria) {
        List<Libro> librosEncontrados = new ArrayList<>();
        for (Libro libro : libros) {
            if (libro.getCategoria().equalsIgnoreCase(categoria)) {
                librosEncontrados.add(libro);
            }
        }
        return librosEncontrados;
    }

    public List<Libro> listarLibrosPrestados() {
        List<Libro> librosPrestados = new ArrayList<>();
        for (Libro libro : libros) {
            if (libro.isPrestado()) {
                librosPrestados.add(libro);
            }
        }
        return librosPrestados;
    }

    public List<Libro> listarLibrosDisponibles() {
        List<Libro> librosDisponibles = new ArrayList<>();
        for (Libro libro : libros) {
            if (!libro.isPrestado()) {
                librosDisponibles.add(libro);
            }
        }
        return librosDisponibles;
    }

    public List<Libro> listarLibrosDeUsuario(Usuario usuario) {
        List<Libro> librosDeUsuario = new ArrayList<>();
        for (Libro libro : libros) {
            if (libro.getUsuario() != null && libro.getUsuario().equals(usuario)) {
                librosDeUsuario.add(libro);
            }
        }
        return librosDeUsuario;
    }
    public void listarLibrosPrestadosPorUsuario(Usuario usuario) {
        System.out.println("Libros prestados al usuario: " + usuario.getNombreCompleto());
        for (Libro libro : libros) {
            if (libro.isPrestado() && libro.getUsuario().equals(usuario)) {
                System.out.println(libro);
            }
        }
    }
    
    public void guardarLibrosEnArchivo(String nombreArchivo) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(nombreArchivo))) {
            for (Libro libro : libros) {
                writer.println(libro.getFormatoArchivo());
            }
        } catch (IOException e) {
            System.out.println("Error al guardar los libros en el archivo: " + e.getMessage());
        }
    }
    
    public void cargarLibrosDesdeArchivo(String nombreArchivo) {
        try (BufferedReader reader = new BufferedReader(new FileReader(nombreArchivo))) {
            String linea;
            while ((linea = reader.readLine()) != null) {
                String[] partes = linea.split(";");
                if (partes.length == 5) {
                    String identificador = partes[0];
                    String titulo = partes[1];
                    String autor = partes[2];
                    String categoria = partes[3];
                    int edadRecomendada = Integer.parseInt(partes[4]);
                    Libro libro = new Libro(identificador, titulo, autor, categoria, edadRecomendada);
                    libros.add(libro);
                }
            }
        } catch (IOException e) {
            System.out.println("Error al cargar los libros desde el archivo: " + e.getMessage());
        }
    }
    
    public void guardarUsuariosEnArchivo(String nombreArchivo) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(nombreArchivo))) {
            for (Usuario usuario : usuarios) {
                writer.println(usuario.getFormatoArchivo());
            }
        } catch (IOException e) {
            System.out.println("Error al guardar los usuarios en el archivo: " + e.getMessage());
        }
    }
    
    public void cargarUsuariosDesdeArchivo(String nombreArchivo) {
        try (BufferedReader reader = new BufferedReader(new FileReader(nombreArchivo))) {
            String linea;
            while ((linea = reader.readLine()) != null) {
                String[] partes = linea.split(";");
                if (partes.length == 5) {
                    String nombre = partes[0];
                    String apellido1 = partes[1];
                    String apellido2 = partes[2];
                    LocalDate fechaNacimiento = LocalDate.parse(partes[3]);
                    String dni = partes[4];
                    Usuario usuario = new Usuario(nombre, apellido1, apellido2, fechaNacimiento, dni);
                    usuarios.add(usuario);
                }
            }
        } catch (IOException e) {
            System.out.println("Error al cargar los usuarios desde el archivo: " + e.getMessage());
        }
    }
    
    public void listarUsuarios() {
        System.out.println("Usuarios en la biblioteca:");
        for (Usuario usuario : usuarios) {
            System.out.println(usuario.getNombreCompleto());
        }
    }

    public void guardarEstadoBiblioteca(String nombreArchivo) {
        try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(nombreArchivo))) {
            outputStream.writeObject(libros);
            outputStream.writeObject(usuarios);
            System.out.println("Estado de la biblioteca guardado en el archivo: " + nombreArchivo);
        } catch (IOException e) {
            System.out.println("Error al guardar el estado de la biblioteca en el archivo: " + nombreArchivo);
        }
    }

    public void cargarEstadoBiblioteca(String nombreArchivo) {
        try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(nombreArchivo))) {
            libros = (List<Libro>) inputStream.readObject();
            usuarios = (List<Usuario>) inputStream.readObject();
            System.out.println("Estado de la biblioteca cargado desde el archivo: " + nombreArchivo);
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error al cargar el estado de la biblioteca desde el archivo: " + nombreArchivo);
        }
    }
}
