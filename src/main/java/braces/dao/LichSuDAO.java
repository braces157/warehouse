/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package braces.dao;

import braces.dto.LichSuDTO;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Braces
 */
public interface LichSuDAO {
    List<LichSuDTO> getLichSuNhap(Date start, Date end);
    List<LichSuDTO> getLichSuXuat(Date start, Date end);
    
}
