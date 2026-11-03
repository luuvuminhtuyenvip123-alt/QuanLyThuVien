package view;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginForm extends JFrame {
    private JTextField txtUser;
    private JPasswordField txtPass;
    private JButton btnLogin;

    public LoginForm() {
        setTitle("Đăng Nhập - Quản Lý Thư Viện");
        setSize(350, 220);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);

        JLabel lblTitle = new JLabel("ĐĂNG NHẬP HỆ THỐNG");
        lblTitle.setBounds(80, 20, 200, 25);
        add(lblTitle);

        JLabel lblUser = new JLabel("Tài khoản:");
        lblUser.setBounds(30, 60, 80, 25);
        add(lblUser);

        txtUser = new JTextField("admin");
        txtUser.setBounds(110, 60, 180, 25);
        add(txtUser);

        JLabel lblPass = new JLabel("Mật khẩu:");
        lblPass.setBounds(30, 100, 80, 25);
        add(lblPass);

        txtPass = new JPasswordField("123");
        txtPass.setBounds(110, 100, 180, 25);
        add(txtPass);

        btnLogin = new JButton("Đăng Nhập");
        btnLogin.setBounds(110, 140, 100, 30);
        add(btnLogin);

        // Sự kiện khi bấm nút Đăng Nhập
        btnLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String user = txtUser.getText();
                String pass = new String(txtPass.getPassword());
                
                if(user.equals("admin") && pass.equals("123")) {
                    JOptionPane.showMessageDialog(null, "Đăng nhập thành công!");
                    dispose(); // Đóng form đăng nhập
                    new MainFrame(); // Mở giao diện chính quản lý độc giả
                } else {
                    JOptionPane.showMessageDialog(null, "Sai tài khoản hoặc mật khẩu!");
                }
            }
        });

        setVisible(true);
    }

    public static void main(String[] args) {
        new LoginForm();
    }
}