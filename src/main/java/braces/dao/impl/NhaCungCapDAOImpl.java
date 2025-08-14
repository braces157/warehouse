/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package braces.dao.impl;

import braces.dao.NhaCungCapDAO;
import braces.entity.NhaCungCap;
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
public class NhaCungCapDAOImpl implements NhaCungCapDAO {
    
    @Override
    public List<NhaCungCap> findAll() throws SQLException {
        String sql = "SELECT * FROM NHACUNGCAP";
        return XQuery.getBeanList(NhaCungCap.class, sql);
    }
    
    @Override
    public Optional<NhaCungCap> findById(Integer id) throws SQLException {
        String sql = "SELECT * FROM NHACUNGCAP WHERE manhacungcap = ?";
        NhaCungCap nhaCungCap = XQuery.getSingleBean(NhaCungCap.class, sql, id);
        return Optional.ofNullable(nhaCungCap);
    }
    
    @Override
    public NhaCungCap save(NhaCungCap nhaCungCap) throws SQLException {
        if (nhaCungCap.getMaNhaCungCap() == null) {
            return insert(nhaCungCap);
        } else {
            return update(nhaCungCap);
        }
    }
    
    private NhaCungCap insert(NhaCungCap nhaCungCap) throws SQLException {
        String sql = "INSERT INTO NHACUNGCAP (tennhacungcap, diachi, email, sodienthoai) VALUES (?, ?, ?, ?)";
        Integer id = XJdbc.executeUpdate(sql,
                nhaCungCap.getTenNhaCungCap(),
                nhaCungCap.getDiaChi(),
                nhaCungCap.getEmail(),
                nhaCungCap.getSoDienThoai());
        nhaCungCap.setMaNhaCungCap(id);
        return nhaCungCap;
    }
    
    private NhaCungCap update(NhaCungCap nhaCungCap) throws SQLException {
        String sql = "UPDATE NHACUNGCAP SET tennhacungcap = ?, diachi = ?, email = ?, sodienthoai = ? WHERE manhacungcap = ?";
        XJdbc.executeUpdate(sql,
                nhaCungCap.getTenNhaCungCap(),
                nhaCungCap.getDiaChi(),
                nhaCungCap.getEmail(),
                nhaCungCap.getSoDienThoai(),
                nhaCungCap.getMaNhaCungCap());
        return nhaCungCap;
    }
    
    @Override
    public void deleteById(Integer id) throws SQLException {
        String sql = "DELETE FROM NHACUNGCAP WHERE manhacungcap = ?";
        XJdbc.executeUpdate(sql, id);
    }

    @Override
    public Optional<NhaCungCap> findByTenNhaCungCap(String ten) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}