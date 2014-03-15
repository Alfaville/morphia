package br.com.livraria.morphia.model;

import java.io.Serializable;

import org.bson.types.ObjectId;

import com.google.code.morphia.annotations.Entity;
import com.google.code.morphia.annotations.Id;
import com.google.code.morphia.annotations.Indexed;
import com.google.code.morphia.utils.IndexDirection;

@Entity( "users" )
public class User implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7296578348897914373L;

	@Id
	private ObjectId id;

	private String nome;

	@Indexed( value = IndexDirection.ASC, name = "idxlogin", unique = true )
	private String login;

	private String senha = null;

	@Indexed( unique = true )
	private String email;

	public ObjectId getId() {
		return id;
	}

	public void setId( ObjectId id ) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome( String nome ) {
		this.nome = nome;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin( String login ) {
		this.login = login;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha( String senha ) {
		this.senha = senha;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail( String email ) {
		this.email = email;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ( ( email == null ) ? 0 : email.hashCode() );
		result = prime * result + ( ( id == null ) ? 0 : id.hashCode() );
		result = prime * result + ( ( login == null ) ? 0 : login.hashCode() );
		result = prime * result + ( ( nome == null ) ? 0 : nome.hashCode() );
		result = prime * result + ( ( senha == null ) ? 0 : senha.hashCode() );
		return result;
	}

	@Override
	public boolean equals( Object obj ) {
		if ( this == obj )
			return true;
		if ( obj == null )
			return false;
		if ( getClass() != obj.getClass() )
			return false;
		User other = (User) obj;
		if ( email == null ) {
			if ( other.email != null )
				return false;
		} else if ( !email.equals( other.email ) )
			return false;
		if ( id == null ) {
			if ( other.id != null )
				return false;
		} else if ( !id.equals( other.id ) )
			return false;
		if ( login == null ) {
			if ( other.login != null )
				return false;
		} else if ( !login.equals( other.login ) )
			return false;
		if ( nome == null ) {
			if ( other.nome != null )
				return false;
		} else if ( !nome.equals( other.nome ) )
			return false;
		if ( senha == null ) {
			if ( other.senha != null )
				return false;
		} else if ( !senha.equals( other.senha ) )
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", nome=" + nome + ", login=" + login + ", senha=" + senha + ", email=" + email + "]";
	}

}