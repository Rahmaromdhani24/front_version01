package Front_java.Loading;

import java.io.IOException;
import java.lang.ModuleLayer.Controller;
import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialogLayout;

import Front_java.Configuration.AppInformations;
import Front_java.Configuration.SoudureInformations;
import Front_java.Controller.Dashboard2Controller;
import Front_java.Modeles.SoudureDTO;
import Front_java.SoudureUltrason.RemplirFormCodeB.RemplirFormCodeB;
import javafx.animation.PauseTransition;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.*;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.*;



public class LoadingController {

	// Variables pour stocker la position de la souris
	private double xOffset = 0;
	private double yOffset = 0;
 
    
    @FXML
    private StackPane rootPane; 
    
    @FXML
    private Button closeButton;
    
    @FXML
    private Button btnTerminer;

    @FXML
    private Button btnPause;

   @FXML
    private void closeFenetre2(ActionEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();
    }

    @FXML
    private void initialize() {
        // Charger la langue par défaut (Français)

    }
    @FXML
    private void pauseChargement(ActionEvent event) {
        try {
            // Charger le fichier FXML pour la fenêtre de chargement
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Front_java/SoudureUltrason/CodeB/RemplirQuantiteAtteintAvantCodeB.fxml"));
            Scene loadingScene = new Scene(loader.load());

            // Ajouter le fichier CSS
            String cssPath = "/Front_java/SoudureUltrason/CodeB/RemplirQuantitieAtteintAvantCodeB.css";
            if (getClass().getResource(cssPath) != null) {
                loadingScene.getStylesheets().add(getClass().getResource(cssPath).toExternalForm());
            } else {
                System.out.println("Fichier CSS introuvable : " + cssPath);
            }

            // Récupérer la fenêtre parente
            Stage parentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();

            // Cacher la fenêtre parente
            parentStage.hide();

            // Créer la fenêtre de chargement
            Stage loadingStage = new Stage();
            loadingStage.setScene(loadingScene);
            loadingStage.initStyle(StageStyle.UNDECORATED);
            loadingStage.initModality(Modality.APPLICATION_MODAL);
            loadingStage.initOwner(parentStage);

            // Ajouter une icône (facultatif)
            String iconPath = "/logo_app.jpg";
            if (getClass().getResource(iconPath) != null) {
                loadingStage.getIcons().add(new Image(getClass().getResourceAsStream(iconPath)));
            } else {
                System.out.println("Icône introuvable : " + iconPath);
            }

            // Permettre de déplacer la fenêtre avec la souris
            loadingScene.setOnMousePressed(eventMouse -> {
                xOffset = eventMouse.getSceneX();
                yOffset = eventMouse.getSceneY();
            });

            loadingScene.setOnMouseDragged(eventMouse -> {
                loadingStage.setX(eventMouse.getScreenX() - xOffset);
                loadingStage.setY(eventMouse.getScreenY() - yOffset);
            });

            // Afficher la fenêtre de chargement
            loadingStage.show();
        } catch (IOException ex) {
            System.out.println("❌ Erreur lors du chargement de la fenêtre de chargement : " + ex.getMessage());
            ex.printStackTrace();
        }
    }


    private Dashboard2Controller parentController; // Référence vers la fenêtre mère

    public void setParentController(Dashboard2Controller parentController) {
        this.parentController = parentController;
    }
    
    @FXML
    public void terminerChargement() {
        AppInformations.testTerminitionCommande = 1;

        // Vérifier si le parentController n'est pas null avant d'appeler la mise à jour
        if (parentController != null) {
            parentController.actualiserFenetreMere();
            parentController.afficherNotification("Veuillez remplir la quantité atteinte.");
        }

        // Fermer la fenêtre fille
        Stage stage = (Stage) btnTerminer.getScene().getWindow();
        stage.close();
    }
    private Runnable onTerminerAction;

    // Méthode pour définir l'action à exécuter lorsque le bouton Terminer est cliqué
    public void setOnTerminerAction(Runnable action) {
        this.onTerminerAction = action;
    }

    // Méthode appelée lorsque le bouton "Terminer" est cliqué
    @FXML
    private void onTerminerClicked() {
        if (onTerminerAction != null) {
            onTerminerAction.run(); // Exécute l'action définie
        }
        // Fermer la fenêtre de chargement si nécessaire
        Stage stage = (Stage) btnTerminer.getScene().getWindow();
        stage.close();
    }

}
