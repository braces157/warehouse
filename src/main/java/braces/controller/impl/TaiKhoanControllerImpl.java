/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package braces.controller.impl;

import braces.dao.TaiKhoanDAO;
import braces.entity.TaiKhoan;
import braces.controller.TaiKhoanController;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 *
 * @author Braces
 */
public class TaiKhoanControllerImpl implements TaiKhoanController {

    private final TaiKhoanDAO taiKhoanDAO;

    public TaiKhoanControllerImpl(TaiKhoanDAO taiKhoanDAO) {
        this.taiKhoanDAO = taiKhoanDAO;
    }

    @Override
    public List<TaiKhoan> getAll() {
        try {
            return taiKhoanDAO.findAll();
        } catch (SQLException e) {
            throw new RuntimeException("Failed to get accounts", e);
        }
    }
    @Override
    public  Optional<TaiKhoan> findByUsername(String username) {
         try {
            return taiKhoanDAO.findByUsername(username);
        } catch (SQLException e) {
            throw new RuntimeException("Failed to get accounts", e);
        }
    }
    @Override
    public Optional<TaiKhoan> getById(Integer id) {
        try {
            return taiKhoanDAO.findById(id);
        } catch (SQLException e) {
            throw new RuntimeException("Failed to get account", e);
        }
    }

    @Override
    public TaiKhoan save(TaiKhoan taiKhoan) {
        try {
            return taiKhoanDAO.save(taiKhoan);
        } catch (SQLException e) {
            throw new RuntimeException("Failed to save account", e);
        }
    }

    @Override
    public void deleteById(Integer id) {
        try {
            taiKhoanDAO.deleteById(id);
        } catch (SQLException e) {
            throw new RuntimeException("Failed to delete account", e);
        }
    }

    public List<TaiKhoan> getSearchTable(String text, String searchType) {
        text = text.toLowerCase();
        List<TaiKhoan> result = new ArrayList<>();

        switch (searchType) {
            case "Tất cả" -> {
                for (TaiKhoan e : this.getAll()) {
                    if ((e.getManv() != null && e.getManv().toString().toLowerCase().contains(text))
                            || (e.getUsername() != null && e.getUsername().toLowerCase().contains(text))
                            || (e.getTenNv() != null && e.getTenNv().toLowerCase().contains(text))
                            || (e.getSoDienThoai() != null && e.getSoDienThoai().toLowerCase().contains(text))
                            || (e.getEmail() != null && e.getEmail().toLowerCase().contains(text))
                            || (e.getRole() != null && getRoleText(e.getRole()).toLowerCase().contains(text))
                            || (e.getTrangThai() != null && getTrangThaiText(e.getTrangThai()).toLowerCase().contains(text))) {
                        result.add(e);
                    }
                }
            }
            case "Mã" -> {
                for (TaiKhoan e : this.getAll()) {
                    if (e.getManv() != null && e.getManv().toString().toLowerCase().contains(text)) {
                        result.add(e);
                    }
                }
            }
            case "Tài khoản" -> {
                for (TaiKhoan e : this.getAll()) {
                    if (e.getUsername() != null && e.getUsername().toLowerCase().contains(text)) {
                        result.add(e);
                    }
                }
            }
            case "Vai trò" -> {
                for (TaiKhoan e : this.getAll()) {
                    if (e.getRole() != null && getRoleText(e.getRole()).toLowerCase().contains(text)) {
                        result.add(e);
                    }
                }
            }
            case "Sđt" -> {
                for (TaiKhoan e : this.getAll()) {
                    if (e.getSoDienThoai() != null && e.getSoDienThoai().toLowerCase().contains(text)) {
                        result.add(e);
                    }
                }
            }
            case "Trạng thái" -> {
                for (TaiKhoan e : this.getAll()) {
                    if (e.getTrangThai() != null && getTrangThaiText(e.getTrangThai()).toLowerCase().contains(text)) {
                        result.add(e);
                    }
                }
            }
            case "Email" -> {
                for (TaiKhoan e : this.getAll()) {
                    if (e.getEmail() != null && e.getEmail().toLowerCase().contains(text)) {
                        result.add(e);
                    }
                }
            }
            case "Tên" -> {
                for (TaiKhoan e : this.getAll()) {
                    if (e.getTenNv() != null && e.getTenNv().toLowerCase().contains(text)) {
                        result.add(e);
                    }
                }
            }
            default ->
                throw new AssertionError("Unknown search type: " + searchType);
        }
        return result;
    }

    public String getRoleText(Integer role) {
        if (role == null) {
            return "";
        }
        return switch (role) {
            case 0 -> "Admin";
            case 1 ->
                "Nhân viên";
            case 2 ->
                "Quản lý";
            default ->
                "Không xác định";
        };
    }

    public String getTrangThaiText(Integer trangThai) {
        if (trangThai == null) {
            return "";
        }
        return switch (trangThai) {
            case 0 ->
                "Không hoạt động";
            case 1 ->
                "Hoạt động";
            default ->
                "Không xác định";
        };
    }

    public Integer getRole(String role) {
        if (role == null) {
            return 0;
        }
        return switch (role) {
            case "Nhân viên" ->
                1;
            case "Quản lý" ->
                2;
            default ->
                0;
        };

    }

    public Integer getTrangThai(String trangThai) {
        if (trangThai == null) {
            return 0;
        }
        return switch (trangThai) {
            case "Hoạt động" ->
                1;
            case "Không hoạt động" ->
                0;
            default ->
                0;
        };
    }



}
