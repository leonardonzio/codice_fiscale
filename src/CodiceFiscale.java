import com.sun.jdi.connect.LaunchingConnector;

import java.io.CharArrayReader;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Date;

public class CodiceFiscale {
    private String nome, cognome, città;
    private LocalDate nascita;
    private char sesso;
    private String vocali = "aeiouàèéìòù";
    private static char[] alfabetoInglese = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H',
            'I', 'J', 'K', 'L', 'M', 'N', 'O',
            'P', 'Q', 'R', 'S', 'T', 'U', 'V',
            'W', 'X', 'Y', 'Z'};

    private static String[] numeriSpeciali = {"1", "0", "5", "7", "9", "13", "15", "17", "19", "21",
            "2", "4", "18", "20", "11", "3", "6", "8", "12", "14",
            "16", "10", "22", "25", "24", "23"};

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
            String currLettera = Character.toString(ch).toLowerCase();
            if (vocali.contains(currLettera))
                voc.append(ch);
            else
                cons.append(ch);
        }
        String[] res = new String[]{cons.toString(), voc.toString()};

        return res;
    }


    // fa return delle tre lettere del CF dato un cognome
    private String calcolaCognome() {
        String[] divisione = separa(getCognome());
        String consonanti = divisione[0];
        String vocali = divisione[1];

        if (consonanti.length() > 2) {// abbastanza consonanti
            return consonanti.substring(0, 3);
        }

        StringBuilder result = new StringBuilder();
        result.append(consonanti);
        int rimaste = 3 - consonanti.length();
        for (char ch: vocali.toCharArray()) {// aggiungi vocali
            result.append(ch);
            rimaste--;

            if (rimaste == 0) {
                return result.toString();
            }
        }

        while (rimaste != 0) {// aggiungi x
            result.append("X");
            rimaste--;
        }

        return result.toString();
    }


    // fa return delle tre lettere del CF dato un nome
    private String calcolaNome(){
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
        for (char ch: vocali.toCharArray()) {// aggiungi vocali
            result.append(ch);
            rimaste--;

            if (rimaste == 0) {
                return result.toString();
            }
        }

        while (rimaste != 0) {// aggiungi x
            result.append("X");
            rimaste--;
        }

        return result.toString();
    }


    private String calcolaNascita(){

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
                res.append(giorno);
            }

            else res.append(giorno);
            return res.toString();
        }

        // se è donna
        res.append(giorno + 40);
        return res.toString();
    }


    private String calcolaCittà(){

        String città = getCittà().toLowerCase();

        if      (città.equals("rimini"))    return "H294";
        else if (città.equals("milano"))    return "F205";
        else if (città.equals("bologna"))   return "A944";
        else if (città.equals("siracusa"))  return "I754";

        System.err.println("--ERRORE-CITTA'--");       return "-1";
    }



    // funzione che resittuisce l'indice di un dato carattere in un array, -1 se non lo trova
    private static int findCharIndex(char[] arr, char c) throws Exception{
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == c)
                return i;

        }
        throw new Exception("errore");
    }


    // return ultima lettera
    private String calcolaUltimaLettera(){

        StringBuilder cfParziale = new StringBuilder();

        String cognomeStr = calcolaCognome();
        String nomeStr = calcolaNome();
        char[] nascita = calcolaNascita().toCharArray();
        char[] città = calcolaCittà().toCharArray();


        // sostituisco numeri con lettere dell'alfabeto inglese in nascita
        for (int i = 0; i < nascita.length; i++) {
            if(Character.isDigit(nascita[i])){
                int curr = Integer.parseInt(Character.toString(nascita[i]));
                char letteraDaMettere = alfabetoInglese[curr];
                nascita[i] = letteraDaMettere;
            }
        }
        String nascitaStr = new String(nascita);


        // sostituisco numeri con lettere dell'alfabeto inglese in città
        for (int i = 0; i < città.length; i++) {
            if(Character.isDigit(città[i])){
                int curr = Integer.parseInt(Character.toString(città[i]));
                char letteraDaMettere = alfabetoInglese[curr];
                città[i] = letteraDaMettere;
            }
        }
        String cittàStr = new String(città);

        // costruisco il cf parziale
        cfParziale.append(cognomeStr);
        cfParziale.append(nomeStr);
        cfParziale.append(nascitaStr);
        cfParziale.append(cittàStr);


        // lo trasformo in un array di char in modo da poter scorrere ogni carattere
        char[] cfParzialeArray = cfParziale.toString().toCharArray();


        // faccio il calcolo vero e proprio dell'ultima lettera
        int somma = 0;

        // ciclo da 1 a cfParzialeArray.length
        for (int i = 0; i < cfParzialeArray.length; i++) {

            // posizione pari
            if(i % 2 != 0){
                char curr = cfParzialeArray[i];
                try {
                    int valoreLettera = findCharIndex(alfabetoInglese, curr);
                    somma += valoreLettera;
                }catch (Exception e){
                    System.out.println(e.getMessage());
                }
            }

            // posizione dispari
            else{
                char curr = cfParzialeArray[i];
                try {
                    int indice = findCharIndex(alfabetoInglese, curr);
                    String valoreLettera = numeriSpeciali[indice];
                    int valoreLetteraInt = Integer.parseInt(valoreLettera);
                    somma += valoreLetteraInt;
                }catch (Exception e){
                    System.out.println(e.getMessage());
                }


            }

        }

        int finalIndex = somma % 26;
        char finalLetter = alfabetoInglese[finalIndex];

        return Character.toString(finalLetter);
    }


    // funzione che crea codice fiscale finale
    public String calcolaCF(){

        StringBuilder codFisc = new StringBuilder();

        String cognomeCod = calcolaCognome();
        String nomeCod = calcolaNome();
        String nascitaCod = calcolaNascita();
        String cittàCod = calcolaCittà();
        String lastCode = calcolaUltimaLettera();

        codFisc.append(cognomeCod);
        codFisc.append(nomeCod);
        codFisc.append(nascitaCod);
        codFisc.append(cittàCod);
        codFisc.append(lastCode);

        return codFisc.toString();
    }


    private static String calcolaUltimaLetteraGenerico(char[] cf){

        int somma = 0;

        // converto tutto in lettere
        for (int i = 0; i < cf.length; i++) {
            if(Character.isDigit(cf[i])){
                int curr = Integer.parseInt(Character.toString(cf[i]));
                char letteraDaMettere = alfabetoInglese[curr];
                cf[i] = letteraDaMettere;
            }
        }

        for (int i = 0; i < cf.length; i++) {

            // posizione pari
            if(i % 2 != 0){
                char curr = cf[i];
                try {
                    int valoreLettera = findCharIndex(alfabetoInglese, curr);
                    somma += valoreLettera;
                }catch (Exception e){
                    System.out.println(e.getMessage());
                }
            }

            // posizione dispari
            else{
                char curr = cf[i];
                try {
                    int indice = findCharIndex(alfabetoInglese, curr);
                    String valoreLettera = numeriSpeciali[indice];
                    int valoreLetteraInt = Integer.parseInt(valoreLettera);
                    somma += valoreLetteraInt;
                }catch (Exception e){
                    System.out.println(e.getMessage());
                }


            }

        }
        int finalIndex = somma % 26;
        char finalLetter = alfabetoInglese[finalIndex];

        return Character.toString(finalLetter);

    }


    public String[] calcolaOmocodici(){

        int indexOm = 0;
        String[] cfOmocodici = new String[0];
        char[] cambi = {'L', 'M', 'N', 'P', 'Q', 'R', 'S', 'T', 'U', 'V'};
        String cfBase= calcolaCF();

        for (int i = 14; i >= 6; i--) {

            char[] cf = Arrays.copyOf(cfBase.toCharArray(), cfBase.toCharArray().length - 1);

            if (Character.isDigit(cf[i])) {

                int indice = Integer.parseInt(Character.toString(cf[i]));
                cf[i] = cambi[indice];

                String cfPartial = new String(cf);

                String ultimaLettera = calcolaUltimaLetteraGenerico(cf);

                String finalCf = cfPartial + ultimaLettera;


                cfOmocodici = Arrays.copyOf(cfOmocodici, cfOmocodici.length + 1);
                cfOmocodici[indexOm] = finalCf;
                indexOm++;
            }
        }

        return cfOmocodici;
    }

}