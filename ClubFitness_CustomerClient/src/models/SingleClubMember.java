package models;

public class SingleClubMember extends Member{
	private int clubID;
	
	public SingleClubMember(String memberType, int memberID, String name, double fees, String clubName, int clubID) {
		super(memberType, memberID, name, fees,clubName);
		this.clubID =clubID;	
	}
	public SingleClubMember(String memberType,  String name, double fees, String clubName, int clubID) {
		super(memberType,  name, fees, clubName);
		this.clubID =clubID;	
	}

	public int getClubID() {
		return clubID;
	}

	public void setClubID(int clubID) {
		this.clubID = clubID;
	}

	
	@Override
	public String toString() {
		return super.toString() +""+ clubID;
	}
	
	
	

}
