package org.example.cloning_project_toolkit;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
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

        for (int port = startPort; port <= endPort; port++) {
            try {
                Socket socket = new Socket();
                socket.connect(new InetSocketAddress(ipAddress, port), 200); // 200ms timeout
                socket.close();
                System.out.println("Порт " + port + " открыт.");
            } catch (IOException e) {
                // Порт закрыт или недоступен
            }
        }

        System.out.println("Сканирование завершено.");
    }
}
