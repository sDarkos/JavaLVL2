public class Main {

    static final int size = 10000000;
    static final int h = size / 2;

    public static void main(String[] args) {

        OneThread();
        twoThread();

    }

    static void OneThread(){
        Float[] arr = new Float[size];

        for (int i = 0; i < arr.length; i++) {
            arr[i] = 1f;
        }

        long a = System.currentTimeMillis();

        for (int i = 0; i < arr.length; i++) {
            arr[i] = (float)(arr[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
        }

        System.out.println("One thread time : " + (System.currentTimeMillis() - a));
    }

    static void twoThread(){
        Float[] arr = new Float[size];

        for (int i = 0; i < arr.length; i++) {
            arr[i] = 1f;
        }

        long a = System.currentTimeMillis();

        Float[] a1 = new Float[h];
        Float[] a2 = new Float[h];

        System.arraycopy(arr, 0, a1, 0, h);
        System.arraycopy(arr, h, a2, 0, h);

        Thread t1 = new Thread(() ->
        {
            for (int i = 0; i < a1.length; i++) {
                a1[i] = (float)(a1[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
            }
        });

        Thread t2 = new Thread(() ->
        {
            for (int i = 0; i < a2.length; i++) {
                a2[i] = (float)(a2[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
            }
        });

        t1.start();
        t2.start();

        System.arraycopy(a1, 0, arr, 0, h);
        System.arraycopy(a2, 0, arr, h, h);

        System.out.println("One thread time : " + (System.currentTimeMillis() - a));
    }
}
