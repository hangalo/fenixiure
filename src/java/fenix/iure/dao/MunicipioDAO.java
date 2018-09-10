/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fenix.iure.dao;

import fenix.iure.modelo.Municipio;
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
 * @author Aisha Lubadika
 */
public class MunicipioDAO implements GenericoDAO<Municipio> {

    private static final String INSERT = "INSERT INTO municipio(nome_municipio, id_provincia) VALUES (?,?)";
    private static final String UPDATE = "UPDATE municipio set nome_municicpio = ? , id_provincia = ? WHERE id_municipio = ?";
    private static final String DELETE = "DELETE FROM municipio WHERE id_municipio = ?";
    private static final String SELECT_BY_ID = " SELECT m.id_municipio, m.nome_municipio, p.nome_provincia FROM municipio m INNER JOIN provincia p ON m.id_provincia = p.id_provincia WHERE id_municipio = ?";
    private static final String SELECT_ALL = " SELECT m.id_municipio, m.nome_municipio, p.nome_provincia FROM municipio m INNER JOIN provincia p ON m.id_provincia = p.id_provincia;";

    @Override
    public boolean save(Municipio municipio) {
        boolean flagControlo = false;
        PreparedStatement ps = null;
        Connection conn = null;
        if (municipio == null) {
            System.err.println("O valor oassado não pode ser nulo!");
        }
        try {
            conn = Conexao.getConnection();
            ps = conn.prepareStatement(INSERT);
         
            ps.setString(1, municipio.getNomeMunicipio());
            ps.setInt(2, municipio.getIdProvincia().getIdProvincia());

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
    public boolean update(Municipio municipio) {
        boolean flagControlo = false;
        PreparedStatement ps = null;
        Connection conn = null;
        if (municipio == null) {
            System.err.println("O valor oassado não pode ser nulo!");
        }
        try {
            conn = Conexao.getConnection();
            ps = conn.prepareStatement(UPDATE);

            ps.setString(1,municipio.getNomeMunicipio());
            ps.setInt(2, municipio.getIdProvincia().getIdProvincia());
            ps.setInt(3, municipio.getIdMunicipio());
           
            
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
    public boolean delete(Municipio municipio) {
        boolean flagControlo = false;
        PreparedStatement ps = null;
        Connection conn = null;
        if (municipio == null) {
            System.err.println("O valor passado nao pode ser nulo");
        }
        try {
            conn = Conexao.getConnection();
            ps = conn.prepareStatement(DELETE);
            ps.setInt(1, municipio.getIdMunicipio());
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
    public Municipio findById(Integer id) {
          PreparedStatement ps = null;
        Connection conn = null;
        ResultSet rs = null;
       Municipio municipio = new Municipio();
        try {
            conn = Conexao.getConnection();
            ps = conn.prepareStatement(SELECT_BY_ID);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            if (!rs.next()) {
                System.err.println("Não foi encontrado nenhum registo com o id: " + id);
            }
            popularComDados(municipio, rs);
        } catch (SQLException ex) {
            System.err.println("Erro ao ler dados: " + ex.getLocalizedMessage());
        } finally {
            Conexao.closeConnection(conn, ps, rs);
        }
        return municipio;
    }

    @Override
    public List<Municipio> findAll() {
        PreparedStatement ps = null;
        Connection conn = null;
        ResultSet rs = null;
        List<Municipio> municipios = new ArrayList<>();
        try {
            conn = Conexao.getConnection();
            ps = conn.prepareStatement(SELECT_ALL);
            rs = ps.executeQuery();
            while (rs.next()) {
                Municipio municipio = new Municipio();
                popularComDados(municipio, rs);
                municipios.add(municipio);
            }
        } catch (SQLException ex) {
            System.err.println("Erro ao ler dados: " + ex.getLocalizedMessage());
        } finally {
            Conexao.closeConnection(conn);
        }
        return municipios;
    }

    @Override
    public void popularComDados(Municipio municipio, ResultSet rs) {
         try {
            municipio.setIdMunicipio(rs.getInt("id_municipio"));
            municipio.setNomeMunicipio(rs.getString("nome_municipio"));
            Provincia provincia = new Provincia();
            provincia.setNomeProvincia(rs.getString("nome_provincia"));
            municipio.setIdProvincia(provincia);

        } catch (SQLException ex) {
            System.err.println("Erro ao carregar dados: " + ex.getLocalizedMessage());
        }
    }

}
