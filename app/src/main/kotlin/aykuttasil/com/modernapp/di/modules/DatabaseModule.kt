/**
 * Designed and developed by Aykut Asil (@aykuttasil)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package aykuttasil.com.modernapp.di.modules

import android.content.Context
import androidx.room.Room
import aykuttasil.com.modernapp.data.AppDatabase
import com.aykutasil.modernapp.di.ApplicationContext
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
open class DatabaseModule {

  companion object {
    const val DB_NAME = "aa_3.db"
  }

  @Provides
  @Singleton
  open fun provideDatabase(@ApplicationContext context: Context) =
    Room.databaseBuilder(context, AppDatabase::class.java, DB_NAME).fallbackToDestructiveMigration().build()

  @Provides
  @Singleton
  fun provideUserDao(db: AppDatabase) = db.getUserDao()

  @Provides
  @Singleton
  fun provideLocationDao(db: AppDatabase) = db.getLocationDao()
}
