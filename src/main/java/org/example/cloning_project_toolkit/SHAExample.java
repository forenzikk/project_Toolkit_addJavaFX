package org.example.cloning_project_toolkit;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class SHAExample {

    public static void maining() throws NoSuchAlgorithmException {

        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the password to hash: ");
        String passwordToHash = scanner.nextLine();
        String salt = getSalt();

        // Создание файла отчета
        String timestamp = LocalDateTime.now()
                .format(DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss"));
        String reportFilename = "hash_report_" + timestamp + ".txt";

        try (PrintWriter writer = new PrintWriter(new FileWriter(reportFilename))) {

            // Запись информации о хешировании
            writer.println("Отчет хеширования пароля");
            writer.println("Пароль: " + passwordToHash);
            writer.println("Соль: " + salt);
            writer.println("----------------------------------");

            String securePassword = get_SHA_1_SecurePassword(passwordToHash, salt);
            System.out.println("SHA 1: " + securePassword);
            writer.println("SHA 1: " + securePassword);

            securePassword = get_SHA_256_SecurePassword(passwordToHash, salt);
            System.out.println("SHA 256: " + securePassword);
            writer.println("SHA 256: " + securePassword);

            securePassword = get_SHA_384_SecurePassword(passwordToHash, salt);
            System.out.println("SHA 384: " + securePassword);
            writer.println("SHA 384: " + securePassword);

            securePassword = get_SHA_512_SecurePassword(passwordToHash, salt);
            System.out.println("SHA 512: " + securePassword);
            writer.println("SHA 512: " + securePassword);

            System.out.println(
                    "Хеширование завершено. Отчет сохранен в файл: "
                            + reportFilename);
            writer.println("----------------------------------");
            writer.println("Хеширование завершено.");

        } catch (IOException e) {
            System.err.println("Ошибка при создании отчета: " + e.getMessage());
        }
    }

    private static String get_SHA_1_SecurePassword(String passwordToHash,
                                                   String salt) {
        String generatedPassword = null;
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-1");
            md.update(salt.getBytes());
            byte[] bytes = md.digest(passwordToHash.getBytes());
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < bytes.length; i++) {
                sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16)
                        .substring(1));
            }
            generatedPassword = sb.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return generatedPassword;
    }

    private static String get_SHA_256_SecurePassword(String passwordToHash,
                                                     String salt) {
        String generatedPassword = null;
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            md.update(salt.getBytes());
            byte[] bytes = md.digest(passwordToHash.getBytes());
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < bytes.length; i++) {
                sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16)
                        .substring(1));
            }
            generatedPassword = sb.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return generatedPassword;
    }

    private static String get_SHA_384_SecurePassword(String passwordToHash,
                                                     String salt) {
        String generatedPassword = null;
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-384");
            md.update(salt.getBytes());
            byte[] bytes = md.digest(passwordToHash.getBytes());
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < bytes.length; i++) {
                sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16)
                        .substring(1));
            }
            generatedPassword = sb.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return generatedPassword;
    }

    private static String get_SHA_512_SecurePassword(String passwordToHash,
                                                     String salt) {
        String generatedPassword = null;
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-512");
            md.update(salt.getBytes());
            byte[] bytes = md.digest(passwordToHash.getBytes());
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < bytes.length; i++) {
                sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16)
                        .substring(1));
            }
            generatedPassword = sb.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return generatedPassword;
    }

    // Метод для генерации случайной соли
    private static String getSalt() throws NoSuchAlgorithmException {
        SecureRandom sr = SecureRandom.getInstance("SHA1PRNG");
        byte[] salt = new byte[16];
        sr.nextBytes(salt);
        return bytesToHex(salt);
    }

    // Метод для преобразования массива байтов в шестнадцатеричную строку
    private static String bytesToHex(byte[] bytes) {
        StringBuilder sb = new StringBuilder();
        for (byte b : bytes) {
            sb.append(String.format("%02x", b));
        }
        return sb.toString();
    }
}