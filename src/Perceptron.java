public class Perceptron {
    int[][] weights;
    int wb;
    int alpha;
    int theta;
    int yin;
    int y;
    int t;
    int[] xArray;

    //Basic constructor with all modifiable data
    Perceptron(int[][] weights, int wb, int alpha, int theta, int yin, int y, int t, int[] xArray) {
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
    yin Function to determine the f(yin) that we discussed in class, takes both wb, wi's and xi's
    to formulate the function:
    yin = wb + x1w1 + x2w2 + ... + xnwn
    and then throw that through f(yin) to return 3 possible values
     */
    public int yinFunction() {
        int summation = this.wb;
        for(int i = 0; i < this.xArray.length; i++) { //this is making the fat assumption that both weights and xArray have the SAME length, probably shouldn't be in end code for this loop
            for(int j = 0; j < this.weights.length; j++) {
                summation += (this.weights[i][j] * this.xArray[i]);
            }
        }
        if(summation > 0) {
            return 1;
        }
        else if(summation == 0) {
            return 0;
        }
        else {
            return -1;
        }
    }





}
