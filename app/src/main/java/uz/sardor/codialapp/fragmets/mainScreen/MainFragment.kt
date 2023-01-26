package uz.sardor.codialapp.fragmets.mainScreen

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import uz.sardor.codialapp.R
import uz.sardor.codialapp.databinding.FragmentMainBinding


class MainFragment : Fragment() {
    private val binding by lazy { FragmentMainBinding.inflate(layoutInflater) }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding.kurslar.setOnClickListener {
            findNavController().navigate(R.id.kurslarFragment)
        }


        binding.mentorlar.setOnClickListener {
            findNavController().navigate(R.id.mentorFragment)
        }



        return binding.root
    }
}