package org.example.cloning_project_toolkit;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class SimplyScanner {

    public static void startScan() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Введите IP-адрес: ");
        String ipAddress = scanner.nextLine();

        System.out.print("Введите начальный порт: ");
        int startPort = scanner.nextInt();
        System.out.print("Введите конечный порт: ");
        int endPort = scanner.nextInt();

        System.out.println("Сканирование портов...");

        // Создание файла отчета
        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss"));
        String fileName = "scan_report_" + timestamp + ".txt";
        try (PrintWriter writer = new PrintWriter(new FileWriter(fileName))) {

            // Запись информации о сканировании
            writer.println("Отчет сканирования портов");
            writer.println("IP-адрес: " + ipAddress);
            writer.println("Диапазон портов: " + startPort + " - " + endPort);
            writer.println("----------------------------------");

            for (int port = startPort; port <= endPort; port++) {
                try {
                    Socket socket = new Socket();
                    socket.connect(new InetSocketAddress(ipAddress, port), 200); // 200ms timeout
                    socket.close();
                    System.out.println("Порт " + port + " открыт.");
                    writer.println("Порт " + port + " открыт."); // Запись в файл
                } catch (IOException e) {
                    // Порт закрыт или недоступен
                }
            }

            System.out.println("Сканирование завершено.");
            writer.println("----------------------------------");
            writer.println("Сканирование завершено.");

        } catch (IOException e) {
            System.err.println("Ошибка при создании отчета: " + e.getMessage());
        }
    }
}
