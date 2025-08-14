/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package braces.dao.impl;

import braces.dao.ThongKeDAO;
import braces.dto.ThongKeDTO;
import braces.util.XQuery;
import java.util.List;

/**
 *
 * @author Braces
 */
public class ThongKeDAOImpl implements ThongKeDAO {

    @Override
    public List<ThongKeDTO> thongKeTheoNgay(int thang, int nam) {
        String sql = """
                    DECLARE @thang INT = ?; 
                     DECLARE @nam INT = ?;
                     DECLARE @ngayString NVARCHAR(10) = CONVERT(NVARCHAR(10), @nam) + '-' 
                         + RIGHT('0' + CONVERT(NVARCHAR(2), @thang), 2) + '-01';
                     
                     WITH numbers AS (
                         SELECT ROW_NUMBER() OVER (ORDER BY (SELECT NULL)) - 1 AS number
                         FROM master..spt_values
                     ),
                     dates AS (
                         SELECT DATEADD(DAY, c.number, @ngayString) AS ngay
                         FROM numbers c
                         WHERE DATEADD(DAY, c.number, @ngayString) 
                               <= DATEADD(DAY, -1, DATEADD(MONTH, DATEDIFF(MONTH, 0, @ngayString) + 1, 0))
                     ),
                     daily_imports AS (
                         SELECT 
                             CAST(pn.thoigian AS DATE) AS ngay,
                             SUM(CAST(ct.soluong AS BIGINT)) AS so_luong_san_pham_nhap,
                             SUM(CAST(ct.soluong AS BIGINT) * CAST(ct.dongia AS BIGINT)) AS tong_so_tien_nhap
                         FROM PHIEUNHAP pn
                         INNER JOIN CTPHIEUNHAP ct ON pn.maphieunhap = ct.maphieunhap
                         WHERE pn.trangthai = 1
                             AND pn.thoigian >= @ngayString
                             AND pn.thoigian < DATEADD(MONTH, 1, @ngayString)
                         GROUP BY CAST(pn.thoigian AS DATE)
                     ),
                     daily_exports AS (
                         SELECT 
                             CAST(px.thoigian AS DATE) AS ngay,
                             SUM(CAST(ct.soluong AS BIGINT)) AS so_luong_san_pham_xuat,
                             SUM(CAST(ct.soluong AS BIGINT) * CAST(ct.dongia AS BIGINT)) AS tong_so_tien_xuat
                         FROM PHIEUXUAT px
                         INNER JOIN CTPHIEUXUAT ct ON px.maphieuxuat = ct.maphieuxuat
                         WHERE px.trangthai = 1
                             AND px.thoigian >= @ngayString
                             AND px.thoigian < DATEADD(MONTH, 1, @ngayString)
                         GROUP BY CAST(px.thoigian AS DATE)
                     )
                     SELECT 
                         FORMAT(d.ngay, 'dd/MM/yyyy') AS [thoigian],
                         ISNULL(di.so_luong_san_pham_nhap, 0) AS soluongnhap,
                         ISNULL(de.so_luong_san_pham_xuat, 0) AS soluongxuat,
                         ISNULL(di.tong_so_tien_nhap, 0) AS tongtiennhap,
                         ISNULL(de.tong_so_tien_xuat, 0) AS tongtienxuat
                     FROM dates d
                     LEFT JOIN daily_imports di ON d.ngay = di.ngay
                     LEFT JOIN daily_exports de ON d.ngay = de.ngay
                     ORDER BY d.ngay; """;
        return XQuery.getBeanList(ThongKeDTO.class, sql, thang, nam);
    }

    @Override
    public List<ThongKeDTO> thongKeTheoThang(int nam) {
        String sql = """
                     DECLARE @nam INT = ?;
                     WITH months AS (
                         SELECT 
                             @nam AS nam,
                             ROW_NUMBER() OVER (ORDER BY (SELECT NULL)) AS thang,
                             DATEFROMPARTS(@nam, ROW_NUMBER() OVER (ORDER BY (SELECT NULL)), 1) AS ngay_dau_thang
                         FROM (VALUES (1),(2),(3),(4),(5),(6),(7),(8),(9),(10),(11),(12)) AS t(n)
                     ),
                     monthly_imports AS (
                         SELECT 
                             YEAR(pn.thoigian) AS nam,
                             MONTH(pn.thoigian) AS thang,
                             SUM(CAST(ct.soluong AS BIGINT)) AS so_luong_san_pham_nhap,
                             SUM(CAST(ct.soluong AS BIGINT) * CAST(ct.dongia AS BIGINT)) AS tong_so_tien_nhap,
                             COUNT(DISTINCT pn.maphieunhap) AS so_phieu_nhap
                         FROM PHIEUNHAP pn
                         INNER JOIN CTPHIEUNHAP ct ON pn.maphieunhap = ct.maphieunhap
                         WHERE pn.trangthai = 1
                             AND YEAR(pn.thoigian) = @nam
                         GROUP BY YEAR(pn.thoigian), MONTH(pn.thoigian)
                     ),
                     monthly_exports AS (
                         SELECT 
                             YEAR(px.thoigian) AS nam,
                             MONTH(px.thoigian) AS thang,
                             SUM(CAST(ct.soluong AS BIGINT)) AS so_luong_san_pham_xuat,
                             SUM(CAST(ct.soluong AS BIGINT) * CAST(ct.dongia AS BIGINT)) AS tong_so_tien_xuat,
                             COUNT(DISTINCT px.maphieuxuat) AS so_phieu_xuat
                         FROM PHIEUXUAT px
                         INNER JOIN CTPHIEUXUAT ct ON px.maphieuxuat = ct.maphieuxuat
                         WHERE px.trangthai = 1
                             AND YEAR(px.thoigian) = @nam
                         GROUP BY YEAR(px.thoigian), MONTH(px.thoigian)
                     )
                     SELECT 
                         CONCAT('Tháng ', FORMAT(m.thang, '0')) AS thoigian,
                         ISNULL(mi.so_luong_san_pham_nhap, 0) AS soluongnhap,
                         ISNULL(me.so_luong_san_pham_xuat, 0) AS soluongxuat,
                         ISNULL(mi.tong_so_tien_nhap,0) AS tongtiennhap,
                         ISNULL(me.tong_so_tien_xuat, 0) AS tongtienxuat
                     FROM months m
                     LEFT JOIN monthly_imports mi ON m.nam = mi.nam AND m.thang = mi.thang
                     LEFT JOIN monthly_exports me ON m.nam = me.nam AND m.thang = me.thang
                     ORDER BY m.thang;""";
        return XQuery.getBeanList(ThongKeDTO.class, sql, nam);
    }

    @Override
    public List<ThongKeDTO> thongKeTheoNam(int start, int end) {
        String sql = """
                 DECLARE @namBatDau INT = ?;
                     DECLARE @namKetThuc INT = ?;
                     
                     WITH years AS (
                         SELECT @namBatDau AS nam
                         UNION ALL
                         SELECT nam + 1
                         FROM years
                         WHERE nam < @namKetThuc
                     ),
                     yearly_imports AS (
                         SELECT 
                             YEAR(pn.thoigian) AS nam,
                             COUNT(DISTINCT pn.maphieunhap) AS so_phieu_nhap,
                             SUM(CAST(ct.soluong AS BIGINT) * CAST(ct.dongia AS BIGINT)) AS tong_tien_nhap
                         FROM PHIEUNHAP pn
                         INNER JOIN CTPHIEUNHAP ct ON pn.maphieunhap = ct.maphieunhap
                         WHERE pn.trangthai = 1
                             AND YEAR(pn.thoigian) BETWEEN @namBatDau AND @namKetThuc
                         GROUP BY YEAR(pn.thoigian)
                     ),
                     yearly_exports AS (
                         SELECT 
                             YEAR(px.thoigian) AS nam,
                             COUNT(DISTINCT px.maphieuxuat) AS so_phieu_xuat,
                             SUM(CAST(ct.soluong AS BIGINT) * CAST(ct.dongia AS BIGINT)) AS tong_tien_xuat
                         FROM PHIEUXUAT px
                         INNER JOIN CTPHIEUXUAT ct ON px.maphieuxuat = ct.maphieuxuat
                         WHERE px.trangthai = 1
                             AND YEAR(px.thoigian) BETWEEN @namBatDau AND @namKetThuc
                         GROUP BY YEAR(px.thoigian)
                     )
                     SELECT 
                         Cast(y.nam as varchar(5)) AS ThoiGian,
                         ISNULL(yi.so_phieu_nhap, 0) AS SoLuongNhap,
                         ISNULL(ye.so_phieu_xuat, 0) AS SoLuongXuat,
                         ISNULL(yi.tong_tien_nhap, 0) AS TongTienNhap,
                         ISNULL(ye.tong_tien_xuat, 0) AS TongTienXuat
                     FROM years y
                     LEFT JOIN yearly_imports yi ON y.nam = yi.nam
                     LEFT JOIN yearly_exports ye ON y.nam = ye.nam
                     ORDER BY y.nam;""";
        return XQuery.getBeanList(ThongKeDTO.class, sql, start, end);
    }



}
