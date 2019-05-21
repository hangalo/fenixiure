/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fenix.iure.converters;


import fenix.iure.ejbs.EspecieProcessoFacade;
import fenix.iure.entities.EspecieProcesso;
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
@FacesConverter(value = "especieProcesoConverter", forClass = EspecieProcesso.class)
public class EspecieProcesoConverter implements Converter{

    //EspecieProcessoDAO especieProcessoDAO = new EspecieProcessoDAO();
    EspecieProcessoFacade especieProcessoFacade = lookupEspecieProcessoFacade();
    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        EspecieProcesso especieProcesso;
        if (value != null) {
            especieProcesso = (EspecieProcesso)especieProcessoFacade.find(Integer.parseInt(value));
            return especieProcesso;
        }
        return null;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (value != null) {
            return ((EspecieProcesso) value).getIdEspecieProcesso().toString();
        }
        return null;
    }
    private EspecieProcessoFacade lookupEspecieProcessoFacade() {
         Context context = null;
        try {
            context = new InitialContext();
            return (EspecieProcessoFacade) context.lookup("java:global/fenixiure/EspecieProcessoFacade");
        } catch (NamingException ne) {
            System.out.println("Erro" + ne.getMessage());
            return null;
        }

    }
    
}
