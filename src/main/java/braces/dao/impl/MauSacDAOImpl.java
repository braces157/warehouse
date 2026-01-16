/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package braces.dao.impl;

import braces.dao.MauSacDAO;
import braces.entity.MauSac;
import braces.util.XQuery;
import braces.util.XJdbc;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Optional;

/**
 *
 * @author Braces
 */
public class MauSacDAOImpl implements MauSacDAO {

    @Override
    public List<MauSac> findAll() throws SQLException {
        String sql = "SELECT * FROM MAUSAC";
        return XQuery.getBeanList(MauSac.class, sql);
    }

    @Override
    public Optional<MauSac> findById(Integer id) throws SQLException {
        String sql = "SELECT * FROM MAUSAC WHERE mamau = ?";
        MauSac mauSac = XQuery.getSingleBean(MauSac.class, sql, id);
        return Optional.ofNullable(mauSac);
    }

    @Override
    public MauSac save(MauSac mauSac) throws SQLException {
        if (mauSac.getMaMau() == null) {
            return insert(mauSac);
        } else {
            return update(mauSac);
        }
    }

    private MauSac insert(MauSac mauSac) throws SQLException {
        String sql = "INSERT INTO MAUSAC (tenmau) VALUES (?)";
        Integer id = XJdbc.executeUpdate(sql,
                mauSac.getTenMau());
        mauSac.setMaMau(id);
        return mauSac;
    }

    private MauSac update(MauSac mauSac) throws SQLException {
        String sql = "UPDATE MAUSAC SET tenmau = ? WHERE mamau = ?";
        XJdbc.executeUpdate(sql,
                mauSac.getTenMau(),
                mauSac.getMaMau());
        return mauSac;
    }

    @Override
    public void deleteById(Integer id) throws SQLException {
        String sql = "DELETE FROM MAUSAC WHERE mamau = ?";
        XJdbc.executeUpdate(sql, id);
    }

    @Override
    public Optional<MauSac> findByTenMau(String tenMau) {
        String sql = "SELECT * FROM MAUSAC WHERE tenmau = ?";
        MauSac mauSac = XQuery.getSingleBean(MauSac.class, sql, tenMau);
        return Optional.ofNullable(mauSac);

    }
}
