package fr.istic.aoc.metronome.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import fr.istic.aoc.components.api.IAfficheur;
import fr.istic.aoc.components.api.IClavier;
import fr.istic.aoc.components.api.IEmetteurSonore;
import fr.istic.aoc.components.api.IHorloge;
import fr.istic.aoc.components.api.IMolette;
import fr.istic.aoc.metronome.controller.IController;
import fr.istic.aoc.metronome.controller.IControllerListener;

/**
 * @author Jimmy
 */
public class Metronome extends JPanel implements IView, IControllerListener {

	private static final long serialVersionUID = 1L;
	private static final String LED_1 = "TEMPO";
	private static final String LED_2 = "MESURE";
	private static final String START_BTN = "Start";
	private static final String STOP_BTN = "Stop";
	private static final String INC_BTN = "Inc";
	private static final String DEC_BTN = "Dec";

	private IController controller;

	private JSlider tempoSlider;

	private ImageIcon redLedIcon;
	private ImageIcon blueLedIcon;
	private ImageIcon offLedIcon;
	
	private JTextField displayLabel;
	private JLabel[] ledLabels;

	private MyButton[] cmdButtons;
	
	private MyButton startBtn; 
	private MyButton stopBtn;	
	private MyButton incBtn;	
	private MyButton decBtn;	
	
	private int minBPM;
	private int maxBPM;

	public Metronome() {

	}

	public void init(){
		initComponents();
		configureView();
		defineCallbacks();
	}
	
	private void initComponents() {
		tempoSlider = new JSlider(JSlider.VERTICAL);
		
		blueLedIcon = new ImageIcon(getClass().getResource("/images/bleu.png"));
		redLedIcon = new ImageIcon(getClass().getResource("/images/rouge.png"));
		offLedIcon = new ImageIcon(getClass().getResource("/images/blanc.png"));
		
		displayLabel = new JTextField();
		displayLabel.setEditable(false);
		displayLabel.setSize(new Dimension(25,25));
		displayLabel.setPreferredSize(new Dimension(25,25));
		ledLabels = new JLabel[2];
		ledLabels[IAfficheur.TEMPO] = new JLabel(LED_1, offLedIcon, JLabel.RIGHT);
		ledLabels[IAfficheur.MEASURE] = new JLabel(LED_2, offLedIcon, JLabel.RIGHT);

		startBtn = new MyButton(START_BTN);
		stopBtn = new MyButton(STOP_BTN);
		incBtn = new MyButton(INC_BTN);
		decBtn = new MyButton(DEC_BTN);
		
		cmdButtons = new MyButton[4];
		cmdButtons[IClavier.START] = startBtn;
		cmdButtons[IClavier.STOP] = stopBtn;
		cmdButtons[IClavier.INC] = incBtn;
		cmdButtons[IClavier.DEC] = decBtn;
	}

	private void configureView() {
		setLayout(new BorderLayout());
		
		// Turn on labels at major tick marks.
		tempoSlider.setMajorTickSpacing(100);
		tempoSlider.setMinorTickSpacing(10);
		tempoSlider.setPaintTicks(true);
		tempoSlider.setPaintLabels(true);
		tempoSlider.setBorder(new EmptyBorder(0, 10, 0, 0));
		
		displayLabel.setHorizontalAlignment(SwingConstants.CENTER);

		JPanel ledsPanel = new JPanel(new GridLayout(1, 2));
		ledsPanel.setBorder(new EmptyBorder(0, 0, 0, 10));
		ledsPanel.add(ledLabels[0]);
		ledsPanel.add(ledLabels[1]);
		
		JPanel btnsPanel = new JPanel(new GridLayout(1, 4));
		btnsPanel.add(startBtn);
		btnsPanel.add(stopBtn);
		btnsPanel.add(incBtn);
		btnsPanel.add(decBtn);

		add(tempoSlider, BorderLayout.WEST);
		add(displayLabel, BorderLayout.NORTH);
		add(ledsPanel, BorderLayout.CENTER);
		add(btnsPanel, BorderLayout.SOUTH);
	}

	private void defineCallbacks() {
		tempoSlider.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent evt) {
				controller.tempo();
			}
		});
		
		startBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				controller.start();
			}
		});
		
		stopBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				controller.stop();
			}
		});
		
		incBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				controller.inc();
			}
		});
		
		decBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				controller.dec();
			}
		});
	}

	public void setController(IController c){
		controller = c;
	}
	
	protected void postExecute(TimerTask task, int delay) {
		Timer timer = new Timer();
		timer.schedule(task, delay);
	}
	
	public void setTempoConstants(int min, int max, int init) {
		tempoSlider.setMinimum(min);
		tempoSlider.setMaximum(max);
		tempoSlider.setValue(init);
		onTempoChanged(init);
	}
	
	public void setBPMConstants(int minBpm, int maxBpm, int defaultBpm) {
		minBPM = minBpm;
		maxBPM = maxBpm;
	}
	
	public void onStart(int tempo, int bpm) {
		startBtn.setEnabled(false);
		stopBtn.setEnabled(true);
		
		onTempoChanged(tempo);
		onBPMChanged(bpm);
	}
	
	public void onStop() {
		startBtn.setEnabled(true);
		stopBtn.setEnabled(false);
		incBtn.setEnabled(false);
		decBtn.setEnabled(false);
	}

	public void onBPMChanged(int bpm) {
		if (bpm > minBPM) decBtn.setEnabled(true);
		else decBtn.setEnabled(false);
		
		if (bpm < maxBPM) incBtn.setEnabled(true);
		else incBtn.setEnabled(false);
	}

	public void onTempoChanged(int tempo) {
		displayLabel.setText(String.valueOf(tempo)+ " b/min");
	}
		
	public void allumerLed(int id) {
		switch (id) {
			case IAfficheur.TEMPO:
				ledLabels[id].setIcon(redLedIcon);
				break;
				
			case IAfficheur.MEASURE:
				ledLabels[id].setIcon(blueLedIcon);
				break;
			
			default:
				System.err.println("Not cool");
				break;
		}
	}

	public void eteindreLed(int id) {
		ledLabels[id].setIcon(offLedIcon);
	}

	public void afficherTempo(int value) {
		displayLabel.setText(String.valueOf(value));		
	}

	public boolean touchePressee(int id) {
		return cmdButtons[id].isPressed();
	}

	public float position() {
		return tempoSlider.getValue();
	}

	public void emettreClick() {
		Toolkit.getDefaultToolkit().beep();
	}

	public IClavier getClavier() {
		return this;
	}

	public IHorloge getHorloge() {
		return null;
	}

	public IAfficheur getAfficheur() {
		return this;
	}

	public IEmetteurSonore getEmetteur() {
		return this;
	}

	public IMolette getMolette() {
		return this;
	}
}
