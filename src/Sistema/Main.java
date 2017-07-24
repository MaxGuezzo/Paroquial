
package Sistema;

import javax.swing.JOptionPane;
import javax.swing.UIManager;
import tela.TelaPrincipal;

public class Main {
    public static void main (String args[]){
       
        try{
            UIManager.setLookAndFeel("com.alee.laf.WebLookAndFeel");
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Não foi possível carregar a skim.");
        }
         TelaPrincipal principal = new TelaPrincipal();
    }
    
    }

