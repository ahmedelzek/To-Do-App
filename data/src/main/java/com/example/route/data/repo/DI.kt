package com.example.route.data.repo

import com.example.route.data.contarct.TaskRepo
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun bindTaskRepo(
        categoriesRepoImpl: TaskRepoImpl
    ): TaskRepo

}