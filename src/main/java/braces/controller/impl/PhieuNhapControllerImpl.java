package braces.controller.impl;

import braces.dao.PhieuNhapDAO;
import braces.entity.PhieuNhap;
import braces.controller.PhieuNhapController;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;


public class PhieuNhapControllerImpl implements PhieuNhapController {

    private final PhieuNhapDAO phieuNhapDAO;

    public PhieuNhapControllerImpl(PhieuNhapDAO phieuNhapDAO) {
        this.phieuNhapDAO = phieuNhapDAO;
    }

    @Override
    public List<PhieuNhap> getAll() {
        try {
            return phieuNhapDAO.findAll();
        } catch (SQLException e) {
            throw new RuntimeException("Failed to get import vouchers", e);
        }
    }

    @Override
    public Optional<PhieuNhap> getById(Integer id) {
        try {
            return phieuNhapDAO.findById(id);
        } catch (SQLException e) {
            throw new RuntimeException("Failed to get import voucher", e);
        }
    }

    @Override
    public PhieuNhap save(PhieuNhap phieuNhap) {
        try {
            return phieuNhapDAO.save(phieuNhap);
        } catch (SQLException e) {
            throw new RuntimeException("Failed to save import voucher", e);
        }
    }

    @Override
    public void deleteById(Integer id) {
        try {
            phieuNhapDAO.deleteById(id);
        } catch (SQLException e) {
            throw new RuntimeException("Failed to delete import voucher", e);
        }
    }
    public List<PhieuNhap> getByNhanVienLast7Days(Integer maNhanVien) {
        try {
            return phieuNhapDAO.findByNhanVienLast7Days(maNhanVien);
        } catch (SQLException e) {
            throw new RuntimeException("Failed to get import vouchers by supplier", e);
        }
    }

    @Override
    public List<PhieuNhap> getByNhaCungCap(Integer maNhaCungCap) {
        try {
            return phieuNhapDAO.findByNhaCungCap(maNhaCungCap);
        } catch (SQLException e) {
            throw new RuntimeException("Failed to get import vouchers by supplier", e);
        }
    }

    @Override
    public List<PhieuNhap> getFilterTable(Integer trangThai, long fromPrice, long toPrice) {
        List<PhieuNhap> result = new ArrayList<>();
        for (PhieuNhap e : this.getAll()) {
            if (Objects.equals(e.getTrangThai(), trangThai) && e.getTongTien() >= fromPrice && e.getTongTien() <= toPrice)  {
                result.add(e);
            }
        }
        return result;
    }
}
