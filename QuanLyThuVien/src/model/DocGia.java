package model;

public class DocGia {
    private String maDG;
    private String hoTen;
    private String ngaySinh;
    private boolean gioiTinh; // true: Nam, false: Nữ
    private String lop;
    private String dienThoai;

    public DocGia() {
    }

    public DocGia(String maDG, String hoTen, String ngaySinh, boolean gioiTinh, String lop, String dienThoai) {
        this.maDG = maDG;
        this.hoTen = hoTen;
        this.ngaySinh = ngaySinh;
        this.gioiTinh = gioiTinh;
        this.lop = lop;
        this.dienThoai = dienThoai;
    }

    public String getMaDG() { return maDG; }
    public void setMaDG(String maDG) { this.maDG = maDG; }

    public String getHoTen() { return hoTen; }
    public void setHoTen(String hoTen) { this.hoTen = hoTen; }

    public String getNgaySinh() { return ngaySinh; }
    public void setNgaySinh(String ngaySinh) { this.ngaySinh = ngaySinh; }

    public boolean isGioiTinh() { return gioiTinh; }
    public void setGioiTinh(boolean gioiTinh) { this.gioiTinh = gioiTinh; }

    public String getLop() { return lop; }
    public void setLop(String lop) { this.lop = lop; }

    public String getDienThoai() { return dienThoai; }
    public void setDienThoai(String dienThoai) { this.dienThoai = dienThoai; }
}