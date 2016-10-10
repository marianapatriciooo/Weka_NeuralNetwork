import java.io.FileReader;
import java.util.Random;

import weka.classifiers.Evaluation;
import weka.classifiers.functions.MultilayerPerceptron;
import weka.core.Instances;

public class NeuralNet {

	public static void simpleWekaTrain(String filepath)
	{
	try{
	//Reading training arff or csv file
	FileReader trainreader = new FileReader(filepath);
	Instances train = new Instances(trainreader);
	int size =train.numAttributes() -1;
	train.setClassIndex(size);
	//Instance of NN
	MultilayerPerceptron mlp = new MultilayerPerceptron();
	//Setting Parameters
	mlp.setLearningRate(0.1);
	mlp.setMomentum(0.2);
	mlp.setTrainingTime(2000);
	mlp.setHiddenLayers("2,2");
	mlp.buildClassifier(train);
	
	 Evaluation eval = new Evaluation(train);
	    eval.evaluateModel(mlp, train);
	    System.out.println(eval.errorRate()); //Printing Training Mean root squared Error
	    System.out.println(eval.toSummaryString()); //Summary of Training

	    System.out.println("______________");
		 Evaluation eval2 = new Evaluation(train);
	    eval2.crossValidateModel(mlp, train, 10, new Random(1));
	    System.out.println(eval2.errorRate()); //Printing Training Mean root squared Error
	    System.out.println(eval2.toSummaryString()); //Summary of Training

	}
	catch(Exception ex){
	ex.printStackTrace();
	}
	
	
	}
	
	

	
	public static void main(String[] args) {
		String filepath = "D:\\Faculdade\\IT4BI master\\2nd year\\Data mining and machine learning\\1 - neural networks and svm\\assignment\\data\\data-chessboard.arff";

		simpleWekaTrain(filepath);
	}
	
	//http://stackoverflow.com/questions/28694971/using-neural-network-class-in-weka-in-java-code
	
}
