package net.guns_project.domain;

public class Weapon extends DomainType{
	private static final long serialVersionUID = -1400278242161089165L;
	
	private String WeaponName;
	private int MakerId;
	private String Date;
	private String Other;
	
	
	public String getWeaponName()
	{
		return WeaponName;
	}
	
	public void setWeaponName(String WeaponName)
	{
		this.WeaponName=WeaponName;
	}
	
	public int getMakerId()
	{
		return MakerId;
	}
	public void setMakerId(int MakerId)
	{
		this.MakerId=MakerId;
	}
	
	public String getDate()
	{
		return Date;
	}
	
	public void setDate(String Date)
	{
		this.Date=Date;
	}
	
	public String getOther()
	{
		return Other;
	}
	
	public void setOther(String Other)
	{
		this.Other=Other;
	}

}
