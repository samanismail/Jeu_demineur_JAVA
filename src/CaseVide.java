/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package demineur.src;

/**
 *
 * @author thoar
 */
public class CaseVide extends Case{
    
    //Constructeur de la classe (le type est "Vide")
    public CaseVide(Plateau p, int lig, int col){
        super(p, lig, col, "Vide");
    }
    
    //Méthode Devoiler() pour une case vide.
    public void devoiler(boolean ok){
        if(this.getActive() && ok == true){ //Si le joueur veut dévoiler (c'est-à-dire ok == true) une case qui est déjà dévoilée
            
            System.out.println("La case a déjà été dévoilé, veuillez en choisir une autre " +getLig()+":"+getCol());
            
        }
        else{
            
            this.setActive(true);
            
            //Récupération des coordonnées de la case
            int lig = this.getLig();
            int col = this.getCol();
            //Récupération du plateau de la partie
            Plateau p = this.getP();

            if(this.nbMinesVoisines() == 0){//Si cette case n'a aucune mine dans les cases voisines
                
                if(!this.getType().equals(" "))//Si c'est une case qui n'a pas encore été dévoilée, que ce soit par son voisinage ou le joueur
                    this.getP().setNbVides(this.getP().getNbVides() - 1); //Le nombre de case vide à dévoiler est décrémenté de 1
                
                this.setType(" "); //On n'affiche rien dans la case dévoilée à l'origine car il n'y a aucune mine à proximité

                //On va dévoiler les cases adjacentes
                for(int i = lig - 1; i <= lig + 1; i++){
                     for(int j = col - 1; j <= col + 1; j++){
                        if(i>0 && j>0 && i<=this.getP().getNblig() && j <=this.getP().getNbcol()){ //On vérifie que nous restons dans les limites du plateau
                            CaseVide c = (CaseVide)(p.getCase(i,j)); //Récupération de la case

                            if(c.getActive()!=true){ //Si la case n'est pas une case invisible (bord du plateau) ou une case déjà activée

                                c.setActive(true); //La case ayant été dévoilée, le booléen de cette case est mis à true

                                if(c.nbMinesVoisines() == 0){

                                    c.setType(" "); //Si la case n'a aucune mine dans les cases voisines, alors il n'y a pas de nombre affiché 
                                    this.getP().setNbVides(this.getP().getNbVides() - 1); //Le nombre de case vide à dévoiler est décrémenté de 1
                                    c.devoiler(false); /*Rappel de la méthode par récursivité sur cette case
                                                        Le booléen est false car ce n'est pas un joueur qui cherche à dévoiler la case
                                                        ce qui évite d'avoir des erreurs lorsque l'on souhaite dévoiler des cases à proximité
                                                        de cases déjà dévoilées
                                                        */

                                }
                                else{
                                    
                                     c.setType(""+c.nbMinesVoisines()); //Sinon, le nombre de mines aux alentours est affiché
                                     this.getP().setNbVides(this.getP().getNbVides() - 1); //Le nombre de case vide à dévoiler est décrémenté de 1
                                
                                }
                            }
                        }
                    }

                }

            }
            else{//Si cette case a au moins une mine à proximité

                this.setType(""+this.nbMinesVoisines()); //On affiche le nombre de mines adjacentes
                this.getP().setNbVides(this.getP().getNbVides() - 1); //Le nombre de case vide à dévoiler est décrémenté de 1

            }
        }    
    }
    
    //Méthode renvoyant le nombre de case mine à proximité de la case vide
    public int nbMinesVoisines(){
        
        int compteur = 0; //Compteur pour le nombre de mines proches
        
        //Récupération des coordonnées de la case
        int lig = this.getLig();
        int col = this.getCol();
        Plateau p = this.getP();
        
        //Si l'une des cases adjacentes est une mine, on incrémente le compteur
        for(int i = lig - 1; i <= lig + 1; i++){
            for(int j = col - 1; j <= col + 1; j++){
                if(p.getCase(i,j).getType().equals("Mine"))
                    compteur++ ;    
            }
        }
        
        return compteur;
        
    }
    
}
