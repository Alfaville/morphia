package br.com.livraria.morphia.dao;

import java.util.List;

import org.bson.types.ObjectId;

import br.com.livraria.morphia.model.Editora;

import com.google.code.morphia.Datastore;
import com.google.code.morphia.dao.BasicDAO;
import com.google.code.morphia.mapping.Mapper;
import com.google.code.morphia.query.Query;

public class EditoraDAO extends BasicDAO<Editora, ObjectId>{

	public EditoraDAO(Datastore ds) {
		super(ds);
		ensureIndexes();
	}
	
	public void saveEditora( List<Editora> editoras ) {
		getDatastore().save( editoras );
	}

	public void updateEditora( Editora editora ) {
		Query<Editora> query = createQuery().field( Mapper.ID_KEY ).equal( editora.getId() );
		getDatastore().updateFirst( query, editora, false );
	}

	public void deleteEditora( Editora editora ) {
		getDatastore().delete( editora );
	}
	
	public List<Editora> findForByName( String name ) {
		return createQuery().field( "nome" ).containsIgnoreCase( name ).asList();
	}

	public List<Editora> findAll(){
		return find().asList();
	}

}