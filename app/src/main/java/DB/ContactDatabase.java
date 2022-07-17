package DB;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import RV.DATA;

@Database(entities = {DATA.class}, version = 1)
public abstract class ContactDatabase extends RoomDatabase {
    public abstract ContactDao contactDao();

    private static volatile ContactDatabase INSTANCE;

    public static ContactDatabase getInstance(Context context) {
        if (INSTANCE == null) {
            synchronized (ContactDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(), ContactDatabase.class, "Contact_db").build();
                    ;
                }
            }
        }
        return INSTANCE;
    }


}
