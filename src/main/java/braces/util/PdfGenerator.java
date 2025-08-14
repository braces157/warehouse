package braces.util;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;
import java.awt.Desktop;
import java.io.File;
import java.io.FileOutputStream;
import javax.swing.JTable;
import javax.swing.table.TableModel;

public class PdfGenerator {

    private static Font createFont(float size, int style, BaseColor color) throws Exception {
        BaseFont baseFont = BaseFont.createFont("c:/windows/fonts/arial.ttf",
                BaseFont.IDENTITY_H,
                BaseFont.EMBEDDED);
        return new Font(baseFont, size, style, color);
    }

    public static void generatePhieuNhap(
            String maPhieu,
            String supplierName,
            String staffName,
            String ngayTao,
            String tongTien,
            JTable table,
            String filePath
    ) throws Exception {
        String logoPath = "src/main/resources/jpg/logo.jpg";
        Document document = new Document(PageSize.A4, 36, 36, 54, 36);
        PdfWriter.getInstance(document, new FileOutputStream(filePath));
        document.open();
        Font titleFontRight = createFont(28, Font.BOLD, BaseColor.RED);
        Font titleFont = createFont(18, Font.BOLD, BaseColor.BLUE);
        Font infoFont = createFont(12, Font.NORMAL, BaseColor.BLACK);
        Font infoBoldFont = createFont(12, Font.BOLD, BaseColor.BLACK);
        Font headerFont = createFont(12, Font.BOLD, BaseColor.WHITE);
        Font cellFont = createFont(10, Font.NORMAL, BaseColor.BLACK);
        Font totalFont = createFont(14, Font.BOLD, BaseColor.RED);

        PdfPTable headerTable = new PdfPTable(2);
        headerTable.setWidthPercentage(100);
        headerTable.setWidths(new float[]{3, 2});
        PdfPTable leftSubTable = new PdfPTable(1);
        leftSubTable.setWidthPercentage(100);
        try {
            Image logo = Image.getInstance(logoPath);
            logo.scaleToFit(60, 60);
            logo.setAlignment(Element.ALIGN_CENTER);
            PdfPCell logoCell = new PdfPCell(logo);
            logoCell.setBorder(Rectangle.NO_BORDER);
            logoCell.setHorizontalAlignment(Element.ALIGN_LEFT);
            logoCell.setPadding(5);
            leftSubTable.addCell(logoCell);
        } catch (Exception e) {
            System.out.println("Logo not found at: " + logoPath + ". Proceeding without logo.");
        }
        PdfPCell titleCell = new PdfPCell(new Phrase("Kho hàng điện thoại\nLê Phan Phú Quốc", titleFont));
        titleCell.setBorder(Rectangle.NO_BORDER);
        titleCell.setHorizontalAlignment(Element.ALIGN_LEFT);
        titleCell.setPadding(5);
        leftSubTable.addCell(titleCell);
        PdfPCell leftCell = new PdfPCell(leftSubTable);
        leftCell.setBorder(Rectangle.NO_BORDER);
        leftCell.setHorizontalAlignment(Element.ALIGN_LEFT);
        leftCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
        leftCell.setPadding(10);
        headerTable.addCell(leftCell);
        PdfPCell rightCell = new PdfPCell(new Phrase("CHI TIẾT\nPHIẾU NHẬP", titleFontRight));
        rightCell.setBorder(Rectangle.NO_BORDER);
        rightCell.setHorizontalAlignment(Element.ALIGN_MIDDLE);
        rightCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
        rightCell.setPadding(10);
        headerTable.addCell(rightCell);
        document.add(headerTable);
        document.add(new Paragraph(" "));
        document.add(new Paragraph(" "));

        Paragraph maHoaDonParagraph = new Paragraph();
        maHoaDonParagraph.add(new Phrase("Mã hóa đơn: ", infoBoldFont));
        maHoaDonParagraph.add(new Phrase(maPhieu, infoFont));
        document.add(maHoaDonParagraph);

        Paragraph nhaCungCapParagraph = new Paragraph();
        nhaCungCapParagraph.add(new Phrase("Nhà cung cấp: ", infoBoldFont));
        nhaCungCapParagraph.add(new Phrase(supplierName, infoFont));
        document.add(nhaCungCapParagraph);

        Paragraph tenNhanVienParagraph = new Paragraph();
        tenNhanVienParagraph.add(new Phrase("Tên nhân viên: ", infoBoldFont));
        tenNhanVienParagraph.add(new Phrase(staffName, infoFont));
        document.add(tenNhanVienParagraph);

        Paragraph ngayTaoParagraph = new Paragraph();
        ngayTaoParagraph.add(new Phrase("Ngày tạo: ", infoBoldFont));
        ngayTaoParagraph.add(new Phrase(ngayTao, infoFont));
        document.add(ngayTaoParagraph);

        document.add(new Paragraph(" "));
        document.add(new Paragraph(" "));

        // Table from JTable
        TableModel model = table.getModel();
        int colCount = model.getColumnCount();
        PdfPTable pdfTable = new PdfPTable(colCount);
        pdfTable.setWidthPercentage(100);

        // Set column widths if needed (example for 5 columns)
        if (colCount == 5) {
            pdfTable.setWidths(new float[]{1, 3, 3, 2, 2});
        }

        // Header
        for (int i = 0; i < colCount; i++) {
            PdfPCell cell = new PdfPCell(new Phrase(model.getColumnName(i), headerFont));
            cell.setBackgroundColor(BaseColor.DARK_GRAY);
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setPadding(8);
            pdfTable.addCell(cell);
        }

        // Rows
        for (int row = 0; row < model.getRowCount(); row++) {
            for (int col = 0; col < colCount; col++) {
                PdfPCell cell = new PdfPCell(new Phrase(
                        model.getValueAt(row, col) == null ? "" : model.getValueAt(row, col).toString(),
                        cellFont
                ));
                cell.setHorizontalAlignment(col == 0 || col == 3 ? Element.ALIGN_CENTER : (col == 4 ? Element.ALIGN_RIGHT : Element.ALIGN_LEFT));
                cell.setPadding(5);
                pdfTable.addCell(cell);
            }
        }
        document.add(pdfTable);
        document.add(new Paragraph(" "));

        Paragraph total = new Paragraph("Tổng hóa đơn: " + tongTien, totalFont);
        total.setAlignment(Element.ALIGN_RIGHT);
        document.add(total);
        document.close();

        Desktop desktop = Desktop.getDesktop();
        desktop.open(new File(filePath));
    }
      public static void generatePhieuXuat(
            String maPhieu,
            String khachHang,
            String staffName,
            String ngayTao,
            String tongTien,
            JTable table,
            String filePath
    ) throws Exception {
        String logoPath = "src/main/resources/jpg/logo.jpg";
        Document document = new Document(PageSize.A4, 36, 36, 54, 36);
        PdfWriter.getInstance(document, new FileOutputStream(filePath));
        document.open();
        Font titleFontRight = createFont(28, Font.BOLD, BaseColor.RED);
        Font titleFont = createFont(18, Font.BOLD, BaseColor.BLUE);
        Font infoFont = createFont(12, Font.NORMAL, BaseColor.BLACK);
        Font infoBoldFont = createFont(12, Font.BOLD, BaseColor.BLACK);
        Font headerFont = createFont(12, Font.BOLD, BaseColor.WHITE);
        Font cellFont = createFont(10, Font.NORMAL, BaseColor.BLACK);
        Font totalFont = createFont(14, Font.BOLD, BaseColor.RED);

        PdfPTable headerTable = new PdfPTable(2);
        headerTable.setWidthPercentage(100);
        headerTable.setWidths(new float[]{3, 2});
        PdfPTable leftSubTable = new PdfPTable(1);
        leftSubTable.setWidthPercentage(100);
        try {
            Image logo = Image.getInstance(logoPath);
            logo.scaleToFit(60, 60);
            logo.setAlignment(Element.ALIGN_CENTER);
            PdfPCell logoCell = new PdfPCell(logo);
            logoCell.setBorder(Rectangle.NO_BORDER);
            logoCell.setHorizontalAlignment(Element.ALIGN_LEFT);
            logoCell.setPadding(5);
            leftSubTable.addCell(logoCell);
        } catch (Exception e) {
            System.out.println("Logo not found at: " + logoPath + ". Proceeding without logo.");
        }
        PdfPCell titleCell = new PdfPCell(new Phrase("Kho hàng điện thoại\nLê Phan Phú Quốc", titleFont));
        titleCell.setBorder(Rectangle.NO_BORDER);
        titleCell.setHorizontalAlignment(Element.ALIGN_LEFT);
        titleCell.setPadding(5);
        leftSubTable.addCell(titleCell);
        PdfPCell leftCell = new PdfPCell(leftSubTable);
        leftCell.setBorder(Rectangle.NO_BORDER);
        leftCell.setHorizontalAlignment(Element.ALIGN_LEFT);
        leftCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
        leftCell.setPadding(10);
        headerTable.addCell(leftCell);
        PdfPCell rightCell = new PdfPCell(new Phrase("CHI TIẾT\nPHIẾU XUẤT", titleFontRight));
        rightCell.setBorder(Rectangle.NO_BORDER);
        rightCell.setHorizontalAlignment(Element.ALIGN_MIDDLE);
        rightCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
        rightCell.setPadding(10);
        headerTable.addCell(rightCell);
        document.add(headerTable);
        document.add(new Paragraph(" "));
        document.add(new Paragraph(" "));

        Paragraph maHoaDonParagraph = new Paragraph();
        maHoaDonParagraph.add(new Phrase("Mã hóa đơn: ", infoBoldFont));
        maHoaDonParagraph.add(new Phrase(maPhieu, infoFont));
        document.add(maHoaDonParagraph);

        Paragraph nhaCungCapParagraph = new Paragraph();
        nhaCungCapParagraph.add(new Phrase("Tên khách hàng: ", infoBoldFont));
        nhaCungCapParagraph.add(new Phrase(khachHang, infoFont));
        document.add(nhaCungCapParagraph);

        Paragraph tenNhanVienParagraph = new Paragraph();
        tenNhanVienParagraph.add(new Phrase("Tên nhân viên: ", infoBoldFont));
        tenNhanVienParagraph.add(new Phrase(staffName, infoFont));
        document.add(tenNhanVienParagraph);

        Paragraph ngayTaoParagraph = new Paragraph();
        ngayTaoParagraph.add(new Phrase("Ngày tạo: ", infoBoldFont));
        ngayTaoParagraph.add(new Phrase(ngayTao, infoFont));
        document.add(ngayTaoParagraph);

        document.add(new Paragraph(" "));
        document.add(new Paragraph(" "));

        // Table from JTable
        TableModel model = table.getModel();
        int colCount = model.getColumnCount();
        PdfPTable pdfTable = new PdfPTable(colCount);
        pdfTable.setWidthPercentage(100);

        // Set column widths if needed (example for 5 columns)
        if (colCount == 5) {
            pdfTable.setWidths(new float[]{1, 3, 3, 2, 2});
        }

        // Header
        for (int i = 0; i < colCount; i++) {
            PdfPCell cell = new PdfPCell(new Phrase(model.getColumnName(i), headerFont));
            cell.setBackgroundColor(BaseColor.DARK_GRAY);
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setPadding(8);
            pdfTable.addCell(cell);
        }

        // Rows
        for (int row = 0; row < model.getRowCount(); row++) {
            for (int col = 0; col < colCount; col++) {
                PdfPCell cell = new PdfPCell(new Phrase(
                        model.getValueAt(row, col) == null ? "" : model.getValueAt(row, col).toString(),
                        cellFont
                ));
                cell.setHorizontalAlignment(col == 0 || col == 3 ? Element.ALIGN_CENTER : (col == 4 ? Element.ALIGN_RIGHT : Element.ALIGN_LEFT));
                cell.setPadding(5);
                pdfTable.addCell(cell);
            }
        }
        document.add(pdfTable);
        document.add(new Paragraph(" "));

        Paragraph total = new Paragraph("Tổng hóa đơn: " + tongTien, totalFont);
        total.setAlignment(Element.ALIGN_RIGHT);
        document.add(total);
        document.close();

        Desktop desktop = Desktop.getDesktop();
        desktop.open(new File(filePath));
    }
}