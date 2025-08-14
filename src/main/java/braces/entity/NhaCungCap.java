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
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class NhaCungCap {
    private Integer maNhaCungCap;
    private String tenNhaCungCap;
    private String diaChi;
    private String email;
    private String soDienThoai;
}