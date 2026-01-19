package braces.util;

import org.testng.Assert;
import org.testng.annotations.Test;

public class BCryptTest {

    @Test
    public void testCompare_Success() throws Exception {
        // TC017
        String password = "pass";
        byte[] salt = BCrypt.gensalt();
        String hash = BCrypt.hashpw(password, salt);
        
        Assert.assertTrue(BCrypt.compare(password, hash));
    }

    @Test
    public void testCompare_Failure() throws Exception {
        // TC018
        String password = "pass";
        byte[] salt = BCrypt.gensalt();
        String hash = BCrypt.hashpw(password, salt);
        
        Assert.assertFalse(BCrypt.compare("wrong", hash));
    }
}
