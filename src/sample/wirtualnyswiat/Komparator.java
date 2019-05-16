package sample.wirtualnyswiat;

import java.util.Comparator;


public class Komparator implements Comparator<Organizm>
{
    @Override public int compare(Organizm a, Organizm b)
    {
        if (a.Pokaz_inicjatywe() == b.Pokaz_inicjatywe())
        {
            if(a.Pokaz_wiek() > b.Pokaz_wiek())return -1;
            else if(a.Pokaz_wiek() < b.Pokaz_wiek())return 1;
            else return 0;
        }
        else if(a.Pokaz_inicjatywe() > b.Pokaz_inicjatywe())return -1;
        else return 1;
    }
}