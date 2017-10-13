package BaseFolder.OutputSourceInterface;
public class MulitOutputSource implements OutputSource{
    private OutputSource[] outputSources;
    
    public MulitOutputSource(OutputSource... outputSources){
        setOutputSources(outputSources);
    }
    
    
    @Override
    public void outputData(Object data) {
        for(OutputSource outputSource : outputSources){
            outputSource.outputData(data);
        }
    }
    
    public final void setOutputSources(OutputSource[] outputSources){
        if(outputSources == null) throw new IllegalArgumentException("OuputSources may not be null");
        this.outputSources = outputSources;
    }

    public OutputSource[] getOutputSources() {
        return outputSources;
    }
    
}
