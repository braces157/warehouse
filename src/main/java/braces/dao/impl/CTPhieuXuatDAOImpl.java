package braces.dao.impl;

import braces.dao.CTPhieuXuatDAO;
import braces.entity.CTPhieuXuat;
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
public class CTPhieuXuatDAOImpl implements CTPhieuXuatDAO {

    @Override
    public List<CTPhieuXuat> findAll() throws SQLException {
        String sql = "SELECT * FROM CTPHIEUXUAT";
        return XQuery.getBeanList(CTPhieuXuat.class, sql);
    }

    @Override
    public Optional<CTPhieuXuat> findById(Integer id) throws SQLException {
        String sql = "SELECT * FROM CTPHIEUXUAT WHERE id = ?";
        CTPhieuXuat ctpx = XQuery.getSingleBean(CTPhieuXuat.class, sql, id);
        return Optional.ofNullable(ctpx);
    }

    @Override
    public CTPhieuXuat save(CTPhieuXuat ctPhieuXuat) throws SQLException {
        return insert(ctPhieuXuat);

    }

    private CTPhieuXuat insert(CTPhieuXuat ctPhieuXuat) throws SQLException {
        String sql = "INSERT INTO CTPHIEUXUAT (maphieuxuat, maphienban, soluong, dongia) VALUES (?, ?, ?, ?)";
        XJdbc.executeUpdate(sql,
                ctPhieuXuat.getMaPhieuXuat(),
                ctPhieuXuat.getMaPhienBan(),
                ctPhieuXuat.getSoLuong(),
                ctPhieuXuat.getDonGia());
        return ctPhieuXuat;
    }

  

    @Override
    public void deleteById(Integer id) throws SQLException {
        String sql = "DELETE FROM CTPHIEUXUAT WHERE id = ?";
        XJdbc.executeUpdate(sql, id);
    }

    @Override
    public List<CTPhieuXuat> findByPhieuXuat(Integer maPhieuXuat) throws SQLException {
        String sql = "SELECT * FROM CTPHIEUXUAT WHERE maphieuxuat = ?";
        return XQuery.getBeanList(CTPhieuXuat.class, sql, maPhieuXuat);
    }

    @Override
    public List<CTPhieuXuat> findByPhienBan(String maPhienBan) throws SQLException {
        String sql = "SELECT * FROM CTPHIEUXUAT WHERE maphienban = ?";
        return XQuery.getBeanList(CTPhieuXuat.class, sql, maPhienBan);
    }
}
