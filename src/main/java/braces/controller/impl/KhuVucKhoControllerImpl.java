/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package braces.controller.impl;

import braces.dao.KhuVucKhoDAO;
import braces.entity.KhuVucKho;
import braces.controller.KhuVucKhoController;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 *
 * @author Braces
 */
public class KhuVucKhoControllerImpl implements KhuVucKhoController {

    private final KhuVucKhoDAO khuVucKhoDAO;

    public KhuVucKhoControllerImpl(KhuVucKhoDAO khuVucKhoDAO) {
        this.khuVucKhoDAO = khuVucKhoDAO;
    }

    @Override
    public List<KhuVucKho> getAll() {
        try {
            return khuVucKhoDAO.findAll();
        } catch (SQLException e) {
            throw new RuntimeException("Failed to get warehouse areas", e);
        }
    }

    @Override
    public Optional<KhuVucKho> getById(Integer id) {
        try {
            return khuVucKhoDAO.findById(id);
        } catch (SQLException e) {
            throw new RuntimeException("Failed to get warehouse area", e);
        }
    }

    @Override
    public KhuVucKho save(KhuVucKho khuVucKho) {
        try {
            return khuVucKhoDAO.save(khuVucKho);
        } catch (SQLException e) {
            throw new RuntimeException("Failed to save warehouse area", e);
        }
    }

    @Override
    public void deleteById(Integer id) {
        try {
            khuVucKhoDAO.deleteById(id);
        } catch (SQLException e) {
            throw new RuntimeException("Failed to delete warehouse area", e);
        }
    }

    public List<KhuVucKho> getSearchTable(String text, String searchType) {
        text = text.toLowerCase();
        List<KhuVucKho> result = new ArrayList<>();

        switch (searchType) {
            case "Tất cả" -> {
                for (KhuVucKho e : this.getAll()) {
                    if ((e.getMaKhuVuc() != null && e.getMaKhuVuc().toString().toLowerCase().contains(text))
                            || (e.getTenKhuVuc() != null && e.getTenKhuVuc().toLowerCase().contains(text))
                            || (e.getDiaChi() != null && e.getDiaChi().toLowerCase().contains(text))) {
                        result.add(e);
                    }
                }
            }
            case "Mã" -> {
                for (KhuVucKho e : this.getAll()) {
                    if (e.getMaKhuVuc() != null && e.getMaKhuVuc().toString().toLowerCase().contains(text)) {
                        result.add(e);
                    }
                }
            }
            case "Tên" -> {
                for (KhuVucKho e : this.getAll()) {
                    if (e.getTenKhuVuc() != null && e.getTenKhuVuc().toLowerCase().contains(text)) {
                        result.add(e);
                    }
                }
            }
            case "Địa chỉ" -> {
                for (KhuVucKho e : this.getAll()) {
                    if (e.getDiaChi() != null && e.getDiaChi().toLowerCase().contains(text)) {
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
