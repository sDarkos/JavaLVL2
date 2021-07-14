package obstacles;

import entity.Entity;

public class RunRoad implements Obstacles{

    public int distance;

    public RunRoad( int distance) {
        this.distance = distance;
    }

    @Override
    public void start(Entity entity) {
        if (entity.getIsReady()){
            if (distance <= entity.getMaxRun()){
                entity.run();
                entity.setMaxRun(entity.getMaxRun() - distance);
            } else {
                entity.noRun();
                entity.setIsReady(false);
            }
        }

    }
}
