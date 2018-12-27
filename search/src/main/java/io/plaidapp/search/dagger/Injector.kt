/*
 * Copyright 2018 Google, Inc.
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

package io.plaidapp.search.dagger

import io.plaidapp.core.dagger.FilterAdapterModule
import io.plaidapp.core.dagger.OnDataLoadedModule
import io.plaidapp.core.dagger.SharedPreferencesModule
import io.plaidapp.core.designernews.data.login.LoginLocalDataSource
import io.plaidapp.search.ui.SearchActivity
import io.plaidapp.ui.coreComponent

/**
 * Injector for SearchActivity.
 *
 * TODO: Convert to extension function once [SearchActivity] is converted to Kotlin.
 */
object Injector {

    @JvmStatic
    fun inject(activity: SearchActivity) {
        DaggerSearchComponent.builder()
            .activity(activity)
            .coreComponent(activity.coreComponent())
            .dataLoadedModule(OnDataLoadedModule(null))
            .filterAdapterModule(FilterAdapterModule(activity))
            .sharedPreferencesModule(
                SharedPreferencesModule(
                    activity,
                    LoginLocalDataSource.DESIGNER_NEWS_PREF
                )
            )
            .searchModule(SearchModule(activity))
            .build()
            .inject(activity)
    }
}
