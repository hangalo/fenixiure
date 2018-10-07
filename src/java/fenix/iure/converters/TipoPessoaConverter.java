/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fenix.iure.converters;

import fenix.iure.dao.TipoPessoaDAO;
import fenix.iure.modelo.TipoPessoa;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author Aisha Lubadika
 */
@FacesConverter(value = "tipoPessoaConverter", forClass = TipoPessoa.class)
public class TipoPessoaConverter implements Converter {
                 TipoPessoaDAO tipoPessoaDAO = new TipoPessoaDAO();
    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
         Integer id = Integer.parseInt(value);
        try {
            return tipoPessoaDAO.findById(id);
        } catch (Exception ex) {
            System.err.println("Erro na conversão: " + ex.getMessage());
        }
        return null;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (value != null) {
            TipoPessoa tipoPessoa = (TipoPessoa) value;
            return String.valueOf(tipoPessoa.getIdTipoPessoa());
        }
        return null;
    }
    
}
