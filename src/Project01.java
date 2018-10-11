import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Arrays;
import java.util.Scanner;
import java.lang.Math;


public class Project01 {
    public static void main(String[] args) {
        System.out.println("Test: " + patternFinder("SampleData.txt"));
        int[] test = sampleArrayData("SampleData.txt");



    }


    public static void programCall() {
        System.out.println("My name is lubie and I am gay");

        //until we can read in data for weights and values, just for testing currently
        int wb = 0;
        double[][] weights = new double[63][7];
        int[] values = new int[7];
        int alpha = 0;
        int theta = 0;
        int yin = 0;
        int t = 0;
        int y = 0;
        int threshold = 0;
        int max_epochs = 0;
        int init_weights = 0;
        int sw = 0;     //sw is short for start weights if there is a pre-done weights file
        String train_file;
        String input_weights;
        String output_weights;


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
                for(int i = 0; i < 63; i++) {
                    for(int j = 0; j < 7; j++) {
                        weights[i][j] = 0;
                    }
                }
            }
            else if(init_weights == 1)  {       //This loop initializes all weights to random values between -0.5 and 0.5
                for(int i = 0; i < 63; i++) {
                    for (int j = 0; j < 7; j++) {
                        weights[i][j] = (Math.random() - 0.5);
                    }
                }
            }
            System.out.println("\nEnter the maximum number of training epochs:");
            max_epochs = kb.nextInt();
            System.out.println("\nEnter a file name to save the trained weight settings:");
            output_weights = kb.nextLine();
            System.out.println("\nEnter the learning rate alpha from 0 to 1 but not including 0");
            alpha = kb.nextInt();
            System.out.println("\nEnter the threshold theta:");
            theta = kb.nextInt();
            System.out.println("\nEnter the threshold to be used for measuring weight changes:");
            threshold = kb.nextInt();
        }
        else if(sw == 2)     {
            System.out.println("Enter the trained weight settings input data file name:");
            input_weights = kb.nextLine();
            //Put a function here to read in that file to assign weights
        }




        //call perceptron class and run table, displaying it will be called in this class
        //Perceptron test = new Perceptron(weights,wb,alpha,theta,yin,y,t,values);
        //int fyin = test.yinFunction();
        //System.out.println(fyin);
    }


    public static int patternFinder(String fileName) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(fileName));
            String line;
            while((line = reader.readLine()) != null) {
                if(line.equals("")) {
                    line = reader.readLine();
                    break;
                }
            }
            String[] lineArray = line.split("\\s");
            int patternNum = -1;
            for(int i = 0; i < lineArray.length; i++) {
                if(lineArray[i].equals("1")) {
                    patternNum = i;
                }
            }

            return patternNum;
        }
        catch(Exception ex) {
            System.out.println(ex.getMessage());
        }

        return -1;
    }


    public static int[] sampleArrayData(String fileName) {
        try{
            BufferedReader reader = new BufferedReader(new FileReader(fileName));
            String line;
            int[] sampleData = new int[63];
            int count = 0;
            while((line = reader.readLine()) != null) {
                if(line.equals("")) {
                    break;
                }
                else {
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
