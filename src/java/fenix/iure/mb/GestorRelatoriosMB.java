/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fenix.iure.mb;

import fenix.iure.util.Conexao;
import java.io.IOException;
import java.io.Serializable;
import java.sql.Connection;
import java.util.HashMap;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;

/**
 *
 * @author Elísio Kavaimunwa
 */
@Named(value = "gestorRelatoriosMB")
@RequestScoped
public class GestorRelatoriosMB implements Serializable {

    public GestorRelatoriosMB() {

    }

    public void imprimirFichaProcesso(Integer paramentro) throws IOException {
        Connection conn = null;
        conn = Conexao.getConnection();
        JasperPrint jasperPrint = null;
        try {
            String reportPath = FacesContext.getCurr­entInstance().getExt­ernalContext().getRe­alPath("/WEB-INF/relatorios/processos_por_numero.jasper");
            HashMap parametros = new HashMap();
            parametros.put("numeroProcesso", paramentro);
            System.out.println("Parametro: " + paramentro);
            jasperPrint = JasperFillManager.fillReport(reportPath, parametros, conn);
            HttpServletResponse httpServletResponse = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();

            try (ServletOutputStream servletOutputStream = httpServletResponse.getOutputStream()) {
                JasperExportManager.exportReportToPdfStream(jasperPrint, servletOutputStream);
                
                servletOutputStream.flush();
            }

        } catch (IOException | JRException ioe) {

            ioe.printStackTrace();
        }
        FacesContext.getCurrentInstance().responseComplete();
        FacesContext.getCurrentInstance().responseComplete();
    }

}
