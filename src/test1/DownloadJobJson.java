package test1;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

//code to pull "last_page" value from json file
public class DownloadJobJson {

	long last_page_number;

	public long DownloadJobJson1() {
		JSONParser parser = new JSONParser();
		try {
			Object obj = parser.parse(new FileReader(
					"/home/quicko/Desktop/Files/file1.txt"));

			JSONObject jsonObject = (JSONObject) obj;

			last_page_number = (Long) jsonObject.get("last_page");
			System.out.println(last_page_number);

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();

		}
		return last_page_number;
	}
}
