package sample.Roslinki;
import sample.wirtualnyswiat.Swiat;
import sample.wirtualnyswiat.Organizm;
import sample.wirtualnyswiat.Rosliny;

final public class Mlecz extends Rosliny
{
    public Mlecz(Swiat refswiat, int x, int y)
    {
        super(refswiat, x, y);
        this.sila = 0;
        this.symbol = "Mlecz";
        this.prawdopodobienstwo = 3;
    }

    @Override protected boolean specjalna_akcja()
    {
        for(int i=0;i<3;i++)
            this.rozmnazanie(this.lokalizacja.getKey(), this.lokalizacja.getValue());
        return true;
    }

    @Override public boolean specjalna_kolizja(Organizm inny)
    {
        return false;
    }

}