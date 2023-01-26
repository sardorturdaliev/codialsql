package uz.sardor.codialapp.fragmets.screenKurs

import android.app.Activity
import androidx.navigation.NavController
import uz.sardor.codialapp.databinding.FragmentKurslarBinding
import uz.sardor.codialapp.dataclass.Course
import uz.sardor.codialapp.extra.GetObj
import uz.sardor.codialapp.fragmets.dbHelper.DBHelper
import uz.sardor.codialapp.fragmets.screenKurs.adapter.CoursesAdapter

class GetAllCourses(
    var activity: Activity,
    val bidnding: FragmentKurslarBinding,
    var findNavCon: NavController
) {


    lateinit var arrayList: ArrayList<Course>
    lateinit var courseAdapter: CoursesAdapter
    lateinit var myDBHelper: DBHelper


    fun getCourses() {
        initData()
        courseAdapter = CoursesAdapter(arrayList, object : CoursesAdapter.RVClickCourses {
            override fun onClick(courses: Course) {
                GetObj.course = courses
                findNavCon.navigate(GetObj.navId)
            }
        })
        bidnding.rvkurs.adapter = courseAdapter
    }

    fun initData() {
        arrayList = ArrayList()
        myDBHelper = DBHelper.getInstance()
        arrayList = myDBHelper.getCourses()
    }
}