/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BaseFolder.OutputSourceInterface;

/**
 *
 * @author Brandon
 */
public class CounsolOutputSource implements OutputSource{
    public final void outputData(Object data) {
        System.out.println(data);
    }
}
