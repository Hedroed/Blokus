package ihm;

import controlleur.*;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;


/**
*JPanel contenant toutes les options et reglages.
*/
public class FenetreOptions extends AbstractPanneau{
	
	
	private Rectangle rec2;
	private Image items;
	private Image[] resolutions;
	private int indReso;
	
	private Image itemsH;
	private Image bar;
	private Image curseur;
	
	private int tailleBar;
	private int coordBar;
	
	private int volume1;
	private int volume2;
	
	private JButton retour;
	private JButton bouton;
	
	private Image background;
	/**
	*Le constructeur de la classe.
	*@Param m , le Moteur du jeu.
	*/
	public FenetreOptions(Controlleur c) {
		super(c);
		background = Fenetre.loadImage("options/background.png");
		
		items = Fenetre.loadImage("options/retour.png");
		
		
		
		resolutions = new Image[5];
		resolutions[0] = Fenetre.loadImage("options/resolution0.png");
		resolutions[1] = Fenetre.loadImage("options/resolution1.png");
		resolutions[2] = Fenetre.loadImage("options/resolution2.png");
		resolutions[3] = Fenetre.loadImage("options/resolution3.png");
		resolutions[4] = Fenetre.loadImage("options/resolution4.png");
		indReso=0;
		
		itemsH = Fenetre.loadImage("options/retourOver.png");
		bar = Fenetre.loadImage("options/bar.png");
		curseur = Fenetre.loadImage("options/curseur.png");
		
		
		setLayout(null);
		creeBoutons();
		
		setMusique(50);
		setSon(50);
		
		tailleBar=(int)bar.getWidth(null);
		coordBar= (int)((Fenetre.WIDTH)/2-(int)((bar.getWidth(null))/2));
		
		VolumeControlleur vc = new VolumeControlleur(this);
		addMouseListener(vc);
		addMouseMotionListener(vc);
		
	}
	
	/**
	  * This think do its job
	  */
	public void calculePositions() {
		
		System.out.println("calculePositions de fenetre options");
		
		fond = background.getScaledInstance(Fenetre.WIDTH,Fenetre.HEIGHT, Image.SCALE_SMOOTH);
		
		coordBar= (int)((Fenetre.WIDTH)/2-(int)((bar.getWidth(null))/2));
		
		double x = (Fenetre.WIDTH-500)/2;
		double y = (int)(Fenetre.HEIGHT);
		double heigth = (int)(Fenetre.HEIGHT*0.16);
		double scaleH = Fenetre.HEIGHT/1080d;
		double scaleW = Fenetre.WIDTH/1920d;
		
		
		int w = (int)(items.getWidth(null)*scaleW);
		int h = (int)(items.getHeight(null)*scaleH);
		
		Rectangle rec1 = new Rectangle((int)x+((500-w)/2),(int)(y*0.84),w,h);
		
		Image iconN = items.getScaledInstance(rec1.width, rec1.height, Image.SCALE_SMOOTH);
		Image iconO = itemsH.getScaledInstance(rec1.width, rec1.height, Image.SCALE_SMOOTH);
		
		retour.setRolloverIcon(new ImageIcon(iconO));
		retour.setIcon(new ImageIcon(iconN));
		retour.setBounds(rec1);
		
		
		w = (int)(resolutions[0].getWidth(null)*scaleW);
		h = (int)(resolutions[0].getHeight(null)*scaleH);
		
		rec2 = new Rectangle((int)x+((500-w)/2),(int)(y*0.673),w,h);
		
		iconN = resolutions[indReso].getScaledInstance(rec2.width, rec2.height, Image.SCALE_SMOOTH);
		bouton.setIcon(new ImageIcon(iconN));
		
		bouton.setBounds(rec2);
		
	}
	
	private void changeResolution(){
		indReso++;
		if(indReso>=resolutions.length){
			indReso=0;
		}
		retour.setActionCommand("retourOptions");
		Image iconN = resolutions[indReso].getScaledInstance(rec2.width, rec2.height, Image.SCALE_SMOOTH);
		bouton.setIcon(new ImageIcon(iconN));
		repaint();
		System.out.println(indReso+" <-indReso");
	}
	
	/**
	  * This think do its job
	  */
	private void creeBoutons() {
		
			
			
			bouton = new JButton();
			bouton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent a){
					changeResolution();
					System.out.println("tu as appuye sur le bouton");
				}
			});
			bouton.setOpaque(false);
			bouton.setContentAreaFilled(false);
			bouton.setFocusable(false);
			bouton.setBorderPainted(false);
			
			
			this.add(bouton);
			
			retour = new JButton();
			retour.setActionCommand("retourOptions");
			retour.setOpaque(false);
			retour.setContentAreaFilled(false);
			retour.setFocusable(false);
			retour.setBorderPainted(false);
			retour.addActionListener(control);
			
			
			this.add(retour);
		
		
		
	}
	
	/**
	*Change le volume de la musique
	**/
	public void setMusique(int volume){
		if(volume>=0 && volume <=100){
			volume1=volume;
		}
		repaint();
	}
	
	/**
	*Change le volume du son
	**/
	public void setSon(int volume){
		if(volume>=0 && volume <=100){
			volume2=volume;
		}
		repaint();
	}
	
	/**
	*Retourne le volume de la musique
	**/
	public int getMusique(){
		return volume1;
	}
	
	/**
	*Retourne le volume du son
	**/
	public int getSon(){
		return volume2;
	}
	
	/**
	*Retourne la dimensiion
	*/
	public Dimension getResolution(){
		Dimension ret=null;
		if(indReso==0){
			ret= Toolkit.getDefaultToolkit().getScreenSize();
		}
		else if(indReso==1){
			ret= new Dimension(1280,720);
		}
		else if(indReso==2){
			ret= new Dimension(1366,768);
		}
		else if(indReso==3){
			ret= new Dimension(1600,900);
		}
		else if(indReso==4){
			ret= new Dimension(1920,1080);
		}
		return ret;
	}
	
	/**
	*Retourne si on est en plein ecran
	*/
	public boolean estPleinEcran(){
		return (indReso==0);
	}
	
	public void resetBouton() {
		if(retour != null) {
			retour.setActionCommand(Fenetre.MENU);
		}
	}
	
	public int getTailleBar(){
		return tailleBar;
	}
	
	public int getCoordBar(){
		return coordBar;
	}
	
	
	/**
	*Lorsque l'on entre dans cet ecran
	*
	*/
	public void entree() {
		
	}
	
	/**
	*Lorsque l'on quitte cet ecran
	*/
	public void sortie() {}

	/**
	 * Detecte les clics effectues sur ce JPanel
	 * @param e
	 */
	public void mousePressed(MouseEvent e) {}
	
	/**
	  * This think do its job
	  */
	public void paintComponent(Graphics g) {
		g.drawImage(fond,0,0,null);
		
		g.drawImage(bar,coordBar,(int)(Fenetre.HEIGHT*0.395),null);
		
		g.drawImage(bar,coordBar,(int)(Fenetre.HEIGHT*0.625),null);
		
		g.drawImage(curseur,(int)(volume1*(tailleBar/100)+(Fenetre.WIDTH)/2-(int)((tailleBar)/2)),(int)((Fenetre.HEIGHT*0.398)-(int)(curseur.getHeight(null)/2)),null);
		
		g.drawImage(curseur,(int)(volume2*(tailleBar/100)+(Fenetre.WIDTH)/2-(int)((tailleBar)/2)),(int)((Fenetre.HEIGHT*0.628)-(int)(curseur.getHeight(null)/2)),null);
		
	}
}