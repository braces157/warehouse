/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package braces.controller;

import braces.entity.CTPhieuNhap;
import java.util.List;

/**
 *
 * @author Braces
 */
public interface CTPhieuNhapController extends BaseController<CTPhieuNhap, Integer> {

    List<CTPhieuNhap> getByPhieuNhap(Integer maPhieuNhap);

    List<CTPhieuNhap> getByPhienBan(String maPhienBan);

}

