/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package braces.dao.impl;

import braces.dao.TaiKhoanDAO;
import braces.dao.BaseDAO;
import braces.entity.TaiKhoan;
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
public class TaiKhoanDAOImpl implements TaiKhoanDAO {

    @Override
    public List<TaiKhoan> findAll() throws SQLException {
        String sql = "SELECT * FROM TAIKHOAN";
        return XQuery.getBeanList(TaiKhoan.class, sql);
    }

    @Override
    public Optional<TaiKhoan> findById(Integer id) throws SQLException {
        String sql = "SELECT * FROM TAIKHOAN WHERE manv = ?";
        TaiKhoan taiKhoan = XQuery.getSingleBean(TaiKhoan.class, sql, id);
        return Optional.ofNullable(taiKhoan);
    }
    
    @Override
    public Optional<TaiKhoan> findByUsername(String username) throws SQLException {
        String sql = "SELECT * FROM TAIKHOAN WHERE username = ?";
        TaiKhoan taiKhoan = XQuery.getSingleBean(TaiKhoan.class, sql, username);
        return Optional.ofNullable(taiKhoan);
    }

    @Override
    public TaiKhoan save(TaiKhoan taiKhoan) throws SQLException {
        if (taiKhoan.getManv() == null) {
            return insert(taiKhoan);
        } else {
            return update(taiKhoan);
        }
    }

    public TaiKhoan insert(TaiKhoan taiKhoan) throws SQLException {
        String sql = "INSERT INTO TAIKHOAN (username, matkhau, trangthai, role, tennv, sodienthoai, email) VALUES (?, ?, ?, ?, ?, ?, ?)";

        XJdbc.executeUpdate(sql,
                taiKhoan.getUsername(),
                taiKhoan.getMatKhau(),
                taiKhoan.getTrangThai(),
                taiKhoan.getRole(),
                taiKhoan.getTenNv(),
                taiKhoan.getSoDienThoai(),
                taiKhoan.getEmail());
        return taiKhoan;
    }

    private TaiKhoan update(TaiKhoan taiKhoan) throws SQLException {
        String sql = "UPDATE TAIKHOAN SET  matkhau = ?, trangthai = ?, role = ?, tennv = ?, sodienthoai = ?, email = ? WHERE username = ?";
        XJdbc.executeUpdate(sql,
                taiKhoan.getMatKhau(),
                taiKhoan.getTrangThai(),
                taiKhoan.getRole(),
                taiKhoan.getTenNv(),
                taiKhoan.getSoDienThoai(),
                taiKhoan.getEmail(),
                taiKhoan.getUsername());
        return taiKhoan;
    }

    @Override
    public void deleteById(Integer id) throws SQLException {
        String sql = "DELETE FROM TAIKHOAN WHERE manv = ?";
        XJdbc.executeUpdate(sql, id);
    }

    @Override
    public List<TaiKhoan> findByRole(Integer role) throws SQLException {
        String sql = "SELECT * FROM TAIKHOAN WHERE role = ?";
        return XQuery.getBeanList(TaiKhoan.class, sql, role);
    }

    @Override
    public List<TaiKhoan> findActiveAccounts() throws SQLException {
        String sql = "SELECT * FROM TAIKHOAN WHERE trangthai = 1";
        return XQuery.getBeanList(TaiKhoan.class, sql);
    }
}
