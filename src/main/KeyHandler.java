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
	public Boolean lookLeft;
	
	public KeyHandler() {
		ZKey = false;
		QKey = false;
		SKey = false;
		DKey = false;
		wantToHit = false;
		openInventory = false;
		lookLeft = false;
	}
	
	@Override
	public void keyTyped(KeyEvent e) {
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// récupère le code du boutton appuyé
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
		
		if (code == 73) {
			openInventory = true;
		}
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
