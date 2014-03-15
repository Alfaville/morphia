package br.com.livraria.morphia.utils;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.primefaces.context.RequestContext;

public class AbstractMB {

	public static void addInfo( String title, String msg ) {
		FacesContext.getCurrentInstance().addMessage( null, new FacesMessage( FacesMessage.SEVERITY_INFO, title, msg ) );
	}

	public static void addWarn( String title, String msg ) {
		FacesContext.getCurrentInstance().addMessage( null, new FacesMessage( FacesMessage.SEVERITY_WARN, title, msg ) );
	}

	public static void addError( String title, String msg ) {
		FacesContext.getCurrentInstance().addMessage( null, new FacesMessage( FacesMessage.SEVERITY_ERROR, title, msg ) );
	}

	public static void addFatal( String title, String msg ) {
		FacesContext.getCurrentInstance().addMessage( null, new FacesMessage( FacesMessage.SEVERITY_FATAL, title, msg ) );
	}
	
	public static RequestContext context(){
		return RequestContext.getCurrentInstance();
	}
	
}
