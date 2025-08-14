/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package braces;

import braces.controller.impl.KhachHangControllerImpl;
import braces.controller.impl.TaiKhoanControllerImpl;
import braces.dao.KhachHangDAO;
import braces.dao.impl.KhachHangDAOImpl;
import braces.dao.impl.TaiKhoanDAOImpl;
import braces.entity.KhachHang;
import braces.entity.TaiKhoan;
import braces.gui.Login;
import braces.gui.MainLayout;
import braces.util.BCrypt;
import braces.util.XAuth;
import com.formdev.flatlaf.FlatLaf;
import com.formdev.flatlaf.FlatLightLaf;
import java.util.List;

/**
 *
 * @author Braces
 */
public class Project {

    public static void main(String[] args) {

        FlatLaf.registerCustomDefaultsSource("style");
        FlatLightLaf.setup();
 new Login().setVisible(true);

        //TaiKhoanControllerImpl ct = new TaiKhoanControllerImpl(new TaiKhoanDAOImpl());
       // XAuth.taikhoan = ct.findByUsername("admin").get();
       // new MainLayout().setVisible(true);

    }
}
