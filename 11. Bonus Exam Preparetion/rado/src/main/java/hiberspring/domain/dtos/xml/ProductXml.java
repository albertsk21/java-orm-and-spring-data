package hiberspring.domain.dtos.xml;

import javax.xml.bind.annotation.*;

@XmlRootElement(name = "product")
@XmlAccessorType(XmlAccessType.FIELD)
public class ProductXml {


    @XmlAttribute
    private String name;

    @XmlAttribute
    private int clients;

    @XmlElement
    private String branch;

    public ProductXml() {
    }

    public void setName(String name) {
        this.name = name;
    }
    public void setClients(int clients) {
        this.clients = clients;
    }
    public void setBranch(String branch) {
        this.branch = branch;
    }

    public String getName() {
        return name;
    }
    public int getClients() {
        return clients;
    }
    public String getBranch() {
        return branch;
    }
}
