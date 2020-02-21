import processing.core.PImage;

import java.util.List;

public abstract class AnimatedEntity extends ActiveEntity
{
    private int animationPeriod;

    public AnimatedEntity(String id, Point position,
                          List<PImage> images, int resourceLimit, int resourceCount,
                          int actionPeriod, int animationPeriod)
    {
        super(id, position, images, resourceLimit, resourceCount, actionPeriod);
        this.animationPeriod = animationPeriod;
    }
    public int getAnimationPeriod()
    {
        return animationPeriod;
    }

    public void nextImage()
    {
        super.setImageIndex((super.getImageIndex() + 1) % super.getImages().size());
    }

    public void scheduleActions(EventScheduler scheduler,
                                WorldModel world, ImageStore imageStore)
    {
        scheduler.scheduleEvent(this,
                Activity.createActivityAction(this, world, imageStore),
                super.getActionPeriod());
        scheduler.scheduleEvent(this,
                Animation.createAnimationAction(this,0), getAnimationPeriod());
    }
}
