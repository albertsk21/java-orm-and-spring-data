package car.pojo;


import com.google.gson.annotations.Expose;

public class SupplierPojo {


    @Expose
    private Long id;
    @Expose
    private String name;
    @Expose
    private int partCount;



    public SupplierPojo() {
    }


    public void setId(Long id) {
        this.id = id;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setPartCount(int partCount) {
        this.partCount = partCount;
    }

    public Long getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public int getPartCount() {
        return partCount;
    }
}
