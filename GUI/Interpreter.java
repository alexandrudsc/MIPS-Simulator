/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import java.awt.BorderLayout;
import java.awt.TextArea;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JPanel;
import javax.swing.JTextArea;

/**
 *
 * @author Alexandru
 */
public class Interpreter extends JPanel {
    
    private TextArea txtArea;
    
    public Interpreter()
    {
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
                if (e.getKeyChar() == '\n')
                   txtArea.append(">>");
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
