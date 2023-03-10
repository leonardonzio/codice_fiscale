import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        var data = LocalDate.of(1996, 04, 05);
        CodiceFiscale cf = new CodiceFiscale(
                "Marco",
                "Alfonsi",
                data,
                "Milano",
                'M'
        );
        System.out.println(cf.calcolaCf());
   }
}