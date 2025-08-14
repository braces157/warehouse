/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

// ThuocTinhSanPhamController.java
package braces.controller;

import braces.entity.ThuocTinhSanPham;
import java.util.List;

/**
 *
 * @author Braces
 */
public interface ThuocTinhSanPhamController extends BaseController<ThuocTinhSanPham, Integer> {
    List<ThuocTinhSanPham> getBySanPham(Integer maSP);
}

