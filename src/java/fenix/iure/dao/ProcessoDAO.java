/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fenix.iure.dao;

import fenix.iure.modelo.Advogado;
import fenix.iure.modelo.EspecieProcesso;
import fenix.iure.modelo.EstadoProcesso;
import fenix.iure.modelo.Juiz;
import fenix.iure.modelo.Processo;
import fenix.iure.modelo.Requerente;
import fenix.iure.modelo.Requerido;
import fenix.iure.modelo.TipoDecisao;
import fenix.iure.util.Conexao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Elísio Kavaimunwa
 */
public class ProcessoDAO implements GenericoDAO<Processo> {

    private static final String INSERIR = "INSERT INTO processo(numero_processo, resumo_despacho, data_entrada, data_conclusao, id_especie_processo, id_requente, id_requerido, id_advogado, id_juiz, id_estado_processo, id_tipo_decisao) VALUES(?,?,?,?,?,?,?,?,?,?,?)";
    private static final String ACTUALIZAR = "UPDATE processo SET numero_processo=?, resumo_despacho=?, data_entrada=?, data_conclusao=?, id_especie_processo=?,id_requente=?, id_requerido=?,id_advogado=?,  id_juiz=?, id_estado_processo=?, id_tipo_decisao=? WHERE id_processo=?";
    private static final String ELIMINAR = "DELETE FROM processo WHERE id_processo=?";

    private static final String BUSCAR_POR_CODIGO = "SELECT * FROM processo p "
            + "INNER JOIN especie_processo ep ON p.id_especie_processo = ep.id_especie_processo "
            + "INNER JOIN requente r ON p.id_requente = r.id_requente "
            + "INNER JOIN requerido rqd ON p.id_requerido = rqd.id_requerido "
            + "INNER JOIN advogado ad ON p.id_advogado = ad.id_advogado "
            + "INNER JOIN juiz jz ON p.id_juiz = jz.id_juiz "
            + "INNER JOIN estado_processo esp ON p.id_estado_processo = esp.id_estado_processo "
            + "INNER JOIN tipo_decisao td ON p.id_tipo_decisao = td.id_tipo_decisao "
            + "WHERE id_processo=? ORDER BY p.data_entrada ASC;";

    private static final String BUSCAR_TUDO = "SELECT * FROM processo p "
            + "INNER JOIN especie_processo ep ON p.id_especie_processo = ep.id_especie_processo "
            + "INNER JOIN requente r ON p.id_requente = r.id_requente "
            + "INNER JOIN requerido rqd ON p.id_requerido = rqd.id_requerido "
            + "INNER JOIN advogado ad ON p.id_advogado = ad.id_advogado "
            + "INNER JOIN juiz jz ON p.id_juiz = jz.id_juiz "
            + "INNER JOIN estado_processo esp ON p.id_estado_processo = esp.id_estado_processo "
            + "INNER JOIN tipo_decisao td ON p.id_tipo_decisao = td.id_tipo_decisao ORDER BY p.data_entrada ASC;";

    private static final String BUSCAR_BY_ESTADO_PROCESSO = "SELECT * FROM processo p "
            + "INNER JOIN estado_processo ep ON p.id_estado_processo = ep.id_estado_processo "
            + "INNER JOIN especie_processo esp ON p.id_especie_processo = esp.id_especie_processo "
            + "INNER JOIN juiz j ON p.id_juiz = j.id_juiz "
            + "INNER JOIN requente rt ON p.id_requente = rt.id_requente "
            + "INNER JOIN requerido rd ON p.id_requerido = rd.id_requerido "
            + "INNER JOIN advogado ad ON p.id_advogado = ad.id_advogado "
            + "INNER JOIN tipo_decisao td ON p.id_tipo_decisao = td.id_tipo_decisao "
            + "WHERE p.id_estado_processo = ?";

    private static final String BUSCAR_BY_ESPECIE_PROCESSO = "SELECT * FROM processo p "
            + "INNER JOIN estado_processo ep ON p.id_estado_processo = ep.id_estado_processo "
            + "INNER JOIN especie_processo esp ON p.id_especie_processo = esp.id_especie_processo "
            + "INNER JOIN juiz j ON p.id_juiz = j.id_juiz "
            + "INNER JOIN requente rt ON p.id_requente = rt.id_requente "
            + "INNER JOIN requerido rd ON p.id_requerido = rd.id_requerido "
            + "INNER JOIN advogado ad ON p.id_advogado = ad.id_advogado "
            + "INNER JOIN tipo_decisao td ON p.id_tipo_decisao = td.id_tipo_decisao "
            + "WHERE p.id_especie_processo = ?";

    private static final String BUSCAR_BY_JUIZ = "SELECT * FROM processo p "
            + "INNER JOIN estado_processo ep ON p.id_estado_processo = ep.id_estado_processo "
            + "INNER JOIN especie_processo esp ON p.id_especie_processo = esp.id_especie_processo "
            + "INNER JOIN juiz j ON p.id_juiz = j.id_juiz "
            + "INNER JOIN requente rt ON p.id_requente = rt.id_requente "
            + "INNER JOIN requerido rd ON p.id_requerido = rd.id_requerido "
            + "INNER JOIN advogado ad ON p.id_advogado = ad.id_advogado "
            + "INNER JOIN tipo_decisao td ON p.id_tipo_decisao = td.id_tipo_decisao "
            + "WHERE p.id_juiz = ?";

    private static final String BUSCAR_BY_ADVOGADO = "SELECT * FROM processo p "
            + "INNER JOIN estado_processo ep ON p.id_estado_processo = ep.id_estado_processo "
            + "INNER JOIN especie_processo esp ON p.id_especie_processo = esp.id_especie_processo "
            + "INNER JOIN juiz j ON p.id_juiz = j.id_juiz "
            + "INNER JOIN requente rt ON p.id_requente = rt.id_requente "
            + "INNER JOIN requerido rd ON p.id_requerido = rd.id_requerido "
            + "INNER JOIN advogado ad ON p.id_advogado = ad.id_advogado "
            + "INNER JOIN tipo_decisao td ON p.id_tipo_decisao = td.id_tipo_decisao "
            + "WHERE p.id_advogado = ?";

    private static final String BUSCAR_BY_TIPO_DECISAO = "SELECT * FROM processo p "
            + "INNER JOIN estado_processo ep ON p.id_estado_processo = ep.id_estado_processo "
            + "INNER JOIN especie_processo esp ON p.id_especie_processo = esp.id_especie_processo "
            + "INNER JOIN juiz j ON p.id_juiz = j.id_juiz "
            + "INNER JOIN requente rt ON p.id_requente = rt.id_requente "
            + "INNER JOIN requerido rd ON p.id_requerido = rd.id_requerido "
            + "INNER JOIN advogado ad ON p.id_advogado = ad.id_advogado "
            + "INNER JOIN tipo_decisao td ON p.id_tipo_decisao = td.id_tipo_decisao "
            + "WHERE p.id_requente = ?";

    private static final String BUSCAR_BY_REQUERIDO = "SELECT * FROM processo p "
            + "INNER JOIN estado_processo ep ON p.id_estado_processo = ep.id_estado_processo "
            + "INNER JOIN especie_processo esp ON p.id_especie_processo = esp.id_especie_processo "
            + "INNER JOIN juiz j ON p.id_juiz = j.id_juiz "
            + "INNER JOIN requente rt ON p.id_requente = rt.id_requente "
            + "INNER JOIN requerido rd ON p.id_requerido = rd.id_requerido "
            + "INNER JOIN advogado ad ON p.id_advogado = ad.id_advogado "
            + "INNER JOIN tipo_decisao td ON p.id_tipo_decisao = td.id_tipo_decisao "
            + "WHERE p.id_requerido = ?";

    private static final String BUSCAR_BY_REQUERENTE = "SELECT * FROM processo p "
            + "INNER JOIN estado_processo ep ON p.id_estado_processo = ep.id_estado_processo "
            + "INNER JOIN especie_processo esp ON p.id_especie_processo = esp.id_especie_processo "
            + "INNER JOIN juiz j ON p.id_juiz = j.id_juiz "
            + "INNER JOIN requente rt ON p.id_requente = rt.id_requente "
            + "INNER JOIN requerido rd ON p.id_requerido = rd.id_requerido "
            + "INNER JOIN advogado ad ON p.id_advogado = ad.id_advogado "
            + "INNER JOIN tipo_decisao td ON p.id_tipo_decisao = td.id_tipo_decisao "
            + "WHERE p.id_tipo_decisao = ?";

    private static final String BUSCAR_BY_DATA_ENTRADA = "SELECT * FROM processo p "
            + "INNER JOIN estado_processo ep ON p.id_estado_processo = ep.id_estado_processo "
            + "INNER JOIN especie_processo esp ON p.id_especie_processo = esp.id_especie_processo "
            + "INNER JOIN juiz j ON p.id_juiz = j.id_juiz "
            + "INNER JOIN requente rt ON p.id_requente = rt.id_requente "
            + "INNER JOIN requerido rd ON p.id_requerido = rd.id_requerido "
            + "INNER JOIN advogado ad ON p.id_advogado = ad.id_advogado "
            + "INNER JOIN tipo_decisao td ON p.id_tipo_decisao = td.id_tipo_decisao "
            + "WHERE p.data_entrada = ? ORDER BY p.data_entrada;";

    private static final String BUSCAR_BY_DATA_CONCLUSAO = "SELECT * FROM processo p "
            + "INNER JOIN estado_processo ep ON p.id_estado_processo = ep.id_estado_processo "
            + "INNER JOIN especie_processo esp ON p.id_especie_processo = esp.id_especie_processo "
            + "INNER JOIN juiz j ON p.id_juiz = j.id_juiz "
            + "INNER JOIN requente rt ON p.id_requente = rt.id_requente "
            + "INNER JOIN requerido rd ON p.id_requerido = rd.id_requerido "
            + "INNER JOIN advogado ad ON p.id_advogado = ad.id_advogado "
            + "INNER JOIN tipo_decisao td ON p.id_tipo_decisao = td.id_tipo_decisao "
            + "WHERE p.data_conclusao = ? ORDER BY p.data_conclusao;";

    private static final String BUSCAR_BY_INTERVALO_DATA_ENTRADA = "SELECT * FROM processo p "
            + "INNER JOIN estado_processo ep ON p.id_estado_processo = ep.id_estado_processo "
            + "INNER JOIN especie_processo esp ON p.id_especie_processo = esp.id_especie_processo "
            + "INNER JOIN juiz j ON p.id_juiz = j.id_juiz "
            + "INNER JOIN requente rt ON p.id_requente = rt.id_requente "
            + "INNER JOIN requerido rd ON p.id_requerido = rd.id_requerido "
            + "INNER JOIN advogado ad ON p.id_advogado = ad.id_advogado "
            + "INNER JOIN tipo_decisao td ON p.id_tipo_decisao = td.id_tipo_decisao "
            + "WHERE p.data_entrada BETWEEN ? AND ? ORDER BY p.data_entrada;";

    private static final String BUSCAR_BY_INTERVALO_DATA_CONCLUSAO = "SELECT * FROM processo p "
            + "INNER JOIN estado_processo ep ON p.id_estado_processo = ep.id_estado_processo "
            + "INNER JOIN especie_processo esp ON p.id_especie_processo = esp.id_especie_processo "
            + "INNER JOIN juiz j ON p.id_juiz = j.id_juiz "
            + "INNER JOIN requente rt ON p.id_requente = rt.id_requente "
            + "INNER JOIN requerido rd ON p.id_requerido = rd.id_requerido "
            + "INNER JOIN advogado ad ON p.id_advogado = ad.id_advogado "
            + "INNER JOIN tipo_decisao td ON p.id_tipo_decisao = td.id_tipo_decisao "
            + "WHERE p.data_conclusao BETWEEN ? AND ? ORDER BY p.data_conclusao;";

    @Override
    public boolean save(Processo processo) {
        boolean flagControlo = false;
        PreparedStatement ps = null;
        Connection conn = null;

        if (processo == null) {
            System.err.println("O valor oassado não pode ser nulo!");
        }
        try {
            conn = Conexao.getConnection();
            ps = conn.prepareStatement(INSERIR);

            ps.setString(1, processo.getNumeroProcesso());
            ps.setString(2, processo.getResumoDespacho());
            ps.setDate(3, new java.sql.Date(processo.getDataEntrada().getTime()));
            ps.setDate(4, new java.sql.Date(processo.getDataConclusao().getTime()));
            ps.setInt(5, processo.getEspecieProcesso().getIdEspecieProcesso());
            ps.setInt(6, processo.getRequerente().getIdRequerente());
            ps.setInt(7, processo.getRequerido().getIdRequerido());
            ps.setInt(8, processo.getAdvogado().getIdAdvogado());
            ps.setInt(9, processo.getJuiz().getIdJuiz());
            ps.setInt(10, processo.getEstadoProcesso().getIdEstadoProcesso());
            ps.setInt(11, processo.getTipoDecisao().getIdTipoDecisao());

            int retorno = ps.executeUpdate();
            if (retorno > 0) {
                System.out.println("Dados inseridos com sucesso: " + ps.getUpdateCount());
                flagControlo = true;
            }

            return flagControlo;

        } catch (SQLException e) {
            System.out.println("Erro ao inserir dados: " + e.getMessage());
            return false;
        } finally {
            Conexao.closeConnection(conn, ps);
        }
    }

    @Override
    public boolean update(Processo processo) {
        boolean flagControlo = false;
        PreparedStatement ps = null;
        Connection conn = null;

        if (processo == null) {
            System.err.println("O valor oassado não pode ser nulo!");
        }
        try {
            conn = Conexao.getConnection();
            ps = conn.prepareStatement(ACTUALIZAR);

            ps.setString(1, processo.getNumeroProcesso());
            ps.setString(2, processo.getResumoDespacho());
            ps.setDate(3, new java.sql.Date(processo.getDataEntrada().getTime()));
            ps.setDate(4, new java.sql.Date(processo.getDataConclusao().getTime()));
            ps.setInt(5, processo.getEspecieProcesso().getIdEspecieProcesso());
            ps.setInt(6, processo.getRequerente().getIdRequerente());
            ps.setInt(7, processo.getRequerido().getIdRequerido());
            ps.setInt(8, processo.getAdvogado().getIdAdvogado());
            ps.setInt(9, processo.getJuiz().getIdJuiz());
            ps.setInt(10, processo.getEstadoProcesso().getIdEstadoProcesso());
            ps.setInt(11, processo.getTipoDecisao().getIdTipoDecisao());
            ps.setInt(12, processo.getIdProcesso());

            int retorno = ps.executeUpdate();
            if (retorno > 0) {
                System.out.println("Dados inseridos com sucesso: " + ps.getUpdateCount());
                flagControlo = true;
            }

            return flagControlo;

        } catch (SQLException e) {
            System.out.println("Erro ao inserir dados: " + e.getMessage());
            return false;
        } finally {
            Conexao.closeConnection(conn, ps);
        }
    }

    @Override
    public boolean delete(Processo processo) {
        boolean flagControlo = false;
        PreparedStatement ps = null;
        Connection conn = null;
        if (processo == null) {
            System.err.println("O valor passado nao pode ser nulo");
        }
        try {
            conn = Conexao.getConnection();
            ps = conn.prepareStatement(ELIMINAR);
            ps.setInt(1, processo.getIdProcesso());
            int retorno = ps.executeUpdate();

            if (retorno > 0) {
                System.out.println("Dados eliminados com sucesso: " + ps.getUpdateCount());
                flagControlo = true;
            }

            return flagControlo;

        } catch (Exception ex) {
            System.err.println("Erro ao eliminar dados: " + ex.getLocalizedMessage());
            return false;
        } finally {
            Conexao.closeConnection(conn, ps);
            {
            }
        }
    }

    @Override
    public Processo findById(Integer id) {
        PreparedStatement ps = null;
        Connection conn = null;
        ResultSet rs = null;
        Processo processo = new Processo();
        try {
            conn = Conexao.getConnection();
            ps = conn.prepareStatement(BUSCAR_POR_CODIGO);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            if (!rs.next()) {
                System.err.println("Não foi encontrado nenhum registo com o id: " + id);
            }
            popularComDados(processo, rs);
        } catch (SQLException ex) {
            System.err.println("Erro ao ler dados: " + ex.getLocalizedMessage());
        } finally {
            Conexao.closeConnection(conn, ps, rs);
        }
        return processo;
    }

    @Override
    public List<Processo> findAll() {
        PreparedStatement ps = null;
        Connection conn = null;
        ResultSet rs = null;
        List<Processo> processos = new ArrayList<>();
        try {
            conn = Conexao.getConnection();
            ps = conn.prepareStatement(BUSCAR_TUDO);
            rs = ps.executeQuery();
            while (rs.next()) {
                Processo processo = new Processo();
                popularComDados(processo, rs);
                processos.add(processo);
            }
        } catch (SQLException ex) {
            System.err.println("Erro ao ler dados: " + ex.getLocalizedMessage());
        } finally {
            Conexao.closeConnection(conn);
        }
        return processos;
    }
    
    public List<Processo> findByEstadoProcesso( EstadoProcesso estadoProcesso) {
        PreparedStatement ps = null;
        Connection conn = null;
        ResultSet rs = null;
        if (estadoProcesso == null) {
            System.err.println("O valor passado nao pode ser nulo");
        }
        List<Processo> processos = new ArrayList<>();
        try {
            conn = Conexao.getConnection();
            ps = conn.prepareStatement(BUSCAR_BY_ESTADO_PROCESSO);
            ps.setInt(1, estadoProcesso.getIdEstadoProcesso());
            rs = ps.executeQuery();
            while (rs.next()) {
                Processo processo = new Processo();
                popularComDados(processo, rs);
                processos.add(processo);
            }
        } catch (SQLException ex) {
            System.err.println("Erro ao ler dados: " + ex.getLocalizedMessage());
        } finally {
            Conexao.closeConnection(conn);
        }
        return processos;
    }

    @Override
    public void popularComDados(Processo processo, ResultSet rs) {
        try {
            processo.setIdProcesso(rs.getInt("id_processo"));
            processo.setNumeroProcesso(rs.getString("numero_processo"));
            processo.setDataEntrada(rs.getDate("data_entrada"));
            processo.setDataConclusao(rs.getDate("data_conclusao"));
            processo.setResumoDespacho(rs.getString("resumo_despacho"));

            EspecieProcesso especieProcesso = new EspecieProcesso();
            especieProcesso.setIdEspecieProcesso(rs.getInt("id_especie_processo"));
            especieProcesso.setEspecieProcesso(rs.getString("especie_processo"));

            Requerente requerente = new Requerente();
            requerente.setIdRequerente(rs.getInt("id_requente"));
            requerente.setNomeRequerente(rs.getString("nome_requente"));
            requerente.setSobrenomeRequerente(rs.getString("sobrenome_requerente"));
            requerente.setNbiRequerente(rs.getString("n_bi_requerente"));
            requerente.setCasaRequerente(rs.getString("casa_requerente"));
            requerente.setRuaRequerente(rs.getString("rua_requerente"));
            requerente.setBairroRequerente(rs.getString("bairro_requerente"));

            Requerido requerido = new Requerido();
            requerido.setIdRequerido(rs.getInt("id_requerido"));
            requerido.setNomeRequerido(rs.getString("nome_requerido"));
            requerido.setSobrenomeRequerido(rs.getString("sobrenome_requerido"));
            requerido.setNbiRequerido(rs.getString("n_bi_requerido"));
            requerido.setCasaRequerido(rs.getString("casa_requerido"));
            requerido.setRuaRequerido(rs.getString("rua_requerido"));
            requerido.setBairroRequerdo(rs.getString("bairro_requerido"));

            Advogado advogado = new Advogado();
            advogado.setIdAdvogado(rs.getInt("id_advogado"));
            advogado.setNomeAdvogado(rs.getString("nome_advogado"));
            advogado.setSobreNomeAdvogado(rs.getString("sobrenome_advogado"));
            advogado.setDataNascimento(rs.getDate("data_nascimento_advogado"));
            advogado.setDataInicioFuncoes(rs.getDate("data_inicio_funcoes"));

            Juiz juiz = new Juiz();
            juiz.setIdJuiz(rs.getInt("id_juiz"));
            juiz.setNomeJuiz(rs.getString("nome_juiz"));
            juiz.setSobreNomeJuiz(rs.getString("sobrenome_juiz"));
            juiz.setDataNascimento(rs.getDate("data_nascimento_juiz"));
            juiz.setDataInicioFuncoes(rs.getDate("data_inicio_funcoes"));

            EstadoProcesso estadoProcesso = new EstadoProcesso();
            estadoProcesso.setIdEstadoProcesso(rs.getInt("id_estado_processo"));
            estadoProcesso.setEstadoProcesso(rs.getString("estado_processo"));

            TipoDecisao tipoDecisao = new TipoDecisao();
            tipoDecisao.setIdTipoDecisao(rs.getInt("id_tipo_decisao"));
            tipoDecisao.setTipoDecisao(rs.getString("tipo_decisao"));

            processo.setEspecieProcesso(especieProcesso);
            processo.setRequerente(requerente);
            processo.setRequerido(requerido);
            processo.setAdvogado(advogado);
            processo.setJuiz(juiz);
            processo.setEstadoProcesso(estadoProcesso);
            processo.setTipoDecisao(tipoDecisao);

        } catch (SQLException ex) {
            System.err.println("Erro ao carregar dados: " + ex.getLocalizedMessage());
        }
    }

}
