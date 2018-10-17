import java.util.Arrays;

public class Perceptron{
    int[][] samp_dat;
    double[][] weights;
    double[] bias_w;
    int[][] target;
    int[] outputs;
    double alph;
    double thet;
    double thresh;
    int max_epchs;

    public Perceptron(int[][] samp, double[][] w, double[] bias, int[][] tar, int[] out, double alpha, double theta, double threshold, int max_epochs) {
        samp_dat = samp;
        weights = w;
        bias_w = bias;
        target = tar;
        outputs = out;
        alph = alpha;
        thet = theta;
        thresh = threshold;
        max_epchs = max_epochs;


    }

    public int learn() {
        boolean converged = false;
        int curr_count = 0;

        while(!converged)   {
            if(curr_count > max_epchs)  {
                System.out.println("The training data set did not converge.  The program will now quit.");
                System.exit(0);
            }
            curr_count++;
            double maxWeightChange = 0;
            //Compute output vector per pattern
            for(int i = 0; i < 21; i++) {

                target = getTarget(i);

                for(int j = 0; j< bias_w.length; j++) {
                    int outputs = computeOutput(i, j);     //Computes activation for output neuron in pattern


                    if(outputs != target[i][j]) {   //Checks if output matches with target output to change weights
                        bias_w[j] = bias_w[j] + (1 * target[i][j]);
                        for(int k = 0; k < 63; k++) {
                            double weightChange = (1 * target[i][j] * samp_dat[i][k]);
                            weights[j][k] = weights[j][k] + weightChange;
                            if(weightChange > maxWeightChange) {
                                maxWeightChange = weightChange;
                            }
                        }
                    }



                    if (i == 20 && maxWeightChange < thresh) {
                        converged = true;
                        //This is where I would put the FileWriter method writeWeights
                        return curr_count;
                    }
                }
            }
        }
        return 0;
    }


    public int[][] getTarget(int i) {
        for(int l = 0; l < 7; l++) {
            if(l == i%7) {
                target[i][l] = 1;
            }
            else {
                target[i][l] = -1;
            }
        }
        return target;
    }


    public int computeOutput(int i, int j) {

            double y_in_j = bias_w[j];
            for(int k = 0; k < 63; k++) {
                y_in_j += samp_dat[i][k] * weights[j][k];
            }
            if(y_in_j > thet) {
                outputs[j] = 1;
            }
            else if(y_in_j < -thet) {
                outputs[j] = -1;
            }
            else {
                outputs[j] = 0;
            }

        return outputs[j];
    }

    //i is pattern j is output neuron
    public double determineEquality(int i, int j) {
        double maxWeightChange = 0;
        if(!Arrays.equals(outputs, target[i])) {
                bias_w[j] = bias_w[j] + (alph * target[i][j]);
                for(int k = 0; k < 63; k++) {
                    double weightChange = (alph * target[i][j] * samp_dat[i][k]);
                    weights[j][k] = weights[j][k] + weightChange;
                    if(weightChange > maxWeightChange) {
                        maxWeightChange = weightChange;
                    }
                }
        }
        return maxWeightChange;
    }

    public void writeWeights(double[][] weights)
    {
        int hope = 1;
    }
}

