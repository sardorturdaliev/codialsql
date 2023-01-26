package uz.sardor.codialapp.dataclass

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
class Course() : Parcelable {
    var id: Int? = null
    var name: String? = null
    var about: String? = null

    constructor(id: Int?, name: String?, about: String?) : this() {
        this.id = id
        this.name = name
        this.about = about
    }

    constructor(name: String?, about: String?) : this() {
        this.name = name
        this.about = about
    }
}
