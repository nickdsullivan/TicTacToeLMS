
/**
 * Write a description of class LMS here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class LMS
{
    float [] weights; 
    
    int size;
    final float weightUpdateConstant = (float).1;
    /**
     * Constructor for objects of class LMS
     */
    //inputs at index 0 will be 1 always 
    
    public LMS(int size)
    {
       //Size + 1 because you have w0 that will never be affected by an input value
        weights = new float [size+1];
        
        this.size = size;
        for (int i = 0; i < size+1; i ++){
            weights[i] = (float)(0);
        }
    }
    public float getEvaluations(int[] inputs){
        float sum = 0;
        float temp;
        for (int i = 0; i <= size; i ++){
            temp = inputs[i];
            sum += weights[i]*temp;
           
        }
        
        return sum;
    }
    public void train(float trainingValue, int[] inputs){
        //Do this now so we don't recompute later
        //Might not be necessary
       
        float temp;
        float currVal =getEvaluations(inputs);
        for(int i =0; i <= size; i++){
            //This is the evaluations function
            temp = inputs[i];
            //System.out.println((currentValue));
            weights[i] = (weights[i] + (weightUpdateConstant*(trainingValue-getEvaluations(inputs))*temp));
            weights[i]= (float)Math.round(weights[i]*100000.0)/ (float)(100000.0);
           
        }
    }
    public String toString(){
        String str = "V(x) = " + weights[0];
        for (int i = 1; i <= size; i ++){
            str += "+ " +weights[i] + "x" + i + " ";
        }
        return str;
        
    }
    public void setWeights(float[] w){
        for (int i = 0; i <= size; i ++){
            weights[i] = w[i];
        }
    }
}
