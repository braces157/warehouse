package braces.controller.impl;

import braces.dao.CTPhieuNhapDAO;
import braces.entity.CTPhieuNhap;
import braces.controller.CTPhieuNhapController;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 *
 * @author Braces
 */
public class CTPhieuNhapControllerImpl implements CTPhieuNhapController {

    private final CTPhieuNhapDAO ctPhieuNhapDAO;

    public CTPhieuNhapControllerImpl(CTPhieuNhapDAO ctPhieuNhapDAO) {
        this.ctPhieuNhapDAO = ctPhieuNhapDAO;
    }

    @Override
    public List<CTPhieuNhap> getAll() {
        try {
            return ctPhieuNhapDAO.findAll();
        } catch (SQLException e) {
            throw new RuntimeException("Failed to get import voucher details", e);
        }
    }

    @Override
    public Optional<CTPhieuNhap> getById(Integer id) {
        try {
            return ctPhieuNhapDAO.findById(id);
        } catch (SQLException e) {
            throw new RuntimeException("Failed to get import voucher detail", e);
        }
    }

    @Override
    public CTPhieuNhap save(CTPhieuNhap ctPhieuNhap) {
        try {
            return ctPhieuNhapDAO.save(ctPhieuNhap);
        } catch (SQLException e) {
            throw new RuntimeException("Failed to save import voucher detail", e);
        }
    }

    @Override
    public void deleteById(Integer id) {
        try {
            ctPhieuNhapDAO.deleteById(id);
        } catch (SQLException e) {
            throw new RuntimeException("Failed to delete import voucher detail", e);
        }
    }

    @Override
    public List<CTPhieuNhap> getByPhieuNhap(Integer maPhieuNhap) {
        try {
            return ctPhieuNhapDAO.findByPhieuNhap(maPhieuNhap);
        } catch (SQLException e) {
            throw new RuntimeException("Failed to get import voucher details by voucher", e);
        }
    }

    @Override
    public List<CTPhieuNhap> getByPhienBan(String maPhienBan) {
        try {
            return ctPhieuNhapDAO.findByPhienBan(maPhienBan);
        } catch (SQLException e) {
            throw new RuntimeException("Failed to get import voucher details by version", e);
        }
    }

    @Override
    public List<CTPhieuNhap> getSearchTable(String text, String searchType) {
        text = text.toLowerCase();
        List<CTPhieuNhap> result = new ArrayList<>();

        switch (searchType) {
            case "Tất cả" -> {
                for (CTPhieuNhap e : this.getAll()) {
                    if ((e.getMaPhieuNhap() != null && e.getMaPhieuNhap().toString().toLowerCase().contains(text))
                            || (e.getMaPhienBan() != null && e.getMaPhienBan().toLowerCase().contains(text))
                            || (e.getSoLuong() != null && e.getSoLuong().toString().toLowerCase().contains(text))
                            || (e.getDonGia() != null && e.getDonGia().toString().toLowerCase().contains(text))) {
                        result.add(e);
                    }
                }
            }
            case "Mã phiếu nhập" -> {
                for (CTPhieuNhap e : this.getAll()) {
                    if (e.getMaPhieuNhap() != null && e.getMaPhieuNhap().toString().toLowerCase().contains(text)) {
                        result.add(e);
                    }
                }
            }
            case "Mã phiên bản" -> {
                for (CTPhieuNhap e : this.getAll()) {
                    if (e.getMaPhienBan() != null && e.getMaPhienBan().toLowerCase().contains(text)) {
                        result.add(e);
                    }
                }
            }
            case "Số lượng" -> {
                for (CTPhieuNhap e : this.getAll()) {
                    if (e.getSoLuong() != null && e.getSoLuong().toString().toLowerCase().contains(text)) {
                        result.add(e);
                    }
                }
            }
            case "Đơn giá" -> {
                for (CTPhieuNhap e : this.getAll()) {
                    if (e.getDonGia() != null && e.getDonGia().toString().toLowerCase().contains(text)) {
                        result.add(e);
                    }
                }
            }
            default ->
                throw new AssertionError("Unknown search type: " + searchType);
        }
        return result;
    }
}
