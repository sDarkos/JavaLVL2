import java.util.*;

public class main {
    public static void main(String[] args) {
        String[] array = {
                "Осень","Весна","Зима","Лето","Зима","Осень","Весна", "Лето","Зима",
                "Осень","Зима","Осень","Осень","Лето","Шоколад","Виноград","Абрикос"
        };

        checkArray(array);

        TelephoneBook.set("петров", 11111);
        TelephoneBook.set("петров", 22222);
        TelephoneBook.set("козлов", 33333);
        TelephoneBook.set("козлов", 44444);
        TelephoneBook.set("козлов", 55555);
        TelephoneBook.set("иванов", 66666);
        TelephoneBook.set("козлов", 77777);
        TelephoneBook.set("добрынин", 88888);
        TelephoneBook.set("петров", 99999);
        System.out.println(TelephoneBook.get("козлов"));

    }

    public static void checkArray(String[] array) {
        Set<String> arr = new HashSet<>();
        for (String s : array) {
            arr.add(s);
        }
        for (String s : arr) {
            int i = 0;
            for (String s1 : array) {
                if(s.equals(s1)){
                    i++;
                }
            }
            System.out.println("В массиве: " + s + ": " + i + "шт.");
        }

    }
}
