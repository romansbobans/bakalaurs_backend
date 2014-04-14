package database;

/**
 * Created by TAHKICT on 14/04/14.
 */
public interface RawConnection {

    String[] getCategories();

    String[] getViews(String id);
}
