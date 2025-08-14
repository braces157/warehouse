package braces.dao.impl;

import braces.dao.PhieuNhapDAO;
import braces.entity.PhieuNhap;
import braces.util.XQuery;
import braces.util.XJdbc;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

/**
 *
 * @author Braces
 */
public class PhieuNhapDAOImpl implements PhieuNhapDAO {
    
    @Override
    public List<PhieuNhap> findAll() throws SQLException {
        String sql = "SELECT * FROM PHIEUNHAP";
        return XQuery.getBeanList(PhieuNhap.class, sql);
    }
    
    @Override
    public Optional<PhieuNhap> findById(Integer id) throws SQLException {
        String sql = "SELECT * FROM PHIEUNHAP WHERE maphieunhap = ?";
        PhieuNhap pn = XQuery.getSingleBean(PhieuNhap.class, sql, id);
        return Optional.ofNullable(pn);
    }
    
    @Override
    public PhieuNhap save(PhieuNhap phieuNhap) throws SQLException {
        return insert(phieuNhap);
    }
    
    private PhieuNhap insert(PhieuNhap phieuNhap) throws SQLException {
        String sql = "INSERT INTO PHIEUNHAP (manhacungcap, nguoitaophieunhap, thoigian, tongtien, trangthai,maphieunhap) VALUES (?, ?, ?, ?, ?,?)";
        XJdbc.executeUpdate(sql,
                phieuNhap.getMaNhaCungCap(),
                phieuNhap.getNguoiTaoPhieuNhap(),
                phieuNhap.getThoiGian(),
                phieuNhap.getTongTien(),
                phieuNhap.getTrangThai(),phieuNhap.getMaPhieuNhap());
        return phieuNhap;
    }
    
    @Override
    public void deleteById(Integer id) throws SQLException {
        String sql = "DELETE FROM PHIEUNHAP WHERE maphieunhap = ?";
        XJdbc.executeUpdate(sql, id);
    }
    @Override
    public List<PhieuNhap> findByNhanVien(Integer maNhanVien) throws SQLException {
        String sql = "SELECT * FROM PHIEUNHAP WHERE nguoitaophieunhap = ?";
        return XQuery.getBeanList(PhieuNhap.class, sql, maNhanVien);
    }
    @Override
      public List<PhieuNhap> findByNhanVienLast7Days(Integer maNhanVien) throws SQLException {
        String sql = "SELECT * FROM PHIEUNHAP WHERE nguoitaophieunhap = ? and DATEADD(DAY, -7, GETDATE())<thoigian";
        return XQuery.getBeanList(PhieuNhap.class, sql, maNhanVien);
    }
    @Override
    public List<PhieuNhap> findByNhaCungCap(Integer maNhaCungCap) throws SQLException {
        String sql = "SELECT * FROM PHIEUNHAP WHERE manhacungcap = ?";
        return XQuery.getBeanList(PhieuNhap.class, sql, maNhaCungCap);
    }
}