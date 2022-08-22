package car.dto.json;


import car.dto.json.PartDto;
import com.google.gson.annotations.Expose;

import java.util.Set;

public class SupplierDto {


    private Long id;
    @Expose
    private String name;
    @Expose
    private boolean isImporter;
    private Set<PartDto> parts;


    public SupplierDto() {
    }

    public void setParts(Set<PartDto> parts) {
        this.parts = parts;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public void setImporter(boolean importer) {
        isImporter = importer;
    }
    public void setName(String name) {
        this.name = name;
    }


    public Set<PartDto> getParts() {
        return parts;
    }
    public Long getId() {
        return id;
    }
    public boolean isImporter() {
        return isImporter;
    }
    public String getName() {
        return name;
    }


}
