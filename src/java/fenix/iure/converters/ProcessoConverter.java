/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fenix.iure.converters;

import fenix.iure.ejbs.ProcessoFacade;
import fenix.iure.entities.Processo;
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

@FacesConverter(value = "processoConverter", forClass = ProcessoConverter.class)
public class ProcessoConverter implements Converter{
    
    ProcessoFacade processoFacade = lookupJuizFacade();
    
    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        Processo processo;
        if (value != null) {
            processo = (Processo)processoFacade.find(Integer.parseInt(value));
            return processo;
        }
        return null;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (value != null) {
            return ((Processo) value).getIdProcesso().toString();
        }
        return null;
    }
    
    
    
    
    
    
    
    
    
    
    
    
    private ProcessoFacade lookupJuizFacade() {
        Context context = null;
        try {
            context = new InitialContext();
            return (ProcessoFacade) context.lookup("java:global/fenixiure/ProcessoFacade");
        } catch (NamingException ne) {
            System.out.println("Erro" + ne.getMessage());
            return null;
        }

    }

    
}





 