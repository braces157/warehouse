package braces.controller.impl;

import braces.dao.CTPhieuNhapDAO;
import braces.entity.CTPhieuNhap;
import braces.controller.CTPhieuNhapController;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 *
 * @author Braces
 */
public class CTPhieuNhapControllerImpl implements CTPhieuNhapController {

    private final CTPhieuNhapDAO ctPhieuNhapDAO;

    public CTPhieuNhapControllerImpl(CTPhieuNhapDAO ctPhieuNhapDAO) {
        this.ctPhieuNhapDAO = ctPhieuNhapDAO;
    }

    @Override
    public List<CTPhieuNhap> getAll() {
        try {
            return ctPhieuNhapDAO.findAll();
        } catch (SQLException e) {
            throw new RuntimeException("Failed to get import voucher details", e);
        }
    }

    @Override
    public Optional<CTPhieuNhap> getById(Integer id) {
        try {
            return ctPhieuNhapDAO.findById(id);
        } catch (SQLException e) {
            throw new RuntimeException("Failed to get import voucher detail", e);
        }
    }

    @Override
    public CTPhieuNhap save(CTPhieuNhap ctPhieuNhap) {
        try {
            return ctPhieuNhapDAO.save(ctPhieuNhap);
        } catch (SQLException e) {
            throw new RuntimeException("Failed to save import voucher detail", e);
        }
    }

    @Override
    public void deleteById(Integer id) {
        try {
            ctPhieuNhapDAO.deleteById(id);
        } catch (SQLException e) {
            throw new RuntimeException("Failed to delete import voucher detail", e);
        }
    }

    @Override
    public List<CTPhieuNhap> getByPhieuNhap(Integer maPhieuNhap) {
        try {
            return ctPhieuNhapDAO.findByPhieuNhap(maPhieuNhap);
        } catch (SQLException e) {
            throw new RuntimeException("Failed to get import voucher details by voucher", e);
        }
    }

    @Override
    public List<CTPhieuNhap> getByPhienBan(String maPhienBan) {
        try {
            return ctPhieuNhapDAO.findByPhienBan(maPhienBan);
        } catch (SQLException e) {
            throw new RuntimeException("Failed to get import voucher details by version", e);
        }
    }

    
}
