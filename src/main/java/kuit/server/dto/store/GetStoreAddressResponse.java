package kuit.server.dto.store;

public class GetStoreAddressResponse {
    private long storeId;
    private String address;

    public GetStoreAddressResponse(long storeId, String address) {
        this.storeId = storeId;
        this.address = address;
    }

    public long getStoreId() {
        return storeId;
    }

    public String getAddress() {
        return address;
    }
}
