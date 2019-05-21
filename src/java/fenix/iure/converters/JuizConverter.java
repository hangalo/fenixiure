/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fenix.iure.converters;


import fenix.iure.ejbs.JuizFacade;
import fenix.iure.entities.Juiz;
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

@FacesConverter(value = "juizConverter", forClass = Juiz.class)
public class JuizConverter implements Converter{

    JuizFacade juizFacade = lookupJuizFacade();
    
    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        Juiz juiz;
        if (value != null) {
            juiz = (Juiz)juizFacade.find(Integer.parseInt(value));
            return juiz;
        }
        return null;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (value != null) {
            return ((Juiz) value).getIdJuiz().toString();
        }
        return null;
    }
    
     private JuizFacade lookupJuizFacade() {
        Context context = null;
        try {
            context = new InitialContext();
            return (JuizFacade) context.lookup("java:global/fenixiure/JuizFacade");
        } catch (NamingException ne) {
            System.out.println("Erro" + ne.getMessage());
            return null;
        }

    }
    
    
}
