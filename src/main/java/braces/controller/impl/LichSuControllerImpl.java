/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package braces.controller.impl;

import braces.controller.LichSuController;
import braces.dao.LichSuDAO;
import braces.dao.impl.LichSuDAOImpl;
import braces.dto.LichSuDTO;
import java.util.Date;
import java.util.List;

/**
 * Implementation of LichSuController interface
 * 
 * @author Braces
 */
public class LichSuControllerImpl implements LichSuController {
    
    private final LichSuDAO lichSuDAO;
    
    /**
     * Default constructor - initializes with LichSuDAOImpl
     */
    public LichSuControllerImpl() {
        this.lichSuDAO = new LichSuDAOImpl();
    }
    
    /**
     * Constructor with dependency injection
     * 
     * @param lichSuDAO DAO implementation to use
     */
    public LichSuControllerImpl(LichSuDAO lichSuDAO) {
        this.lichSuDAO = lichSuDAO;
    }
    
    @Override
    public List<LichSuDTO> getLichSuNhap(Date start, Date end) {
        try {
            // Validate input parameters
            if (start == null || end == null) {
                throw new IllegalArgumentException("Start date and end date cannot be null");
            }
            
            if (start.after(end)) {
                throw new IllegalArgumentException("Start date cannot be after end date");
            }
            
            // Call DAO method
            return lichSuDAO.getLichSuNhap(start, end);
            
        } catch (Exception e) {
            throw new RuntimeException("Failed to retrieve import history", e);
        }
    }
    
    @Override
    public List<LichSuDTO> getLichSuXuat(Date start, Date end) {
        try {
            // Validate input parameters
            if (start == null || end == null) {
                throw new IllegalArgumentException("Start date and end date cannot be null");
            }
            
            if (start.after(end)) {
                throw new IllegalArgumentException("Start date cannot be after end date");
            }
            
            // Call DAO method
            return lichSuDAO.getLichSuXuat(start, end);
            
        } catch (Exception e) {
            throw new RuntimeException("Failed to retrieve export history", e);
        }
    }
}