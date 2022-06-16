/* 
Viết một chương trình tính tổng các số nguyên tố từ 1 đến 10_000 với 3 cách. Mỗi cách yêu
cầu sử dụng 1 cấu trúc lặp khác nhau bao gồm: while, do while và for
 */
public class Bai3 {
    public static boolean checkSNT(int n) {
        if (n < 2) {
            return false;
        }
        for (int i = 2; i <= (int) Math.sqrt(n); i++) {
            if (n % i == 0) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        sum_While();
        sum_DoWhile();
        sum_For();
    }

    public static void sum_While() {
        int i = 1, sum = 0;
        while (i <= 10_000) {
            sum += checkSNT(i) ? i : 0;
            i++;
        }
        System.out.println(sum);
    }

    public static void sum_DoWhile() {
        int i = 1, sum = 0;
        do {
            sum += checkSNT(i) ? i : 0;
            i++;
        } while (i <= 10_000);
        System.out.println(sum);
    }

    public static void sum_For() {
        int sum = 0;
        for (int i = 1; i < 10_000; i++) {
            sum += checkSNT(i) ? i : 0;
        }
        System.out.println(sum);
    }
}
