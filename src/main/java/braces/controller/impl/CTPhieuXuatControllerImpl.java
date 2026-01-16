
package braces.controller.impl;

import braces.dao.CTPhieuXuatDAO;
import braces.entity.CTPhieuXuat;
import braces.controller.CTPhieuXuatController;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 *
 * @author Braces
 */
public class CTPhieuXuatControllerImpl implements CTPhieuXuatController {
    
    private final CTPhieuXuatDAO ctPhieuXuatDAO;
    
    public CTPhieuXuatControllerImpl(CTPhieuXuatDAO ctPhieuXuatDAO) {
        this.ctPhieuXuatDAO = ctPhieuXuatDAO;
    }
    
    @Override
    public List<CTPhieuXuat> getAll() {
        try {
            return ctPhieuXuatDAO.findAll();
        } catch (SQLException e) {
            throw new RuntimeException("Failed to get export voucher details", e);
        }
    }
    
    @Override
    public Optional<CTPhieuXuat> getById(Integer id) {
        try {
            return ctPhieuXuatDAO.findById(id);
        } catch (SQLException e) {
            throw new RuntimeException("Failed to get export voucher detail", e);
        }
    }
    
    @Override
    public CTPhieuXuat save(CTPhieuXuat ctPhieuXuat) {
        try {
            return ctPhieuXuatDAO.save(ctPhieuXuat);
        } catch (SQLException e) {
            throw new RuntimeException("Failed to save export voucher detail", e);
        }
    }
    
    @Override
    public void deleteById(Integer id) {
        try {
            ctPhieuXuatDAO.deleteById(id);
        } catch (SQLException e) {
            throw new RuntimeException("Failed to delete export voucher detail", e);
        }
    }
    
    @Override
    public List<CTPhieuXuat> getByPhieuXuat(Integer maPhieuXuat) {
        try {
            return ctPhieuXuatDAO.findByPhieuXuat(maPhieuXuat);
        } catch (SQLException e) {
            throw new RuntimeException("Failed to get export voucher details by voucher", e);
        }
    }
    
    @Override
    public List<CTPhieuXuat> getByPhienBan(String maPhienBan) {
        try {
            return ctPhieuXuatDAO.findByPhienBan(maPhienBan);
        } catch (SQLException e) {
            throw new RuntimeException("Failed to get export voucher details by version", e);
        }
    }
    
    
 
}