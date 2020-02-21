import java.util.List;
import java.util.Random;

import processing.core.PImage;

public class Fish extends ActiveEntity
{

    private final String CRAB_KEY = "crab";
    private final String CRAB_ID_SUFFIX = " -- crab";
    private final int CRAB_PERIOD_SCALE = 4;
    private final int CRAB_ANIMATION_MIN = 50;
    private final int CRAB_ANIMATION_MAX = 150;

    private final Random rand = new Random();

    public Fish(String id, Point position,
                    List<PImage> images, int resourceLimit, int resourceCount,
                    int actionPeriod)
    {
        super(id, position, images, resourceLimit, resourceCount, actionPeriod);
    }

    public static Fish createFish(String id, Point position, int actionPeriod,
                                    List<PImage> images)
    {
        return new Fish(id, position, images, 0, 0,
                actionPeriod);
    }


    public void execute(WorldModel world,
                                    ImageStore imageStore, EventScheduler scheduler)
    {
        Point pos = super.getPosition();  // store current position before removing

        world.removeEntity(this);
        scheduler.unscheduleAllEvents(this);

        Crab crab = Crab.createCrab(super.getId() + CRAB_ID_SUFFIX,
                pos, super.getActionPeriod() / CRAB_PERIOD_SCALE,
                CRAB_ANIMATION_MIN +
                        rand.nextInt(CRAB_ANIMATION_MAX - CRAB_ANIMATION_MIN),
                imageStore.getImageList(CRAB_KEY));

        world.addEntity(crab);
        crab.scheduleActions(scheduler, world, imageStore);
    }
}