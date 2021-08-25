import java.awt.Color;
import java.awt.Graphics;

public class Player {

	public boolean right, left;
	
	public int x, y;
	
	public int width, height;
	
	public Player(int x, int y) {
		this.x = x;
		this.y = y;
		this.width = 40;
		this.height = 5;
	}
	
	public void tick() {
		if(right) { // aumentar posição x (movimento)
			x++;
		}else if(left) { // diminuir posição x (movimento)
			x--;
		}
		
		if(x+width > Game.width) { // Limitar movimento para direita
			x = Game.width-width;
		}else if(x<0) { // Limitar movimento para esquerda
			x=0;
		}
		
	}
	
	public void render(Graphics g) { // Renderiza o Player
		g.setColor(Color.blue); //Define a cor do player
		g.fillRect(x, y, width, height); // Define tamanho do player
	}
	
	
	
}
