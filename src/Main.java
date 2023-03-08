import java.time.LocalDate;

public class Main {

    public static void main(String[] args) {
        var data = LocalDate.of(2003, 3, 2);
        CodiceFiscale cf = new CodiceFiscale(
                "Prova",
                "Uno",
                data,
                "NonRoma",
                'M'
        );

        System.out.println(cf.calcolaCognome());
        System.out.println(cf.calcolaNome());
        System.out.println(cf.calcolaNascita());
   }
}