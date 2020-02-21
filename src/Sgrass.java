import java.util.List;
import java.util.Optional;
import java.util.Random;

import processing.core.PImage;

public class Sgrass extends ActiveEntity
{
    private final String FISH_ID_PREFIX = "fish -- ";
    private final int FISH_CORRUPT_MIN = 20000;
    private final int FISH_CORRUPT_MAX = 30000;
    private final String FISH_KEY = "fish";

    private final Random rand = new Random();

    public Sgrass(String id, Point position,
                    List<PImage> images, int resourceLimit, int resourceCount,
                    int actionPeriod)
    {
        super(id, position, images, resourceLimit, resourceCount, actionPeriod);
    }

    public static Sgrass createSgrass(String id, Point position, int actionPeriod,
                                      List<PImage> images)
    {
        return new Sgrass(id, position, images, 0, 0,
                actionPeriod);
    }


    public void execute(WorldModel world,
                        ImageStore imageStore, EventScheduler scheduler)
    {
        Optional<Point> openPt = world.findOpenAround(super.getPosition());

        if (openPt.isPresent())
        {
            ActiveEntity fish = Fish.createFish(FISH_ID_PREFIX + super.getId(),
                    openPt.get(), FISH_CORRUPT_MIN +
                            rand.nextInt(FISH_CORRUPT_MAX - FISH_CORRUPT_MIN),
                    imageStore.getImageList(FISH_KEY));
            world.addEntity(fish);
            fish.scheduleActions(scheduler, world, imageStore);
        }
        scheduler.scheduleEvent(this, Activity.createActivityAction(this, world, imageStore), super.getActionPeriod());
    }
}