/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import java.awt.BorderLayout;
import java.awt.TextArea;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JPanel;

import simulator.Simulator;
/**
 *
 * @author Alexandru
 */
public class Interpreter extends JPanel {
    
    private TextArea txtArea;
    
    private Simulator simulator;
    
    public Interpreter(Simulator simulator)
    {
        this.simulator = simulator;
        
        this.setLayout(new BorderLayout());
        
        initTextArea();
        
        
    }
    
    private void initTextArea()
    {
        txtArea = new TextArea();
        txtArea.setBounds(0, 600, this.getWidth() - 50, 100);
        txtArea.setName("txtAreaLive");
        txtArea.setText(">>");
        this.add("North", txtArea);
        
        txtArea.addKeyListener(new KeyListener() {

            @Override
            public void keyTyped(KeyEvent e) {
                if (e.getKeyChar() == '\n'){
                    try {
                        // get all lines of the text area of the interpreter
                        String[] liveInstr = txtArea.getText().split("\n");
                        // the last line is the instruction to run. Firs to chars of line is the intepretor sign "<<", so skip them.
                        simulator.runInstruction(liveInstr[liveInstr.length - 1].substring(2));
                    } catch (Exception ex) {
                        System.out.println("Instrucțiune invalidă: " + ex.toString());
                    }
                    txtArea.append(">>");
                }
            }

            @Override
            public void keyPressed(KeyEvent e) {
            }

            @Override
            public void keyReleased(KeyEvent e) {
            }
        });
        
    }
    
}
