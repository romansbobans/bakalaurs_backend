package controllers;


import Exceptions.FileTooLargeException;
import Holders.Category;
import Holders.CategoryGroup;
import Holders.VisitObject;
import Holders.utils.ImageThumbnailPair;
import database.DBConnectionFactory;
import database.ICollection;
import org.json.JSONException;
import play.Logger;
import play.data.DynamicForm;
import play.data.Form;
import play.mvc.BodyParser;
import play.mvc.BodyParser.Json;
import play.mvc.Controller;
import play.mvc.Http.MultipartFormData;
import play.mvc.Result;
import utils.CategoryManager;
import utils.FileManager;
import utils.ViewManager;
import views.html.upload_view;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public class UploadController extends Controller {

	static ICollection coll = DBConnectionFactory.getDatabase().getCollection();

	public static Result uploadCategory() {
		MultipartFormData body = request().body().asMultipartFormData();

		final Map<String, String[]> values = body.asFormUrlEncoded();// .toString();

		List<Category> categories = new CategoryManager()
				.createCategories(values);

		CategoryGroup group = null;
		try {

			List<String> files = FileManager.createThumbnails(body, "categories/");

			group = new CategoryGroup(categories, files.get(0));

			DBConnectionFactory.getDatabase().getCollection()
					.uploadCategory(group);
		} catch (FileTooLargeException e) {

            e.printStackTrace();
		}

		catch (Exception exception) {
            exception.printStackTrace();
            StringBuilder b = new StringBuilder();
			b.append(exception.getMessage());
			for (StackTraceElement element : exception.getStackTrace())
				b.append(element.toString()).append('\n');
			return ok(b.toString());
		}

		return Application.index();

	}
	@BodyParser.Of(Json.class)
	public static Result uploadView() {
		request().body().asJson();
        DynamicForm requestData = Form.form().bindFromRequest();
        String reqJSON = requestData.data().toString();

        try {
			VisitObject object = ViewManager.createView(reqJSON);
			DBConnectionFactory.getDatabase().getCollection().uploadObject(object);
		} catch (JSONException e) {
			StringBuilder b = new StringBuilder();
			b.append(e.getMessage());
			for (StackTraceElement element : e.getStackTrace())
				b.append(element.toString()).append('\n');
			b.append(reqJSON.replaceAll("=", ":"));
			return badRequest();
		}
        System.out.print("UPLOADED VIEW, RETURNING");
		return ok("/");//Application.index();
	}

	public static Result uploadEditedCategory(String id) {

		MultipartFormData body = request().body().asMultipartFormData();

		// Old category
		CategoryGroup group = coll.getCategory(id);
		final Map<String, String[]> values = body.asFormUrlEncoded();// .toString();


        Logger.error("--------\n" + values.toString());

		List<Category> categories = new CategoryManager()
				.createCategories(values);


        Logger.error("--------\n" + categories.toString());
			group.setCategories(categories);
			
			DBConnectionFactory.getDatabase().getCollection()
					.updateCategory(group);

		return Application.index();
	}
	
	public static Result prepareUploadViewForm()
	{
		
		CategoryGroup[] groups = coll.getAllCategories();
		
		String[] ids = new String[groups.length];
		int counter = 0;
		for (CategoryGroup g : groups)
		{
			ids[counter] = g.getId();
			counter++;
		}
		return ok(upload_view.render(ids, routes.UploadController.uploadView().url().toString()));
	}
	
	public static Result uploadImagesForView(String id) {
		
		MultipartFormData fdata = request().body().asMultipartFormData();
		
		try {
			List<ImageThumbnailPair> files = FileManager.createImageThumbnailPair(fdata, "visitobjects/");
			coll.addImagesToObject(id, files);
			return ok(files.toString());
		} catch (IOException e) {
			StringBuilder b = new StringBuilder();
			b.append(e.getMessage());
			for (StackTraceElement element : e.getStackTrace())
				b.append(element.toString()).append('\n');
			return ok(b.toString());
		}
		
	}
}
