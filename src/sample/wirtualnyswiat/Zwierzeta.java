package sample.wirtualnyswiat;

import java.util.Random;
import javafx.util.Pair;

public abstract class Zwierzeta extends Organizm
{
    public Zwierzeta(Swiat refswiat,int x,int y)
    {
        super(refswiat,x,y);
    }

    @Override public void akcja()
    {
        if(specjalna_akcja())return;
        else this.ruch();
    }
    @Override public void rozmnazanie(int x1, int y1)
    {
        Random generator = new Random();
        int x, y;
        boolean czy_jest_miejsce_na_dziecko_przy_tacie = false;
        boolean czy_jest_miejsce_na_dziecko_przy_mamie = false;

        for(int i=-1;i<2;i++)
            for(int j=-1;j<2;j++)
            {
                if ((i != 0 || j != 0) && refswiat.Puste_miejsce(new Pair<>(x1+i,y1+j)))
                    czy_jest_miejsce_na_dziecko_przy_mamie = true;
                if ((i != 0 || j != 0) && refswiat.Puste_miejsce(new Pair<>(this.lokalizacja.getKey() + i,this.lokalizacja.getValue() + j )))
                    czy_jest_miejsce_na_dziecko_przy_tacie = true;
            }

        if (czy_jest_miejsce_na_dziecko_przy_mamie||czy_jest_miejsce_na_dziecko_przy_tacie)
        {
            int przesunX = 0, przesunY = 0;
            if(czy_jest_miejsce_na_dziecko_przy_mamie)
            {x = x1; y = y1;}
            else
            {x = this.lokalizacja.getKey(); y = this.lokalizacja.getValue();}

            while ((przesunX == 0 && przesunY == 0) || (!refswiat.Puste_miejsce(new Pair<>(x + przesunX, y + przesunY ))))
            {
                przesunX = generator.nextInt(3)-1;
                przesunY = generator.nextInt(3)-1;
            }
            refswiat.Dodaj_organizm(x+przesunX,y+przesunY,this.symbol.charAt(0));
        }

    }

    protected void ruch()
    {
        Random generator = new Random();
        int przesunX=0, przesunY=0;
        while ((przesunX == 0 && przesunY == 0) || (refswiat.Poza_tablice(new Pair<>(this.lokalizacja.getKey() + przesunX, this.lokalizacja.getValue() + przesunY ))))
        {
            przesunX = generator.nextInt(3)-1;
            przesunY = generator.nextInt(3)-1;
        }
        refswiat.Wykonaj_ruch(this.lokalizacja.getKey(),this.lokalizacja.getValue(),this.lokalizacja.getKey() + przesunX, this.lokalizacja.getValue() + przesunY);
    }

    protected abstract boolean mozliwosc_wykonania_ruchu();
}