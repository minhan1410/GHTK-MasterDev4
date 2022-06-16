/* 
Viết một chương trình nhận vào 1 trong 12 tháng làm input.Output là số ngày trong tháng từ input.Giả sử tháng 2 luôn chỉ có 28 ngày.Yêu cầu làm theo 2 cách:
    Cách 1: Sử dụng enum và cấu trúc switch.
    Cách 2: Không sử dụng enum.
 */

class Bai1 {
    public static void main(String[] args) {
        c1(12);
        c1(9);
        c1(19);

        c2(10);
        c2(1);
    }

    public static void c1(int month) {
        enum Month {
            Thang_1(31), Thang_2(28), Thang_3(31), Thang_4(30), Thang_5(31), Thang_6(30), Thang_7(31), Thang_8(31),
            Thang_9(30),
            Thang_10(31), Thang_11(30), Thang_12(31);

            private int day;

            private Month(int day) {
                this.day = day;
            }
        }

        if (month >= 1 && month <= 12) {
            Month m = Month.values()[month - 1];

            switch (m) {
                case Thang_1, Thang_2, Thang_3, Thang_4, Thang_5, Thang_6, Thang_7, Thang_8, Thang_9, Thang_10,
                        Thang_11, Thang_12:
                    System.out.println(m + " - " + m.day);
            }
        }
    }

    public static void c2(int month) {
        switch (month) {
            case 1, 3, 5, 7, 8, 10, 12:
                System.out.println("Thang " + month + " 31");
                break;
            case 2:
                System.out.println("Thang " + month + " 28");
                break;
            case 4, 6, 9, 11:
                System.out.println("Thang " + month + " 30");
                break;
            default:
                System.out.println("😒😒😒");
        }
    }
}