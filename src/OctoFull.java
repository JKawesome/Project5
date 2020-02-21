import java.util.List;
import java.util.Optional;

import processing.core.PImage;

public class OctoFull extends Octo
{
    public OctoFull(String id, Point position,
                    List<PImage> images, int resourceLimit, int resourceCount,
                    int actionPeriod, int animationPeriod)
    {
        super(id, position, images, resourceLimit, resourceCount, actionPeriod, animationPeriod, true);
    }


    public static OctoFull createOctoFull(String id, int resourceLimit,
                                        Point position, int actionPeriod, int animationPeriod,
                                        List<PImage> images)
    {
        return new OctoFull(id, position, images,
                resourceLimit, resourceLimit, actionPeriod, animationPeriod);
    }


    public void execute(WorldModel world,
                                        ImageStore imageStore, EventScheduler scheduler)
    {
        Optional<Entity> fullTarget = world.findNearest(super.getPosition(),
                Atlantis.class);

        if (fullTarget.isPresent() &&
                moveTo(world, fullTarget.get(), scheduler))
        {

            Entity current = fullTarget.get();
            if(current instanceof Atlantis)
            {
                //at atlantis trigger animation
                ((Atlantis)fullTarget.get()).scheduleActions(scheduler, world, imageStore);
                //transform to unfull
                transform(world, scheduler, imageStore);
            }
        }
        else
        {
            scheduler.scheduleEvent(this,
                    Activity.createActivityAction(this, world, imageStore),
                    super.getActionPeriod());
        }
    }

    public boolean transform(WorldModel world,
                              EventScheduler scheduler, ImageStore imageStore)
    {
        OctoNotFull octo = OctoNotFull.createOctoNotFull(super.getId(), super.getResourceLimit(),
                super.getPosition(), super.getActionPeriod(), super.getAnimationPeriod(),
                super.getImages());

        world.removeEntity(this);
        scheduler.unscheduleAllEvents(this);

        world.addEntity(octo);
        octo.scheduleActions(scheduler, world, imageStore);
        return true;
    }
}
