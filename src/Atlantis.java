import java.util.List;

import processing.core.PImage;

public class Atlantis extends AnimatedEntity
{
    private final int ATLANTIS_ANIMATION_REPEAT_COUNT = 7;

    public Atlantis(String id, Point position,
      List<PImage> images, int resourceLimit, int resourceCount,
      int actionPeriod, int animationPeriod)
   {
       super(id, position, images, resourceLimit, resourceCount, actionPeriod, animationPeriod);
   }

    public static Atlantis createAtlantis(String id, Point position,
                                        List<PImage> images)
    {
        return new Atlantis(id, position, images,
                0, 0, 0, 0);
    }

    public void execute(WorldModel world,
                                        ImageStore imageStore, EventScheduler scheduler)
    {
        scheduler.unscheduleAllEvents(this);
        world.removeEntity(this);
    }

    public void scheduleActions(EventScheduler scheduler,
                                WorldModel world, ImageStore imageStore)
    {
        scheduler.scheduleEvent(this,
                Animation.createAnimationAction(this, ATLANTIS_ANIMATION_REPEAT_COUNT),
                getAnimationPeriod());
    }
}
