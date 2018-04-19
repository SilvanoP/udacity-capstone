package com.udacity.animal.model.entities.user;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import java.util.List;

@Root(name = "myanimelist")
public class MyMangaListResponse {

    @Element(name = "myinfo", required = false)
    private UserManga userManga;
    @ElementList(name = "manga", inline = true, required = false)
    private List<UserMangaItem> userMangaItens;

    // Required by SimpleXML
    public MyMangaListResponse() {
    }

    public UserManga getUserManga() {
        return userManga;
    }

    public void setUserManga(UserManga userManga) {
        this.userManga = userManga;
    }

    public List<UserMangaItem> getUserMangaItens() {
        return userMangaItens;
    }

    public void setUserMangaItens(List<UserMangaItem> userMangaItens) {
        this.userMangaItens = userMangaItens;
    }
}
