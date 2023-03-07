import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {

        CodiceFiscale cf = new CodiceFiscale(
                "Prova",
                "Uno",
                LocalDate.now(),
                "NonRoma",
                'M'
        );

        System.out.println(cf.calcolaCognome());
        System.out.println(cf.calcolaNome());
   }
}