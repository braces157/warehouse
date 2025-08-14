/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package braces.dao;

import braces.entity.CTPhieuNhap;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author Braces
 */
public interface CTPhieuNhapDAO extends BaseDAO<CTPhieuNhap, Integer> {
    List<CTPhieuNhap> findByPhieuNhap(Integer maPhieuNhap) throws SQLException;
    List<CTPhieuNhap> findByPhienBan(String maPhienBan) throws SQLException;
}
