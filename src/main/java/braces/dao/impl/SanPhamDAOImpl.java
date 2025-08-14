/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package braces.dao.impl;

import braces.dao.SanPhamDAO;
import braces.entity.SanPham;
import braces.util.XQuery;
import braces.util.XJdbc;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Optional;

public class SanPhamDAOImpl implements SanPhamDAO {



    @Override
    public List<SanPham> findAll() throws SQLException {
        String sql = "SELECT * FROM SANPHAM";
        return XQuery.getBeanList(SanPham.class, sql);
    }

    @Override
    public Optional<SanPham> findById(Integer id) throws SQLException {
        String sql = "SELECT * FROM SANPHAM WHERE masp = ?";
        SanPham sp = XQuery.getSingleBean(SanPham.class, sql, id);
        return Optional.ofNullable(sp);
    }

    @Override
    public SanPham save(SanPham sanPham) throws SQLException {
        if (sanPham.getMaSP() == null) {
            return insert(sanPham);
        } else {
            return update(sanPham);
        }
    }

    private SanPham insert(SanPham sanPham) throws SQLException {
        String sql = "INSERT INTO SANPHAM (tensanpham, hinhanh, xuatxu, chipxuly, makhuvuckho) VALUES (?, ?, ?, ?, ?)";
        Integer id = XJdbc.executeUpdate(sql, 
                sanPham.getTenSanPham(),
                sanPham.getHinhAnh(),
                sanPham.getXuatXu(),
                sanPham.getChipXuLy(),
                sanPham.getMaKhuVucKho());
        sanPham.setMaSP(id);
        return sanPham;
    }

    private SanPham update(SanPham sanPham) throws SQLException {
        String sql = "UPDATE SANPHAM SET tensanpham = ?, hinhanh = ?, xuatxu = ?, chipxuly = ?, makhuvuckho = ? WHERE masp = ?";
        XJdbc.executeUpdate(sql,
                sanPham.getTenSanPham(),
                sanPham.getHinhAnh(),
                sanPham.getXuatXu(),
                sanPham.getChipXuLy(),
                sanPham.getMaKhuVucKho(),
                sanPham.getMaSP());
        return sanPham;
    }

    @Override
    public void deleteById(Integer id) throws SQLException {
        String sql = "DELETE FROM SANPHAM WHERE masp = ?";
        XJdbc.executeUpdate(sql, id);
    }

    @Override
    public List<SanPham> findByKhuVuc(Integer khuVucId) throws SQLException {
        String sql = "SELECT * FROM SANPHAM WHERE makhuvuckho = ?";
        return XQuery.getBeanList(SanPham.class, sql, khuVucId);
    }
}
