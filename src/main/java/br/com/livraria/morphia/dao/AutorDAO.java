package br.com.livraria.morphia.dao;

import java.util.List;

import org.bson.types.ObjectId;

import br.com.livraria.morphia.model.Autor;

import com.google.code.morphia.Datastore;
import com.google.code.morphia.dao.BasicDAO;
import com.google.code.morphia.mapping.Mapper;
import com.google.code.morphia.query.Query;

public class AutorDAO extends BasicDAO<Autor, ObjectId> {

	public AutorDAO( Datastore ds ) {
		super( ds );
		ensureIndexes();
	}

	public void saveAutor( List<Autor> autores ) {
		getDatastore().save( autores );
	}

	public void updateAutor( Autor autor ) {
		Query<Autor> query = createQuery().field( Mapper.ID_KEY ).equal( autor.getId() );
		getDatastore().updateFirst( query, autor, false );
	}

	public List<Autor> findForByName( String name ) {
		return createQuery().field( "nome" ).containsIgnoreCase( name ).asList();
	}

	public List<Autor> findAll() {
		return find().asList();
	}

}
