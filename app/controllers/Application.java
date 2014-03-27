package controllers;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import play.mvc.Controller;
import play.mvc.Result;
import views.html.upload_category;
import views.html.upload_view;
import views.html.*;
import Holders.CategoryGroup;
import Holders.VisitObjectGroup;
import Holders.utils.ImageThumbnailPair;
import database.DBConnectionFactory;
import database.ICollection;

public class Application extends Controller {

	static ICollection coll = DBConnectionFactory.getDatabase()
			.getCollection();
	public static Result index() {

		return ok(views.html.index.render());
	}
	
	public static Result editCategory(String id) {

		CategoryGroup group = coll.getCategory(id);
		System.out.println( group);
		return ok(views.html.edit_category.render(group));
	}
	
	
	public static Result removeCategory(String id) {

		coll.removeCategory(id);
		return redirect(request().getHeader("referer"));
	}

	public static Result removeView(String id) {

		coll.removeObject(id);
		return redirect(request().getHeader("referer"));
	}

	public static Result prepareCategoryPage() {
		CategoryGroup[] groups = coll.getAllCategories();

		return ok(views.html.categories.render(groups));
	}
	
	public static Result prepareViewsPage() {
		VisitObjectGroup[] groups = coll.getAllObjects();
		

		return ok(views.html.view_objects.render(groups));
	}

	public static Result getImage(String name) throws IOException {
		File file = new File(name);
		/*
		 * BufferedImage image = ImageIO.read(file);
		 * 
		 * ImageInputStream is = ImageIO.createImageInputStream(image);
		 * ByteArrayOutputStream baos = new ByteArrayOutputStream();
		 * ImageIO.write(image, "png", baos); ByteArrayInputStream bais = new
		 * ByteArrayInputStream(baos.toByteArray());
		 */
		response().setContentType("image/jpg");
		// BufferedInputStreamReader stream = new BufferedInputStreamReader(new
		// InputStreamReader(file));
		return ok(new FileInputStream(file));
	}
	
	public static Result prepareUploadImagesFOrViewPage(String id) {
		
		ImageThumbnailPair[] pair = coll.getObject(id).getImagePairs();
		return ok(upload_images.render(pair, id));
	}

	public static Result getCategoryUploadForm() {
		return ok(upload_category.render());
	}

}
