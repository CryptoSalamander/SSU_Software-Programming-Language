
public class Order {
	private String customernum;
	private String orderdate;
	private String menu;
	public boolean iscoupon;
	
	public Order(String _num, String _date, String _menu)
	{
		customernum = _num;
		orderdate = _date;
		menu = _menu;
		iscoupon = false;
	}
	
	public String getnum()
	{
		return customernum;
	}
	
	public String getdate()
	{
		return orderdate;
	}
	
	public String getmenu()
	{
		return menu;
	}
}
