package sample.Roslinki;
import sample.wirtualnyswiat.Swiat;
import sample.wirtualnyswiat.Organizm;
import sample.wirtualnyswiat.Rosliny;

final public class Trawa extends Rosliny
{
    public Trawa(Swiat refswiat, int x, int y)
    {
        super(refswiat, x, y);
        this.sila = 0;
        this.symbol = "Trawa";
        this.prawdopodobienstwo = 4;
    }

    @Override protected boolean specjalna_akcja()
    {
        return false;
    }

    @Override public boolean specjalna_kolizja(Organizm inny)
    {
        return false;
    }
}