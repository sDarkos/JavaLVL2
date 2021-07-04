package entity ;

public class Human implements Entity{
    static int maxRun;
    static int maxJump;
    static boolean isReady = true;


    public Human(int maxRun, int maxJump) {
        Human.maxRun = maxRun;
        Human.maxJump = maxJump;
    }

    @Override
    public void run() {
        System.out.println("Human run...");
    }

    @Override
    public  void jump() {
        System.out.println("Human jump...");
    }

    @Override
    public void noRun() {
        System.out.println("Human NO run... And retires");
    }

    @Override
    public void noJump() {
        System.out.println("Human NO jump... And retires");
    }

    @Override
    public boolean getIsReady() {
        return isReady;
    }

    @Override
    public void setIsReady(boolean isReady) {
        Human.isReady = isReady;
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
        Human.maxRun = maxRun;
    }

}
