package braces.controller;

import braces.controller.impl.TaiKhoanControllerImpl;
import braces.dao.TaiKhoanDAO;
import braces.entity.TaiKhoan;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;

public class TaiKhoanControllerTest {

    private TaiKhoanDAO taiKhoanDAO;
    private TaiKhoanControllerImpl controller;

    @BeforeMethod
    public void setUp() {
        // Manual mocking to avoid annotation processing issues if any
        taiKhoanDAO = mock(TaiKhoanDAO.class);
        controller = new TaiKhoanControllerImpl(taiKhoanDAO);
    }

    @Test
    public void testGetRoleText_Admin() {
        String role = controller.getRoleText(0);
        Assert.assertEquals(role, "Admin");
    }

    @Test
    public void testGetRoleText_NhanVien() {
        String role = controller.getRoleText(1);
        Assert.assertEquals(role, "Nhân viên");
    }

    @Test
    public void testGetRole_Default() {
        Integer roleId = controller.getRole("RandomString");
        Assert.assertEquals(roleId, Integer.valueOf(0));
    }

    @Test
    public void testGetSearchTable_SearchName() throws SQLException {
        TaiKhoan user1 = new TaiKhoan();
        user1.setTenNv("Alice");
        TaiKhoan user2 = new TaiKhoan();
        user2.setTenNv("Bob");
        
        when(taiKhoanDAO.findAll()).thenReturn(Arrays.asList(user1, user2));
        
        List<TaiKhoan> result = controller.getSearchTable("Alice", "Tên");

        Assert.assertEquals(result.size(), 1);
        Assert.assertEquals(result.get(0).getTenNv(), "Alice");
    }

    @Test
    public void testGetSearchTable_SearchRole() throws SQLException {
        TaiKhoan user1 = new TaiKhoan();
        user1.setRole(0); // Admin
        TaiKhoan user2 = new TaiKhoan();
        user2.setRole(1); // Nhan vien
        
        when(taiKhoanDAO.findAll()).thenReturn(Arrays.asList(user1, user2));

        List<TaiKhoan> result = controller.getSearchTable("Admin", "Vai trò");

        Assert.assertEquals(result.size(), 1);
        Assert.assertEquals(result.get(0).getRole(), Integer.valueOf(0));
    }

    @Test
    public void testGetTrangThaiText_HoatDong() {
        String text = controller.getTrangThaiText(1);
        Assert.assertEquals(text, "Hoạt động");
    }

    @Test
    public void testGetTrangThaiText_KhongHoatDong() {
        String text = controller.getTrangThaiText(0);
        Assert.assertEquals(text, "Không hoạt động");
    }

    @Test
    public void testGetTrangThai_HoatDong() {
        Integer status = controller.getTrangThai("Hoạt động");
        Assert.assertEquals(status, Integer.valueOf(1));
    }

    @Test
    public void testSave_Account() throws SQLException {
        TaiKhoan tk = new TaiKhoan();
        tk.setUsername("testuser");
        
        when(taiKhoanDAO.save(any(TaiKhoan.class))).thenReturn(tk);
        
        TaiKhoan savedTk = controller.save(tk);
        
        Assert.assertNotNull(savedTk);
        Assert.assertEquals(savedTk.getUsername(), "testuser");
        verify(taiKhoanDAO).save(tk);
    }
}
