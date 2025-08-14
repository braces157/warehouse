/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package braces.dao;

import braces.entity.SanPham;
import java.sql.SQLException;
import java.util.List;

public interface SanPhamDAO extends BaseDAO<SanPham, Integer> {

    List<SanPham> findByKhuVuc(Integer khuVucId) throws SQLException;
}
