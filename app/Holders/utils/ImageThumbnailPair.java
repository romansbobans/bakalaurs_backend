package Holders.utils;

public class ImageThumbnailPair {

	String path;
	String thumbnailPath;
	
	public ImageThumbnailPair(String path, String thumbnailPath) {
		super();
		this.path = path;
		this.thumbnailPath = thumbnailPath;
	}
	public String getPath() {
		return path;
	}
	public String getThumbnailPath() {
		return thumbnailPath;
	}
	
	
	public void setPath(String path) {
		this.path = path;
	}
	public void setThumbnailPath(String thumbnailPath) {
		this.thumbnailPath = thumbnailPath;
	}
	@Override
	public String toString() {
		return "ImageThumbnailPair [path=" + path + ", thumbnailPath="
				+ thumbnailPath + "]";
	}
	
}
