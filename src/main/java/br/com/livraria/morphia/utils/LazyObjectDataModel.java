package br.com.livraria.morphia.utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

public class LazyObjectDataModel<T> extends LazyDataModel<T> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7413463950059554215L;

	private List<T> datasource;

	public LazyObjectDataModel( List<T> datasource ) {
		this.datasource = datasource;
	}

	@Override
	public List<T> load( int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, String> filters ) {
		List<T> data = new ArrayList<T>();

		// filter
		for ( T item : datasource ) {
			boolean match = true;

			for ( Iterator<String> it = filters.keySet().iterator(); it.hasNext(); ) {
				try {
					String filterProperty = it.next();
					String filterValue = filters.get( filterProperty );
					String fieldValue = String.valueOf( item.getClass().getField( filterProperty ).get( item ) );

					if ( filterValue == null || fieldValue.startsWith( filterValue ) ) {
						match = true;
					} else {
						match = false;
						break;
					}
				} catch ( Exception e ) {
					match = false;
				}
			}

			if ( match ) {
				data.add( item );
			}
		}

		// sort
		if ( sortField != null ) {
			Collections.sort( data, new LazySorter( sortField, sortOrder ) );
		}

		// rowCount
		int dataSize = data.size();
		this.setRowCount( dataSize );

		// paginate
		if ( dataSize > pageSize ) {
			try {
				return data.subList( first, first + pageSize );
			} catch ( IndexOutOfBoundsException e ) {
				return data.subList( first, first + ( dataSize % pageSize ) );
			}
		} else {
			return data;
		}
	}

	@Override
	public void setRowIndex( int rowIndex ) {
		/*
		 * The following is in ancestor (LazyDataModel): this.rowIndex =
		 * rowIndex == -1 ? rowIndex : (rowIndex % pageSize);
		 */
		if ( rowIndex == -1 || getPageSize() == 0 ) {
			super.setRowIndex( -1 );
		} else
			super.setRowIndex( rowIndex % getPageSize() );
	}

}