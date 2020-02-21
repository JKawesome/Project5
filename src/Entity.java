
import processing.core.PImage;

import java.util.List;

/*
Entity ideally would includes functions for how all the entities in our virtual world might act...
 */

public class Entity
{
   private String id;
   private Point position;
   private List<PImage> images;
   private int imageIndex;

   public Entity(String id, Point position,
                 List<PImage> images)
   {
      this.id = id;
      this.position = position;
      this.images = images;
      this.imageIndex = 0;
   }

   public String getId()
   {
      return id;
   }

   public Point getPosition()
   {
      return position;
   }

   public void setPosition(Point point)
   {
      this.position = point;
   }

   public List<PImage> getImages()
   {
      return images;
   }

   public PImage getCurrentImage()
   {
      return images.get(imageIndex);
   }

   public int getImageIndex()
   {
      return imageIndex;
   }

   public void setImageIndex(int num)
   {
      imageIndex = num;
   }
}
