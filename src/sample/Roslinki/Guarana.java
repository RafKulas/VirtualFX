package sample.Roslinki;
import sample.wirtualnyswiat.Swiat;
import sample.wirtualnyswiat.Organizm;
import sample.wirtualnyswiat.Rosliny;

final public class Guarana extends Rosliny
{
    public Guarana(Swiat refswiat, int x, int y)
    {
        super(refswiat, x, y);
        this.sila = 0;
        this.symbol = "Guarana";
        this.prawdopodobienstwo = 4;
    }

    @Override protected boolean specjalna_akcja()
    {
        return false;
    }

    @Override public boolean specjalna_kolizja(Organizm inny)
    {
        refswiat.Informuj(this.Pokaz_symbol()+ " wzmocnil "+ inny.Pokaz_symbol());
        inny.Ustaw_sile(inny.Pokaz_sile() + 3);
        return false;
    }
}
