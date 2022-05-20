package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener{
	
	public Boolean ZKey;
	public Boolean QKey;
	public Boolean SKey;
	public Boolean DKey;
	public Boolean openInventory;
	public Boolean wantToHit;
	public Boolean wantToHitFar;
	public Boolean lookLeft;
	public Boolean wantToPick;
	public Boolean wantToUsePotion;
	
	public KeyHandler() {
		ZKey = false;
		QKey = false;
		SKey = false;
		DKey = false;
		wantToHit = false;
		wantToHitFar = false;
		openInventory = false;
		wantToPick = false;
		lookLeft = false;
		wantToUsePotion = false;
	}
	
	@Override
	public void keyTyped(KeyEvent e) {
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// r�cup�re le code du boutton appuy�
		int code = e.getKeyCode();
		if (code == 90 || code == 38) {
			ZKey = true;
		}else if (code == 81 || code == 37) {
			QKey = true;
			lookLeft = true;
		}else if (code == 83 || code == 40) {
			SKey = true;
		}else if (code == 68 || code == 39) {
			DKey = true;
			lookLeft = false;
		}
		
		if (code == 32) {
			wantToHit = true;
		}
		
		if (code == 67) {
			wantToHitFar = true;
		}
		
		if (code == 73) {
			openInventory = true;
		}
		
		if (code == 69) {
			wantToPick = true;
		}

		if (code == 80) {
			wantToUsePotion = true;
		}
		//System.out.println(code);
	}

	@Override
	public void keyReleased(KeyEvent e) {
		int code = e.getKeyCode();
		if (code == 90 || code == 38) {
			ZKey = false;
		}else if (code == 81 || code == 37) {
			QKey = false;
		}else if (code == 83 || code == 40) {
			SKey = false;
		}else if (code == 68 || code == 39) {
			DKey = false;
		}
	}

}
