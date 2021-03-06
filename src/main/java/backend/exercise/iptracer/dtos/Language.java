package backend.exercise.iptracer.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Language {
    private String nativeName;
    @JsonProperty("iso639_1")
    private String code;

    public String getNativeName() {
        return nativeName;
    }

    public void setNativeName(String nativeName) {
        this.nativeName = nativeName;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
