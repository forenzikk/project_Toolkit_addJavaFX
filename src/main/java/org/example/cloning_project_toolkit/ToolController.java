package org.example.cloning_project_toolkit;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class ToolController extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        AnchorPane root = new AnchorPane();
        root.setPrefSize(1000, 1000);

        Button scanningButton = new Button("Scanning");
        scanningButton.setOnAction(e -> showMessage("Сканирование - реализация nmap. Реализация"));

        Button scanning2Button = new Button("Ovannisyan");
        scanning2Button.setOnAction(e -> showMessage("Сканирование - реализация nmap. Реализация"));

        Button reverseButton = new Button("Reverse");
        reverseButton.setOnAction(e -> showMessage("Реверс - изменение порядка следования элементов"));

        Button attackButton = new Button("Attack");
        attackButton.setOnAction(e -> showMessage("Атака - попытка вторжения в систему"));

        VBox buttons = new VBox(10, scanningButton, scanning2Button, reverseButton, attackButton);
        buttons.setAlignment(Pos.CENTER);
        AnchorPane.setTopAnchor(buttons, 450.0);
        AnchorPane.setLeftAnchor(buttons, 450.0);

        root.getChildren().addAll(buttons);

        primaryStage.setScene(new Scene(root));
        primaryStage.setTitle("JavaFX Buttons Example");
        primaryStage.show();
    }

    private void showMessage(String message) {
        Stage popupStage = new Stage();
        popupStage.initModality(Modality.APPLICATION_MODAL);
        popupStage.setTitle("Message");

        Label label = new Label(message);
        Button closeButton = new Button("Close");
        closeButton.setOnAction(e -> popupStage.close());

        VBox vbox = new VBox(10, label, closeButton);
        vbox.setAlignment(Pos.CENTER);

        Scene popupScene = new Scene(vbox, 300, 200);
        popupStage.setScene(popupScene);
        popupStage.show();
    }

}
