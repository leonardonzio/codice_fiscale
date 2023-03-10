import java.time.LocalDate;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        var data = LocalDate.of(1990, 1, 1);
        CodiceFiscale cf = new CodiceFiscale(
                "Mario",
                "Rossi",
                data,
                "Milano",
                'M'
        );
        System.out.println("codice fiscale: " + cf.calcolaCF());
        String omocodici = Arrays.toString(cf.calcolaOmocodici());
        System.out.println("codici fiscali omocodici: " + omocodici);

   }
}