/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package demineur.src;

/**
 *
 * @author thoar
 */
public class Jeu {
    
    private int niveau; //Niveau de la partie (entre 1 et 9)
    private Plateau p; //Plateau pour le jeu
    private boolean jeufini; //Booléen pour savoir si la partie est terminée ou non
    private int coup; //Nombre de coups du joueur pour terminer la partie
    
    //Accesseurs des attributs
    public int getNiveau() {
        return niveau;
    }

    public Plateau getP() {
        return p;
    }

    public void setJeufini(boolean jeufini) {
        this.jeufini = jeufini;
    }

    //Constructeur de la classe (nblig et nbcol sont utilisés pour définir la taille du plateau de jeu)
    public Jeu(int niveau, int nblig, int nbcol){
        this.niveau = niveau;
        this.p = new Plateau(this, nblig, nbcol); /*Appel du constructeur de la classe Plateau pour créer un 
                                                   plateau avec le nombre de lignes et colonnes souhaitées*/
        this.jeufini = false;
        this.coup = 1;
    }
    
    //Méthode permettant de traiter un tour de jeu
    public void traiterTour(){

        int lig,col; //Ligne et colonne de la case qui sera dévoilée

        this.getP().affichePlateau(); //Affichage du plateau
        System.out.println("Choisissez la case à dévoiler pour le coup " +this.coup);

        //Choix de la ligne
        System.out.print("Choisissez la ligne : ");
        lig = Lire.i();
        while(lig < 1 || lig > this.p.getNblig()){ //On vérifie si la valeur rentrée est valable
            System.out.print("La valeur n'est pas possible, veuillez réessayer : ");
            lig = Lire.i();
        }
        
        //Choix de la colonne
        System.out.print("Choisissez la colonne : ");
        col = Lire.i();
        while(col < 1 || col > this.p.getNbcol()){ //On vérifie si la valeur rentrée est valable
            System.out.print("La valeur n'est pas possible, veuillez réessayer : ");
            col = Lire.i();
        }
        
        this.coup += 1; //On incrémente le nombre de coup
        
        //La case est dévoilée avec la méthode Devoiler()
        this.getP().getCase(lig,col).devoiler(true);
        
        if(this.getP().getNbVides() == 0 && this.jeufini != true){ //Si le jeu est fini (il n'y a plus de case vide et aucune mine n'a été dévoilée)
            this.jeufini = true;
            System.out.print("Vous avez gagné en " + (this.coup-1) + " coups !");
            this.getP().affichePlateauFinal(); //Affichage du plateau dévoilée
        }
    }
    
    //Méthode qui lance et continue la partie tant que cela est possible
    public void joue(){
        
        //Avoir des informations sur le plateau (partie facultative pour la démonstration)
        String choix;
        System.out.print("Infos plateau ? ('O' pour oui ou 'N' pour non) : ");
        choix = Lire.S();
        while(choix.equals("O") != true && choix.equals("N") != true){ //On vérifie si la valeur rentrée est valable
            System.out.print("Lettre invalide, veuillez réessayer : ");
            choix = Lire.S();
        }
        if(choix.equals("O")){
            System.out.println("");
            System.out.println("Il y a " + this.getP().getNbMines() + " mines et " + this.getP().getNbVides() + " cases vides");
            this.getP().affichePlateauDevoile();
        }
        
        //Continuer la partie tant que cela est possible
        System.out.println("Il y a " + this.getP().getNbMines() + " mines ");
        while(jeufini != true){
            traiterTour();
            
            //Avoir des informations sur le plateau (partie facultative pour la démonstration)
            /*if(jeufini != true){
                System.out.print("Infos plateau ? ('O' pour oui ou 'N' pour non) : ");
                choix = Lire.S();
                while(choix.equals("O") != true && choix.equals("N") != true){ //On vérifie si la valeur rentrée est valable
                    System.out.print("Lettre invalide, veuillez réessayer : ");
                    choix = Lire.S();
                }
                if(choix.equals("O")){
                    System.out.println("");
                    System.out.println("Il y a " + this.getP().getNbMines() + " mines " + this.getP().getNbVides() + " cases vides");
                    this.getP().affichePlateauDevoile();
                }
            }*/
        }    
    }
    
}
