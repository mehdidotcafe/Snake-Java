import java.awt.Graphics;

public class GameModel
{
   private int value;

   public void compute()
   {
      this.value++;
   }

   public void print(Graphics g)
   {
      g.drawString(String.valueOf(this.value), 5, 15);
   }
}
