/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package braces.entity;
import lombok.*;
/**
 *
 * @author Braces
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class KhachHang {

    private Integer maKH;
    private String tenKhachHang;
    private String diaChi;
    private String soDienThoai;
}
