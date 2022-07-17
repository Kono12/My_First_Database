package DB;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import RV.DATA;

@Dao
public interface ContactDao {
    @Query(" SELECT * FROM DATA ORDER BY name DESC")
    LiveData <List<DATA>> getAll();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertContact(DATA contact);

    @Delete
    void deleteContact(DATA contact);

    @Update
   void UpdateContact(DATA contact);


}
