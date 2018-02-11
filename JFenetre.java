
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class JFenetre extends JFrame implements Constantes {

	private ModeleDuJeu modele;
	
      public JFenetre() {
            // titre de la fenêtre
            super("Snake");
         // créer le modèle du jeu
            this.modele = new ModeleDuJeu();
            // fermeture de l'application lorsque la fenêtre est fermée
            setDefaultCloseOperation(EXIT_ON_CLOSE);
            // pas de redimensionnement possible de la fenêtre
            setResizable(false);
            // créer un conteneur qui affichera le jeu
            final JPanel content = new JPanel() {
                @Override
                protected void paintComponent(Graphics g) {
                      super.paintComponent(g);
                      // affichage du modèle du jeu
                      JFenetre.this.modele.affichage(g);
                }
          };
       // le listener gérant les entrées au clavier
          content.addKeyListener(new KeyAdapter() {
                @Override
                public void keyPressed(KeyEvent e) {
                      JFenetre.this.modele.gestionDuClavier(e);
                }
          });
            // dimension de ce conteneur 
            content.setPreferredSize(new Dimension(
            		NBRE_DE_COLONNES * CASE_EN_PIXELS,
                    NBRE_DE_LIGNES * CASE_EN_PIXELS));
         // s'assurer du focus pour le listener clavier
            setFocusable(false);
            content.setFocusable(true);
            // ajouter le conteneur à la fenêtre
            setContentPane(content);
         // Créer un thread infini
            Thread thread = new Thread(new Runnable() {
                  @Override
                  public void run() {
                        while (true) { // boucle infinie
                        	// à chaque fois que la boucle est exécutée, la
                            // méthode de calcul du jeu est appelée.
                            // Comme la boucle est infinie, la méthode de calcul
                            // sera appelée en cycle perpétuel.
                            JFenetre.this.modele.calcul();
                         // demander à l'EDT de redessiner le conteneur
                            content.repaint();
                         // temporisation
                            try {
                                  Thread.sleep(DELAY);
                            } catch (InterruptedException e) {
                                  //
                            }
                        }
                  }
            });
            // lancer le thread
            thread.start();
      }
      
      // Lancement du jeu 
      public static void main(String[] args) {
            // création de la fenêtre
            JFenetre fenetre = new JFenetre();
            // dimensionnement de la fenêre "au plus juste" suivant
            // la taille des composants qu'elle contient
            fenetre.pack();
            // centrage sur l'écran
            fenetre.setLocationRelativeTo(null);
            // affichage
            fenetre.setVisible(true);
      }

}