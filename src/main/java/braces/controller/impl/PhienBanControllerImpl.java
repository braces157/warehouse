/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package braces.controller.impl;

import braces.dao.PhienBanDAO;
import braces.entity.PhienBan;
import braces.controller.PhienBanController;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


public class PhienBanControllerImpl implements PhienBanController {
    
    private final PhienBanDAO phienBanDAO;
    
    public PhienBanControllerImpl(PhienBanDAO phienBanDAO) {
        this.phienBanDAO = phienBanDAO;
    }
    
    @Override
    public List<PhienBan> getAll() {
        try {
            return phienBanDAO.findAll();
        } catch (SQLException e) {
            throw new RuntimeException("Failed to get product versions", e);
        }
    }
    
    @Override
    public Optional<PhienBan> getById(String maPhienBanSP) {
        try {
            return phienBanDAO.findById(maPhienBanSP);
        } catch (SQLException e) {
            throw new RuntimeException("Failed to get product version", e);
        }
    }
    
    @Override
    public PhienBan save(PhienBan phienBan) {
        try {
            return phienBanDAO.save(phienBan);
        } catch (SQLException e) {
            throw new RuntimeException("Failed to save product version", e);
        }
    }
    
    @Override
    public void deleteById(String maPhienBanSP) {
        try {
            phienBanDAO.deleteById(maPhienBanSP);
        } catch (SQLException e) {
            throw new RuntimeException("Failed to delete product version", e);
        }
    }
    
    @Override
    public List<PhienBan> getBySanPham(Integer maSP) {
        try {
            return phienBanDAO.findBySanPham(maSP);
        } catch (SQLException e) {
            throw new RuntimeException("Failed to get product versions by product", e);
        }
    }
    
  
}