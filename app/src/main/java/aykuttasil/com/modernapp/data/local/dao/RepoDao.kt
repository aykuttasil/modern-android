package aykuttasil.com.modernapp.data.local.dao

/*
@Dao
abstract class RepoDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insertItems(list: List<UserEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insertItem(item: UserEntity): Long

    @Update(onConflict = OnConflictStrategy.REPLACE)
    abstract fun updatetItem(item: UserEntity)

    @Delete
    abstract fun deletetItem(item: UserEntity)

    @Query("SELECT count(*) FROM repo")
    abstract fun getItemsCount(): Flowable<Int>

    @Query("SELECT * FROM repo")
    abstract fun getItems(): Flowable<List<UserEntity>>

    @Query("SELECT * FROM repo")
    abstract fun getItemsLiveData(): LiveData<List<UserEntity>>

    @Query("SELECT * FROM repo WHERE repoId=:id")
    abstract fun getItem(id: Long): UserEntity

    @Query("SELECT * FROM repo")
    abstract fun getItem(): LiveData<UserEntity>

    @Insert
    abstract fun insertAll(vararg users: UserEntity)

}
        */