package GUI;

import file.operations.Utils;
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
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class MainView extends JFrame {

    Simulator sim;

    private JPanel contentPane;

    private JScrollPane instructionMemory;
    private JPanel panelInstructions;
    private ArrayList<JLabel> instructionLabels;

    private JScrollPane dataMemory;
    private JPanel panelMemory;

    private JScrollPane registers;
    private JPanel paneRegisters;
    
    private JScrollPane outputCode;
    private JPanel paneOuputCode;
    private DefaultListModel outputListModel;
    private JList<String> outputList;
    
    private JPanel panel_2;
    private JFrame datapathFrame;
    public DataPath datapath;
    private JButton next;
    private JButton next_instruction;
    private JButton run;

    // live interpreter (one instr at the time)
    private Interpreter interpreter;

    private JPanel panel_1;

    /**
     * Create the frame.
     *
     * @param s instance of Simulator class. Manages execution of all
     * instruction and visual aid
     */
    public MainView(Simulator s) {
        this.sim = s;
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(300, 10, 980, 750);

        // create the menu bar
        createMenuBar();

        contentPane = new JPanel();
        contentPane.setLayout(null);
        setContentPane(contentPane);

        instructionLabels = new ArrayList<JLabel>();

        // create datapath with all components
        datapath = new DataPath();

        datapathFrame = new JFrame();
        datapathFrame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        datapathFrame.setBounds(10, 150, 1250, 700);
        datapathFrame.add(datapath);
        datapathFrame.setVisible(false);

        // label for memory panelInstructions
        Label instrLabel = new Label("Instrucțiuni");
        instrLabel.setBounds(10, 50, 100, 20);
        contentPane.add(instrLabel);

        // add instruction panel
        instructionMemory = new JScrollPane();
        instructionMemory.setBounds(10, 70, 190, 300);
        contentPane.add(instructionMemory);

        panelInstructions = new JPanel();
        panelInstructions.setBorder(new LineBorder(new Color(0, 0, 0)));
        instructionMemory.setViewportView(panelInstructions);
        
        // label for memory panelInstructions
        Label memLabel = new Label("Memoria (1024 bytes)");
        memLabel.setBounds(350, 50, 120, 20);
        contentPane.add(memLabel);
        
        // label for tegisters panel
        registers = new JScrollPane();
        registers.setBounds(220, 70, 110, 300);
        contentPane.add(registers);
        
        // label for registers panel
        paneRegisters = new JPanel();
        paneRegisters.setBorder(new LineBorder(new Color(0, 0, 0)));
        registers.setViewportView(paneRegisters);
        
        // add registers panelInstructions
        Label regsLabel = new Label("Regiștri");
        regsLabel.setBounds(220, 50, 100, 20);
        contentPane.add(regsLabel);

        // add memory panelInstructions
        dataMemory = new JScrollPane();
        dataMemory.setBounds(350, 70, 300, 300);
        contentPane.add(dataMemory);
        
        panelMemory = new JPanel();
        panelMemory.setBorder(new LineBorder(new Color(0, 0, 0)));
        dataMemory.setViewportView(panelMemory);
        
        // label for output
        Label outputLabel = new Label("Cod masina");
        outputLabel.setBounds(760, 50, 100, 20);
        contentPane.add(outputLabel);
        
        paneOuputCode = new JPanel();
        outputCode = new JScrollPane();
        outputList = new JList<>();
        outputCode.setBounds(700, 70, 260, 300);
        paneOuputCode.add(outputList);
        outputCode.setViewportView(paneOuputCode);
        contentPane.add(outputCode);

        // adapter for list
        this.outputListModel = new DefaultListModel();
        outputList.setModel(outputListModel);
        
        
        panel_1 = new JPanel();
        paneRegisters.add(panel_1);

        panel_2 = new JPanel();
        paneRegisters.add(panel_2);

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

        // label for interpreter panelInstructions
        Label interprLabel = new Label("Interpretor");
        interprLabel.setBounds(0, 400, 100, 20);
        contentPane.add(interprLabel);

        // add interpreter panelInstructions
        interpreter = new Interpreter(this.sim);
        interpreter.setBounds(0, 420, this.getWidth() - 50, this.getHeight() / 3);
        contentPane.add(interpreter);

        paneRegisters.setLayout(new GridLayout(1, 2, 0, 0));
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

    public void selectInstruction(int pc) {
        for (JLabel l : instructionLabels) {
            l.setForeground(Color.black);
        }
        instructionLabels.get(pc - 1).setForeground(Color.red);
        repaint();
        revalidate();
    }

    public void fillMemory(byte[] memory) {
        panelMemory.setLayout(new GridLayout(memory.length / 16, 16));//GridLayout(memory.length, 1, 0, 0));
        for (int i = 0; i < memory.length; i++) {
            JLabel l = new JLabel(memory[i] + "");
            l.setBorder(new LineBorder(new Color(0, 0, 0)));
            panelMemory.add(l);
        }
        dataMemory.remove(panelMemory);
        dataMemory.setViewportView(panelMemory);
        repaint();
    }

    public void fillRegisters(int[] registerValues) {
        panel_2.removeAll();
        panel_2.setLayout(new GridLayout(32, 1, 0, 0));
        for (int i = 0; i < registerValues.length; i++) {
            JLabel l = new JLabel(registerValues[i] + "");
            l.setBorder(new LineBorder(new Color(0, 0, 0)));
            panel_2.add(l);
        }
        panel_2.repaint();
        paneRegisters.remove(panel_2);
        paneRegisters.add(panel_2);
        paneRegisters.repaint();
        repaint();
        validate();
    }

    public void addInstructions(ArrayList<String> instructions) {
        panelInstructions.setLayout(new GridLayout(instructions.size(), 1, 0, 0));
        for (int i = 0; i < instructions.size(); i++) {
            JLabel l = new JLabel(instructions.get(i));
            panelInstructions.add(l);
            instructionLabels.add(l);
        }
        instructionMemory.remove(panelInstructions);
        instructionMemory.setViewportView(panelInstructions);

        repaint();
    }

    public void addOutputCode(String code)
    {
        this.outputListModel.addElement(code);
    }
    
    public void set_fetch_red() {
        set_to_black();
        datapath.getPc().setColor(Color.red);
        datapath.getAdder1().setColor(Color.red);
        datapath.getInstructionMemory().setColor(Color.red);
        datapath.repaint();
    }

    public void set_decode_red() {
        set_to_black();
        datapath.getRegisters().setColor(Color.red);
        datapath.repaint();
    }

    public void set_exec_red() {
        set_to_black();
//		System.out.println(sim.get_current_instruction_format());
        switch (sim.get_current_instruction_format()) {
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

    public void set_memory_red() {
        set_to_black();
        datapath.getDataMemory().setColor(Color.red);
        datapath.repaint();
    }

    public void set_write_back_red() {
        set_to_black();
        datapath.getRegisters().setColor(Color.red);
        datapath.repaint();
    }

    // set all drawings colors to black
    public void set_to_black() {
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

    /**
     * Create the menu bar for main view
     *
     * @author Alexandru
     */
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
                String filename = Utils.fileChooser(MainView.this);
                try {

                    // clear current instructions
                    panelInstructions.removeAll();
                    instructionLabels.clear();
                    
                    //clear all generated code
                    outputListModel.clear();
                    
                    // add new instructions
                    sim.loadInstructions(filename);

                    MainView.this.repaint();
                } catch (IOException ex) {
                    System.out.println("Eroare la citire fisier: " + ex);
                    return;
                }
            }
        });
        file.add(eMenuItem);

        // file->export output
        eMenuItem = new JMenuItem("Export cod masina");
        eMenuItem.setMnemonic(KeyEvent.VK_E);
        eMenuItem.setToolTipText("Import fisier instructiuni");
        eMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                String filename = "codmasina.txt";
                try {

                    BufferedWriter bw = new BufferedWriter(new FileWriter(new File(filename)));
                    
                    for(int i = 0; i < outputListModel.size(); i++)
                    {
                        bw.append((String)(outputListModel.elementAt(i)));
                        bw.newLine();
                    }
                    
                    bw.close();
                    System.out.println("Saved");
                    
                } catch (IOException ex) {
                    System.out.println("Eroare la scriere fisier: " + ex);
                    return;
                }
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
        
        // add the menu file to menu bar
        menubar.add(file);
        
        // Executa -> 
        file  = new JMenu("Executa");
        file.setMnemonic(KeyEvent.VK_F);
        
        // Executa -> Ciclu
        eMenuItem = new JMenuItem("Ciclul urmator");
        eMenuItem.setMnemonic(KeyEvent.VK_E);
        eMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                try {
                    sim.run_instruction();
                } catch (Exception e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        });
            
        file.add(eMenuItem);
        // Executa -> 
        eMenuItem = new JMenuItem("Instrucțiunea următoare");
        eMenuItem.setMnemonic(KeyEvent.VK_E);
        eMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                try {
                    sim.run_instruction();
                } catch (Exception e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        });
            
        file.add(eMenuItem);
        
        eMenuItem = new JMenuItem("Executa tot");
        eMenuItem.setMnemonic(KeyEvent.VK_E);
        eMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                try {
                    sim.run_all_instructions();
                } catch (Exception e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        });
        
        file.add(eMenuItem);
        
        // add the menu file to menu bar
        menubar.add(file);
        
        
        file = new JMenu("Unelte");
        file.setMnemonic(KeyEvent.VK_F);
        
        // Unelte -> Vizual
        eMenuItem = new JMenuItem("Vizual");
        eMenuItem.setMnemonic(KeyEvent.VK_E);
        eMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                System.out.println(""+ datapathFrame.isVisible());
                datapathFrame.setVisible(!datapathFrame.isVisible());
            }
        });
        file.add(eMenuItem);
        
        // add the menu file to menu bar
        menubar.add(file);
        
        
        setJMenuBar(menubar);
    }

    /**
     * @return the panelMemory
     */
    public JPanel getPanel2() {
        return panelMemory;
    }

    /**
     * @param panel2 the panelMemory to set
     */
    public void setPanel2(JPanel panel2) {
        this.panelMemory = panel2;
    }

    /**
     * @return the paneRegisters
     */
    public JPanel getPanel3() {
        return paneRegisters;
    }

    /**
     * @param panel3 the paneRegisters to set
     */
    public void setPanel3(JPanel panel3) {
        this.paneRegisters = panel3;
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
}
