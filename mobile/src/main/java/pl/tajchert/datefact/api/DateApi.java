package pl.tajchert.datefact.api;

import com.bluelinelabs.logansquare.annotation.JsonField;
import com.bluelinelabs.logansquare.annotation.JsonObject;


@JsonObject
public class DateApi {
    @JsonField
    public String text;

    @JsonField
    public int year;

    @JsonField
    public int number;

    @JsonField
    public boolean found;

    @JsonField
    public String type;
}
