/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package braces.controller.impl;

import braces.dao.MauSacDAO;
import braces.entity.MauSac;
import braces.controller.MauSacController;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 *
 * @author Braces
 */
public class MauSacControllerImpl implements MauSacController {

    private final MauSacDAO mauSacDAO;

    public MauSacControllerImpl(MauSacDAO mauSacDAO) {
        this.mauSacDAO = mauSacDAO;
    }

    public MauSacControllerImpl() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<MauSac> getAll() {
        try {
            return mauSacDAO.findAll();
        } catch (SQLException e) {
            throw new RuntimeException("Failed to get colors", e);
        }
    }

    @Override
    public Optional<MauSac> getById(Integer id) {
        try {
            return mauSacDAO.findById(id);
        } catch (SQLException e) {
            throw new RuntimeException("Failed to get color", e);
        }
    }

    @Override
    public MauSac save(MauSac mauSac) {
        try {
            return mauSacDAO.save(mauSac);
        } catch (SQLException e) {
            throw new RuntimeException("Failed to save color", e);
        }
    }

    @Override
    public void deleteById(Integer id) {
        try {
            mauSacDAO.deleteById(id);
        } catch (SQLException e) {
            throw new RuntimeException("Failed to delete color", e);
        }
    }

    @Override
    public List<MauSac> getSearchTable(String text, String searchType) {
        text = text.toLowerCase();
        List<MauSac> result = new ArrayList<>();

        switch (searchType) {
            case "Tất cả" -> {
                for (MauSac e : this.getAll()) {
                    if ((e.getMaMau() != null && e.getMaMau().toString().toLowerCase().contains(text))
                            || (e.getTenMau() != null && e.getTenMau().toLowerCase().contains(text))) {
                        result.add(e);
                    }
                }
            }
            case "Mã màu" -> {
                for (MauSac e : this.getAll()) {
                    if (e.getMaMau() != null && e.getMaMau().toString().toLowerCase().contains(text)) {
                        result.add(e);
                    }
                }
            }
            case "Tên màu" -> {
                for (MauSac e : this.getAll()) {
                    if (e.getTenMau() != null && e.getTenMau().toLowerCase().contains(text)) {
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
