package uz.sardor.codialapp.fragmets.dbHelper

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.widget.Toast
import uz.sardor.codialapp.dataclass.Course
import uz.sardor.codialapp.dataclass.MentorsData

class DBHelper(context: Context) : SQLiteOpenHelper(context, DATABASNAME, null, 3) {
    override fun onCreate(db: SQLiteDatabase?) {
        val query1 =
            "create table $TABLE_COURSES($COURSES_ID integer not null primary key autoincrement unique , $COURSES_NAME text not null unique , $COURSES_ABOUT text not null)"
        val query2 =
            "create table $TABLE_MENTORS($MENTORS_ID integer not null primary key autoincrement unique , $MENTORS_SURNAME text not null , $MENTORS_NAME text not null , $MENTORS_PATRONYMIC text not null , $MENTORS_COURSES_ID integer not null , foreign key ($MENTORS_COURSES_ID) references $TABLE_COURSES($COURSES_ID))"

        db?.execSQL(query1)
        db?.execSQL(query2)

    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {

    }

    //<------------------------------------------ Add Course  Start ---------------------------------------------------
    fun addCourses(courses: Course) {
        val database = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put(COURSES_NAME, courses.name)
        contentValues.put(COURSES_ABOUT, courses.about)
        database.insert(TABLE_COURSES, null, contentValues)

        database.close()

    }


    fun getCourses(): ArrayList<Course> {
        val arrayListCourses = ArrayList<Course>()
        val database = this.readableDatabase
        val query = "select * from $TABLE_COURSES"
        val cursor = database.rawQuery(query, null)
        if (cursor.moveToFirst()) {
            do {
                val courses = Course(
                    cursor.getInt(0),
                    cursor.getString(1),
                    cursor.getString(2)
                )
                arrayListCourses.add(courses)
            } while (cursor.moveToNext())
        }
        return arrayListCourses
    }

    fun deleteKurs(courses: Course) {
        val db = this.writableDatabase
        db.delete(TABLE_COURSES, "$COURSES_ID =?", arrayOf(courses.id.toString()))
    }


    //<------------------------------------------ GET  Course  End --------------------------------------------------


    fun addMentorData(mentors: MentorsData) {
        val db = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put(MENTORS_SURNAME, mentors.surname)
        contentValues.put(MENTORS_NAME, mentors.name)
        contentValues.put(MENTORS_PATRONYMIC, mentors.patronymic)
        contentValues.put(MENTORS_COURSES_ID, mentors.courses!!.id)
        db.insert(TABLE_MENTORS, null, contentValues)
        db.close()
    }

    fun getMentors(): ArrayList<MentorsData> {
        val arrayListMentors = ArrayList<MentorsData>()
        val database = this.readableDatabase
        val query = "select * from $TABLE_MENTORS"
        val cursor = database.rawQuery(query, null)
        if (cursor.moveToFirst()) {
            do {
                val mentors = MentorsData(
                    cursor.getInt(0),
                    cursor.getString(1),
                    cursor.getString(2),
                    cursor.getString(3),
                    getCoursesByID(cursor.getInt(4))
                )
                arrayListMentors.add(mentors)
            } while (cursor.moveToNext())
        }
        return arrayListMentors
    }

    fun getCoursesByID(id: Int): Course {
        val database = this.readableDatabase
        val cursor =
            database.query(
                TABLE_COURSES,
                arrayOf(COURSES_ID, COURSES_NAME, COURSES_ABOUT),
                "$COURSES_ID = ?",
                arrayOf(id.toString()),
                null,
                null,
                null
            )

        cursor.moveToFirst()
        return Course(
            cursor.getInt(0),
            cursor.getString(1),
            cursor.getString(2)
        )
    }

    fun deleteMentor(mentors: MentorsData) {
        val db = this.writableDatabase
        db.delete(TABLE_MENTORS, "$MENTORS_ID = ?", arrayOf(mentors.id.toString()))
    }


    fun upDateMentor(mentors: MentorsData) {
        val db = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put(MENTORS_SURNAME, mentors.surname)
        contentValues.put(MENTORS_NAME, mentors.name)
        contentValues.put(MENTORS_PATRONYMIC, mentors.patronymic)
        contentValues.put(MENTORS_COURSES_ID, mentors.courses!!.id)

        db.update(TABLE_MENTORS, contentValues, "id=?", arrayOf(mentors.id.toString()))

    }


    companion object {
        const val DATABASNAME = "databasenamee"
        const val TABLE_COURSES = "tablecourse"
        const val COURSES_ID = "id"
        const val COURSES_NAME = "name"
        const val COURSES_ABOUT = "aboutsdesc"


        const val TABLE_MENTORS = "mentors"
        const val MENTORS_ID = "id"
        const val MENTORS_SURNAME = "surname"
        const val MENTORS_NAME = "name"
        const val MENTORS_PATRONYMIC = "patronymic"
        const val MENTORS_COURSES_ID = "courses_id"


        private var dbHelper: DBHelper? = null

        fun init(context: Context) {
            if (dbHelper == null) {
                dbHelper = DBHelper(context)
            }
        }

        fun getInstance() = dbHelper!!
    }
}