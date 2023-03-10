import java.time.LocalDate;

public class TestCodiceFiscale {

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
                LocalDate.of(1975, 12, 6),
                "Bologna",
                'M'
        );

        assert (cf1.calcolaCF().equals("RSSMRA90A01F205Z"));
    }


}
