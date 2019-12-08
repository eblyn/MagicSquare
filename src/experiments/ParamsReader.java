package experiments;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Scanner;

public class ParamsReader {
	HashMap<ParamsName, String> params;
	
	public ParamsReader(String file) throws IOException {
		InputStream in = new FileInputStream(new File(file));
		params = readParams(in);
		in.close();
	}
	
	private HashMap<ParamsName, String> readParams(InputStream in) {
		Scanner scannerInput = new Scanner(in);
		HashMap<ParamsName, String> params = new HashMap<ParamsName, String>();
		while(scannerInput.hasNextLine())
			readParam(scannerInput.nextLine(), params);
		return params;
	}
	
	private void readParam(String line, HashMap<ParamsName, String> params) {
		String[] words = line.split("[\\s]+", 2);
		int nameIndex = nextIndex(words, 0);
		ParamsName param = ParamsName.valueOf(words[nameIndex]);
		String value = words[nextIndex(words, nameIndex + 1)];
		params.put(param, value);
	}
	
	private int nextIndex(String[] words, int actualIndex) {
		int i = actualIndex;
		while(words[i].equals("") && i < words.length){
			i++;
			continue;
		}
		return i;
	}
	
	public String toString() {
		String str = "";
		ParamsName[] param = ParamsName.values();
		for(int i = 0; i < param.length; i++)
			str += params.get(param[i]) + ",";
		return str;
	}
	
	public String getParam(String name) {
		ParamsName paramName = ParamsName.valueOf(name);
		String str = params.get(paramName);
		if(str == null)
			return "";
		return str;
	}
}