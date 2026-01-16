/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package braces.controller.impl;

import braces.dao.PhieuXuatDAO;
import braces.entity.PhieuXuat;
import braces.controller.PhieuXuatController;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;


public class PhieuXuatControllerImpl implements PhieuXuatController {

    private final PhieuXuatDAO phieuXuatDAO;

    public PhieuXuatControllerImpl(PhieuXuatDAO phieuXuatDAO) {
        this.phieuXuatDAO = phieuXuatDAO;
    }

    @Override
    public List<PhieuXuat> getAll() {
        try {
            return phieuXuatDAO.findAll();
        } catch (SQLException e) {
            throw new RuntimeException("Failed to get export vouchers", e);
        }
    }

    @Override
    public Optional<PhieuXuat> getById(Integer id) {
        try {
            return phieuXuatDAO.findById(id);
        } catch (SQLException e) {
            throw new RuntimeException("Failed to get export voucher", e);
        }
    }

    @Override
    public PhieuXuat save(PhieuXuat phieuXuat) {
        try {
            return phieuXuatDAO.save(phieuXuat);
        } catch (SQLException e) {
            throw new RuntimeException("Failed to save export voucher", e);
        }
    }

    @Override
    public void deleteById(Integer id) {
        try {
            phieuXuatDAO.deleteById(id);
        } catch (SQLException e) {
            throw new RuntimeException("Failed to delete export voucher", e);
        }
    }

    @Override
    public List<PhieuXuat> getByKhachHang(Integer maKH) {
        try {
            return phieuXuatDAO.findByKhachHang(maKH);
        } catch (SQLException e) {
            throw new RuntimeException("Failed to get export vouchers by customer", e);
        }
    }
    public List<PhieuXuat> getByNhanVienLast7Days(Integer maNhanVien) {
        try {
            return phieuXuatDAO.findByNhanVienLast7Days(maNhanVien);
        } catch (SQLException e) {
            throw new RuntimeException("Failed to get export vouchers by customer", e);
        }
    }



    @Override
    public List<PhieuXuat> getFilterTable(Integer trangThai, long fromPrice, long toPrice) {
        List<PhieuXuat> result = new ArrayList<>();
        for (PhieuXuat e : this.getAll()) {
           if (Objects.equals(e.getTrangThai(), trangThai) && e.getTongTien() >= fromPrice && e.getTongTien() <= toPrice) 
       result.add(e);
        }
        return result;
    }

}
