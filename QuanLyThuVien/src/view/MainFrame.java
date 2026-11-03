package view;

import dao.DocGiaDAO;
import model.DocGia;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class MainFrame extends JFrame {
    private JTable table;
    private DefaultTableModel tableModel;
    private JButton btnLoad, btnThem;

    public MainFrame() {
        setTitle("Quản Lý Độc Giả - Thư Viện");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);

        // Bảng hiển thị dữ liệu
        tableModel = new DefaultTableModel();
        tableModel.setColumnIdentifiers(new String[]{"Mã ĐG", "Họ Tên", "Ngày Sinh", "SĐT", "Email"});
        table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(20, 20, 540, 250);
        add(scrollPane);

        // Nút tải lại dữ liệu từ SQL
        btnLoad = new JButton("Tải Dữ Liệu");
        btnLoad.setBounds(20, 290, 120, 30);
        add(btnLoad);

        btnLoad.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loadDataToTable();
            }
        });

        // Tải dữ liệu luôn khi mở bảng
        loadDataToTable();

        setVisible(true);
    }

    // Hàm lấy dữ liệu từ cơ sở dữ liệu đổ vào bảng
    public void loadDataToTable() {
        tableModel.setRowCount(0); // Xóa dữ liệu cũ trên bảng
        ArrayList<DocGia> list = DocGiaDAO.getInstance().selectAll();
        for (DocGia dg : list) {
            tableModel.addRow(new Object[]{
                dg.getMaDocGia(), dg.getHoTen(), dg.getNgaySinh(), dg.getSoDienThoai(), dg.getEmail()
            });
        }
    }
}