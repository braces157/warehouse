/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package braces.dao;

import braces.entity.ThuocTinhSanPham;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author Braces
 */
public interface ThuocTinhSanPhamDAO extends BaseDAO<ThuocTinhSanPham, Integer> {
    List<ThuocTinhSanPham> findBySanPham(Integer maSP) throws SQLException;
}
