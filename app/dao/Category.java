package dao;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Romans on 07/04/14.
 */
public class Category {
    String id;
    String thumb_url;

    @SerializedName("object_description")
    Description[] objectDescription;

    public class Description
    {
        public String lang;
        public String name;


    }

