
package braces;

import braces.gui.Login;
import braces.util.BCrypt;
import com.formdev.flatlaf.FlatLaf;
import com.formdev.flatlaf.FlatLightLaf;


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
