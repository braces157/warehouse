/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package braces.controller.impl;

import braces.dao.NhaCungCapDAO;
import braces.entity.NhaCungCap;
import braces.controller.NhaCungCapController;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 *
 * @author Braces
 */
public class NhaCungCapControllerImpl implements NhaCungCapController {
    
    private final NhaCungCapDAO nhaCungCapDAO;
    
    public NhaCungCapControllerImpl(NhaCungCapDAO nhaCungCapDAO) {
        this.nhaCungCapDAO = nhaCungCapDAO;
    }
    
    @Override
    public List<NhaCungCap> getAll() {
        try {
            return nhaCungCapDAO.findAll();
        } catch (SQLException e) {
            throw new RuntimeException("Failed to get suppliers", e);
        }
    }
    
    @Override
    public Optional<NhaCungCap> getById(Integer id) {
        try {
            return nhaCungCapDAO.findById(id);
        } catch (SQLException e) {
            throw new RuntimeException("Failed to get supplier", e);
        }
    }
    
    @Override
    public NhaCungCap save(NhaCungCap nhaCungCap) {
        try {
            return nhaCungCapDAO.save(nhaCungCap);
        } catch (SQLException e) {
            throw new RuntimeException("Failed to save supplier", e);
        }
    }
    
    @Override
    public void deleteById(Integer id) {
        try {
            nhaCungCapDAO.deleteById(id);
        } catch (SQLException e) {
            throw new RuntimeException("Failed to delete supplier", e);
        }
    }
    
    @Override
    public List<NhaCungCap> getSearchTable(String text, String searchType) {
        text = text.toLowerCase();
        List<NhaCungCap> result = new ArrayList<>();
        
        switch (searchType) {
            case "Tất cả" -> {
                for (NhaCungCap e : this.getAll()) {
                    if ((e.getMaNhaCungCap() != null && e.getMaNhaCungCap().toString().toLowerCase().contains(text))
                            || (e.getTenNhaCungCap() != null && e.getTenNhaCungCap().toLowerCase().contains(text))
                            || (e.getDiaChi() != null && e.getDiaChi().toLowerCase().contains(text))
                            || (e.getEmail() != null && e.getEmail().toLowerCase().contains(text))
                            || (e.getSoDienThoai() != null && e.getSoDienThoai().toLowerCase().contains(text))) {
                        result.add(e);
                    }
                }
            }
            case "Mã" -> {
                for (NhaCungCap e : this.getAll()) {
                    if (e.getMaNhaCungCap() != null && e.getMaNhaCungCap().toString().toLowerCase().contains(text)) {
                        result.add(e);
                    }
                }
            }
            case "Tên" -> {
                for (NhaCungCap e : this.getAll()) {
                    if (e.getTenNhaCungCap() != null && e.getTenNhaCungCap().toLowerCase().contains(text)) {
                        result.add(e);
                    }
                }
            }
            case "Địa chỉ" -> {
                for (NhaCungCap e : this.getAll()) {
                    if (e.getDiaChi() != null && e.getDiaChi().toLowerCase().contains(text)) {
                        result.add(e);
                    }
                }
            }
            case "Email" -> {
                for (NhaCungCap e : this.getAll()) {
                    if (e.getEmail() != null && e.getEmail().toLowerCase().contains(text)) {
                        result.add(e);
                    }
                }
            }
            case "Sđt" -> {
                for (NhaCungCap e : this.getAll()) {
                    if (e.getSoDienThoai() != null && e.getSoDienThoai().toLowerCase().contains(text)) {
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