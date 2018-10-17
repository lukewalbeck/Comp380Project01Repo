import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Arrays;
import java.util.Scanner;
import java.lang.Math;


public class Project01 {
    public static void main(String[] args) {

        double alpha = 0;
        double theta = 0;
        double threshold = 0;
        int max_epochs = 0;
        int init_weights;
        int test_deploy;
        int sw;     //sw is short for start weights if there is a pre-done weights file
        String train_file = "";
        String input_weights;
        String output_weights;
        double[][] weights = new double[7][63];

        Scanner kb = new Scanner(System.in);

        System.out.println("Welcome to my first neural network - A Perceptron Net!\n");
        System.out.println("Enter 1 to train using a training file, enter 2 to train using a trained weight settings data file:");
        sw = kb.nextInt();

        if(sw == 1)   {
            System.out.println("\nEnter the training data file name:");
            train_file = kb.nextLine();
            System.out.println("\nEnter 0 to initialize weights to 0, enter 1 to initialize weights to random values between -0.5 and 0.5:");
            init_weights = kb.nextInt();
            if(init_weights == 0)   {           //This loop initializes all weights to 0
                for(int i = 0; i < 7; i++) {
                    for(int j = 0; j < 63; j++) {
                        weights[i][j] = 0;
                    }
                }
            }
            else if(init_weights == 1)  {       //This loop initializes all weights to random values between -0.5 and 0.5
                for(int i = 0; i < 7; i++) {
                    for (int j = 0; j < 63; j++) {
                        weights[i][j] = (Math.random() - 0.5);
                    }
                }
            }
            System.out.println("\nEnter the maximum number of training epochs:");
            max_epochs = kb.nextInt();
            System.out.println("\nEnter a file name to save the trained weight settings:");
            output_weights = kb.nextLine();
            System.out.println("\nEnter the learning rate alpha from 0 to 1 but not including 0");
            alpha = kb.nextDouble();
            System.out.println("\nEnter the threshold theta:");
            theta = kb.nextDouble();
            System.out.println("\nEnter the threshold to be used for measuring weight changes:");
            threshold = kb.nextDouble();
        }
        else if(sw == 2)     {
            System.out.println("Enter the trained weight settings input data file name:");
            input_weights = kb.nextLine();
            //Put a function here to read in the file to assign weights
        }


        int[] s = sampleArrayData(train_file, 1);
        int[][] sep_s = new int[21][63];
        for(int kk = 0; kk < 21; kk++)  {
            for(int jj = 0; jj < 63; jj++)  {
                sep_s[kk][jj] = s[(kk*63)+jj];
            }
        }



        double[] bias_w = new double[7];
        int[][] target = new int[21][7];
        int[] outputs = new int[7];

        Perceptron perc = new Perceptron(sep_s, weights, bias_w, target, outputs, alpha, theta, threshold, max_epochs);
        int occur_epochs = perc.learn();

        System.out.println("Training converged after " + occur_epochs + " epochs.");
        System.out.println("Enter 1 to test/deploy using a testing/deploying data file, enter 2 to quit.");
        test_deploy = kb.nextInt();
        if(test_deploy == 1)
        {
            //Have code here to accept the testing data and use the weights on it
        }
        else
        {
            System.out.println("I hope you enjoyed my perceptron net!");
            System.exit(0);
        }

    }

    public static int[] sampleArrayData(String fileName, int epoch) {
        try{
            BufferedReader reader = new BufferedReader(new FileReader(fileName));
            String line;
            int[] sampleData = new int[63 * 21];
            int count = 0;
            int lineCount = 0;
            while((line = reader.readLine()) != null) {
                if(line.equals("")) {
                    lineCount++;
                }
                else if(lineCount%2 == 0){
                    String[] lineArray = line.split("\\s+");
                    if(lineArray[0].equals("")) {
                        String[] temp = Arrays.copyOfRange(lineArray, 1, lineArray.length);
                        lineArray = temp;
                    }
                    for(int i = 0; i < lineArray.length; i++) {
                        sampleData[i+count] = Integer.parseInt(lineArray[i]);
                    }
                    count += 7;
                }
            }
            return sampleData;
        }
        catch(Exception ex) {
            System.out.println(ex.getMessage());
        }
        return null;
    }


}
