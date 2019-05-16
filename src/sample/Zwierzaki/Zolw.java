package sample.Zwierzaki;
import sample.wirtualnyswiat.Swiat;
import sample.wirtualnyswiat.Organizm;
import sample.wirtualnyswiat.Zwierzeta;
import java.util.Random;

final public class Zolw extends Zwierzeta
{
    public Zolw(Swiat refswiat, int x, int y)
    {
        super(refswiat,x,y);
        this.sila = 2;
        this.inicjatywa = 1;
        this.symbol = "Zolw";
    }

    @Override protected boolean specjalna_akcja()
    {
        return !(this.mozliwosc_wykonania_ruchu());
    }

    @Override public boolean specjalna_kolizja(Organizm inny)
    {
        if (inny.Pokaz_sile() < 5 && inny.Pokaz_sile() > 0)
        {
            refswiat.Informuj(this.Pokaz_symbol() + " odparl " + inny.Pokaz_symbol());
            return true;
        }
        return false;
    }

    @Override protected boolean mozliwosc_wykonania_ruchu()
    {
        Random generator = new Random();
        int procenty = generator.nextInt(100);
        return (procenty > 20 && procenty <= 45);
    }
}