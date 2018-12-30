/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fenix.iure.converters;

import fenix.iure.dao.AdvogadoDAO;
import fenix.iure.ejbs.AdvogadoFacade;
import fenix.iure.entities.Advogado;
//import fenix.iure.modelo.Advogado;
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
@FacesConverter(value = "advogadoConverter", forClass = Advogado.class)
public class AdvogadoConverter implements Converter{

   // AdvogadoDAO advogadoDAO = new AdvogadoDAO();
    
    
    
    
     AdvogadoFacade advogadoFacade = lookupAdvogadoFacade();

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        Advogado advogado;
        if (value != null) {
            advogado = (Advogado)advogadoFacade.find(Integer.parseInt(value));
            return advogado;
        }
        return null;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (value != null) {
            return ((Advogado) value).getIdAdvogado().toString();
        }
        return null;
    }
    
    /*
    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        Integer id = Integer.parseInt(value);
        try {
            return advogadoDAO.findById(id);
        } catch (Exception ex) {
            System.err.println("Erro na conversão: " + ex.getMessage());
        }
        return null;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (value != null) {
            Advogado  advogado =(Advogado)value;
            return String.valueOf(advogado.getIdAdvogado());
        }
        return null;
    }
    */
    
     private AdvogadoFacade lookupAdvogadoFacade() {
        Context context = null;
        try {
            context = new InitialContext();
            return (AdvogadoFacade) context.lookup("java:global/fenixiure/AdvogadoFacade");
        } catch (NamingException ne) {
            System.out.println("Erro" + ne.getMessage());
            return null;
        }

    }
    
    
    
    
}
