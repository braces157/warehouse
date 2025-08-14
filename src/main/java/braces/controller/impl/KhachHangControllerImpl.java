/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package braces.controller.impl;

import braces.dao.KhachHangDAO;
import braces.entity.KhachHang;
import braces.controller.KhachHangController;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 *
 * @author Braces
 */
public class KhachHangControllerImpl implements KhachHangController {
    
    private final KhachHangDAO khachHangDAO;
    
    public KhachHangControllerImpl(KhachHangDAO khachHangDAO) {
        this.khachHangDAO = khachHangDAO;
    }
    
    @Override
    public List<KhachHang> getAll() {
        try {
            return khachHangDAO.findAll();
        } catch (SQLException e) {
            throw new RuntimeException("Failed to get customers", e);
        }
    }
    
    @Override
    public Optional<KhachHang> getById(Integer id) {
        try {
            return khachHangDAO.findById(id);
        } catch (SQLException e) {
            throw new RuntimeException("Failed to get customer", e);
        }
    }
    
    @Override
    public KhachHang save(KhachHang khachHang) {
        try {
            return khachHangDAO.save(khachHang);
        } catch (SQLException e) {
            throw new RuntimeException("Failed to save customer", e);
        }
    }
    
    @Override
    public void deleteById(Integer id) {
        try {
            khachHangDAO.deleteById(id);
        } catch (SQLException e) {
            throw new RuntimeException("Failed to delete customer", e);
        }
    }
    
    
    @Override
    public List<KhachHang> getSearchTable(String text, String searchType) {
        text = text.toLowerCase();
        List<KhachHang> result = new ArrayList<>();
        
        switch (searchType) {
            case "Tất cả" -> {
                for (KhachHang e : this.getAll()) {
                    if ((e.getTenKhachHang() != null && e.getTenKhachHang().toLowerCase().contains(text))
                            || (e.getDiaChi() != null && e.getDiaChi().toLowerCase().contains(text))
                            || (e.getSoDienThoai()!= null && e.getSoDienThoai().toLowerCase().contains(text))) {
                        result.add(e);
                    }
                }
            }
            case "Tên" -> {
                for (KhachHang e : this.getAll()) {
                    if (e.getTenKhachHang() != null && e.getTenKhachHang().toLowerCase().contains(text)) {
                        result.add(e);
                    }
                }
            }
            case "Địa chỉ" -> {
                for (KhachHang e : this.getAll()) {
                    if (e.getDiaChi() != null && e.getDiaChi().toLowerCase().contains(text)) {
                        result.add(e);
                    }
                }
            }
            case "Sđt" -> {
                for (KhachHang e : this.getAll()) {
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

    @Override
    public Optional<KhachHang> findBySdt(String sdt) {
        return khachHangDAO.findBySdt(sdt);
    }

}