/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fenix.iure.converters;

import fenix.iure.dao.EspecieProcessoDAO;
import fenix.iure.modelo.EspecieProcesso;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author Elísio Kavaimunwa
 */
@FacesConverter(value = "especieProcesoConverter", forClass = EspecieProcesso.class)
public class EspecieProcesoConverter implements Converter{

    EspecieProcessoDAO especieProcessoDAO = new EspecieProcessoDAO();
    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        Integer id = Integer.parseInt(value);
        try {
            return especieProcessoDAO.findById(id);
        } catch (Exception ex) {
            System.err.println("Erro na conversão: " + ex.getMessage());
        }
        return null;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (value != null) {
            EspecieProcesso  especieProcesso =(EspecieProcesso)value;
            return String.valueOf(especieProcesso.getIdEspecieProcesso());
        }
        return null;
    }
    
}
