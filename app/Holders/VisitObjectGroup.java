package Holders;

import java.util.Arrays;

import Holders.utils.ImageThumbnailPair;

import com.google.gson.annotations.SerializedName;

public class VisitObjectGroup {
	
	String id;
	@SerializedName("category_id")
	String categoryId;
	
	ImageThumbnailPair[] imagePairs = new ImageThumbnailPair[0];
	
	VisitObject[] objects;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(String categoryId) {
		this.categoryId = categoryId;
	}

	public ImageThumbnailPair[] getImagePairs() {
		return imagePairs;
	}

	public void setImagePairs(ImageThumbnailPair[] imagePairs) {
		this.imagePairs = imagePairs;
	}

	public VisitObject[] getObjects() {
		return objects;
	}

	public void setObjects(VisitObject[] objects) {
		this.objects = objects;
	}

	@Override
	public String toString() {
		return "VisitObjectGroup [id=" + id + ", categoryId=" + categoryId
				+ ", imagePairs=" + Arrays.toString(imagePairs) + ", objects="
				+ Arrays.toString(objects) + "]";
	}
	
	
	
	

}
