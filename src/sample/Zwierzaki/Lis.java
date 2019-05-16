package sample.Zwierzaki;
import sample.wirtualnyswiat.Swiat;
import sample.wirtualnyswiat.Organizm;
import sample.wirtualnyswiat.Zwierzeta;
import java.util.Random;
import javafx.util.Pair;

final public class Lis extends Zwierzeta
{
    public Lis(Swiat refswiat, int x, int y)
    {
        super(refswiat,x,y);
        this.sila = 3;
        this.inicjatywa = 7;
        this.symbol = "Lis";
    }

    @Override protected boolean specjalna_akcja()
    {
        Random generator = new Random();
        if(this.mozliwosc_wykonania_ruchu())
        {
            int przesunX = 0, przesunY = 0;
            while ((przesunX == 0 && przesunY == 0) || (refswiat.Poza_tablice(new Pair<>(this.lokalizacja.getKey() + przesunX, this.lokalizacja.getValue() + przesunY ))) || (!refswiat.Wygrany_ruch(new Pair<>(this.lokalizacja.getKey() + przesunX, this.lokalizacja.getValue() + przesunY ), this.sila)))
            {
                przesunX = generator.nextInt(3)-1;
                przesunY = generator.nextInt(3)-1;
            }
            refswiat.Wykonaj_ruch(this.lokalizacja.getKey(), this.lokalizacja.getValue(), this.lokalizacja.getKey() + przesunX, this.lokalizacja.getValue() + przesunY);
        }
        return true;
    }

    @Override public boolean specjalna_kolizja(Organizm inny)
    {
        return false ;
    }

    @Override protected boolean mozliwosc_wykonania_ruchu()
    {
        for (int i = -1; i < 2; i++)
            for (int j = -1; j < 2; j++)
                if ((i != 0 || j != 0) && refswiat.Wygrany_ruch(new Pair<>(this.lokalizacja.getKey() + i, this.lokalizacja.getValue() + j ), this.sila))
                    return true;
        return false;
    }
}