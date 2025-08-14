/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package braces.controller.impl;

import braces.dao.DungLuongRAMDAO;
import braces.entity.DungLuongRAM;
import braces.controller.DungLuongRAMController;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 *
 * @author Braces
 */
public class DungLuongRAMControllerImpl implements DungLuongRAMController {
    
    private final DungLuongRAMDAO dungLuongRAMDAO;
    
    public DungLuongRAMControllerImpl(DungLuongRAMDAO dungLuongRAMDAO) {
        this.dungLuongRAMDAO = dungLuongRAMDAO;
    }
    
    @Override
    public List<DungLuongRAM> getAll() {
        try {
            return dungLuongRAMDAO.findAll();
        } catch (SQLException e) {
            throw new RuntimeException("Failed to get RAM capacities", e);
        }
    }
    
    @Override
    public Optional<DungLuongRAM> getById(Integer id) {
        try {
            return dungLuongRAMDAO.findById(id);
        } catch (SQLException e) {
            throw new RuntimeException("Failed to get RAM capacity", e);
        }
    }
    
    @Override
    public DungLuongRAM save(DungLuongRAM dungLuongRAM) {
        try {
            return dungLuongRAMDAO.save(dungLuongRAM);
        } catch (SQLException e) {
            throw new RuntimeException("Failed to save RAM capacity", e);
        }
    }
    
    @Override
    public void deleteById(Integer id) {
        try {
            dungLuongRAMDAO.deleteById(id);
        } catch (SQLException e) {
            throw new RuntimeException("Failed to delete RAM capacity", e);
        }
    }
    
    @Override
    public List<DungLuongRAM> getSearchTable(String text, String searchType) {
        text = text.toLowerCase();
        List<DungLuongRAM> result = new ArrayList<>();
        
        switch (searchType) {
            case "Tất cả" -> {
                for (DungLuongRAM e : this.getAll()) {
                    if ((e.getMaRam() != null && e.getMaRam().toString().toLowerCase().contains(text))
                            || (e.getKichThuocRam() != null && e.getKichThuocRam().toString().toLowerCase().contains(text))) {
                        result.add(e);
                    }
                }
            }
            case "Mã RAM" -> {
                for (DungLuongRAM e : this.getAll()) {
                    if (e.getMaRam() != null && e.getMaRam().toString().toLowerCase().contains(text)) {
                        result.add(e);
                    }
                }
            }
            case "Kích thước RAM" -> {
                for (DungLuongRAM e : this.getAll()) {
                    if (e.getKichThuocRam() != null && e.getKichThuocRam().toString().toLowerCase().contains(text)) {
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