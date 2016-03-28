package no.picklepick.flickerswipe.common;

import android.content.Context;

import com.snappydb.DB;
import com.snappydb.DBFactory;
import com.snappydb.SnappydbException;

/**
 * Simple database manager.
 *
 * @author Audun Follegg (audunfo@gmail.com)
 */
public class DatabaseManager {

    private static final String DB_NAME = "instaDb";
    private static final String TOKEN_KEY = "token";
    private static DatabaseManager instance;

    public static DatabaseManager getInstance() {
        if (instance == null) {
            instance = new DatabaseManager();
        }
        return instance;
    }

    /**
     * Save instagram access token.
     *
     * @param token   {@link String}.
     * @param context sender context.
     */
    public void saveToken(String token, Context context) {
        try {
            DB db = DBFactory.open(context, DB_NAME);
            // Save token.
            db.put(TOKEN_KEY, token);

            db.close();
        } catch (SnappydbException e) {
            throw new RuntimeException("Error saving token to DB", e);
        }
    }

    /**
     * Delete instagram access token.
     *
     * @param context sender context.
     */
    public void deleteToken(Context context) {
        try {
            DB db = DBFactory.open(context, DB_NAME);
            // Save token.
            if (db.exists(TOKEN_KEY)) {
                db.del(TOKEN_KEY);
            }

            db.close();
        } catch (SnappydbException e) {
            throw new RuntimeException("Error deleting token", e);
        }
    }

    /**
     * GET instagram access token.
     *
     * @param context sender context
     * @return {@link String}.
     */
    public String getToken(Context context) {
        try {
            DB db = DBFactory.open(context, DB_NAME);

            // Get token
            String token = db.get(TOKEN_KEY);
            db.close();

            return token;
        } catch (SnappydbException e) {
            return null;
        }
    }
}
