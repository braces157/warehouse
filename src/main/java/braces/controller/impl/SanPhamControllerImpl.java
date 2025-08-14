/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package braces.controller.impl;


import braces.dao.SanPhamDAO;
import braces.entity.SanPham;
import braces.controller.SanPhamController;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import java.util.Optional;
import javax.swing.JTable;

/**
 *
 * @author Braces
 */
public class SanPhamControllerImpl implements SanPhamController {

    private final SanPhamDAO sanPhamDAO;

    public SanPhamControllerImpl(SanPhamDAO sanPhamDAO) {
        this.sanPhamDAO = sanPhamDAO;
    }

    @Override
    public List<SanPham> getAll() {
        try {
            return sanPhamDAO.findAll();
        } catch (SQLException e) {
            throw new RuntimeException("Failed to get products", e);
        }
    }

    @Override
    public Optional<SanPham> getById(Integer id) {
        try {
            return sanPhamDAO.findById(id);
        } catch (SQLException e) {
            throw new RuntimeException("Failed to get product", e);
        }
    }

    @Override
    public SanPham save(SanPham sanPham) {
        try {
            return sanPhamDAO.save(sanPham);
        } catch (SQLException e) {
            throw new RuntimeException("Failed to save product", e);
        }
    }

    @Override
    public void deleteById(Integer id) {
        try {
            sanPhamDAO.deleteById(id);
        } catch (SQLException e) {
            throw new RuntimeException("Failed to delete product", e);
        }
    }

    @Override
    public List<SanPham> getSearchTable(String text, String searchType) {
        text = text.toLowerCase();
        List<SanPham> result = new ArrayList<>();

        switch (searchType) {
            case "Tất cả" -> {
                for (SanPham e : this.getAll()) {
                    if ((e.getMaSP() != null && e.getMaSP().toString().toLowerCase().contains(text))
                            || (e.getTenSanPham() != null && e.getTenSanPham().toLowerCase().contains(text))
                            || (e.getXuatXu() != null && e.getXuatXu().toLowerCase().contains(text))
                            || (e.getChipXuLy() != null && e.getChipXuLy().toLowerCase().contains(text))
                            || (e.getMaKhuVucKho() != null && e.getMaKhuVucKho().toString().toLowerCase().contains(text))) {
                        result.add(e);
                    }
                }
            }
            case "Mã" -> {
                for (SanPham e : this.getAll()) {
                    if (e.getMaSP() != null && e.getMaSP().toString().toLowerCase().contains(text)) {
                        result.add(e);
                    }
                }
            }
            case "Tên" -> {
                for (SanPham e : this.getAll()) {
                    if (e.getTenSanPham() != null && e.getTenSanPham().toLowerCase().contains(text)) {
                        result.add(e);
                    }
                }
            }
            case "Xuất xứ" -> {
                for (SanPham e : this.getAll()) {
                    if (e.getXuatXu() != null && e.getXuatXu().toLowerCase().contains(text)) {
                        result.add(e);
                    }
                }
            }
            case "Chip" -> {
                for (SanPham e : this.getAll()) {
                    if (e.getChipXuLy() != null && e.getChipXuLy().toLowerCase().contains(text)) {
                        result.add(e);
                    }
                }
            }
            case "Mã kho" -> {
                for (SanPham e : this.getAll()) {
                    if (e.getMaKhuVucKho() != null && e.getMaKhuVucKho().toString().toLowerCase().contains(text)) {
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
