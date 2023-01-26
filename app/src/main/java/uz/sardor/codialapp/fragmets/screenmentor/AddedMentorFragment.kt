package uz.sardor.codialapp.fragmets.screenmentor

import android.content.DialogInterface
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import uz.sardor.codialapp.R
import uz.sardor.codialapp.databinding.CustomEditDialogBinding
import uz.sardor.codialapp.databinding.FragmentAddMentorBinding
import uz.sardor.codialapp.dataclass.Course
import uz.sardor.codialapp.dataclass.MentorsData
import uz.sardor.codialapp.fragmets.dbHelper.DBHelper
import uz.sardor.codialapp.fragmets.screenmentor.adapter.MentorsAdapter


class AddedMentorFragment : Fragment(), MentorsAdapter.RVClickMentors {
    private val binding by lazy { FragmentAddMentorBinding.inflate(layoutInflater) }
    private lateinit var course: Course
    private lateinit var dbHelper: DBHelper
    private lateinit var mentorsAdapter: MentorsAdapter
    private lateinit var arrayListMentor: ArrayList<MentorsData>
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        initArgs()
        dbHelper = DBHelper.getInstance()
        binding.tvCoursesName.text = course.name
        initData()

        binding.imageAdd.setOnClickListener {
            val args = Bundle().apply {
                putParcelable("addmentorkey", course)
            }
            findNavController().navigate(R.id.mentorAddFragment, args)
        }





        return binding.root
    }

    //get Cours id
    private fun initArgs() {
        requireArguments().getParcelable<Course>("mentorkey")?.let {
            course = it
        }
    }


    private fun initData() {
        val arrayList = ArrayList<MentorsData>()

        //get Mentors from DataBase
        arrayListMentor = dbHelper.getMentors() as ArrayList<MentorsData>


        // setting id to id
        for (i in arrayListMentor) {
            if (i.courses!!.id == course.id) {
                arrayList.add(i)
            }
        }


        //list
        arrayListMentor = arrayList

        //set To RecyclerView
        mentorsAdapter = MentorsAdapter(requireContext(), arrayListMentor, this)
        binding.recyclerMentors.adapter = mentorsAdapter
        binding.recyclerMentors.layoutManager = LinearLayoutManager(requireContext())
    }


    override fun onClickEdit(mentors: MentorsData) {
        //code custom Dialog
        val alert = AlertDialog.Builder(requireContext())
        val view = CustomEditDialogBinding.inflate(layoutInflater)
        alert.setView(view.root)
        val dialog = alert.show()
        dialog.create()

        //edit Text
        view.edtMentorsName.setText(mentors.name)
        view.edtMentorsSurname.setText(mentors.surname)
        view.edtMentorsPatronymic.setText(mentors.patronymic)



        view.tvSave.setOnClickListener {
            //save Data
        }


    }

    override fun onClickDelete(mentors: MentorsData) {
        val alert = AlertDialog.Builder(requireContext())
        alert.setTitle("Delete")
        alert.setMessage("Do you want to delete")
        alert.setPositiveButton("Yes") { p0, p1 ->
            dbHelper.deleteMentor(mentors)
            Toast.makeText(requireContext(), "Deleted", Toast.LENGTH_SHORT).show()
            mentorsAdapter.notifyItemRemoved(arrayListMentor.size - 1)
            initData()
        }

        alert.setNegativeButton("No") { _, _ ->
            {

            }
        }

        alert.show()
    }

}