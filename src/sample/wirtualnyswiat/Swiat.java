package sample.wirtualnyswiat;

import sample.Zwierzaki.Antylopa;
import sample.Roslinki.Barszcz;
import sample.Roslinki.Guarana;
import sample.Roslinki.Jagody;
import sample.Zwierzaki.Lis;
import sample.Roslinki.Mlecz;
import sample.Zwierzaki.Owca;
import sample.Roslinki.Trawa;
import sample.Zwierzaki.Wilk;
import sample.Zwierzaki.Zolw;
import sample.Zwierzaki.Czlowiek;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Random;
import java.util.Scanner;
import java.util.Vector;
import javafx.util.Pair;

final public class Swiat
{
    public Swiat(Pair<Integer,Integer> rozmiar)
    {
        this.tura = 0;
        this.rozmiar_swiata=rozmiar;
        Organizmy = new Organizm[this.rozmiar_swiata.getValue()][this.rozmiar_swiata.getKey()];
        for (int i = 0; i < this.rozmiar_swiata.getKey(); i++)
            for (int j = 0; j < this.rozmiar_swiata.getValue(); j++) {
                Organizmy[j][i] = null;
            }
        kolejka_ruchu=new Vector<>();
        komunikat = new Vector<>();
        Inicjalizuj_nowy_swiat();
    }

    public void Start(int tryb,String nazwa_pliku)
    {
        switch (tryb)
        {
            case 0:
            {
                this.Inicjalizuj_nowy_swiat();
                break;
            }
            case 1:
            {
                try {this.Wczytaj_swiat();} catch (FileNotFoundException ex)
                {
                    this.komunikat.add("Nie udało się wczytać pliku");
                }
                break;
            }
        }
        this.Pokaz_swiat();
    }

    public Pair<Integer,Integer> Kierunek()
    {
        return new Pair<>(przesunX,przesunY);
    }

    public boolean Poza_tablice(Pair<Integer,Integer>wspolrzedne) //prawda gdy wyjdzie poza tablice
    {
        return  (wspolrzedne.getKey() < 0 || wspolrzedne.getValue() < 0 || wspolrzedne.getKey() >= rozmiar_swiata.getKey() || wspolrzedne.getValue() >= rozmiar_swiata.getValue());
    }

    public boolean Wygrany_ruch(Pair<Integer,Integer>wspolrzedne,int sila) //prawda gdy ruch jest wygrany
    {
        return ((!Poza_tablice(wspolrzedne) && (Organizmy[wspolrzedne.getValue()][wspolrzedne.getKey()] == null || (Organizmy[wspolrzedne.getValue()][wspolrzedne.getKey()]!=null && Organizmy[wspolrzedne.getValue()][wspolrzedne.getKey()].Pokaz_sile() <= sila))));
    }

    public boolean Puste_miejsce(Pair<Integer,Integer>wspolrzedne)//prawda gdy wskazane miejsce jest puste
    {
        return  (!Poza_tablice(wspolrzedne) && Organizmy[wspolrzedne.getValue()][wspolrzedne.getKey()] == null);
    }

    public void Dodaj_organizm(int x_gen,int y_gen,char typ_gen)
    {
        switch (typ_gen)
        {
            case 'C':
                Organizmy[y_gen][x_gen] = new Czlowiek(this, x_gen, y_gen);
                break;
            case 'W':
                Organizmy[y_gen][x_gen] = new Wilk(this, x_gen, y_gen);
                break;
            case 'O':
                Organizmy[y_gen][x_gen] = new Owca(this, x_gen, y_gen);
                break;
            case 'L':
                Organizmy[y_gen][x_gen] = new Lis(this, x_gen, y_gen);
                break;
            case 'Z':
                Organizmy[y_gen][x_gen] = new Zolw(this, x_gen, y_gen);
                break;
            case 'A':
                Organizmy[y_gen][x_gen] = new Antylopa(this, x_gen, y_gen);
                break;
            case 'T':
                Organizmy[y_gen][x_gen] = new Trawa(this, x_gen, y_gen);
                break;
            case 'M':
                Organizmy[y_gen][x_gen] = new Mlecz(this, x_gen, y_gen);
                break;
            case 'G':
                Organizmy[y_gen][x_gen] = new Guarana(this, x_gen, y_gen);
                break;
            case 'J':
                Organizmy[y_gen][x_gen] = new Jagody(this, x_gen, y_gen);
                break;
            case 'B':
                Organizmy[y_gen][x_gen] = new Barszcz(this, x_gen, y_gen);
                break;
        }
        kolejka_ruchu.add(Organizmy[y_gen][x_gen]);
        komunikat.add("Urodził się "+ Organizmy[y_gen][x_gen].Pokaz_symbol());
    }

    public void Usun_organizm(Organizm organizm)
    {
        for (int i = 0; i < kolejka_ruchu.size(); i++)
            if (organizm == kolejka_ruchu.elementAt(i))
            {
                kolejka_ruchu.remove(kolejka_ruchu.elementAt(i)); //usuwanie zjedzonego organizmu z kolejki
                if(rozmiar_ruchu_inicjatywy>i) //jesli zjedzone zostalo dziecko narodzone w tej turze to rozmiar vectora sie nie zmienia
                    rozmiar_ruchu_inicjatywy--;
                if (iterator_organizmow >= i) //jesli zjedzony organizm jest wczesniej w kolejce to vector sie spisuje do tylu wiec i wskaznik trzeba przesunac
                    iterator_organizmow--;
                break;
            }
        Organizmy[organizm.Pokaz_lokalizacje().getValue()][organizm.Pokaz_lokalizacje().getKey()] = null;
    }

    public void Wyczysc_okolice(int tryb ,int x,int y)
    {
        boolean czy_mam_usunac = false;
        for(int i=-1;i<2;i++)
            for(int j=-1;j<2;j++)
                if ((i != 0 || j != 0) && (!Poza_tablice(new Pair<>(x + i, y + j ))) && (Organizmy[y + j][x + i] != null))
                {
                    if(tryb == 0) czy_mam_usunac = true;
                    else
                    if(tryb == 1)
                        if(Organizmy[y + j][x + i] instanceof Zwierzeta) czy_mam_usunac = true;

                    if(czy_mam_usunac)
                    {
                        this.Usun_organizm(Organizmy[y + j][x + i]);
                        Organizmy[y + j][x + i] = null;
                    }
                }
    }
    public void printWorld() {
        for (int i = 0; i < this.rozmiar_swiata.getKey(); i++) {
            for (int j = 0; j < this.rozmiar_swiata.getValue(); j++) {
                if (Organizmy[j][i] != null) {
                    System.out.print(Organizmy[j][i].Pokaz_symbol() + "\t");
                } else {
                    System.out.print("nico\t");
                }
            }
            System.out.println(" \n");
        }
        System.out.println("-----------------------------");
    }


    public void Wykonaj_ruch(int x_start, int y_start, int x_cel, int y_cel)
    {
        if (Organizmy[y_cel][x_cel] == null)
        {
            Organizmy[y_cel][x_cel] = Organizmy[y_start][x_start];
            Organizmy[y_start][x_start] = null;
            Organizmy[y_cel][x_cel].Ustaw_lokalizacje(new Pair<>(x_cel,y_cel));
        }
        else
        {
            if(Organizmy[y_cel][x_cel].Pokaz_symbol().equals(Organizmy[y_start][x_start].Pokaz_symbol()))
            {
                Organizmy[y_start][x_start].rozmnazanie(x_cel,y_cel);
            }
            else
            {
                if (Organizmy[y_start][x_start].specjalna_kolizja(Organizmy[y_cel][x_cel]))return;
                if (Organizmy[y_cel][x_cel].specjalna_kolizja(Organizmy[y_start][x_start]))return;

                if (Organizmy[y_start][x_start].Pokaz_sile() >= Organizmy[y_cel][x_cel].Pokaz_sile())
                {
                    String kom = Organizmy[y_start][x_start].Pokaz_symbol() + " zabija " + Organizmy[y_cel][x_cel].Pokaz_symbol();
                    komunikat.add(kom);
                    this.Usun_organizm(Organizmy[y_cel][x_cel]);
                    Organizmy[y_cel][x_cel] = Organizmy[y_start][x_start];
                    Organizmy[y_start][x_start] = null;
                    Organizmy[y_cel][x_cel].Ustaw_lokalizacje(new Pair<>(x_cel,y_cel));
                }
                else
                {
                    String kom = Organizmy[y_cel][x_cel].Pokaz_symbol() + " zabija " + Organizmy[y_start][x_start].Pokaz_symbol();
                    komunikat.add(kom);
                    this.Usun_organizm(Organizmy[y_start][x_start]);
                }
            }
        }
    }

    public void Wykonaj_ture(int x,int y)
    {
        przesunX=x;
        przesunY=y;
        if(przesunX!=0||przesunY!=0)
        {
            Komparator compare=new Komparator();
            kolejka_ruchu.sort(compare);
            this.tura=this.tura+1;
            rozmiar_ruchu_inicjatywy = kolejka_ruchu.size(); //pomocniczo zmienny rozmiar vectora, zeby w danej turze nie bralo dzieci ktore dopiero doszly i zeby nie wykroczylo za zakres jak sie zjedza
            for (iterator_organizmow = 0; iterator_organizmow < rozmiar_ruchu_inicjatywy; iterator_organizmow++)
                this.kolejka_ruchu.elementAt(iterator_organizmow).akcja();
            for (int j = 0; j<rozmiar_ruchu_inicjatywy; j++)
                this.kolejka_ruchu.elementAt(j).Postarz_organizm();
        }
        else
        {
            for (iterator_organizmow = 0; iterator_organizmow < rozmiar_ruchu_inicjatywy; iterator_organizmow++)
                if(this.kolejka_ruchu.elementAt(iterator_organizmow) instanceof Czlowiek)
                    this.kolejka_ruchu.elementAt(iterator_organizmow).akcja();
        }
        this.Pokaz_swiat();
    }

    private void Inicjalizuj_nowy_swiat()
    {
        Random generator = new Random();
        int startowa_liczba_obiektow = (generator.nextInt(15)+ 20)*this.rozmiar_swiata.getKey()*this.rozmiar_swiata.getValue() / 100; //20-34% calej powierzchni
        int x_gen, y_gen;
        char typ_gen = 0;
        this.Dodaj_organizm(0, 0, 'C');
        String symbole = "WOLZATMGJB";
        for (int i = 0; i < startowa_liczba_obiektow; i++)
        {
            x_gen = 0; y_gen = 0;
            while ((x_gen == 0 && y_gen == 0) || (!this.Puste_miejsce(new Pair<>(x_gen, y_gen ))))
            {
                x_gen = generator.nextInt(this.rozmiar_swiata.getKey());
                y_gen = generator.nextInt(this.rozmiar_swiata.getValue());
                typ_gen = symbole.charAt(generator.nextInt(10));
            }
            this.Dodaj_organizm(x_gen, y_gen, typ_gen);
        }
    }

    public void Pokaz_swiat()
    {
//        gra.Wyczysc_swiat();
//        for(int i=0;i<rozmiar_swiata.getValue();i++)
//            for(int j=0;j<rozmiar_swiata.getKey();j++)
//                if(Organizmy[i][j]!=null)
//                    gra.Wstaw_napis(Organizmy[i][j].Pokaz_symbol(),j,i);
//        for(int i=0;i<10;i++)
//            gra.Pokaz_powiadomienia(k
//            omunikat.Wypisz_powiadomienie(), i);
//        komunikat.Wyczysc_komunikaty();
    }

    public void Wczytaj_swiat()throws FileNotFoundException
    {
        int liczba_organizmow,p,r,inicjatywa,sila,wiek,ladowanie,umiejetnosc;
        String symbol;
        File file=new File("Save.txt");
        Scanner wejscie = new Scanner(file);
        komunikat.clear();
        kolejka_ruchu.clear();
        int x=wejscie.nextInt();
        int y=wejscie.nextInt();
        rozmiar_swiata=new Pair<>(x,y);
        Organizmy = new Organizm[rozmiar_swiata.getValue()][rozmiar_swiata.getKey()];
        for (int i = 0; i < this.rozmiar_swiata.getValue(); i++)
            for (int j = 0; j < this.rozmiar_swiata.getKey(); j++) {
                Organizmy[i][j] = null;
            }
        this.tura=wejscie.nextInt();
        liczba_organizmow=wejscie.nextInt();
        for(int i=0;i<liczba_organizmow;i++)
        {
            p=wejscie.nextInt();
            r=wejscie.nextInt();
            inicjatywa=wejscie.nextInt();
            sila=wejscie.nextInt();
            symbol=wejscie.next();
            wiek=wejscie.nextInt();
            this.Dodaj_organizm(p,r,symbol.charAt(0));
            Organizmy[r][p].Ustaw_inicjatywe(inicjatywa);
            Organizmy[r][p].Ustaw_lokalizacje(new Pair<>(p,r));
            Organizmy[r][p].Ustaw_sile(sila);
            Organizmy[r][p].Ustaw_symbol(symbol);
            Organizmy[r][p].Ustaw_wiek(wiek);
            if ("Czlowiek".equals(symbol))
            {
                Czlowiek czlowiek=(Czlowiek)Organizmy[r][p];
                ladowanie=wejscie.nextInt();
                umiejetnosc=wejscie.nextInt();
                czlowiek.Ustaw_ladowanie_umiejetnosci(ladowanie);
                czlowiek.Ustaw_aktywnosc_umiejetnosci(umiejetnosc);
            }
        }
        wejscie.close();
        this.Pokaz_swiat();
    }

    public void Zapisz_swiat()throws FileNotFoundException
    {
        PrintWriter zapis = new PrintWriter("Save.txt");
        zapis.println(rozmiar_swiata.getKey());
        zapis.println(rozmiar_swiata.getValue());
        zapis.println(this.tura);
        int liczba_organizmow=0;
        for(int i=0;i<rozmiar_swiata.getValue();i++)
            for(int j=0;j<rozmiar_swiata.getKey();j++)
                if (Organizmy[i][j] != null)
                    liczba_organizmow++;
        zapis.println(liczba_organizmow);
        for(int i=0;i<rozmiar_swiata.getValue();i++)
            for(int j=0;j<rozmiar_swiata.getKey();j++)
                if (Organizmy[i][j] != null)
                {
                    zapis.println(j);
                    zapis.println(i);
                    zapis.println(Organizmy[i][j].Pokaz_inicjatywe());
                    zapis.println(Organizmy[i][j].Pokaz_sile());
                    zapis.println(Organizmy[i][j].Pokaz_symbol());
                    zapis.println(Organizmy[i][j].Pokaz_wiek());
                    if ("Czlowiek".equals(Organizmy[i][j].Pokaz_symbol()))
                    {
                        Czlowiek czlowiek=(Czlowiek)Organizmy[i][j];
                        zapis.println(czlowiek.Pokaz_ladowanie_umiejetnosci());
                        zapis.println(czlowiek.Pokaz_aktywnosc_umiejetnosci());
                    }
                }
        zapis.close();
    }

    public void Informuj(String co) {
        komunikat.add(co);
    }

    public Pair<Integer, Integer> getRozmiar_swiata() {
        return rozmiar_swiata;
    }

    private int tura;
    private Vector<String> komunikat;
    public Organizm[][] Organizmy;
    private Pair<Integer,Integer> rozmiar_swiata;
    private Vector<Organizm>kolejka_ruchu;
    private int rozmiar_ruchu_inicjatywy;
    private int iterator_organizmow;
    private int przesunX;
    private int przesunY;
}