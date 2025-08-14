/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package braces.dao.impl;

import braces.dao.LichSuDAO;
import braces.dto.LichSuDTO;
import braces.util.XQuery;
import java.util.Date;
import java.util.List;

/**
 * Implementation of LichSuDAO interface
 * 
 * @author Braces
 */
public class LichSuDAOImpl implements LichSuDAO {

    @Override
    public List<LichSuDTO> getLichSuNhap(Date start, Date end) {
        String sql = """
            SELECT 
                sp.tensanpham as TenSanPham,
                pb.maphienbansp as MaPhienBan,
                SUM(ct.soluong) as SoLuong,
                AVG(ct.dongia) as GiaTien
            FROM CTPHIEUNHAP ct
            JOIN phieunhap pn ON pn.maphieunhap = ct.maphieunhap
            JOIN PHIENBAN pb ON pb.maphienbansp = ct.maphienban
            JOIN sanpham sp ON pb.masp = sp.masp
            WHERE 
                pn.thoigian >= ? 
                AND pn.thoigian <= ?
            GROUP BY 
                sp.tensanpham, pb.maphienbansp
            ORDER BY sp.tensanpham, pb.maphienbansp
            """;
        
        return XQuery.getBeanList(LichSuDTO.class, sql, start, end);
    }

    @Override
    public List<LichSuDTO> getLichSuXuat(Date start, Date end) {
        String sql = """
            SELECT 
                sp.tensanpham as TenSanPham,
                pb.maphienbansp as MaPhienBan,
                SUM(ct.soluong) as SoLuong,
                AVG(ct.dongia) as GiaTien
            FROM CTPHIEUXUAT ct
            JOIN phieuxuat px ON px.maphieuxuat = ct.maphieuxuat
            JOIN PHIENBAN pb ON pb.maphienbansp = ct.maphienban
            JOIN sanpham sp ON pb.masp = sp.masp
            WHERE 
                px.thoigian >= ? 
                AND px.thoigian <= ?
            GROUP BY 
                sp.tensanpham, pb.maphienbansp
            ORDER BY sp.tensanpham, pb.maphienbansp
            """;
        
        return XQuery.getBeanList(LichSuDTO.class, sql, start, end);
    }
}