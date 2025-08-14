/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package braces.dao.impl;

import braces.dao.PhienBanDAO;
import braces.entity.PhienBan;
import braces.util.XQuery;
import braces.util.XJdbc;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

/**
 *
 * @author Braces
 */
public class PhienBanDAOImpl implements PhienBanDAO {
    
    @Override
    public List<PhienBan> findAll() throws SQLException {
        String sql = "SELECT * FROM PHIENBAN";
        return XQuery.getBeanList(PhienBan.class, sql);
    }
    
    @Override
    public Optional<PhienBan> findById(String maPhienBanSP) throws SQLException {
        String sql = "SELECT * FROM PHIENBAN WHERE maphienbansp = ?";
        PhienBan pb = XQuery.getSingleBean(PhienBan.class, sql, maPhienBanSP);
        return Optional.ofNullable(pb);
    }
    
    @Override
    public PhienBan save(PhienBan phienBan) throws SQLException {
        if (isExisting(phienBan.getMaPhienBanSP())) {
            return update(phienBan);
        } else {
            return insert(phienBan);
        }
    }
    
    private boolean isExisting(String maPhienBanSP) throws SQLException {
        return findById(maPhienBanSP).isPresent();
    }
    
    private PhienBan insert(PhienBan phienBan) throws SQLException {
        String sql = "INSERT INTO PHIENBAN (maphienbansp, masp, mausac, maram, marom, soluongton, giaban, gianhap) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        XJdbc.executeUpdate(sql,
                phienBan.getMaPhienBanSP(),
                phienBan.getMaSP(),
                phienBan.getMauSac(),
                phienBan.getMaRam(),
                phienBan.getMaRom(),
                phienBan.getSoLuongTon(),
                phienBan.getGiaBan(),
                phienBan.getGiaNhap());
        return phienBan;
    }
    
    private PhienBan update(PhienBan phienBan) throws SQLException {
        String sql = "UPDATE PHIENBAN SET masp = ?, mausac = ?, maram = ?, marom = ?, soluongton = ?, giaban = ?, gianhap = ? WHERE maphienbansp = ?";
        XJdbc.executeUpdate(sql,
                phienBan.getMaSP(),
                phienBan.getMauSac(),
                phienBan.getMaRam(),
                phienBan.getMaRom(),
                phienBan.getSoLuongTon(),
                phienBan.getGiaBan(),
                phienBan.getGiaNhap(),
                phienBan.getMaPhienBanSP());
        return phienBan;
    }
    
    @Override
    public void deleteById(String maPhienBanSP) throws SQLException {
        String sql = "DELETE FROM PHIENBAN WHERE maphienbansp = ?";
        XJdbc.executeUpdate(sql, maPhienBanSP);
    }
    
    @Override
    public List<PhienBan> findBySanPham(Integer maSP) throws SQLException {
        String sql = "SELECT * FROM PHIENBAN WHERE masp = ?";
        return XQuery.getBeanList(PhienBan.class, sql, maSP);
    }
}
