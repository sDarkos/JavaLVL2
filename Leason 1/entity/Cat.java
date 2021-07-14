package entity;

public class Cat implements Entity{

    static int maxRun;
    static int maxJump;
    static boolean isReady = true;

    public Cat(int maxRun, int maxJump) {
        Cat.maxRun = maxRun;
        Cat.maxJump = maxJump;
    }

    @Override
    public void run() {
        System.out.println("Cat run...");
    }

    @Override
    public void jump() {
        System.out.println("Cat jump...");

    }

    @Override
    public void noRun() {
        System.out.println("Cat NO run... And retires");
    }

    @Override
    public void noJump() {
        System.out.println("Cat NO jump... And retires");
    }

    @Override
    public boolean getIsReady() {
        return isReady;
    }

    @Override
    public void setIsReady(boolean isReady) {
        Cat.isReady = isReady;
    }

    @Override
    public int getMaxJump() {
        return maxJump;
    }

    @Override
    public int getMaxRun() {
        return maxRun;
    }

    @Override
    public void setMaxRun(int maxRun) {
        Cat.maxRun = maxRun;
    }

}
