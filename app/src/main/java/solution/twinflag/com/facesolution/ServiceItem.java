package solution.twinflag.com.facesolution;

public class ServiceItem {

    private int serviceId;

    private String serviceName;

    private int serviceIconId;

    public ServiceItem(int serviceId, String serviceName, int serviceIconId) {
        this.serviceId = serviceId;
        this.serviceName = serviceName;
        this.serviceIconId = serviceIconId;
    }

    public ServiceItem() {
    }

    public int getServiceId() {
        return serviceId;
    }

    public void setServiceId(int serviceId) {
        this.serviceId = serviceId;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public int getServiceIconId() {
        return serviceIconId;
    }

    public void setServiceIconId(int serviceIconId) {
        this.serviceIconId = serviceIconId;
    }
}
