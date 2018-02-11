
public class Case implements Constantes {

      private int xIndice;
      private int yIndice;
      
      public Case(int xIndice, int yIndice) {
            this.xIndice = xIndice;
            this.yIndice = yIndice;
      }

      // indice horizontal
      public void setIndiceX(int x) {
            this.xIndice = x;
      }

      // indice horizontal
      public int getIndiceX() {
            return this.xIndice;
      }

      // indice vertical
      public void setIndiceY(int y) {
            this.yIndice = y;
      }

      // indice vertical
      public int getIndiceY() {
            return this.yIndice;
      }

      // coordonnée horizontale en pixels
      public int getX() {
            return this.xIndice * CASE_EN_PIXELS;
      }

      // coordonnée verticale en pixels
      public int getY() {
            return this.yIndice * CASE_EN_PIXELS;
      }

      public int getLargeur() {
            return CASE_EN_PIXELS;
      }

      public int getHauteur() {
            return CASE_EN_PIXELS;
      }
      
   // renvoie true si la case est contenue dans le plateau de jeu
      public boolean estValide() {
            return this.xIndice >= 0 && this.xIndice < NBRE_DE_COLONNES
                   && this.yIndice >= 0 && this.yIndice < NBRE_DE_LIGNES;
      }
      
      @Override
      public boolean equals(Object obj) {
            if (obj instanceof Case) {
                  Case box = (Case) obj;
                  return this.xIndice == box.xIndice
                         && this.yIndice == box.yIndice;
            }
            return false;
      }
      
}