package Front_java;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Bounds;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.chart.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.util.Arrays;
import java.util.List;

public class ChartSertissageIDCHauteurCoteC2 extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        VBox root = new VBox(20);
        root.setPadding(new Insets(20));

        List<Double> moyennes = Arrays.asList(10.55, 10.75, 10.86, 10.9, 10.95, 11.0, 11.1, 11.2, 11.3);
        StackPane chartPane = createMoyenneChartWithZones();
        root.getChildren().add(chartPane);

        Scene scene = new Scene(root, 600, 450);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Zones Colorées - Soudure");
        primaryStage.show();

        // ➤ Attendre affichage complet avant de dessiner les zones
        Platform.runLater(() -> {
            LineChart<String, Number> chart = (LineChart<String, Number>) chartPane.getChildren().get(0);
            NumberAxis yAxis = (NumberAxis) chart.getYAxis();
            drawZones(chartPane, chart, yAxis);
        });
    }

    public static StackPane createMoyenneChartWithZones() {
        CategoryAxis xAxis = new CategoryAxis();
        NumberAxis yAxis = new NumberAxis(10.5, 11.35, 0.05);

        LineChart<String, Number> chart = new LineChart<>(xAxis, yAxis);
        chart.setLegendVisible(false);
        chart.setAnimated(false);
        chart.setPrefSize(400, 150);

        StackPane stackPane = new StackPane(chart);

        chart.lookup(".chart-plot-background").boundsInParentProperty().addListener((obs, oldVal, newVal) -> {
            Platform.runLater(() -> drawZones(stackPane, chart, yAxis));
        });

        return stackPane;
    }
    public static void drawZones(StackPane stackPane, LineChart<String, Number> chart, NumberAxis yAxis) {
        // Limites ajustées selon tes indications
        double[] limites = {10.5, 10.85, 10.88, 10.97, 11.0, 11.35};  // Rouges, jaunes, vertes, jaunes, rouges
        Color[] couleurs = {
            Color.rgb(255, 0, 0, 0.2),    // rouge bas
            Color.rgb(255, 255, 0, 0.2),  // jaune
            Color.rgb(0, 255, 0, 0.2),    // vert
            Color.rgb(255, 255, 0, 0.2),  // jaune
            Color.rgb(255, 0, 0, 0.2)     // rouge haut
        };

        double offset = 10;  // Décalage de 10 pixels vers le bas

        Node plotArea = chart.lookup(".chart-plot-background");
        if (plotArea == null) return;

        Bounds plotBounds = plotArea.getBoundsInParent();
        double width = plotBounds.getWidth();
        double offsetX = plotBounds.getMinX();

        Pane overlay = new Pane();
        overlay.setMouseTransparent(true);

        // Dessiner les zones colorées avec le nouvel ordre de limites
        for (int i = 0; i < limites.length - 1; i++) {
            double y1 = yAxis.getDisplayPosition(limites[i]) + offset;  // Ajouter un offset
            double y2 = yAxis.getDisplayPosition(limites[i + 1]) + offset;  // Ajouter un offset

            if (Double.isNaN(y1) || Double.isNaN(y2)) continue;

            Rectangle rect = new Rectangle(offsetX, Math.min(y1, y2), width, Math.abs(y2 - y1));
            rect.setFill(couleurs[i]);
            overlay.getChildren().add(rect);
        }

        // Ajouter des lignes de seuil
        for (int i = 1; i < limites.length - 1; i++) {
            double y = yAxis.getDisplayPosition(limites[i]) + offset;  // Ajouter un offset
            if (!Double.isNaN(y)) {
                Line line = new Line(offsetX, y, offsetX + width, y);
                line.setStroke(Color.GRAY);
                line.setStrokeWidth(1);
                overlay.getChildren().add(line);
            }
        }

        Node parent = plotArea.getParent();
        if (parent instanceof Pane pane) {
            pane.getChildren().removeIf(n -> "zone-overlay".equals(n.getUserData()));
            overlay.setUserData("zone-overlay");
            pane.getChildren().add(overlay);
        }
    }


}
