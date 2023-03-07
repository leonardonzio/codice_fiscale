import java.time.LocalDate;
import java.util.Date;

public class CodiceFiscale {
    private String nome, cognome, città;
    private LocalDate nascita;
    private char sesso;
    private String consonanti = "bcdfghjklmnpqrstvwxyzBCDFGHJKLMNPQRSTVWXYZ";
    private String vocali = "aeiouàèéìòù";

    public CodiceFiscale(String nome, String cognome, LocalDate nascita, String città, char sesso) {
        this.nome = nome.toUpperCase();
        this.cognome = cognome.toUpperCase();
        this.nascita = nascita;
        this.città = città;
        this.sesso = sesso;
    }

    private int quanteConsonanti(String str) {
        int counter = 0;

        for (int i = 0; i < str.length(); i++) {
            char ch = str.charAt(i);
            if (consonanti.contains(Character.toString(ch)))
                counter++;
        }
        return counter;
    }


    private String[] separa(String str) {

        StringBuilder cons = new StringBuilder();
        StringBuilder voc = new StringBuilder();

        for (char ch : str.toCharArray()) {
            if (consonanti.contains(Character.toString(ch)))
                cons.append(ch);
            else
                voc.append(ch);
        }
        String[] res = new String[]{cons.toString(), voc.toString()};

        return res;
    }


    public String calcolaCognome() {
        String[] divisione = separa(cognome);
        String consonanti = divisione[0];
        String vocali = divisione[1];

        if (consonanti.length() > 2) {//abbastanza consonanti
            return consonanti.substring(0, 3);
        }

        StringBuilder result = new StringBuilder();
        result.append(consonanti);
        int rimaste = 3 - consonanti.length();
        for (char ch: vocali.toCharArray()) {//aggiungi vocali
            result.append(ch);
            rimaste--;

            if (rimaste == 0) {
                return result.toString();
            }
        }

        while (rimaste != 0) {//aggiungi x
            result.append("X");
            rimaste--;
        }

        return result.toString();
    }


    public String calcolaNome(){
        String[] divisione = separa(nome);
        String consonanti = divisione[0];
        String vocali = divisione[1];

        if(consonanti.length() > 3)
            return consonanti.charAt(0) + consonanti.substring(2,4);

        else if (consonanti.length() == 3) {
            return consonanti.substring(0, 3);
        }

        StringBuilder result = new StringBuilder();
        result.append(consonanti);
        int rimaste = 3 - consonanti.length();
        for (char ch: vocali.toCharArray()) {//aggiungi vocali
            result.append(ch);
            rimaste--;

            if (rimaste == 0) {
                return result.toString();
            }
        }

        while (rimaste != 0) {//aggiungi x
            result.append("X");
            rimaste--;
        }

        return result.toString();
    }


}