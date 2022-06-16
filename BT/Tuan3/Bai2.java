import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

/* 
    Viết chương trình tính ra số ngày giữa 2 mốc thời điểm người dùng nhập vào. Dữ liệu nhập
    vào của người dùng sẽ có dạng y1 m1 d1 y2 m2 d2. Với điều kiện mốc thời gian 1 lớn hơn
    mốc thời gian 2.
 */
public class Bai2 {
    public static void main(String[] args) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        differenceDate(sdf.parse("1/05/2022"), sdf.parse("26/05/2022"));
        differenceDate(sdf.parse("26/05/2022"), sdf.parse("1/05/2022"));
    }

    public static void differenceDate(Date d1, Date d2) {
        long sub = d1.getTime() - d2.getTime();
        if (sub > 0) {
            long diffInMillies = Math.abs(sub);
            long diff = TimeUnit.DAYS.convert(diffInMillies, TimeUnit.MILLISECONDS);
            System.out.println("Số ngày giữa 2 ngày: " + diff);
        } else {
            System.out.println("Mốc thời gian 1 lớn hơn mốc thời gian 2");
        }
    }
}
