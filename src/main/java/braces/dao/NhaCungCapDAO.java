/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package braces.dao;

import braces.entity.NhaCungCap;
import java.util.Optional;

/**
 *
 * @author Braces
 */
public interface NhaCungCapDAO extends BaseDAO<NhaCungCap, Integer> {

    Optional<NhaCungCap> findByTenNhaCungCap(String ten);
}
