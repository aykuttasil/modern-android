package aykuttasil.com.myandroidstructure.data

import androidx.lifecycle.LiveData
import aykuttasil.com.modernapp.data.IDataManager
import aykuttasil.com.modernapp.data.Resource
import aykuttasil.com.modernapp.data.local.entity.LocationEntity
import aykuttasil.com.modernapp.data.local.entity.UserEntity
import aykuttasil.com.modernapp.data.repository.UserRepository
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Created by aykutasil on 8.12.2017.
 */
@Singleton
class DataManager @Inject constructor(private val userRepository: UserRepository) : IDataManager {

    override fun getUser(username: String): LiveData<Resource<UserEntity>> {
        return userRepository.getUser(username)
    }

    override fun addLocation(loc: LocationEntity) {

    }

    /*
    override fun getUser(userId: Long): Flowable<UserEntity> {
        return Flowable.defer { Flowable.just(appDatabase.getUserDao().getItem(userId)) }
    }

    override fun getUsersCount(): Flowable<Int> {
        return appDatabase.getUserDao().getItemsCount()
    }

    override fun addUsers(items: List<UserEntity>) {
        Single.create<Unit>({
            try {
                it.onSuccess(appDatabase.getUserDao().insertItems(items))
            } catch (exp: Exception) {
                exp.printStackTrace()
            }
        }).subscribeOn(Schedulers.io())
                .subscribe()
    }

    override fun addUser(item: UserEntity) {
        Single.create<Long>({
            try {
                it.onSuccess(appDatabase.getUserDao().insertItem(item))
            } catch (exp: Exception) {
                exp.printStackTrace()
            }
        }).subscribeOn(Schedulers.io())
                .subscribe()
    }

    override fun getUsersRx(): Flowable<List<UserEntity>> {
        return appDatabase.getUserDao().getItems()
    }

    override fun getLocationsRx(): Flowable<List<LocationEntity>> {
        return appDatabase.getLocationDao().getItems()
    }

    override fun addLocation(loc: LocationEntity) {
        Single.create<Long>({
            try {
                it.onSuccess(appDatabase.getLocationDao().insertItem(loc))
            } catch (exp: Exception) {
                exp.printStackTrace()
            }
        }).subscribeOn(Schedulers.io())
                .subscribe()
    }

    override fun getLocation(id: Long): LocationEntity {
        return appDatabase.getLocationDao().getItem(id)
    }

    override fun getLocations(): LiveData<List<LocationEntity>> {
        return appDatabase.getLocationDao().getItemsLiveData()
    }

    fun addUserToLocal(user: UserEntity): Single<Long>? {
        return Single.create {
            try {
                val userId = appDatabase.getUserDao().insertItem(user)
                Log.i("aaa", "User Id: " + userId)
                it.onSuccess(userId)
            } catch (e: Exception) {
                it.onError(e)
            }
        }
    }

    fun getUsersToLocal(): Flowable<List<UserEntity>> {
        return appDatabase.getUserDao().getItems()
    }

    fun getUsersToLocalLive(): LiveData<List<UserEntity>> {
        return appDatabase.getUserDao().getItemsLiveData()
    }
    */


}