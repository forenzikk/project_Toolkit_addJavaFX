package org.example.cloning_project_toolkit;

import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ping {

    public static void maining() throws IOException {
        // Запрос IP-адреса у пользователя
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.print("Введите IP-адрес: ");
        String ipAddress = reader.readLine();

        // Создание файла отчета
        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss"));
        String reportFilename = "ping_report_" + timestamp + ".txt";

        try (PrintWriter writer = new PrintWriter(new FileWriter(reportFilename))) {

            // Запись информации о проверке
            writer.println("Отчет проверки доступности");
            writer.println("IP-адрес: " + ipAddress);
            writer.println("Время проверки: " + timestamp);
            writer.println("----------------------------------");

            // Проверка доступности узла
            InetAddress inetAddress = InetAddress.getByName(ipAddress);
            System.out.println("Проверка доступности " + ipAddress + "...");
            writer.println("Проверка доступности " + ipAddress + "...");

            boolean reachable = inetAddress.isReachable(3000); // 3 секунды таймаут

            if (reachable) {
                String message = "Узел доступен!";
                System.out.println(message);
                writer.println(message);
            } else {
                String message = "Узел недоступен.";
                System.out.println(message);
                writer.println(message);
            }

            System.out.println("Проверка завершена. Отчет сохранен в файл: " + reportFilename);
            writer.println("----------------------------------");
            writer.println("Проверка завершена.");

        } catch (IOException e) {
            System.err.println("Ошибка при создании отчета: " + e.getMessage());
        }
    }
}
