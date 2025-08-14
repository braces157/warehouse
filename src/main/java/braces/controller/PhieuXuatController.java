/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package braces.controller;

import braces.entity.PhieuXuat;
import java.util.List;

/**
 *
 * @author Braces
 */
public interface PhieuXuatController extends BaseController<PhieuXuat, Integer> {
    List<PhieuXuat> getByKhachHang(Integer maKH);
    List<PhieuXuat> getFilterTable(Integer trangThai, long fromPrice, long toPrice);
    
}