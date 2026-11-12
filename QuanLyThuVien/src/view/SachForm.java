package view;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.*;

public class SachForm extends JPanel {
    private JTable table;
    private DefaultTableModel tableModel;
    private JTextField txtMaSach, txtTenSach, txtTacGia, txtMaTheLoai, txtSoLuong;

    public SachForm() {
        setLayout(new BorderLayout());

        // Panel tiêu đề
        JLabel lblTitle = new JLabel("QUẢN LÝ SÁCH THƯ VIỆN", JLabel.CENTER);
        lblTitle.setFont(new Font("Arial", Font.BOLD, 20));
        lblTitle.setForeground(new Color(0, 51, 102));
        add(lblTitle, BorderLayout.NORTH);

        // Panel nhập liệu phía trên
        JPanel panelInput = new JPanel(new GridLayout(3, 2, 10, 10));
        panelInput.setBorder(BorderFactory.createTitledBorder("Thông tin sách (tblSach)"));

        panelInput.add(new JLabel(" Mã Sách:"));
        txtMaSach = new JTextField();
        panelInput.add(txtMaSach);

        panelInput.add(new JLabel(" Tên Sách:"));
        txtTenSach = new JTextField();
        panelInput.add(txtTenSach);

        panelInput.add(new JLabel(" Tác Giả:"));
        txtTacGia = new JTextField();
        panelInput.add(txtTacGia);

        panelInput.add(new JLabel(" Mã Thể Loại:"));
        txtMaTheLoai = new JTextField();
        panelInput.add(txtMaTheLoai);

        panelInput.add(new JLabel(" Số Lượng:"));
        txtSoLuong = new JTextField();
        panelInput.add(txtSoLuong);

        // Tạo panel chứa form nhập liệu và bảng
        JPanel panelCenter = new JPanel(new BorderLayout());
        panelCenter.add(panelInput, BorderLayout.NORTH);

        // Bảng dữ liệu Sách
        String[] columnNames = {"Mã Sách", "Tên Sách", "Tác Giả", "Mã Thể Loại", "Số Lượng"};
        tableModel = new DefaultTableModel(columnNames, 0);
        table = new JTable(tableModel);
        panelCenter.add(new JScrollPane(table), BorderLayout.CENTER);

        add(panelCenter, BorderLayout.CENTER);

        // Panel chứa các nút chức năng ở dưới
        JPanel panelButtons = new JPanel();
        JButton btnAdd = new JButton("Thêm mới");
        JButton btnUpdate = new JButton("Cập nhật");
        JButton btnDelete = new JButton("Xóa");
        JButton btnRefresh = new JButton("Làm mới");

        btnAdd.setBackground(new Color(40, 167, 69)); btnAdd.setForeground(Color.WHITE);
        btnUpdate.setBackground(new Color(255, 193, 7));
        btnDelete.setBackground(new Color(220, 53, 69)); btnDelete.setForeground(Color.WHITE);
        btnRefresh.setBackground(new Color(108, 117, 125)); btnRefresh.setForeground(Color.WHITE);

        panelButtons.add(btnAdd);
        panelButtons.add(btnUpdate);
        panelButtons.add(btnDelete);
        panelButtons.add(btnRefresh);

        add(panelButtons, BorderLayout.SOUTH);

        // Load dữ liệu từ SQL Server lên bảng
        loadDataToTable();
    }

    private void loadDataToTable() {
        try {
            Connection conn = database.DBConnection.getConnection();
            String sql = "SELECT * FROM tblSach";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            tableModel.setRowCount(0);
            while (rs.next()) {
                Object[] row = {
                    rs.getString("MaSach"),
                    rs.getString("TenSach"),
                    rs.getString("TacGia"),
                    rs.getString("MaTheLoai"),
                    rs.getInt("SoLuong")
                };
                tableModel.addRow(row);
            }
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}