package BaseFolder.OutputSourceInterface;

public class NoOutputSource implements OutputSource{
    @Override public void outputData(Object data) { /*Does Nothing*/} 
    @Override public boolean equals(Object object){return object instanceof NoOutputSource;}
    @Override public String toString(){return "A No Output Source Object";}
}
