package dto;

public class Turf {

	private int turfId;
	private int ownerId;
	private String turfName;
	private String location;
	private String city;
	private double pricePerHour;
	private String openingTime;
	private String closingTime;
	private String description;
	private String thumbnailUrl;
	private double rating;
	private String status;

	// Default Constructor
	public Turf() {
	}

	// Parameterized Constructor
	public Turf(int turfId, int ownerId, String turfName, String location, String city, double pricePerHour,
			String openingTime, String closingTime, String description, String thumbnailUrl, double rating,
			String status) {

		this.turfId = turfId;
		this.ownerId = ownerId;
		this.turfName = turfName;
		this.location = location;
		this.city = city;
		this.pricePerHour = pricePerHour;
		this.openingTime = openingTime;
		this.closingTime = closingTime;
		this.description = description;
		this.thumbnailUrl = thumbnailUrl;
		this.rating = rating;
		this.status = status;
	}

	public int getTurfId() {
		return turfId;
	}

	public void setTurfId(int turfId) {
		this.turfId = turfId;
	}

	public int getOwnerId() {
		return ownerId;
	}

	public void setOwnerId(int ownerId) {
		this.ownerId = ownerId;
	}

	public String getTurfName() {
		return turfName;
	}

	public void setTurfName(String turfName) {
		this.turfName = turfName;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public double getPricePerHour() {
		return pricePerHour;
	}

	public void setPricePerHour(double pricePerHour) {
		this.pricePerHour = pricePerHour;
	}

	public String getOpeningTime() {
		return openingTime;
	}

	public void setOpeningTime(String openingTime) {
		this.openingTime = openingTime;
	}

	public String getClosingTime() {
		return closingTime;
	}

	public void setClosingTime(String closingTime) {
		this.closingTime = closingTime;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getThumbnailUrl() {
		return thumbnailUrl;
	}

	public void setThumbnailUrl(String thumbnailUrl) {
		this.thumbnailUrl = thumbnailUrl;
	}

	public double getRating() {
		return rating;
	}

	public void setRating(double rating) {
		this.rating = rating;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "Turf [turfId=" + turfId + ", ownerId=" + ownerId + ", turfName=" + turfName + ", location=" + location
				+ ", city=" + city + ", pricePerHour=" + pricePerHour + ", openingTime=" + openingTime
				+ ", closingTime=" + closingTime + ", description=" + description + ", thumbnailUrl=" + thumbnailUrl
				+ ", rating=" + rating + ", status=" + status + "]";
	}
}
