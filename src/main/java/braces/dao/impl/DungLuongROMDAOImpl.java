/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package braces.dao.impl;

import braces.dao.DungLuongROMDAO;
import braces.entity.DungLuongROM;
import braces.util.XQuery;
import braces.util.XJdbc;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

/**
 *
 * @author Braces
 */
public class DungLuongROMDAOImpl implements DungLuongROMDAO {
    
    @Override
    public List<DungLuongROM> findAll() throws SQLException {
        String sql = "SELECT * FROM DUNGLUONGROM";
        return XQuery.getBeanList(DungLuongROM.class, sql);
    }
    
    @Override
    public Optional<DungLuongROM> findById(Integer id) throws SQLException {
        String sql = "SELECT * FROM DUNGLUONGROM WHERE marom = ?";
        DungLuongROM dungLuongROM = XQuery.getSingleBean(DungLuongROM.class, sql, id);
        return Optional.ofNullable(dungLuongROM);
    }
    
    @Override
    public DungLuongROM save(DungLuongROM dungLuongROM) throws SQLException {
        if (dungLuongROM.getMaRom() == null) {
            return insert(dungLuongROM);
        } else {
            return update(dungLuongROM);
        }
    }
    
    private DungLuongROM insert(DungLuongROM dungLuongROM) throws SQLException {
        String sql = "INSERT INTO DUNGLUONGROM (kichthuocrom) VALUES (?)";
        Integer id = XJdbc.executeUpdate(sql,
                dungLuongROM.getKichThuocRom());
        dungLuongROM.setMaRom(id);
        return dungLuongROM;
    }
    
    private DungLuongROM update(DungLuongROM dungLuongROM) throws SQLException {
        String sql = "UPDATE DUNGLUONGROM SET kichthuocrom = ? WHERE marom = ?";
        XJdbc.executeUpdate(sql,
                dungLuongROM.getKichThuocRom(),
                dungLuongROM.getMaRom());
        return dungLuongROM;
    }
    
    @Override
    public void deleteById(Integer id) throws SQLException {
        String sql = "DELETE FROM DUNGLUONGROM WHERE marom = ?";
        XJdbc.executeUpdate(sql, id);
    }
}