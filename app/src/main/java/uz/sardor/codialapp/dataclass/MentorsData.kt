package uz.sardor.codialapp.dataclass

import android.os.Parcelable
import kotlinx.parcelize.Parcelize


@Parcelize
class MentorsData() : Parcelable {
    var id: Int? = null
    var surname: String? = null
    var name: String? = null
    var patronymic: String? = null
    var courses: Course? = null

    constructor(id: Int?, surname: String?, name: String?, patronymic: String?, courses: Course?) : this() {
        this.id = id
        this.surname = surname
        this.name = name
        this.patronymic = patronymic
        this.courses = courses
    }

    constructor(surname: String?, name: String?, patronymic: String?, courses: Course?) : this() {
        this.surname = surname
        this.name = name
        this.patronymic = patronymic
        this.courses = courses
    }

}