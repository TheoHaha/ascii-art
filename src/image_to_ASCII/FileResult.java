package image_to_ASCII;

import java.io.FileWriter;
import java.io.IOException;
import java.io.BufferedWriter;
import java.io.File;
//import java.io.File;

public class FileResult {
	
	public static final int PRINTMODE_DEFAULT = 0;
	public static final int PRINTMODE_INVERTED = 1;
	public static final int PRINTMODE_WHITESPACES = 2;
	public static final int PRINTMODE_BRIGHTMAP = 3;
	
	private File file;
	private BufferedWriter writer;
	
	public FileResult(String fileName) {
		this.file = new File(fileName);
	}
	
	public FileResult(File file) {
		this.file = file;
	}
	
	public void printResult(ImageFile imgFile) {
		printResult(imgFile.getPixels(), PRINTMODE_DEFAULT);
	}
	
	public void printResult(int[][] result) {
		printResult(result, PRINTMODE_DEFAULT);
	}
	
	public void printResult(ImageFile imgFile, int printMode) {
		printResult(imgFile.getPixels(), printMode);
	}
	
	public void printResult(int[][] result, int printMode) {
		
		// ASCII art palette
		//System.out.println("`^\",:;Il!i~+_-?][}{1)(|\\/tfjrxnuvczXYUJCLQ0OZmwqpdbkhao*#MW&8%B@$");
		
		try {
			// thanks https://www.baeldung.com/java-write-to-file
			this.writer = new BufferedWriter(new FileWriter(this.file));
			this.writer.write("");
			
			for(int i=0; i < result.length ; i++ ) {
				for(int j=0; j < result[0].length ; j++) {
					
					int brightValue = result[i][j];
					if (printMode == PRINTMODE_DEFAULT) {
						this.print_default(brightValue);
					} else if (printMode == PRINTMODE_INVERTED) {
						this.print_inverted(brightValue);
					} else if (printMode == PRINTMODE_WHITESPACES) {
						this.print_whitespaces(brightValue);
					} else if (printMode == PRINTMODE_BRIGHTMAP) {
						this.print_brightmap(brightValue);
					} else {
						this.print_default(brightValue);
					}
				}
				this.writer.append("\n");
			}
			this.writer.close();
		} catch (IOException e) {
			System.out.println("An error has occured");
			e.printStackTrace();
		}
		System.out.println("job's done"); // message to confirm that the job is done
	}
	
	private void print_default(int brightValue) {
		try {
			if (brightValue <= 17) {
				this.writer.append("``");
			} else if (brightValue <= 34) {
				this.writer.append("\"\"");
			} else if (brightValue <= 51) {
				this.writer.append(";;");
			} else if (brightValue <= 68) {
				this.writer.append("!!");
			} else if (brightValue <= 85){
				this.writer.append("++");
			} else if(brightValue <= 102) {
				this.writer.append("??");
			} else if (brightValue <= 119) {
				this.writer.append("}}");
			} else if (brightValue <= 136) {
				this.writer.append("))");
			} else if (brightValue <= 153) {
				this.writer.append("\\\\");
			} else if (brightValue <= 170){
				this.writer.append("ff");
			} else if(brightValue <= 187) {
				this.writer.append("XX");
			} else if (brightValue <= 204) {
				this.writer.append("JJ");
			} else if (brightValue <= 221) {
				this.writer.append("00");
			} else if (brightValue <= 238) {
				this.writer.append("hh");
			} else {
				this.writer.append("$$");
			}
		} catch (IOException e) {
			System.out.println("An error has occured");
			e.printStackTrace();
		}
	}
	
	private void print_inverted(int brightValue) {
		try {
			if (brightValue <= 17) {
				this.writer.append("$$");
			} else if (brightValue <= 34) {
				this.writer.append("hh");
			} else if (brightValue <= 51) {
				this.writer.append("00");
			} else if (brightValue <= 68) {
				this.writer.append("JJ");
			} else if (brightValue <= 85){
				this.writer.append("XX");
			} else if(brightValue <= 102) {
				this.writer.append("ff");
			} else if (brightValue <= 119) {
				this.writer.append("\\\\");
			} else if (brightValue <= 136) {
				this.writer.append("))");
			} else if (brightValue <= 153) {
				this.writer.append("}}");
			} else if (brightValue <= 170){
				this.writer.append("??");
			} else if(brightValue <= 187) {
				this.writer.append("++");
			} else if (brightValue <= 204) {
				this.writer.append("!!");
			} else if (brightValue <= 221) {
				this.writer.append(";;");
			} else if (brightValue <= 238) {
				this.writer.append("\"\"");
			} else {
				this.writer.append("``");
			}
		} catch (IOException e) {
			System.out.println("An error has occured");
			e.printStackTrace();
		}
	}
	
	private void print_whitespaces(int brightValue) {
		try {
			if (brightValue == -1 || brightValue >= 250) {
				this.writer.append("  ");
			} else if (brightValue <= 17) {
				this.writer.append("``");
			} else if (brightValue <= 34) {
				this.writer.append("\"\"");
			} else if (brightValue <= 51) {
				this.writer.append(";;");
			} else if (brightValue <= 68) {
				this.writer.append("!!");
			} else if (brightValue <= 85){
				this.writer.append("++");
			} else if(brightValue <= 102) {
				this.writer.append("??");
			} else if (brightValue <= 119) {
				this.writer.append("}}");
			} else if (brightValue <= 136) {
				this.writer.append("))");
			} else if (brightValue <= 153) {
				this.writer.append("\\\\");
			} else if (brightValue <= 170){
				this.writer.append("ff");
			} else if(brightValue <= 187) {
				this.writer.append("XX");
			} else if (brightValue <= 204) {
				this.writer.append("JJ");
			} else if (brightValue <= 221) {
				this.writer.append("00");
			} else if (brightValue <= 238) {
				this.writer.append("hh");
			} else {
				this.writer.append("$$");
			}
		} catch (IOException e) {
			System.out.println("An error has occured");
			e.printStackTrace();
		}
	}
	
	private void print_brightmap(int brightValue) {
		try {
			this.writer.append(brightValue+"\t");
		} catch (IOException e) {
			System.out.println("An error has occured");
			e.printStackTrace();
		}
	}
}
