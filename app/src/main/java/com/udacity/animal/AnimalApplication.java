package com.udacity.animal;

import android.app.Application;

import com.udacity.animal.data.DataModule;
import com.udacity.animal.di.ContextModule;
import com.udacity.animal.di.DaggerKitsuComponent;
import com.udacity.animal.di.KitsuComponent;

public class AnimalApplication extends Application {

    private static KitsuComponent kitsuComponent;

    public static KitsuComponent getKitsuComponent() {
        return kitsuComponent;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        kitsuComponent = DaggerKitsuComponent.builder()
                .contextModule(new ContextModule(this.getApplicationContext()))
                .dataModule(new DataModule())
                .build();
    }
}
