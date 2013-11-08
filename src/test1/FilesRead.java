package test1;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

//Class to read all the JSON files and print consolidated data in a file after sorting as per createDate variable

public class FilesRead {

	/**
	 * @param args
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub

		// Finding the number of files in the folder
		int files = new File("/home/quicko/Desktop/Files").listFiles().length;

		Set<Pojo> p = new TreeSet<Pojo>(); // passing data of a files into set

		for (int size = 1; size <= files; size++) { // read files contained in
													// folder

			JSONParser parser = new JSONParser();

			try {

				Object obj = parser.parse(new FileReader(
						"/home/quicko/Desktop/Files/file" + size + ".txt")); // parsing
																				// file
																				// by
																				// file
				JSONObject jsonObject = (JSONObject) obj;
				// Parsing the parent element in Json file which is array

				JSONArray start = (JSONArray) jsonObject.get("startups");
				// reading Json objects into variables and placing them in
				// Treeset inorder to sort the data.

				for (int i = 0; i < start.size(); i++) {
					JSONObject c = (JSONObject) start.get(i);
					String angelUrl = (String) c.get("angellist_url");
					if (angelUrl != null) {
						String companyUrl = (String) c.get("company_url");
						if (companyUrl == null) {
							companyUrl = " ";
						}// passing space if company url is null
						String companyName = (String) c.get("name");
						String createDate = (String) c.get("created_at");

						// passing data into tree set for sorting
						p.add(new Pojo(angelUrl, companyUrl, companyName,
								createDate));

					}// close of checking the correct record and passing it into
						// tree

				} // close of parsing Json array

			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} catch (ParseException e) {
				e.printStackTrace();
			}// end of try-catch block

		}// end of for-loop

		/*
		 * Creating new file and writing the Consolidated data into that file
		 * from from the Sorted Set. Due to requirement comparing existing list
		 * of company names in a file with new data and placing the consolidated
		 * list of companyName,companyUrl and angel list link in a file
		 */

		Set<String> hashexist = new HashSet<String>();

		BufferedReader br = new BufferedReader(new FileReader(
				"/home/quicko/Desktop/angel-checked.txt"));
		String line;
		while ((line = br.readLine()) != null) {

			hashexist.add(line);
		}
		br.close();

		// new file creation to write data
		File file1 = new File("/home/quicko/Desktop/Files10.txt");
		BufferedWriter writer = new BufferedWriter(new FileWriter(file1));
		int count = 0;
		Iterator<Pojo> it = p.iterator();
		while (it.hasNext()) {

			Pojo p3 = it.next();

			if (hashexist.contains(p3.getCompanyName())) { // checking with
															// existing company
															// names
				count += 1;
			} else {
				writer.write(p3.getCompanyName());
				writer.write("\t");
				writer.write(p3.getCompanyUrl());
				writer.write("\t");
				writer.write(p3.getAngelUrl());
				writer.write("\t");
				writer.write(p3.getCreateDate());
				writer.newLine();
			}

		} // end of while
		System.out.println(count);
		writer.close();

		System.out.println("done");

	}

}
