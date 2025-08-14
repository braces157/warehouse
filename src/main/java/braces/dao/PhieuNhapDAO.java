package braces.dao;

import braces.entity.PhieuNhap;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author Braces
 */
public interface PhieuNhapDAO extends BaseDAO<PhieuNhap, Integer> {

    List<PhieuNhap> findByNhaCungCap(Integer maNhaCungCap) throws SQLException;

    List<PhieuNhap> findByNhanVien(Integer maNhanVien) throws SQLException;

    List<PhieuNhap> findByNhanVienLast7Days(Integer maNhanVien) throws SQLException;
}
