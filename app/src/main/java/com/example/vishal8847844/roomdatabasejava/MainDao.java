package com.example.vishal8847844.roomdatabasejava;



import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

import static androidx.room.OnConflictStrategy.REPLACE;


@Dao
public interface MainDao {
    @Insert(onConflict = REPLACE)
    void insert(MainData mainData);

    //Delete Query
    @Delete
    void delete(MainData mainData);

    //delete all query

    @Delete
    void reset (List<MainData> mainData);

    //Update query
    @Query("UPDATE table_name SET text=:sText Where ID=:sID ")
    void  update(int sID,String sText);

    //get all DataQuery
    @Query("Select * FROM table_name")
    List<MainData> getAll();

//https://www.youtube.com/watch?v=GlzbOjzEhc0
}
