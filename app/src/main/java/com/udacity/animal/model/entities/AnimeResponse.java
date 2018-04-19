package com.udacity.animal.model.entities;

import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import java.util.List;

/**
 * This is the response object when search anime is called
 */
@Root(name = "anime")
public class AnimeResponse {

    @ElementList(inline = true, required = false)
    private List<AnimeEntry> entry;

    // Required by SimpleXML
    public AnimeResponse() {
    }

    public List<AnimeEntry> getEntry() {
        return entry;
    }

    public void setEntry(List<AnimeEntry> entry) {
        this.entry = entry;
    }
}
