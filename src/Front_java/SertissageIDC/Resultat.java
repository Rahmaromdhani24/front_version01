package Front_java.SertissageIDC;

import java.io.IOException;
import java.net.URI;
import java.net.URL;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;

import Front_java.ChartEttenduTorsadage;
import Front_java.ChartMoyenneTorsadage;
import Front_java.ChartSertissageIDCHauteur;
import Front_java.ChartSertissageIDCHauteurCoteC2;
import Front_java.ChartSertissageIDCTractionCote1;
import Front_java.ChartSertissageIDCTraction;
import Front_java.ChartSertissageIDCTraction;
import Front_java.Configuration.AppInformations;
import Front_java.Configuration.SertissageIDCInformations;
import Front_java.Configuration.TorsadageInformations;
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
    
    @FXML
    private Pane paneChartHauteur ; 
 
    
    @FXML
    private Pane paneChartForceTraction ; 
    


/*********** Chart HauteurSertissage C1 *****/

    @FXML
    private Pane  paneHauteurSertissageC2;
    

	@FXML
	public void initialize() {
	
		testerHauteurSertissageCote1(SertissageIDCInformations.hauteurSertissageC1Ech1 ,SertissageIDCInformations.hauteurSertissageC1Ech2 ,
        		SertissageIDCInformations.hauteurSertissageC1Ech3 ,	SertissageIDCInformations.hauteurSertissageC1EchFin) ; 
		
		testerHauteurSertissageCote2(SertissageIDCInformations.hauteurSertissageC2Ech1 ,SertissageIDCInformations.hauteurSertissageC2Ech2 ,
        		SertissageIDCInformations.hauteurSertissageC2Ech3 ,	SertissageIDCInformations.hauteurSertissageC2EchFin); 
		
		testerForceTractionCote1(SertissageIDCInformations.forceTractionEch1C1 ,SertissageIDCInformations.forceTractionEch2C1 ,
        		SertissageIDCInformations.forceTractionEch3C1 ,	SertissageIDCInformations.forceTractionEchFinC1) ; 
		
		testerForceTractionCote2(SertissageIDCInformations.forceTractionEch1C2 ,SertissageIDCInformations.forceTractionEch2C2 ,
        		SertissageIDCInformations.forceTractionEch3C2 ,	SertissageIDCInformations.forceTractionEchFinC2); 
		
		ajouterPdekAvecSertissageIDC() ; 
		initialiserDonneesPDEKEnregistrer() ; 
		afficherInfosOperateur();
		AppInformations.testTerminitionCommande = 0 ; 
		
		afficherDateSystem();
		afficherHeureSystem();
		
		 // Une fois les données récupérées, créer le graphique
        StackPane moyenneChartHauteur = ChartSertissageIDCHauteur.createMoyenneChartWithZones(
 // cote 1       	
        		SertissageIDCInformations.hauteurSertissageC1Ech1 ,SertissageIDCInformations.hauteurSertissageC1Ech2 ,
        		SertissageIDCInformations.hauteurSertissageC1Ech3 ,	SertissageIDCInformations.hauteurSertissageC1EchFin ,
        		// Cote 2
        		SertissageIDCInformations.hauteurSertissageC2Ech1 ,SertissageIDCInformations.hauteurSertissageC2Ech2 ,
        		SertissageIDCInformations.hauteurSertissageC2Ech3 ,	SertissageIDCInformations.hauteurSertissageC2EchFin); 
        
        paneChartHauteur.getChildren().clear(); // au cas où il y a un ancien graphique
        paneChartHauteur.getChildren().add(moyenneChartHauteur);  
        
        
        StackPane moyenneChartTraction = ChartSertissageIDCTraction.createMoyenneChartWithZones(
        		// cote 1       	
        		SertissageIDCInformations.forceTractionEch1C1 ,SertissageIDCInformations.forceTractionEch2C1 ,
        		SertissageIDCInformations.forceTractionEch3C1 ,	SertissageIDCInformations.forceTractionEchFinC1 ,
        		// Cote 2
        		SertissageIDCInformations.forceTractionEch1C2 ,SertissageIDCInformations.forceTractionEch2C2 ,
        		SertissageIDCInformations.forceTractionEch3C2 ,	SertissageIDCInformations.forceTractionEchFinC2); 
        paneChartForceTraction.getChildren().clear(); // au cas où il y a un ancien graphique
        paneChartForceTraction.getChildren().add(moyenneChartTraction);

     
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
     /****************************************************************************************************************/
		 public void testerHauteurSertissageCote1(double ech1 , double ech2 , double ech3 , double echFin){
			if(ech1 <= 10.85 ||ech2 <= 10.85 ||  ech3 <= 10.85 ||  echFin <= 10.85) {
			    Platform.runLater(() -> {
		            showErrorDialog("Une des valeurs mesureés des échantillons de Côté 1 dépasse les limites de de contrôle (zone rouge).\n L'opérateur "
		                + AppInformations.operateurInfo.getPrenom() + " " 
		                + AppInformations.operateurInfo.getNom() 
		                + " doit appliquer l'arrêt 1er défaut.", "Problème détecté dans hauteur de sertissage Côté 1");
		    	    });
		 }
			if(ech1 >= 11 ||ech2 >=  11 ||  ech3 >= 11 ||  echFin >=  11) {
			    Platform.runLater(() -> {
		            showErrorDialog("Une des valeurs mesureés des échantillons de Côté 1 dépasse les limites de de contrôle (zone rouge).\n L'opérateur "
		                + AppInformations.operateurInfo.getPrenom() + " " 
		                + AppInformations.operateurInfo.getNom() 
		                + " doit appliquer l'arrêt 1er défaut.", "Problème détecté dans hauteur de sertissage Côté 1");
		    	    });
		 }
			if((ech1 <= 10.88 && ech1 > 10.85) ||(ech2 <= 10.88 && ech2 > 10.85) || (ech3 <= 10.88 && ech3 > 10.85) ||  (echFin <= 10.88 && echFin > 10.85)) {
			    Platform.runLater(() -> {
		            showWarningDialog("Une des valeurs mesureés des échantillons de Côté 1 dépasse les limites de de d'alarme (zone jaune).\n L'opérateur "
		                + AppInformations.operateurInfo.getPrenom() + " " 
		                + AppInformations.operateurInfo.getNom() 
		                + " doit informer son supérieur hiérachique.", "Problème détecté dans hauteur de sertissage Côté 1");
		    	    });
		 }
			if((ech1 <= 11 && ech1 > 10.97) ||(ech2 <= 11 && ech2 > 10.97) || (ech3 <= 11 && ech3 > 10.97) ||  (echFin <= 11 && echFin > 10.97)) {
			    Platform.runLater(() -> {
			    	showWarningDialog("Une des valeurs mesureés des échantillons de Côté 1 dépasse les limites de de d'alarme (zone jaune).\n L'opérateur "
		                + AppInformations.operateurInfo.getPrenom() + " " 
		                + AppInformations.operateurInfo.getNom() 
		                + " doit informer son supérieur hiérachique.", "Problème détecté dans hauteur de sertissage Côté 1");
		    	    });
		 }
		 }
		 
		 public void testerHauteurSertissageCote2(double ech1 , double ech2 , double ech3 , double echFin){
			if(ech1 <= 10.85 ||ech2 <= 10.85 ||  ech3 <= 10.85 ||  echFin <= 10.85) {
			    Platform.runLater(() -> {
		            showErrorDialog("Une des valeurs mesureés des échantillons de Côté 2 dépasse les limites de de contrôle (zone rouge).\n L'opérateur "
		                + AppInformations.operateurInfo.getPrenom() + " " 
		                + AppInformations.operateurInfo.getNom() 
		                + " doit appliquer l'arrêt 1er défaut.", "Problème détecté dans hauteur de sertissage Côté 2");
		    	    });
		 }
			if(ech1 >= 11 ||ech2 >=  11 ||  ech3 >= 11 ||  echFin >=  11) {
			    Platform.runLater(() -> {
		            showErrorDialog("Une des valeurs mesureés des échantillons de Côté 2 dépasse les limites de de contrôle (zone rouge).\n L'opérateur "
		                + AppInformations.operateurInfo.getPrenom() + " " 
		                + AppInformations.operateurInfo.getNom() 
		                + " doit appliquer l'arrêt 1er défaut.", "Problème détecté dans hauteur de sertissage Côté 2");
		    	    });
		 }
			if((ech1 <= 10.88 && ech1 > 10.85) ||(ech2 <= 10.88 && ech2 > 10.85) || (ech3 <= 10.88 && ech3 > 10.85) ||  (echFin <= 10.88 && echFin > 10.85)) {
			    Platform.runLater(() -> {
			    	showWarningDialog("Une des valeurs mesureés des échantillons de Côté 2 dépasse les limites de de d'alarme (zone jaune).\n L'opérateur "
		                + AppInformations.operateurInfo.getPrenom() + " " 
		                + AppInformations.operateurInfo.getNom() 
		                + " doit informer son supérieur hiérachique.", "Problème détecté dans hauteur de sertissage Côté 2");
		    	    });
		 }
			if((ech1 <= 11 && ech1 > 10.97) ||(ech2 <= 11 && ech2 > 10.97) || (ech3 <= 11 && ech3 > 10.97) ||  (echFin <= 11 && echFin > 10.97)) {
			    Platform.runLater(() -> {
			    	showWarningDialog("Une des valeurs mesureés des échantillons de Côté 2 dépasse les limites de de d'alarme (zone jaune).\n L'opérateur "
		                + AppInformations.operateurInfo.getPrenom() + " " 
		                + AppInformations.operateurInfo.getNom() 
		                + " doit informer son supérieur hiérachique.", "Problème détecté dans hauteur de sertissage Côté 2");
		    	    });
		 }
		 }
		 public void testerForceTractionCote1(double ech1 , double ech2 , double ech3 , double echFin){
			 //ech1 = 85
				if(ech1 <= 50 ||ech2 <= 50 ||  ech3 <= 50 ||  echFin <= 50) {
				    Platform.runLater(() -> {
			            showErrorDialog("Une des valeurs mesureés des échantillons de Côté 1 dépasse les limites de de contrôle (zone rouge).\n L'opérateur "
			                + AppInformations.operateurInfo.getPrenom() + " " 
			                + AppInformations.operateurInfo.getNom() 
			                + " doit appliquer l'arrêt 1er défaut.", "Problème détecté dans force de traction Côté 1");
			    	    });
			 }
				
			
				if((ech1 >= 50 && ech1 <= 60) ||(ech2 >= 50 && ech2 <= 60) || (ech3 >= 50 && ech3 <= 60) ||  (echFin >= 50 && echFin <= 60)) {
				    Platform.runLater(() -> {
				    	showWarningDialog("Une des valeurs mesureés des échantillons de Côté 1 dépasse les limites de de d'alarme (zone jaune).\n L'opérateur "
			                + AppInformations.operateurInfo.getPrenom() + " " 
			                + AppInformations.operateurInfo.getNom() 
			                + " doit informer son supérieur hiérachique.", "Problème détecté dans force de traction Côté 1");
			    	    });
			 }
			 }
		 
		 public void testerForceTractionCote2(double ech1 , double ech2 , double ech3 , double echFin){
				if(ech1 <= 50 ||ech2 <= 50 ||  ech3 <= 50 ||  echFin <= 50) {
				    Platform.runLater(() -> {
			            showErrorDialog("Une des valeurs mesureés des échantillons de Côté 2 dépasse les limites de de contrôle (zone rouge).\n L'opérateur "
			                + AppInformations.operateurInfo.getPrenom() + " " 
			                + AppInformations.operateurInfo.getNom() 
			                + " doit appliquer l'arrêt 1er défaut.", "Problème détecté dans force de traction Côté 2");
			    	    });
			 }
				
			
				if((ech1 >= 50 && ech1 <= 60) ||(ech2 >= 50 && ech2 <= 60) || (ech3 >= 50 && ech3 <= 60) ||  (echFin >= 50 && echFin <= 60)) {
				    Platform.runLater(() -> {
				    	showWarningDialog("Une des valeurs mesureés des échantillons de Côté 2 dépasse les limites de de d'alarme (zone jaune).\n L'opérateur "
			                + AppInformations.operateurInfo.getPrenom() + " " 
			                + AppInformations.operateurInfo.getNom() 
			                + " doit informer son supérieur hiérachique.", "Problème détecté dans force de traction Côté 2");
			    	    });
			 }
			 }
	 /**********************************   Alerts  Erreur et warning    **********************************************/
		   
		 private void showErrorDialog(String title, String message) {
		 		Image errorIcon = new Image(getClass().getResource("/icone_erreur.png").toExternalForm());
		 		ImageView errorImageView = new ImageView(errorIcon);
		 		errorImageView.setFitWidth(100);
		 		errorImageView.setFitHeight(100);

		 		VBox iconBox = new VBox(errorImageView);
		 		iconBox.setAlignment(Pos.CENTER);

		 		Label messageLabel = new Label(message);
		 		messageLabel.setWrapText(true);
		 		messageLabel.setStyle("-fx-font-size: 19px; -fx-font-weight: bold; -fx-text-alignment: center;");

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
		 		closeButton.setStyle("-fx-font-size: 19px; -fx-padding: 10px 20px; -fx-font-weight: bold;");
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
		  
		 	
		 	private void showWarningDialog(String title, String message) {
		 		Image warningIcon = new Image(getClass().getResource("/warning.jpg").toExternalForm());
		 		ImageView warningImageView = new ImageView(warningIcon);
		 		warningImageView.setFitWidth(150);
		 		warningImageView.setFitHeight(150);

		 		VBox iconBox = new VBox(warningImageView);
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
		 		closeButton.setStyle("-fx-font-size: 19px; -fx-padding: 10px 20px; -fx-font-weight: bold;");
		 		content.setActions(closeButton);

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
}