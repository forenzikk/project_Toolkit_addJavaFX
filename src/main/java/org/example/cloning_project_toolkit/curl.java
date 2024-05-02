package org.example.cloning_project_toolkit;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class curl {

    public static void starting() throws IOException {
        // Запрос URL-адреса у пользователя
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.print("Введите URL-адрес: ");
        String urlString = reader.readLine();

        // Создание объекта URL
        URL url = new URL(urlString);

        // Открытие соединения
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();

        // Установка метода запроса (GET по умолчанию)
        connection.setRequestMethod("GET");

        // Получение кода ответа
        int responseCode = connection.getResponseCode();

        // Вывод информации о запросе
        System.out.println("Отправка curl-запроса на " + urlString);
        System.out.println("Код ответа: " + responseCode);

        // Чтение ответа (если он есть)
        if (responseCode == HttpURLConnection.HTTP_OK) { // Успешный запрос
            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                System.out.println(inputLine);
            }
            in.close();
        } else {
            System.out.println("Запрос не выполнен. Код ответа: " + responseCode);
        }

        // Закрытие соединения
        connection.disconnect();
    }
}
