package uz.sardor.codialapp.fragmets.screenmentor

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import uz.sardor.codialapp.R
import uz.sardor.codialapp.databinding.FragmentAddMentorBinding
import uz.sardor.codialapp.databinding.FragmentMentorAddBinding
import uz.sardor.codialapp.databinding.FragmentMentorBinding
import uz.sardor.codialapp.dataclass.Course
import uz.sardor.codialapp.dataclass.MentorsData
import uz.sardor.codialapp.fragmets.dbHelper.DBHelper

class MentorAddFragment : Fragment() {
    private val binding by lazy { FragmentMentorAddBinding.inflate(layoutInflater) }
    private lateinit var dbHelper: DBHelper
    private lateinit var course: Course

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        dbHelper = DBHelper.getInstance()
        initArgss()
        binding.btnSaveMentor.setOnClickListener {
            val surname = binding.edtMentorsSurname.text.toString()
            val name = binding.edtMentorsName.text.toString()
            val patroname = binding.edtMentorsPatronymic.text.toString()

            if (surname.isNotEmpty() && name.isNotEmpty() && patroname.isNotEmpty()) {
                dbHelper.addMentorData(
                    MentorsData(
                        surname,
                        name,
                        patroname,
                        dbHelper.getCoursesByID(course.id!!)
                    )
                )
                findNavController().popBackStack()
            }
        }







        return binding.root
    }

    private fun initArgss() {
        requireArguments().getParcelable<Course>("addmentorkey")?.let {
            course = it
        }
    }
}