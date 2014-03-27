package Holders;

import com.google.gson.annotations.SerializedName;

public class Category {
	int id;
	@SerializedName("lang")
	String language;
	@SerializedName("name")
	String type;
	
	public Category(String language, String type) {
		this.language = language;
		this.type = type;
	}

	public Category(int id, String language, String type) {
		this.id = id;
		this.language = language;
		this.type = type;
	}

	public int getId() {
		return id;
	}




	public void setId(int id) {
		this.id = id;
	}




	public String getLanguage() {
		return language;
	}




	public void setLanguage(String language) {
		this.language = language;
	}




	public String getType() {
		return type;
	}




	public void setType(String type) {
		this.type = type;
	}




	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		result = prime * result
				+ ((language == null) ? 0 : language.hashCode());
		result = prime * result + ((type == null) ? 0 : type.hashCode());
		return result;
	}




	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Category other = (Category) obj;
		if (id != other.id)
			return false;
		if (language == null) {
			if (other.language != null)
				return false;
		} else if (!language.equals(other.language))
			return false;
		if (type == null) {
			if (other.type != null)
				return false;
		} else if (!type.equals(other.type))
			return false;
		return true;
	}




	@Override
	public String toString() {
		return "Category [id=" + id + ", language=" + language + ", type="
				+ type + "]";
	}
}
