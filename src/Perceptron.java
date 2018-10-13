public class Perceptron {
    double[][] weights;
    double[] wb;
    int alpha;
    int theta;
    int yin;
    int[] y;
    int t;
    int[] xArray;
    int threshold;

    //Basic constructor with all modifiable data
    Perceptron(double[][] weights, double[] wb, int alpha, int theta, int yin, int[] y, int t, int[] xArray, int threshold) {
        this.weights = weights;
        this.wb = wb;
        this.alpha = alpha;
        this.theta = theta;
        this.yin = yin;
        this.y = y;
        this.t = t;
        this.xArray = xArray;
        this.threshold = threshold;
    }



    /*
    S is SAMPLE DATA, T is TARGET, J denotes which pattern we are learning for
    Currently at static 63 for # of neurons
     */
    public void learningAlgorithm(int[] s, int t, int j) {
        boolean converged = false;
        boolean changed;
        while(!converged) {
            changed = false;
            for(int i = 0; i < 64; i++) {
                xArray[i] = s[i];
                y[j] = solveYFunction(weights, xArray, wb[j], j);

                if(y[j] != t) { //change weights

                    weights[j][i] = weights[j][i] + (theta*t*xArray[i]);


                    wb[j] = wb[j] + (theta*t);


                    changed = true;

                }

                if(i == 63 && !changed) { //if at the end of the loop nothing has been changed, stop the while statement
                    converged = true;
                }
                //incorporate logic to tell when no weights were changed
            }
        }
    }

    public int solveYFunction(double[][] weights, int[] xArray, double wb, int j) {
        double yin = wb;
        for(int i = 0; i < 64; i++) {
            yin += (xArray[i] * weights[j][i]);
        }
        if(yin > 1) {
            return 1;
        }
        else if (yin == 0) {
            return 0;
        }
        else {
            return -1;
        }
    }





}
