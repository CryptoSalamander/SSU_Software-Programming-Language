public class Customer {
	private String customernum;
	private String reg_date;
	private String name;
	private String phone;
	private String birthday;
	public int count;
	public Customer(String _customernum, String _reg_date, String _name, String _phone, String _birthday)
	{
		customernum = _customernum;
		reg_date = _reg_date;
		name = _name;
		phone = _phone;
		birthday = _birthday;
		count = 0;
	}
	public String getcusnum()
	{
		return customernum;
	}
	public String getregdate()
	{
		return reg_date;
	}
	public String getname()
	{
		return name;
	}	
	public String getphone()
	{
		return phone;
	}	
	public String getbirth()
	{
		return birthday;
	}
}
