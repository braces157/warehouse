/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package braces.controller.impl;


import braces.dao.ThuocTinhSanPhamDAO;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Optional;

import braces.controller.ThuocTinhSanPhamController;
import braces.entity.ThuocTinhSanPham;
import java.util.List;


public class ThuocTinhSanPhamControllerImpl implements ThuocTinhSanPhamController {
    
    private final ThuocTinhSanPhamDAO thuocTinhSanPhamDAO;
    
    public ThuocTinhSanPhamControllerImpl(ThuocTinhSanPhamDAO thuocTinhSanPhamDAO) {
        this.thuocTinhSanPhamDAO = thuocTinhSanPhamDAO;
    }
    
    @Override
    public List<ThuocTinhSanPham> getAll() {
        try {
            return thuocTinhSanPhamDAO.findAll();
        } catch (SQLException e) {
            throw new RuntimeException("Failed to get product attributes", e);
        }
    }
    
    @Override
    public Optional<ThuocTinhSanPham> getById(Integer id) {
        try {
            return thuocTinhSanPhamDAO.findById(id);
        } catch (SQLException e) {
            throw new RuntimeException("Failed to get product attribute", e);
        }
    }
    
    @Override
    public ThuocTinhSanPham save(ThuocTinhSanPham thuocTinhSanPham) {
        try {
            return thuocTinhSanPhamDAO.save(thuocTinhSanPham);
        } catch (SQLException e) {
            throw new RuntimeException("Failed to save product attribute", e);
        }
    }
    
    @Override
    public void deleteById(Integer id) {
        try {
            thuocTinhSanPhamDAO.deleteById(id);
        } catch (SQLException e) {
            throw new RuntimeException("Failed to delete product attribute", e);
        }
    }
    
    @Override
    public List<ThuocTinhSanPham> getBySanPham(Integer maSP) {
        try {
            return thuocTinhSanPhamDAO.findBySanPham(maSP);
        } catch (SQLException e) {
            throw new RuntimeException("Failed to get product attributes by product", e);
        }
    }
    
    @Override
    public List<ThuocTinhSanPham> getSearchTable(String text, String searchType) {
       return null;
    }
}