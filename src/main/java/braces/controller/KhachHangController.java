/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package braces.controller;

import braces.entity.KhachHang;
import java.util.Optional;

/**
 *
 * @author Braces
 */
public interface KhachHangController extends BaseController<KhachHang, Integer> {
    Optional<KhachHang> findBySdt(String sdt);
}