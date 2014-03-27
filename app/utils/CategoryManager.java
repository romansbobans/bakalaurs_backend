package utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import Holders.Category;

public class CategoryManager {
	
	private static final String LANG_PREFIX = "lang";
	private static final String CAT_PREFIX = "category";
	private int index = -1;
	
	public List<Category> createCategories(Map<String, String[]> map)
	{
		List<Category> categories = new ArrayList<>();
		
		while(true)
		{
			Category cat = bindCategoryAndLanguage(map);
			if (cat == null)
			{
				break;
			}
			categories.add(cat);
		}
		if (categories.size() == 0)
		{
			throw new IllegalArgumentException("Cannot create categories for list for empty map");
		}
		
		return categories;
	}
	
	private Category bindCategoryAndLanguage(Map<String, String[]> map)
	{
		String postFix = null;
		if (index < 0)
		{
			postFix = "";
		}
		else 
		{
			postFix = "" + index;
		}
		String[] lang = map.get(LANG_PREFIX + postFix);
		String[] cat = map.get(CAT_PREFIX + postFix);
		
		if (lang == null || cat == null)
		{
			return null;
		}
		Category category = new Category(lang[0], cat[0]);
		index++;
		return category;
	}
	
}
