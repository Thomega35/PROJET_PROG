package entity;


import java.util.ArrayList;
 
public class Inventaire {
	public ArrayList<objets> inventaire;
	
	public Inventaire() {
		inventaire=new ArrayList<objets>();}
	
	void push_back(objets Obj) {
		inventaire.add(Obj);
	}
	
	
}
