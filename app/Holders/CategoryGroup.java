package Holders;

import java.util.Iterator;
import java.util.List;

import com.google.gson.annotations.SerializedName;

public class CategoryGroup implements Iterator<Category>{
	@SerializedName("object_description")
private List<Category> categories;
	@SerializedName("thumb_url")
private String image;
int iterIndex = 0;

private String id;


public CategoryGroup(List<Category> categories, String image) {
	this.categories = categories;
	this.image = image;
	this.id = categories.get(0).getType();
}



public String getImage() {
	return image;
}



public void setImage(String image) {
	this.image = image;
}



public List<Category> getCategories() {
	return categories;
}

@Override
public boolean hasNext() {
	return iterIndex < categories.size();
}

@Override
public Category next() {
	return categories.get(iterIndex++);
}

@Override
public void remove() {
	throw new UnsupportedOperationException("Cannot call remove");
}



@Override
public String toString() {
	return "CategoryGroup [categories=" + categories + ", image=" + image + "]";
}


public void setId(String id) {
	this.id = id;
}


public String getId() {
	// TODO Auto-generated method stub
	return id;
}



public void setCategories(List<Category> categories) {
	// TODO Auto-generated method stub
	this.categories = categories;
	
}


}
