/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package braces.dao;

import braces.entity.TaiKhoan;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

/**
 *
 * @author Braces
 */
public interface TaiKhoanDAO extends BaseDAO<TaiKhoan, Integer> {
    Optional<TaiKhoan> findByUsername(String username) throws SQLException;
    List<TaiKhoan> findByRole(Integer role) throws SQLException;
    List<TaiKhoan> findActiveAccounts() throws SQLException;
}