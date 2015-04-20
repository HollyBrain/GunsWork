package net.guns_project.domain;

public class Description extends DomainType{
	private static final long serialVersionUID = -1400278242161089165L;
	
	private int WeaponName;
	private String DescriptionName;
	private String ValueDescription;
	private String OtherDescription;
	
	public int getWeaponName()
	{
		return WeaponName;
	}
	
	public void setWeaponName(int WeaponName)
	{
		this.WeaponName=WeaponName;
	}
	
	public String getDescriptionName()
	{
		return DescriptionName;
	}
	public void setDescriptionName(String DescriptionName)
	{
		this.DescriptionName=DescriptionName;
	}
	
	public String getValueDescription()
	{
		return ValueDescription;
	}
	public void setValueDescription(String ValueDescription)
	{
		this.ValueDescription=ValueDescription;
	}
	
	public String getOtherDescription()
	{
		return OtherDescription;
	}
	public void setOtherDescription(String OtherDescription)
	{
		this.OtherDescription=OtherDescription;
	}
	
}
