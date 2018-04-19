package com.udacity.animal.model.entities;

import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import java.util.List;

@Root(name = "manga")
public class MangaResponse {

    @ElementList(inline = true, required = false)
    private List<MangaEntry> entry;

    // Required by SimpleXML
    public MangaResponse() {
    }

    public List<MangaEntry> getEntry() {
        return entry;
    }

    public void setEntry(List<MangaEntry> entry) {
        this.entry = entry;
    }
}
