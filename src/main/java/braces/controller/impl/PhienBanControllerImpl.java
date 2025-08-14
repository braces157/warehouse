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

/**
 *
 * @author Braces
 */
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
    
    @Override
    public List<PhienBan> getSearchTable(String text, String searchType) {
        text = text.toLowerCase();
        List<PhienBan> result = new ArrayList<>();
        
        switch (searchType) {
            case "Tất cả" -> {
                for (PhienBan e : this.getAll()) {
                    if ((e.getMaPhienBanSP() != null && e.getMaPhienBanSP().toLowerCase().contains(text))
                            || (e.getMaSP() != null && e.getMaSP().toString().toLowerCase().contains(text))
                            || (e.getMauSac() != null && e.getMauSac().toString().toLowerCase().contains(text))
                            || (e.getMaRam() != null && e.getMaRam().toString().toLowerCase().contains(text))
                            || (e.getMaRom() != null && e.getMaRom().toString().toLowerCase().contains(text))
                            || (e.getSoLuongTon() != null && e.getSoLuongTon().toString().toLowerCase().contains(text))
                            || (e.getGiaBan() != null && e.getGiaBan().toString().toLowerCase().contains(text))
                            || (e.getGiaNhap() != null && e.getGiaNhap().toString().toLowerCase().contains(text))) {
                        result.add(e);
                    }
                }
            }
            case "Mã phiên bản" -> {
                for (PhienBan e : this.getAll()) {
                    if (e.getMaPhienBanSP() != null && e.getMaPhienBanSP().toLowerCase().contains(text)) {
                        result.add(e);
                    }
                }
            }
            case "Mã sản phẩm" -> {
                for (PhienBan e : this.getAll()) {
                    if (e.getMaSP() != null && e.getMaSP().toString().toLowerCase().contains(text)) {
                        result.add(e);
                    }
                }
            }
            case "Màu sắc" -> {
                for (PhienBan e : this.getAll()) {
                    if (e.getMauSac() != null && e.getMauSac().toString().toLowerCase().contains(text)) {
                        result.add(e);
                    }
                }
            }
            case "RAM" -> {
                for (PhienBan e : this.getAll()) {
                    if (e.getMaRam() != null && e.getMaRam().toString().toLowerCase().contains(text)) {
                        result.add(e);
                    }
                }
            }
            case "ROM" -> {
                for (PhienBan e : this.getAll()) {
                    if (e.getMaRom() != null && e.getMaRom().toString().toLowerCase().contains(text)) {
                        result.add(e);
                    }
                }
            }
            case "Số lượng tồn" -> {
                for (PhienBan e : this.getAll()) {
                    if (e.getSoLuongTon() != null && e.getSoLuongTon().toString().toLowerCase().contains(text)) {
                        result.add(e);
                    }
                }
            }
            case "Giá bán" -> {
                for (PhienBan e : this.getAll()) {
                    if (e.getGiaBan() != null && e.getGiaBan().toString().toLowerCase().contains(text)) {
                        result.add(e);
                    }
                }
            }
            case "Giá nhập" -> {
                for (PhienBan e : this.getAll()) {
                    if (e.getGiaNhap() != null && e.getGiaNhap().toString().toLowerCase().contains(text)) {
                        result.add(e);
                    }
                }
            }
            default ->
                throw new AssertionError("Unknown search type: " + searchType);
        }
        return result;
    }
}