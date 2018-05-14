package com.udacity.animal;

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
        byte[] value = ("proview:***").getBytes();
        String encoded = Base64.getEncoder().encodeToString(value);
        token = encoded.trim();
    }

    @Test
    public void verifyCredentials() {
        try {
            ServiceGenerator service = ServiceGenerator.INSTANCE();
            service.refreshToken(token);
            Call<Void> response = service.getService().verfiyCredentials();
            Assert.assertEquals(200, response.execute().code());
        } catch (IOException ex) {
            ex.printStackTrace();
            Assert.fail();
        } catch (NullPointerException ex) {
            ex.printStackTrace();
            Assert.fail("Nullpointer found: " + ex.getMessage());
        }
    }
}
