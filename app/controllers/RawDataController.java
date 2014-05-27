package controllers;

import Holders.CategoryGroup;
import Holders.VisitObjectGroup;
import Holders.utils.ImageThumbnailPair;
import database.DBConnectionFactory;
import database.ICollection;
import database.RawConnection;
import model.CategoryManager;
import model.VisitObjectManager;
import model.impl.CategoryManagerImpl;
import model.impl.VisitObjectManagerImpl;
import play.mvc.Controller;
import play.mvc.Result;
import utils.ViewManager;
import views.html.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Arrays;

public class RawDataController extends Controller {

	static RawConnection conn = DBConnectionFactory.getRawDatabase();
    static CategoryManager categoryManager = new CategoryManagerImpl();
    static VisitObjectManager visitObjectManager = new VisitObjectManagerImpl();
	public static Result getCategories() {

        String categories = categoryManager.getAllCategoriesRaw();
		return ok(categories);
	}


   // public static Result getViews(String id) {
       // return ok(Arrays.toString(conn.getViews(id)));
  //  }

    public static Result getVisitObjects(String id) {
        String views = visitObjectManager.getObjectsRaw(id);
        return ok(views);
    }
}
