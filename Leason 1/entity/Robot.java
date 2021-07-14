package entity;

public class Robot implements Entity{

    static int maxRun;
    static int maxJump;
    static boolean isReady = true;

    public Robot(int maxRun, int maxJump) {
        Robot.maxRun = maxRun;
        Robot.maxJump = maxJump;
    }

    @Override
    public void run() {
        System.out.println("Robot run...");
    }

    @Override
    public void jump() {
        System.out.println("Robot jump...");
    }

    @Override
    public void noRun() {
        System.out.println("Robot NO run... And retires");
    }

    @Override
    public void noJump() {
        System.out.println("Robot NO jump... And retires");
    }

    @Override
    public boolean getIsReady() {
        return isReady;
    }

    @Override
    public void setIsReady(boolean isReady) {
        Robot.isReady = isReady;
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
        Robot.maxRun = maxRun;
    }

}

