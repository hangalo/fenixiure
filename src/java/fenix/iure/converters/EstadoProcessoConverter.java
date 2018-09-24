/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fenix.iure.converters;

import fenix.iure.dao.EstadoProcessoDAO;
import fenix.iure.modelo.EstadoProcesso;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author Elísio Kavaimunwa
 */
@FacesConverter(value = "estadoProcessoConverter", forClass = EstadoProcesso.class)
public class EstadoProcessoConverter implements Converter{

    EstadoProcessoDAO estadoProcessoDAO = new EstadoProcessoDAO();
    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        Integer id = Integer.parseInt(value);
        try {
            return estadoProcessoDAO.findById(id);
        } catch (Exception ex) {
            System.err.println("Erro na conversão: " + ex.getMessage());
        }
        return null;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (value != null) {
            EstadoProcesso  estadoProcesso =(EstadoProcesso)value;
            return String.valueOf(estadoProcesso.getIdEstadoProcesso());
        }
        return null;
    }
    
}
