package br.com.livraria.morphia.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.bson.types.ObjectId;

import com.google.code.morphia.annotations.Entity;
import com.google.code.morphia.annotations.Id;
import com.google.code.morphia.annotations.Indexed;
import com.google.code.morphia.annotations.Property;
import com.google.code.morphia.annotations.Reference;
import com.google.code.morphia.utils.IndexDirection;

@Entity("livros")
public class Livro implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8324875194457608572L;

	@Id
	private ObjectId id;
	
	private String titulo;
	
	@Indexed(value=IndexDirection.ASC, name="idxisbn", unique=true)
	private String isbn;
	
	@Property(value="ano")
	private int anoPublicacao;
	
	@Reference("editora")
	private Editora editora;
	
	@Reference("autores")
	private List<Autor> autores = new ArrayList<Autor>();

	public ObjectId getId() {
		return id;
	}

	public void setId(ObjectId id) {
		this.id = id;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public int getAnoPublicacao() {
		return anoPublicacao;
	}

	public void setAnoPublicacao(int anoPublicacao) {
		this.anoPublicacao = anoPublicacao;
	}

	public Editora getEditora() {
		return editora;
	}

	public void setEditora(Editora editora) {
		this.editora = editora;
	}

	public List<Autor> getAutores() {
		return autores;
	}

	public void setAutores(List<Autor> autores) {
		this.autores = autores;
	}

	@Override
	public String toString() {
		return "Livro [id=" + id + ", titulo=" + titulo + ", isbn=" + isbn + ", anoPublicacao=" + anoPublicacao + ", editora=" + editora + ", autores=" + autores + "]";
	}
	
}
