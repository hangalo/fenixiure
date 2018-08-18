/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fenix.iure.util;

import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author informatica
 */
public class FicheiroUtil {

  public static String getPathPastaAplicacaoJSF() {
        String separador = System.getProperty("file.separator");
        String pasta ="fotos"+ separador;
        
        String raizAplicacao = FacesContext.getCurrentInstance().getExternalContext().getRealPath("/");
        return raizAplicacao + pasta;
    }

    public static String getPathLocalDisk() {
        String separador = System.getProperty("file.separator");
        String caminhoAbsoluto = "C:" + separador + "fotos_alunos" + separador;
        return caminhoAbsoluto;
    }

    public static String getPathPastaRede() {
        String separador = System.getProperty("file.separator");
        String IP = "192.168.0.18";
        String pasta = "dados";
        String subpasta = "foto";
        String pathRede = separador + separador + IP + separador + pasta + separador + subpasta + separador;
        return pathRede;
    }

    public static String getPathPastaAplicacaoServlet(HttpServletRequest request) {
        
        String separador = System.getProperty("file.separator");
        String pasta = "fotos" + separador;
        ServletContext servletContext = request.getServletContext();
        String raizAplicacao = servletContext.getRealPath("/");
        
        return raizAplicacao + pasta;
    }
    
      public static String getPathPastaRelatoriosAplicacaoJSF() {
        String separador = System.getProperty("file.separator");
        String pasta ="WEB-INF"+ separador+"relatorios"+separador;
        String raizAplicacao = FacesContext.getCurrentInstance().getExternalContext().getRealPath("/");
        return raizAplicacao + pasta;
    }
    
    
}
