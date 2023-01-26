package uz.sardor.codialapp.fragmets.screenKurs.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import uz.sardor.codialapp.databinding.ItemCardBinding
import uz.sardor.codialapp.dataclass.Course

class CoursesAdapter(var arrayListCourses: ArrayList<Course> , var rvClickCourses: RVClickCourses) :
    RecyclerView.Adapter<CoursesAdapter.VH>() {

    inner class VH(var itemRV: ItemCardBinding) : RecyclerView.ViewHolder(itemRV.root) {
        fun onBind(courses: Course) {
            itemRV.tvKursname.text = courses.name
            itemRV.root.setOnClickListener {
                rvClickCourses.onClick(courses)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        return VH(ItemCardBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        holder.onBind(arrayListCourses[position])
    }

    override fun getItemCount(): Int = arrayListCourses.size

    interface RVClickCourses{
        fun onClick(courses: Course)
    }
}