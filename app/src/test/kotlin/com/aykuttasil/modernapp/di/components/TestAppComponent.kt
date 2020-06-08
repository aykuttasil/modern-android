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
package com.aykuttasil.modernapp.di.components

import android.app.Application
import com.aykuttasil.modernapp.TestApp
import com.aykuttasil.modernapp.di.ActivityBuilder
import com.aykuttasil.modernapp.di.ServiceBuilder
import com.aykuttasil.modernapp.di.modules.ApiModule
import com.aykuttasil.modernapp.di.modules.AppModule
import com.aykuttasil.modernapp.di.modules.DatabaseModule
import com.aykuttasil.network.di.modules.NetworkModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
  modules = [
    (AndroidSupportInjectionModule::class),
    (ActivityBuilder::class),
    (ServiceBuilder::class),
    (AppModule::class),
    (NetworkModule::class),
    (ApiModule::class)
  ]
)
interface TestAppComponent : AndroidInjector<TestApp> {

  fun setDatabaseModule(databaseModule: DatabaseModule)

  @Component.Builder
  interface Builder {

    @BindsInstance
    fun application(application: Application): Builder

    fun build(): TestAppComponent
  }
}
