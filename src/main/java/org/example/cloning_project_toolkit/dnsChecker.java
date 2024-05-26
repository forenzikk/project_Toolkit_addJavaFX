package org.example.cloning_project_toolkit;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

public class dnsChecker {

    public static void start() throws IOException, UnknownHostException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("0. Проверка DNS-записи");
        System.out.println("1. Выход");

        int choice = 0;
        do {
            System.out.print("Введите номер инструмента: ");
            choice = Integer.parseInt(reader.readLine());

            switch (choice) {
                case 0:
                    dnsLookup(reader);
                    break;
                case 1:
                    System.out.println("До свидания!");
                    break;
                default:
                    System.out.println("Неверный выбор. Пожалуйста, попробуйте снова.");
            }
        } while (choice != 1);
    }

    private static void dnsLookup(BufferedReader reader) throws IOException {
        System.out.print("Введите имя хоста или IP-адрес: ");
        String hostname = reader.readLine();

        try {
            InetAddress address = InetAddress.getByName(hostname);
            System.out.println("IP-адрес: " + address.getHostAddress());
            System.out.println("Имя хоста: " + address.getHostName());
        } catch (UnknownHostException e) {
            System.out.println("Не удалось найти DNS-запись.");
        }
    }

}