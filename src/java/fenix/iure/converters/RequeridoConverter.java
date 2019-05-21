/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fenix.iure.converters;

import fenix.iure.ejbs.RequeridoFacade;
import fenix.iure.entities.Requerido;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

/**
 *
 * @author Elísio Kavaimunwa
 */
@FacesConverter(value = "requeridoConverter", forClass = Requerido.class)
public class RequeridoConverter implements Converter{

            
    RequeridoFacade requeridoFacade = lookupRequeridoFacade();
    
    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        Requerido requerido;
        if (value != null) {
            requerido = (Requerido)requeridoFacade.find(Integer.parseInt(value));
            return requerido;
        }
        return null;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (value != null) {
            return ((Requerido) value).getIdRequerido().toString();
        }
        return null;
    }
    
    
    private RequeridoFacade lookupRequeridoFacade() {
         Context context = null;
        try {
            context = new InitialContext();
            return (RequeridoFacade) context.lookup("java:global/fenixiure/RequeridoFacade");
        } catch (NamingException ne) {
            System.out.println("Erro" + ne.getMessage());
            return null;
        }

    }
    
}
