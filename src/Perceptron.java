public class Perceptron {
    int[][] weights;
    int[] wb;
    int alpha;
    int theta;
    int yin;
    int[] y;
    int t;
    int[] xArray;

    //Basic constructor with all modifiable data
    Perceptron(int[][] weights, int[] wb, int alpha, int theta, int yin, int[] y, int t, int[] xArray) {
        this.weights = weights;
        this.wb = wb;
        this.alpha = alpha;
        this.theta = theta;
        this.yin = yin;
        this.y = y;
        this.t = t;
        this.xArray = xArray;
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
            for(int i = 0; i < 63; i++) {
                xArray[i] = s[i];
                y[j] = solveYFunction(weights, xArray, wb[j], j);

                if(y[j] != t) { //change weights
                    weights[i][j] = weights[i][j] + (theta*t*xArray[i]);
                    wb[j] = wb[j] + (theta*t);
                    changed = true;
                }

                if(i == 62 && !changed) { //if at the end of the loop nothing has been changed, stop the while statement
                    converged = true;
                }
                //incorporate logic to tell when no weights were changed
            }
        }
    }

    public int solveYFunction(int[][] weights, int[] xArray, int wb, int j) {
        int yin = wb;
        for(int i = 0; i < 63; i++) {
            yin += (xArray[i] * weights[i][j]);
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
