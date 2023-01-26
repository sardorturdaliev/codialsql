package uz.sardor.codialapp.app

import android.app.Application
import uz.sardor.codialapp.fragmets.dbHelper.DBHelper

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        DBHelper.init(this)
    }
}