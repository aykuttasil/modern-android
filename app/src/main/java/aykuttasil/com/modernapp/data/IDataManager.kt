package aykuttasil.com.modernapp.data

import androidx.lifecycle.LiveData
import aykuttasil.com.modernapp.data.local.entity.LocationEntity
import aykuttasil.com.modernapp.data.local.entity.UserEntity

/**
 * Created by aykutasil on 27.12.2017.
 */
interface IDataManager {

    fun addLocation(loc: LocationEntity)

    fun getUser(username: String): LiveData<Resource<UserEntity>>

}