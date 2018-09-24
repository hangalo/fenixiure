/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fenix.iure.converters;

import fenix.iure.dao.TipoDecisaoDAO;
import fenix.iure.modelo.TipoDecisao;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author Elísio Kavaimunwa
 */
@FacesConverter(value = "tipoDecisaoConverter", forClass = TipoDecisao.class)
public class TipoDecisaoConverter implements Converter{

    TipoDecisaoDAO tipoDecisaoDAO = new TipoDecisaoDAO();
    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        Integer id = Integer.parseInt(value);
        try {
            return tipoDecisaoDAO.findById(id);
        } catch (Exception ex) {
            System.err.println("Erro na conversão: " + ex.getMessage());
        }
        return null;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
         if (value != null) {
            TipoDecisao  tipoDecisao =(TipoDecisao)value;
            return String.valueOf(tipoDecisao.getIdTipoDecisao());
        }
        return null;
    }
    
}
