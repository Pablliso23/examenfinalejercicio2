import java.util.Objects;
public class Libro {
    private String titulo;
    private String autor;
    private String identificador;
    private String categoria;
    private int edadRecomendada;
    private boolean prestado;
    private Usuario usuario;

    public Libro(String titulo, String autor, String identificador, String categoria, int edadRecomendada) {
        this.titulo = titulo;
        this.autor = autor;
        this.identificador = identificador;
        this.categoria = categoria;
        this.edadRecomendada = edadRecomendada;
        this.prestado = false;
        this.usuario = null;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getIdentificador() {
        return identificador;
    }

    public void setIdentificador(String identificador) {
        this.identificador = identificador;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public int getEdadRecomendada() {
        return edadRecomendada;
    }

    public void setEdadRecomendada(int edadRecomendada) {
        this.edadRecomendada = edadRecomendada;
    }

    public boolean isPrestado() {
        return prestado;
    }

    public void setPrestado(boolean prestado) {
        this.prestado = prestado;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
    
    public String getFormatoArchivo() {
        return String.format("%s;%s;%s;%s;%d", identificador, titulo, autor, categoria, edadRecomendada);
    }
    @Override
    public String toString() {
        return "Libro{" +
                "titulo='" + titulo + '\'' +
                ", autor='" + autor + '\'' +
                ", identificador='" + identificador + '\'' +
                ", categoria='" + categoria + '\'' +
                ", edadRecomendada=" + edadRecomendada +
                ", prestado=" + prestado +
                ", usuario=" + usuario +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Libro libro = (Libro) o;
        return identificador.equals(libro.identificador);
    }

    @Override
    public int hashCode() {
        return Objects.hash(identificador);
    }
}
