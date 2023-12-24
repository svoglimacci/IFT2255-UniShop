package org.udem.unishop.models;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.UUID;
import org.udem.unishop.utilities.IssueState;
import org.udem.unishop.utilities.OrderState;

/**
 * Represents an issue associated to an order.
 */
public class Issue {

    private final String description;
    private String solution;

    @JsonProperty("status")
    private IssueState status = IssueState.OPEN;


    @JsonProperty("tracking_number")
    private UUID trackingNumber;

    @JsonProperty("tracking_number_date")
    private long trackingNumberDate;

    /**
     * Constructor for Issue object.
     *
     * @param description The description of the issue.
     */
    @JsonCreator
    public Issue(@JsonProperty("description")String description){
        this.description = description;
        this.solution = "En Attente du Revendeur";
        this.trackingNumber = null;
        this.trackingNumberDate = 0;

    }

    /**
     * Gets the description of the issue.
     *
     * @return The description of the issue.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Gets the solution or current status of the issue.
     *
     * @return The solution or current status of the issue.
     */
    public String getSolution() {
        return solution;
    }

    /**
     * Sets the solution or current status of the issue.
     *
     * @param solution The new solution or current status of the issue.
     */
    public void setSolution(String solution) {
        this.solution = solution;
    }

    /**
     * Gets the status of the issue.
     *
     * @return The status of the issue.
     */
    public IssueState getStatus() {
        return status;
    }

    /**
     * Sets a status to the issue.
     *
     * @param status The status to be set.
     */
    public void setStatus(IssueState status) {
        this.status = status;
    }


    /**
     * Sets a tracking number to the order associated to the issue.
     *
     * @param uuid The tracking number to be set.
     */
    public void setTrackingNumber(UUID uuid) {
    this.trackingNumber = uuid;
  }

    /**
     * Gets the tracking number associated to the issue.
     *
     * @return the tracking number associated to the issue.
     */
    public UUID getTrackingNumber() {
    return this.trackingNumber;
  }

    /**
     * Sets the date when the tracking number was assigned to the issue.
     *
     * @param date the date when the tracking number was assigned to the issue.
     */
    public void setTrackingNumberDate(long date) {
    this.trackingNumberDate = date;
  }

    /**
     * Gets the date when the tracking number was assigned to the issue.
     *
     * @return the date when the tracking number was assigned to the issue.
     */
    public long getTrackingNumberDate() {
    return this.trackingNumberDate;
  }
}