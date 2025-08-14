/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package braces.dao;

import braces.entity.PhieuXuat;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author Braces
 */
public interface PhieuXuatDAO extends BaseDAO<PhieuXuat, Integer> {

    List<PhieuXuat> findByKhachHang(Integer maKH) throws SQLException;

    List<PhieuXuat> findByNhanVien(Integer maNhanVien) throws SQLException;

    List<PhieuXuat> findByNhanVienLast7Days(Integer maNhanVien) throws SQLException;
}
