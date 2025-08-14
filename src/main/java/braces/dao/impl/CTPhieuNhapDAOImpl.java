/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package braces.dao.impl;

import braces.dao.CTPhieuNhapDAO;
import braces.entity.CTPhieuNhap;
import braces.util.XQuery;
import braces.util.XJdbc;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

/**
 *
 * @author Braces
 */
public class CTPhieuNhapDAOImpl implements CTPhieuNhapDAO {
    
    @Override
    public List<CTPhieuNhap> findAll() throws SQLException {
        String sql = "SELECT * FROM CTPHIEUNHAP";
        return XQuery.getBeanList(CTPhieuNhap.class, sql);
    }
    
    @Override
    public Optional<CTPhieuNhap> findById(Integer id) throws SQLException {
        String sql = "SELECT * FROM CTPHIEUNHAP WHERE id = ?";
        CTPhieuNhap ctpn = XQuery.getSingleBean(CTPhieuNhap.class, sql, id);
        return Optional.ofNullable(ctpn);
    }
    
    @Override
    public CTPhieuNhap save(CTPhieuNhap ctPhieuNhap) throws SQLException {
      return insert(ctPhieuNhap);
    }
    
    private CTPhieuNhap insert(CTPhieuNhap ctPhieuNhap) throws SQLException {
        String sql = "INSERT INTO CTPHIEUNHAP (maphieunhap, maphienban, soluong, dongia) VALUES (?, ?, ?, ?)";
        XJdbc.executeUpdate(sql, 
                ctPhieuNhap.getMaPhieuNhap(),
                ctPhieuNhap.getMaPhienBan(),
                ctPhieuNhap.getSoLuong(),
                ctPhieuNhap.getDonGia());
        return ctPhieuNhap;
    }
    
    @Override
    public void deleteById(Integer id) throws SQLException {
        String sql = "DELETE FROM CTPHIEUNHAP WHERE maphiuenhap = ?";
        XJdbc.executeUpdate(sql, id);
    }
    
    @Override
    public List<CTPhieuNhap> findByPhieuNhap(Integer maPhieuNhap) throws SQLException {
        String sql = "SELECT * FROM CTPHIEUNHAP WHERE maphieunhap = ?";
        return XQuery.getBeanList(CTPhieuNhap.class, sql, maPhieuNhap);
    }
    
    @Override
    public List<CTPhieuNhap> findByPhienBan(String maPhienBan) throws SQLException {
        String sql = "SELECT * FROM CTPHIEUNHAP WHERE maphienban = ?";
        return XQuery.getBeanList(CTPhieuNhap.class, sql, maPhienBan);
    }    
}
