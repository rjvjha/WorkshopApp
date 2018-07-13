package com.example.rjvjha.workshop.data;

import android.provider.BaseColumns;

/**
 * API Contract for the Workshop app.
 */


public class WorkshopContract {

    // To prevent someone from accidentally instantiating the contract class,
    // give it an empty constructor.
    private WorkshopContract(){}

    public static final class WorkshopEntry implements BaseColumns{

        /* Name of the database table */
        public static final String TABLE_NAME = "workshops";

        /*ID column */
        public static final String COL_ID = BaseColumns._ID;

        /*Title Column*/
        public static final String COL_TITLE = "title";

        /*Date-Time Column*/
        public static final String COL_DATE = "dateTime";

        /*Description column*/
        public static final String COL_DESCP = "description";

        /*Apply-Status Column*/
        public static final String COL_APPLY_STATUS = "applied";

        /*Registered student name Column*/
        public static final String COL_REG_Student = "studentName";

        /**
         * Possible values for the status of student.
         */
        public static final int APPLIED_FALSE = 0;
        public static final int APPLIED_TRUE = 1;



    }




}
