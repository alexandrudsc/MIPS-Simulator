package GUI;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Label;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.LineBorder;

import simulator.Simulator;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class MainView extends JFrame {

	Simulator sim;
	
	private JPanel contentPane;
	private JScrollPane instructionMemory;
	private JPanel panel;
	private JScrollPane dataMemory;
	private JPanel panel2;
	private JScrollPane registers;
	private JPanel panel3;
	private ArrayList<JLabel> instructionLabels;
        
        // live interpreter (one instr at the time)
        private Interpreter interpreter;
        
	private JPanel panel_1;
	/**
	 * @return the panel2
	 */
	public JPanel getPanel2() {
		return panel2;
	}

	/**
	 * @param panel2 the panel2 to set
	 */
	public void setPanel2(JPanel panel2) {
		this.panel2 = panel2;
	}

	/**
	 * @return the panel3
	 */
	public JPanel getPanel3() {
		return panel3;
	}

	/**
	 * @param panel3 the panel3 to set
	 */
	public void setPanel3(JPanel panel3) {
		this.panel3 = panel3;
	}

	/**
	 * @return the panel_1
	 */
	public JPanel getPanel_1() {
		return panel_1;
	}

	/**
	 * @param panel_1 the panel_1 to set
	 */
	public void setPanel_1(JPanel panel_1) {
		this.panel_1 = panel_1;
	}

	/**
	 * @return the panel_2
	 */
	public JPanel getPanel_2() {
		return panel_2;
	}

	/**
	 * @param panel_2 the panel_2 to set
	 */
	public void setPanel_2(JPanel panel_2) {
		this.panel_2 = panel_2;
	}

	private JPanel panel_2;
	private JFrame datapathFrame;
	public DataPath datapath;
	private JButton next;
	private JButton next_instruction;
	private JButton run;


	/**
	* Create the frame.
        * @param s instance of Simulator class. Manages execution of all instruction and visual aid
	*/
	public MainView(Simulator s) {
		this.sim = s;
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(600, 10, 870, 750);
                
                
                
                // create the menu bar
                createMenuBar();
                
		contentPane = new JPanel();
		contentPane.setLayout(null);
		setContentPane(contentPane);
		
		instructionLabels = new ArrayList<JLabel>();
		
                // create datapath with all components
		datapath = new DataPath();
		
		datapathFrame = new JFrame();
		datapathFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		datapathFrame.setBounds(10, 150, 1250, 700);
		datapathFrame.add(datapath);
		datapathFrame.setVisible(true);
		
                // label for memory panel
		Label instrLabel = new Label("Instrucțiuni");
                instrLabel.setBounds(10, 50, 100, 20);
                contentPane.add(instrLabel);
                
                // add instruction panel
		instructionMemory = new JScrollPane();
		instructionMemory.setBounds(10, 70, 190, 300);
		contentPane.add(instructionMemory);
                
		panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(0, 0, 0)));
		instructionMemory.setViewportView(panel);
		
                // label for memory panel
		Label memLabel = new Label("Memoria");
                memLabel.setBounds(220, 50, 100, 20);
                contentPane.add(memLabel);
                
                // add memory panel
		dataMemory = new JScrollPane();
		dataMemory.setBounds(220, 70, 110, 300);
		contentPane.add(dataMemory);
		
		panel2 = new JPanel();
		panel2.setBorder(new LineBorder(new Color(0, 0, 0)));
		dataMemory.setViewportView(panel2);
		
                // label for registers panel
                Label regLabel = new Label("Regiștri");
                regLabel.setBounds(350, 50, 100, 20);
                contentPane.add(regLabel);
                
		// add registers panel
		registers = new JScrollPane();
		registers.setBounds(350, 70, 190, 300);
		contentPane.add(registers);
		
		panel3 = new JPanel();
		panel3.setBorder(new LineBorder(new Color(0, 0, 0)));
		registers.setViewportView(panel3);
		
		panel_1 = new JPanel();
		panel3.add(panel_1);
		
		panel_2 = new JPanel();
		panel3.add(panel_2);
		
                // create button for next cycle
		next = new JButton("Ciclul urmator");
		next.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					sim.run_step();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		next.setBounds(10, 10, 120, 20);
		contentPane.add(next);
		
                // button for next instruction
		next_instruction = new JButton("Instrucțiunea următoare");
		next_instruction.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					sim.run_instruction();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		next_instruction.setBounds(150, 10, 180, 20);
		contentPane.add(next_instruction);
		
                // add button for execute all instruction
		run = new JButton("Executa tot");
		run.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					sim.run_all_instructions();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		run.setBounds(350, 10, 120, 20);
		contentPane.add(run);
		
                // label for interpreter panel
		Label interprLabel = new Label("Interpretor");
                interprLabel .setBounds(0, 400, 100, 20);
                contentPane.add(interprLabel );
                
                // add interpreter panel
                interpreter = new Interpreter();
                interpreter.setBounds(0, 420, this.getWidth() - 50, this.getHeight()/3);   
                contentPane.add(interpreter);
                
		panel3.setLayout(new GridLayout(1, 2, 0, 0));
		panel_1.setLayout(new GridLayout(32, 1, 0, 0));
		panel_1.add(new JLabel("$zero"));
		panel_1.add(new JLabel("$at"));
		panel_1.add(new JLabel("$v0"));
		panel_1.add(new JLabel("$v1"));
		panel_1.add(new JLabel("$a0"));
		panel_1.add(new JLabel("$a1"));
		panel_1.add(new JLabel("$a2"));
		panel_1.add(new JLabel("$a3"));
		panel_1.add(new JLabel("$t0"));
		panel_1.add(new JLabel("$t1"));
		panel_1.add(new JLabel("$t2"));
		panel_1.add(new JLabel("$t3"));
		panel_1.add(new JLabel("$t4"));
		panel_1.add(new JLabel("$t5"));
		panel_1.add(new JLabel("$t6"));
		panel_1.add(new JLabel("$t7"));
		panel_1.add(new JLabel("$s0"));
		panel_1.add(new JLabel("$s1"));
		panel_1.add(new JLabel("$s2"));
		panel_1.add(new JLabel("$s3"));
		panel_1.add(new JLabel("$s4"));
		panel_1.add(new JLabel("$s5"));
		panel_1.add(new JLabel("$s6"));
		panel_1.add(new JLabel("$s7"));
		panel_1.add(new JLabel("$t8"));
		panel_1.add(new JLabel("$t9"));
		panel_1.add(new JLabel("$k0"));
		panel_1.add(new JLabel("$k1"));
		panel_1.add(new JLabel("$gp"));
		panel_1.add(new JLabel("$sp"));
		panel_1.add(new JLabel("$fp"));
		panel_1.add(new JLabel("$ra"));
	}
	
	public void selectInstruction(int pc){
		for (JLabel l : instructionLabels){
			l.setForeground(Color.black);
		}
		instructionLabels.get(pc-1).setForeground(Color.red);
		repaint();
		revalidate();
	}
	
	public void fillMemory(byte[]memory){
		panel2.setLayout(new GridLayout(memory.length, 1, 0, 0));
		for(int i=0 ; i<memory.length;i++){
			JLabel l = new JLabel(memory[i] + "");
			l.setBorder(new LineBorder(new Color(0, 0, 0)));
			panel2.add(l);
		}
		dataMemory.remove(panel2);
		dataMemory.setViewportView(panel2);
		repaint();
	}
	
	public void fillRegisters(int[] registerValues){
		panel_2.removeAll();
		panel_2.setLayout(new GridLayout(32, 1, 0, 0));
		for(int i=0 ; i<registerValues.length;i++){
			JLabel l = new JLabel(registerValues[i] + "");
			l.setBorder(new LineBorder(new Color(0, 0, 0)));
			panel_2.add(l);
		}
		panel_2.repaint();
		panel3.remove(panel_2);
		panel3.add(panel_2);
		panel3.repaint();
		repaint();
		validate();
	}
	
	public void addInstructions(ArrayList<String> instructions){
		panel.setLayout(new GridLayout(instructions.size(), 1, 0, 0));
		for(int i=0 ; i<instructions.size();i++){
			JLabel l = new JLabel(instructions.get(i));
			panel.add(l);
			instructionLabels.add(l);
		}
		instructionMemory.remove(panel);
		instructionMemory.setViewportView(panel);
		repaint();
	}
	
	public void set_fetch_red(){
		set_to_black();
		datapath.getPc().setColor(Color.red);
		datapath.getAdder1().setColor(Color.red);
		datapath.getInstructionMemory().setColor(Color.red);
		datapath.repaint();
	}
	
	public void set_decode_red(){
		set_to_black();
		datapath.getRegisters().setColor(Color.red);
		datapath.repaint();
	}
	
	public void set_exec_red(){
		System.out.println("RED CALLED");
		set_to_black();
//		System.out.println(sim.get_current_instruction_format());
		switch(sim.get_current_instruction_format()){
		case 0:
			datapath.getAlu().setColor(Color.red);
			break;
		case 1:
			datapath.getAlu().setColor(Color.red);
			break;
		case 2:
			datapath.getAlu().setColor(Color.red);
			break;
		case 3:
			datapath.getAlu().setColor(Color.red);
			break;
		case 4:
			datapath.getAdder2().setColor(Color.red);
		case 5:
			datapath.getAdder2().setColor(Color.red);
		}
		datapath.repaint();
	}
	
	public void set_memory_red(){
		set_to_black();
		datapath.getDataMemory().setColor(Color.red);
		datapath.repaint();
	}
	
	public void set_write_back_red(){
		set_to_black();
		datapath.getRegisters().setColor(Color.red);
		datapath.repaint();
	}
	
        // set all drawings colors to black
	public void set_to_black(){
		datapath.getPc().setColor(Color.black);
		datapath.getAdder1().setColor(Color.black);
		datapath.getRegisters().setColor(Color.black);
		datapath.getInstructionMemory().setColor(Color.black);
		datapath.getDataMemory().setColor(Color.black);
		datapath.getShiftLeft().setColor(Color.black);
		datapath.getAlu().setColor(Color.black);
		datapath.getAdder2().setColor(Color.black);
		datapath.getSignExtend().setColor(Color.black);
		datapath.getMux1().setColor(Color.black);
		datapath.getMux2().setColor(Color.black);
		datapath.getMux3().setColor(Color.black);
		datapath.getMux4().setColor(Color.black);
		datapath.getMux5().setColor(Color.black);
	}
        
        // create menu bar for main frame
        private void createMenuBar() {

            JMenuBar menubar = new JMenuBar();

            JMenu file = new JMenu("Fisier");
            file.setMnemonic(KeyEvent.VK_F);
            
            // file->import menu item
            JMenuItem eMenuItem = new JMenuItem("Import");
            eMenuItem.setMnemonic(KeyEvent.VK_E);
            eMenuItem.setToolTipText("Import fisier instructiuni");
            eMenuItem.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent event) {
                    
                    
                }
            });
            file.add(eMenuItem);
            
            // file->exit menu item
            eMenuItem = new JMenuItem("Exit");
            eMenuItem.setMnemonic(KeyEvent.VK_E);
            eMenuItem.setToolTipText("Exit applicatie");
            eMenuItem.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent event) {
                    System.exit(0);
                }
            });
            file.add(eMenuItem);
                
            
            menubar.add(file);
            setJMenuBar(menubar);
        }
        
}
