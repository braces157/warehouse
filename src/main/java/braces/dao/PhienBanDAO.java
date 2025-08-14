package braces.dao;

import braces.entity.PhienBan;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author Braces
 */
public interface PhienBanDAO extends BaseDAO<PhienBan, String> {
    List<PhienBan> findBySanPham(Integer maSP) throws SQLException;
}
