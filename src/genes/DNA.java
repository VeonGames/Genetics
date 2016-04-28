/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package genes;

/**
 *
 * @author Nicno90
 */
public class DNA
{
    private double[] dna; 
    private int cellAmount;
    public DNA(Cell[] cell)
    {
        cellAmount = cell.length;
        int ctr = 0;
        for (int k = 0; k < cell.length; k++)
        {
            ctr += cell[k].pheno.length + 1;//tree- first digit represents how many in the next cell
        }
        dna = new double[ctr];
    }
    
    public DNA reproduce(double paFit, DNA mom, double maFit, double mutateR) //higher the better fitness
    {
        double fr = paFit * 1.0 / (paFit + maFit); //chance of beig dominate
        Cell cell;
        if (dna.length != mom.dna.length)
        {
            //add in a way to evolve new genes
            return null;
        }
        
        Cell[] baby = new Cell[cellAmount]; //baby's cells
        int ctr = 0; //current cell index
        
        
        for (int k = 0; k < dna.length; k++)
        {
            cell = new Cell();
            cell.pheno = new double[(int) dna[k]];
            ctr++;
            for (int i = 0; i < ((int) dna[k]); i++,k++)
            {
                i++; k++; //skip the explanitory digit
                if (Math.random() < fr) //if gene is dominent
                {
                    cell.pheno[i] = dna[k];
                }
                else
                {
                    cell.pheno[i] = mom.dna[k];
                }
                
                if (Math.random() < mutateR)
                {
                    //ranomly add or subtract the variation given away from the cell digit
                    cell.pheno[i] += (Math.random() * 2 -1) * mutateR * cell.pheno[i]; 
                }
            }
            //add the baby cell to the baby' cells
            baby[ctr] = cell;
        }
        
        return new DNA(baby);
    }
    
    public void encode(Cell[] cell)
    {
        for (int k = 0; k < dna.length; k++)
        {
            dna[k] = cell[k].pheno.length;
            for (int i = 1; i < cell[k].pheno.length; i++)
            {
                dna[k*i + i] = cell[k].pheno[i];
            }
        }
    }
    
    public Cell[] decode()
    {
        Cell[] cell = new Cell[cellAmount];
        Cell c = new Cell();
        for (int k = 0; k < cell.length; k++)
        {
            c.pheno = new double[(int)dna[k]];
            for (int i = 0; i < cell[k].pheno.length; i++)
            {
                c.pheno[i] = dna[k*i + i + 1];
            }
            
            cell[k] = c;
        }
        return cell;
    }
}
