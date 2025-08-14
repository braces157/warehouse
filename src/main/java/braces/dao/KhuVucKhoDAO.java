/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package braces.dao;

import braces.entity.KhuVucKho;
import java.util.Optional;

/**
 *
 * @author Braces
 */
public interface KhuVucKhoDAO extends BaseDAO<KhuVucKho, Integer>{
    Optional<KhuVucKho> findByTenKho(String ten);
}
