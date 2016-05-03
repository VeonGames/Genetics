/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package neuralnet;

import genes.*;

/**
 *
 * @author Nicno90
 */
public class NeuralNet extends Cell //cell for an organism 
{
    public Layer[] layer;
    public int fitness;
    public double[] genes;
    private int[] numNodes;
    
    public NeuralNet(int[] numNodes)
    {
        this.numNodes = numNodes;
        layer[0] = new Layer(numNodes[0], -1);
        
        for (int k = 1; k < numNodes.length; k++)
        {
            layer[k] = new Layer(numNodes[k], numNodes[k-1]);
            layer[k].input = layer[k-1].outputVector(); 
        }
        this.pheno = initGenes();
    }
    
    public void fire()
    {
        double[] outs;
        for (int k = 1; k < layer.length; k++)
        {
            layer[k].input = layer[k-1].fire();
        }
    }
    
    private double[] initGenes()
    {
        int ctr = 0;
        int temp = 0;
        for (int k = 1; k < numNodes.length; k++)
        {
            temp += numNodes[k-1] * numNodes[k];
        }
        double[] genes = new double[temp];
        for (int k = 0; k < layer.length; k++) //going throguh layers
        {
            for (int i = 0; i < layer[k].node.length; i++) //going through nodes
            {
                for (int j = 0; j < layer[k].node[i].weights.length; j++) // going through weights
                {
                    genes[ctr] = layer[k].node[i].weights[j];
                    ctr++;
                }
            }
        }
        return genes;
    }
    

/* //makes new baybe where this is the more fit parent
    public NeuralNet[] makeNew(int amount, NeuralNet mom, double mutate)
    {
        NeuralNet[] net = new NeuralNet[amount];
        double[] gene;
        double fit = fitness / (fitness + mom.fitness);
        for (int k = 0; k < amount; k++)
        {
            net[k] = new NeuralNet(numNodes);
            gene = new double[genes.length];
            
            for (int i = 0; i < genes.length; i++)
            {
                //more fit parent has better chance of genese passing down
                if (fit < Math.random())
                {
                    gene[i] = genes[i];
                }
                else
                {
                    gene[i] = mom.genes[i];
                }
                //mutation chance adding on to the gene with the relative random number
                if (Math.random() > mutate)
                {
                    gene[i] += (Math.random() * 2 - 1) * mutate * gene[i];
                }
            }
        }
        return net;
    }*/
    
}
