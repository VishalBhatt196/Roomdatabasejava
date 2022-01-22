package com.example.vishal8847844.roomdatabasejava;



import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

//define table name
@Entity(tableName = "table_name")
public class MainData implements Serializable {



    @PrimaryKey(autoGenerate = true)
    private int ID;

    //create text colomn
    @ColumnInfo(name="text")
    private String text;

    //generate  getter setter
    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
