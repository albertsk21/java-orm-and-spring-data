package softuni.exam.dto.json.importjson;

import com.google.gson.annotations.Expose;

public class PictureDtoJsonInput {
    @Expose
    private String url;


    public PictureDtoJsonInput() {
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
