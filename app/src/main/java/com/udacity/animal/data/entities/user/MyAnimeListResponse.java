package com.udacity.animal.data.entities.user;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import java.util.List;

@Root(name = "myanimelist")
public class MyAnimeListResponse {

    @Element(name = "myinfo", required = false)
    private UserAnime userAnime;
    @ElementList(name = "anime", inline = true, required = false)
    private List<UserAnimeItem> userAnimeItens;

    // Required by SimpleXML
    public MyAnimeListResponse() {
    }

    public UserAnime getUserAnime() {
        return userAnime;
    }

    public void setUserAnime(UserAnime userAnime) {
        this.userAnime = userAnime;
    }

    public List<UserAnimeItem> getUserAnimeItens() {
        return userAnimeItens;
    }

    public void setUserAnimeItens(List<UserAnimeItem> userAnimeItens) {
        this.userAnimeItens = userAnimeItens;
    }
}
