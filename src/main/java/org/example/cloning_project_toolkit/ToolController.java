package org.example.cloning_project_toolkit;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class ToolController extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        GridPane root = new GridPane();
        root.setAlignment(Pos.CENTER);
        root.setHgap(20);
        root.setVgap(20);
        root.setPadding(new Insets(20));

        // Создаем кнопки с улучшенным стилем
        Button scanningButton = createStyledButton("Simply port scanner", "Сканирование портов необходимо" +
                "для четкого понимания того, какое ПО и сервисы расположены на машине, после чего можно" +
                "составлять возможные вектора реализации первичного доступа в целевую систему");
        Button scanning2Button = createStyledButton("Optimised port scanner", "Оптимизированное и быстрое" +
                "сканирование портов отлично подойдет пользователям для быстрого реагирования при изучении анализа" +
                "защищенности целевой системы. Отнюдь никому не придется часами ждать результатов сканирования портов");
        Button fazzingButton = createStyledButton("Fuzzer", "Фаззер - незаменимый инструмент пентестера" +
                "при анализе уязвимостей на веб-ресурсах. Быстрый перебор каталогов веб-сервера позволит пользователям" +
                "найти все скрытые тайны системного администратора");
        Button SHAencodingButton = createStyledButton("SHA-encoding tool", "Есть большое количество" +
                "различных кодировок. Многие из них уже легко взламываются, другие - посложнее, но тоже. Весьма" +
                "хорошим вариантом будет использование нескольких 'замудренных' алгоримтов хеширования для большей" +
                "надежности");
        Button pingScanningTool = createStyledButton("Ping-scanning", "Хочешь приступить к пентесту, но" +
                "не понимаешь, блочит ли твои пакеты файрвол? Или просто хочешь проверить сетевую доступность" +
                "удаленного хоста? Тебе явно стоит поиграться с ping-cканированием!");
        Button curlButton = createStyledButton("Curl", "Какой анализ защищенности веба возможен без" +
                "использования веб-запросов? Он явно нужен нам под рукой, чтобы оперативно получить исходные данные" +
                "для последующего анализа");
        Button signatureButton = createStyledButton("signature of file", "Получил странный файл, но" +
                "хочешь в пару кликов узнать его функционал? А что если это модификация известного ПО или утилиты?" +
                "Это явно наш вариант!");
        Button cookieInjectionButton = createStyledButton("Cookie Injection", "Флажок secure для файлов" +
                "cookie гарантирует, что файлы cookie должны отправляться только по HTTPS-соединению, однако для файлов" +
                "cookie также может быть установлено значение secure flag true для HTTP-соединения. Следовательно, файлы" +
                "cookie могут отправляться по HTTPS-соединению, установленному с помощью HTTP-соединения. Инструмент" +
                "лишь выявляет данную уязвимость в связи с соблюдением законодательства РФ и этичными нормами");


        // Добавляем кнопки на панель
        root.add(scanningButton, 0, 0);
        root.add(scanning2Button, 0, 1);
        root.add(fazzingButton, 0, 2);
        root.add(SHAencodingButton, 0, 3);
        root.add(pingScanningTool, 1, 0);
        root.add(curlButton, 1, 1);
        root.add(signatureButton, 1, 2);
        root.add(cookieInjectionButton, 1, 3);

        for (int i = 0; i < root.getChildren().size(); i++) {
            if (root.getChildren().get(i) instanceof Button) {
                Button button = (Button) root.getChildren().get(i);
                button.setPrefWidth(200); // ширина
                button.setPrefHeight(50); // высота
            }
        }

        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.setTitle("JavaFX Buttons Example");
        primaryStage.show();
    }

    private Button createStyledButton(String text, String message) {
        Button button = new Button(text);
        button.setOnAction(e -> showMessage(message));

        //стиль кнопки
        button.setFont(Font.font("Arial", 14));
        button.setTextFill(Color.WHITE);
        button.setBackground(new javafx.scene.layout.Background(
                new javafx.scene.layout.BackgroundFill(Color.web("#4CAF50"),
                        new javafx.scene.layout.CornerRadii(5), Insets.EMPTY)));

        return button;
    }

    private void showMessage(String message) {
        Stage popupStage = new Stage();
        popupStage.initModality(Modality.APPLICATION_MODAL);
        popupStage.setTitle("Message");

        Label label = new Label(message);
        label.setWrapText(true);
        label.setFont(Font.font(14));

        Button closeButton = new Button("Close");
        closeButton.setOnAction(e -> popupStage.close());

        VBox vbox = new VBox(10, label, closeButton);
        vbox.setAlignment(Pos.CENTER);
        vbox.setPadding(new Insets(20));

        Scene popupScene = new Scene(vbox, 400, 300);
        popupStage.setScene(popupScene);
        popupStage.show();
    }
}
