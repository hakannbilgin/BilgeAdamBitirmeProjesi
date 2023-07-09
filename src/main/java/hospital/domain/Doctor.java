package hospital.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity(name = "doctor")
@Table(name = "doctor")
public class Doctor extends AbstractEntity{
	
	
	@Column(name = "first_name")
	private String firstName;
	
	
	@Column(name = "last_name")
	private String lastName;
	
	
	@Column(name = "citizen_number")
	private String citizenNumber;
	
	
	@Column(name = "branch")
	private String branch;


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


	public String getCitizenNumber() {
		return citizenNumber;
	}


	public void setCitizenNumber(String citizenNumber) {
		this.citizenNumber = citizenNumber;
	}


	public String getBranch() {
		return branch;
	}


	public void setBranch(String branch) {
		this.branch = branch;
	}


	@Override
	public String toString() {
		return "Doctor [firstName=" + firstName + ", lastName=" + lastName + ", branch=" + branch + "]";
	}
	
	
	

}
