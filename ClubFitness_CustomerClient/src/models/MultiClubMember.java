package models;

public class MultiClubMember extends Member{

	private int membershipPoints;
	
	
	public MultiClubMember(String memberType, int memberID, String name, double fees, String clubName, int membershipPoints) {
		super(memberType, memberID, name, fees, clubName);
		this.membershipPoints = membershipPoints;
	}
	public MultiClubMember(String memberType,  String name, double fees, String clubName, int membershipPoints) {
		super(memberType, name, fees, clubName);
		this.membershipPoints = membershipPoints;
	}

	public int getMembershipPoints() {
		return membershipPoints;
	}

	public void setMembershipPoints(int membershipPoints) {
		this.membershipPoints = membershipPoints;
	}

	
	@Override
	public String toString() {
		return super.toString() +""+ membershipPoints;
	}
}
