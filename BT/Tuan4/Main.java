import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

/* 
Cho 2 danh sách chứa các số có thể duplicate. Viết chương trình lấy ra 1 danh sách duy nhất chứa tất cả các số đấy, không chứa phần tử duplicate và danh sách kq được sắp xếp theo thứ tự từ bé đến lớn.
VD:
    Danh sách 1: 1,4,6,9,11,2,7,3,8
    Danh sách 2: 1,3,2,4,5,10,11,6
    Danh sách kq: 1,2,3,4,5,6,7,8,9,10,11 
*/

public class Main {
    public static void main(String[] args) {
        System.out.println(btvn(List.of(1, 4, 6, 9, 11, 2, 7, 3, 8), List.of(1, 3, 2, 4, 5, 10, 11, 6)));
    }

    public static List<Integer> btvn(List<Integer> l1, List<Integer> l2){
        Set<Integer> result = new TreeSet<Integer>();
        result.addAll(l1);
        result.addAll(l2);
        return new ArrayList<>(result);
    }
}