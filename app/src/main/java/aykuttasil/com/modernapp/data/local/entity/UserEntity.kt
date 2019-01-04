package aykuttasil.com.modernapp.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import aykuttasil.com.modernapp.BR

/**
 * Created by aykutasil on 24.12.2017.
 */
@Entity(tableName = "user")
data class UserEntity(
    @PrimaryKey(autoGenerate = true) val UserId: Long? = null,
    @ColumnInfo(name = "UserName") private var _UserName: String?,
    val UserEmail: String?,
    @ColumnInfo(name = "UserJob") private var _UserJob: String? = null
) : BaseObservable() {

    var UserName: String
        @Bindable
        get() {
            return _UserName ?: ""
        }
        set(value) {
            _UserName = value
            notifyPropertyChanged(BR.userName)
        }

    var UserJob: String?
        @Bindable
        get() {
            return _UserJob
        }
        set(value) {
            _UserJob = value
            notifyPropertyChanged(BR.userJob)
        }
}