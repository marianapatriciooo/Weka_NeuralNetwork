import java.io.FileReader;
import java.util.Random;

import weka.classifiers.Evaluation;
import weka.classifiers.functions.MultilayerPerceptron;
import weka.core.Instances;

public class NeuralNet {

	
	public static void simpleWekaTrain(String filepath,Double learningRate,Double momentum, Integer trainingTime,String hiddenLayers,String filetoWrite,String parameter)
	{
	try{
	//Reading training arff or csv file
	FileReader trainreader = new FileReader(filepath);
	Instances train = new Instances(trainreader);
	
	
	//int classindex =train.numAttributes() -1;
	int classindex =0;
	train.setClassIndex(classindex);
	
	
	
	//Instance of NN
	MultilayerPerceptron mlp = new MultilayerPerceptron();
	//Setting Parameters
	mlp.setLearningRate(learningRate);
	mlp.setMomentum(momentum);
	mlp.setTrainingTime(trainingTime);
	mlp.setHiddenLayers(hiddenLayers);
	mlp.buildClassifier(train);
	
//	 Evaluation eval = new Evaluation(train);
//	    eval.evaluateModel(mlp, train);
//	    System.out.println("For the first one"+eval.incorrect()); //Printing Training Mean root squared Error
//	    System.out.println(eval.toSummaryString()); //Summary of Training

	    
		 Evaluation eval2 = new Evaluation(train);
	    eval2.crossValidateModel(mlp, train, 10, new Random(1));
	    
	    WriteClass writing=new WriteClass();
	    System.out.println(eval2.errorRate()); //Get ErrorRate
	    
	    writing.write(
	    		
	    		filetoWrite, parameter+","+String.valueOf(eval2.errorRate())+","+
	    		String.valueOf(eval2.weightedPrecision())+
	    		","+String.valueOf(eval2.weightedRecall())+","+
	    		String.valueOf(eval2.incorrect())+","+
	    		String.valueOf(eval2.correct())+","+
	    		String.valueOf(eval2.meanAbsoluteError())
	    		);
	    
	    System.out.println(eval2.weightedPrecision()); //Precision
	    System.out.println(eval2.weightedRecall()); //Recall
	    System.out.println(eval2.numInstances());//Instances
	    System.out.println(eval2.incorrect());//InCorrect
	    System.out.println(eval2.correct());//correct
	    System.out.println(eval2.meanAbsoluteError());//Mean Error
	    System.out.println(eval2.relativeAbsoluteError());//Relative Absolute Error
	        
	    
	    //System.out.println(eval2.toSummaryString()); //Summary of Training

	}
	catch(Exception ex){
	ex.printStackTrace();
	}
	
	
	}
	
	

	public static void changeTrainingTime(String filepath){
		for(int i=50; i<=1000; i=i+50){
			System.out.println("Number of epochs: " + i);
			String writepath = "D://Faculdade//IT4BI master//2nd year//Data mining and machine learning//1 - neural networks and svm//assignment//output_tests_letter//testTrainingTime.txt";
			simpleWekaTrain(filepath,0.3,0.2,i, "a",writepath,i+"");
		}
	}
	
	public static void changeLearnignRate(String filepath){
		for(double i=0.01; i<=1; i=i+0.01){
			System.out.println("LearningRate: " + i);
			String writepath = "D://Faculdade//IT4BI master//2nd year//Data mining and machine learning//1 - neural networks and svm//assignment//output_tests_letter//testLearningRate.txt";
			simpleWekaTrain(filepath,i,0.2,1000, "16",writepath,i+"");
		}
	}
	public static void changeMomentum(String filepath){
		for(double i=0.01; i<=1; i=i+0.01){
			System.out.println("Momentum: " + i);
			String writepath = "D://Faculdade//IT4BI master//2nd year//Data mining and machine learning//1 - neural networks and svm//assignment//output_tests_letter//testTrainingMomentum.txt";
			simpleWekaTrain(filepath,0.29,i,1000, "16",writepath,i+"");
		}
	}
	
	public static void changeHiddenLayers(String filepath){
		//Test the hidden layers
		String writepath = "D://Faculdade//IT4BI master//2nd year//Data mining and machine learning//1 - neural networks and svm//assignment//output_tests_letter//testHiddenLayers.txt";
				for(int i=1; i<=20;i++){
					String layers =""+i;
					System.out.println("1 HIDDEN LAYER " + layers);
					layers = layers + ",,";
					simpleWekaTrain(filepath,0.3,0.2,1000, layers,writepath,layers);
				}
				for(int i=2; i<=10;i++){
					for(int j=2;j<=10;j++){
						String layers =""+i+", "+j;
						System.out.println("2 HIDDEN LAYERS " + layers);
						layers = layers + ",";
						simpleWekaTrain(filepath,0.3,0.2,1000, layers,writepath,layers);
					}
				}
				for(int i=2; i<=10;i++){
					for(int j=2;j<=10;j++){
						for(int k=2; k<=5;k++){
							String layers =""+i+", "+j + ", "+ k;
							System.out.println("3 HIDDEN LAYERS " + layers);
							simpleWekaTrain(filepath,0.3,0.2,1000, layers,writepath,layers);
						}
					}
				}
	}
	
	
	
	public static void main(String[] args) {
		//String filetoWrite="D://Galicia//Documents//IT4BI//ECP//DMML//Tests//test1.txt";
		String filepath = "D:\\Faculdade\\IT4BI master\\2nd year\\Data mining and machine learning\\1 - neural networks and svm\\assignment\\data\\letterCG.arff";
		//changeTrainingTime(filepath);
		//changeHiddenLayers(filepath);
		//changeLearnignRate(filepath);
		changeMomentum(filepath);
		

			}
		

	
	//http://stackoverflow.com/questions/28694971/using-neural-network-class-in-weka-in-java-code
	//Jorge's Comment :)
}
