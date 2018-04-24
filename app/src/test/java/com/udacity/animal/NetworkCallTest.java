package com.udacity.animal;

import com.udacity.animal.data.entities.AnimeResponse;
import com.udacity.animal.data.network.ServiceGenerator;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.Base64;

import retrofit2.Call;
import retrofit2.http.GET;

public class NetworkCallTest {

    private String token;

    @Before
    public void setup() {
        byte[] value = ("proview:88081461").getBytes();
        String encoded = Base64.getEncoder().encodeToString(value);
        token = encoded.trim();
    }

    @Test
    public void verifyCredentials() {
        try {
            ServiceGenerator service = new ServiceGenerator(token);
            Call<GET> response = service.verifyCredentials();
            Assert.assertEquals(200, response.execute().code());
        } catch (IOException ex) {
            ex.printStackTrace();
            Assert.fail();
        } catch (NullPointerException ex) {
            ex.printStackTrace();
            Assert.fail("Nullpointer found: " + ex.getMessage());
        }
    }

    @Test
    public void searchAllAnimesTest() {
        try {
            ServiceGenerator service = new ServiceGenerator(token);
            Call<AnimeResponse> response = service.serchAnimes("Full metal");
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
