package sample.Roslinki;
import sample.wirtualnyswiat.Swiat;
import sample.wirtualnyswiat.Organizm;
import sample.wirtualnyswiat.Rosliny;

final public class Barszcz extends Rosliny
{
    public Barszcz(Swiat refswiat, int x, int y)
    {
        super(refswiat, x, y);
        this.sila = 10;
        this.symbol = "Barszcz";
        this.prawdopodobienstwo = 5;
    }


    @Override protected boolean specjalna_akcja()
    {
        refswiat.Informuj(this.Pokaz_symbol() + " czysci okolice");
        refswiat.Wyczysc_okolice(1,this.lokalizacja.getKey(),this.lokalizacja.getValue());
        return true;
    }

    @Override public boolean specjalna_kolizja(Organizm inny)
    {
        refswiat.Informuj(this.Pokaz_symbol() + " otrul " + inny.Pokaz_symbol());
        refswiat.Usun_organizm(inny);
        refswiat.Usun_organizm(this);
        return true;
    }
}