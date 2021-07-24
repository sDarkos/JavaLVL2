public class CheckArray {

    private static final int DEFAULT_ARRAY_SIZE = 4;

    static void checkSize(String[][] array) {

        boolean checkSize = true;

        if (DEFAULT_ARRAY_SIZE != array.length) {
            checkSize = false;
        }
        for (int i = 0; i < array.length; i++) {
            if (array[i].length != DEFAULT_ARRAY_SIZE) {
                checkSize = false;
            }
        }
        if (!checkSize) {
            throw new MyArraySizeException("array of the wrong size");
        } else {
            System.out.println("Array Size OK...");
        }
    }

    static void checkDataArray(String[][] array) {

        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[i].length; j++) {
                if (!isDigit(array[i][j]))
                    throw new MyArrayDataException("error element number: [" + i + "][" + j + "]. '" + array[i][j] + "' is not digit.");
            }
        }

        System.out.println("Array data OK...");
    }

    public static boolean isDigit(String str) {
        try {
            Double.parseDouble(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
