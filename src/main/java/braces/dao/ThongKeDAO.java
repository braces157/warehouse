/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package braces.dao;

import braces.dto.ThongKeDTO;
import java.util.List;

/**
 *
 * @author Braces
 */
public interface ThongKeDAO {
    public List<ThongKeDTO> thongKeTheoNgay(int thang,int nam);
    public List<ThongKeDTO> thongKeTheoThang(int nam);
    public List<ThongKeDTO> thongKeTheoNam(int start,int end);
}
