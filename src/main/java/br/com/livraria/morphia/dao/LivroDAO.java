package br.com.livraria.morphia.dao;

import java.util.List;
import java.util.Map;

import org.bson.types.ObjectId;

import br.com.livraria.morphia.model.Autor;
import br.com.livraria.morphia.model.Livro;

import com.google.code.morphia.Datastore;
import com.google.code.morphia.dao.BasicDAO;
import com.google.code.morphia.mapping.Mapper;
import com.google.code.morphia.query.Query;
import com.google.code.morphia.query.UpdateOperations;

public class LivroDAO extends BasicDAO<Livro, ObjectId> {

	public LivroDAO( Datastore ds ) {
		super( ds );
		ensureIndexes();
	}

	public void saveLivro( Livro livro ) {
		getDatastore().save( livro );
	}

	public void updateLivro( Livro livro ) {
		Query<Livro> query = createQuery().field( Mapper.ID_KEY ).equal( livro.getId() );
		getDatastore().updateFirst( query, livro, false );
	}

	public void deleteLivro( Livro livro, Autor autor ) {
		Query<Livro> query = createQuery().field( Mapper.ID_KEY ).equal( livro.getId() );
		query.and( query.criteria( "autores" ).equal( autor ) );
		UpdateOperations<Livro> updateOperations = createUpdateOperations().removeAll( "autores", autor );
		update( query, updateOperations );
	}

	@SuppressWarnings( "unchecked" )
	public List<Livro> findByAll( Map<String, Object> parametros ) {
		Query<Livro> query = createQuery();
		if ( parametros.get( "titulo" ) != null && !parametros.get( "titulo" ).equals( "" ) )
			query.field( "titulo" ).containsIgnoreCase( (String) parametros.get( "titulo" ) );
		if ( parametros.get( "isbn" ) != null && !parametros.get( "isbn" ).equals( "" ) )
			query.field( "isbn" ).containsIgnoreCase( (String) parametros.get( "isbn" ) );
		if ( parametros.get( "editora" ) != null )
			query.filter( "editora =", parametros.get( "editora" ) );
		if ( parametros.get( "livros.autores" ) != null && !( (List<Autor>) parametros.get( "livros.autores" ) ).isEmpty() )
			query.filter( "livros.autores in", parametros.get( "autorList" ) );
		if ( parametros.get( "anoInicial" ) == null && parametros.get( "anoFinal" ) != null )
			query.filter( "ano =", parametros.get( "anoFinal" ) );
		if ( parametros.get( "anoInicial" ) != null && parametros.get( "anoFinal" ) == null )
			query.filter( "ano =", parametros.get( "anoInicial" ) );
		if ( parametros.get( "anoInicial" ) != null && parametros.get( "anoFinal" ) != null )
			query.filter( "ano >=", parametros.get( "anoInicial" ) ).filter( "ano <=", parametros.get( "anoFinal" ) );
		return query.asList();
	}

}