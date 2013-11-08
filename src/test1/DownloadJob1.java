package test1;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URI;

//Class to pull Json data from angel.co API for Indian startup and place it in a local folder page wise in files.
public class DownloadJob1 {
	public static void main(String args[]) throws MalformedURLException,
			IOException {

		System.out.println("Start downloading files from server...");
		int i = 1;
		long pagenumber = 0;

		// Reading Json data from Rest API and paginating it.
		do {
			// change the URL as per the requirement and also paginating it
			String libURL = "https://api.angel.co/1/tags/1647/startups?page="
					+ i;
			InputStream in = URI.create(libURL).toURL().openStream();
			// writing each page into a seperate file
			FileOutputStream fout = new FileOutputStream(
					"/home/quicko/Desktop/Files/file" + i + ".txt");
			byte data[] = new byte[1024];
			int count;
			while ((count = in.read(data, 0, 1024)) != -1) {
				fout.write(data, 0, count);
			}// end of while
			if (i == 1) {
				DownloadJobJson db = new DownloadJobJson(); // code to pull
															// "last_page" value
															// from json file
				pagenumber = db.DownloadJobJson1();
			}// end of if
			i = i + 1;
		} while (i <= pagenumber); // end of do-while()
	} // end of main

}// end of class

