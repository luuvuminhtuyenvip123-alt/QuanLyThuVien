package model;
public class DocGia {
    private String maDocGia;
    private String hoTen;
    private String ngaySinh;
    private String soDienThoai;
    private String email;

    // Hàm khởi tạo không tham số
    public DocGia() {}

    // Hàm khởi tạo đầy đủ tham số
    public DocGia(String maDocGia, String hoTen, String ngaySinh, String soDienThoai, String email) {
        this.maDocGia = maDocGia;
        this.hoTen = hoTen;
        this.ngaySinh = ngaySinh;
        this.soDienThoai = soDienThoai;
        this.email = email;
    }

    // lấy và gán dữ liệu
    public String getMaDocGia() { return maDocGia; }
    public void setMaDocGia(String maDocGia) { this.maDocGia = maDocGia; }
    
    public String getHoTen() { return hoTen; }
    public void setHoTen(String hoTen) { this.hoTen = hoTen; }
    
    public String getNgaySinh() { return ngaySinh; }
    public void setNgaySinh(String ngaySinh) { this.ngaySinh = ngaySinh; }
    
    public String getSoDienThoai() { return soDienThoai; }
    public void setSoDienThoai(String soDienThoai) { this.soDienThoai = soDienThoai; }
    
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
}