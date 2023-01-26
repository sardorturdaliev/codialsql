package uz.sardor.codialapp.fragmets.screenKurs

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.navigation.fragment.findNavController
import uz.sardor.codialapp.R
import uz.sardor.codialapp.databinding.CustomDialogRemoveBinding
import uz.sardor.codialapp.databinding.FragmentAboutBinding
import uz.sardor.codialapp.dataclass.Course
import uz.sardor.codialapp.fragmets.dbHelper.DBHelper


class AboutFragment : Fragment() {
    private val binding by lazy { FragmentAboutBinding.inflate(layoutInflater) }
    private lateinit var dbHelper: DBHelper
    private lateinit var course: Course
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        parseArgs()
        dbHelper = DBHelper.getInstance()

        binding.tvDescription.text = course.about
        binding.tvAllCourseName.text = course.name

        binding.imageDelete.setOnClickListener {

        deleteCourseDialog()
        }


        return binding.root
    }

    private fun parseArgs() {
        requireArguments().getParcelable<Course>("keyobject")?.let {
            course = it
        }
    }


    private fun deleteCourseDialog() {
        val alert = AlertDialog.Builder(requireContext())
        val view = CustomDialogRemoveBinding.inflate(layoutInflater)
        alert.setView(view.root)
        val dialog = alert.show()
        dialog.create()


        view.tvDelete.setOnClickListener {

            dbHelper.deleteKurs(course)

            findNavController().popBackStack()
            dialog.dismiss()
        }

    }
}