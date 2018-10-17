import java.util.Arrays;

public class Perceptron{
    int[][] samp_dat;
    double[][] weights;
    int[] bias_w;
    int[][] target;
    int[] outputs;

    public Perceptron(int[][] samp, double[][] w, int[] bias, int[][] tar, int[] out) {
        samp_dat = samp;
        weights = w;
        bias_w = bias;
        target = tar;
        outputs = out;
    }

    public void learn() {
        boolean converged = false;

        int count = 0;
        while(!converged)   {
            count++;
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



                    if (i == 20 && maxWeightChange < 0.1) {
                        converged = true;
                    }
                }
            }
        }

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
            //TODO: theta change
            if(y_in_j > 1) {
                outputs[j] = 1;
            }
            else if(y_in_j < -1) {
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
//            for(int j = 0; j < 7; j++) {
                //TODO: add alpha instead of 1
                bias_w[j] = bias_w[j] + (1 * target[i][j]);
                for(int k = 0; k < 63; k++) {
                    double weightChange = (1 * target[i][j] * samp_dat[i][k]);
                    weights[j][k] = weights[j][k] + weightChange;
                    if(weightChange > maxWeightChange) {
                        maxWeightChange = weightChange;
                    }
                }
//            }
        }
        return maxWeightChange;
    }
}