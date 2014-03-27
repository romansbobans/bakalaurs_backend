package utils;


import org.json.JSONException;
import org.json.JSONObject;

import Holders.VisitObject;

public class ViewManager {

	public static VisitObject createView(String string) throws JSONException {
		string = string.replaceAll("=", ":");
		JSONObject object = new JSONObject(string);
		
		String objectName = object.getString("viewname");
		String categoryId = object.getString("type");
		String description = object.getString("description");
		String shortDescription = object.getString("shortDescription");
		

		VisitObject visitObj = new VisitObject(objectName, categoryId, description, shortDescription);
		
		int i = 0;
		String workinghrsName = "workinghrs_";
		
		String day = workinghrsName + "day" + i;

		String hrs = workinghrsName + "hrs" + i;
		while(object.has(day) && object.has(hrs))
		{
			System.out.print("Creating workinghr");
			visitObj.addWorkingHrs(visitObj.new WorkingHours(object.getString(day), object.getString(hrs)));
			
			i++;
			day = workinghrsName + "day" + i;

			hrs = workinghrsName + "hrs" + i;
		}
		
		i = 0;
		String groups = "groups_";
		
		String group = groups + "group" + i;

		String price = groups + "price" + i;
		while(object.has(group) && object.has(price))
		{
			visitObj.addGroup(visitObj.new Groups(object.getString(group), object.getString(price)));
			
			i++;
			group = groups + "day" + i;

			price = groups + "hrs" + i;
		}
		
		
		return visitObj;
		
	}

}
