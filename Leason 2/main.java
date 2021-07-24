public class main {
    public static void main(String[] args) {

        String[][] myArray = {
                {"1","2","3","4"},
                {"1","2","3","4"},
                {"1","2","5","4"},
                {"1","2","3","4"}
        };

        SumArray(myArray);

    }

    static void SumArray(String[][] arr){
        try {
            CheckArray.checkSize(arr);
            CheckArray.checkDataArray(arr);
        }
        catch (MyArraySizeException | MyArrayDataException ex){
                throw new MyArrayException("Check array", ex);
        }
        finally {
            System.out.println("Check array done...");
        }

        int[][] intArr = new int[4][4];

        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++) {
                try {
                    intArr[i][j] = Integer.parseInt(arr[i][j]);
                } catch (MyArraySizeException ex){
                }
            }
        }

        int sumArr = 0;

        for (int i = 0; i < intArr.length; i++) {
            for (int j = 0; j < intArr[i].length; j++) {
                sumArr += intArr[i][j];
            }
        }

        System.out.println("Sum all elements array: " + sumArr);
    }
}
