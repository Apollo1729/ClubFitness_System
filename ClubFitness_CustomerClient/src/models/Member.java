package models;

public class Member {
	
	private String memberType;
	private int memberID;
	private String name;
	private double fees;
	private String clubName;
	
	public Member(String memberType, int memberID, String name, double fees, String clubName) {
		super();
		this.memberType = memberType;
		this.memberID = memberID;
		this.name = name;
		this.fees = fees;
		this.setClubName(clubName);
		
	}

	public Member(String memberType, String name, double fees, String clubName) {
		this.memberType = memberType;
		this.name = name;
		this.fees = fees;
		this.clubName = clubName;
	}

	public String getMemberType() {
		return memberType;
	}

	public void setMemberType(String memberType) {
		this.memberType = memberType;
	}

	public int getMemberID() {
		return memberID;
	}

	public void setMemberID(int memberID) {
		this.memberID = memberID;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getFees() {
		return fees;
	}

	public void setFees(double fees) {
		this.fees = fees;
	}
	public String getClubName() {
		return clubName;
	}

	public void setClubName(String clubName) {
		this.clubName = clubName;
	}
	
	public String toString() {
		return memberType +","+ memberID +","+ name+ ","+fees;
		
	}

	
	

}
