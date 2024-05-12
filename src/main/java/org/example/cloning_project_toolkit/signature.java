package org.example.cloning_project_toolkit;

import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class signature {

    public static void checkIt() {
        try {
            // Запрос названия файла у пользователя
            System.out.print("Введите название файла: ");
            String filename = new java.util.Scanner(System.in).nextLine();

            // Создание файла отчета
            String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss"));
            String reportFilename = "signature_report_" + timestamp + ".txt";

            try (PrintWriter writer = new PrintWriter(new FileWriter(reportFilename))) {

                // Запись информации в отчет
                writer.println("Отчет проверки сигнатуры файла");
                writer.println("Имя файла: " + filename);
                writer.println("Время проверки: " + timestamp);
                writer.println("----------------------------------");

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
                String message = "Сигнатура файла (SHA-256): " + hexSignature.toString();
                System.out.println(message);
                writer.println(message);

                System.out.println("Проверка завершена. Отчет сохранен в файл: " + reportFilename);
                writer.println("----------------------------------");
                writer.println("Проверка завершена.");

            } catch (IOException e) {
                System.err.println("Ошибка при создании отчета: " + e.getMessage());
            }

        } catch (NoSuchAlgorithmException e) {
            System.err.println("Ошибка: " + e.getMessage());
        }
    }
}
