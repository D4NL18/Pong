import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;

public class Game extends Canvas implements Runnable, KeyListener{


	private static final long serialVersionUID = 1L;
	public static int width = 160;
	public static int height = 120;
	public static int scale = 3;
	
	public BufferedImage layer = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
	
	public static Player player;
	public static Enemy enemy;
	public static Ball ball;
	
	public Game() {
		this.setPreferredSize(new Dimension (width*scale, height*scale)); //Define tamanhos padrão
		this.addKeyListener(this);
		
		player = new Player(100, height-5); // instancia player
		enemy = new Enemy(100, 0);
		ball = new Ball(100, height/2 - 1);
		
	}
	
	public static void main(String[] args) {
		Game game = new Game();
		JFrame frame = new JFrame("Pong"); //Criar Janela
		frame.setResizable(false); // Não pode alterar o tamanho da janela
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Ao fechar, não rodar em segundo plano
		frame.add(game); //adiciona componente
		frame.pack();
		frame.setLocationRelativeTo(null); //Coloca a janela no centro da tela
		frame.setVisible(true); //Faz a janela aparecer
		new Thread(game).start();
	}
	
	public void tick() {
		player.tick();
		enemy.tick();
		ball.tick();
	}
	
	public void render() { // Renderiza o jogo inteiro
		BufferStrategy bs = this.getBufferStrategy();
		if(bs == null) {
			this.createBufferStrategy(3);
			return;
		}
		Graphics g = layer.getGraphics();
		g.setColor(Color.black); 
		g.fillRect(0, 0, width, height); // Atualiza backgroud quando movimenta
		player.render(g); //renderiza jogador
		enemy.render(g); //renderiza o inimigo
		ball.render(g); // renderiza a bola
		
		g = bs.getDrawGraphics();
		g.drawImage(layer, 0, 0, width*scale, height*scale, null); // Cria background
		
		bs.show();
	}
	
	
	
	@Override
	public void run() {
		
		while(true) {
			tick();
			render();
			try {
				Thread.sleep(1000/60); // Loop 60 FPS
			} catch (InterruptedException e) {
				
				e.printStackTrace();
			}
		}
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) { //Ações ao clicar em algo
		if(e.getKeyCode() == KeyEvent.VK_RIGHT) { // Se a tecla apertada for igual à seta direita
			player.right = true; 
		}else if(e.getKeyCode() == KeyEvent.VK_LEFT){// Se a tecla apertada for igual à seta esquerda
			player.left = true;
		}
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_RIGHT) { // Se a tecla solta for igual à seta direita
			player.right = false; 
		}else if(e.getKeyCode() == KeyEvent.VK_LEFT){// Se a tecla solta for igual à seta esquerda
			player.left = false;
		}
		
	}

}
