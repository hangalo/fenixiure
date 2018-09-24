/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fenix.iure.converters;

import fenix.iure.dao.JuizDAO;
import fenix.iure.modelo.Juiz;
import fenix.iure.modelo.Requerente;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author Elísio Kavaimunwa
 */

@FacesConverter(value = "juizConverter", forClass = Juiz.class)
public class JuizConverter implements Converter{

    JuizDAO juizDAO = new JuizDAO();
    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        Integer id = Integer.parseInt(value);
        try {
            return juizDAO.findById(id);
        } catch (Exception ex) {
            System.err.println("Erro na conversão: " + ex.getMessage());
        }
        return null;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (value != null) {
            Juiz  juiz =(Juiz)value;
            return String.valueOf(juiz.getIdJuiz());
        }
        return null;
    }
    
}
