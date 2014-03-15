package br.com.livraria.morphia.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.livraria.morphia.dao.AutorDAO;
import br.com.livraria.morphia.model.Autor;
import br.com.livraria.morphia.utils.AbstractMB;
import br.com.livraria.morphia.utils.LazyObjectDataModel;

@ManagedBean
@ViewScoped
public class AutorController extends BaseController<Autor> {

	private AutorDAO dao;

	private List<Autor> all;

	@PostConstruct
	public void init() {
		setEntityCreate( new Autor() );
		setEntitySearch( new Autor() );
		setEntityUpdate( new Autor() );
		dao = new AutorDAO( getConn() );
		all = new ArrayList<Autor>();
	}

	public void reinit() {
		setEntityCreate( new Autor() );
	}

	@Override
	public void find() {
		String nome = getEntitySearch().getNome();
		if ( nome.trim().equals( "" ) )
			super.setLazyModel( new LazyObjectDataModel<Autor>( dao.findAll() ) );
		else
			super.setLazyModel( new LazyObjectDataModel<Autor>( dao.findForByName( nome ) ) );
	}

	@Override
	public void save() {
		try {
			dao.saveAutor( all );
			all = new ArrayList<Autor>();
			AbstractMB.addInfo( "", "Efetivado com sucesso!" );
		} catch ( Exception e ) {
			AbstractMB.addInfo( "Ocorreu um erro: ", e.getMessage() );
		}
	}

	@Override
	public void update() {
		try {
			dao.updateAutor( getEntityUpdate() );
			setEntityUpdate( new Autor() );
			AbstractMB.addInfo( "", "Efetivado com sucesso!" );
			closeTab( getTabUpdate() );
		} catch ( Exception e ) {
			AbstractMB.addInfo( "Ocorreu um erro: ", e.getMessage() );
		}
	}

	@Override
	public void delete() {
		try {
			dao.delete( getEntityCreate() );
			reinit();
			AbstractMB.addInfo( "", "Efetivado com sucesso!" );
			find();
		} catch ( Exception e ) {
			AbstractMB.addInfo( "Ocorreu um erro: ", e.getMessage() );
		}
	}

	public List<Autor> getAll() {
		return all;
	}

	public void setAll( List<Autor> all ) {
		this.all = all;
	}

	public List<Autor> autocomplete( String value ) {
		return dao.findForByName( value );
	}

}