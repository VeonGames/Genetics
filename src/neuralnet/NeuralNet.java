/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package neuralnet;

/**
 *
 * @author Nicno90
 */
public class NeuralNet extends Thread
{
    public Layer[] layer;
    public double[][] output;
    
    public NeuralNet(int[] numNodes, double learnRate, double[] input)
    {
        
        layer[0] = new Layer(numNodes[0], -1);
        
        for (int k = 1; k < numNodes.length; k++)
        {
            layer[k] = new Layer(numNodes[k], numNodes[k-1]);
            layer[k].input = layer[k-1].outputVector(); 
        }
        layer[0].input = input;
    }
    
    public void fire()
    {
        double[] outs;
        for (int k = 0; k < layer.length; k++)
        {
            layer[k].feed();
            outs = layer[k].outputVector();
            layer[k].input = outs;
        }
        
    }
}
