package softuni.exam.instagraphlite.models.json;

import com.google.gson.annotations.Expose;

import java.io.Serializable;
import java.math.BigDecimal;

public class PictureJson implements Serializable {
    @Expose
    private String path;
    @Expose
    private BigDecimal size;

    public PictureJson() {}

    public void setPath(String path) {
        this.path = path;
    }
    public void setSize(BigDecimal size) {
        this.size = size;
    }

    public String getPath() {
        return path;
    }
    public BigDecimal getSize() {
        return size;
    }
}
