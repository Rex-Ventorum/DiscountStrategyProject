package BaseFolder.OutputSourceInterface;

import java.awt.Font;
import javax.swing.JOptionPane;
import javax.swing.UIManager;

public class GUIOutputSource implements OutputSource {

    @Override
    public final void outputData(Object data) {
        Font original = UIManager.getFont("Label.font");
        UIManager.put("Label.font", new Font(Font.MONOSPACED,Font.PLAIN,12));
        JOptionPane.showMessageDialog(null,data);
        UIManager.put("Label.font", original);
    }
    
    @Override
    public final boolean equals(Object object){
        return object instanceof GUIOutputSource;
    }
    
    @Override
    public final String toString(){
        return "A GUI Output Source Object";
    }
    
}
