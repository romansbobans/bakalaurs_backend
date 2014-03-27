package database;

import java.util.List;

import Holders.CategoryGroup;
import Holders.VisitObject;
import Holders.VisitObjectGroup;
import Holders.utils.ImageThumbnailPair;

public interface Model {
	
	boolean uploadCategory(CategoryGroup cat);
	
	CategoryGroup[] getAllCategories();
	
	CategoryGroup getCategory(String id);

	boolean updateCategory(CategoryGroup cat, String oldId);
	
	boolean removeCategory(String id);
	
	boolean updateCategory(CategoryGroup g);
	
	
	boolean uploadObject(VisitObject object);
	
	VisitObjectGroup[] getAllObjects();
	
	VisitObjectGroup getObjectGroup(CategoryGroup group);
	
	VisitObjectGroup getObject(String id);
	
	boolean updateObject(VisitObject object, String id);
	
	boolean addImagesToObject(VisitObjectGroup group, ImageThumbnailPair... imagePath);
	

	boolean addImagesToObject(String groupId, List<ImageThumbnailPair> imagePath);
	
	boolean removeObject(String id);
	
	
	
}
