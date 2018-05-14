package com.udacity.animal.data.entities.entries;

import com.udacity.animal.data.entities.entries.MangaEntry;

import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import java.util.List;

/**
 * This is the response object when search anime is called.
 */
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
