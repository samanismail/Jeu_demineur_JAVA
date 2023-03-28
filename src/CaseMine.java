/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package demineur.src;

/**
 *
 * @author thoar
 */
public class CaseMine extends Case{
    
    //Constructeur de la classe (le type est "Mine")
    public CaseMine(Plateau p, int lig, int col){
        super(p, lig, col, "Mine");
    }
    
    //Méthode Devoiler() pour une case mine
    public void devoiler(boolean ok){
        this.getP().getJ().setJeufini(true); //Une mine a été dévoilée donc le jeu est terminé
        System.out.println("Vous avez perdu !");
        this.getP().affichePlateauFinal(); //Affichage du plateau final car partie terminée
    }
    
}
