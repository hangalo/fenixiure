/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fenix.iure.converters;

import fenix.iure.dao.MunicipioDAO;
import fenix.iure.ejbs.MunicipioFacade;
import fenix.iure.entities.Municipio;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.inject.Inject;

/**
 *
 * @author Aisha Lubadika
 */
@FacesConverter(value = "municipioConverter", forClass = Municipio.class)
public class MunicipioConverter implements Converter {
                   MunicipioDAO municipioDAO = new MunicipioDAO();
    
    @Inject
    MunicipioFacade municipioFacade;
    
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
    
}
