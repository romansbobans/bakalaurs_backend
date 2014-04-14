package controllers;

import Holders.CategoryGroup;
import Holders.VisitObjectGroup;
import Holders.utils.ImageThumbnailPair;
import database.DBConnectionFactory;
import database.ICollection;
import database.RawConnection;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Arrays;

public class RawDataController extends Controller {

	static RawConnection conn = DBConnectionFactory.getRawDatabase();
	public static Result getCategories() {

        String[] categories = conn.getCategories();
		return ok(Arrays.toString(categories));
	}


    public static Result getViews(String id) {
        return ok(Arrays.toString(conn.getViews(id)));
    }
}
