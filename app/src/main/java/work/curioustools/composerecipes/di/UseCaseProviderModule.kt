package work.curioustools.composerecipes.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import work.curioustools.composerecipes.network.repos.UserRepo
import work.curioustools.composerecipes.network.usecases.CreateUserUseCase
import work.curioustools.composerecipes.network.usecases.GetAllUsersUseCase
import work.curioustools.composerecipes.network.usecases.GetSingleUserUseCase
import work.curioustools.composerecipes.network.usecases.UpdateUserUseCase


@Module
@InstallIn(SingletonComponent::class)
class UseCaseProviderModule {

    @Provides
    fun provideGetAllUsersUseCase(repo: UserRepo): GetAllUsersUseCase {
        return GetAllUsersUseCase(repo)
    }

    @Provides
    fun provideGetSingleUserUseCase(repo: UserRepo): GetSingleUserUseCase {
        return GetSingleUserUseCase(repo)
    }

    @Provides
    fun provideCreateUserUseCase(repo: UserRepo): CreateUserUseCase {
        return CreateUserUseCase(repo)
    }

    @Provides
    fun provideUpdateUserUseCase(repo: UserRepo): UpdateUserUseCase {
        return UpdateUserUseCase(repo)
    }


}