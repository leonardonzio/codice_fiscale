import java.io.CharArrayReader;
import java.time.LocalDate;
import java.util.Date;

public class CodiceFiscale {
    private String nome, cognome, città;
    private LocalDate nascita;
    private char sesso;
    private String consonanti = "bcdfghjklmnpqrstvwxyzBCDFGHJKLMNPQRSTVWXYZ";
    private String vocali = "aeiouàèéìòù";


    // costruttore codice fiscale
    public CodiceFiscale(String nome, String cognome, LocalDate nascita, String città, char sesso) {
        this.nome = nome.toUpperCase();
        this.cognome = cognome.toUpperCase();
        this.nascita = nascita;
        this.città = città;
        this.sesso = sesso;
    }


    // getter
    public String getNome() {
        return nome;
    }

    public String getCognome() {
        return cognome;
    }

    public String getCittà() {
        return città;
    }

    public LocalDate getNascita() {
        return nascita;
    }

    public char getSesso() {
        return sesso;
    }

    // prende in input una stringa e ne restituisce due: una con le consonanti ed una con
    // le vocali (entrambe in ordine)
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


    // fa return delle tre lettere del CF dato un cognome
    public String calcolaCognome() {
        String[] divisione = separa(getCognome());
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


    // fa return delle tre lettere del CF dato un nome
    public String calcolaNome(){
        String[] divisione = separa(getNome());
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


    public String calcolaNascita(){

        StringBuilder res = new StringBuilder();
        char[] lettere = {'A', 'B', 'C', 'D', 'E', 'H', 'L', 'M', 'P', 'R', 'S', 'T'};

        int anno = getNascita().getYear();
        int mese = getNascita().getMonthValue();
        int giorno = getNascita().getDayOfMonth();
        char sesso = Character.toLowerCase(getSesso());

        // -----converto l'anno------
        String annoStr = Integer.toString(anno).substring(2,4);
        res.append(annoStr);

        // -----converto il mese-----

        char letteraMese = lettere[mese - 1];
        String letteraMeseStr = Character.toString(letteraMese);
        res.append(letteraMeseStr);

        // -----converto il giorno-----
        if (sesso == 'm'){
            if (giorno >= 1 && giorno <= 9){
                res.append("0");
                res.append(Integer.toString(giorno));
            }

            else res.append(Integer.toString(giorno));
            return res.toString();
        }

        // se è donna
        res.append(Integer.toString(giorno+40));
        return res.toString();
    }

}