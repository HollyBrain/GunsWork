package net.guns_project.domain;



public class Maker extends DomainType{
	private static final long serialVersionUID = -1400278242161089165L;
	
	private String CompanyName;
	private int YearOfFoundation;
	private String DirectorName;
	private String RegistrationNumber;
	private String Adress;
	private String PhoneNumber;
	private String FaxNumber;
	private String Mail;
	private String WebSite;
	
	
	public String getCompanyName()
	{
		return CompanyName;
	}
	
	public void setCompanyName(String CompanyName)
	{
		this.CompanyName=CompanyName;
	}
	
	public int getYearOfFoundation()
	{
		return YearOfFoundation;
	}
	public void setYearOfFoundation(int YearOfFoundation)
	{
		this.YearOfFoundation=YearOfFoundation;
	}
	public String getDirectorName()
	{
		return DirectorName;
	}
	public void setDirectorName(String DirectorName)
	{
		this.DirectorName=DirectorName;
	}
	
	public String getRegistrationNumber()
	{
		return RegistrationNumber;
	}
	public void setRegistrationNumber(String RegistrationNumber)
	{
		this.RegistrationNumber=RegistrationNumber;
	}
	
	public String getAdress()
	{
		return Adress;
	}
	public void setAdress(String Adress)
	{
		this.Adress=Adress;
	}
	
	public String getPhoneNumber()
	{
		return PhoneNumber;
	}
	public void setPhoneNumber(String PhoneNumber)
	{
		this.PhoneNumber=PhoneNumber;
	}
	
	public String getFaxNumber()
	{
		return FaxNumber;
	}
	public void setFaxNumber(String FaxNumber)
	{
		this.FaxNumber=FaxNumber;
	}
	
	public String getMail()
	{
		return Mail;
	}
	public void setMail(String Mail)
	{
		this.Mail=Mail;
	}
	
	public String getSite()
	{
		return WebSite;
	}
	public void setSite(String WebSite)
	{
		this.WebSite=WebSite;
	}

}
