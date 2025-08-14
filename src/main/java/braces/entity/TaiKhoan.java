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
public class TaiKhoan {
    private Integer manv;
    private String username;
    private String matKhau;
    private Integer trangThai;
    private Integer role;
    private String tenNv;
    private String soDienThoai;
    private String email;
}