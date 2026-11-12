
package view;

import dao.DocGiaDAO;
import model.DocGia;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.SQLException;
import java.util.ArrayList;

public class DocGiaForm extends JPanel {

    private JTextField txtMaDG;
    private JTextField txtHoTen;
    private JTextField txtNgaySinh;
    private JTextField txtLop;
    private JTextField txtDienThoai;
    private JTextField txtTimKiem;

    private JRadioButton rdNam;
    private JRadioButton rdNu;

    private JButton btnThem;
    private JButton btnSua;
    private JButton btnXoa;
    private JButton btnLamMoi;
    private JButton btnTim;

    private JTable table;
    private DefaultTableModel tableModel;

    private DocGiaDAO dao = DocGiaDAO.getInstance();

    public DocGiaForm() {

        //setTitle("Quản Lý Độc Giả - Thư Viện");
        //setSize(1050, 650);
       //setLocationRelativeTo(null);
        //setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        initComponents();
        initEvents();

        try {
            dao.createTableIfNotExists();
            loadDataToTable();
        } catch (SQLException e) {
            showError(e);
        }
    }

    // Khởi tạo giao diện
    private void initComponents() {

        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        mainPanel.setBackground(new Color(245, 247, 250));

        // Tiêu đề
        JLabel lblTitle = new JLabel(
            "QUẢN LÝ ĐỘC GIẢ THƯ VIỆN",
            SwingConstants.CENTER
        );

        lblTitle.setFont(new Font("Arial", Font.BOLD, 24));
        lblTitle.setForeground(new Color(30, 100, 160));

        mainPanel.add(lblTitle, BorderLayout.NORTH);

        // Khu vực thông tin
        JPanel contentPanel = new JPanel(new BorderLayout(10, 10));
        contentPanel.setOpaque(false);

        JPanel infoPanel = new JPanel(new GridBagLayout());
        infoPanel.setBorder(
            BorderFactory.createTitledBorder("Thông tin độc giả (tblDocGia)")
        );
        infoPanel.setBackground(Color.WHITE);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8, 8, 8, 8);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        txtMaDG = new JTextField(15);
        txtHoTen = new JTextField(15);
        txtNgaySinh = new JTextField(15);
        txtLop = new JTextField(15);
        txtDienThoai = new JTextField(15);

        rdNam = new JRadioButton("Nam", true);
        rdNu = new JRadioButton("Nữ");

        rdNam.setBackground(Color.WHITE);
        rdNu.setBackground(Color.WHITE);

        ButtonGroup groupGioiTinh = new ButtonGroup();
        groupGioiTinh.add(rdNam);
        groupGioiTinh.add(rdNu);

        // Dòng 1
        addField(infoPanel, gbc, 0, 0, "Mã Độc Giả:", txtMaDG);
        addField(infoPanel, gbc, 0, 2, "Họ Tên:", txtHoTen);

        // Dòng 2
        addField(infoPanel, gbc, 1, 0, "Ngày Sinh:", txtNgaySinh);

        gbc.gridx = 2;
        gbc.gridy = 1;
        infoPanel.add(new JLabel("Giới tính:"), gbc);

        JPanel genderPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        genderPanel.setBackground(Color.WHITE);
        genderPanel.add(rdNam);
        genderPanel.add(rdNu);

        gbc.gridx = 3;
        gbc.gridy = 1;
        infoPanel.add(genderPanel, gbc);

        // Dòng 3
        addField(infoPanel, gbc, 2, 0, "Lớp / Khoa:", txtLop);
        addField(infoPanel, gbc, 2, 2, "Điện Thoại:", txtDienThoai);

        // Tìm kiếm
        JPanel searchPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        searchPanel.setOpaque(false);

        txtTimKiem = new JTextField(18);
        btnTim = createButton("Tìm kiếm", new Color(35, 130, 190));

        searchPanel.add(new JLabel("Tìm kiếm (Mã/Tên):"));
        searchPanel.add(txtTimKiem);
        searchPanel.add(btnTim);

        JPanel topPanel = new JPanel(new BorderLayout(5, 5));
        topPanel.setOpaque(false);
        topPanel.add(infoPanel, BorderLayout.CENTER);
        topPanel.add(searchPanel, BorderLayout.SOUTH);

        contentPanel.add(topPanel, BorderLayout.NORTH);

        // Bảng dữ liệu
        String[] columns = {
            "Mã ĐG",
            "Họ Tên",
            "Ngày Sinh",
            "Giới Tính",
            "Lớp / Khoa",
            "Điện Thoại"
        };

        tableModel = new DefaultTableModel(columns, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        table = new JTable(tableModel);
        table.setRowHeight(28);
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        table.getTableHeader().setFont(
            new Font("Arial", Font.BOLD, 13)
        );
        table.getTableHeader().setBackground(new Color(35, 130, 190));
        table.getTableHeader().setForeground(Color.WHITE);

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setPreferredSize(new Dimension(900, 260));

        contentPanel.add(scrollPane, BorderLayout.CENTER);

        mainPanel.add(contentPanel, BorderLayout.CENTER);

        // Các nút chức năng
        JPanel buttonPanel = new JPanel(
            new FlowLayout(FlowLayout.CENTER, 15, 5)
        );
        buttonPanel.setOpaque(false);

        btnThem = createButton("Thêm mới", new Color(30, 170, 90));
        btnSua = createButton("Cập nhật", new Color(240, 155, 20));
        btnXoa = createButton("Xóa", new Color(220, 65, 65));
        btnLamMoi = createButton("Làm mới", new Color(120, 135, 140));

        buttonPanel.add(btnThem);
        buttonPanel.add(btnSua);
        buttonPanel.add(btnXoa);
        buttonPanel.add(btnLamMoi);

        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        add(mainPanel);
    }

    // Tạo nhãn và ô nhập
    private void addField(JPanel panel, GridBagConstraints gbc,
                          int row, int column,
                          String label, JTextField field) {

        gbc.gridx = column;
        gbc.gridy = row;
        gbc.weightx = 0;
        panel.add(new JLabel(label), gbc);

        gbc.gridx = column + 1;
        gbc.weightx = 1;
        panel.add(field, gbc);
    }

    // Tạo nút
    private JButton createButton(String text, Color color) {

        JButton button = new JButton(text);

        button.setFont(new Font("Arial", Font.BOLD, 13));
        button.setForeground(Color.WHITE);
        button.setBackground(color);
        button.setFocusPainted(false);
        button.setBorderPainted(false);
        button.setPreferredSize(new Dimension(115, 38));
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));

        return button;
    }

    // Gắn sự kiện
    private void initEvents() {

        btnThem.addActionListener(e -> themDocGia());

        btnSua.addActionListener(e -> suaDocGia());

        btnXoa.addActionListener(e -> xoaDocGia());

        btnLamMoi.addActionListener(e -> {
            clearForm();
            try {
                loadDataToTable();
            } catch (SQLException ex) {
                showError(ex);
            }
        });

        btnTim.addActionListener(e -> timKiem());

        txtTimKiem.addActionListener(e -> timKiem());

        // Chọn một dòng trong bảng
        table.getSelectionModel().addListSelectionListener(e -> {

            if (!e.getValueIsAdjusting()) {

                int row = table.getSelectedRow();

                if (row >= 0) {

                    txtMaDG.setText(
                        tableModel.getValueAt(row, 0).toString()
                    );

                    txtHoTen.setText(
                        tableModel.getValueAt(row, 1).toString()
                    );

                    txtNgaySinh.setText(
                        tableModel.getValueAt(row, 2).toString()
                    );

                    String gioiTinh =
                        tableModel.getValueAt(row, 3).toString();

                    if (gioiTinh.equals("Nam")) {
                        rdNam.setSelected(true);
                    } else {
                        rdNu.setSelected(true);
                    }

                    txtLop.setText(
                        tableModel.getValueAt(row, 4).toString()
                    );

                    txtDienThoai.setText(
                        tableModel.getValueAt(row, 5).toString()
                    );
                }
            }
        });
    }

    // Kiểm tra thông tin
    private boolean validateForm() {

        if (txtMaDG.getText().trim().isEmpty()
                || txtHoTen.getText().trim().isEmpty()
                || txtNgaySinh.getText().trim().isEmpty()
                || txtLop.getText().trim().isEmpty()
                || txtDienThoai.getText().trim().isEmpty()) {

            JOptionPane.showMessageDialog(
                this,
                "Vui lòng nhập đầy đủ thông tin!"
            );

            return false;
        }

        return true;
    }

    // Lấy dữ liệu từ form
    private DocGia getDocGiaFromForm() {

        return new DocGia(
            txtMaDG.getText().trim(),
            txtHoTen.getText().trim(),
            txtNgaySinh.getText().trim(),
            rdNam.isSelected(),
            txtLop.getText().trim(),
            txtDienThoai.getText().trim()
        );
    }

    // 1. Thêm độc giả
    private void themDocGia() {

        if (!validateForm()) {
            return;
        }

        try {

            int result = dao.insert(getDocGiaFromForm());

            if (result > 0) {

                JOptionPane.showMessageDialog(
                    this,
                    "Thêm độc giả thành công!"
                );

                loadDataToTable();
                clearForm();

            }

        } catch (SQLException e) {

            showError(e);
        }
    }

    // 2. Cập nhật độc giả
    private void suaDocGia() {

        if (!validateForm()) {
            return;
        }

        try {

            int result = dao.update(getDocGiaFromForm());

            if (result > 0) {

                JOptionPane.showMessageDialog(
                    this,
                    "Cập nhật độc giả thành công!"
                );

                loadDataToTable();
                clearForm();

            } else {

                JOptionPane.showMessageDialog(
                    this,
                    "Không tìm thấy độc giả cần sửa!"
                );
            }

        } catch (SQLException e) {

            showError(e);
        }
    }

    // 3. Xóa độc giả
    private void xoaDocGia() {

        String maDG = txtMaDG.getText().trim();

        if (maDG.isEmpty()) {

            JOptionPane.showMessageDialog(
                this,
                "Vui lòng chọn độc giả cần xóa!"
            );

            return;
        }

        int confirm = JOptionPane.showConfirmDialog(
            this,
            "Bạn có chắc chắn muốn xóa độc giả " + maDG + "?",
            "Xác nhận xóa",
            JOptionPane.YES_NO_OPTION
        );

        if (confirm == JOptionPane.YES_OPTION) {

            try {

                int result = dao.delete(maDG);

                if (result > 0) {

                    JOptionPane.showMessageDialog(
                        this,
                        "Xóa độc giả thành công!"
                    );

                    loadDataToTable();
                    clearForm();

                } else {

                    JOptionPane.showMessageDialog(
                        this,
                        "Không tìm thấy độc giả!"
                    );
                }

            } catch (SQLException e) {

                showError(e);
            }
        }
    }

    // 4. Tìm kiếm độc giả
    private void timKiem() {

        String keyword = txtTimKiem.getText().trim();

        try {

            ArrayList<DocGia> list =
                dao.selectByCondition(keyword);

            hienThiDanhSach(list);

        } catch (SQLException e) {

            showError(e);
        }
    }

    // 5. Lấy dữ liệu từ SQL Server
    private void loadDataToTable() throws SQLException {

        ArrayList<DocGia> list = dao.selectAll();

        hienThiDanhSach(list);
    }

    // Đổ dữ liệu vào JTable
    private void hienThiDanhSach(ArrayList<DocGia> list) {

        tableModel.setRowCount(0);

        for (DocGia dg : list) {

            Object[] row = {
                dg.getMaDG(),
                dg.getHoTen(),
                dg.getNgaySinh(),
                dg.isGioiTinh() ? "Nam" : "Nữ",
                dg.getLop(),
                dg.getDienThoai()
            };

            tableModel.addRow(row);
        }
    }

    // 6. Làm mới form
    private void clearForm() {

        txtMaDG.setText("");
        txtHoTen.setText("");
        txtNgaySinh.setText("");
        txtLop.setText("");
        txtDienThoai.setText("");
        txtTimKiem.setText("");

        rdNam.setSelected(true);

        table.clearSelection();
        txtMaDG.setEditable(true);
        txtMaDG.requestFocus();
    }

    // Hiển thị lỗi
    private void showError(Exception e) {

        e.printStackTrace();

        JOptionPane.showMessageDialog(
            this,
            "Có lỗi xảy ra:\n" + e.getMessage(),
            "Lỗi",
            JOptionPane.ERROR_MESSAGE
        );
    }

    // Chạy giao diện
    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {

            new DocGiaForm().setVisible(true);

        });
    }
}
