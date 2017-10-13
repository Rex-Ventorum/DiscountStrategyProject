package BaseFolder.OutputSourceInterface;

import java.awt.Font;
import javax.swing.JOptionPane;
import javax.swing.UIManager;

public class GUIOutputSource implements OutputSource {

    @Override
    public final void outputData(Object data) {
        Font font = UIManager.getFont("OptionPane.font");
        UIManager.put("OptionPane.font", new Font(Font.MONOSPACED, Font.PLAIN, 50));
        font = UIManager.getFont("OptionPane.font");
        JOptionPane.showMessageDialog(null, data);
    }
    
}
