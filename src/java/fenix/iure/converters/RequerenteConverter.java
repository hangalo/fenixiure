/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fenix.iure.converters;


import fenix.iure.ejbs.RequenteFacade;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import fenix.iure.entities.Requente;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
/**
 *
 * @author Elísio Kavaimunwa
 */
@FacesConverter(value = "requerenteConverter", forClass = Requente.class)
public class RequerenteConverter implements Converter{

    //RequerenteDAO requerenteDAO = new RequerenteDAO();
    RequenteFacade requenteFacade = lookupRequerenteFacade();
    
    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        Requente requente;
        if (value != null) {
            requente = (Requente)requenteFacade.find(Integer.parseInt(value));
            return requente;
        }
        return null;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (value != null) {
            return ((Requente) value).getIdRequente().toString();
        }
        return null;
    }
    
    private RequenteFacade lookupRequerenteFacade() {
         Context context = null;
        try {
            context = new InitialContext();
            return (RequenteFacade) context.lookup("java:global/fenixiure/RequenteFacade");
        } catch (NamingException ne) {
            System.out.println("Erro" + ne.getMessage());
            return null;
        }

    }
    
}
