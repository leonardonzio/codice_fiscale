import java.time.LocalDate;

public class TestCodiceFiscale {

    public static void main(String[] args) {



        // definisco due codici fiscali di due persone diverse
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



        // ------------cf1---------------
        // controllo che il codice fiscale base di cf1 sia questo
        assert (cf1.calcolaCF().equals("RSSMRA90A01F205Z"));

        // controllo se sono valide anche le sue omocodie
        assert(cf1.verificaCF("RSSMRA90A01F205Z"));
        assert(cf1.verificaCF("RSSMRA90A01FN05O"));
        assert(cf1.verificaCF("RSSMRA90AL1F205K"));
        assert(cf1.verificaCF("RSSMRA90A0MF205R"));
        assert(cf1.verificaCF("RSSMRA90A01F2L5K"));
        assert(cf1.verificaCF("RSSMRA90A01F20RU"));




        // ------------cf2---------------
        // controllo che il codice fiscale base di cf2 sia questo
        assert (cf2.calcolaCF().equals("RSSMRA76T06A944O"));

        // controllo se sono valide anche le sue omocodie
        assert(cf2.verificaCF("RSSMRA76T06A94QL"));
        assert(cf2.verificaCF("RSSMRA76T06A9Q4A"));
        assert(cf2.verificaCF("RSSMRA76T06AV44D"));
        assert(cf2.verificaCF("RSSMRA76T0SA944L"));
        assert(cf2.verificaCF("RSSMRA76TL6A944Z"));
        assert(cf2.verificaCF("RSSMRA7ST06A944A"));
        assert(cf2.verificaCF("RSSMRAT6T06A944L"));

    }

}
