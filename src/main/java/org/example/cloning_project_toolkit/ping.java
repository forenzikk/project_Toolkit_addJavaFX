package org.example.cloning_project_toolkit;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;

public class ping {

    public static void main() throws IOException {
        // Запрос IP-адреса у пользователя
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.print("Введите IP-адрес: ");
        String ipAddress = reader.readLine();

        // Проверка доступности узла
        InetAddress inetAddress = InetAddress.getByName(ipAddress);
        System.out.println("Проверка доступности " + ipAddress + "...");

        boolean reachable = inetAddress.isReachable(3000); // 3 секунды таймаут

        if (reachable) {
            System.out.println("Узел доступен!");
        } else {
            System.out.println("Узел недоступен.");
        }
    }
}
