package sample.wirtualnyswiat;

import java.util.Random;
import javafx.util.Pair;

public abstract class Rosliny extends Organizm
{
    public Rosliny(Swiat refswiat,int x,int y)
    {
        super(refswiat,x,y);
        this.inicjatywa=0;
    }

    @Override public void akcja()
    {
        if (!specjalna_akcja())rozmnazanie(this.lokalizacja.getKey(),this.lokalizacja.getValue());
    }

    @Override public void rozmnazanie(int x1, int y1)
    {
        Random generator=new Random();
        if (this.mozliwosc_rozmnazania())
        {
            int przesunX = 0, przesunY = 0;
            while ((przesunX == 0 && przesunY == 0) || (!refswiat.Puste_miejsce(new Pair<>(this.lokalizacja.getKey() + przesunX, this.lokalizacja.getValue() + przesunY ))))
            {
                przesunX = generator.nextInt(3) - 1;
                przesunY = generator.nextInt(3) - 1;
            }
            refswiat.Dodaj_organizm(this.lokalizacja.getKey() + przesunX, this.lokalizacja.getValue() + przesunY, this.symbol.charAt(0));
        }
    }

    protected boolean mozliwosc_rozmnazania()
    {
        Random generator=new Random();
        int szansa = generator.nextInt(100);
        boolean czy_jest_miejsce_na_nasiona = false;
        for (int i = -1; i < 2; i++)
            for (int j = -1; j < 2; j++)
                if ((i!=0 || j!=0) && refswiat.Puste_miejsce(new Pair<>(this.lokalizacja.getKey() + i, this.lokalizacja.getValue() + j )))
                    czy_jest_miejsce_na_nasiona = true;

        return (szansa <this.prawdopodobienstwo && czy_jest_miejsce_na_nasiona);
    }

    protected int prawdopodobienstwo;
}