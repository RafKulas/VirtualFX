package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Pair;
import sample.wirtualnyswiat.Swiat;

public class Controller {
    @FXML
    private Button closeBtn;
    @FXML
    private Button newgameBtn;
    @FXML
    private Button go;
    @FXML
    private Button loadBtn;
    @FXML
    private TextField wysokosc;
    @FXML
    private TextField szerokosc;
    @FXML
    private VBox nowySwiat;
    @FXML
    public void handleCloseBtnAction(ActionEvent event) {
        Stage stage = (Stage) closeBtn.getScene().getWindow();
        stage.close();
    }
    @FXML
    public void handleNewgameBtnAction(ActionEvent event) {
        nowySwiat.setVisible(true);
    }
    @FXML
    public void handlegoAction(ActionEvent event) {
        try {
            int wysokoscPlanszy = Integer.parseInt(wysokosc.getText());
            int szerokoscPlanszy = Integer.parseInt(szerokosc.getText());
            if(wysokoscPlanszy<3 || szerokoscPlanszy<2) {
                throw new Exception("Liczby mniejsze od 2");
            }
            Stage stage = new Stage();
            FXMLLoader fxmlLoader = new FXMLLoader();
            Pane root = fxmlLoader.load(getClass().getResource("plansza.fxml").openStream());
            stage.setScene(new Scene(root));
            stage.setTitle("Rafał Kulik, 175750");
            Plansza plansza = fxmlLoader.<Plansza>getController();
            plansza.setPlansza(szerokoscPlanszy, wysokoscPlanszy);
            //stage.setMaximized(true);
            stage.setResizable(false);
            stage.show();

            Stage current = (Stage) closeBtn.getScene().getWindow();
            current.close();
        }
        catch (Exception e){
            e.printStackTrace();
            int niepotrzebny;
            Alert blad = new Alert(Alert.AlertType.ERROR);
            blad.setTitle("Niepoprawne dane");
            blad.setHeaderText("Wpisane dane nie są poprawne");
            String info = "Szerokość oraz długość planszy muszą być nie mniejsze od 2!\n\n";
            try{
                niepotrzebny = Integer.parseInt(wysokosc.getText());
                if(niepotrzebny<2) {
                    throw new Exception("Liczby mniejsze od 2");
                }
            }
            catch (Exception e1){
                info+="Popraw pole \"wysokość\"\n";
            }
            try{
                niepotrzebny = Integer.parseInt(szerokosc.getText());
                if(niepotrzebny<2) {
                    throw new Exception("Liczby mniejsze od 2");
                }
            }
            catch (Exception e1){
                info+="Popraw pole \"szerokość\"\n";
            }
            blad.setContentText(info);
            blad.showAndWait();
        }

    }

    public void loadGame(ActionEvent actionEvent) {
        try {
            Stage stage = new Stage();
            FXMLLoader fxmlLoader = new FXMLLoader();
            Pane root = fxmlLoader.load(getClass().getResource("plansza.fxml").openStream());
            stage.setScene(new Scene(root));
            stage.setTitle("Rafał Kulik, 175750");
            Plansza plansza = fxmlLoader.<Plansza>getController();
            //stage.setMaximized(true);
            if(plansza.loadWorld()) {
                stage.setResizable(false);
                stage.show();
                Stage actual = (Stage) closeBtn.getScene().getWindow();
                actual.close();
            }
        }
        catch (Exception e) {
            Alert error = new Alert(Alert.AlertType.ERROR);
            error.setTitle("Ups");
            error.setHeaderText("Cos poszlo nie tak");
            error.setContentText("Najprawdopodobniej brakuje pliku");
        }
    }
}
