package braces.gui.page;

import braces.controller.impl.KhachHangControllerImpl;
import braces.controller.impl.PhieuNhapControllerImpl;
import braces.controller.impl.PhieuXuatControllerImpl;
import braces.controller.impl.SanPhamControllerImpl;
import braces.dao.impl.KhachHangDAOImpl;
import braces.dao.impl.PhieuNhapDAOImpl;
import braces.dao.impl.PhieuXuatDAOImpl;
import braces.dao.impl.SanPhamDAOImpl;
import braces.entity.PhieuNhap;
import braces.entity.PhieuXuat;
import braces.entity.SanPham;
import braces.gui.chart.ModelChart;
import braces.util.Formatter;
import braces.util.JTableUtilities;
import com.formdev.flatlaf.extras.FlatSVGIcon;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author atuandev
 */
public class HomePage extends javax.swing.JPanel {

    PhieuNhapControllerImpl ctPN = new PhieuNhapControllerImpl(new PhieuNhapDAOImpl());
    PhieuXuatControllerImpl ctPX = new PhieuXuatControllerImpl(new PhieuXuatDAOImpl());
    KhachHangControllerImpl ctKH = new KhachHangControllerImpl(new KhachHangDAOImpl());
    SanPhamControllerImpl ctSP = new SanPhamControllerImpl(new SanPhamDAOImpl());
    int soLuongSanPham = ctSP.getAll().size();
    int soLuongKhachHang = ctKH.getAll().size();

    @Getter
    @Setter
    class LichSu {

        Date thoiGian;
        boolean isPhieuNhap;
        long giaTien;
    }
    List<LichSu> list = new ArrayList<>();

    public HomePage() {
        initComponents();

        loadTable();
        initChart();
        initHeader();
    }
    
    private  void initHeader() {
        txtTongPhieu.setText(String.valueOf(list.size()));
        txtKH.setText(String.valueOf(soLuongKhachHang));
        txtSP.setText(String.valueOf(soLuongSanPham));
    }

    private void initChart() {
        chart.addLegend("Chi ra", new Color(244, 67, 54));
        chart.addLegend("Thu vào", new Color(76, 175, 80));
        Date now = new Date();
        Date start = new Date(now.getTime() - (6L * 24 * 60 * 60 * 1000));
        for (Date date = start; !date.after(now); date = new Date(date.getTime() + (24L * 60 * 60 * 1000))) {
            double chiRa = 0.0;
            double thuVao = 0.0;
            for (LichSu ls : list) {
                if (Formatter.FormatDate(ls.getThoiGian()).equals(Formatter.FormatDate(date))) {
                    if (ls.isPhieuNhap()) {
                        chiRa += ls.getGiaTien();
                    } else {
                        thuVao += ls.getGiaTien();
                    }
                }
            }

            double[] data = {chiRa, thuVao};
            chart.addData(new ModelChart(Formatter.FormatDate(date), data));
        }
    }

    private void loadTable() {
        JTableUtilities.setCellsAlignment(table, SwingConstants.CENTER);
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.setRowCount(0);
        List<PhieuNhap> listPN = ctPN.getByNhanVienLast7Days(1);
        List<PhieuXuat> listPX = ctPX.getByNhanVienLast7Days(1);
        for (PhieuNhap pn : listPN) {
            LichSu ls = new LichSu();
            ls.setGiaTien(pn.getTongTien());
            ls.setThoiGian(pn.getThoiGian());
            ls.setPhieuNhap(true);
            list.add(ls);
        }
        for (PhieuXuat px : listPX) {
            LichSu ls = new LichSu();
            ls.setGiaTien(px.getTongTien());
            ls.setThoiGian(px.getThoiGian());
            ls.setPhieuNhap(false);
            list.add(ls);
        }
        list.sort(Comparator.comparing(LichSu::getThoiGian));
        int index = 0;
        for (LichSu ls : list) {
            index++;
            Object[] rowData = {index,
                Formatter.FormatDate(ls.getThoiGian()),
                ls.isPhieuNhap() ? "Phiếu nhập" : "Phiếu xuất",
                Formatter.formatVND(ls.getGiaTien())};
            model.addRow(rowData);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jPanel7 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        txtSP = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jPanel8 = new javax.swing.JPanel();
        jPanel9 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jPanel10 = new javax.swing.JPanel();
        txtTongPhieu = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jPanel11 = new javax.swing.JPanel();
        jPanel12 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jPanel13 = new javax.swing.JPanel();
        txtKH = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jPanel16 = new javax.swing.JPanel();
        lblTable = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        table = new javax.swing.JTable();
        chart = new braces.gui.chart.Chart();

        setBackground(new java.awt.Color(230, 245, 245));
        setBorder(new javax.swing.border.LineBorder(new java.awt.Color(230, 245, 245), 6, true));
        setMinimumSize(new java.awt.Dimension(1130, 800));
        setPreferredSize(new java.awt.Dimension(1130, 800));
        setLayout(new java.awt.BorderLayout(0, 6));

        jPanel2.setBackground(new java.awt.Color(230, 245, 245));
        jPanel2.setPreferredSize(new java.awt.Dimension(100, 110));
        jPanel2.setLayout(new java.awt.GridLayout(1, 3, 16, 8));

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setPreferredSize(new java.awt.Dimension(370, 100));
        jPanel3.setLayout(new java.awt.BorderLayout());

        jPanel7.setBackground(new java.awt.Color(255, 255, 255));
        jPanel7.setPreferredSize(new java.awt.Dimension(120, 100));
        jPanel7.setLayout(new java.awt.BorderLayout());

        jLabel1.setBackground(new java.awt.Color(255, 255, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setIcon(new FlatSVGIcon("svg/phone.svg"));
        jLabel1.setBorder(javax.swing.BorderFactory.createEmptyBorder(16, 16, 16, 16));
        jLabel1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jPanel7.add(jLabel1, java.awt.BorderLayout.CENTER);

        jPanel3.add(jPanel7, java.awt.BorderLayout.WEST);

        jPanel6.setBackground(new java.awt.Color(255, 255, 255));
        jPanel6.setPreferredSize(new java.awt.Dimension(120, 100));

        txtSP.setFont(new java.awt.Font("Roboto", 1, 36)); // NOI18N
        txtSP.setForeground(new java.awt.Color(51, 51, 51));
        txtSP.setText("9");
        txtSP.setPreferredSize(new java.awt.Dimension(100, 16));

        jLabel3.setFont(new java.awt.Font("Roboto", 2, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(51, 51, 51));
        jLabel3.setText("Sản phẩm hiện có");

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtSP, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, 253, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(txtSP, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(21, Short.MAX_VALUE))
        );

        jPanel3.add(jPanel6, java.awt.BorderLayout.CENTER);

        jPanel2.add(jPanel3);

        jPanel8.setBackground(new java.awt.Color(255, 255, 255));
        jPanel8.setPreferredSize(new java.awt.Dimension(370, 100));
        jPanel8.setLayout(new java.awt.BorderLayout());

        jPanel9.setBackground(new java.awt.Color(255, 255, 255));
        jPanel9.setPreferredSize(new java.awt.Dimension(120, 100));
        jPanel9.setLayout(new java.awt.BorderLayout());

        jLabel2.setBackground(new java.awt.Color(255, 255, 255));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setIcon(new FlatSVGIcon("svg/bill.svg"));
        jLabel2.setBorder(javax.swing.BorderFactory.createEmptyBorder(16, 16, 16, 16));
        jLabel2.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jPanel9.add(jLabel2, java.awt.BorderLayout.CENTER);

        jPanel8.add(jPanel9, java.awt.BorderLayout.WEST);

        jPanel10.setBackground(new java.awt.Color(255, 255, 255));
        jPanel10.setPreferredSize(new java.awt.Dimension(120, 100));

        txtTongPhieu.setFont(new java.awt.Font("Roboto", 1, 36)); // NOI18N
        txtTongPhieu.setForeground(new java.awt.Color(51, 51, 51));
        txtTongPhieu.setText("50");
        txtTongPhieu.setPreferredSize(new java.awt.Dimension(100, 16));

        jLabel4.setFont(new java.awt.Font("Roboto", 2, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(51, 51, 51));
        jLabel4.setText("Số lượng phiếu đã thực hiện trong tuần");

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtTongPhieu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, 253, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(txtTongPhieu, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(21, Short.MAX_VALUE))
        );

        jPanel8.add(jPanel10, java.awt.BorderLayout.CENTER);

        jPanel2.add(jPanel8);

        jPanel11.setBackground(new java.awt.Color(255, 255, 255));
        jPanel11.setPreferredSize(new java.awt.Dimension(370, 100));
        jPanel11.setLayout(new java.awt.BorderLayout());

        jPanel12.setBackground(new java.awt.Color(255, 255, 255));
        jPanel12.setPreferredSize(new java.awt.Dimension(120, 100));
        jPanel12.setLayout(new java.awt.BorderLayout());

        jLabel5.setBackground(new java.awt.Color(255, 255, 255));
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setIcon(new FlatSVGIcon("svg/revenue.svg"));
        jLabel5.setBorder(javax.swing.BorderFactory.createEmptyBorder(16, 16, 16, 16));
        jLabel5.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jPanel12.add(jLabel5, java.awt.BorderLayout.CENTER);

        jPanel11.add(jPanel12, java.awt.BorderLayout.WEST);

        jPanel13.setBackground(new java.awt.Color(255, 255, 255));
        jPanel13.setPreferredSize(new java.awt.Dimension(120, 100));

        txtKH.setFont(new java.awt.Font("Roboto", 1, 36)); // NOI18N
        txtKH.setForeground(new java.awt.Color(51, 51, 51));
        txtKH.setText("0");
        txtKH.setPreferredSize(new java.awt.Dimension(100, 16));

        jLabel6.setFont(new java.awt.Font("Roboto", 2, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(51, 51, 51));
        jLabel6.setText("Số lượng khách hàng");

        javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(jPanel13);
        jPanel13.setLayout(jPanel13Layout);
        jPanel13Layout.setHorizontalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtKH, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, 253, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel13Layout.setVerticalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(txtKH, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(21, Short.MAX_VALUE))
        );

        jPanel11.add(jPanel13, java.awt.BorderLayout.CENTER);

        jPanel2.add(jPanel11);

        add(jPanel2, java.awt.BorderLayout.PAGE_START);

        jPanel1.setLayout(new java.awt.BorderLayout());

        jPanel16.setBackground(new java.awt.Color(0, 153, 153));
        jPanel16.setMinimumSize(new java.awt.Dimension(100, 60));
        jPanel16.setPreferredSize(new java.awt.Dimension(500, 40));
        jPanel16.setLayout(new java.awt.BorderLayout());

        lblTable.setFont(new java.awt.Font("Roboto Medium", 0, 18)); // NOI18N
        lblTable.setForeground(new java.awt.Color(255, 255, 255));
        lblTable.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTable.setText("QUÁ TRÌNH LÀM VIỆC CỦA BẠN TRONG TUẦN VỪA QUA");
        lblTable.setPreferredSize(new java.awt.Dimension(454, 35));
        jPanel16.add(lblTable, java.awt.BorderLayout.PAGE_START);

        jScrollPane1.setPreferredSize(new java.awt.Dimension(456, 600));

        table.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "STT", "Ngày tạo", "Loại phiếu", "Giá tiền"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.Integer.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        table.setFocusable(false);
        table.setRowHeight(40);
        table.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        table.setShowHorizontalLines(true);
        jScrollPane1.setViewportView(table);

        jPanel16.add(jScrollPane1, java.awt.BorderLayout.CENTER);

        jPanel1.add(jPanel16, java.awt.BorderLayout.CENTER);
        jPanel1.add(chart, java.awt.BorderLayout.PAGE_START);

        add(jPanel1, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private braces.gui.chart.Chart chart;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel16;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblTable;
    private javax.swing.JTable table;
    private javax.swing.JLabel txtKH;
    private javax.swing.JLabel txtSP;
    private javax.swing.JLabel txtTongPhieu;
    // End of variables declaration//GEN-END:variables
}
