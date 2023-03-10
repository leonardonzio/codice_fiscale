import java.time.LocalDate;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {

        CodiceFiscale cf1 = new CodiceFiscale(
                "Mario",
                "Rossi",
                LocalDate.of(1990, 1, 1),
                "Milano",
                'M'
        );

        CodiceFiscale cf2 = new CodiceFiscale(
                "Mario",
                "Rossi",
                LocalDate.of(1976, 12, 6),
                "Bologna",
                'M'
        );

        System.out.println("Prima persona:");
        System.out.println("codice fiscale: " + cf1.calcolaCF());
        String omocodici1 = Arrays.toString(cf1.calcolaOmocodici());
        System.out.println("codici fiscali omocodici: " + omocodici1 + "\n");

        System.out.println("Seconda persona:");
        System.out.println("codice fiscale: " + cf1.calcolaCF());
        String omocodici2 = Arrays.toString(cf1.calcolaOmocodici());
        System.out.println("codici fiscali omocodici: " + omocodici2 + "\n");

   }
}