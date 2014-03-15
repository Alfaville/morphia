package br.com.livraria.morphia.controller;

import java.util.ResourceBundle;

import javax.annotation.PostConstruct;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

@ManagedBean
@ApplicationScoped
public class BundleController {

	private ResourceBundle bundle;

	@PostConstruct
	public void init() {
		this.bundle = ResourceBundle.getBundle( "widgets", FacesContext.getCurrentInstance().getViewRoot().getLocale() );
	}

	public String getMessage( String chave ) {
		String message = null;
		try {
			message = bundle.getString( chave );
		} catch ( Exception e ) {
			return "?? chave " + chave + " inexistente ??";
		}
		return message;
	}

}
