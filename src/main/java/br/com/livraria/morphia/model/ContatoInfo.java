package br.com.livraria.morphia.model;

import java.io.Serializable;

public class ContatoInfo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2486768316530988690L;

	private String telefone;
	
	private String email;

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String toString() {
		return "ContatoInfo [telefone=" + telefone + ", email=" + email + "]";
	}
		
}
