package com.udacity.animal;

import android.support.test.runner.AndroidJUnit4;

import com.udacity.animal.model.entities.AnimeResponse;
import com.udacity.animal.model.entities.MangaResponse;
import com.udacity.animal.model.network.MALAPIClient;

import junit.framework.Assert;

import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.IOException;

import retrofit2.Call;

@RunWith(AndroidJUnit4.class)
public class MALAPITests {

    @Test
    public void searchAllAnimesTest() {
        try {
            MALAPIClient service = new MALAPIClient(); // TODO this should be used with dagger

            Call<AnimeResponse> response = service.getService("proview", "88081461")
                    .searchAllAnime("Full metal");

            AnimeResponse body = response.execute().body();
            Assert.assertTrue("Entries less than 0", body.getEntry().size() > 0);
        } catch (IOException ex) {
            ex.printStackTrace();
            Assert.fail(ex.getMessage());
        } catch (NullPointerException ex) {
            ex.printStackTrace();
            Assert.fail("Nullpointer found: " + ex.getMessage());
        }
    }

    @Test
    public void searchAllMangasTest() {
        try {
            MALAPIClient service = new MALAPIClient(); // TODO this should be used with dagger

            Call<MangaResponse> response = service.getService("proview", "88081461")
                    .searchAllManga("Full metal");

            MangaResponse body = response.execute().body();
            Assert.assertTrue("Entries less than 0", body.getEntry().size() > 0);
        } catch (IOException ex) {
            ex.printStackTrace();
            Assert.fail(ex.getMessage());
        } catch (NullPointerException ex) {
            ex.printStackTrace();
            Assert.fail("Nullpointer found: " + ex.getMessage());
        }
    }
}
