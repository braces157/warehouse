package braces.dao;

import braces.entity.CTPhieuXuat;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author Braces
 */
public interface CTPhieuXuatDAO extends BaseDAO<CTPhieuXuat, Integer> {
    List<CTPhieuXuat> findByPhieuXuat(Integer maPhieuXuat) throws SQLException;
    List<CTPhieuXuat> findByPhienBan(String maPhienBan) throws SQLException;
}