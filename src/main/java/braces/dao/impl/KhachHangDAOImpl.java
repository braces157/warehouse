/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package braces.dao.impl;

import braces.dao.KhachHangDAO;
import braces.entity.KhachHang;
import braces.util.XQuery;
import braces.util.XJdbc;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Optional;

public class KhachHangDAOImpl implements KhachHangDAO {

    @Override
    public List<KhachHang> findAll() throws SQLException {
        String sql = "SELECT * FROM KHACHHANG";
        return XQuery.getBeanList(KhachHang.class, sql);
    }

    @Override
    public Optional<KhachHang> findById(Integer id) throws SQLException {
        String sql = "SELECT * FROM KHACHHANG WHERE makh = ?";
        KhachHang kh = XQuery.getSingleBean(KhachHang.class, sql, id);
        return Optional.ofNullable(kh);
    }

    @Override
    public KhachHang save(KhachHang khachHang) throws SQLException {
        if (khachHang.getMaKH() == null) {
            return insert(khachHang);
        } else {
            return update(khachHang);
        }
    }

    private KhachHang insert(KhachHang khachHang) throws SQLException {
        String sql = "INSERT INTO KHACHHANG (tenkhachhang, diachi, sodienthoai) VALUES (?, ?, ?)";
        Integer id = XJdbc.executeUpdate(sql,
                khachHang.getTenKhachHang(),
                khachHang.getDiaChi(),
                khachHang.getSoDienThoai());
        khachHang.setMaKH(id);
        return khachHang;
    }

    private KhachHang update(KhachHang khachHang) throws SQLException {
        String sql = "UPDATE KHACHHANG SET tenkhachhang = ?, diachi = ?, sodienthoai = ? WHERE makh = ?";
        XJdbc.executeUpdate(sql,
                khachHang.getTenKhachHang(),
                khachHang.getDiaChi(),
                khachHang.getSoDienThoai(),
                khachHang.getMaKH());
        return khachHang;
    }

    @Override
    public void deleteById(Integer id) throws SQLException {
        String sql = "DELETE FROM KHACHHANG WHERE makh = ?";
        XJdbc.executeUpdate(sql, id);
    }

    @Override
    public Optional<KhachHang> findBySdt(String sdt) {
        String sql = "SELECT * FROM KHACHHANG WHERE sodienthoai = ?";
        KhachHang kh = XQuery.getSingleBean(KhachHang.class, sql, sdt);
        return Optional.ofNullable(kh);
    }
}
