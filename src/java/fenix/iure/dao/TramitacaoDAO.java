/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fenix.iure.dao;

import fenix.iure.entities.EspecieProcesso;
import fenix.iure.entities.Juiz;
import fenix.iure.entities.Processo;
import fenix.iure.entities.Requente;
import fenix.iure.entities.Requerido;
import fenix.iure.entities.TipoDecisao;
import fenix.iure.entities.Tramitacao;

import fenix.iure.util.Conexao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Elísio Kavaimunwa
 */
public class TramitacaoDAO implements GenericoDAO<Tramitacao> {

    // Variavel para consultar processos Findos
    private static final String SELECT_PROCESSO_FINDO = "SELECT MAX(data_termino) AS data_termino, t.id_tramitacao, t.data_conclusao_tramitacao, t.dispacho_tramitacao, p.numero_processo,p.id_processo, p.data_entrada, r.nome_requente, r.id_requente, re.nome_requerido,re.id_requerido, ep.especie_processo, ep.id_especie_processo, td.id_tipo_decisao, td.tipo_decisao, t.estado_processo, j.id_juiz, j.nome_juiz "
            + " FROM tramitacao t"
            + " INNER JOIN processo p ON p.id_processo = t.id_processo "
            + " INNER JOIN requente r ON p.id_requente = r.id_requente "
            + " INNER JOIN requerido re ON p.id_requerido = re.id_requerido "
            + " INNER JOIN tipo_decisao td ON t.id_tipo_decisao = td.id_tipo_decisao "
            + " INNER JOIN juiz j ON j.id_juiz = p.id_juiz "
            + " INNER JOIN especie_processo ep ON p.id_especie_processo = ep.id_especie_processo "
            + " WHERE t.estado_processo = 'FINDO'"
            + " GROUP BY p.numero_processo ORDER BY t.data_termino DESC;";

    private static final String SELECT_PROCESSO_FINDO_POR_DATAS = "SELECT MAX(data_termino) AS data_termino, t.id_tramitacao, t.data_conclusao_tramitacao, t.dispacho_tramitacao, p.numero_processo,p.id_processo, p.data_entrada, r.nome_requente, r.id_requente, re.nome_requerido,re.id_requerido, ep.especie_processo, ep.id_especie_processo, td.id_tipo_decisao, td.tipo_decisao, t.estado_processo, j.id_juiz, j.nome_juiz "
            + " FROM tramitacao t "
            + " INNER JOIN processo p ON p.id_processo = t.id_processo "
            + " INNER JOIN requente r ON p.id_requente = r.id_requente "
            + " INNER JOIN requerido re ON p.id_requerido = re.id_requerido "
            + " INNER JOIN tipo_decisao td ON t.id_tipo_decisao = td.id_tipo_decisao "
            + " INNER JOIN juiz j ON j.id_juiz = p.id_juiz "
            + " INNER JOIN especie_processo ep ON p.id_especie_processo = ep.id_especie_processo "
            
            + " WHERE t.estado_processo = 'FINDO' AND p.data_entrada >= ? AND p.data_entrada <= ? "
            + " GROUP BY p.numero_processo ORDER BY t.data_termino DESC;";

    @Override
    public void save(Tramitacao t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void update(Tramitacao t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete(Tramitacao t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Tramitacao findById(Integer id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Tramitacao> findAll() {

        return null;
    }

    public List<Tramitacao> buscarProcessosFindos() {
        // Buscar a lista de processos Findos

        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Tramitacao> tramitacaos = new ArrayList<>();
        try {
            conn = (Connection) Conexao.getConnection();
            ps = conn.prepareStatement(SELECT_PROCESSO_FINDO);
            rs = ps.executeQuery();
            while (rs.next()) {
                Tramitacao tramitacao = new Tramitacao();
                popularComDados(tramitacao, rs);
                tramitacaos.add(tramitacao);
            }
        } catch (SQLException ex) {
            System.err.println("Erro ao ler os dados: " + ex.getLocalizedMessage() + ""
                    + "" + ex.getLocalizedMessage());
        } finally {
            Conexao.closeConnection((java.sql.Connection) conn);
        }

        return tramitacaos;
    }

    // Buscar a lista de processos Findos por datas
    public List<Tramitacao> buscarProcessosFindosPorDatas(String dataInicio, String dataFim) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Tramitacao> tramitacaos = new ArrayList<>();
        try {
            conn = (Connection) Conexao.getConnection();
            ps = conn.prepareStatement(SELECT_PROCESSO_FINDO_POR_DATAS);
            ps.setString(1, dataInicio);
            ps.setString(2, dataFim);
            rs = ps.executeQuery();
            while (rs.next()) {
                Tramitacao tramitacao = new Tramitacao();
                popularComDados(tramitacao, rs);
                tramitacaos.add(tramitacao);
            }
        } catch (SQLException ex) {
            System.err.println("Erro ao ler os dados: " + ex.getLocalizedMessage() + ""
                    + "" + ex.getLocalizedMessage());
        } finally {
            Conexao.closeConnection((java.sql.Connection) conn);
        }
        return tramitacaos;
    }

    // Popular os dados para a busca dos processos findos
    public void popularComDados(Tramitacao tramitacao, ResultSet rs) {
        try {
            tramitacao.setIdTramitacao(rs.getInt("id_tramitacao"));
            tramitacao.setDataConclusaoTramitacao(rs.getDate("data_conclusao_tramitacao"));
            tramitacao.setDataTermino(rs.getDate("data_termino"));// Analizar essa linha ...........................
            tramitacao.setEstadoProcesso(rs.getString("estado_processo"));
            tramitacao.setDispachoTramitacao(rs.getString("dispacho_tramitacao"));

            TipoDecisao tipoDecisao = new TipoDecisao();
            tipoDecisao.setIdTipoDecisao(rs.getInt("id_tipo_decisao"));
            tipoDecisao.setTipoDecisao(rs.getString("tipo_decisao"));
            tramitacao.setIdTipoDecisao(tipoDecisao);

            Processo processo = new Processo();
            processo.setIdProcesso(rs.getInt("id_processo"));
            processo.setNumeroProcesso(rs.getString("numero_processo"));
            processo.setDataEntrada(rs.getDate("data_entrada"));

            EspecieProcesso especieProcesso = new EspecieProcesso();
            especieProcesso.setIdEspecieProcesso(rs.getInt("id_especie_processo"));
            especieProcesso.setEspecieProcesso(rs.getString("especie_processo"));
            processo.setIdEspecieProcesso(especieProcesso);

            Requente requente = new Requente();
            requente.setIdRequente(rs.getInt("id_requente"));
            requente.setNomeRequente(rs.getString("nome_requente"));
            processo.setIdRequente(requente);

            Requerido requerido = new Requerido();
            requerido.setIdRequerido(rs.getInt("id_requerido"));
            requerido.setNomeRequerido(rs.getString("nome_requerido"));
            processo.setIdRequerido(requerido);

            Juiz juiz = new Juiz();
            juiz.setIdJuiz(rs.getInt("id_juiz"));
            juiz.setNomeJuiz(rs.getString("nome_juiz"));
            processo.setIdJuiz(juiz);

            tramitacao.setIdProcesso(processo);

        } catch (SQLException e) {
            System.out.println("Erro ao carregar dados " + e.getMessage());
        }
    }
}
