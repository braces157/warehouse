/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package braces.dao.impl;

import braces.dao.KhuVucKhoDAO;
import braces.entity.KhuVucKho;
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
public class KhuVucKhoDAOImpl implements KhuVucKhoDAO {

    @Override
    public List<KhuVucKho> findAll() throws SQLException {
        String sql = "SELECT * FROM KHUVUCKHO";
        return XQuery.getBeanList(KhuVucKho.class, sql);
    }

    @Override
    public Optional<KhuVucKho> findById(Integer id) throws SQLException {
        String sql = "SELECT * FROM KHUVUCKHO WHERE makhuvuc = ?";
        KhuVucKho khuVucKho = XQuery.getSingleBean(KhuVucKho.class, sql, id);
        return Optional.ofNullable(khuVucKho);
    }

    @Override
    public KhuVucKho save(KhuVucKho khuVucKho) throws SQLException {
        if (khuVucKho.getMaKhuVuc() == null) {
            return insert(khuVucKho);
        } else {
            return update(khuVucKho);
        }
    }

    private KhuVucKho insert(KhuVucKho khuVucKho) throws SQLException {
        String sql = "INSERT INTO KHUVUCKHO (tenkhuvuc,diachi) VALUES (?,?)";
        Integer id = XJdbc.executeUpdate(sql,
                khuVucKho.getTenKhuVuc(), khuVucKho.getDiaChi());
        khuVucKho.setMaKhuVuc(id);
        return khuVucKho;
    }

    private KhuVucKho update(KhuVucKho khuVucKho) throws SQLException {
        String sql = "UPDATE KHUVUCKHO SET tenkhuvuc = ?, diachi = ? WHERE makhuvuc = ?";
        XJdbc.executeUpdate(sql,
                khuVucKho.getTenKhuVuc(),
                khuVucKho.getDiaChi(),
                khuVucKho.getMaKhuVuc());
        return khuVucKho;
    }

    @Override
    public void deleteById(Integer id) throws SQLException {
        String sql = "DELETE FROM KHUVUCKHO WHERE makhuvuc = ?";
        XJdbc.executeUpdate(sql, id);
    }

    @Override
    public Optional<KhuVucKho> findByTenKho(String ten) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
