/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package file.operations;

import java.awt.Component;
import javax.swing.JFileChooser;

/**
 *
 * @author Alexandru
 */
public class Utils {

    // Choose a file and return the filename or null
    public static String fileChooser(Component component) {
        JFileChooser c = new JFileChooser();
        
        int rVal = c.showOpenDialog(component);
        if (rVal == JFileChooser.APPROVE_OPTION) {
            return c.getSelectedFile().getName();

        }
        
        return  null;
        
    }
}
