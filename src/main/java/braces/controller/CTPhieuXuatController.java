/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package braces.controller;

import braces.entity.CTPhieuXuat;
import java.util.List;

/**
 *
 * @author Braces
 */
public interface CTPhieuXuatController extends BaseController<CTPhieuXuat, Integer> {

    List<CTPhieuXuat> getByPhieuXuat(Integer maPhieuXuat);

    List<CTPhieuXuat> getByPhienBan(String maPhienBan);

}


