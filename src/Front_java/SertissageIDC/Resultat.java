package Front_java.SertissageIDC;

import java.io.IOException;
import java.net.URI;
import java.net.URL;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;

import Front_java.Configuration.AppInformations;
import Front_java.Configuration.SertissageIDCInformations;
import Front_java.Modeles.OperateurInfo;
import Front_java.Modeles.SertissageIDCData;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.event.*;
import javafx.fxml.*;
import javafx.geometry.Pos;
import javafx.scene.*;
import javafx.scene.chart.StackedBarChart;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.*;
import java.time.*;
import java.time.format.DateTimeFormatter;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialogLayout;
import javafx.animation.KeyFrame;
import javafx.util.Duration;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;



public class Resultat {

	// Variables pour stocker la position de la souris
	private double xOffset = 0;
	private double yOffset = 0;
 
    @FXML
    private Button btnClose;

    @FXML
    private Button btnLogout;

    @FXML
    private Button btnMinimize;

    @FXML
    private Button btnSuivant;

    @FXML
    private Label codeControleSelectionner;

    @FXML
    private Label dateSystem;

    @FXML
    private Label nbrEch;

    @FXML
    private Label forceTraction;

    @FXML
    private Label forceTractionEch1C1;

    @FXML
    private Label forceTractionEch1C2;

    @FXML
    private Label forceTractionEch2C1;

    @FXML
    private Label forceTractionEch2C2;

    @FXML
    private Label forceTractionEch3C1;

    @FXML
    private Label forceTractionEch3C2;

    @FXML
    private Label forceTractionEchFinC1;

    @FXML
    private Label forceTractionEchFinC2;

    @FXML
    private Label hauteurSertissage;

    @FXML
    private Label hauteurSertissageEch1C1;

    @FXML
    private Label hauteurSertissageEch1C2;

    @FXML
    private Label hauteurSertissageEch2C1;

    @FXML
    private Label hauteurSertissageEch2C2;

    @FXML
    private Label hauteurSertissageEch3C1;

    @FXML
    private Label hauteurSertissageEch3C2;

    @FXML
    private Label hauteurSertissageEchFinC1;

    @FXML
    private Label hauteurSertissageEchFinC2;

    @FXML
    private Label heureSystem;

    @FXML
    private Label matriculeUser;

    @FXML
    private Label nbrCycle;

    @FXML
    private Label nomPrenomUser;

    @FXML
    private Label nomProjet;

    @FXML
    private Label numMachine;

    @FXML
    private Label operationUser;

    @FXML
    private Label plantUser;

    @FXML
    private Label posteUser;

    @FXML
    private Label produit;

    @FXML
    private BorderPane rootPane;

    @FXML
    private Label sectionFil;

    @FXML
    private Label segementUser;

    @FXML
    private Label serieProduit;

    @FXML
    private Label quantiteCycle ; 
    @FXML
    private StackPane stackPane;

/*********** Chart HauteurSertissage C1 *****/
    @FXML
    private StackedBarChart<String, Number> chartHauteurC1; // Lier le graphique à l'ID du FXML
    @FXML
    private CategoryAxis xAxis; // Lier l'axe X à l'ID du FXML
    @FXML
    private NumberAxis yAxis; // Lier l'axe Y à l'ID du FXML
    @FXML
    private Pane  paneHauteurSertissageC1;
    
    /*********** Chart HauteurSertissage C2 *****/
    @FXML
    private StackedBarChart<String, Number> chartHauteurC2; // Lier le graphique à l'ID du FXML
  
    @FXML
    private Pane  paneHauteurSertissageC2;
    

	@FXML
	public void initialize() {
	
		ajouterPdekAvecSertissageIDC() ; 
		initialiserDonneesPDEKEnregistrer() ; 
		afficherInfosOperateur();
		AppInformations.testTerminitionCommande = 0 ; 
		
		afficherDateSystem();
		afficherHeureSystem();
		setupChartHauteur() ;
		setupChartTraction() ;
	}




	@FXML
	private void close(ActionEvent event) {
		Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		stage.close();
	}

	@FXML
	private void minimize(ActionEvent event) {
		Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		stage.setIconified(true);
	}
	@FXML
	private void scanne(ActionEvent event) {
	
	}
	

	@FXML
	public void suivant(ActionEvent event) {
	
		SertissageIDCInformations.projetSelectionner= null ; 
		SertissageIDCInformations.codeControleSelectionner= null ; 
		SertissageIDCInformations.sectionFilSelectionner = null ; 
		try {
			// Charger la scène de Dashboard1
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/Front_java/SertissageIDC/SelectionSertissageIDC.fxml"));
			Scene dashboard1Scene = new Scene(loader.load());
			dashboard1Scene.getStylesheets()
					.add(getClass().getResource("/Front_java/SertissageIDC/SelectionSertissageIDC.css").toExternalForm());

			// Créer une nouvelle fenêtre (Stage)
			Stage dashboard1Stage = new Stage();
			dashboard1Stage.setScene(dashboard1Scene);
			dashboard1Stage.setMaximized(true);
			dashboard1Stage.initStyle(StageStyle.UNDECORATED); // Supprimer le titre et les boutons
			Image icon = new Image("/logo_app.jpg");
			dashboard1Stage.getIcons().add(icon);
			dashboard1Stage.show();

			// Fermer la fenêtre actuelle (Dashboard2)
			Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
			currentStage.close();

		} catch (IOException e) {
			System.out.println("Erreur lors du chargement de la fenêtre dashboard1 : " + e.getMessage());
		}
	}
	

	

	@FXML
	void logout(ActionEvent event) {

    	AppInformations.reset();
    	SertissageIDCInformations.reset() ; 
    

		Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		stage.close();

		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/Front_java/Login.fxml"));
			Scene loginScene = new Scene(loader.load());
			loginScene.getStylesheets().add(getClass().getResource("/Front_java/loginDesign.css").toExternalForm());

			Stage loginStage = new Stage();
			loginStage.initStyle(StageStyle.UNDECORATED); // Placer cette ligne avant show()
			loginStage.setResizable(false); // Désactiver le redimensionnement
			Image icon = new Image("/logo_app.jpg");
			loginStage.getIcons().add(icon);

			loginStage.setScene(loginScene);
			loginStage.show();
		} catch (IOException e) {
			System.out.println("Erreur lors du chargement de la fenêtre login : " + e.getMessage());
		}
	}

	public void afficherInfosOperateur() {
		// Vérifier si les informations de l'opérateur existent
		if (AppInformations.operateurInfo != null) {
			OperateurInfo operateurInfo = AppInformations.operateurInfo;

			// Mettre à jour les labels avec les informations de l'opérateur
			matriculeUser.setText(String.valueOf(operateurInfo.getMatricule()));
			nomPrenomUser.setText(operateurInfo.getNom() + " " + operateurInfo.getPrenom());
			operationUser.setText(operateurInfo.getOperation());
			plantUser.setText(operateurInfo.getPlant());
			posteUser.setText(operateurInfo.getPoste());
			segementUser.setText(operateurInfo.getSegment());
			nomProjet.setText(SertissageIDCInformations.projetSelectionner);
			codeControleSelectionner.setText(SertissageIDCInformations.codeControleSelectionner);

		} else {
			System.out.println("Aucune information d'opérateur disponible.");
		}
	}

	private void afficherDateSystem() {
		LocalDate dateActuelle = LocalDate.now();

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

		dateSystem.setText(dateActuelle.format(formatter));
	}

	private void afficherHeureSystem() {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");

		Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(1), event -> {
			LocalTime heureActuelle = LocalTime.now();
			heureSystem.setText(heureActuelle.format(formatter));
		}));

		timeline.setCycleCount(Timeline.INDEFINITE); // Répéter indéfiniment
		timeline.play(); // Démarrer l'animation
	}
	public void initialiserDonneesPDEKEnregistrer() {
		
		nbrCycle.setText(SertissageIDCInformations.numCycle);
		hauteurSertissageEch1C1.setText(SertissageIDCInformations.hauteurSertissageC1Ech1+ " ");
		hauteurSertissageEch2C1.setText(SertissageIDCInformations.hauteurSertissageC1Ech2+ "");
		hauteurSertissageEch3C1.setText(SertissageIDCInformations.hauteurSertissageC1Ech3+ "");
		hauteurSertissageEchFinC1.setText(SertissageIDCInformations.hauteurSertissageC1EchFin+ "");
		
		forceTractionEch1C1.setText(SertissageIDCInformations.forceTractionEch1C1+ "");
		forceTractionEch2C1.setText(SertissageIDCInformations.forceTractionEch2C1+ "");
		forceTractionEch3C1.setText(SertissageIDCInformations.forceTractionEch3C1+ "");
		forceTractionEchFinC1.setText(SertissageIDCInformations.forceTractionEchFinC1+ "");
		
		hauteurSertissageEch1C2.setText(SertissageIDCInformations.hauteurSertissageC2Ech1+ "");
		hauteurSertissageEch2C2.setText(SertissageIDCInformations.hauteurSertissageC2Ech2+ "");
		hauteurSertissageEch3C2.setText(SertissageIDCInformations.hauteurSertissageC2Ech3+ "");
		hauteurSertissageEchFinC2.setText(SertissageIDCInformations.hauteurSertissageC2EchFin+ "");
		
		forceTractionEch1C2.setText(SertissageIDCInformations.forceTractionEch1C2+ "");
		forceTractionEch2C2.setText(SertissageIDCInformations.forceTractionEch2C2+ "");
		forceTractionEch3C2.setText(SertissageIDCInformations.forceTractionEch3C2+ "");
		forceTractionEchFinC2.setText(SertissageIDCInformations.forceTractionEchFinC2+ "");
		
		produit.setText(SertissageIDCInformations.produit+ "");
		serieProduit.setText(SertissageIDCInformations.serieProduit+ "");
		numMachine.setText(SertissageIDCInformations.numeroMachine +"");
		quantiteCycle.setText(SertissageIDCInformations.quantiteCycle +"");
				
	}



		/*********************************          Alerts        ***************************************/

		private void showErrorDialog(String title, String message) {
			Image errorIcon = new Image(getClass().getResource("/icone_erreur.png").toExternalForm());
			ImageView errorImageView = new ImageView(errorIcon);
			errorImageView.setFitWidth(100);
			errorImageView.setFitHeight(100);

			VBox iconBox = new VBox(errorImageView);
			iconBox.setAlignment(Pos.CENTER);

			Label messageLabel = new Label(message);
			messageLabel.setWrapText(true);
			messageLabel.setStyle("-fx-font-size: 19px; -fx-font-weight: bold; -fx-text-alignment: center; -fx-text-fill: black;");

			Label titleLabel = new Label(title);
			titleLabel.setStyle("-fx-font-size: 19px; -fx-text-alignment: center;");
			VBox titleBox = new VBox(titleLabel);
			titleBox.setAlignment(Pos.CENTER);

			VBox contentBox = new VBox(10, iconBox, messageLabel, titleBox);
			contentBox.setAlignment(Pos.CENTER);

			JFXDialogLayout content = new JFXDialogLayout();
			content.setBody(contentBox);
			content.setStyle("-fx-background-color: white; -fx-background-radius: 10;");

			JFXButton closeButton = new JFXButton("Fermer");
			closeButton.setStyle("-fx-font-size: 19px; -fx-padding: 10px 20px;-fx-font-weight: bold;");
			content.setActions(closeButton);

			// Utilisation de stackPane ici
			JFXDialog dialog = new JFXDialog(stackPane, content, JFXDialog.DialogTransition.CENTER);
			closeButton.setOnAction(e -> dialog.close());

			dialog.show();

			Platform.runLater(() -> {
				StackPane overlayPane = (StackPane) dialog.lookup(".jfx-dialog-overlay-pane");
				if (overlayPane != null) {
					overlayPane.setStyle("-fx-background-color: transparent;");
				}
			});
		}

/****************************** chart Hauteur Sertissage C1 **********************/
		// Configuration du graphique et des axes
		public void setupChartHauteur() {
		    // Configuration des axes
		    yAxis.setLowerBound(0);
		    yAxis.setUpperBound(30);
		    xAxis.setOpacity(0);  // Masquer l'axe X
		    yAxis.setOpacity(0);  // Masquer l'axe Y

		    // Création des séries de données
		    XYChart.Series<String, Number> series1 = new XYChart.Series<>();
		    series1.getData().add(new XYChart.Data<>("A", 10));

		    XYChart.Series<String, Number> series2 = new XYChart.Series<>();
		    series2.getData().add(new XYChart.Data<>("A", 10));

		    XYChart.Series<String, Number> series3 = new XYChart.Series<>();
		    series3.getData().add(new XYChart.Data<>("A", 10));

		    XYChart.Series<String, Number> series4 = new XYChart.Series<>();
		    series4.getData().add(new XYChart.Data<>("A", 10));

		    XYChart.Series<String, Number> series5 = new XYChart.Series<>();
		    series5.getData().add(new XYChart.Data<>("A", 10));

		    // Ajout des séries au graphique
		    chartHauteurC1.getData().addAll(series1, series2, series3, series4, series5);

		    // Masquer la légende
		    chartHauteurC1.setLegendVisible(false);

		    // Appliquer les couleurs après le rendu du graphique
		    Platform.runLater(() -> {
		        setBarColor(series1, "red");
		        setBarColor(series2, "yellow");
		        setBarColor(series3, "green");
		        setBarColor(series4, "yellow");
		        setBarColor(series5, "red");
		    });

		    // Ajout des points sur le graphique
		    /*Platform.runLater(() -> {
		        addPointToChart(chartHauteurC1, xAxis, yAxis, paneHauteurSertissageC1, 12, -250); // Décale de 15 pixels à droite
		        addPointToChart(chartHauteurC1, xAxis, yAxis, paneHauteurSertissageC1, 11, -200); // Décale de 15 pixels à droite
		        addPointToChart(chartHauteurC1, xAxis, yAxis, paneHauteurSertissageC1, 10.80, -150); // Décale de 15 pixels à droite
		    });*/
		}

		// Applique les couleurs aux barres de la série
		private void setBarColor(XYChart.Series<String, Number> series, String color) {
		    Platform.runLater(() -> {
		        for (XYChart.Data<String, Number> data : series.getData()) {
		            Node barNode = data.getNode();
		            if (barNode != null) {
		                barNode.setStyle("-fx-bar-fill: " + color + ";");
		            }
		        }
		    });
		}

		private void addPointToChart(StackedBarChart<String, Number> stackedBarChart, 
                CategoryAxis xAxis, 
                NumberAxis yAxis, 
                Pane overlayPane, 
                double yValue, 
                double xOffset) {
    // Vérification de la série à laquelle le point appartient
    int seriesIndex = getSeriesIndexForValue(yValue);
    
    if (seriesIndex == -1) {
        // Si la valeur n'appartient à aucune série valide, ne pas ajouter de point
        return;
    }

    // Calcul de la position Y correcte
    double yPosition = getYPositionForPoint(yValue, yAxis);

    // Ajustement de la position X pour la série spécifique
    double xPosition = xAxis.getDisplayPosition("A") + (seriesIndex * 15) + xOffset;

    // Création du point
    Circle point = new Circle(5);
    point.setFill(Color.BLACK);
    point.setCenterX(xPosition);
    point.setCenterY(yPosition);

    // Ajouter le point à l'overlayPane
    overlayPane.getChildren().add(point);
}

		// Détermine dans quelle série ajouter le point en fonction de la valeur Y
		private int getSeriesIndexForValue(double value) {
		    if (value >= 0 && value <= 10.85) {
		        return 0; // Série 1 (Rouge)
		    } else if (value > 10.85 && value <= 10.88) {
		        return 1; // Série 2 (Jaune)
		    } else if (value > 10.88 && value <= 10.97) {
		        return 2; // Série 3 (Vert)
		    } else if (value > 10.97 && value <= 11) {
		        return 3; // Série 4 (Jaune)
		    } else if (value > 11) {
		        return 4; // Série 5 (Rouge)
		    }
		    return -1; // Valeur par défaut (non valide)
		}
		// Calcule la position Y correcte en fonction des tranches de valeurs
		private double getYPositionForPoint(double value, NumberAxis yAxis) {
		    double cumulativeHeight = 0;

		    if (value >= 0 && value <= 10.85) {
		        cumulativeHeight = value;
		    } else if (value > 10.85 && value <= 10.88) {
		        cumulativeHeight = 10.85 + (value - 10.85);
		    } else if (value > 10.88 && value <= 10.97) {
		        cumulativeHeight = 10.88 + (value - 10.88); // Correction ici
		    } else if (value > 10.97 && value <= 11) {
		        cumulativeHeight = 10.97 + (value - 10.97); // Correction ici
		    } else if (value > 11 && value <= 20) {
		        cumulativeHeight = 11 + (value - 11); // Correction ici
		    }

		    return yAxis.getDisplayPosition(cumulativeHeight);
		}

		/****************************** chart Hauteur Sertissage C2 **********************/
		public void setupChartTraction() {
		    // Configuration des axes
		    yAxis.setLowerBound(0);
		    yAxis.setUpperBound(30);
		    xAxis.setOpacity(0);  // Masquer l'axe X
		    yAxis.setOpacity(0);  // Masquer l'axe Y

		    // Création des séries de données
		    XYChart.Series<String, Number> series1 = new XYChart.Series<>();
		    series1.getData().add(new XYChart.Data<>("A", 10));

		    XYChart.Series<String, Number> series2 = new XYChart.Series<>();
		    series2.getData().add(new XYChart.Data<>("A", 10));

		    XYChart.Series<String, Number> series3 = new XYChart.Series<>();
		    series3.getData().add(new XYChart.Data<>("A", 10));

		    XYChart.Series<String, Number> series4 = new XYChart.Series<>();
		    series4.getData().add(new XYChart.Data<>("A", 10));

		    XYChart.Series<String, Number> series5 = new XYChart.Series<>();
		    series5.getData().add(new XYChart.Data<>("A", 10));

		    XYChart.Series<String, Number> series6 = new XYChart.Series<>();
		    series6.getData().add(new XYChart.Data<>("A", 10));

		    XYChart.Series<String, Number> series7 = new XYChart.Series<>();
		    series7.getData().add(new XYChart.Data<>("A", 10));

		    // Ajout des séries au graphique
		    chartHauteurC2.getData().addAll(series1, series2, series3, series4, series5, series6, series7);

		    // Masquer la légende
		    chartHauteurC2.setLegendVisible(false);

		    // Appliquer les couleurs après le rendu du graphique
		    Platform.runLater(() -> {
		        setBarColor(series1, "red");   // Niveau 1 - Rouge
		        setBarColor(series2, "yellow"); // Niveau 2 - Jaune
		        setBarColor(series3, "green");  // Niveau 3 - Vert
		        setBarColor(series4, "green");  // Niveau 4 - Vert
		        setBarColor(series5, "green");  // Niveau 5 - Vert
		        setBarColor(series6, "green");  // Niveau 6 - Vert
		        setBarColor(series7, "green");  // Niveau 7 - Vert
		    });

		    // Ajout d'un point noir à une valeur spécifique
		  //  Platform.runLater(() -> addPointToChart(chartHauteurC2, xAxis, yAxis, paneHauteurSertissageC2, 15));
		}

/**********************************   Ajouter Sertissage IDC *****************************************************/
		 private void ajouterPdekAvecSertissageIDC() {
				Task<Void> task = new Task<>() {
					@Override
					protected Void call() throws Exception {
						try {
							// Code pour l'ajout du PDEK
							String token = AppInformations.token;
							String encodedProjet = URLEncoder.encode(SertissageIDCInformations.projetSelectionner,
									StandardCharsets.UTF_8);

							String url = "http://localhost:8281/operations/SertissageIDC/ajouterPdekSertissageIDC" + "?matricule="
									+ AppInformations.operateurInfo.getMatricule() + "&nomProjet=" + encodedProjet;

							// Récupération des valeurs saisies et création de l'objet SoudureDTO
							SertissageIDCData sertissageIDC = new SertissageIDCData();							

							sertissageIDC.setSectionFil(SertissageIDCInformations.sectionFilSelectionner);
							sertissageIDC.setForceTraction("50 N");
							sertissageIDC.setHauteurSertissageMax(11);
							sertissageIDC.setHauteurSertissageMin(10.85); 	
							LocalDate dateActuelle = LocalDate.now();
							DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
							sertissageIDC.setDate(dateActuelle.format(formatter)); 									
							sertissageIDC.setHauteurSertissageC1Ech1(SertissageIDCInformations.hauteurSertissageC1Ech1);
							sertissageIDC.setHauteurSertissageC1Ech2(SertissageIDCInformations.hauteurSertissageC1Ech2);
							sertissageIDC.setHauteurSertissageC1Ech3(SertissageIDCInformations.hauteurSertissageC1Ech3);
							sertissageIDC.setHauteurSertissageC1EchFin(SertissageIDCInformations.hauteurSertissageC1EchFin);
							sertissageIDC.setHauteurSertissageC2Ech1(SertissageIDCInformations.hauteurSertissageC2Ech1);		
							sertissageIDC.setHauteurSertissageC2Ech2(SertissageIDCInformations.hauteurSertissageC2Ech2);
							sertissageIDC.setHauteurSertissageC2Ech3(SertissageIDCInformations.hauteurSertissageC2Ech3);					
							sertissageIDC.setHauteurSertissageC2EchFin(SertissageIDCInformations.hauteurSertissageC2EchFin);
							sertissageIDC.setCodeControle(SertissageIDCInformations.codeControleSelectionner);
							sertissageIDC.setProduit(SertissageIDCInformations.produit);
							sertissageIDC.setSerieProduit(SertissageIDCInformations.serieProduit);
							sertissageIDC.setQuantiteCycle(SertissageIDCInformations.quantiteCycle);
							sertissageIDC.setNumeroMachine(SertissageIDCInformations.numeroMachine );
							sertissageIDC.setForceTractionC1Ech1(SertissageIDCInformations.forceTractionEch1C1);
							sertissageIDC.setForceTractionC1Ech2(SertissageIDCInformations.forceTractionEch2C1);
							sertissageIDC.setForceTractionC1Ech3(SertissageIDCInformations.forceTractionEch3C1);
							sertissageIDC.setForceTractionC1EchFin(SertissageIDCInformations.forceTractionEchFinC1);
							sertissageIDC.setForceTractionC2Ech1(SertissageIDCInformations.forceTractionEch1C2);
							sertissageIDC.setForceTractionC2Ech2(SertissageIDCInformations.forceTractionEch2C2);
							sertissageIDC.setForceTractionC2Ech3(SertissageIDCInformations.forceTractionEch3C2);
							sertissageIDC.setForceTractionC2EchFin(SertissageIDCInformations.forceTractionEchFinC2);
                            sertissageIDC.setQuantiteCycle(SertissageIDCInformations.quantiteCycle) ; 
                            
							// Conversion de l'objet SoudureDTO en JSON
							ObjectMapper objectMapper = new ObjectMapper();
							String sertissageIDCJson = objectMapper.writeValueAsString(sertissageIDC);

							HttpRequest request = HttpRequest.newBuilder().uri(URI.create(url))
									.header("Authorization", "Bearer " + token).header("Content-Type", "application/json")
									.POST(HttpRequest.BodyPublishers.ofString(sertissageIDCJson)).build();

							HttpClient client = HttpClient.newHttpClient();
							HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

							if (response.statusCode() == 201) {
								System.out.println("Succès Ajout PDEK : " + response.body());
							} else {
								System.out.println("Erreur dans l'ajout PDEK, code : " + response.statusCode() + ", message : "
										+ response.body());
								throw new Exception("Erreur dans l'ajout PDEK : " + response.body());
							}

						} catch (Exception e) {
							e.printStackTrace();
							throw new Exception("Erreur dans la méthode ajouterPdekAvecSoudure : " + e.getMessage());
						}
						return null;
					}
				};

				task.setOnFailed(event -> {
					Throwable exception = task.getException();
					System.out.println("Erreur lors de l'ajout du PDEK : " + exception.getMessage());
					showErrorDialog("Erreur", "Erreur lors de l'ajout du PDEK : " + exception.getMessage());
				});

				new Thread(task).start();
			}
	
}