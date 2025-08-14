/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package braces.controller;

import braces.entity.PhienBan;
import java.util.List;

/**
 *
 * @author Braces
 */
public interface PhienBanController extends BaseController<PhienBan, String> {

    List<PhienBan> getBySanPham(Integer maSP);
}
