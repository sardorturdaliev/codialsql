package uz.sardor.codialapp.fragmets.screenmentor.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import uz.sardor.codialapp.databinding.CardAddMentorBinding
import uz.sardor.codialapp.dataclass.MentorsData

class MentorsAdapter(val context: Context, private val arrayList: ArrayList<MentorsData>, var rvClickMentors: RVClickMentors) :
    RecyclerView.Adapter<MentorsAdapter.VH>() {

    inner class VH(private var itemRV: CardAddMentorBinding) : RecyclerView.ViewHolder(itemRV.root) {
        @SuppressLint("SetTextI18n")
        fun onBind(mentors: MentorsData) {
            itemRV.tvName.text = "${mentors.name} ${mentors.surname}"
            itemRV.tvNumber.text = mentors.patronymic
            itemRV.cardDelete.setOnClickListener {
                rvClickMentors.onClickDelete(mentors)
            }
            itemRV.cardEdit.setOnClickListener {
                rvClickMentors.onClickEdit(mentors)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        return VH(CardAddMentorBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        holder.onBind(arrayList[position])

    }

    override fun getItemCount(): Int = arrayList.size

    interface RVClickMentors {
        fun onClickEdit(mentors: MentorsData) {

        }

        fun onClickDelete(mentors: MentorsData) {

        }
    }

}