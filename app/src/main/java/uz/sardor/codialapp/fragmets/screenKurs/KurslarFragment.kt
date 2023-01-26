package uz.sardor.codialapp.fragmets.screenKurs

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CursorAdapter
import androidx.appcompat.app.AlertDialog
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import uz.sardor.codialapp.R
import uz.sardor.codialapp.databinding.CustomDialogAddBinding
import uz.sardor.codialapp.databinding.FragmentKurslarBinding
import uz.sardor.codialapp.dataclass.Course
import uz.sardor.codialapp.extra.GetObj
import uz.sardor.codialapp.fragmets.dbHelper.DBHelper
import uz.sardor.codialapp.fragmets.screenKurs.adapter.CoursesAdapter
import java.util.*
import kotlin.collections.ArrayList


class KurslarFragment : Fragment(), CoursesAdapter.RVClickCourses {
    private val binding by lazy { FragmentKurslarBinding.inflate(layoutInflater) }
    lateinit var dbHelper: DBHelper
    lateinit var coursesAdapter: CoursesAdapter
    lateinit var list: ArrayList<Course>
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        dbHelper = DBHelper.getInstance()
        init()


        binding.addCourse.setOnClickListener {
            //customdialog
            customAddDialog()
        }

        binding.backimg1.setOnClickListener {
            findNavController().popBackStack()
        }



        return binding.root
    }

    private fun customAddDialog() {
        val alert = AlertDialog.Builder(requireContext())
        val view = CustomDialogAddBinding.inflate(layoutInflater)
        alert.setView(view.root)

        val dialog = alert.show()
        dialog.create()
        view.tvSave.setOnClickListener {
            val name = view.edtCoursesName.text.toString()
            val about = view.edtCoursesAbout.text.toString()
            if (name.isNotEmpty() && about.isNotEmpty()) {
                dbHelper.addCourses(Course(name, about))
                dialog.dismiss()
                init()
            }
        }
    }

    private fun init() {
        list = dbHelper.getCourses() as ArrayList<Course>
        coursesAdapter = CoursesAdapter(list, this)
        binding.rvkurs.adapter = coursesAdapter
        binding.rvkurs.layoutManager = LinearLayoutManager(requireContext())
        coursesAdapter.notifyItemInserted(list.size - 1)
    }

    override fun onClick(courses: Course) {
        //
        val args = Bundle().apply {
            putParcelable("keyobject", courses)
        }
        findNavController().navigate(R.id.aboutFragment, args)
    }


}