package braces.util;

import org.testng.Assert;
import org.testng.annotations.Test;

public class FormatterTest {

    @Test
    public void testFormatVND_Standard() {
        // TC014
        String result = Formatter.formatVND(1000000);
        // Depending on locale, it might be 1,000,000 or 1.000.000. 
        // The code uses DecimalFormat("###,###,###") which uses default locale symbols.
        // Assuming US/Standard. If system is Vietnamese, it might be different.
        // I will assert based on the csv expectation: "1,000,000VNĐ"
        Assert.assertEquals(result, "1,000,000VNĐ");
    }

    @Test
    public void testUnformatVND_Standard() {
        // TC015
        double result = Formatter.unformatVND("1,000,000VNĐ");
        Assert.assertEquals(result, 1000000.0, 0.001);
    }

    @Test
    public void testFormatNumber_LargeNumber() {
        // TC016
        String result = Formatter.formatNumber(1000000);
        Assert.assertEquals(result, "1M");
    }
}
