package br.com.livraria.morphia.controller;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.livraria.morphia.dao.UserDao;
import br.com.livraria.morphia.model.User;
import br.com.livraria.morphia.utils.AbstractMB;
import br.com.livraria.morphia.utils.Criptografia;
import br.com.livraria.morphia.utils.LazyObjectDataModel;

@ManagedBean
@ViewScoped
public class UserController extends BaseController<User> {

	private UserDao dao;

	private String pass;
	
	@PostConstruct
	public void init() {
		setEntityCreate( new User() );
		setEntitySearch( new User() );
		setEntityUpdate( new User() );
		dao = new UserDao( getConn() );
	}

	@Override
	public void save() {
		try {
			String password = getEntityCreate().getSenha().trim();
			Criptografia crip = new Criptografia( password );
			getEntityCreate().setSenha( crip.toString() );
			dao.save( getEntityCreate() );
			init();
			AbstractMB.addInfo( null, "Efetivado com sucesso!" );
		} catch ( Exception e ) {
			AbstractMB.addError( "Ocorreu um erro: ", e.getMessage() );
		}
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
			Criptografia crip = new Criptografia( pass.trim() );
			getEntityUpdate().setSenha( crip.toString() );
			dao.updateUser( getEntityUpdate() );
			setEntityUpdate( new User() );
			AbstractMB.addInfo( "", "Efetivado com sucesso!" );
			closeTab( getTabUpdate() );
		} catch ( Exception e ) {
			AbstractMB.addInfo( "Ocorreu um erro: ", e.getMessage() );
		}
	}

	@Override
	public void find() {
		Map<String, Object> paramentros = new HashMap<String, Object>();
		paramentros.put( "nome", getEntitySearch().getNome() );
		paramentros.put( "login", getEntitySearch().getLogin() );
		super.setLazyModel( new LazyObjectDataModel<User>( dao.findByAll( paramentros ) ) );
	}

	public String getPass() {
		return pass;
	}

	public void setPass( String pass ) {
		this.pass = pass;
	}

}