package org.example.cloning_project_toolkit;

import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class curl {

    public static void starting() throws IOException {
        // Запрос URL-адреса у пользователя
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.print("Введите URL-адрес: ");
        String urlString = reader.readLine();

        // Создание файла отчета
        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss"));
        String reportFilename = "curl_report_" + timestamp + ".txt";

        try (PrintWriter writer = new PrintWriter(new FileWriter(reportFilename))) {

            // Запись информации о запросе
            writer.println("Отчет curl-запроса");
            writer.println("URL-адрес: " + urlString);
            writer.println("Время запроса: " + timestamp);
            writer.println("----------------------------------");

            // Создание объекта URL
            URL url = new URL(urlString);

            // Открытие соединения
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            // Установка метода запроса (GET по умолчанию)
            connection.setRequestMethod("GET");

            // Получение кода ответа
            int responseCode = connection.getResponseCode();

            // Вывод информации о запросе
            System.out.println("Отправка curl-запроса на " + urlString);
            System.out.println("Код ответа: " + responseCode);
            writer.println("Отправка curl-запроса на " + urlString);
            writer.println("Код ответа: " + responseCode);

            // Чтение ответа (если он есть)
            if (responseCode == HttpURLConnection.HTTP_OK) { // Успешный запрос
                BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String inputLine;
                while ((inputLine = in.readLine()) != null) {
                    System.out.println(inputLine);
                    writer.println(inputLine);
                }
                in.close();
            } else {
                String message = "Запрос не выполнен. Код ответа: " + responseCode;
                System.out.println(message);
                writer.println(message);
            }

            // Закрытие соединения
            connection.disconnect();

            System.out.println("Запрос завершен. Отчет сохранен в файл: " + reportFilename);
            writer.println("----------------------------------");
            writer.println("Запрос завершен.");

        } catch (IOException e) {
            System.err.println("Ошибка при создании отчета: " + e.getMessage());
        }
    }
}
