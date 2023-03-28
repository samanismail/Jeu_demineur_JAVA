/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package demineur.src;

/**
 *
 * @author thoar
 */
public class Demineur {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        Jeu j; //Variable qui contiendra l'instance Jeu (donc le plateau) pour la partie
        int lig, col, nv;
        
        System.out.println("Veuillez choisir la taille du plateau : ");
        
        //Choix des différentes options pour le jeu
        
        //Nombre de lignes du plateau
        System.out.print("Nombre de lignes (compris entre 1 et 99) : ");
        lig = Lire.i();
        while(lig < 1 || lig > 99){
            System.out.print("La valeur n'est pas possible, veuillez réessayer : ");
            lig = Lire.i();
        }
        
        //Nombre de colonnes du plateau
        System.out.print("Nombre de colonnes (compris entre 1 et 99) : ");
        col = Lire.i();
        while(col < 1 || col > 99){
            System.out.print("La valeur n'est pas possible, veuillez réessayer : ");
            col = Lire.i();
        }
        
        //Niveau de difficulté du jeu
        System.out.print("Veuillez choisir le niveau de jeu (Compris entre 1 et 9) : ");
        nv = Lire.i();
        while(nv < 1 || nv > 9){
            System.out.print("La valeur n'est pas possible, veuillez réessayer : ");
            nv = Lire.i();
        }
        
        j = new Jeu(nv, lig, col); //Création d'une instance Jeu avec ces paramètres
        j.joue(); //Lancement de la partie
        
    }
}
