public class Activity implements Action
{


    private Entity entity;
    private WorldModel world;
    private ImageStore imageStore;
    private int repeatCount;


    public Activity(Entity entity, WorldModel world, ImageStore imageStore,
                     int repeatCount)
    {
        this.entity = entity;
        this.world = world;
        this.imageStore = imageStore;
        this.repeatCount = repeatCount;
    }


    public static Action createActivityAction(Entity entity, WorldModel world,
                                       ImageStore imageStore)
    {
        return new Activity(entity, world, imageStore, 0);
    }

    public void executeAction(EventScheduler scheduler)
    {
        ((ActiveEntity)entity).execute(world,
                imageStore, scheduler);
    }
}
