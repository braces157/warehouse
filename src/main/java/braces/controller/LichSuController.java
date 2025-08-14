package braces.controller;

import braces.dto.LichSuDTO;
import java.util.Date;
import java.util.List;

/**
 * Controller interface for LichSu operations
 * 
 * @author Braces
 */
public interface LichSuController {
    
    /**
     * Get import history within date range
     * 
     * @param start Start date
     * @param end End date
     * @return List of import history records
     */
    List<LichSuDTO> getLichSuNhap(Date start, Date end);
    
    /**
     * Get export history within date range
     * 
     * @param start Start date
     * @param end End date
     * @return List of export history records
     */
    List<LichSuDTO> getLichSuXuat(Date start, Date end);
}
