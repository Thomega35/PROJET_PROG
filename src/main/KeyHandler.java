package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener{
	
	public Boolean ZKey = false;
	public Boolean QKey = false;
	public Boolean SKey = false;
	public Boolean DKey = false;
	
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// récupère le code du boutton appuyé
		int code = e.getKeyCode();
		if (code == 90) {
			ZKey = true;
		}else if (code == 81) {
			QKey = true;
		}else if (code == 83) {
			SKey = true;
		}else if (code == 68) {
			DKey = true;
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		int code = e.getKeyCode();
		if (code == 90) {
			ZKey = false;
		}else if (code == 81) {
			QKey = false;
		}else if (code == 83) {
			SKey = false;
		}else if (code == 68) {
			DKey = false;
		}
	}

}
