package com.udacity.animal.data;

import android.arch.persistence.room.Room;
import android.content.Context;

import com.udacity.animal.Source;
import com.udacity.animal.data.database.AnimalDatabase;
import com.udacity.animal.data.services.AuthorizationInterceptor;
import com.udacity.animal.data.services.KitsuClient;
import com.udacity.animal.di.ContextModule;
import com.udacity.animal.util.Constants;

import javax.inject.Singleton;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Module(includes = ContextModule.class)
public class DataModule {

    @Provides
    @Singleton
    AuthorizationInterceptor providesInterceptor() {
        return new AuthorizationInterceptor();
    }

    @Provides
    @Singleton
    KitsuClient providesAniListClient(AuthorizationInterceptor interceptor) {
        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        return retrofit.create(KitsuClient.class);
    }

    @Provides
    @Singleton
    @Source(Constants.DataSource.REMOTE)
    DataSource providesRemoteDataSource(RemoteDataSource remote) {
        return remote;
    }

    @Provides
    @Singleton
    @Source(Constants.DataSource.LOCAL)
    DataSource providesLocalDataSource(LocalDataSource local) {
        return local;
    }

    @Provides
    @Singleton
    AnimalDatabase providesAnimalDatabase(Context context) {
        return Room.databaseBuilder(context, AnimalDatabase.class, "animal_db").build();
    }

}
