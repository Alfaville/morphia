package br.com.livraria.morphia.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import br.com.livraria.morphia.dao.LivroDAO;
import br.com.livraria.morphia.model.Autor;
import br.com.livraria.morphia.model.Livro;
import br.com.livraria.morphia.utils.AbstractMB;
import br.com.livraria.morphia.utils.LazyObjectDataModel;

@ManagedBean
@ViewScoped
public class LivroController extends BaseController<Livro> {

	private LivroDAO dao;

	private Autor autor;

	private List<Autor> autores;

	private Integer initialDate;

	private Integer finalDate;

	@PostConstruct
	public void init() {
		setEntityCreate( new Livro() );
		setEntitySearch( new Livro() );
		setEntityUpdate( new Livro() );
		autor = new Autor();
		autores = new ArrayList<Autor>();
		dao = new LivroDAO( getConn() );
	}

	public void addLivro( ActionEvent event ) {
		this.autores.add( autor );
	}

	@Override
	public void save() {
		try {
			getEntityCreate().setAutores( autores );
			dao.save( getEntityCreate() );
			init();
			AbstractMB.addInfo( "", "Efetivado com sucesso!" );
		} catch ( Exception e ) {
			AbstractMB.addInfo( "Ocorreu um erro: ", e.getMessage() );
		}
	}

	@Override
	public void find() {
		Map<String, Object> paramentros = new HashMap<String, Object>();
		paramentros.put( "titulo", getEntitySearch().getTitulo() );
		paramentros.put( "isbn", getEntitySearch().getIsbn() );
		paramentros.put( "editora", getEntitySearch().getEditora() );
		paramentros.put( "livros.autores", autores );
		paramentros.put( "anoInicial", initialDate == 0 ? null : initialDate );
		paramentros.put( "anoFinal", finalDate == 0 ? null : finalDate );
		super.setLazyModel( new LazyObjectDataModel<Livro>( dao.findByAll( paramentros ) ) );
	}

	@Override
	public void delete() {
		try {
			dao.delete( getEntityCreate() );
			find();
			AbstractMB.addInfo( "", "Realizado com sucesso!" );
		} catch ( Exception e ) {
			AbstractMB.addError( "Correu um erro: ", e.getMessage() );
		}
	}

	@Override
	public void update() {

	}

	public Autor getAutor() {
		return autor;
	}

	public void setAutor( Autor autor ) {
		this.autor = autor;
	}

	public List<Autor> getAutores() {
		return autores;
	}

	public void setAutores( List<Autor> autores ) {
		this.autores = autores;
	}

	public Integer getInitialDate() {
		return initialDate;
	}

	public void setInitialDate( Integer initialDate ) {
		this.initialDate = initialDate;
	}

	public Integer getFinalDate() {
		return finalDate;
	}

	public void setFinalDate( Integer finalDate ) {
		this.finalDate = finalDate;
	}

}