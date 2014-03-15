package br.com.livraria.morphia.controller;

import java.io.IOException;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import br.com.livraria.morphia.dao.UserDao;
import br.com.livraria.morphia.model.User;
import br.com.livraria.morphia.utils.AbstractMB;

@ManagedBean
@SessionScoped
public class LoginController {

	private UserDao dao;

	private User user;

	private String login;

	private String pass;

	private boolean loggedIn;

	@PostConstruct
	public void init() {
		dao = new UserDao( ConexaoController.conn.getDatastore() );
	}

	public String dologin() {
		user = dao.login( login, pass );
		if ( user == null ) {
			AbstractMB.addError( null, "Login ou Senha incorreto, tente novamente!" );
			FacesContext.getCurrentInstance().validationFailed();
			return "/login.xhtml?faces-redirect=true";
		} else {
			loggedIn = true;
			return "/views/index.xhtml?faces-redirect=true";
		}
	}

	public String logout() throws IOException {
		user = null;
		loggedIn = false;
		AbstractMB.addError( null, "Logout realizado com sucesso!" );
		return "/login.xhtml?faces-redirect=true";
	}

	public User getUser() {
		return user;
	}

	public void setUser( User user ) {
		this.user = user;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin( String login ) {
		this.login = login;
	}

	public String getPass() {
		return pass;
	}

	public void setPass( String pass ) {
		this.pass = pass;
	}

	public boolean isLoggedIn() {
		return loggedIn;
	}

	public void setLoggedIn( boolean loggedIn ) {
		this.loggedIn = loggedIn;
	}

}