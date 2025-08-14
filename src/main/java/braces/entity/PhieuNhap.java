/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package braces.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PhieuNhap {

    private Integer maPhieuNhap;
    private Integer maNhaCungCap;
    private Integer nguoiTaoPhieuNhap;
    private Date thoiGian;
    private long tongTien;
    private Integer trangThai;
}
