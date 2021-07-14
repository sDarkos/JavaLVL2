package obstacles;

import entity.Entity;

public class Wall implements Obstacles{

    public int height;

    public Wall(int height) {
        this.height = height;
    }

    @Override
    public void start(Entity entity) {
        if (entity.getIsReady()){
            if (height <= entity.getMaxJump()){
                entity.jump();
            } else {
                entity.noJump();
                entity.setIsReady(false);
            }
        }

    }
}
