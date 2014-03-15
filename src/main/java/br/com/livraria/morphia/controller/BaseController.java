package br.com.livraria.morphia.controller;

import org.primefaces.component.tabview.Tab;
import org.primefaces.component.tabview.TabView;
import org.primefaces.context.RequestContext;
import org.primefaces.event.TabCloseEvent;
import org.primefaces.model.LazyDataModel;

import br.com.livraria.morphia.utils.AbstractMB;

import com.google.code.morphia.Datastore;

public abstract class BaseController<T> {

	private T entityCreate;

	private T entitySearch;

	private T entityUpdate;

	private LazyDataModel<T> lazyModel;

	private Tab tabUpdate;
	
	protected AbstractMB message;

	protected Datastore getConn() {
		return ConexaoController.conn.getDatastore();
	}

	abstract void save();
	
	abstract void delete();
	
	abstract void update();
	
	abstract void find();
	
	public void loadToEdit() {
		tabUpdate.setRendered( true );
		TabView parent = (TabView) tabUpdate.getParent();
		int editIndex = parent.getChildren().indexOf( tabUpdate );
		parent.setActiveIndex( editIndex );
	}

	public void closeTab( Tab tabClose ) {
		TabView parent = (TabView) tabClose.getParent();
		tabClose.setRendered( false );
		parent.setActiveIndex( 1 );
		RequestContext.getCurrentInstance().update( parent.getClientId() );
	}

	public void onTabClose( TabCloseEvent event ) {
		closeTab( event.getTab() );
	}

	public LazyDataModel<T> getLazyModel() {
		return lazyModel;
	}

	public void setLazyModel( LazyDataModel<T> lazyModel ) {
		this.lazyModel = lazyModel;
	}

	public T getEntityCreate() {
		return entityCreate;
	}

	public void setEntityCreate( T entityCreate ) {
		this.entityCreate = entityCreate;
	}

	public T getEntitySearch() {
		return entitySearch;
	}

	public void setEntitySearch( T entitySearch ) {
		this.entitySearch = entitySearch;
	}

	public T getEntityUpdate() {
		return entityUpdate;
	}

	public void setEntityUpdate( T entityUpdate ) {
		this.entityUpdate = entityUpdate;
	}

	public Tab getTabUpdate() {
		return tabUpdate;
	}

	public void setTabUpdate( Tab tabUpdate ) {
		this.tabUpdate = tabUpdate;
	}

	public AbstractMB getMessage() {
		return message;
	}

	public void setMessage( AbstractMB message ) {
		this.message = message;
	}

}