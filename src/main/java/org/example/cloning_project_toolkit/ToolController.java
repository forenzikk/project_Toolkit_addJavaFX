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

        Button scanningButton = new Button("Simply port scanner");
        scanningButton.setOnAction(e -> showMessage("Сканирование портов необходимо для четкого\n" +
                " понимания того, какое ПО и сервисы \nрасположены на машине, после чего можно\n" +
                " составлять возможные вектора реализации\n первичного доступа в целевую систему"));

        Button scanning2Button = new Button("Optimised port scanner");
        scanning2Button.setOnAction(e -> showMessage("Оптимизированное и быстрое сканирование\n портов" +
                " отлично подойдет пользователям для \nбыстрого реагирования при изучении \nанализа" +
                " защищенности целевой системы. \nОтнюдь никому не придется часами ждать \nрезультатов" +
                " сканирования портов"));

        Button reverseButton = new Button("Fuzzer");
        reverseButton.setOnAction(e -> showMessage("   Фаззер - незаменимый инструмент \nпентестера при анализе" +
                " уязвимостей \n   на веб-ресурсах. Быстрый перебор каталогов\n веб-сервера позволит пользователям\n" +
                "    найти все скрытые тайны системного администратора"));

        Button attackButton = new Button("SHA-encoding tool");
        attackButton.setOnAction(e -> showMessage("Есть большое количество различных кодировок.\n Многие" +
                " из них уже легко взламываются,\n другие - посложнее, но тоже. Весьма \nхорошим вариантом" +
                " будет использование \nнескольких 'замудренных' алгоримтов \nхеширования для большей" +
                " надежности"));

        Button pingButton = new Button("ping scanning");
        pingButton.setOnAction(e -> showMessage("Хочешь приступить к пентесту,\n но не понимаешь, блочит" +
                " ли\n твои пакеты файрвол? Или просто \nхочешь проверить сетевую доступность \nудаленного" +
                " хоста? Тебе явно стоит \nпоиграться с ping-cканированием!"));

        Button curlButton = new Button("curl request");
        curlButton.setOnAction(e -> showMessage("   Какой анализ защищенности веба \nвозможен без использования" +
                " веб-запросов? Он явно\nнужен нам под рукой, чтобы оперативно \nполучить исходные данные" +
                " для \n   последующего анализа"));

        Button signaButton = new Button("signature of file");
        signaButton.setOnAction(e -> showMessage("Получил странный файл, но хочешь \nв пару кликов узнать" +
                " его \nфункционал? А что если это модификация \nизвестного ПО или утилиты? \nЭто явно наш вариант!"));

        VBox buttons = new VBox(10, scanningButton, scanning2Button, reverseButton, attackButton, pingButton,
                curlButton, signaButton);
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

        Scene popupScene = new Scene(vbox, 400, 300);
        popupStage.setScene(popupScene);
        popupStage.show();
    }

}
