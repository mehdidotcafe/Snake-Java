
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.util.LinkedList;

public class Serpent {

      private LinkedList<Case> list;
      private Direction direction;
      private boolean estMort;
      private Direction demande;
      private int eatCount;
      private int moveCounter;
      
      public Serpent() {
            this.list = new LinkedList<Case>();
            this.list.add(new Case(14, 15));
            this.list.add(new Case(15, 15));
            this.list.add(new Case(16, 15));
            this.direction = Direction.VERS_LA_GAUCHE;
      }
      
      public void setDemandeClavier(Direction demande) {
          this.demande = demande;
    }
      
      private void tourner() {
          if (this.demande != null) { // une touche à été pressée
                // le serpent va vers le haut ou le bas
                if (this.direction == Direction.VERS_LE_HAUT
                            || this.direction == Direction.VERS_LE_BAS) {
                      if (this.demande == Direction.VERS_LA_DROITE) { // la touche droite
                                                                   // à été pressée
                            // le serpent tourne à droite
                            this.direction = Direction.VERS_LA_DROITE;
                      } else if (this.demande == Direction.VERS_LA_GAUCHE) { // la touche
                                                                          // gauche à
                                                                          // été pressée
                            // le serpent tourne à gauche
                            this.direction = Direction.VERS_LA_GAUCHE;
                      }
                } else { // le serpent va vers la droite ou la gauche
                      if (this.demande == Direction.VERS_LE_HAUT) { // la touche haut à
                                                                 // été pressée
                            // le serpent tourne vers le haut
                            this.direction = Direction.VERS_LE_HAUT;
                      } else if (this.demande == Direction.VERS_LE_BAS) { // la touche bas
                                                                       // à été pressée
                            // le serpent tourne vers le bas
                            this.direction = Direction.VERS_LE_BAS;
                      }
                }
                // nous avons tenu compte du clavier, nous le vidons afin de
                // forcer le joueur à rappuyer sur une touche pour demander
                // une autre direction
                this.demande = null;
          }
    }
      
      private void avance() {
          // ajoute en tête de liste la case sur laquelle
          // le serpent doit se déplacer
          this.list.addFirst(getNextcase());
          // supprime le dernier élément de la liste
          this.list.removeLast();
    }
      
      private Case getNextcase() {
          Case tete = this.list.getFirst();
          switch (this.direction) {
                case VERS_LE_HAUT:
                      return new Case(tete.getIndiceX(), tete.getIndiceY() - 1);
                case VERS_LA_DROITE:
                      return new Case(tete.getIndiceX() + 1, tete.getIndiceY());
                case VERS_LE_BAS:
                      return new Case(tete.getIndiceX(), tete.getIndiceY() + 1);
                case VERS_LA_GAUCHE:
                      return new Case(tete.getIndiceX() - 1, tete.getIndiceY());
          }
          return null;
    }
      
      private boolean peutAvancer() {
    	  Case nextCase = getNextcase();
          return nextCase.estValide() && !this.list.contains(nextCase);
    }
      
      public boolean estMort() {
          return this.estMort;
    }
     
      private boolean peutManger(Grenouille grenouille) {
          return grenouille.equals(getNextcase());
    }
      
      private void mange() {
          // ajoute en tête de liste la case sur laquelle
          // le serpent doit se déplacer
          this.list.addFirst(getNextcase());
       // comptabiliser les grenouilles "mangées"
          this.eatCount++;
    }
      
      public int getEatCount() {
          return this.eatCount;
    }
      
      private int getThresholdCounter(int niveau) {
          switch (niveau) {
                case 1:
                      return 20;
                case 2:
                      return 16;
                case 3:
                      return 14;
                case 4:
                      return 12;
                case 5:
                      return 10;
                case 6:
                      return 8;
                case 7:
                      return 6;
                case 8:
                      return 4;
                case 9:
                      return 3;
                default :
                      return 2;
          }
    }
      
      public void calcul(Grenouille grenouille, int niveau) {
    	// incrémenter le compteur
          this.moveCounter++;
          // vérifier qu'il est temps d'animer le serpent
          if (this.moveCounter >= getThresholdCounter(niveau)) {
                // remettre le compteur à zéro 
                this.moveCounter = 0;
                // calcul du serpent
                tourner();
                if (peutManger(grenouille)) {
                      mange();
                      grenouille.nouvelleGrenouille();
                } else if (peutAvancer()) {
                      avance();
                } else {
                      // la partie est perdue car le serpent
                      // a atteint les limites du plateau de jeu
                      this.estMort = true;
                }
          }
      }
      
      
      public void affichage(Graphics g) {
    	  // activer l'anti-aliasing du dessin
          Graphics2D g2 = (Graphics2D) g;
          g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                              RenderingHints.VALUE_ANTIALIAS_ON);
            // dessin du serpent
    	  for (Case box : this.list) {
              g.fillOval(box.getX(), box.getY(), box.getLargeur(), box.getHauteur());
        }
      }
      
}
