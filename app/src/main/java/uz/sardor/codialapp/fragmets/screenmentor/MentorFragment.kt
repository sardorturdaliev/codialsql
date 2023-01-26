package uz.sardor.codialapp.fragmets.screenmentor

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import uz.sardor.codialapp.R
import uz.sardor.codialapp.databinding.FragmentMainBinding
import uz.sardor.codialapp.databinding.FragmentMentorBinding
import uz.sardor.codialapp.dataclass.Course
import uz.sardor.codialapp.fragmets.dbHelper.DBHelper
import uz.sardor.codialapp.fragmets.screenKurs.adapter.CoursesAdapter

class MentorFragment : Fragment(), CoursesAdapter.RVClickCourses {
    private val binding by lazy { FragmentMentorBinding.inflate(layoutInflater) }
    lateinit var dbHelper: DBHelper
    lateinit var coursesAdapter: CoursesAdapter
    lateinit var list: ArrayList<Course>
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        dbHelper = DBHelper.getInstance()
        init()






        return binding.root
    }

    private fun init() {
        list = dbHelper.getCourses() as ArrayList<Course>
        coursesAdapter = CoursesAdapter(list, this)
        binding.rvkurs.adapter = coursesAdapter
        binding.rvkurs.layoutManager = LinearLayoutManager(requireContext())
        coursesAdapter.notifyItemInserted(list.size - 1)
    }




    override fun onClick(courses: Course) {
        val args = Bundle().apply {
            putParcelable("mentorkey",courses)
        }
        findNavController().navigate(R.id.addMentorFragment,args)
    }





}