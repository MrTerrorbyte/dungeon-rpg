package dungeonRPGPackage;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class BattlePanel extends JPanel implements ActionListener {
	
	Hero hero;
	Monster monster;
	private JLabel heroImage, monsterImage, heroName, monsterName, heroHP, monsterHP, monsterLevel;
	private JButton attackButton, potionButton;
	
	/**
	 * initializes all the JLabels and JButtons
	 * 
	 * @param hero : the hero that we are loading
	 * @param monster : the monster that we are loading
	 */
	public BattlePanel(Hero hero, Monster monster){
		this.hero = hero;
		this.monster = monster;
		this.setLayout(null);
		heroImage = new JLabel();
		heroImage.setIcon(new ImageIcon(resize(hero.getImage(), 150, 150)));
		
		monsterImage = new JLabel();
		monsterImage.setIcon(new ImageIcon(resize(monster.getImage(), 150, 150)));
		
		heroName = new JLabel();
		heroName.setText(hero.getName());
		heroName.setFont(new Font(null, 1, 20));
		
		monsterName = new JLabel();
		monsterName.setText(monster.getName());
		monsterName.setFont(new Font(null, 1, 20));
		
		heroHP = new JLabel();
		heroHP.setText("HP: "+(int)hero.getCurrHealth()+"/"+(int)hero.getMaxHealth());
		
		monsterHP = new JLabel();
		monsterHP.setText("HP: "+(int)monster.getCurrHealth()+"/"+(int)monster.getMaxHealth());
		
		monsterLevel = new JLabel();
		monsterLevel.setText("Lv"+monster.getLevel());
		
		attackButton = new JButton("Attack");
		attackButton.addActionListener(this);
		potionButton = new JButton(hero.getPotionCount()+"x potions left");
		potionButton.addActionListener(this);
		
		initPositions();
		addLabels();
	}
	
	/**
	 * adds all the labels and buttons to the JPanel
	 */
	public void addLabels(){
		this.add(heroImage);
		this.add(monsterImage);
		this.add(heroName);
		this.add(monsterName);
		this.add(heroHP);
		this.add(monsterHP);
		this.add(monsterLevel);
		this.add(attackButton);
		this.add(potionButton);
	}
	
	/**
	 * Sets the sizes and positions for the labels and buttons
	 */
	public void initPositions(){
		monsterName.setBounds(30, 30, 200, 20);
		monsterLevel.setBounds(50, 50, 2000, 20);
		monsterHP.setBounds(50, 70, 200, 20);
		monsterImage.setBounds(400, 50, 150, 150);
		
		heroName.setBounds(400, 300, 200, 20);
		heroHP.setBounds(420, 320, 200, 20);
		heroImage.setBounds(30, 320, 150, 150);
		
		attackButton.setBounds(30, 500, 150, 30);
		potionButton.setBounds(200, 500, 150, 30);
	}
	
	/**
	 * resizes a buffered image
	 * 
	 * @param image : the image to resize
	 * @param width : the new width
	 * @param height : the new height
	 * @return the new image
	 * cite: http://stackoverflow.com/questions/14548808/scale-the-imageicon-automatically-to-label-size
	 * by David Kroukamp
	 */
	public static BufferedImage resize(BufferedImage image, int width, int height) {
	    BufferedImage bi = new BufferedImage(width, height, BufferedImage.TRANSLUCENT);
	    Graphics2D g2d = (Graphics2D) bi.createGraphics();
	    g2d.addRenderingHints(new RenderingHints(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY));
	    g2d.drawImage(image, 0, 0, width, height, null);
	    g2d.dispose();
	    return bi;
	}

	/**
	 * After clicking on a button, the labels have to be updated
	 * to reflect the new data.
	 */
	public void updateLabels(){
		heroHP.setText("HP: "+(int)hero.getCurrHealth()+"/"+(int)hero.getMaxHealth());
		
		monsterHP.setText("HP: "+(int)monster.getCurrHealth()+"/"+(int)monster.getMaxHealth());
		
		monsterLevel.setText("Lv"+monster.getLevel());
		
		potionButton.setText(hero.getPotionCount()+"x potions left");
		
		heroImage.setLocation(30, 320);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(attackButton.equals(e.getSource())){
			Battle.attack(hero, monster, true);
			
			Battle.attack(hero, monster, false);
			updateLabels();
			
		}
		else if(potionButton.equals(e.getSource())){
			Battle.usePotion(hero, new Potion("", "", 0.5));
			updateLabels();
			
			try {
				Thread.sleep(500);
			} catch (InterruptedException e1) {
				e1.printStackTrace();
			}
			
			Battle.attack(hero, monster, false);
			updateLabels();
		}
	}
}