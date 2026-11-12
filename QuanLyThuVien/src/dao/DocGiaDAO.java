
package dao;

import database.DBConnection;
import model.DocGia;

import java.sql.*;
import java.util.ArrayList;

public class DocGiaDAO {

    private static DocGiaDAO instance;

    public static DocGiaDAO getInstance() {
        if (instance == null) {
            instance = new DocGiaDAO();
        }
        return instance;
    }

    // Tạo bảng nếu chưa tồn tại
    public void createTableIfNotExists() throws SQLException {

        String sql =
            "IF OBJECT_ID('dbo.tblDocGia', 'U') IS NULL "
            + "BEGIN "
            + "CREATE TABLE dbo.tblDocGia ("
            + "fldMaDG VARCHAR(20) PRIMARY KEY,"
            + "fldHoTen NVARCHAR(100) NOT NULL,"
            + "fldNgaySinh VARCHAR(20),"
            + "fldGioiTinh BIT,"
            + "fldLop NVARCHAR(50),"
            + "fldDienThoai VARCHAR(20)"
            + ") END";

        try (Connection con = DBConnection.getConnection();
             Statement st = con.createStatement()) {
            st.executeUpdate(sql);
        }
    }

    // Thêm độc giả
    public int insert(DocGia dg) throws SQLException {

        String sql =
            "INSERT INTO dbo.tblDocGia "
            + "(fldMaDG, fldHoTen, fldNgaySinh, fldGioiTinh, fldLop, fldDienThoai) "
            + "VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement pst = con.prepareStatement(sql)) {

            pst.setString(1, dg.getMaDG());
            pst.setString(2, dg.getHoTen());
            pst.setString(3, dg.getNgaySinh());
            pst.setBoolean(4, dg.isGioiTinh());
            pst.setString(5, dg.getLop());
            pst.setString(6, dg.getDienThoai());

            return pst.executeUpdate();
        }
    }

    // Sửa độc giả
    public int update(DocGia dg) throws SQLException {

        String sql =
            "UPDATE dbo.tblDocGia SET "
            + "fldHoTen = ?, fldNgaySinh = ?, "
            + "fldGioiTinh = ?, fldLop = ?, "
            + "fldDienThoai = ? WHERE fldMaDG = ?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement pst = con.prepareStatement(sql)) {

            pst.setString(1, dg.getHoTen());
            pst.setString(2, dg.getNgaySinh());
            pst.setBoolean(3, dg.isGioiTinh());
            pst.setString(4, dg.getLop());
            pst.setString(5, dg.getDienThoai());
            pst.setString(6, dg.getMaDG());

            return pst.executeUpdate();
        }
    }

    // Xóa độc giả
    public int delete(String maDG) throws SQLException {

        String sql =
            "DELETE FROM dbo.tblDocGia WHERE fldMaDG = ?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement pst = con.prepareStatement(sql)) {

            pst.setString(1, maDG);

            return pst.executeUpdate();
        }
    }

    // Lấy toàn bộ danh sách độc giả
    public ArrayList<DocGia> selectAll() throws SQLException {

        ArrayList<DocGia> list = new ArrayList<>();

        String sql = "SELECT * FROM dbo.tblDocGia ORDER BY fldMaDG";

        try (Connection con = DBConnection.getConnection();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {

                DocGia dg = new DocGia(
                    rs.getString("fldMaDG"),
                    rs.getString("fldHoTen"),
                    rs.getString("fldNgaySinh"),
                    rs.getBoolean("fldGioiTinh"),
                    rs.getString("fldLop"),
                    rs.getString("fldDienThoai")
                );

                list.add(dg);
            }
        }

        return list;
    }

    // Tìm kiếm theo mã hoặc tên
    public ArrayList<DocGia> selectByCondition(String keyword)
            throws SQLException {

        ArrayList<DocGia> list = new ArrayList<>();

        String sql =
            "SELECT * FROM dbo.tblDocGia "
            + "WHERE fldMaDG LIKE ? OR fldHoTen LIKE ? "
            + "ORDER BY fldMaDG";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement pst = con.prepareStatement(sql)) {

            pst.setString(1, "%" + keyword + "%");
            pst.setString(2, "%" + keyword + "%");

            try (ResultSet rs = pst.executeQuery()) {

                while (rs.next()) {

                    DocGia dg = new DocGia(
                        rs.getString("fldMaDG"),
                        rs.getString("fldHoTen"),
                        rs.getString("fldNgaySinh"),
                        rs.getBoolean("fldGioiTinh"),
                        rs.getString("fldLop"),
                        rs.getString("fldDienThoai")
                    );

                    list.add(dg);
                }
            }
        }

        return list;
    }
}
