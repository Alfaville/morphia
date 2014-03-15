package br.com.livraria.morphia.dao;

import java.util.List;
import java.util.Map;

import org.bson.types.ObjectId;

import br.com.livraria.morphia.model.User;
import br.com.livraria.morphia.utils.Criptografia;

import com.google.code.morphia.Datastore;
import com.google.code.morphia.dao.BasicDAO;
import com.google.code.morphia.mapping.Mapper;
import com.google.code.morphia.query.Query;

public class UserDao extends BasicDAO<User, ObjectId> {

	public UserDao( Datastore ds ) {
		super( ds );
		ensureIndexes();
	}

	public User login( String login, String senha ) {
		Query<User> query = createQuery();
		List<User> user;
		query.filter( "login =", login ).filter( "senha =", convertStringToMD5( senha ) );
		user = query.asList();

		if ( user.size() == 1 )
			return user.get( 0 );

		return null;
	}

	private String convertStringToMD5( String pass ) {
		Criptografia password = new Criptografia( pass );
		return password.toString();
	}

	public List<User> findByAll( Map<String, Object> parametros ) {
		Query<User> query = createQuery();
		if ( parametros.get( "login" ) != null && !parametros.get( "login" ).equals( "" ) )
			query.field( "login" ).containsIgnoreCase( (String) parametros.get( "login" ) );
		if ( parametros.get( "nome" ) != null && !parametros.get( "nome" ).equals( "" ) )
			query.field( "nome" ).containsIgnoreCase( (String) parametros.get( "nome" ) );
		return query.asList();
	}

	public void updateUser( User autor ) {
		Query<User> query = createQuery().field( Mapper.ID_KEY ).equal( autor.getId() );
		getDatastore().updateFirst( query, autor, false );
	}
	
}