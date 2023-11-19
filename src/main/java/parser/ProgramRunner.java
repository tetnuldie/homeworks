package parser;

import utilities.Iterator;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.EmptyStackException;

public class ProgramRunner {

    public static void main(String[] args) {
   /*     XMLParser parser = new XMLParser("D:\\At1Project\\xmlParser\\src\\main\\resources\\test_valid.xml");
        parser.parseDocument();
        if (parser.getErrors().isEmpty()) {
            System.out.println("Valid xml");
        } else {
            Iterator<String> errorsIter = parser.getErrors().iterator();
            while (errorsIter.hasNext()) {
                System.out.println(errorsIter.next());
            }
        }*/

        String input = "Malinouski";

        try {
            // Получите экземпляр MessageDigest с алгоритмом SHA-256
            MessageDigest digest = MessageDigest.getInstance("SHA-256");

            // Обновите сообщение
            digest.update(input.getBytes());

            // Получите хеш в виде массива байт
            byte[] hashedBytes = digest.digest();

            // Преобразуйте массив байт в строку шестнадцатеричных символов
            StringBuilder stringBuilder = new StringBuilder();
            for (byte b : hashedBytes) {
                stringBuilder.append(String.format("%02x", b));
            }

            String sha256Hash = stringBuilder.toString();

            // Выведите результат
            System.out.println("Input: " + input);
            System.out.println("SHA-256 Hash: " + sha256Hash);
            System.out.println("len = "+ hashedBytes.length);

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

    }


    }
