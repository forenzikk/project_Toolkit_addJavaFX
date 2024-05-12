package org.example.cloning_project_toolkit;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

public class OptimisedPortScanner {

    private static final ReentrantLock lock = new ReentrantLock(); // Блокировка для синхронизации записи

    public static void startScanning() throws InterruptedException {
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

            // Создаём пул потоков
            int numThreads = 100; // Количество потоков (настройте в зависимости от системы)
            ExecutorService executor = Executors.newFixedThreadPool(numThreads);

            // Запускаем задачу сканирования для каждого порта в отдельном потоке
            for (int port = startPort; port <= endPort; port++) {
                int currentPort = port; // Для использования в лямбда-выражении
                executor.execute(() -> {
                    try (Socket socket = new Socket()) {
                        socket.connect(new InetSocketAddress(ipAddress, currentPort), 200);
                        // Синхронизированная запись в файл
                        lock.lock();
                        try {
                            System.out.println("Порт " + currentPort + " открыт.");
                            writer.println("Порт " + currentPort + " открыт.");
                        } finally {
                            lock.unlock();
                        }
                    } catch (Exception ignored) {
                        // Порт закрыт или недоступен
                    }
                });
            }

            // Завершаем работу пула потоков
            executor.shutdown();
            executor.awaitTermination(1, TimeUnit.MINUTES);

            System.out.println("Сканирование завершено.");
            writer.println("----------------------------------");
            writer.println("Сканирование завершено.");

        } catch (IOException e) {
            System.err.println("Ошибка при создании отчета: " + e.getMessage());
        }
    }
}
