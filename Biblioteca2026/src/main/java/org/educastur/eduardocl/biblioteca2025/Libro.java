
package org.educastur.eduardocl.biblioteca2025;

public class Libro {
    private String isbn;
    private String titulo;
    private String autor;
    private String genero;
    private int ejemplares;

    public Libro(String isbn, String titulo, String autor, String genero, int ejemplares) {
        this.isbn = isbn;
        this.titulo = titulo;
        this.autor = autor;
        this.genero = genero;
        this.ejemplares = ejemplares;
    }

    public String getIsbn() {
        return isbn;
    }
    public void setIsbn(String isbn) {
        this.isbn = isbn;
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
    public String getGenero() {
        return genero;
    }
    public void setGenero(String genero) {
        this.genero = genero;
    }
    
    public int getEjemplares() {
        return ejemplares;
    }
    public void setEjemplares(int ejemplares) {
        this.ejemplares = ejemplares;
    }

    @Override
    public String toString() {
        return isbn + "\t" + titulo + "\t" + autor + "\t" + genero + "\t" + ejemplares;
    }
}




