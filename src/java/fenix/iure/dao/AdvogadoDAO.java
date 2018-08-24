/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fenix.iure.dao;

import fenix.iure.modelo.Advogado;
import fenix.iure.modelo.Juiz;
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
public class AdvogadoDAO implements GenericoDAO<Advogado>{
    
    private static final String INSERIR ="INSERT INTO advogado(nome_advogado,sobrenome_advogado,data_nascimento_advogado,data_inicio_funcoes ) VALUES (?,?,?,?)";
    private static final String ACTUALIZAR ="UPDATE advogado set nome_advogado=?, sobrenome_advogado=?,data_nascimento_advogado=?, data_inicio_funcoes=? WHERE id_advogado=?";
    private static final String ELIMINAR ="DELETE FROM advogado WHERE id_advogado=?";
    private static final String BUSCAR_POR_CODIGO ="SELECT id_advogado,nome_advogado,sobrenome_advogado,data_nascimento_advogado, data_inicio_funcoes FROM advogado WHERE id_advogado = ?";
    private static final String LISTAR_TUDO ="SELECT id_advogado,nome_advogado,sobrenome_advogado,data_nascimento_advogado, data_inicio_funcoes FROM advogado ORDER BY nome_advogado ASC;";

    

    @Override
    public boolean save(Advogado advogado) {
        boolean flagControlo = false;
        PreparedStatement ps = null;
        Connection conn = null;
        if (advogado == null) {
            System.err.println("O valor oassado não pode ser nulo!");
        }
        try {
            conn = Conexao.getConnection();
            ps = conn.prepareStatement(INSERIR);

            ps.setString(1, advogado.getNomeAdvogado());
            ps.setString(2, advogado.getSobreNomeAdvogado());
            ps.setDate(3, new java.sql.Date(advogado.getDataNascimento().getTime()));
            ps.setDate(4, new java.sql.Date(advogado.getDataInicioFuncoes().getTime()));
            
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
    public boolean update(Advogado advogado) {
        boolean flagControlo = false;
        PreparedStatement ps = null;
        Connection conn = null;
        if (advogado == null) {
            System.err.println("O valor oassado não pode ser nulo!");
        }
        try {
            conn = Conexao.getConnection();
            ps = conn.prepareStatement(ACTUALIZAR);

            ps.setString(1, advogado.getNomeAdvogado());
            ps.setString(2, advogado.getSobreNomeAdvogado());
            ps.setDate(3, new java.sql.Date(advogado.getDataNascimento().getTime()));
            ps.setDate(4, new java.sql.Date(advogado.getDataInicioFuncoes().getTime()));
            ps.setInt(5, advogado.getIdAdvogado());
            
            int retorno = ps.executeUpdate();
            if (retorno > 0) {
                System.out.println("Dados actualizar com sucesso: " + ps.getUpdateCount());
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
    public boolean delete(Advogado advogado) {
        boolean flagControlo = false;
        PreparedStatement ps = null;
        Connection conn = null;
        if (advogado == null) {
            System.err.println("O valor passado nao pode ser nulo");
        }
        try {
            conn = Conexao.getConnection();
            ps = conn.prepareStatement(ELIMINAR);
            ps.setInt(1, advogado.getIdAdvogado());
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
    public Advogado findById(Integer id) {
        PreparedStatement ps = null;
        Connection conn = null;
        ResultSet rs = null;
        Advogado advogado = new Advogado();
        try {
            conn = Conexao.getConnection();
            ps = conn.prepareStatement(BUSCAR_POR_CODIGO);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            if (!rs.next()) {
                System.err.println("Não foi encontrado nenhum registo com o id: " + id);
            }
            popularComDados(advogado, rs);
        } catch (SQLException ex) {
            System.err.println("Erro ao ler dados: " + ex.getLocalizedMessage());
        } finally {
            Conexao.closeConnection(conn, ps, rs);
        }
        return advogado;
    }

    @Override
    public List<Advogado> findAll() {
        PreparedStatement ps = null;
        Connection conn = null;
        ResultSet rs = null;
        List<Advogado> advogados = new ArrayList<>();
        try {
            conn = Conexao.getConnection();
            ps = conn.prepareStatement(LISTAR_TUDO);
            rs = ps.executeQuery();
            while (rs.next()) {
                Advogado advogado = new Advogado();
                popularComDados(advogado, rs);
                advogados.add(advogado);
            }
        } catch (SQLException ex) {
            System.err.println("Erro ao ler dados: " + ex.getLocalizedMessage());
        } finally {
            Conexao.closeConnection(conn);
        }
        return advogados;
    }

    @Override
    public void popularComDados(Advogado advogado, ResultSet rs) {
        try {
            advogado.setIdAdvogado(rs.getInt("id_advogado"));
            advogado.setNomeAdvogado(rs.getString("nome_advogado"));
            advogado.setSobreNomeAdvogado(rs.getString("sobrenome_advogado"));
            advogado.setDataNascimento(rs.getDate("data_nascimento_advogado"));
            advogado.setDataInicioFuncoes(rs.getDate("data_inicio_funcoes"));

        } catch (SQLException ex) {
            System.err.println("Erro ao carregar dados: " + ex.getLocalizedMessage());
        }
    }
    
}
