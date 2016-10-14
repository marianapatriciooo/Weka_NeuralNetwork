
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class WriteClass {
	
	public void write(String filePath,String content) throws IOException{
		
		File resultFile = new File(filePath);
		FileWriter fw = new FileWriter(resultFile.getAbsoluteFile(),true);
		BufferedWriter bw = new BufferedWriter(fw);
		
		if (!resultFile.exists()) {
			resultFile.createNewFile();
			bw.write("Error Rate, Precision, Recall, Instances, Correct, Incorrect, MeanError, Relative Absolute Error");
			
		}
		
		
		bw.write(content);
		bw.newLine();
		bw.close();

		System.out.println("Done");
	}
	
	

}

