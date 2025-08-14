/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package braces.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface BaseDAO<T, ID> {
    List<T> findAll() throws SQLException;
    Optional<T> findById(ID id) throws SQLException;
    T save(T entity) throws SQLException;
    void deleteById(ID id) throws SQLException;
}
