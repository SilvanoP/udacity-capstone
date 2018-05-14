package com.udacity.animal.data.entities.user;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import java.util.List;

@Root(name = "myanimelist")
public class MangaListResponse extends BaseListResponse {

    @Element(name = "myinfo", required = false)
    private UserManga userManga;
    @ElementList(name = "manga", inline = true, required = false)
    private List<UserMangaItem> userMangaItens;

    // Required by SimpleXML
    public MangaListResponse() {
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
