/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fenix.iure.testes;
import fenix.iure.dao.TramitacaoDAO;
import fenix.iure.entities.Tramitacao;
import fenix.iure.util.DateUtil;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Elísio Kavaimunwa
 */
public class Testes {
    public static void main(String[] args) {
        String dataInicio = "2019/07/09";
        String dataFim = "2019/07/24";
        Integer idDecisao = 4;

        TramitacaoDAO tramitacaoDAO = new TramitacaoDAO();
        List<Tramitacao> tramitacaos = null;
       
        tramitacaos = tramitacaoDAO.buscarProcessosFindosPorDatasDecisão(DateUtil.strToDate(dataInicio), DateUtil.strToDate(dataFim),idDecisao);
        for (Tramitacao tramitacao : tramitacaos) {
            
            System.out.println("Numero: " + tramitacao.getIdProcesso().getNumeroProcesso());
            System.out.println("Data Entrada: " + tramitacao.getIdProcesso().getDataEntrada());
            System.out.println("------------------------");
        
            System.out.println("Data Término: "+ tramitacao.getDataTermino());
            System.out.println("Decisão: " + tramitacao.getIdTipoDecisao().getTipoDecisao());
            System.out.println("Autor: " + tramitacao.getIdProcesso().getIdRequente().getNomeRequente());
            System.out.println("Réu: " + tramitacao.getIdProcesso().getIdRequerido().getNomeRequerido());
            System.out.println("Juíz: " + tramitacao.getIdProcesso().getIdJuiz().getNomeJuiz());
            System.out.println("Especie: " + tramitacao.getIdProcesso().getIdEspecieProcesso().getIdEspecieProcesso());
            
            System.out.println("\n");
        }
        System.out.println("Size: " + tramitacaos.size());
        
    }
}
