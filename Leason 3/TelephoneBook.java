import java.util.HashMap;

public class TelephoneBook {
    private static HashMap<Integer, String> telephone = new HashMap<>();

    public static void set(String name, int number) {
        telephone.put(number, name);
    }

    public static String get(String name) {

        String msg = new String();

        for (Integer key : telephone.keySet()) {
            if (telephone.get(key).equals(name)){
//                System.out.println(telephone.get(key)+ ": " + key); //ну или так
                msg += (telephone.get(key)+ ": " + key + "\n");
            }
        }
        if (msg.isEmpty()){
            msg = "не найдено";
        }
        return msg;
    }
}
