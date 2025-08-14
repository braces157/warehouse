/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package braces.dao;

import braces.entity.MauSac;
import java.util.Optional;

/**
 *
 * @author Braces
 */
public interface MauSacDAO extends BaseDAO<MauSac, Integer> {

    Optional<MauSac> findByTenMau(String tenMau);
}
