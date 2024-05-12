package org.example.cloning_project_toolkit;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.Scanner;
import javafx.application.Application;
import java.util.concurrent.*;

public class main {
    public static void main(String[] args) throws Exception {

        Scanner scanner = new Scanner(System.in);

        System.out.println("XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX");
        System.out.println("||||||||||||||||||||||||||||||||||||||||");
        System.out.println("XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX");

        System.out.println("            _____");
        System.out.println("           /      \\");
        System.out.println("          |  X   X |");
        System.out.println("          |    ^   |");
        System.out.println("           \\__=__/");
        System.out.println("            |    |");
        System.out.println("            |____|");
        System.out.println("             \\  / ");
        System.out.println("              \\/ ");

        System.out.println("XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX");
        System.out.println("          MADE BY OVANNISYAN");
        System.out.println("XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX");

        System.out.println("Hello, hacker!");
        System.out.println("Nice to meet you in my Hacker ToolKit!");
        System.out.println("-----------------------------------------------");
        System.out.println("Enter code to your tool:");
        System.out.println("1 - Simply port scanner");
        System.out.println("2 - Optimized port scanner");
        System.out.println("3 - Fuzzer");
        System.out.println("4 - SHA-encoding tool");
        System.out.println("5 - ping scanning");
        System.out.println("6 - curl request");
        System.out.println("7 - signature of file (check malicious)");
        System.out.println("8 - Cookie Injection");
        System.out.println("10 - help page");

        System.out.println("Your choose: ");

        int temp = scanner.nextInt();
        if (temp == 1) {

            System.out.println("OK. Starting...");
            SimplyScanner.startScan();

        }

        if (temp == 2) {

            System.out.println("OK. Starting...");
            OptimisedPortScanner.startScanning();

        }

        if (temp == 3) {

            System.out.println("OK. Starting...");
            AdvancedFuzzer.start();

        }

        if (temp == 4) {

            System.out.println("OK. Starting...");
            SHAExample.maining();

        }

        if (temp == 5) {

            System.out.println("OK. Starting...");
            ping.maining();

        }

        if (temp == 6) {

            System.out.println("OK. Starting...");
            curl.starting();

        }

        if (temp == 7) {

            System.out.println("OK. Starting...");
            signature.checkIt();

        }

        if (temp == 8) {

            System.out.println("OK. Starting...");
            CookieInjection.start();

        }


        if (temp == 10) {

            System.out.println("OK. Starting...");
            Application.launch(ToolController.class, args);
        }


        scanner.close();
    }
}