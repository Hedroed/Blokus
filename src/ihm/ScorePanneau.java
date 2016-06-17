package ihm;

import controlleur.*;

import java.util.*;
import java.awt.event.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import javax.swing.*;
import javax.imageio.ImageIO;

public class ScorePanneau extends AbstractPanneau{

	private JButton boutonRejouer;
	private JButton boutonQuitter;
	
	private Image background;
	
	private Image imgBoutonRejouer;
	private Image imgBoutonQuitter;
	private Image imgBoutonRejouerH;
	private Image imgBoutonQuitterH;
	
	private String[] nomsJoueurs;
	private Color[] couleursJoueurs;
	private int[] scores;
	
	private Font laPolice;
	
	/**
	  * Constructeur
	  * @param c le Controlleur
	  */
	public ScorePanneau(Controlleur c){
		super(c);
		
		try {
			laPolice = Font.createFont(Font.TRUETYPE_FONT,ClassLoader.getSystemResourceAsStream("Electronica.ttf"));
		}
		catch(IOException | FontFormatException e) {
			e.printStackTrace();
		}
		laPolice = laPolice.deriveFont(50f);
		
		background = Fenetre.loadImage("score/background.png");
		
		
		imgBoutonRejouer = Fenetre.loadImage("score/rejouer.png");
		imgBoutonQuitter = Fenetre.loadImage("score/quitter.png");
		
		
		imgBoutonRejouerH = Fenetre.loadImage("score/rejouerHover.png");
		imgBoutonQuitterH = Fenetre.loadImage("score/quitterHover.png");
		
		addMouseListener(new MouseAdapter() {});
		
		setLayout(null);
		setOpaque(false);
		creeBoutons();
		
		
		nomsJoueurs=new String[4];
		nomsJoueurs[0]="Bleu";
		nomsJoueurs[1]="Jaune";
		nomsJoueurs[2]="Rouge";
		nomsJoueurs[3]="Vert";
		
		scores=new int[4];
		scores[0]=89;
		scores[1]=30;
		scores[2]=29;
		scores[3]=62;
		
		couleursJoueurs=new Color[4];
		couleursJoueurs[0] = new Color(0,169,227);		//Bleu
		couleursJoueurs[1] = new Color(246, 255, 0);	//Jaune
		couleursJoueurs[2] = Color.red;					//Rouge
		couleursJoueurs[3] = new Color(30,254,0);		//Vert
		
	}
	
	
	public void calculePositions() {
		
		
			
			fond = background.getScaledInstance(Fenetre.WIDTH,Fenetre.HEIGHT, Image.SCALE_SMOOTH);
			
			System.out.println("calculePositions score");
			
			double x = (Fenetre.WIDTH-500)/2;
			double y = (int)(Fenetre.HEIGHT*0.160);
			double heigth = (int)(Fenetre.HEIGHT*0.16);
			double scaleH = Fenetre.HEIGHT/1080d;
			double scaleW = Fenetre.WIDTH/1920d;
			
			
				
				int w = (int)(imgBoutonRejouer.getWidth(null)*scaleW);
				int h = (int)(imgBoutonRejouer.getHeight(null)*scaleH);
				
				Rectangle rec = new Rectangle((int)x+((500-w)/2),(int)y,w,h);
				boutonRejouer.setBounds(rec);
				Image iconN = imgBoutonRejouer.getScaledInstance(rec.width, rec.height, Image.SCALE_SMOOTH);
				Image iconO = imgBoutonRejouerH.getScaledInstance(rec.width, rec.height, Image.SCALE_SMOOTH);
				boutonRejouer.setIcon(new ImageIcon(iconN));
				boutonRejouer.setRolloverIcon(new ImageIcon(iconO));
				
				
				
				rec = new Rectangle((int)x+((500-w)/2),(int)(Fenetre.HEIGHT*0.74),w,h);
				boutonQuitter.setBounds(rec);
				iconN = imgBoutonQuitter.getScaledInstance(rec.width, rec.height, Image.SCALE_SMOOTH);
				iconO = imgBoutonQuitterH.getScaledInstance(rec.width, rec.height, Image.SCALE_SMOOTH);
				boutonQuitter.setIcon(new ImageIcon(iconN));
				boutonQuitter.setRolloverIcon(new ImageIcon(iconO));
			
		
	}
	
	
	private void creeBoutons() {
				
				JButton b = new JButton();
				b.setOpaque(false);
				b.setContentAreaFilled(false);
				b.setFocusable(false);
				b.setBorderPainted(false);
				b.addActionListener(control);
				b.setActionCommand(Fenetre.SELECTEUR);
				
				boutonRejouer=b;
				this.add(b);
			
				b = new JButton();
				b.setOpaque(false);
				b.setContentAreaFilled(false);
				b.setFocusable(false);
				b.setBorderPainted(false);
				b.addActionListener(control);
				b.setActionCommand(Fenetre.MENU);
				
				boutonQuitter=b;
				this.add(b);
		
	}
	
	public void paintComponent(Graphics g) {
		
		
		g.setFont(laPolice);
		((Graphics2D)g).setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
		
		
		g.drawImage(fond,0,0,null);
		
		
		if(nomsJoueurs!=null){
			// triTableau();
			for(int i=0; i<nomsJoueurs.length ;i++){
				
					g.setColor(couleursJoueurs[i]);
					String phrase= nomsJoueurs[i]+" :  "+scores[i];
					int len = g.getFontMetrics().stringWidth(phrase);
					g.drawString(phrase,(int)(Fenetre.WIDTH-len)/2,(int)(Fenetre.HEIGHT*(0.37+i*0.1)));
				
			}
		}
	}
	
	public void sortie(){}
	public void entree(){}
	
	public static void main(String[] args){
		JFrame f = new JFrame();
		f.setSize(1280,720);
		ScorePanneau p = new ScorePanneau(null);
		f.add(p);
		f.setVisible(true);
		Fenetre.WIDTH=1600;
		Fenetre.HEIGHT=900;
		p.calculePositions();
		
		
	}
	
	public void setNoms(String[] noms){
		nomsJoueurs = noms;
		for(int i=0;i<nomsJoueurs.length;i++){
			System.out.println("le nom de "+i+" est  "+nomsJoueurs[i]);
		}
	}
	
	public void setScores(int[] scores){
		this.scores=scores;
		for(int i=0;i<scores.length;i++){
			System.out.println("le score de "+i+" est de "+scores[i]);
		}
	}
	
	public String[] getNoms(){
		return nomsJoueurs;
	}
	
	public int[] getScores(){
		return scores;
	}
	
	public void triTableau(){
		Color[] lesCouleurs = new Color[4];
		lesCouleurs[0] = new Color(0,169,227);		//Bleu
		lesCouleurs[1] = new Color(246, 255, 0);	//Jaune
		lesCouleurs[2] = Color.red;					//Rouge
		lesCouleurs[3] = new Color(30,254,0);		//Vert
		
		// couleursJoueurs= new Color[4];
		// couleursJoueurs[0] = new Color(0,169,227);		//Bleu
		// couleursJoueurs[1] = new Color(246, 255, 0);	//Jaune
		// couleursJoueurs[2] = Color.red;					//Rouge
		// couleursJoueurs[3] = new Color(30,254,0);		//Vert
		for(int i=0; i<nomsJoueurs.length ;i++){
			
			for(int j=0; j<nomsJoueurs.length ;j++){
				if(scores[i]>scores[j]){
					int temp = scores[j];
					scores[j]=scores[i];
					scores[i]= temp;
					
					Color tmp = lesCouleurs[j];
					lesCouleurs[j] = lesCouleurs[i];
					lesCouleurs[i] = tmp;
					
					String tp = nomsJoueurs[j];
					nomsJoueurs[j]=nomsJoueurs[i];
					nomsJoueurs[i]=tp;
					
					couleursJoueurs=lesCouleurs;
				}
			}
		}
	}
}