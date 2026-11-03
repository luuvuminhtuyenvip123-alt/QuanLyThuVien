package dao;

import database.DBConnection;
import model.DocGia;
import java.sql.*;
import java.util.ArrayList;

public class DocGiaDAO {
    // Tạo đối tượng DAO để gọi từ bên ngoài
    public static DocGiaDAO getInstance() {
        return new DocGiaDAO();
    }

    // 1. Thêm độc giả mới (INSERT)
    public int insert(DocGia t) {
        int ketQua = 0;
        try {
            Connection con = DBConnection.getConnection();
            Statement st = con.createStatement();
            String sql = "INSERT INTO DocGia (MaDocGia, HoTen, NgaySinh, SoDienThoai, Email) VALUES ("
                    + "'" + t.getMaDocGia() + "', "
                    + "N'" + t.getHoTen() + "', "
                    + "'" + t.getNgaySinh() + "', "
                    + "'" + t.getSoDienThoai() + "', "
                    + "'" + t.getEmail() + "')";
            ketQua = st.executeUpdate(sql);
            DBConnection.closeConnection(con);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ketQua;
    }

    // 2. Cập nhật thông tin độc giả (UPDATE)
    public int update(DocGia t) {
        int ketQua = 0;
        try {
            Connection con = DBConnection.getConnection();
            Statement st = con.createStatement();
            String sql = "UPDATE DocGia SET "
                    + "HoTen = N'" + t.getHoTen() + "', "
                    + "NgaySinh = '" + t.getNgaySinh() + "', "
                    + "SoDienThoai = '" + t.getSoDienThoai() + "', "
                    + "Email = '" + t.getEmail() + "' "
                    + "WHERE MaDocGia = '" + t.getMaDocGia() + "'";
            ketQua = st.executeUpdate(sql);
            DBConnection.closeConnection(con);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ketQua;
    }

    // 3. Xóa độc giả theo mã (DELETE)
    public int delete(String maDocGia) {
        int ketQua = 0;
        try {
            Connection con = DBConnection.getConnection();
            Statement st = con.createStatement();
            String sql = "DELETE FROM DocGia WHERE MaDocGia = '" + maDocGia + "'";
            ketQua = st.executeUpdate(sql);
            DBConnection.closeConnection(con);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ketQua;
    }

    // 4. Lấy danh sách toàn bộ độc giả (SELECT ALL)
    public ArrayList<DocGia> selectAll() {
        ArrayList<DocGia> list = new ArrayList<>();
        try {
            Connection con = DBConnection.getConnection();
            Statement st = con.createStatement();
            String sql = "SELECT * FROM DocGia";
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                list.add(new DocGia(
                        rs.getString("MaDocGia"),
                        rs.getString("HoTen"),
                        rs.getString("NgaySinh"),
                        rs.getString("SoDienThoai"),
                        rs.getString("Email")
                ));
            }
            DBConnection.closeConnection(con);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    // 5. Tìm kiếm độc giả theo Mã hoặc Tên (SEARCH)
    public ArrayList<DocGia> selectByCondition(String keyword) {
        ArrayList<DocGia> list = new ArrayList<>();
        try {
            Connection con = DBConnection.getConnection();
            Statement st = con.createStatement();
            String sql = "SELECT * FROM DocGia WHERE MaDocGia LIKE '%" + keyword + "%' OR HoTen LIKE N'%" + keyword + "%'";
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                list.add(new DocGia(
                        rs.getString("MaDocGia"),
                        rs.getString("HoTen"),
                        rs.getString("NgaySinh"),
                        rs.getString("SoDienThoai"),
                        rs.getString("Email")
                ));
            }
            DBConnection.closeConnection(con);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
}