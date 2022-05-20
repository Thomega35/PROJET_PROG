package main;

import java.util.ArrayList;

import badGuys.Monsters;
import entity.Entity;
import entity.Player;
import entityItem.Items;

public class Functions {
	GamePanel gp;
	public Functions(GamePanel gp) {
		// TODO Auto-generated constructor stub
		this.gp = gp;
	}

	public static Items giveMeFirstItem(ArrayList<Items> listeObjects, Entity me, double distance) {
		Items res = null;
		for (Items obj : listeObjects) {
			if ((res == null || me.distanceWidth(obj) < me.distanceWidth(res)) && me.distanceWidth(obj) < distance) {
				res = obj;
			}
		}
		return res;
	}
	
	public static Monsters giveMeFirstMonster(ArrayList<Monsters> listeMonster, Entity me, double distance) {
		Monsters res = null;
		for (Monsters obj : listeMonster) {
			if ((res == null || me.distanceWidth(obj) < me.distanceWidth(res)) && me.distanceWidth(obj) < distance) {
				res = obj;
			}
		}
		return res;
	}
	
	public Player giveMeFirstPlayer(Entity me, double distance) {
		Player obj = gp.player;
		if(me.distanceWidth(obj) < distance) {
			return obj;
		}
		return null;
	}
}
