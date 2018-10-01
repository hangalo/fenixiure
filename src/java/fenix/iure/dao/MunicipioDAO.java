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
 * @author informatica
 */
public class MunicipioDAO implements GenericoDAO<Municipio> {

    private static final String INSERT = "INSERT INTO Municipio (nome_municipio, id_provincia)VALUES(?, ?)";
    private static final String UPDATE = "UPDATE Municipio SET nome_municipio = ?, id_provincia = ? WHERE id_municipio = ?";
    private static final String DELETE = "DELETE FROM Municipio WHERE id_municipio = ?";

    private static final String SELECT_ALL = "SELECT m.id_municipio, m.nome_municipio, p.nome_provincia FROM municipio m INNER JOIN provincia p on p.id_provincia = m.id_provincia ORDER BY nome_municipio ASC;";
    private static final String SELECT_BY_ID = "SELECT m.id_municipio, m.nome_municipio, p.nome_provincia FROM municipio m INNER JOIN provincia p on p.id_provincia = m.id_provincia WHERE id_municipio = ?";

    private static final String SELECT_BY_ID_PROVINCIA = "SELECT m.id_municipio, m.nome_municipio, p.nome_provincia FROM municipio m INNER JOIN provincia p on p.id_provincia = m.id_provincia WHERE m.id_provincia = ?";

    /*@Override
    public void save(Municipio municipio) {
        
    }
    
    @Override
    public void update(Municipio municipio) {
        
    }
    
    @Override
    public void delete(Municipio municipio) {
       
    }
    
    public Municipio findById(int id) {
        
    }
    
    
    
    
    @Override
    public List<Municipio> findAll() {
        
    }
    
    @Override
    public void popularComDados(Municipio municipio, ResultSet rs) {
        
       
    }
    
    @Override
    public Municipio findById(Integer id){
        
        PreparedStatement ps = null;
        Connection conn = null;
        ResultSet rs = null;
        Municipio municipio = new Municipio();
        try {
            conn = (Connection) Conexao.getConnection();
            ps = conn.prepareStatement(SELECT_BY_ID);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            if (!rs.next()) {
                System.err.println("Não foi encontrado nenhum registo com o id: " + id);
            }
            popularComDados(municipio, rs);
        } catch (SQLException ex) {
            System.err.println("Erro ao ler dados metodo findById: " + ex.getLocalizedMessage());
        } finally {
            Conexao.closeConnection(conn, ps, rs);
        }
        return municipio;
    }
     */
    @Override
    public boolean save(Municipio municipio) {
        PreparedStatement ps = null;
        Connection conn = null;
        if (municipio == null) {
            System.err.println("O valor oassado não pode ser nulo!");
        }
        try {
            conn = Conexao.getConnection();
            ps = conn.prepareStatement(INSERT);

            ps.setString(1, municipio.getNomeMunicipio());
            ps.setInt(2, municipio.getProvinciaMunicipio().getIdProvincia());
            ps.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Erro ao inserir dados: " + e.getMessage());
        } finally {
            Conexao.closeConnection(conn, ps);
        }
        return true;
    }

    @Override
    public boolean update(Municipio municipio) {
        PreparedStatement ps = null;
        Connection conn = null;
        if (municipio == null) {
            System.err.println("O valor passado nao pode ser nulo");
        }
        try {
            conn = (Connection) Conexao.getConnection();
            ps = conn.prepareStatement(UPDATE);
            ps.setString(1, municipio.getNomeMunicipio());
            ps.setInt(2, municipio.getProvinciaMunicipio().getIdProvincia());
            ps.setInt(3, municipio.getIdMunicipio());
            ps.executeUpdate();

        } catch (Exception ex) {
            System.err.println("Erro ao actualizar dados: " + ex.getLocalizedMessage());
        } finally {
            Conexao.closeConnection(conn, ps);
        }
        return true;

    }

    @Override
    public boolean delete(Municipio municipio) {
        PreparedStatement ps = null;
        Connection conn = null;
        if (municipio == null) {
            System.err.println("O valor passado nao pode ser nulo");
        }
        try {
            conn = (Connection) Conexao.getConnection();
            ps = conn.prepareStatement(DELETE);
            ps.setInt(1, municipio.getIdMunicipio());
            ps.executeUpdate();
        } catch (Exception ex) {
            System.err.println("Erro ao eliminar dados: " + ex.getLocalizedMessage());
        } finally {
            Conexao.closeConnection(conn, ps);
            {
            }
        }

        return true;
    }

    @Override
    public Municipio findById(Integer id) {
        PreparedStatement ps = null;
        Connection conn = null;
        ResultSet rs = null;
        Municipio municipio = new Municipio();
        try {
            conn = (Connection) Conexao.getConnection();
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

    public List<Municipio> findByIdProvincia(int id) {
        PreparedStatement ps = null;
        Connection conn = null;
        ResultSet rs = null;
        System.out.println("Provincia DAO <<<<<=====" + id);
        List<Municipio> municipios = new ArrayList<>();
        try {
            conn = (Connection) Conexao.getConnection();
            ps = conn.prepareStatement(SELECT_BY_ID_PROVINCIA);
            ps.setInt(1, id);
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

    public List<Municipio> findByIdProvincia2(Provincia p) {
        PreparedStatement ps = null;
        Connection conn = null;
        ResultSet rs = null;
        System.out.println("Provincia DAO <<<<<=====" + p.getNomeProvincia());

        List<Municipio> municipios = new ArrayList<>();
        try {
            conn = (Connection) Conexao.getConnection();
            ps = conn.prepareStatement(SELECT_BY_ID_PROVINCIA);
            ps.setInt(1, p.getIdProvincia());
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
    public List<Municipio> findAll() {
        PreparedStatement ps = null;
        Connection conn = null;
        ResultSet rs = null;
        List<Municipio> municipios = new ArrayList<>();
        try {
            conn = (Connection) Conexao.getConnection();
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
           
            municipio.setIdMunicipio(rs.getInt("m.id_municipio"));
            municipio.setNomeMunicipio(rs.getString("m.nome_municipio"));
            Provincia p = new Provincia();
            p.setNomeProvincia(rs.getString("p.nome_provincia"));
            municipio.setProvinciaMunicipio(p);
            
        } catch (SQLException ex) {
            System.err.println("Erro ao carregar dados do municipio: " + ex.getLocalizedMessage());
        }
    }
}
