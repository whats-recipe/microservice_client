package ao.inocencio.userservice.domain.model;

import java.time.LocalDateTime;
import java.util.UUID;

public class Client {
    private final UUID id;
    private final String name;
    private  String phoneNumber;
    private  String country;
    private boolean consent;
    private  boolean activeUser;
    private  final LocalDateTime createdAt;

    public Client(UUID id, String name, String phoneNumber, String country, LocalDateTime createdAt) {
        this.id =id;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.country = country;
        this.activeUser = true;
        this.createdAt = createdAt;
    }
    // Business methods
    public void updateConfigInfo(String phoneNumber, String country){
        this.phoneNumber = phoneNumber;
        this.country = country;
    }
    public void grantConsent(){
        this.consent = true;
    }
    public void revokeConsent(){
        this.consent = false;
    }
    public  void deactivate(){
        this.activeUser = false;
    }

    //Getters
    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getCountry() {
        return country;
    }

    public boolean isConsent() {
        return consent;
    }

    public boolean isActiveUser() {
        return activeUser;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
}
