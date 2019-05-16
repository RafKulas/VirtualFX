package sample.Roslinki;
import sample.wirtualnyswiat.Swiat;
import sample.wirtualnyswiat.Organizm;
import sample.wirtualnyswiat.Rosliny;

final public class Jagody extends Rosliny
{
    public Jagody(Swiat refswiat, int x, int y)
    {
        super(refswiat,x,y);
        this.sila = 99;
        this.symbol = "Jagody";
        this.prawdopodobienstwo = 5;
    }

    @Override protected boolean specjalna_akcja()
    {
        return false;
    }

    @Override public boolean specjalna_kolizja(Organizm inny)
    {
        refswiat.Informuj(this.Pokaz_symbol()+ " otrul " +inny.Pokaz_symbol());
        refswiat.Usun_organizm(inny);
        refswiat.Usun_organizm(this);
        return true;
    }
}