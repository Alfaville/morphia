package br.com.livraria.morphia.converter;

import java.lang.reflect.Field;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@FacesConverter( value = "autoCompleteCollectorConverter" )
public class AutoCompleteCollectorConverter implements Converter {

	@Override
	public Object getAsObject( FacesContext context, UIComponent component, String value ) {
		if ( value == null || "null".equals( value ) || value.trim().equals( "" ) || value.trim().isEmpty() )
			return null;

		return component.getAttributes().get( value );
	}

	@Override
	public String getAsString( FacesContext context, UIComponent component, Object value ) {
		Object id = null;
		for ( Field field : value.getClass().getDeclaredFields() ) {
			if ( field.getName().equals( "id" ) ) {
				try {
					field.setAccessible( true );
					id = field.get( value );
					component.getAttributes().put( id.toString(), value );
				} catch ( IllegalArgumentException e ) {
					e.printStackTrace();
				} catch ( IllegalAccessException e ) {
					e.printStackTrace();
				}
				break;
			}
		}		
		return id.toString();
	}
}