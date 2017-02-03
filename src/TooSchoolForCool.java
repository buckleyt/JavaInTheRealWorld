import javax.swing.JFrame;

public class TooSchoolForCool extends JFrame{
	
	public TooSchoolForCool() {
		super("Too Cool For School");
		this.setSize(new java.awt.Dimension(7000, 10000));
		this.setMinimumSize(this.getSize());
		this.setMaximumSize(this.getSize());
		this.setVisible(true);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
	public static void main(String[] args) {
		TooSchoolForCool f = new TooSchoolForCool();
		Grapher p = new Grapher("(x)");
		f.add(p);
		p.repaint();
	}
	
}
