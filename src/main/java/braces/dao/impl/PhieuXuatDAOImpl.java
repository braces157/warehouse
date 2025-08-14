/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */package braces.dao.impl;

import braces.dao.PhieuXuatDAO;
import braces.entity.PhieuXuat;
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
public class PhieuXuatDAOImpl implements PhieuXuatDAO {

    @Override
    public List<PhieuXuat> findAll() throws SQLException {
        String sql = "SELECT * FROM PHIEUXUAT";
        return XQuery.getBeanList(PhieuXuat.class, sql);
    }

    @Override
    public Optional<PhieuXuat> findById(Integer id) throws SQLException {
        String sql = "SELECT * FROM PHIEUXUAT WHERE maphieuxuat = ?";
        PhieuXuat px = XQuery.getSingleBean(PhieuXuat.class, sql, id);
        return Optional.ofNullable(px);
    }

    @Override
    public PhieuXuat save(PhieuXuat phieuXuat) throws SQLException {
        return insert(phieuXuat);

    }

    private PhieuXuat insert(PhieuXuat phieuXuat) throws SQLException {
        String sql = "INSERT INTO PHIEUXUAT (maphieuxuat,thoigian, trangthai, nguoitaophieuxuat, makh, tongtien) VALUES (?,?, ?, ?, ?, ?)";
        Integer id = XJdbc.executeUpdate(sql,
                phieuXuat.getMaPhieuXuat(),
                phieuXuat.getThoiGian(),
                phieuXuat.getTrangThai(),
                phieuXuat.getNguoiTaoPhieuXuat(),
                phieuXuat.getMaKH(),
                phieuXuat.getTongTien());
        return phieuXuat;
    }

    @Override
    public void deleteById(Integer id) throws SQLException {
        String sql = "DELETE FROM PHIEUXUAT WHERE maphieuxuat = ?";
        XJdbc.executeUpdate(sql, id);
    }
    public List<PhieuXuat> findByNhanVien(Integer maNhanVien) throws SQLException {
        String sql = "SELECT * FROM PHIEUXUAT WHERE nguoitaophieuxuat = ?";
        return XQuery.getBeanList(PhieuXuat.class, sql, maNhanVien);
    }
    
    public List<PhieuXuat> findByNhanVienLast7Days(Integer maNhanVien) throws SQLException {
        String sql = "SELECT * FROM PHIEUXUAT WHERE nguoitaophieuxuat = ? and DATEADD(DAY, -7, GETDATE())<thoigian";
        return XQuery.getBeanList(PhieuXuat.class, sql, maNhanVien);
    }
    
    @Override
    public List<PhieuXuat> findByKhachHang(Integer maKH) throws SQLException {
        String sql = "SELECT * FROM PHIEUXUAT WHERE makh = ?";
        return XQuery.getBeanList(PhieuXuat.class, sql, maKH);
    }

}
