/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fenix.iure.converters;

import fenix.iure.dao.MunicipioDAO;
import fenix.iure.ejbs.MunicipioFacade;
import fenix.iure.ejbs.TipoPessoaFacade;
import fenix.iure.entities.Municipio;
import fenix.iure.entities.TipoPessoa;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.inject.Inject;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

/**
 *
 * @author Aisha Lubadika
 */
@FacesConverter(value = "municipioConverter", forClass = Municipio.class)
public class MunicipioConverter implements Converter {
                   MunicipioDAO municipioDAO = new MunicipioDAO();
    
    
    MunicipioFacade municipioFacade = lookupMunicipioFacade();
    
    /*
    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        Integer id = Integer.parseInt(value);
        try {
            return municipioFacade.find(id);
        } catch (Exception ex) {
            System.err.println("Erro na conversão: " + ex.getMessage());
        }
        return null;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
          
      if (value != null) {
            Municipio municipio = (Municipio) value;
            return String.valueOf(municipio.getIdMunicipio());
        }
        return null;
    }
    */
    
     @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        Municipio municipio;
        if (value != null) {
            municipio = (Municipio)municipioFacade.find(Integer.parseInt(value));
            return municipio;
        }
        return null;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (value != null) {
            return ((Municipio) value).getIdMunicipio().toString();
        }
        return null;
    }
    
    
    
    private MunicipioFacade lookupMunicipioFacade() {
        Context context = null;
        try {
            context = new InitialContext();
            return (MunicipioFacade) context.lookup("java:global/fenixiure/MunicipioFacade");
        } catch (NamingException ne) {
            System.out.println("Erro" + ne.getMessage());
            return null;
        }

    }
    
}
