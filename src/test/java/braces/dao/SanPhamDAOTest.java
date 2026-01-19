package braces.dao;

import braces.dao.impl.SanPhamDAOImpl;
import braces.entity.SanPham;
import braces.util.XQuery;
import braces.util.XJdbc;
import org.mockito.MockedStatic;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;

public class SanPhamDAOTest {

    @Test
    public void testFindAll_Retrieve() throws SQLException {
        // TC024: Retrieve all products from DB (Mocked).
        try (MockedStatic<XQuery> mockedXQuery = mockStatic(XQuery.class)) {
            SanPham sp1 = new SanPham();
            sp1.setMaSP(1);
            sp1.setTenSanPham("iPhone 15");
            
            SanPham sp2 = new SanPham();
            sp2.setMaSP(2);
            sp2.setTenSanPham("Samsung S24");
            
            List<SanPham> mockList = Arrays.asList(sp1, sp2);
            
            mockedXQuery.when(() -> XQuery.getBeanList(eq(SanPham.class), anyString()))
                    .thenReturn(mockList);
            
            SanPhamDAOImpl dao = new SanPhamDAOImpl();
            List<SanPham> result = dao.findAll();
            
            Assert.assertNotNull(result);
            Assert.assertEquals(result.size(), 2);
            Assert.assertEquals(result.get(0).getTenSanPham(), "iPhone 15");
        }
    }

    @Test
    public void testFindByKhuVuc_Filter() throws SQLException {
        // TC025: Filter products by warehouse area (Mocked).
        try (MockedStatic<XQuery> mockedXQuery = mockStatic(XQuery.class)) {
            Integer khuVucId = 1;
            SanPham sp = new SanPham();
            sp.setMaSP(1);
            sp.setMaKhuVucKho(khuVucId);
            
            List<SanPham> mockList = Arrays.asList(sp);
            
            mockedXQuery.when(() -> XQuery.getBeanList(eq(SanPham.class), anyString(), eq(khuVucId)))
                    .thenReturn(mockList);
            
            SanPhamDAOImpl dao = new SanPhamDAOImpl();
            List<SanPham> result = dao.findByKhuVuc(khuVucId);
            
            Assert.assertNotNull(result);
            Assert.assertEquals(result.size(), 1);
            Assert.assertEquals(result.get(0).getMaKhuVucKho(), khuVucId);
        }
    }

    @Test
    public void testSave_NewProduct() throws SQLException {
        try (MockedStatic<XJdbc> mockedXJdbc = mockStatic(XJdbc.class)) {
            SanPham sp = new SanPham();
            sp.setTenSanPham("New Product");
            
            mockedXJdbc.when(() -> XJdbc.executeUpdate(anyString(), any(), any(), any(), any(), any()))
                    .thenReturn(101); // Mocked generated ID
            
            SanPhamDAOImpl dao = new SanPhamDAOImpl();
            SanPham savedSp = dao.save(sp);
            
            Assert.assertEquals(savedSp.getMaSP(), Integer.valueOf(101));
            mockedXJdbc.verify(() -> XJdbc.executeUpdate(anyString(), any(), any(), any(), any(), any()));
        }
    }

    @Test
    public void testDeleteById() throws SQLException {
        try (MockedStatic<XJdbc> mockedXJdbc = mockStatic(XJdbc.class)) {
            Integer idToDelete = 1;
            
            mockedXJdbc.when(() -> XJdbc.executeUpdate(anyString(), eq(idToDelete)))
                    .thenReturn(1);
            
            SanPhamDAOImpl dao = new SanPhamDAOImpl();
            dao.deleteById(idToDelete);
            
            mockedXJdbc.verify(() -> XJdbc.executeUpdate(anyString(), eq(idToDelete)));
        }
    }
}
