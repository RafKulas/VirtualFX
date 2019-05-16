package sample.Zwierzaki;

import javafx.util.Pair;
import sample.wirtualnyswiat.Organizm;
import sample.wirtualnyswiat.Swiat;
import sample.wirtualnyswiat.Zwierzeta;

import java.util.Random;

final public class Czlowiek extends Zwierzeta
{
    public Czlowiek(Swiat refswiat,int x,int y)
    {
        super(refswiat,x,y);
        this.inicjatywa = 4;
        this.sila = 5;
        this.symbol = "Czlowiek";
        this.umiejetnosc = false;
        this.wiek = 0;
        this.ladowanie_umiejetnosci = 0;
    }

    @Override protected boolean specjalna_akcja()
    {
        this.sterowanie();
        return true;
    }

    @Override protected boolean mozliwosc_wykonania_ruchu() {
        if(umiejetnosc) {
            boolean czyMoznaSieRuszyc = false;
            for(int i =-1; i<2; ++i) {
                for(int j = -1; j<2; ++j) {
                    if(!(i==0 && j==0) && refswiat.Puste_miejsce(new Pair<>(lokalizacja.getKey()+i, lokalizacja.getValue()+j))) {
                        czyMoznaSieRuszyc = true;
                    }
                }
            }
            return czyMoznaSieRuszyc;
        }
        return true;
    }

    @Override public void rozmnazanie(int x, int y)
    {

    }

    @Override public boolean specjalna_kolizja(Organizm inny)
    {
        if (umiejetnosc && inny.Pokaz_sile()>=sila && mozliwosc_wykonania_ruchu())
        {
            int przesuniecieX = 0;
            int przesuniecieY = 0;
            Random rand = new Random();
            while((przesuniecieX == 0 && przesuniecieY == 0) || (refswiat.Poza_tablice(new Pair<>(this.lokalizacja.getKey() + przesunX, this.lokalizacja.getValue() + przesunY ))) || (!refswiat.Wygrany_ruch(new Pair<>(this.lokalizacja.getKey() + przesunX, this.lokalizacja.getValue() + przesunY ), this.sila))) {
                        przesuniecieX = rand.nextInt(3)-1;
                        przesuniecieY = rand.nextInt(3)-1;
            }
            refswiat.Informuj("Człowiek odbija sie od " + inny.Pokaz_symbol());
            refswiat.Wykonaj_ruch(this.lokalizacja.getKey(), this.lokalizacja.getValue(), this.lokalizacja.getKey() + przesuniecieX, this.lokalizacja.getValue() + przesuniecieY);
            return true;
        }
        return false;
    }

    private void sterowanie()
    {
        przesunX = refswiat.Kierunek().getKey();
        przesunY = refswiat.Kierunek().getValue();
        if (ladowanie_umiejetnosci < 7)umiejetnosc = false;
        if (ladowanie_umiejetnosci > 0&&(przesunX!=0||przesunY!=0))ladowanie_umiejetnosci--;
        if(!refswiat.Poza_tablice(new Pair<>(this.lokalizacja.getKey() + przesunX, this.lokalizacja.getValue() + przesunY))&&(przesunX!=0||przesunY!=0))
            refswiat.Wykonaj_ruch(this.lokalizacja.getKey(), this.lokalizacja.getValue(), this.lokalizacja.getKey() + przesunX, this.lokalizacja.getValue() + przesunY);
        else if(przesunX==0&&przesunY==0)
        {
            if (!umiejetnosc && ladowanie_umiejetnosci == 0)
            {
                umiejetnosc = true;
                ladowanie_umiejetnosci = 10;
                refswiat.Informuj("Aktywowano Umiejetnosc");
            }
            else
                refswiat.Informuj("Niepodzenie Aktywacji");
        }
    }

    public int Pokaz_aktywnosc_umiejetnosci()
    {
        if(umiejetnosc)
            return 1;
        return 0;
    }

    public int Pokaz_ladowanie_umiejetnosci()
    {
        return this.ladowanie_umiejetnosci;
    }

    public void Ustaw_aktywnosc_umiejetnosci(int umiejetnosc)
    {
        this.umiejetnosc = (umiejetnosc==1);
    }

    public void Ustaw_ladowanie_umiejetnosci(int ladowanie)
    {
        this.ladowanie_umiejetnosci = ladowanie;
    }

    private boolean umiejetnosc;
    private int ladowanie_umiejetnosci;
    private int przesunY;
    private int przesunX;
}
