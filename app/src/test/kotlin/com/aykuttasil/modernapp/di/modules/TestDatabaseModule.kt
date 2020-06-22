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
package com.aykuttasil.modernapp.di.modules

import android.content.Context
import androidx.room.Room
import com.aykuttasil.data.di.DatabaseModule
import dagger.hilt.android.qualifiers.ApplicationContext

class TestDatabaseModule : DatabaseModule() {

  override
  fun provideDatabase(@ApplicationContext context: Context) = Room.inMemoryDatabaseBuilder(
    context,
    AppDatabase::class.java
  ).fallbackToDestructiveMigration().build()
}
