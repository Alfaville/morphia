package br.com.livraria.morphia.controller;

import javax.annotation.PostConstruct;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

import br.com.livraria.morphia.dao.MorphiaConnection;

/**
 * Controlador responsável pela inicialização<br/>
 * do banco de dados mongo.<br/>
 * 
 * @author alfaville
 * 
 */
@ManagedBean( eager = true )
@ApplicationScoped
public class ConexaoController {

	public static MorphiaConnection conn;

	@PostConstruct
	public void init() {
		conn = MorphiaConnection.getInstance();
		conn.getDatastore();
	}

}