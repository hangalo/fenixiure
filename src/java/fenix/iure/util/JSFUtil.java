
package fenix.iure.util;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

/**
 *
 * @author Elísio Kavaimunwa
 */
public class JSFUtil {
    public static void adicionarMensagemDeSucesso(String mensagem){
        FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, mensagem, mensagem);
        FacesContext contexto = FacesContext.getCurrentInstance();
        contexto.addMessage(null, msg);
    }
    
    public static void adicionarMensagemDeErro(String mensagem){
        FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, mensagem, mensagem);
        FacesContext contexto = FacesContext.getCurrentInstance();
        contexto.addMessage(null, msg);
    }
    
    // Esses métodos serão chamados de cordo os metodos do MBean
    // É necessário passar o grow no template
    //<p:growl id="msgGlobal" life="5000" sticky="true"/>
    // Para ser referenciado na propriedade update="msgGlobal" dos botões onde o metodo for chamado
}
