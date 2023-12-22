package org.udem.unishop.models;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.UUID;
import org.udem.unishop.utilities.IssueState;
import org.udem.unishop.utilities.OrderState;


public class Issue {

    private final String description;
    private String solution;

    @JsonProperty("status")
    private IssueState status = IssueState.OPEN;


    @JsonProperty("tracking_number")
    private UUID trackingNumber;

    @JsonProperty("tracking_number_date")
    private long trackingNumberDate;


    @JsonCreator
    public Issue(@JsonProperty("description")String description){
        this.description = description;
        this.solution = "En Attente du Revendeur";
        this.trackingNumber = null;
        this.trackingNumberDate = 0;

    }

    public String getDescription() {
        return description;
    }

    public String getSolution() {
        return solution;
    }

    public void setSolution(String solution) {
        this.solution = solution;
    }


    public IssueState getStatus() {
        return status;
    }

    public void setStatus(IssueState status) {
        this.status = status;
    }


  public void setTrackingNumber(UUID uuid) {
    this.trackingNumber = uuid;
  }

  public UUID getTrackingNumber() {
    return this.trackingNumber;
  }

  public void setTrackingNumberDate(long date) {
    this.trackingNumberDate = date;
  }

  public long getTrackingNumberDate() {
    return this.trackingNumberDate;
  }
}