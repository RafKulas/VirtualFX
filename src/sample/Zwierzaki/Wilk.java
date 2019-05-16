package sample.Zwierzaki;
import sample.wirtualnyswiat.Swiat;
import sample.wirtualnyswiat.Organizm;
import sample.wirtualnyswiat.Zwierzeta;

final public class Wilk extends Zwierzeta
{
    public Wilk(Swiat refswiat, int x, int y)
    {
        super(refswiat,x,y);
        this.sila = 9;
        this.inicjatywa = 5;
        this.symbol = "Wilk";
    }

    @Override protected boolean specjalna_akcja()
    {
        return false;
    }

    @Override public boolean specjalna_kolizja(Organizm inny)
    {
        return false;
    }

    @Override protected boolean mozliwosc_wykonania_ruchu()
    {
        return true;
    }
}