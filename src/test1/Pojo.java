package test1;

/* Pojo class with angelUrl,companyUrl,companyName and Create date and sorting the data by createDate in ascending order */

public class Pojo implements Comparable<Pojo> {

	private String angelUrl, companyUrl, companyName, createDate;

	Pojo(String angelUrl, String companyUrl, String companyName,
			String createDate) {
		this.angelUrl = angelUrl;
		this.companyUrl = companyUrl;
		this.companyName = companyName;
		this.createDate = createDate;
	}

	public String getAngelUrl() {
		return angelUrl;
	}

	public String getCompanyUrl() {
		return companyUrl;
	}

	public String getCompanyName() {
		return companyName;
	}

	public String getCreateDate() {
		return createDate;
	}

	@Override
	public String toString() {
		return angelUrl + "\t" + companyUrl + "\t" + companyName + "\t"
				+ createDate;

	}

	@Override
	public int compareTo(Pojo o) {
		if (this.createDate.equals(o.createDate)) {
			return this.companyName.compareTo(o.companyName);
		}

		return this.createDate.compareTo(o.createDate);

	}

}
