package entity;

public interface Entity {

      void run();

      void jump();

      void noRun();

      void noJump();

      boolean getIsReady();

      void setIsReady( boolean isReady);

      int getMaxJump();

      int getMaxRun();

      void setMaxRun(int maxRun);
}
