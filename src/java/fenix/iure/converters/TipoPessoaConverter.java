/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fenix.iure.converters;

import fenix.iure.ejbs.TipoPessoaFacade;
import fenix.iure.entities.TipoPessoa;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

/**
 *
 * @author Aisha Lubadika
 */
@FacesConverter(value = "tipoPessoaConverter", forClass = TipoPessoa.class)
public class TipoPessoaConverter implements Converter {

    TipoPessoaFacade tipoPessoaFacade = lookupTipoPessoaFacade();

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {

        TipoPessoa tipoPessoa;
        if (value != null) {
            tipoPessoa = (TipoPessoa) tipoPessoaFacade.find(Integer.parseInt(value));
            return tipoPessoa;
        }
        return null;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (value != null) {
            return ((TipoPessoa) value).getIdTipo().toString();
        }
        return null;
    }

    private TipoPessoaFacade lookupTipoPessoaFacade() {
        Context context = null;
        try {
            context = new InitialContext();
            return (TipoPessoaFacade) context.lookup("java:global/fenixiure/TipoPessoaFacade");
        } catch (NamingException ne) {
            System.out.println("Erro" + ne.getMessage());
            return null;
        }

    }

}
