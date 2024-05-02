package org.example.cloning_project_toolkit;

import java.io.FileInputStream;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class signature {

    public static void checkIt() {
        try {
            // Запрос названия файла у пользователя
            System.out.print("Введите название файла: ");
            String filename = new java.util.Scanner(System.in).nextLine();

            // Создание объекта MessageDigest для алгоритма SHA-256
            MessageDigest md = MessageDigest.getInstance("SHA-256");

            // Чтение файла и обновление дайджеста
            try (FileInputStream fis = new FileInputStream(filename)) {
                byte[] buffer = new byte[8192]; // Буфер для чтения
                int bytesRead;
                while ((bytesRead = fis.read(buffer)) != -1) {
                    md.update(buffer, 0, bytesRead);
                }
            }

            // Получение сигнатуры в виде байтового массива
            byte[] signatureBytes = md.digest();

            // Преобразование сигнатуры в шестнадцатеричную строку
            StringBuilder hexSignature = new StringBuilder();
            for (byte b : signatureBytes) {
                hexSignature.append(String.format("%02x", b));
            }

            // Вывод сигнатуры
            System.out.println("Сигнатура файла (SHA-256): " + hexSignature.toString());

        } catch (NoSuchAlgorithmException | IOException e) {
            System.err.println("Ошибка: " + e.getMessage());
        }
    }
}
