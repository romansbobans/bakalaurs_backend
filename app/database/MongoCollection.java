package database;


import java.io.File;
import java.util.Iterator;
import java.util.List;

import utils.MongoFieldNames;
import Holders.Category;
import Holders.CategoryGroup;
import Holders.VisitObject;
import Holders.VisitObjectGroup;
import Holders.utils.ImageThumbnailPair;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.mongodb.BasicDBList;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.WriteResult;

class MongoCollection implements ICollection {

	DB db;

	DBCollection categoryCollection;
	DBCollection viewsCollection;

	public MongoCollection(DB db) {
		this.db = db;
		categoryCollection = db
				.getCollection(DBConstants.CATEGORIES_COLLECTION);
		viewsCollection = db.getCollection(DBConstants.VIEWS_COLLECTION);
	}

	@Override
	public boolean uploadCategory(CategoryGroup categories) {

		if (categoryCollection.find(
				new BasicDBObject(MongoFieldNames.ID, categories.getId()))
				.hasNext()) {
			File file = new File(categories.getImage());
			file.delete();
			return false;
		}

		categoryCollection.insert(createCategoryObject(categories));
		return false;
	}

	private DBObject createCategoryObject(CategoryGroup categories) {
		BasicDBList object = new BasicDBList();
		while (categories.hasNext()) {
			Category cat = categories.next();
			object.add(new BasicDBObject(MongoFieldNames.Categories.LANG, cat
					.getLanguage()).append(MongoFieldNames.Categories.NAME,
					cat.getType()));
		}
		BasicDBObject obj = new BasicDBObject(
				MongoFieldNames.Categories.THUMB_URL, categories.getImage())
				.append(MongoFieldNames.ID, categories.getId()).append(
						MongoFieldNames.Categories.OBJ_DESCR, object);

		return obj;
	}

	@Override
	public CategoryGroup[] getAllCategories() {
		DBCursor cursor = categoryCollection.find();

		CategoryGroup[] group = new CategoryGroup[cursor.count()];
		int i = 0;
		Gson gson = new GsonBuilder().create();
		while (cursor.hasNext()) {
			DBObject object = cursor.next();

			CategoryGroup g = gson.fromJson(object.toString(),
					CategoryGroup.class);

			group[i++] = g;

		}
		return group;
	}

	@Override
	public CategoryGroup getCategory(String id) {
		// TODO Auto-generated method stub
		Gson gson = new GsonBuilder().create();
		DBObject object = categoryCollection.findOne(new BasicDBObject(
				MongoFieldNames.ID, id));
		CategoryGroup g = gson.fromJson(object.toString(), CategoryGroup.class);
		return g;
	}

	@Override
	public boolean updateCategory(CategoryGroup cat, String oldId) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean removeCategory(String id) {
		// TODO Auto-generated method stub
		DBObject obj = categoryCollection.findAndRemove(new BasicDBObject(
				MongoFieldNames.ID, id));
		String fileName = obj.get(MongoFieldNames.Categories.THUMB_URL)
				.toString();
		if (fileName != null) {
			File file = new File(fileName);
			file.delete();
		}
		return false;
	}

	@Override
	public boolean updateCategory(CategoryGroup g) {
		categoryCollection.findAndModify(new BasicDBObject(MongoFieldNames.ID,
				g.getId()), createCategoryObject(g));
		return false;
	}

	@Override
	public boolean uploadObject(VisitObject object) {
		// TODO Auto-generated method stub
		DBObject obj = viewsCollection.findOne(new BasicDBObject(MongoFieldNames.OBJECT_ARRAY + "." + MongoFieldNames.Views.OBJECT_NAME, object.getObjectName()));
		if (obj != null)
			return false;
		
		BasicDBObject dbObject = new BasicDBObject();
		
		dbObject.append(MongoFieldNames.ID, object.getObjectName());

		dbObject.append(MongoFieldNames.CATEGORY_ID, object.getCategoryId());
	
		BasicDBList list = new BasicDBList(); list.add(createVisitObject(object));
		dbObject.append(MongoFieldNames.OBJECT_ARRAY, list);
	
		viewsCollection.insert(dbObject);
		return false;
	}

	private DBObject createVisitObject(VisitObject visitObj) {
		BasicDBList object = new BasicDBList();
		BasicDBList groupsObject = new BasicDBList();
		Iterator<VisitObject.WorkingHours> hrsIt = visitObj.getHours()
				.iterator();
		while (hrsIt.hasNext()) {
			VisitObject.WorkingHours cat = hrsIt.next();
			object.add(new BasicDBObject(MongoFieldNames.Views.WorkingHrs.DAY,
					cat.getDay()).append(
					MongoFieldNames.Views.WorkingHrs.HOURS, cat.getHours()));
		}

		Iterator<VisitObject.Groups> groups = visitObj.getGroups().iterator();
		while (groups.hasNext()) {
			VisitObject.Groups cat = groups.next();
			groupsObject.add(new BasicDBObject(MongoFieldNames.Views.Groups.GROUP,
					cat.getGroup()).append(MongoFieldNames.Views.Groups.PRICE,
					cat.getPrice()));
		}
		BasicDBObject obj = new BasicDBObject(
				MongoFieldNames.Views.OBJECT_DESCRIPTION,
				visitObj.getObjectDescription())
				.append(MongoFieldNames.Views.OBJECT_NAME,
						visitObj.getObjectName()).append(MongoFieldNames.Views.OBJECT_SHORT_DESCRIPTION, visitObj.getShortDescription())
				.append(MongoFieldNames.Views.OBJECT_HOURS, object).append(MongoFieldNames.Views.OBJECT_GROUPS, groupsObject);

		return obj;
	}

	@Override
	public VisitObjectGroup[] getAllObjects() {
		DBCursor visitObjects = viewsCollection.find();
		VisitObjectGroup[] retrievedObjects = new VisitObjectGroup[visitObjects.count()];
		Gson gson = new GsonBuilder().create();
		int counter = 0;
		while (visitObjects.hasNext())
		{

			String next = visitObjects.next().toString();
			System.out.println(next);
		VisitObjectGroup g = gson.fromJson(next, VisitObjectGroup.class);
		
		retrievedObjects[counter++] = g;
		
		System.out.println(g);
		}
		
		return retrievedObjects;
	}

	@Override
	public VisitObjectGroup getObject(String id) {

		DBObject object = viewsCollection.findOne(new BasicDBObject(MongoFieldNames.ID, id));
		
		Gson gson = new GsonBuilder().create();
		VisitObjectGroup group = gson.fromJson(object.toString(), VisitObjectGroup.class);
		return group;
	}

	@Override
	public boolean updateObject(VisitObject object, String id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean removeObject(String id) {
		DBObject object = viewsCollection.findAndRemove(new BasicDBObject(MongoFieldNames.ID, id));
		
		String imagePair = object.get(MongoFieldNames.Views.PAIR_ARRAY).toString();
		Gson gson = new GsonBuilder().create();
		
		ImageThumbnailPair[] pairs = gson.fromJson(imagePair, ImageThumbnailPair[].class);
		for (ImageThumbnailPair p : pairs)
		{
			File file = new File(p.getPath());
			file.delete();

			file = new File(p.getThumbnailPath());
			file.delete();
		}
		return false;
	}

	@Override
	public VisitObjectGroup getObjectGroup(CategoryGroup group) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean addImagesToObject(VisitObjectGroup group,
			ImageThumbnailPair... imagePath) {
		return false;
	}

	@Override
	public boolean addImagesToObject(String groupId,List<ImageThumbnailPair> imagePath) {
		BasicDBList list = new BasicDBList();
		for (ImageThumbnailPair pair : imagePath)
		{
			list.add(new BasicDBObject(MongoFieldNames.Views.ImagePairs.PATH, pair.getPath())
			.append(MongoFieldNames.Views.ImagePairs.THUMB_PATH, pair.getThumbnailPath()));
		}
		
		  BasicDBObject updateCommand = new BasicDBObject();
		    updateCommand.append( "$set", new BasicDBObject( "imagePairs", list ) );
		    WriteResult result = viewsCollection.update( new BasicDBObject(MongoFieldNames.ID, groupId), updateCommand, true, true );
		
	//	viewsCollection.
		return true;
	}

}
