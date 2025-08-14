/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package braces.controller;

import braces.entity.TaiKhoan;
import java.util.List;
import java.util.Optional;

/**
 *
 * @author Braces
 */
public interface TaiKhoanController extends BaseController<TaiKhoan, Integer> {
    public Optional<TaiKhoan> findByUsername(String username);

}