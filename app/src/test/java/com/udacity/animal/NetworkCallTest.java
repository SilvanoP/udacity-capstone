package com.udacity.animal;

import com.udacity.animal.model.entities.AnimeResponse;
import com.udacity.animal.model.network.MALAPIClient;

import junit.framework.Assert;

import org.junit.Test;

import java.io.IOException;

import dagger.Component;
import retrofit2.Call;

@Component(modules = {MALAPIClient.class})
public class NetworkCallTest {

    @Test
    public void searchAllAnimesTest() {
        try {
            MALAPIClient service = new MALAPIClient(); // TODO this should be used with dagger
            String url = "";

            Call<AnimeResponse> response = service.getService("proview", "88081461")
                    .searchAllAnime("Full metal");
            AnimeResponse ar = response.execute().body();
            Assert.assertTrue("Entries less than 0", ar.getEntry().size() > 0);
        } catch (IOException ex) {
            ex.printStackTrace();
            Assert.fail();
        } catch (NullPointerException ex) {
            ex.printStackTrace();
            Assert.fail("Nullpointer found: " + ex.getMessage());
        }
    }
}
