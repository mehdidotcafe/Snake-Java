import java.awt.Dimension;
import java.awt.Graphics;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class MainWindow extends JFrame
{
   private GameModel model;

   public MainWindow()
   {
      super("Super snake");
      this.model = new GameModel();
      setDefaultCloseOperation(EXIT_ON_CLOSE);
      setResizable(false);
      final JPanel content = new JPanel()
      {
         @Override
         protected void paintComponent(Graphics g)
         {
            super.paintComponent(g);
            MainWindow.this.model.print(g);
         }
      };
      content.setPreferredSize(new Dimension(300, 300));
      setContentPane(content);

      Thread thread = new Thread(new Runnable()
      {
         @Override
         public void run()
         {
            while (true)
            {
               MainWindow.this.model.compute();
               content.repaint();
               try {
                  Thread.sleep(500);
               } catch (InterruptedException e)
               {
                  System.err.println("Sleep foire");
               }
            }
         }
      });
      thread.start();
   }

   public static void main(String[] args)
   {
      MainWindow win = new MainWindow();

      win.pack();
      win.setLocationRelativeTo(null);
      win.setVisible(true);
   }
}
