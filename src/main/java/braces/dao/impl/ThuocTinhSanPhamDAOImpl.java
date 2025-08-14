/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package braces.dao.impl;

import braces.dao.ThuocTinhSanPhamDAO;
import braces.entity.ThuocTinhSanPham;
import braces.util.XQuery;
import braces.util.XJdbc;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Optional;

/**
 *
 * @author Braces
 */
public class ThuocTinhSanPhamDAOImpl implements ThuocTinhSanPhamDAO {
    
    @Override
    public List<ThuocTinhSanPham> findAll() throws SQLException {
        String sql = "SELECT * FROM THUOCTINHSANPHAM";
        return XQuery.getBeanList(ThuocTinhSanPham.class, sql);
    }
    
    @Override
    public Optional<ThuocTinhSanPham> findById(Integer id) throws SQLException {
        String sql = "SELECT * FROM THUOCTINHSANPHAM WHERE id = ?";
        ThuocTinhSanPham ttsp = XQuery.getSingleBean(ThuocTinhSanPham.class, sql, id);
        return Optional.ofNullable(ttsp);
    }
    
    @Override
    public ThuocTinhSanPham save(ThuocTinhSanPham thuocTinhSanPham) throws SQLException {
        if (thuocTinhSanPham.getId() == null) {
            return insert(thuocTinhSanPham);
        } else {
            return update(thuocTinhSanPham);
        }
    }
    
    private ThuocTinhSanPham insert(ThuocTinhSanPham thuocTinhSanPham) throws SQLException {
        String sql = "INSERT INTO THUOCTINHSANPHAM (ten, thutu, mota, masp) VALUES (?, ?, ?, ?)";
        Integer id = XJdbc.executeUpdate(sql,
                thuocTinhSanPham.getTen(),
                thuocTinhSanPham.getThuTu(),
                thuocTinhSanPham.getMoTa(),
                thuocTinhSanPham.getMaSP());
        thuocTinhSanPham.setId(id);
        return thuocTinhSanPham;
    }
    
    private ThuocTinhSanPham update(ThuocTinhSanPham thuocTinhSanPham) throws SQLException {
        String sql = "UPDATE THUOCTINHSANPHAM SET ten = ?, thutu = ?, mota = ?, masp = ? WHERE id = ?";
        XJdbc.executeUpdate(sql,
                thuocTinhSanPham.getTen(),
                thuocTinhSanPham.getThuTu(),
                thuocTinhSanPham.getMoTa(),
                thuocTinhSanPham.getMaSP(),
                thuocTinhSanPham.getId());
        return thuocTinhSanPham;
    }
    
    @Override
    public void deleteById(Integer id) throws SQLException {
        String sql = "DELETE FROM THUOCTINHSANPHAM WHERE id = ?";
        XJdbc.executeUpdate(sql, id);
    }
    
    @Override
    public List<ThuocTinhSanPham> findBySanPham(Integer maSP) throws SQLException {
            String sql = "SELECT * FROM THUOCTINHSANPHAM WHERE masp = ?";
        return XQuery.getBeanList(ThuocTinhSanPham.class, sql, maSP);
    }
}