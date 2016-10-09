package com.dtr.oas.model;

import com.dtr.oas.util.ApiResponse;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.util.StringUtils;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.util.Date;
import java.util.List;

@Entity
@Table(name="user")public class User extends BaseEntity{

	private String username;

	private String password;

	private String email;

	private String firstName;

	private String lastName;

	private String middleName;

	private String status;

	private String userImageUrl;

	private String selectedClientFields;

	private String selectedTicketFields;

	@Transient
	List<UserRole> userRoleList;

	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date dateOfBirth;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getMiddleName() {
		return middleName;
	}

	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	public String getUserImageUrl() {
		return userImageUrl;
	}

	public void setUserImageUrl(String userImageUrl) {
		this.userImageUrl = userImageUrl;
	}

	public Date getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public List<UserRole> getUserRoleList() {
		return userRoleList;
	}

	public void setUserRoleList(List<UserRole> userRoleList) {
		this.userRoleList = userRoleList;
	}

	public ApiResponse validate(boolean update){

		ApiResponse apiResponse = new ApiResponse();

		if(StringUtils.isEmpty(username) || username.length() < 5){
			apiResponse.message("username is too small").success(false).type(ApiResponse.VALIDATION_ERROR);
		}

		else if(StringUtils.isEmpty(password) || password.length() < 5){
			apiResponse.message("password is too small").success(false).type(ApiResponse.VALIDATION_ERROR);
		}

		else if(StringUtils.isEmpty(email)){
			apiResponse.message("email is empty").success(false).type(ApiResponse.VALIDATION_ERROR);
		}

		return apiResponse;
	}

	public String getSelectedClientFields() {
		return selectedClientFields;
	}

	public void setSelectedClientFields(String selectedClientFields) {
		this.selectedClientFields = selectedClientFields;
	}

	public String getSelectedTicketFields() {
		return selectedTicketFields;
	}

	public void setSelectedTicketFields(String selectedTicketFields) {
		this.selectedTicketFields = selectedTicketFields;
	}

	@Override
	public String toString() {
		return "User{" +
				"username='" + username + '\'' +
				", password='" + password + '\'' +
				", email='" + email + '\'' +
				", firstName='" + firstName + '\'' +
				", lastName='" + lastName + '\'' +
				", middleName='" + middleName + '\'' +
				", userImageUrl='" + userImageUrl + '\'' +
				", dateOfBirth=" + dateOfBirth +
				'}';
	}
}
