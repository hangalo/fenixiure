/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fenix.iure.converters;

import fenix.iure.dao.RequeridoDAO;
import fenix.iure.modelo.Requerido;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author Elísio Kavaimunwa
 */
@FacesConverter(value = "requeridoConverter", forClass = Requerido.class)
public class RequeridoConverter implements Converter{

    RequeridoDAO requeridoDAO = new RequeridoDAO();
    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        Integer id = Integer.parseInt(value);
        try {
            return requeridoDAO.findById(id);
        } catch (Exception ex) {
            System.err.println("Erro na conversão: " + ex.getMessage());
        }
        return null;

    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (value != null) {
            Requerido  requerido =(Requerido)value;
            return String.valueOf(requerido.getIdRequerido());
        }
        return null;

    }
    
}
