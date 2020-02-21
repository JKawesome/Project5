import processing.core.PImage;

import java.util.List;
import java.util.Optional;

public abstract class Octo extends Moving
{
    private boolean isFull;

    public Octo(String id, Point position,
                List<PImage> images, int resourceLimit, int resourceCount,
                int actionPeriod, int animationPeriod, boolean isFull)
    {
        super(id, position, images, resourceLimit, resourceCount, actionPeriod, animationPeriod);
        this.isFull = isFull;
    }

    public boolean moveTo(WorldModel world,
                          Entity target, EventScheduler scheduler)
    {
        if (super.getPosition().adjacent(target.getPosition()))
        {
            if(!isFull)
            {
                super.setResourceCount(super.getResourceCount() + 1);
                world.removeEntity(target);
                scheduler.unscheduleAllEvents(target);
            }
            return true;
        }
        else
        {
            Point nextPos = nextPosition(world, target.getPosition());

            if (!super.getPosition().equals(nextPos))
            {
                Optional<Entity> occupant = world.getOccupant(nextPos);
                if (occupant.isPresent())
                {
                    scheduler.unscheduleAllEvents(occupant.get());
                }

                world.moveEntity(this, nextPos);
            }
            return false;
        }
    }

    public abstract boolean transform(WorldModel world, EventScheduler scheduler, ImageStore imageStore);
}
