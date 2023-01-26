package uz.sardor.codialapp.fragmets.Screenguruh

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import uz.sardor.codialapp.R
import uz.sardor.codialapp.databinding.FragmentGuruhBinding

class GuruhFragment : Fragment() {
    private val binding by lazy { FragmentGuruhBinding.inflate(layoutInflater) }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {








        return binding.root
    }
}