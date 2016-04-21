
package ohtu.intjoukkosovellus;

public class IntJoukko {

    public final static int KAPASITEETTI = 5, // aloitustalukon koko
                            OLETUSKASVATUS = 5;  // luotava uusi taulukko on 
    // näin paljon isompi kuin vanha
    private int kasvatuskoko;     // Uusi taulukko on tämän verran vanhaa suurempi.
    private int[] lukujono;      // Joukon luvut säilytetään taulukon alkupäässä. 
    private int alkioidenLkm;    // Tyhjässä joukossa alkioiden_määrä on nolla. 

    public IntJoukko() {
        this(KAPASITEETTI, OLETUSKASVATUS);
    }

    public IntJoukko(int kapasiteetti) {
        this(kapasiteetti, OLETUSKASVATUS);
    }
    
    
    public IntJoukko(int kapasiteetti, int kasvatuskoko) {
        if (kapasiteetti < 0 ||kasvatuskoko < 0 ) {
            throw new IndexOutOfBoundsException("Kapasitteetti väärin");//heitin vaan jotain :D
        }
        lukujono = new int[kapasiteetti];
        this.kasvatuskoko = kasvatuskoko;

    }

    public boolean lisaa(int luku) {
        if (onkoTaulukossa(luku)) {
            return false;
        } else {
            lukujono[alkioidenLkm] = luku;
            alkioidenLkm++;
            kasvataTaulukkoa();
            return true;
        }
    }
    
    private void kasvataTaulukkoa() {
        if (alkioidenLkm == lukujono.length) {
            int[] vanhaTaulukko = new int[lukujono.length];
            kopioiTaulukko(lukujono, vanhaTaulukko);
            lukujono = new int[alkioidenLkm + kasvatuskoko];
            kopioiTaulukko(vanhaTaulukko, lukujono);
        }
    }
    
    public boolean onkoTaulukossa(int luku) {
        for (int i = 0; i < alkioidenLkm; i++) {
            if (luku == lukujono[i]) {
                return true;
            }
        }
        return false;
    }

    public boolean kuuluu(int luku) {
        for (int i = 0; i < alkioidenLkm; i++) {
            if (luku == lukujono[i]) {
                return true;
            }
        }
        return false;
    }

    public boolean poista(int luku) {
        int kohta = etsiTaulukosta(luku);
        if (kohta != -1) {
            siirraTaulukkoa(kohta);
            alkioidenLkm--;
            return true;
        }
        return false;
    }

    private int etsiTaulukosta(int luku) {
        for (int i = 0; i < alkioidenLkm; i++) {
            if (luku == lukujono[i]) {
                return i;
            }
        }
        return -1;
    }

    private void siirraTaulukkoa(int kohta) {
        for (int j = kohta; j < alkioidenLkm - 1; j++) {
            lukujono[j] = lukujono[j + 1];
            lukujono[j + 1] = 0;
        }
    }

    private void kopioiTaulukko(int[] vanha, int[] uusi) {
        for (int i = 0; i < vanha.length; i++) {
            uusi[i] = vanha[i];
        }

    }

    public int mahtavuus() {
        return alkioidenLkm;
    }


    @Override
    public String toString() {
        if(alkioidenLkm == 0){
            return "{}";
        }
        String tuotos ="{";
        tuotos += kayLapiTaulukonLuvut();
        tuotos += lukujono[alkioidenLkm - 1] + "}";
        return tuotos;
    }
    
    public String kayLapiTaulukonLuvut(){
        String tuotos = "";
        for(int i = 0; i < alkioidenLkm - 1; i++){
            tuotos += lukujono[i];
            tuotos += ", ";
        }
        return tuotos;
    }

    public int[] toIntArray() {
        int[] taulu = new int[alkioidenLkm];
        for (int i = 0; i < taulu.length; i++) {
            taulu[i] = lukujono[i];
        }
        return taulu;
    }
   

    public static IntJoukko yhdiste(IntJoukko a, IntJoukko b) {
        IntJoukko x = new IntJoukko();
        int[] aTaulu = a.toIntArray();
        int[] bTaulu = b.toIntArray();
        kopioiAlkiot(aTaulu, x);
        kopioiAlkiot(bTaulu, x);
        return x;
    }

    private static void kopioiAlkiot(int[] aTaulu, IntJoukko x) {
        for (int i = 0; i < aTaulu.length; i++) {
            x.lisaa(aTaulu[i]);
        }
    }

    public static IntJoukko leikkaus(IntJoukko a, IntJoukko b) {
        IntJoukko y = new IntJoukko();
        int[] aTaulu = a.toIntArray();
        int[] bTaulu = b.toIntArray();
        molemmistaLoytyvat(aTaulu, bTaulu, y);
        return y;
    }

    private static void molemmistaLoytyvat(int[] aTaulu, int[] bTaulu, IntJoukko y) {
        for (int i = 0; i < aTaulu.length; i++) {
            for (int j = 0; j < bTaulu.length; j++) {
                if (aTaulu[i] == bTaulu[j]) {
                    y.lisaa(bTaulu[j]);
                }
            }
        }
    }
    
    public static IntJoukko erotus ( IntJoukko a, IntJoukko b) {
        IntJoukko z = new IntJoukko();
        int[] aTaulu = a.toIntArray();
        int[] bTaulu = b.toIntArray();
        kopioiAlkiot(aTaulu, z);
        poistaAlkiot(bTaulu, z);
        return z;
    }

    private static void poistaAlkiot(int[] bTaulu, IntJoukko z) {
        for (int i = 0; i < bTaulu.length; i++) {
            z.poista(i);
        }
    }
}