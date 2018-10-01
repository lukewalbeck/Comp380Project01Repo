
public class Project01 {
    public static void main(String[] args) {
        System.out.println("hello world");

        //until we can read in data for weights and values, just for testing currently
        int wb = 0;
        int[][] weights = new int[63][7];
        int[] values = new int[7];
        int alpha = 0;
        int theta = 0;
        int yin = 0;
        int t = 0;
        int y = 0;


        //call perceptron class and run table, displaying it will be called in this class
        Perceptron test = new Perceptron(weights,wb,alpha,theta,yin,y,t,values);
        int fyin = test.yinFunction();
        System.out.println(fyin);

    }
}
