package br.com.livraria.morphia.utils;

import java.util.Comparator;

import org.primefaces.model.SortOrder;

public class LazySorter implements Comparator<Object> {

    private String sortField;
   
    private SortOrder sortOrder;
   
    public LazySorter(String sortField, SortOrder sortOrder) {
        this.sortField = sortField;
        this.sortOrder = sortOrder;
    }

    @SuppressWarnings( { "unchecked", "rawtypes" } )
	public int compare(Object obj1, Object obj2) {
        try {
            Object value1 = Object.class.getField(this.sortField).get(obj1);
            Object value2 = Object.class.getField(this.sortField).get(obj2);

            int value = ((Comparable)value1).compareTo(value2);
           
            return SortOrder.ASCENDING.equals(sortOrder) ? value : -1 * value;
        }
        catch(Exception e) {
            throw new RuntimeException();
        }
    }
}