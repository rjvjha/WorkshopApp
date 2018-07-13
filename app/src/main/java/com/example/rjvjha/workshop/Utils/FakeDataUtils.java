package com.example.rjvjha.workshop.Utils;


import android.content.ContentValues;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.rjvjha.workshop.data.WorkshopContract;

import java.util.ArrayList;
import java.util.List;

// This class inserts fake data into the database
public class FakeDataUtils {

    private FakeDataUtils(){}

    public static void insertDummyData(SQLiteDatabase db){
        if(db == null){
            return;
        }

        // create a list of fake workshops
        List<ContentValues> list = new ArrayList<>();
        // 1
        ContentValues cv = new ContentValues();
        cv.put(WorkshopContract.WorkshopEntry.COL_TITLE, "Android Workshop");
        cv.put(WorkshopContract.WorkshopEntry.COL_DATE, "11 July 2018");
        cv.put(WorkshopContract.WorkshopEntry.COL_DESCP, "This workshop focuses on how to use Android OS for building your" +
                "own Android Application. Only the basic knowledge of programming is required for Android App Development," +
                "you do not have to be a geek for it! The workshop will start from the basics like designing layouts and " +
                "building complex layouts. Once the basics of Android are done we will begin with building Apps.");
        cv.put(WorkshopContract.WorkshopEntry.COL_APPLY_STATUS, WorkshopContract.WorkshopEntry.APPLIED_FALSE);

        cv.put(WorkshopContract.WorkshopEntry.COL_REG_Student, "Rajeev ");
        list.add(cv);
        // 2
        cv = new ContentValues();
        cv.put(WorkshopContract.WorkshopEntry.COL_TITLE, "Java Workshop");
        cv.put(WorkshopContract.WorkshopEntry.COL_DATE, "12 July 2018");
        cv.put(WorkshopContract.WorkshopEntry.COL_DESCP, "This workshop mainly focuses on the students eager to learn Java from Basic. " +
                "Java is one of the world's most important and widely used computer languages, and it has held this distinction for many years." +
                " Unlike some other computer languages whose influence has weared with passage of time, while Java's has grown." +
                " In this two days workshop we will cover all basic oops concept");
        cv.put(WorkshopContract.WorkshopEntry.COL_APPLY_STATUS, WorkshopContract.WorkshopEntry.APPLIED_FALSE);
        cv.put(WorkshopContract.WorkshopEntry.COL_REG_Student, "Rajeev");
        list.add(cv);

        // 3
        cv = new ContentValues();
        cv.put(WorkshopContract.WorkshopEntry.COL_TITLE, "Python Workshop");
        cv.put(WorkshopContract.WorkshopEntry.COL_DATE, "15 July 2018");
        cv.put(WorkshopContract.WorkshopEntry.COL_DESCP, "Python is a high-level, interpreted, interactive and object-oriented scripting language." +
                " Python is designed to be highly readable. " +
                "It uses English keywords frequently where as other languages use punctuation, and " +
                "it has fewer syntactical constructions than other languages.");
        cv.put(WorkshopContract.WorkshopEntry.COL_APPLY_STATUS, WorkshopContract.WorkshopEntry.APPLIED_FALSE);
        cv.put(WorkshopContract.WorkshopEntry.COL_REG_Student, "Rajeev");
        list.add(cv);

        // 4
        cv = new ContentValues();
        cv.put(WorkshopContract.WorkshopEntry.COL_TITLE, "IoT Workshop");
        cv.put(WorkshopContract.WorkshopEntry.COL_DATE, "28 July 2018");
        cv.put(WorkshopContract.WorkshopEntry.COL_DESCP, "A workshop on \"INTERNET OF THINGS\" presented by i3indya Technologies. Internet of Things is a new revolution of the Internet. ... " +
                "A device become a smart device is called IOT. " +
                "Now a days there are many android and server based application");
        cv.put(WorkshopContract.WorkshopEntry.COL_APPLY_STATUS, WorkshopContract.WorkshopEntry.APPLIED_FALSE);
        cv.put(WorkshopContract.WorkshopEntry.COL_REG_Student, "Rajeev");
        list.add(cv);

        // 5
        cv = new ContentValues();
        cv.put(WorkshopContract.WorkshopEntry.COL_TITLE, "Android App Development");
        cv.put(WorkshopContract.WorkshopEntry.COL_DATE, "30 July 2018");
        cv.put(WorkshopContract.WorkshopEntry.COL_DESCP, "This workshop focuses on how to use Android OS for building your" +
                " own Android Application. Only the basic knowledge of programming is required for Android App Development, " +
                "you do not have to be a geek for it! The workshop will start from the basics like designing layouts and " +
                "building complex layouts. Once the basics of Android are done we will begin with building Apps.");
        cv.put(WorkshopContract.WorkshopEntry.COL_APPLY_STATUS, WorkshopContract.WorkshopEntry.APPLIED_FALSE);
        cv.put(WorkshopContract.WorkshopEntry.COL_REG_Student, "Rajeev");
        list.add(cv);

        // 6
        cv = new ContentValues();
        cv.put(WorkshopContract.WorkshopEntry.COL_TITLE, "ML Workshop");
        cv.put(WorkshopContract.WorkshopEntry.COL_DATE, "1 Aug 2018");
        cv.put(WorkshopContract.WorkshopEntry.COL_DESCP, "These machine learning workshop present the basics behind the application of modern machine learning algorithms." +
                " We will discuss a framework for reasoning about when to apply various machine learning techniques," +
                " emphasizing questions of over-fitting/under-fitting, regularization, interpretability, " +
                "supervised/unsupervised methods, and handling of missing data. ");
        cv.put(WorkshopContract.WorkshopEntry.COL_APPLY_STATUS, WorkshopContract.WorkshopEntry.APPLIED_FALSE);
        cv.put(WorkshopContract.WorkshopEntry.COL_REG_Student, "Rajeev");
        list.add(cv);
        // 7
        cv = new ContentValues();
        cv.put(WorkshopContract.WorkshopEntry.COL_TITLE, "Blockchain Workshop");
        cv.put(WorkshopContract.WorkshopEntry.COL_DATE, "4 Aug 2018");
        cv.put(WorkshopContract.WorkshopEntry.COL_DESCP, "The block chain is a shared public ledger on which the entire Bitcoin network relies." +
                " All confirmed transactions are included in the block chain. This way, Bitcoin wallets can calculate their spendable balance" +
                " and new transactions can be verified to be spending bitcoins that are actually owned by the spender." +
                " The integrity and the chronological order of the block chain are enforced with cryptography.");
        cv.put(WorkshopContract.WorkshopEntry.COL_APPLY_STATUS, WorkshopContract.WorkshopEntry.APPLIED_FALSE);
        cv.put(WorkshopContract.WorkshopEntry.COL_REG_Student, "Rajeev");
        list.add(cv);


        // 8
        cv = new ContentValues();
        cv.put(WorkshopContract.WorkshopEntry.COL_TITLE, "Robotics Workshop");
        cv.put(WorkshopContract.WorkshopEntry.COL_DATE, "5 Aug 2018");
        cv.put(WorkshopContract.WorkshopEntry.COL_DESCP, "This workshop mainly focuses on the students eager to learn Robotics from Basic." +
                " HC-05 / HC-06 based Bluetooth Controlled Robot works in heeding with the commands sent from Android based Smart Phone using " +
                "a Developed Android Application and Bluetooth Technology.");
        cv.put(WorkshopContract.WorkshopEntry.COL_APPLY_STATUS, WorkshopContract.WorkshopEntry.APPLIED_FALSE);
        cv.put(WorkshopContract.WorkshopEntry.COL_REG_Student, "Rajeev");
        list.add(cv);

        // 9
        cv = new ContentValues();
        cv.put(WorkshopContract.WorkshopEntry.COL_TITLE, "Big Data Workshop");
        cv.put(WorkshopContract.WorkshopEntry.COL_DATE, "6 Aug 2018");
        cv.put(WorkshopContract.WorkshopEntry.COL_DESCP, "This workshop mainly focuses on the students eager to learn Robotics from Basic." +
                " HC-05 / HC-06 based Bluetooth Controlled Robot works in heeding with the commands sent from Android based Smart Phone" +
                " using a Developed Android Application and Bluetooth Technology.");
        cv.put(WorkshopContract.WorkshopEntry.COL_APPLY_STATUS, WorkshopContract.WorkshopEntry.APPLIED_FALSE);
        cv.put(WorkshopContract.WorkshopEntry.COL_REG_Student, "Rajeev");
        list.add(cv);

        // 10
        cv = new ContentValues();
        cv.put(WorkshopContract.WorkshopEntry.COL_TITLE, "IOS Dev Workshop");
        cv.put(WorkshopContract.WorkshopEntry.COL_DATE, "26 Sept 2018");
        cv.put(WorkshopContract.WorkshopEntry.COL_DESCP, "Start Developing iOS Apps (Swift) is the perfect starting point for creating apps that run on iPhone and iPad." +
                " View this set of incremental lessons as a guided introduction to building your first app—including the tools, major concepts, " +
                "and best practices that will ease your path.");
        cv.put(WorkshopContract.WorkshopEntry.COL_APPLY_STATUS, WorkshopContract.WorkshopEntry.APPLIED_FALSE);
        cv.put(WorkshopContract.WorkshopEntry.COL_REG_Student, "Rajeev");
        list.add(cv);

        try {
            db.beginTransaction();
            // clear the tables first
            db.delete(WorkshopContract.WorkshopEntry.TABLE_NAME,null, null);
            //go through the list and add one by one
            for(ContentValues c:list){
                db.insert(WorkshopContract.WorkshopEntry.TABLE_NAME, null, c);
            }
            db.setTransactionSuccessful();
        } catch (SQLException e){
            Log.e("", "SqlException",e);

        }finally {
            db.endTransaction();
        }

    }
}
