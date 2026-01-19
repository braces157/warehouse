package braces.util;

import org.testng.Assert;
import org.testng.annotations.Test;

public class ValidationTest {

    @Test
    public void testIsEmpty_NullInput() {
        // TC001
        try {
            Assert.assertTrue(Validation.isEmpty(null));
        } catch (NullPointerException e) {
    
        }

    }


    @Test
    public void testIsEmpty_Null() {
       // TC001
       try {
           Assert.assertTrue(Validation.isEmpty(null));
       } catch (NullPointerException e) {
           Assert.fail("Code threw NPE on null input, expected true");
       }
    }

    @Test
    public void testIsEmpty_Whitespace() {
        // TC002
        Assert.assertTrue(Validation.isEmpty("   "));
    }

    @Test
    public void testIsEmpty_Valid() {
        // TC003
        Assert.assertFalse(Validation.isEmpty("data"));
    }

    @Test
    public void testIsPassword_TooShort() {
        // TC004
        Assert.assertFalse(Validation.isPassword("pass1"));
    }

    @Test
    public void testIsPassword_NoDigit() {
        // TC005
        Assert.assertFalse(Validation.isPassword("password"));
    }

    @Test
    public void testIsPassword_Valid() {
        // TC006
        Assert.assertTrue(Validation.isPassword("password123"));
    }

    @Test
    public void testIsEmail_Invalid() {
        // TC007
        Assert.assertFalse(Validation.isEmail("abc.com"));
    }

    @Test
    public void testIsEmail_Valid() {
        // TC008
        Assert.assertTrue(Validation.isEmail("test@example.com"));
    }

    @Test
    public void testIsNumber_InvalidString() {
        // TC009
        Assert.assertFalse(Validation.isNumber("abc"));
    }

    @Test
    public void testIsNumber_Negative() {
        // TC010
        Assert.assertFalse(Validation.isNumber("-5"));
    }

    @Test
    public void testIsNumber_Valid() {
        // TC011
        Assert.assertTrue(Validation.isNumber("100"));
    }

    @Test
    public void testIsPhoneNumber_InvalidLength() {
        // TC012
        Assert.assertFalse(Validation.isPhoneNumber("12345"));
    }

    @Test
    public void testIsPhoneNumber_Valid10Digits() {
        // TC013
        Assert.assertTrue(Validation.isPhoneNumber("0901234567"));
    }
}
