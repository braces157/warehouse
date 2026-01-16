/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package braces.dao.impl;

import braces.dao.DungLuongRAMDAO;
import braces.entity.DungLuongRAM;
import braces.util.XQuery;
import braces.util.XJdbc;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

/**
 *
 * @author Braces
 */
public class DungLuongRAMDAOImpl implements DungLuongRAMDAO {
    
    @Override
    public List<DungLuongRAM> findAll() throws SQLException {
        String sql = "SELECT * FROM DUNGLUONGRAM";
        return XQuery.getBeanList(DungLuongRAM.class, sql);
    }
    
    @Override
    public Optional<DungLuongRAM> findById(Integer id) throws SQLException {
        String sql = "SELECT * FROM DUNGLUONGRAM WHERE maram = ?";
        DungLuongRAM dungLuongRAM = XQuery.getSingleBean(DungLuongRAM.class, sql, id);
        return Optional.ofNullable(dungLuongRAM);
    }
    
    @Override
    public DungLuongRAM save(DungLuongRAM dungLuongRAM) throws SQLException {
        if (dungLuongRAM.getMaRam() == null) {
            return insert(dungLuongRAM);
        } else {
            return update(dungLuongRAM);
        }
    }
    
    private DungLuongRAM insert(DungLuongRAM dungLuongRAM) throws SQLException {
        String sql = "INSERT INTO DUNGLUONGRAM (kichthuocram) VALUES (?)";
        Integer id = XJdbc.executeUpdate(sql,
                dungLuongRAM.getKichThuocRam());
        dungLuongRAM.setMaRam(id);
        return dungLuongRAM;
    }
    
    private DungLuongRAM update(DungLuongRAM dungLuongRAM) throws SQLException {
        String sql = "UPDATE DUNGLUONGRAM SET kichthuocram = ? WHERE maram = ?";
        XJdbc.executeUpdate(sql,
                dungLuongRAM.getKichThuocRam(),
                dungLuongRAM.getMaRam());
        return dungLuongRAM;
    }
    
    @Override
    public void deleteById(Integer id) throws SQLException {
        String sql = "DELETE FROM DUNGLUONGRAM WHERE maram = ?";
        XJdbc.executeUpdate(sql, id);
    }
}