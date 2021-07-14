import entity.Cat;
import entity.Entity;
import entity.Human;
import entity.Robot;
import obstacles.Obstacles;
import obstacles.RunRoad;
import obstacles.Wall;

public class Main  {

    public static void main(String[] args) {

        Entity[] entities = {
                new Human(8000, 2),
                new Cat(3000,1),
                new Robot(10000,5)
        };

        Obstacles[] obstacles = {
                new Wall(1),
                new RunRoad(500),
                new Wall(2),
                new RunRoad(1000),
                new Wall(3),
                new RunRoad(3000),
                new Wall(4),
                new RunRoad(5000),
                new Wall(5),
                new RunRoad(10000)
        };

        start(entities,obstacles);

    }

    public static void start( Entity[] entity, Obstacles[] obstacles){
        for (int i = 0; i < obstacles.length; i++) {
            for (int j = 0; j < entity.length; j++) {
                obstacles[i].start(entity[j]);
            }
        }

    }

}
