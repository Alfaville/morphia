package br.com.livraria.morphia.model;

import java.io.Serializable;

import org.bson.types.ObjectId;

import com.google.code.morphia.annotations.Embedded;
import com.google.code.morphia.annotations.Entity;
import com.google.code.morphia.annotations.Id;
import com.google.code.morphia.annotations.Indexed;
import com.google.code.morphia.utils.IndexDirection;

@Entity("editoras")
public class Editora implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5664413675604795733L;
   
	@Id
    private ObjectId id;

    @Indexed(value = IndexDirection.ASC, name = "idxEditora", unique = true)
    private String nome;
    
    @Embedded("info")
    private ContatoInfo contatoInfo;

    public Editora(){}
    
	public ObjectId getId() {
		return id;
	}

	public void setId(ObjectId id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public ContatoInfo getContatoInfo() {
		return contatoInfo;
	}

	public void setContatoInfo(ContatoInfo contatoInfo) {
		this.contatoInfo = contatoInfo;
	}

	@Override
	public String toString() {
		return "Editora [id=" + id + ", nome=" + nome + ", contatoInfo=" + contatoInfo + "]";
	}
    
}
