/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fenix.iure.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author informatica
 * @param <T>
 */
public interface GenericoDAO<T> {
    void save(T t);
    void update(T t);
    void delete(T t);
    T findById(Integer id);
    List<T> findAll();
    void popularComDados(T t, ResultSet rs);
}


