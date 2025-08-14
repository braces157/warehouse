/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package braces.gui.page;

import braces.controller.ThuocTinhSanPhamController;
import braces.controller.impl.CTPhieuNhapControllerImpl;
import braces.controller.impl.KhachHangControllerImpl;
import braces.controller.impl.NhaCungCapControllerImpl;
import braces.controller.impl.PhienBanControllerImpl;
import braces.controller.impl.PhieuNhapControllerImpl;
import braces.controller.impl.SanPhamControllerImpl;
import braces.controller.impl.ThuocTinhSanPhamControllerImpl;
import braces.dao.impl.CTPhieuNhapDAOImpl;
import braces.dao.impl.KhachHangDAOImpl;
import braces.dao.impl.NhaCungCapDAOImpl;
import braces.dao.impl.PhienBanDAOImpl;
import braces.dao.impl.PhieuNhapDAOImpl;
import braces.dao.impl.SanPhamDAOImpl;
import braces.dao.impl.ThuocTinhSanPhamDAOImpl;
import braces.entity.CTPhieuNhap;
import braces.entity.CTPhieuXuat;
import braces.entity.KhachHang;
import braces.entity.NhaCungCap;
import braces.entity.PhienBan;
import braces.entity.PhieuNhap;
import braces.entity.SanPham;
import braces.entity.ThuocTinhSanPham;
import braces.util.Formatter;
import braces.util.JTableUtilities;
import braces.util.MessageDialog;
import braces.util.RandomGenerator;
import braces.util.Validation;
import braces.util.XAuth;
import braces.util.XIcon;
import com.formdev.flatlaf.FlatClientProperties;
import com.formdev.flatlaf.extras.FlatSVGIcon;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Braces
 */
public class CreateImportBillPage extends javax.swing.JPanel {

    /**
     * Creates new form CreateImportBill
     */
    ImportPage page;
    NhaCungCapControllerImpl ctNCC = new NhaCungCapControllerImpl(new NhaCungCapDAOImpl());
    SanPhamControllerImpl ctSP = new SanPhamControllerImpl(new SanPhamDAOImpl());
    List<NhaCungCap> listNCC = List.of();
    List<SanPham> listSP = List.of();
    List<PhienBan> listPB = List.of();
    List<ThuocTinhSanPham> listTT = List.of();
    PhienBanControllerImpl ctPB = new PhienBanControllerImpl(new PhienBanDAOImpl());
    ThuocTinhSanPhamController ctTT = new ThuocTinhSanPhamControllerImpl(new ThuocTinhSanPhamDAOImpl());
    List<CTPhieuNhap> listCT = new ArrayList<>();
    DefaultTableModel modelCart;
    PhieuNhapControllerImpl ctPN = new PhieuNhapControllerImpl(new PhieuNhapDAOImpl());
    CTPhieuNhapControllerImpl ctCT = new CTPhieuNhapControllerImpl(new CTPhieuNhapDAOImpl());

    public CreateImportBillPage(ImportPage page) {
        initComponents();
        this.page = page;
        modelCart = (DefaultTableModel) tableCart.getModel();
        init();

    }

    public void init() {
        txtMaPhieu.setText(RandomGenerator.getRandomNumber());
        txtSoLuong.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Số lượng");
        listSP = ctSP.getAll();
        loadTable();
        JTableUtilities.setCellsAlignment(table, SwingConstants.CENTER);
        JTableUtilities.setCellsAlignment(tableCart, SwingConstants.CENTER
        );
        String[] searchType = {"Tất cả", "Mã", "Tên", "Xuất xứ", "Chip", "Mã kho"};
        DefaultComboBoxModel<String> model = new DefaultComboBoxModel<>(searchType);
        cboSearchType.setModel(model);
        loadNhaCungCap();
    }

    public void loadNhaCungCap() {
        listNCC = ctNCC.getAll();
        for (NhaCungCap nc : listNCC) {
            cboNCC.addItem(nc.getTenNhaCungCap());
        }
    }

    public void setThuocTinh(int id) {
        listTT = ctTT.getBySanPham(id);
        listTT.sort(Comparator.comparingInt(ThuocTinhSanPham::getThuTu));
        StringBuilder sb = new StringBuilder();
        for (ThuocTinhSanPham t : listTT) {
            sb.append(t.getTen())
                    .append(": ")
                    .append(t.getMoTa())
                    .append("\n");
        }
        txtThuocTinh.setText(sb.toString());

    }

    public void loadPhienBan(int id) {
        listPB = ctPB.getBySanPham(id);
        String[] searchType = new String[listPB.size()];
        for (int i = 0; i < listPB.size(); i++) {
            searchType[i] = listPB.get(i).getMaPhienBanSP();
        }
        DefaultComboBoxModel<String> model = new DefaultComboBoxModel<>(searchType);
        cboPhienBan.setModel(model);
        txtDonGia.setText(Formatter.formatVND(listPB.get(cboPhienBan.getSelectedIndex()).getGiaBan()));
    }

    public int tinhTongTien() {
        int tong = 0;
        for (CTPhieuNhap ct : listCT) {
            tong += ct.getDonGia() * ct.getSoLuong();
        }
        return tong;
    }

    public void setTongTien() {
        txtTong.setText(Formatter.formatVND(tinhTongTien()));
    }

    public void loadTable() {
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.setRowCount(0);
        for (SanPham sp : listSP) {
            Object[] row = {sp.getMaSP(), sp.getTenSanPham(), sp.getXuatXu(), sp.getChipXuLy(), sp.getMaKhuVucKho()};
            model.addRow(row);
        }

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        mainPanel = new javax.swing.JPanel();
        sanPhamPanel = new javax.swing.JPanel();
        jPanel15 = new javax.swing.JPanel();
        lblThuoc = new javax.swing.JLabel();
        jPanel16 = new javax.swing.JPanel();
        jPanel22 = new javax.swing.JPanel();
        lblHinhAnh = new javax.swing.JLabel();
        jPanel24 = new javax.swing.JPanel();
        jPanel17 = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        cboPhienBan = new javax.swing.JComboBox<>();
        jPanel18 = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        jPanel19 = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        txtThuocTinh = new javax.swing.JTextArea();
        jPanel21 = new javax.swing.JPanel();
        jLabel14 = new javax.swing.JLabel();
        txtDonGia = new javax.swing.JTextField();
        txtTen = new javax.swing.JTextField();
        jPanel4 = new javax.swing.JPanel();
        actionPanel = new javax.swing.JPanel();
        jPanel12 = new javax.swing.JPanel();
        jPanel14 = new javax.swing.JPanel();
        cboSearchType = new javax.swing.JComboBox<>();
        txtSearch = new javax.swing.JTextField();
        btnReload = new javax.swing.JButton();
        jPanel13 = new javax.swing.JPanel();
        txtSoLuong = new javax.swing.JTextField();
        btnAddCart = new javax.swing.JButton();
        tablePanel = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        table = new javax.swing.JTable();
        billPanel = new javax.swing.JPanel();
        cardPanel = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel20 = new javax.swing.JPanel();
        btnDeleteCartItem = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        tableCart = new javax.swing.JTable();
        billInfoPanel = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        jPanel23 = new javax.swing.JPanel();
        jPanel7 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        txtMaPhieu = new javax.swing.JTextField();
        jPanel25 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        cboNCC = new javax.swing.JComboBox<>();
        jPanel2 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        txtSDT = new javax.swing.JTextField();
        jSeparator1 = new javax.swing.JSeparator();
        jPanel26 = new javax.swing.JPanel();
        jPanel11 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        txtTong = new javax.swing.JTextField();
        jPanel10 = new javax.swing.JPanel();
        jPanel9 = new javax.swing.JPanel();
        jPanel8 = new javax.swing.JPanel();
        btnHuy = new javax.swing.JButton();
        btnThanhToan = new javax.swing.JButton();

        setLayout(new java.awt.BorderLayout());

        mainPanel.setBackground(new java.awt.Color(230, 245, 245));
        mainPanel.setLayout(new java.awt.BorderLayout(5, 5));

        sanPhamPanel.setBackground(new java.awt.Color(255, 255, 255));
        sanPhamPanel.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(237, 237, 237), 2, true));
        sanPhamPanel.setPreferredSize(new java.awt.Dimension(832, 300));
        sanPhamPanel.setLayout(new java.awt.BorderLayout());

        jPanel15.setBackground(new java.awt.Color(0, 153, 153));
        jPanel15.setMinimumSize(new java.awt.Dimension(100, 60));
        jPanel15.setPreferredSize(new java.awt.Dimension(500, 30));
        jPanel15.setLayout(new java.awt.BorderLayout());

        lblThuoc.setFont(new java.awt.Font("Roboto Medium", 0, 14)); // NOI18N
        lblThuoc.setForeground(new java.awt.Color(255, 255, 255));
        lblThuoc.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblThuoc.setText("Thông tin sản phẩm");
        jPanel15.add(lblThuoc, java.awt.BorderLayout.CENTER);

        sanPhamPanel.add(jPanel15, java.awt.BorderLayout.NORTH);

        jPanel16.setBackground(new java.awt.Color(255, 255, 255));
        jPanel16.setLayout(new java.awt.BorderLayout(16, 16));

        jPanel22.setBackground(new java.awt.Color(255, 255, 255));
        jPanel22.setLayout(new java.awt.BorderLayout(20, 20));

        lblHinhAnh.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(230, 230, 230), 4, true));
        lblHinhAnh.setPreferredSize(new java.awt.Dimension(300, 200));
        jPanel22.add(lblHinhAnh, java.awt.BorderLayout.CENTER);

        jPanel16.add(jPanel22, java.awt.BorderLayout.WEST);

        jPanel24.setBackground(new java.awt.Color(255, 255, 255));

        jPanel17.setBackground(new java.awt.Color(255, 255, 255));
        jPanel17.setPreferredSize(new java.awt.Dimension(215, 40));

        jLabel10.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        jLabel10.setText("Phiên bản");
        jLabel10.setMaximumSize(new java.awt.Dimension(44, 40));
        jLabel10.setPreferredSize(new java.awt.Dimension(90, 40));

        cboPhienBan.setEditable(true);
        cboPhienBan.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        cboPhienBan.setDoubleBuffered(true);
        cboPhienBan.setMinimumSize(new java.awt.Dimension(200, 32));
        cboPhienBan.setPreferredSize(new java.awt.Dimension(200, 32));
        cboPhienBan.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cboPhienBanItemStateChanged(evt);
            }
        });
        cboPhienBan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboPhienBanActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel17Layout = new javax.swing.GroupLayout(jPanel17);
        jPanel17.setLayout(jPanel17Layout);
        jPanel17Layout.setHorizontalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel17Layout.createSequentialGroup()
                .addGap(4, 4, 4)
                .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(4, 4, 4)
                .addComponent(cboPhienBan, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel17Layout.setVerticalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel17Layout.createSequentialGroup()
                .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addComponent(cboPhienBan, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jPanel18.setBackground(new java.awt.Color(255, 255, 255));
        jPanel18.setPreferredSize(new java.awt.Dimension(215, 40));

        jLabel11.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        jLabel11.setText("Tên");
        jLabel11.setMaximumSize(new java.awt.Dimension(44, 40));
        jLabel11.setPreferredSize(new java.awt.Dimension(90, 40));

        javax.swing.GroupLayout jPanel18Layout = new javax.swing.GroupLayout(jPanel18);
        jPanel18.setLayout(jPanel18Layout);
        jPanel18Layout.setHorizontalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel18Layout.createSequentialGroup()
                .addGap(4, 4, 4)
                .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel18Layout.setVerticalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        jPanel19.setBackground(new java.awt.Color(255, 255, 255));
        jPanel19.setPreferredSize(new java.awt.Dimension(200, 40));

        jLabel12.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        jLabel12.setText("Thuộc tính:");
        jLabel12.setMaximumSize(new java.awt.Dimension(44, 80));
        jLabel12.setPreferredSize(new java.awt.Dimension(90, 80));

        txtThuocTinh.setColumns(20);
        txtThuocTinh.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        txtThuocTinh.setRows(5);
        jScrollPane3.setViewportView(txtThuocTinh);

        javax.swing.GroupLayout jPanel19Layout = new javax.swing.GroupLayout(jPanel19);
        jPanel19.setLayout(jPanel19Layout);
        jPanel19Layout.setHorizontalGroup(
            jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel19Layout.createSequentialGroup()
                .addGap(4, 4, 4)
                .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(4, 4, 4)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 326, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 115, Short.MAX_VALUE))
        );
        jPanel19Layout.setVerticalGroup(
            jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel19Layout.createSequentialGroup()
                .addGap(11, 11, 11)
                .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel19Layout.createSequentialGroup()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 96, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel21.setBackground(new java.awt.Color(255, 255, 255));
        jPanel21.setPreferredSize(new java.awt.Dimension(215, 40));
        jPanel21.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 4, 0));

        jLabel14.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        jLabel14.setText("Giá nhập");
        jLabel14.setMaximumSize(new java.awt.Dimension(44, 40));
        jLabel14.setPreferredSize(new java.awt.Dimension(90, 40));
        jPanel21.add(jLabel14);

        txtDonGia.setEditable(false);
        txtDonGia.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        txtDonGia.setFocusable(false);
        txtDonGia.setPreferredSize(new java.awt.Dimension(200, 40));
        txtDonGia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtDonGiaActionPerformed(evt);
            }
        });
        jPanel21.add(txtDonGia);

        txtTen.setEditable(false);
        txtTen.setFont(new java.awt.Font("Roboto", 0, 12)); // NOI18N
        txtTen.setFocusable(false);
        txtTen.setPreferredSize(new java.awt.Dimension(300, 40));
        txtTen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTenActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel24Layout = new javax.swing.GroupLayout(jPanel24);
        jPanel24.setLayout(jPanel24Layout);
        jPanel24Layout.setHorizontalGroup(
            jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel24Layout.createSequentialGroup()
                .addGroup(jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel24Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel21, javax.swing.GroupLayout.PREFERRED_SIZE, 349, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel24Layout.createSequentialGroup()
                        .addGap(105, 105, 105)
                        .addComponent(txtTen, javax.swing.GroupLayout.PREFERRED_SIZE, 289, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(295, Short.MAX_VALUE))
            .addGroup(jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel24Layout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jPanel17, javax.swing.GroupLayout.DEFAULT_SIZE, 677, Short.MAX_VALUE)
                        .addGroup(jPanel24Layout.createSequentialGroup()
                            .addGroup(jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jPanel19, javax.swing.GroupLayout.PREFERRED_SIZE, 539, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jPanel18, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGap(0, 0, Short.MAX_VALUE)))
                    .addContainerGap()))
        );
        jPanel24Layout.setVerticalGroup(
            jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel24Layout.createSequentialGroup()
                .addGap(53, 53, 53)
                .addComponent(txtTen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 112, Short.MAX_VALUE)
                .addComponent(jPanel21, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(21, 21, 21))
            .addGroup(jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel24Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jPanel17, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(jPanel18, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(jPanel19, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(66, Short.MAX_VALUE)))
        );

        jPanel16.add(jPanel24, java.awt.BorderLayout.CENTER);

        sanPhamPanel.add(jPanel16, java.awt.BorderLayout.CENTER);

        mainPanel.add(sanPhamPanel, java.awt.BorderLayout.PAGE_START);

        jPanel4.setBackground(new java.awt.Color(230, 245, 245));
        jPanel4.setPreferredSize(new java.awt.Dimension(832, 400));
        jPanel4.setLayout(new java.awt.BorderLayout(0, 5));

        actionPanel.setBackground(new java.awt.Color(255, 255, 255));
        actionPanel.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(237, 237, 237), 2, true));
        actionPanel.setPreferredSize(new java.awt.Dimension(605, 60));
        actionPanel.setLayout(new java.awt.BorderLayout());

        jPanel12.setBackground(new java.awt.Color(255, 255, 255));
        jPanel12.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 5, 8));

        jPanel14.setBackground(new java.awt.Color(255, 255, 255));
        jPanel14.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 5, 0));
        jPanel12.add(jPanel14);

        cboSearchType.setToolTipText("");
        cboSearchType.setPreferredSize(new java.awt.Dimension(100, 40));
        cboSearchType.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboSearchTypeActionPerformed(evt);
            }
        });
        jPanel12.add(cboSearchType);

        txtSearch.setToolTipText("Tìm kiếm");
        txtSearch.setPreferredSize(new java.awt.Dimension(200, 40));
        txtSearch.setSelectionColor(new java.awt.Color(230, 245, 245));
        txtSearch.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtSearchKeyReleased(evt);
            }
        });
        jPanel12.add(txtSearch);

        btnReload.setIcon(new FlatSVGIcon("svg/refresh32.svg"));
        btnReload.setToolTipText("Làm mới");
        btnReload.setBorder(null);
        btnReload.setBorderPainted(false);
        btnReload.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnReload.setFocusPainted(false);
        btnReload.setFocusable(false);
        btnReload.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnReload.setPreferredSize(new java.awt.Dimension(40, 40));
        btnReload.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnReloadActionPerformed(evt);
            }
        });
        jPanel12.add(btnReload);

        actionPanel.add(jPanel12, java.awt.BorderLayout.CENTER);

        jPanel13.setBackground(new java.awt.Color(255, 255, 255));
        jPanel13.setPreferredSize(new java.awt.Dimension(260, 60));
        jPanel13.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.RIGHT, 5, 8));

        txtSoLuong.setFont(new java.awt.Font("Roboto", 0, 12)); // NOI18N
        txtSoLuong.setPreferredSize(new java.awt.Dimension(120, 40));
        jPanel13.add(txtSoLuong);

        btnAddCart.setBackground(new java.awt.Color(0, 179, 246));
        btnAddCart.setFont(new java.awt.Font("Roboto Black", 0, 16)); // NOI18N
        btnAddCart.setForeground(new java.awt.Color(255, 220, 0));
        btnAddCart.setIcon(new FlatSVGIcon("svg/addproduct.svg"));
        btnAddCart.setText("THÊM");
        btnAddCart.setBorderPainted(false);
        btnAddCart.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnAddCart.setFocusPainted(false);
        btnAddCart.setFocusable(false);
        btnAddCart.setPreferredSize(new java.awt.Dimension(120, 40));
        btnAddCart.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddCartActionPerformed(evt);
            }
        });
        jPanel13.add(btnAddCart);

        actionPanel.add(jPanel13, java.awt.BorderLayout.EAST);

        jPanel4.add(actionPanel, java.awt.BorderLayout.PAGE_START);

        tablePanel.setBackground(new java.awt.Color(255, 255, 255));
        tablePanel.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(237, 237, 237), 2, true));
        tablePanel.setLayout(new java.awt.BorderLayout());

        table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Mã", "Tên sản phẩm", "Xuất xứ", "Chip xử lí", "Khu vực kho"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        table.setFocusable(false);
        table.setRowHeight(40);
        table.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        table.setShowHorizontalLines(true);
        table.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableMouseClicked(evt);
            }
        });
        table.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tableKeyPressed(evt);
            }
        });
        jScrollPane1.setViewportView(table);

        tablePanel.add(jScrollPane1, java.awt.BorderLayout.CENTER);

        jPanel4.add(tablePanel, java.awt.BorderLayout.CENTER);

        mainPanel.add(jPanel4, java.awt.BorderLayout.CENTER);

        add(mainPanel, java.awt.BorderLayout.CENTER);

        billPanel.setBackground(new java.awt.Color(230, 245, 245));
        billPanel.setPreferredSize(new java.awt.Dimension(460, 800));
        billPanel.setLayout(new java.awt.BorderLayout(0, 5));

        cardPanel.setBackground(new java.awt.Color(255, 255, 255));
        cardPanel.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(238, 238, 238), 2, true));
        cardPanel.setPreferredSize(new java.awt.Dimension(600, 500));
        cardPanel.setLayout(new java.awt.BorderLayout());

        jPanel3.setBackground(new java.awt.Color(0, 153, 153));
        jPanel3.setMinimumSize(new java.awt.Dimension(100, 60));
        jPanel3.setPreferredSize(new java.awt.Dimension(500, 30));
        jPanel3.setLayout(new java.awt.BorderLayout());

        jLabel1.setFont(new java.awt.Font("Roboto Medium", 0, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Chi tiết phiếu nhập");
        jPanel3.add(jLabel1, java.awt.BorderLayout.CENTER);

        cardPanel.add(jPanel3, java.awt.BorderLayout.NORTH);

        jPanel20.setBackground(new java.awt.Color(255, 255, 255));
        jPanel20.setForeground(new java.awt.Color(255, 255, 255));
        jPanel20.setPreferredSize(new java.awt.Dimension(456, 42));
        jPanel20.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.RIGHT, 6, 2));

        btnDeleteCartItem.setBackground(new java.awt.Color(255, 102, 102));
        btnDeleteCartItem.setFont(new java.awt.Font("Roboto Mono", 1, 14)); // NOI18N
        btnDeleteCartItem.setForeground(new java.awt.Color(255, 255, 255));
        btnDeleteCartItem.setIcon(new FlatSVGIcon("svg/trash.svg"));
        btnDeleteCartItem.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnDeleteCartItem.setPreferredSize(new java.awt.Dimension(50, 38));
        btnDeleteCartItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteCartItemActionPerformed(evt);
            }
        });
        jPanel20.add(btnDeleteCartItem);

        cardPanel.add(jPanel20, java.awt.BorderLayout.PAGE_END);

        jScrollPane2.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(230, 230, 230), 1, true));

        tableCart.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Tên sản phẩm", "Mã phiên bản", "Số lượng", "Đơn giá"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.Object.class, java.lang.Integer.class, java.lang.Object.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        tableCart.setFocusable(false);
        jScrollPane2.setViewportView(tableCart);
        if (tableCart.getColumnModel().getColumnCount() > 0) {
            tableCart.getColumnModel().getColumn(0).setPreferredWidth(100);
            tableCart.getColumnModel().getColumn(1).setPreferredWidth(70);
            tableCart.getColumnModel().getColumn(2).setPreferredWidth(30);
        }

        cardPanel.add(jScrollPane2, java.awt.BorderLayout.CENTER);

        billPanel.add(cardPanel, java.awt.BorderLayout.CENTER);

        billInfoPanel.setBackground(new java.awt.Color(255, 255, 255));
        billInfoPanel.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(238, 238, 238), 2, true));
        billInfoPanel.setPreferredSize(new java.awt.Dimension(500, 400));
        billInfoPanel.setLayout(new java.awt.BorderLayout());

        jPanel5.setBackground(new java.awt.Color(0, 153, 153));
        jPanel5.setMinimumSize(new java.awt.Dimension(100, 60));
        jPanel5.setPreferredSize(new java.awt.Dimension(500, 30));
        jPanel5.setLayout(new java.awt.BorderLayout());

        jLabel2.setFont(new java.awt.Font("Roboto Medium", 0, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Hóa đơn");
        jPanel5.add(jLabel2, java.awt.BorderLayout.CENTER);

        billInfoPanel.add(jPanel5, java.awt.BorderLayout.NORTH);

        jPanel6.setBackground(new java.awt.Color(255, 255, 255));
        jPanel6.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 5, 8));

        jPanel23.setBackground(new java.awt.Color(255, 255, 255));
        jPanel23.setPreferredSize(new java.awt.Dimension(440, 140));
        jPanel23.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));

        jPanel7.setBackground(new java.awt.Color(255, 255, 255));
        jPanel7.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 5, 0));

        jLabel4.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        jLabel4.setText("Mã hóa đơn ");
        jLabel4.setPreferredSize(new java.awt.Dimension(120, 40));
        jPanel7.add(jLabel4);

        txtMaPhieu.setEditable(false);
        txtMaPhieu.setFont(new java.awt.Font("Roboto Mono", 1, 14)); // NOI18N
        txtMaPhieu.setText("Z2NX8CN1A");
        txtMaPhieu.setFocusable(false);
        txtMaPhieu.setPreferredSize(new java.awt.Dimension(250, 40));
        jPanel7.add(txtMaPhieu);

        jPanel23.add(jPanel7);

        jPanel25.setBackground(new java.awt.Color(255, 255, 255));
        jPanel25.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 5, 0));

        jLabel8.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        jLabel8.setText("Nhà cung cấp");
        jLabel8.setPreferredSize(new java.awt.Dimension(120, 40));
        jPanel25.add(jLabel8);

        cboNCC.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        cboNCC.setPreferredSize(new java.awt.Dimension(250, 40));
        cboNCC.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cboNCCItemStateChanged(evt);
            }
        });
        cboNCC.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboNCCActionPerformed(evt);
            }
        });
        jPanel25.add(cboNCC);

        jPanel23.add(jPanel25);

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 5, 0));

        jLabel3.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        jLabel3.setText("Số điện thoại");
        jLabel3.setMaximumSize(new java.awt.Dimension(44, 40));
        jLabel3.setPreferredSize(new java.awt.Dimension(120, 40));
        jPanel2.add(jLabel3);

        txtSDT.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        txtSDT.setPreferredSize(new java.awt.Dimension(250, 40));
        txtSDT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSDTActionPerformed(evt);
            }
        });
        txtSDT.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                txtSDTPropertyChange(evt);
            }
        });
        jPanel2.add(txtSDT);

        jPanel23.add(jPanel2);

        jPanel6.add(jPanel23);

        jSeparator1.setPreferredSize(new java.awt.Dimension(400, 3));
        jPanel6.add(jSeparator1);

        jPanel26.setBackground(new java.awt.Color(255, 255, 255));
        jPanel26.setPreferredSize(new java.awt.Dimension(440, 150));
        jPanel26.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));

        jPanel11.setBackground(new java.awt.Color(255, 255, 255));
        jPanel11.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 5, 0));

        jLabel7.setFont(new java.awt.Font("Roboto", 1, 14)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 51, 0));
        jLabel7.setText("Tổng tiền:");
        jLabel7.setPreferredSize(new java.awt.Dimension(120, 40));
        jPanel11.add(jLabel7);

        txtTong.setEditable(false);
        txtTong.setFont(new java.awt.Font("Roboto Mono Medium", 0, 14)); // NOI18N
        txtTong.setForeground(new java.awt.Color(255, 51, 0));
        txtTong.setFocusable(false);
        txtTong.setPreferredSize(new java.awt.Dimension(250, 40));
        jPanel11.add(txtTong);

        jPanel26.add(jPanel11);

        jPanel10.setBackground(new java.awt.Color(255, 255, 255));
        jPanel10.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 5, 0));
        jPanel26.add(jPanel10);

        jPanel9.setBackground(new java.awt.Color(255, 255, 255));
        jPanel9.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 5, 0));
        jPanel26.add(jPanel9);

        jPanel6.add(jPanel26);

        billInfoPanel.add(jPanel6, java.awt.BorderLayout.CENTER);

        jPanel8.setBackground(new java.awt.Color(255, 255, 255));

        btnHuy.setBackground(new java.awt.Color(255, 102, 102));
        btnHuy.setFont(new java.awt.Font("Roboto Mono Medium", 0, 16)); // NOI18N
        btnHuy.setForeground(new java.awt.Color(255, 255, 255));
        btnHuy.setText("HỦY BỎ");
        btnHuy.setBorderPainted(false);
        btnHuy.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnHuy.setFocusPainted(false);
        btnHuy.setFocusable(false);
        btnHuy.setPreferredSize(new java.awt.Dimension(200, 40));
        btnHuy.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHuyActionPerformed(evt);
            }
        });
        jPanel8.add(btnHuy);

        btnThanhToan.setBackground(new java.awt.Color(0, 204, 51));
        btnThanhToan.setFont(new java.awt.Font("Roboto Mono Medium", 0, 16)); // NOI18N
        btnThanhToan.setForeground(new java.awt.Color(255, 255, 255));
        btnThanhToan.setText("NHẬP HÀNG");
        btnThanhToan.setBorderPainted(false);
        btnThanhToan.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnThanhToan.setFocusPainted(false);
        btnThanhToan.setFocusable(false);
        btnThanhToan.setPreferredSize(new java.awt.Dimension(200, 40));
        btnThanhToan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThanhToanActionPerformed(evt);
            }
        });
        jPanel8.add(btnThanhToan);

        billInfoPanel.add(jPanel8, java.awt.BorderLayout.PAGE_END);

        billPanel.add(billInfoPanel, java.awt.BorderLayout.SOUTH);

        add(billPanel, java.awt.BorderLayout.EAST);
    }// </editor-fold>//GEN-END:initComponents

    private void txtSearchKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSearchKeyReleased
        String type = (String) cboSearchType.getSelectedItem();
        listSP = ctSP.getSearchTable(txtSearch.getText(), type);
        loadTable();
    }//GEN-LAST:event_txtSearchKeyReleased

    private void btnReloadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnReloadActionPerformed
        txtSearch.setText("");
        cboSearchType.setSelectedIndex(0);
    }//GEN-LAST:event_btnReloadActionPerformed

    private void btnAddCartActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddCartActionPerformed

        if (table.getSelectedRow() == -1) {
            MessageDialog.warring(this, "Vui lòng chọn sản phẩm");
            return;
        }
        if (!Validation.isNumber(txtSoLuong.getText())) {
            MessageDialog.warring(this, "Số lượng phải là số");
            return;
        }
        int check = -1;
        PhienBan pb = listPB.get(cboPhienBan.getSelectedIndex());
        SanPham sp = listSP.get(table.getSelectedRow());
        for (int i = 0; i < tableCart.getRowCount(); i++) {
            if (pb.getMaPhienBanSP().equals(tableCart.getValueAt(i, 1).toString())) {
                check = i;
            }
        }
        if (check != -1) {
            CTPhieuNhap ctpn = listCT.get(check);
            ctpn.setSoLuong(ctpn.getSoLuong() + Integer.valueOf(txtSoLuong.getText()));
            listCT.set(check, ctpn);
            tableCart.setValueAt(ctpn.getSoLuong(), check, 2);
            setTongTien();
        } else {
            CTPhieuNhap ctpn = new CTPhieuNhap();
            ctpn.setDonGia(pb.getGiaBan());
            ctpn.setMaPhienBan(pb.getMaPhienBanSP());
            ctpn.setMaPhieuNhap(Integer.valueOf(txtMaPhieu.getText()));
            ctpn.setSoLuong(Integer.valueOf(txtSoLuong.getText()));
            listCT.add(ctpn);
            Object[] rowData = {sp.getTenSanPham(), pb.getMaPhienBanSP(), txtSoLuong.getText(), Formatter.formatVND(pb.getGiaBan())};
            modelCart.addRow(rowData);
            setTongTien();
        }

    }//GEN-LAST:event_btnAddCartActionPerformed

    private void btnDeleteCartItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteCartItemActionPerformed
        try {
            int row = tableCart.getSelectedRow();
            if (MessageDialog.confirm(this, "Bạn có chắc chắn xóa sản phẩm này?", "Xóa")) {
                listCT.remove(row);
                modelCart.removeRow(row);
                MessageDialog.info(this, "Xóa thành công!");
            }
        } catch (Exception e) {
            MessageDialog.error(this, "Vui lòng chọn dòng cần thực hiện!");
        }
    }//GEN-LAST:event_btnDeleteCartItemActionPerformed

    private void btnHuyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHuyActionPerformed
        this.page.getMain().setPanel(page);

    }//GEN-LAST:event_btnHuyActionPerformed

    private void btnThanhToanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThanhToanActionPerformed

        if (MessageDialog.confirm(this, "Bạn chắc chắn nhập hàng ?", "Kiểm tra")) {
            if (listCT.isEmpty()) {
                MessageDialog.warring(this, "Bạn chưa thêm sản phẩm nào");
                return;
            }
            PhieuNhap phieuNhap = new PhieuNhap();
            phieuNhap.setMaNhaCungCap(listNCC.get(cboNCC.getSelectedIndex()).getMaNhaCungCap());
            phieuNhap.setNguoiTaoPhieuNhap(XAuth.taikhoan.getManv());
            phieuNhap.setTongTien(tinhTongTien());
            phieuNhap.setThoiGian(new Date());
            phieuNhap.setTrangThai(1);
            phieuNhap.setMaPhieuNhap(Integer.valueOf(txtMaPhieu.getText()));
            ctPN.save(phieuNhap);
            for (CTPhieuNhap ct : listCT) {
                   PhienBan pb = ctPB.getById(ct.getMaPhienBan()).get();
                pb.setSoLuongTon(pb.getSoLuongTon()+ct.getSoLuong());
                ctPB.save(pb);
                ctCT.save(ct);
            }
            MessageDialog.info(this, "Nhập hàng thành công");
            this.page.init();
            this.page.getMain().setPanel(page);
        }
    }//GEN-LAST:event_btnThanhToanActionPerformed

    private void cboPhienBanItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cboPhienBanItemStateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_cboPhienBanItemStateChanged

    private void cboPhienBanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboPhienBanActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cboPhienBanActionPerformed

    private void txtTenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTenActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTenActionPerformed

    private void cboSearchTypeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboSearchTypeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cboSearchTypeActionPerformed

    private void tableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableMouseClicked
        // TODO add your handling code here:
        int row = table.getSelectedRow();

        SanPham sp = listSP.get(row);
        txtTen.setText(sp.getTenSanPham());
        XIcon.setIcon(lblHinhAnh, "src/main/resources/img/" + sp.getHinhAnh());
        loadPhienBan(sp.getMaSP());
        setThuocTinh(sp.getMaSP());
    }//GEN-LAST:event_tableMouseClicked

    private void tableKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tableKeyPressed
        // TODO add your handling code here:

    }//GEN-LAST:event_tableKeyPressed

    private void txtSDTPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_txtSDTPropertyChange
        // TODO add your handling code here:
        txtSDT.setText(listNCC.get(cboNCC.getSelectedIndex()).getSoDienThoai());
    }//GEN-LAST:event_txtSDTPropertyChange

    private void cboNCCActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboNCCActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cboNCCActionPerformed

    private void cboNCCItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cboNCCItemStateChanged
        // TODO add your handling code here:
        txtSDT.setText(listNCC.get(cboNCC.getSelectedIndex()).getSoDienThoai());

    }//GEN-LAST:event_cboNCCItemStateChanged

    private void txtDonGiaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtDonGiaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtDonGiaActionPerformed

    private void txtSDTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSDTActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtSDTActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel actionPanel;
    private javax.swing.JPanel billInfoPanel;
    private javax.swing.JPanel billPanel;
    private javax.swing.JButton btnAddCart;
    private javax.swing.JButton btnDeleteCartItem;
    private javax.swing.JButton btnHuy;
    private javax.swing.JButton btnReload;
    private javax.swing.JButton btnThanhToan;
    private javax.swing.JPanel cardPanel;
    private javax.swing.JComboBox<String> cboNCC;
    private javax.swing.JComboBox<String> cboPhienBan;
    private javax.swing.JComboBox<String> cboSearchType;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel16;
    private javax.swing.JPanel jPanel17;
    private javax.swing.JPanel jPanel18;
    private javax.swing.JPanel jPanel19;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel20;
    private javax.swing.JPanel jPanel21;
    private javax.swing.JPanel jPanel22;
    private javax.swing.JPanel jPanel23;
    private javax.swing.JPanel jPanel24;
    private javax.swing.JPanel jPanel25;
    private javax.swing.JPanel jPanel26;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JLabel lblHinhAnh;
    private javax.swing.JLabel lblThuoc;
    private javax.swing.JPanel mainPanel;
    private javax.swing.JPanel sanPhamPanel;
    private javax.swing.JTable table;
    private javax.swing.JTable tableCart;
    private javax.swing.JPanel tablePanel;
    private javax.swing.JTextField txtDonGia;
    private javax.swing.JTextField txtMaPhieu;
    private javax.swing.JTextField txtSDT;
    private javax.swing.JTextField txtSearch;
    private javax.swing.JTextField txtSoLuong;
    private javax.swing.JTextField txtTen;
    private javax.swing.JTextArea txtThuocTinh;
    private javax.swing.JTextField txtTong;
    // End of variables declaration//GEN-END:variables
}
