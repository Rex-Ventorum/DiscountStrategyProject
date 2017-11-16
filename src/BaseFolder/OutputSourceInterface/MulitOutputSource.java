package BaseFolder.OutputSourceInterface;

import java.util.Arrays;
import java.util.Objects;

public class MulitOutputSource implements OutputSource{
    private OutputSource[] outputSources;
    
    public MulitOutputSource(OutputSource... outputSources){
        setOutputSources(outputSources);
    }
    
    @Override
    public final void outputData(Object data) {
        for(OutputSource outputSource : outputSources){
            outputSource.outputData(data);
        }
    }
    
    public final void setOutputSources(OutputSource[] outputSources){
        if(outputSources == null) throw new IllegalArgumentException("OuputSources may not be null");
        this.outputSources = outputSources;
    }

    public final OutputSource[] getOutputSources() {
        return outputSources;
    }

    @Override
    public final int hashCode() {
        int hash = 3;
        hash = 47 * hash + Arrays.deepHashCode(this.outputSources);
        return hash;
    }

    @Override
    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final MulitOutputSource other = (MulitOutputSource) obj;
        if (!Arrays.deepEquals(this.outputSources, other.outputSources)) {
            return false;
        }
        return true;
    }

    @Override
    public final String toString(){
        return "A Multi Output Source Object";
    }
    
}
