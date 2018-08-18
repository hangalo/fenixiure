/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fenix.iure.dao;


import fenix.iure.modelo.Provincia;
import fenix.iure.util.Conexao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author informatica
 */
public class ProvinciaDAO implements GenericoDAO<Provincia> {

    private static final String INSERIR = "INSERT INT Provincia(nome_provincia) VALUES (?)";
    private static final String ACTUALIZAR = "UPDATE provincia set nome_provincia = ? WHERE id_Provincia = ?";
    private static final String ELIMINAR = "DELETE FROM Provincia WHERE id_Provincia = ?";
    private static final String BUSCAR_POR_CODIGO = "SELECT id_provincia,nome_provincia FROM provincia WHERE id_Provincia = ?";
    private static final String LISTAR_TUDO = "SELECT id_provincia,nome_provincia FROM provincia ORDER BY nome_provincia ASC;";

    @Override
    public boolean save(Provincia provincia) {
        boolean flagControlo = false;
        PreparedStatement ps = null;
        Connection conn = null;
        if (provincia == null) {
            System.err.println("O valor oassado não pode ser nulo!");
        }
        try {
            conn = Conexao.getConnection();
            ps = conn.prepareStatement(INSERIR);

            ps.setString(1, provincia.getNomeProvincia());

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
    public boolean update(Provincia provincia) {
        boolean flagControlo = false;
        PreparedStatement ps = null;
        Connection conn = null;
        if (provincia == null) {
            System.err.println("O valor passado nao pode ser nulo");
        }
        try {
            conn = Conexao.getConnection();
            ps = conn.prepareStatement(ACTUALIZAR);
            ps.setString(1, provincia.getNomeProvincia());
            ps.setInt(2, provincia.getIdProvincia());
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
    public boolean delete(Provincia provincia) {
         boolean flagControlo = false;
        PreparedStatement ps = null;
        Connection conn = null;
        if (provincia == null) {
            System.err.println("O valor passado nao pode ser nulo");
        }
        try {
            conn = Conexao.getConnection();
            ps = conn.prepareStatement(ELIMINAR);
            ps.setInt(1, provincia.getIdProvincia());
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
    public Provincia findById(Integer id) {

        PreparedStatement ps = null;
        Connection conn = null;
        ResultSet rs = null;
        Provincia provincia = new Provincia();
        try {
            conn = Conexao.getConnection();
            ps = conn.prepareStatement(BUSCAR_POR_CODIGO);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            if (!rs.next()) {
                System.err.println("Não foi encontrado nenhum registo com o id: " + id);
            }
            popularComDados(provincia, rs);
        } catch (SQLException ex) {
            System.err.println("Erro ao ler dados: " + ex.getLocalizedMessage());
        } finally {
            Conexao.closeConnection(conn, ps, rs);
        }
        return provincia;
    }

    @Override
    public List<Provincia> findAll() {
        PreparedStatement ps = null;
        Connection conn = null;
        ResultSet rs = null;
        List<Provincia> provincias = new ArrayList<>();
        try {
            conn = Conexao.getConnection();
            ps = conn.prepareStatement(LISTAR_TUDO);
            rs = ps.executeQuery();
            while (rs.next()) {
                Provincia provincia = new Provincia();
                popularComDados(provincia, rs);
                provincias.add(provincia);
            }
        } catch (SQLException ex) {
            System.err.println("Erro ao ler dados: " + ex.getLocalizedMessage());
        } finally {
            Conexao.closeConnection(conn);
        }
        return provincias;
    }

    @Override
    public void popularComDados(Provincia provincia, ResultSet rs) {

        try {
            provincia.setIdProvincia(rs.getInt("id_provincia"));
            provincia.setNomeProvincia(rs.getString("nome_provincia"));

        } catch (SQLException ex) {
            System.err.println("Erro ao carregar dados: " + ex.getLocalizedMessage());
        }

    }

}
