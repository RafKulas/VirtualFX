package sample.Zwierzaki;
import sample.wirtualnyswiat.Swiat;
import sample.wirtualnyswiat.Organizm;
import sample.wirtualnyswiat.Zwierzeta;

final public class Owca extends Zwierzeta
{
    public Owca(Swiat refswiat, int x, int y)
    {
        super(refswiat,x,y);
        this.sila = 4;
        this.inicjatywa = 4;
        this.symbol = "Owca";
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