package sample.Zwierzaki;
import sample.wirtualnyswiat.Swiat;
import sample.wirtualnyswiat.Organizm;
import sample.wirtualnyswiat.Zwierzeta;
import java.util.Random;
import javafx.util.Pair;


final public class Antylopa extends Zwierzeta
{
    public Antylopa(Swiat refswiat, int x, int y)
    {
        super(refswiat,x,y);
        this.sila = 4;
        this.inicjatywa = 4;
        this.symbol = "Antylopa";
    }

    @Override protected boolean specjalna_akcja()
    {
        int przesunX = 0, przesunY = 0;
        Random generator = new Random();
        while ((przesunX == 0 && przesunY == 0) || (refswiat.Poza_tablice(new Pair<>(this.lokalizacja.getKey() + przesunX, this.lokalizacja.getValue() + przesunY ))))
        {
            przesunX = generator.nextInt(5)-2;
            przesunY = generator.nextInt(5)-2;
        }
        refswiat.Wykonaj_ruch(this.lokalizacja.getKey(), this.lokalizacja.getValue(), this.lokalizacja.getKey() + przesunX, this.lokalizacja.getValue() + przesunY);
        return true;
    }

    @Override public boolean specjalna_kolizja(Organizm inny)
    {
        Random generator = new Random();
        if (this.mozliwosc_wykonania_ruchu())
        {
            int przesunX = 0, przesunY = 0;
            while ((przesunX == 0 && przesunY == 0) || (!refswiat.Puste_miejsce(new Pair<>(this.lokalizacja.getKey() + przesunX, this.lokalizacja.getValue() + przesunY ))))
            {
                przesunX = generator.nextInt(5)-2;
                przesunY = generator.nextInt(5)-2;
            }
            refswiat.Wykonaj_ruch(this.lokalizacja.getKey(), this.lokalizacja.getValue(), this.lokalizacja.getKey() + przesunX, this.lokalizacja.getValue() + przesunY);
            refswiat.Informuj(this.Pokaz_symbol() + " ucieka przed " + inny.Pokaz_symbol());
            return true;
        }
        return false;
    }

    @Override protected boolean mozliwosc_wykonania_ruchu()
    {
        Random generator = new Random();
        int szansa = generator.nextInt(2);
        boolean czy_moze_sie_ruszyc = false;
        for (int i = -1; i < 2; i++)
            for (int j = -1; j < 2; j++)
                if ((i != 0 || j != 0) && refswiat.Puste_miejsce(new Pair<>(this.lokalizacja.getKey() + i, this.lokalizacja.getValue() + j )))
                    czy_moze_sie_ruszyc = true;

        return (szansa == 0 && czy_moze_sie_ruszyc);
    }
}