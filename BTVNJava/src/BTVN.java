public class BTVN {

    // Bài 1: Tính tổng các số chẵn

    public static int TongSoChan(int n) {
        int sum = 0;
        for (int i = 0; i <= n; i++) {
            if (i % 2 == 0) {
                sum += i;
            }
        }
        return sum;
    }

    // Bài 2: Xóa nguyên âm trong chuỗi

    public static String XoaNguyenAm(String s) {
        StringBuilder ketqua = new StringBuilder();

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if ("aeiouAEIOU".indexOf(c) == -1) {
                ketqua.append(c);
            }
        }
        return ketqua.toString();
    }

    // Bài 3: Tính thuế

    public static double TinhThue(double luong) {
        double thue = 0;
        if (luong <= 0) {
            return 0;
        } else if (luong <= 5) {
            thue = luong * 0.05;
        } else if (luong <= 10) {
            thue = 5 * 0.05 + (luong - 5) * 0.10;
        } else if (luong <= 18) {
            thue = 5 * 0.05 + 5 * 0.10 + (luong - 10) * 0.15;
        } else if (luong <= 32) {
            thue = 5 * 0.05 + 5 * 0.10 + 8 * 0.15 + (luong - 18) * 0.20;
        } else if (luong <= 52) {
            thue = 5 * 0.05 + 5 * 0.10 + 8 * 0.15 + 14 * 0.20 + (luong - 32) * 0.25;
        } else if (luong <= 80) {
            thue = 5 * 0.05 + 5 * 0.10 + 8 * 0.15 + 14 * 0.20 + 20 * 0.25 + (luong - 52) * 0.30;
        } else {
            thue = 5 * 0.05 + 5 * 0.10 + 8 * 0.15 + 14 * 0.20 + 20 * 0.25 + 28 * 0.30 + (luong - 80) * 0.35;
        }
        return thue;
    }

    // Bài 4: Đếm số từ trong một chuỗi

    public static int DemSoTu(String s) {
        s = s.trim();
        if (s.isEmpty()) return 0;
        return s.split("\\s+").length;  // tách theo 1 hoặc nhiều khoảng trắng
    }

    public static void main(String[] args) {
        System.out.println("Tong so chan: " + TongSoChan(5));
        System.out.println("So tu: " + DemSoTu("Mai    Xuan   Quoc"));
        System.out.println("Thue: " + TinhThue(25000000));
        System.out.println("Xoa nguyen am: " + XoaNguyenAm("Hello"));
    }
}
