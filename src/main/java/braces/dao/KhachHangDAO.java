/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package braces.dao;

import braces.entity.KhachHang;
import java.sql.SQLException;
import java.util.Optional;

public interface KhachHangDAO extends BaseDAO<KhachHang, Integer> {
    Optional<KhachHang> findBySdt (String sdt);
}
