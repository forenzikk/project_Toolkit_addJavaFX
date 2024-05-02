package org.example.cloning_project_toolkit;

import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class OptimisedPortScanner {

    public static void startScanning() throws InterruptedException {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Введите IP-адрес: ");
        String ipAddress = scanner.nextLine();

        System.out.print("Введите начальный порт: ");
        int startPort = scanner.nextInt();
        System.out.print("Введите конечный порт: ");
        int endPort = scanner.nextInt();

        System.out.println("Сканирование портов...");

        // Создаём пул потоков
        int numThreads = 100; // Количество потоков (настройте в зависимости от системы)
        ExecutorService executor = Executors.newFixedThreadPool(numThreads);

        // Запускаем задачу сканирования для каждого порта в отдельном потоке
        for (int port = startPort; port <= endPort; port++) {
            int currentPort = port; // Для использования в лямбда-выражении
            executor.execute(() -> {
                try (Socket socket = new Socket()) {
                    socket.connect(new InetSocketAddress(ipAddress, currentPort), 200);
                    System.out.println("Порт " + currentPort + " открыт.");
                } catch (Exception ignored) {
                    // Порт закрыт или недоступен
                }
            });
        }

        // Завершаем работу пула потоков
        executor.shutdown();
        executor.awaitTermination(1, TimeUnit.MINUTES); // Ждём завершения всех задач (максимум 1 минута)

        System.out.println("Сканирование завершено.");
    }
}
