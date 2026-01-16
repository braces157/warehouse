/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package braces.controller.impl;

import braces.dao.DungLuongROMDAO;
import braces.entity.DungLuongROM;
import braces.controller.DungLuongROMController;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 *
 * @author Braces
 */
public class DungLuongROMControllerImpl implements DungLuongROMController {
    
    private final DungLuongROMDAO dungLuongROMDAO;
    
    public DungLuongROMControllerImpl(DungLuongROMDAO dungLuongROMDAO) {
        this.dungLuongROMDAO = dungLuongROMDAO;
    }
    
    @Override
    public List<DungLuongROM> getAll() {
        try {
            return dungLuongROMDAO.findAll();
        } catch (SQLException e) {
            throw new RuntimeException("Failed to get storage capacities", e);
        }
    }
    
    @Override
    public Optional<DungLuongROM> getById(Integer id) {
        try {
            return dungLuongROMDAO.findById(id);
        } catch (SQLException e) {
            throw new RuntimeException("Failed to get storage capacity", e);
        }
    }
    
    @Override
    public DungLuongROM save(DungLuongROM dungLuongROM) {
        try {
            return dungLuongROMDAO.save(dungLuongROM);
        } catch (SQLException e) {
            throw new RuntimeException("Failed to save storage capacity", e);
        }
    }
    
    @Override
    public void deleteById(Integer id) {
        try {
            dungLuongROMDAO.deleteById(id);
        } catch (SQLException e) {
            throw new RuntimeException("Failed to delete storage capacity", e);
        }
    }

}