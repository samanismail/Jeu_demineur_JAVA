/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package demineur.src;

/**
 *
 * @author thoar
 */
public abstract class Case {
    
    //Attributs de la classe
    private int lig; //Ligne de la case
    private int col; //Colonne de la case
    private Plateau p; //Plateau dans lequel se trouve la case
    private String type; //Type de la case (Mine ou Vide) utilisé aussi pour l'affichage
    private boolean active; //Booléen permettant de savoir si la case a déjà été dévoilée 

    //Accesseurs des attributs
    public void setType(String type) {
        this.type = type;
    }
    
    public int getLig() {
        return lig;
    }

    public int getCol() {
        return col;
    }

    public Plateau getP() {
        return p;
    }

    public void setLig(int lig) {
        this.lig = lig;
    }

    public void setCol(int col) {
        this.col = col;
    }

    public void setP(Plateau p) {
        this.p = p;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
    
    public String getType() {
        return type;
    }
    
    public boolean getActive(){
        return this.active;
    }
    
    //Constructeur de la case 
    public Case(Plateau p, int lig, int col, String type){
        setLig(lig);
        setCol(col);
        setP(p);
        this.type = type;   
    }
    
    public abstract void devoiler(boolean ok); //Méthode commune aux deux sous-classes CaseVide et CaseMine mais avec des conséquences différentes
    
}
