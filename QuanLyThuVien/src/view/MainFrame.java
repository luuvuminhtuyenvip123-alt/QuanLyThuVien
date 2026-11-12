package view;

import javax.swing.*;

public class MainFrame extends JFrame {

    public MainFrame() {
        setTitle("Hệ Thống Quản Lý Thư Viện");
        setSize(1000, 700);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Tạo thanh chứa các tab
        JTabbedPane tabbedPane = new JTabbedPane();

        // Thêm tabbedPane vào cửa sổ chính
        add(tabbedPane);
     // Chỉ thêm mỗi form đúng 1 lần duy nhất:
        tabbedPane.add("Quản Lý Độc Giả", new DocGiaForm());
        tabbedPane.add("Quản Lý Sách", new SachForm());
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new MainFrame().setVisible(true);
            }
        });
    }
}