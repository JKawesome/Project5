import processing.core.PImage;

import java.util.List;

public abstract class ActiveEntity extends Entity
{
    private int resourceLimit;
    private int resourceCount;
    private int actionPeriod;

    public ActiveEntity(String id, Point position,
                        List<PImage> images, int resourceLimit, int resourceCount,
                        int actionPeriod)
    {
        super(id, position, images);
        this.resourceLimit = resourceLimit;
        this.resourceCount = resourceCount;
        this.actionPeriod = actionPeriod;
    }

    public int getActionPeriod()
    {
        return actionPeriod;
    }

    public int getResourceCount()
    {
        return resourceCount;
    }

    public void setResourceCount(int num)
    {
        resourceCount = num;
    }

    public int getResourceLimit()
    {
        return resourceLimit;
    }

    public void scheduleActions(EventScheduler scheduler,
                                WorldModel world, ImageStore imageStore)
    {
        scheduler.scheduleEvent(this,
                Activity.createActivityAction(this, world, imageStore),
                actionPeriod);
    }
    abstract void execute(WorldModel world, ImageStore imageStore, EventScheduler scheduler);
}
