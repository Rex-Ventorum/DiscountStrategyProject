package BaseFolder.OutputSourceInterface;

import javax.swing.JOptionPane;

public class GUIOutputSource implements OutputSource {

    @Override
    public final void outputData(Object data) {
        JOptionPane.showMessageDialog(null, data);
    }
    
}
