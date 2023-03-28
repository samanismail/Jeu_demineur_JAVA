/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package demineur.src;

/**
 *
 * @author thoar
 */
public class Plateau {
    
    //Attributs de la classe
    private int nblig; //Nombre de lignes du plateau
    private int nbcol; //Nombre de colonnes du plateau
    private int nbMines; //Nombre de mines sur le plateau
    private int nbVides; //Nombre de colonnes vides sur le plateau
    private Case[][] plateau; //Tableau à deux dimensions composés d'éléments de type Case
    private Jeu j; //Instance de jeu du plateau
    
    //Accesseurs des attributs
    public int getNblig() {
        return nblig;
    }

    public int getNbcol() {
        return nbcol;
    }

    public int getNbMines() {
        return nbMines;
    }

    public Jeu getJ() {
        return j;
    }

    public int getNbVides() {
        return nbVides;
    }

    public void setNbVides(int nbVides) {
        this.nbVides = nbVides;
    }
    
    //Constructeur de la classe
    public Plateau(Jeu jeu, int nblig, int nbcol){
        this.j = jeu;
        this.nblig = nblig;
        this.nbcol = nbcol;
        this.plateau = new Case[nblig+2][nbcol+2]; /*Le tableau est créé avec deux lignes et colonnes en plus, 
                                                    qui seront des cases "invisibles" c'est-à-dire des cases vides 
                                                    utilisées pour le test*/
        this.remplissage(jeu.getNiveau()); //Remplissage du tableau en fonction de la difficulté
        this.nbVides = (nblig * nbcol) - this.nbMines; //Calcul du nombre de case vide (le nombre de case mine est calculé dans la méthode remplissage()
    }
    
    //Méthode pour remplir le plateau
    public void remplissage(int niveau){
        //Toutes les cases du jeu sont transformées en case vide
        for(int i = 0; i < this.nblig+2;i++){
            for(int j = 0; j < this.nbcol+2;j++){
                this.plateau[i][j] = new CaseVide(this,i,j);
                if(i == 0 || i == (nblig+1) || j == 0 || j == (nbcol +1) )
                    this.plateau[i][j].setType("" + -1); //Si c'est une case "invisible", son type est mis à -1 afin de la reconnaître pour les tests
            }
        }
        
        this.nbMines = (int)(Math.round((this.nblig * this.nbcol * niveau)/10.0)); //Calcul du nombre de mines dans la partie
        int nombre = 0; //Compteur du nombre de mines créées
        while(nombre < this.nbMines){ //Tant qu'il n'y a pas assez de mines créées
            
            //Choix de coordonnées aléatoires dans le plateau
            //int x = (int)((Math.random()*nblig)+1);
            int x = (int)((Math.random()*nblig)+1);
            int y = (int)((Math.random()*nbcol)+1);
            
            if(!this.getCase(x,y).getType().equals("Mine")){ //Si la case n'est pas déjà une mine
                this.plateau[x][y] = new CaseMine(this, x, y); //La case est transformée en case mine
                nombre++; //Incrémentation du nombre de mines créées
            }
            
        }
    }
    
    //Méthode pour récupérer une case dans le plateau à l'aide de ses coordonnées
    public Case getCase(int lig, int col){
        return this.plateau[lig][col];
    }
    
    //Méthode pour afficher le plateau à chaque tour de jeu
    public void affichePlateau(){
        for(int i = 1;i<=this.nblig;i++){
            System.out.println("");
            for(int j = 1; j<=this.nbcol; j++){
                if(getCase(i,j).getActive() != true) //Si c'est une case qui n'a pas déjà été dévoilée
                System.out.print(" [ " + getCase(i,j).getLig() + " : " + getCase(i,j).getCol() + " ] "); //Affichage des coordonnées des cases si elles ne sont pas dévoilées
                else{
                    System.out.print(" [   " + getCase(i,j).getType() + "   ] "); //Sinon, affichage du nombre de mine à proximité
                }
            }
            System.out.println("");
        }
        System.out.println("");
    }
    
    //Méthode pour afficher le plateau dévoilé à la fin de la partie
    public void affichePlateauFinal(){
        for(int i = 1;i<=this.nblig;i++){
            System.out.println("");
            for(int j = 1; j<=this.nbcol; j++){
                
                if(!getCase(i,j).getType().equals("Mine") && getCase(i,j).getActive() != true){ //Si c'est une case non dévoilée
                    getCase(i,j).devoiler(true);
                    System.out.print(" [   " + getCase(i,j).getType() + "   ] " );
                }
                
                else if(getCase(i,j).getActive() == true) //Si c'est une case déjà dévoilée
                    System.out.print(" [   " + getCase(i,j).getType() + "   ] ");
                
                else{ //Si c'est une mine
                    System.out.print(" [  " + getCase(i,j).getType() + " ] ");
                }
            }
            System.out.println("");
        }
        System.out.println("");
    }
    
    //Méthode pour afficher le plateau avec toutes les cases dévoilées (partie facultative pour la démonstration)
    public void affichePlateauDevoile(){
       for(int i = 1;i<=this.nblig;i++){
           System.out.println("");
           for(int j = 1; j<=this.nbcol; j++){
               if(getCase(i,j).getActive() == true)
                   System.out.print(" [  Vide(D) ] "); //Affichage des coordonnées des cases si elles ne sont pas dévoilées
               else{
                   System.out.print(" [   " + getCase(i,j).getType() + "   ] "); //Sinon, affichage du nombre de mine à proximité
               }
           }
           System.out.println("");
       }
       System.out.println("");
    }
}
