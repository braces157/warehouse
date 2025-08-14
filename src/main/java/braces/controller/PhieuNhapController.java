package braces.controller;

import braces.entity.PhieuNhap;
import java.util.List;

/**
 *
 * @author Braces
 */
public interface PhieuNhapController extends BaseController<PhieuNhap, Integer> {
    List<PhieuNhap> getByNhaCungCap(Integer maNhaCungCap);
    List<PhieuNhap> getFilterTable(Integer trangThai, long fromPrice, long toPrice);
}
