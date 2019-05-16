package sample.wirtualnyswiat;
import javafx.util.Pair;

public abstract class Organizm
{
    public Organizm(Swiat refswiat ,int x, int y)
    {
        this.refswiat=refswiat;
        lokalizacja=new Pair<>(x,y);
        this.wiek = 0;
    }

    public void Postarz_organizm()
    {
        this.wiek++;
    }

    public int Pokaz_wiek()
    {
        return this.wiek;
    }

    public Pair<Integer, Integer> Pokaz_lokalizacje()
    {
        return this.lokalizacja;
    }

    public String Pokaz_symbol()
    {
        return this.symbol;
    }

    public int Pokaz_sile()
    {
        return this.sila;
    }

    public int Pokaz_inicjatywe()
    {
        return this.inicjatywa;
    }

    public void Ustaw_symbol(String symbol)
    {
        this.symbol=symbol;
    }

    public void Ustaw_lokalizacje(Pair<Integer,Integer>lokalizacja)
    {
        this.lokalizacja=lokalizacja;
    }

    public void Ustaw_sile(int sila)
    {
        this.sila=sila;
    }

    public void Ustaw_inicjatywe(int inicjatywa)
    {
        this.inicjatywa=inicjatywa;
    }

    public void Ustaw_wiek(int wiek)
    {
        this.wiek=wiek;
    }

    public abstract void rozmnazanie(int x1, int y1);
    public abstract void akcja();

    public boolean specjalna_kolizja(Organizm organizm)
    {
        return false;
    }

    protected boolean specjalna_akcja()
    {
        return false;
    }

    protected int sila;
    protected int inicjatywa;
    protected Pair<Integer,Integer> lokalizacja;
    protected Swiat refswiat;
    protected int wiek;
    protected String symbol;
}