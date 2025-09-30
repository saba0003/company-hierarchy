package contract;

import utils.annotations.CustomAuditable;

public class Client {

    private String name;
    private String industry;

    public Client(String name, String industry) {
        this.name = name;
        this.industry = industry;
    }

    public String getName() {
        return name;
    }

    @CustomAuditable("security")
    public void setName(String name) {
        this.name = name;
    }

    public String getIndustry() {
        return industry;
    }

    @CustomAuditable("business")
    public void setIndustry(String industry) {
        this.industry = industry;
    }
}
