package sample;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Pair;
import sample.wirtualnyswiat.Swiat;


public class Plansza {
    private int wysokosc, szerokosc;
    private Swiat wirtualny = new Swiat(new Pair<>(2, 2));

    public void click(ActionEvent event) {
        wirtualny.Wykonaj_ture(1, 0);
        wirtualny.printWorld();
        refresh();
    }

    public void handleKeyPressed(KeyEvent event) {
        System.out.println(event.getCode());
//        plansza.getScene().setOnKeyTyped(new EventHandler<KeyEvent>() {
//            @Override
//            public void handle(KeyEvent event) {
//                System.out.println(event.getCode());
                switch (event.getCode()) {
                    case UP:    wirtualny.Wykonaj_ture(0, -1);refresh(); break;
                    case DOWN:  wirtualny.Wykonaj_ture(0, 1);refresh(); break;
                    case LEFT:  wirtualny.Wykonaj_ture(-1, 0);refresh(); break;
                    case RIGHT: wirtualny.Wykonaj_ture(1, 0);refresh(); break;
                }
//            }
//        });
    }

    protected boolean loadWorld() {
        try {
            loadGraphic();
            wirtualny.Wczytaj_swiat();
            wysokosc=wirtualny.getRozmiar_swiata().getValue();
            szerokosc=wirtualny.getRozmiar_swiata().getKey();
            for(int i=0; i<wysokosc; ++i) {
                HBox hBox = new HBox();
                hBox.setId("hBox" + (i));
                hBox.setSpacing(10);
                for(int j=0; j<szerokosc; ++j) {
                    ImageView imgV;
                    if(wirtualny.Organizmy[i][j]==null) {
                        imgV = new ImageView(nullIMG);
                    }
                    else if(wirtualny.Organizmy[i][j].Pokaz_symbol().equals("Antylopa")) {
                        imgV = new ImageView(antylopa);
                    }
                    else if(wirtualny.Organizmy[i][j].Pokaz_symbol().equals("Trawa")) {
                        imgV = new ImageView(trawa);
                    }
                    else if(wirtualny.Organizmy[i][j].Pokaz_symbol().equals("Barszcz")) {
                        imgV = new ImageView(barszcz);
                    }
                    else if(wirtualny.Organizmy[i][j].Pokaz_symbol().equals("Guarana")) {
                        imgV = new ImageView(guarana);
                    }
                    else if(wirtualny.Organizmy[i][j].Pokaz_symbol().equals("Jagody")) {
                        imgV = new ImageView(jagody);
                    }
                    else if(wirtualny.Organizmy[i][j].Pokaz_symbol().equals("Mlecz")) {
                        imgV = new ImageView(mlecz);
                    }
                    else if(wirtualny.Organizmy[i][j].Pokaz_symbol().equals("Czlowiek")) {
                        imgV = new ImageView(czlowiek);
                    }
                    else if(wirtualny.Organizmy[i][j].Pokaz_symbol().equals("Lis")) {
                        imgV = new ImageView(lis);
                    }
                    else if(wirtualny.Organizmy[i][j].Pokaz_symbol().equals("Owca")) {
                        imgV = new ImageView(owca);
                    }
                    else if(wirtualny.Organizmy[i][j].Pokaz_symbol().equals("Wilk")) {
                        imgV = new ImageView(wilk);
                    }
                    else {
                        imgV = new ImageView(zolw);
                    }
                    double rozmiar = ((1000.0 / szerokosc) > (500.0 / wysokosc)) ? (500.0 / wysokosc) : (1000.0 / szerokosc);
                    imgV.setId(String.valueOf(j+i*szerokosc));
                    imgV.setFitWidth(rozmiar);
                    imgV.setPreserveRatio(true);
                    hBox.getChildren().add(imgV);
                }
                plansza.getChildren().add(hBox);
            }
            refresh();
            //listner();
            return true;

        }
        catch (Exception e) {

            Alert blad = new Alert(Alert.AlertType.ERROR);
            blad.setTitle("Zapis nie istnieje");
            blad.setContentText("Zagraj i chociażraz zapisz grę");
            blad.showAndWait();
            e.printStackTrace();
            return false;
        }
    }

    protected void setPlansza(int x, int y) {
        loadGraphic();
        szerokosc = x;
        wysokosc = y;
        wirtualny = new Swiat(new Pair<>(szerokosc, wysokosc));
        for(int i = 0; i<wysokosc; ++i) {
            HBox hBox = new HBox();
            hBox.setId("hBox" + (i));
            hBox.setSpacing(10);
//            if(i%2==1) {
//                hBox.setPadding(new Insets(0, 0, 0, 50)); //(top/right/bottom/left)
//            }
            for(int j = 0; j<szerokosc; ++j) {
                ImageView imgV;
                if(wirtualny.Organizmy[i][j]==null) {
                    imgV = new ImageView(nullIMG);
                }
                else if(wirtualny.Organizmy[i][j].Pokaz_symbol().equals("Antylopa")) {
                    imgV = new ImageView(antylopa);
                }
                else if(wirtualny.Organizmy[i][j].Pokaz_symbol().equals("Trawa")) {
                    imgV = new ImageView(trawa);
                }
                else if(wirtualny.Organizmy[i][j].Pokaz_symbol().equals("Barszcz")) {
                    imgV = new ImageView(barszcz);
                }
                else if(wirtualny.Organizmy[i][j].Pokaz_symbol().equals("Guarana")) {
                    imgV = new ImageView(guarana);
                }
                else if(wirtualny.Organizmy[i][j].Pokaz_symbol().equals("Jagody")) {
                    imgV = new ImageView(jagody);
                }
                else if(wirtualny.Organizmy[i][j].Pokaz_symbol().equals("Mlecz")) {
                    imgV = new ImageView(mlecz);
                }
                else if(wirtualny.Organizmy[i][j].Pokaz_symbol().equals("Czlowiek")) {
                    imgV = new ImageView(czlowiek);
                }
                else if(wirtualny.Organizmy[i][j].Pokaz_symbol().equals("Lis")) {
                    imgV = new ImageView(lis);
                }
                else if(wirtualny.Organizmy[i][j].Pokaz_symbol().equals("Owca")) {
                    imgV = new ImageView(owca);
                }
                else if(wirtualny.Organizmy[i][j].Pokaz_symbol().equals("Wilk")) {
                    imgV = new ImageView(wilk);
                }
                else {
                    imgV = new ImageView(zolw);
                }
                imgV.setId(String.valueOf(j+i*szerokosc));
                double rozmiar;
                if(1000.0/szerokosc>500.0/wysokosc) {
                    rozmiar = 500.0/wysokosc;
                }
                else {
                    rozmiar = 1000.0/szerokosc;
                }
                imgV.setFitWidth(rozmiar);
                imgV.setPreserveRatio(true);
                hBox.getChildren().add(imgV);
            }
            plansza.getChildren().add(hBox);
        }
        //listner();
    }

    private void refresh() {
        for(int i =0; i<wysokosc; ++i) {
            for(int j=0; j<szerokosc; ++j) {
                ImageView imgV = (ImageView) plansza.lookup("#"+(j+i*szerokosc));
                if(wirtualny.Organizmy[i][j]==null) {
                    imgV.setImage(nullIMG);
                }
                else if(wirtualny.Organizmy[i][j].Pokaz_symbol().equals("Antylopa")) {
                    imgV.setImage(antylopa);
                }
                else if(wirtualny.Organizmy[i][j].Pokaz_symbol().equals("Trawa")) {
                    imgV.setImage(trawa);
                }
                else if(wirtualny.Organizmy[i][j].Pokaz_symbol().equals("Barszcz")) {
                    imgV.setImage(barszcz);
                }
                else if(wirtualny.Organizmy[i][j].Pokaz_symbol().equals("Guarana")) {
                    imgV.setImage(guarana);
                }
                else if(wirtualny.Organizmy[i][j].Pokaz_symbol().equals("Jagody")) {
                    imgV.setImage(jagody);
                }
                else if(wirtualny.Organizmy[i][j].Pokaz_symbol().equals("Mlecz")) {
                    imgV.setImage(mlecz);
                }
                else if(wirtualny.Organizmy[i][j].Pokaz_symbol().equals("Czlowiek")) {
                    imgV.setImage(czlowiek);
                }
                else if(wirtualny.Organizmy[i][j].Pokaz_symbol().equals("Lis")) {
                    imgV.setImage(lis);
                }
                else if(wirtualny.Organizmy[i][j].Pokaz_symbol().equals("Owca")) {
                    imgV.setImage(owca);
                }
                else if(wirtualny.Organizmy[i][j].Pokaz_symbol().equals("Wilk")) {
                    imgV.setImage(wilk);
                }
                else if(wirtualny.Organizmy[i][j].Pokaz_symbol().equals("Zolw")) {
                    imgV.setImage(zolw);
                }
            }
        }
    }

    private void loadGraphic() {
        try {
            antylopa = new Image(("file:C:\\Users\\Rafał\\Documents\\WirtualnyFX\\src\\sample\\img\\antylopa.png"));
            //System.out.println("Blad? " + antylopa.isError());
            wilk = new Image(("file:C:\\Users\\Rafał\\Documents\\WirtualnyFX\\src\\sample\\img\\wilk.png"));
            //System.out.println("Blad? " + wilk.isError());
            czlowiek = new Image(("file:C:\\Users\\Rafał\\Documents\\WirtualnyFX\\src\\sample\\img\\czlowiek.png"));
            //System.out.println("Blad? " + czlowiek.isError());
            zolw = new Image(("file:C:\\Users\\Rafał\\Documents\\WirtualnyFX\\src\\sample\\img\\zolw.png"));
            //System.out.println("Blad? " + zolw.isError());
            guarana = new Image(("file:C:\\Users\\Rafał\\Documents\\WirtualnyFX\\src\\sample\\img\\guarana.png"));
            //System.out.println("Blad? " + guarana.isError());
            trawa = new Image(("file:C:\\Users\\Rafał\\Documents\\WirtualnyFX\\src\\sample\\img\\trawa.png"));
            //System.out.println("Blad? " + trawa.isError());
            barszcz = new Image(("file:C:\\Users\\Rafał\\Documents\\WirtualnyFX\\src\\sample\\img\\barszcz.png"));
            //System.out.println("Blad? " + barszcz.isError());
            lis = new Image(("file:C:\\Users\\Rafał\\Documents\\WirtualnyFX\\src\\sample\\img\\lis.png"));
            //System.out.println("Blad? " + lis.isError());
            mlecz = new Image(("file:C:\\Users\\Rafał\\Documents\\WirtualnyFX\\src\\sample\\img\\mlecz.png"));
            //System.out.println("Blad? " + mlecz.isError());
            nullIMG = new Image(("file:C:\\Users\\Rafał\\Documents\\WirtualnyFX\\src\\sample\\img\\null.png"));
            //System.out.println("Blad? " + nullIMG.isError());
            jagody = new Image(("file:C:\\Users\\Rafał\\Documents\\WirtualnyFX\\src\\sample\\img\\jagody.png"));
            //System.out.println("Blad? " + jagody.isError());
            owca = new Image(("file:C:\\Users\\Rafał\\Documents\\WirtualnyFX\\src\\sample\\img\\owca.png"));
            //System.out.println("Blad? " + owca.isError());
        }
        catch (Exception e) {
            System.out.println("Cos zle zapisalem");
            e.printStackTrace();
        }
    }


    @FXML
    private VBox plansza;
    @FXML
    private Button tura;
    @FXML
    private Image antylopa;
    @FXML
    private Image czlowiek;
    @FXML
    private Image lis;
    @FXML
    private Image owca;
    @FXML
    private Image wilk;
    @FXML
    private Image zolw;
    @FXML
    private Image barszcz;
    @FXML
    private Image guarana;
    @FXML
    private Image jagody;
    @FXML
    private Image mlecz;
    @FXML
    private Image trawa;
    @FXML
    private Image nullIMG;
    @FXML
    private Button safe;
    @FXML
    private Button closeBtn;

    public void safeGame(ActionEvent actionEvent) {
        try {
            wirtualny.Zapisz_swiat();
            Alert grats = new Alert(Alert.AlertType.CONFIRMATION);
            grats.setTitle("Zapisano");
            grats.setContentText("Gratulacje, grę poprawnie zapisano");
            grats.showAndWait();
        }
        catch (Exception e) {
            Alert blad = new Alert(Alert.AlertType.ERROR);
            blad.setTitle("Plik nie istnieje");
            blad.setHeaderText("Plik do zapisu nie istnieje");
            blad.setContentText("Stwórz plik \"Save.txt\"");
            blad.showAndWait();
        }
    }

    public void closeGame(ActionEvent actionEvent) {
        Stage stage = (Stage) closeBtn.getScene().getWindow();
        stage.close();
    }
}
