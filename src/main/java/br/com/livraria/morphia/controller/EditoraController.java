package br.com.livraria.morphia.controller;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.livraria.morphia.dao.EditoraDAO;
import br.com.livraria.morphia.model.ContatoInfo;
import br.com.livraria.morphia.model.Editora;
import br.com.livraria.morphia.utils.AbstractMB;
import br.com.livraria.morphia.utils.LazyObjectDataModel;

@ManagedBean
@ViewScoped
public class EditoraController extends BaseController<Editora> {

	private EditoraDAO dao;

	@PostConstruct
	public void init() {
		initEntityCreate();
		setEntitySearch( new Editora() );
		setEntityUpdate( new Editora() );
		dao = new EditoraDAO( getConn() );
	}
	
	@Override
	public void save(){
		try {
			dao.save( getEntityCreate() );
			AbstractMB.addInfo( "", "Efetivado com sucesso." );
			initEntityCreate();
		} catch ( Exception e ) {
			AbstractMB.addError( "Ocorreu um erro: ", e.getMessage() );
		}		
	}
	
	private void initEntityCreate(){
		setEntityCreate( new Editora() );
		getEntityCreate().setContatoInfo( new ContatoInfo() );
	}
	
	@Override
	public void find() {
		String nome = getEntitySearch().getNome();
		if ( nome.trim().equals( "" ) )
			super.setLazyModel( new LazyObjectDataModel<Editora>( dao.findAll() ) );
		else
			super.setLazyModel( new LazyObjectDataModel<Editora>( dao.findForByName( nome ) ) );
	}
	
	@Override
	public void delete() {
		try {
			dao.delete( getEntityCreate() );
			AbstractMB.addInfo( "", "Efetivado com sucesso!" );
			find();
		} catch ( Exception e ) {
			AbstractMB.addInfo( "Ocorreu um erro: ", e.getMessage() );
		}
	}
	
	@Override
	public void update() {
		try {
			dao.updateEditora( getEntityUpdate() );
			setEntityUpdate( new Editora() );
			AbstractMB.addInfo( "", "Efetivado com sucesso!" );
			closeTab( getTabUpdate() );
		} catch ( Exception e ) {
			AbstractMB.addInfo( "Ocorreu um erro: ", e.getMessage() );
		}
	}

	public List<Editora> autocomplete(String value){
		return dao.findForByName( value );
	}
	
}