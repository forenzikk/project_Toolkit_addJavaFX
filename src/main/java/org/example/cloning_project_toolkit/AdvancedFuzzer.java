package org.example.cloning_project_toolkit;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class AdvancedFuzzer {

    public static void start() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Введите целевой URL (например, http://example.com/): ");
        String targetURL = scanner.nextLine();

        System.out.print("Введите путь к словарю: ");
        String filename = scanner.nextLine();

        // Создание файла отчета
        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss"));
        String reportFilename = "fuzz_report_" + timestamp + ".txt";

        try (PrintWriter writer = new PrintWriter(new FileWriter(reportFilename));
             BufferedReader br = new BufferedReader(new FileReader(filename))) {

            // Запись информации о сканировании
            writer.println("Отчет сканирования");
            writer.println("Целевой URL: " + targetURL);
            writer.println("Словарь: " + filename);
            writer.println("----------------------------------");

            String line;
            while ((line = br.readLine()) != null) {
                String urlToCheck = targetURL + line.trim();
                try {
                    URL url = new URL(urlToCheck);
                    HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                    connection.setRequestMethod("GET");

                    int responseCode = connection.getResponseCode();
                    if (responseCode == HttpURLConnection.HTTP_OK) {
                        String message = "Найден скрытый каталог или файл: " + urlToCheck;
                        System.out.println(message);
                        writer.println(message);
                    } else {
                        String message = "Каталог или файл не найден: " + urlToCheck;
                        System.out.println(message);
                        // Можно закомментировать следующую строку, если нужно записывать только успешные результаты
                        // writer.println(message);
                    }
                    connection.disconnect();
                } catch (IOException e) {
                    String message = "Ошибка при обращении к URL: " + urlToCheck;
                    System.out.println(message);
                    writer.println(message);
                }
            }

            System.out.println("Сканирование завершено. Отчет сохранен в файл: " + reportFilename);
            writer.println("----------------------------------");
            writer.println("Сканирование завершено.");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
