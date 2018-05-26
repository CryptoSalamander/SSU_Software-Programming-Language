import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.FlowLayout;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.awt.event.ActionEvent;
import java.util.*;
public class Frame extends JFrame {

	private JPanel contentPane;
	private JTextField ord_date;
	private JTextField ord_num;
	private JTextField cus_num;
	private JTextField cus_name;
	private JTextField cus_phone;
	private JTextField cus_date;
	private JTextField cus_birth;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Frame frame = new Frame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Frame() {
		setTitle("\uBD84\uC2DD\uC9D1 \uAD00\uB9AC \uC2DC\uC2A4\uD15C");
		ArrayList<Customer> ca;
		ca = new ArrayList<Customer>();
		ArrayList<Order> oa;
		oa = new ArrayList<Order>();
		try {
			BufferedReader in = new BufferedReader(new FileReader("custom.txt"));
			String s;
			while((s = in.readLine()) != null) {
				StringTokenizer tokens = new StringTokenizer(s);
				String sNum = tokens.nextToken(" ");
				String sRegday = tokens.nextToken(" ");
				String sName = tokens.nextToken(" ");
				String sPhone = tokens.nextToken(" ");
				String sBirth = tokens.nextToken(" ");
				String sCount = tokens.nextToken(" ");
				if(tokens.hasMoreTokens())
				{
					String sOrdDay = tokens.nextToken(" ");
					String sOrdnum = tokens.nextToken(" ");
					String sMenu = tokens.nextToken(" ");
					Order tmp2 = new Order(sOrdnum,sOrdDay,sMenu);
					oa.add(tmp2);
				}
				Customer tmp = new Customer(sNum,sRegday,sName,sPhone,sBirth);
				tmp.count = Integer.parseInt(sCount);
				ca.add(tmp);
			}
		}catch(IOException e)
		{
			System.exit(1);
		}
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		contentPane.add(tabbedPane, BorderLayout.CENTER);
		
		JPanel order = new JPanel();
		tabbedPane.addTab("\uC8FC\uBB38\uAD00\uB9AC", null, order, null);
		order.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("\uB0A0\uC9DC");
		lblNewLabel_1.setBounds(25, 49, 50, 15);
		order.add(lblNewLabel_1);
		
		JLabel label = new JLabel("\uACE0\uAC1D\uBC88\uD638");
		label.setBounds(25, 91, 50, 15);
		order.add(label);
		
		JLabel label_1 = new JLabel("\uBA54\uB274");
		label_1.setBounds(25, 134, 50, 15);
		order.add(label_1);
		
		ord_date = new JTextField();
		ord_date.setBounds(145, 46, 96, 21);
		order.add(ord_date);
		ord_date.setColumns(10);
		
		ord_num = new JTextField();
		ord_num.setBounds(145, 88, 96, 21);
		order.add(ord_num);
		ord_num.setColumns(10);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"\uAE40\uBC25", "\uB5A1\uBCF6\uC774", "\uC21C\uB300", "\uC624\uB385", "\uD280\uAE40"}));
		comboBox.setBounds(145, 130, 96, 23);
		order.add(comboBox);
		
		JButton btnNewButton = new JButton("\uC8FC\uBB38");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				boolean exception = false;
				boolean numcheck = false;
				if(!ord_date.getText().matches("[0-9|/]*") | !cus_birth.getText().matches("[0-9|/]*"))
				{
					JOptionPane.showMessageDialog(null, "날짜형식에 숫자와 / 외의 다른 문자가 있습니다.", "날짜 형식 예외", JOptionPane.ERROR_MESSAGE);
					exception = true;
				}
				if(!ord_num.getText().matches("[0-9|a-z|A-Z|ㄱ-ㅎ|ㅏ-ㅣ|가-힝|]*"))
				{
					JOptionPane.showMessageDialog(null, "고객번호에 특수문자가 있습니다!", "고객번호 형식 예외", JOptionPane.ERROR_MESSAGE);
					exception = true;
				}
				if(ord_num.getText().length() == 0 || ord_date.getText().length() == 0)
				{
					JOptionPane.showMessageDialog(null, "공란을 채워주세요. 고객번호 없을시 Guest 입력", "고객번호 형식 예외", JOptionPane.ERROR_MESSAGE);
					exception = true;
				}
				if(!exception)
				{
					Order tmp = new Order(ord_num.getText(),ord_date.getText(), (String)comboBox.getSelectedItem());
					for(int i = 0; i < ca.size(); i++)
					{
						if(ca.get(i).getcusnum().equals(ord_num.getText()))
						{
							numcheck = true;
							ca.get(i).count++;
							if(ca.get(i).count == 3)
							{
								JOptionPane.showMessageDialog(null, "3회 주문으로 쿠폰이 발급되었습니다!", "고객쿠폰발급", JOptionPane.INFORMATION_MESSAGE);
								tmp.iscoupon = true;
								ca.get(i).count = 0;
							}
						}
						
					}
					if(!numcheck && !ord_num.getText().equals("Guest"))
					{
							JOptionPane.showMessageDialog(null, "입력하신 고객번호는 존재하지 않습니다!", "검색 실패", JOptionPane.ERROR_MESSAGE);
					}
					else 
					{
						oa.add(tmp);
						JOptionPane.showMessageDialog(null, "주문이 완료되었습니다!", "주문 완료", JOptionPane.INFORMATION_MESSAGE);
					}
				}
			}
		});
		btnNewButton.setBounds(40, 175, 130, 37);
		order.add(btnNewButton);
		
		JButton button = new JButton("\uC8FC\uBB38 \uCDE8\uC18C");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				boolean exception = false;
				boolean checknum = false;
				if(!ord_date.getText().matches("[0-9|/]*") | !cus_birth.getText().matches("[0-9|/]*"))
				{
					JOptionPane.showMessageDialog(null, "날짜형식에 숫자와 / 외의 다른 문자가 있습니다.", "날짜 형식 예외", JOptionPane.ERROR_MESSAGE);
					exception = true;
				}
				if(!ord_num.getText().matches("[0-9|a-z|A-Z|ㄱ-ㅎ|ㅏ-ㅣ|가-힝|]*"))
				{
					JOptionPane.showMessageDialog(null, "고객번호에 특수문자가 있습니다!", "고객번호 형식 예외", JOptionPane.ERROR_MESSAGE);
					exception = true;
				}
				if(ord_num.getText().length() == 0 || ord_date.getText().length() == 0)
				{
					JOptionPane.showMessageDialog(null, "공란을 채워주세요. 고객번호 없을시 Guest 입력", "고객번호 형식 예외", JOptionPane.ERROR_MESSAGE);
					exception = true;
				}
				
				if(!exception)
				{
					for(int i = 0; i < oa.size(); i++)
					{
						if(ord_num.getText().equals(oa.get(i).getnum()) && ord_date.getText().equals(oa.get(i).getdate()) && comboBox.getSelectedItem().equals(oa.get(i).getmenu()))
						{
							if(oa.get(i).iscoupon)
							{
								JOptionPane.showMessageDialog(null, "주문취소로 쿠폰 발급이 취소되었습니다!", "쿠폰 취소", JOptionPane.ERROR_MESSAGE);
								for(int j = 0; j < ca.size(); j++)
								{
									if(oa.get(i).getnum().equals(ca.get(j).getcusnum()))
									{
										ca.get(j).count = 2;
									}
								}
							}
							else
							{
								for(int j = 0; j < ca.size(); j++)
								{
									if(oa.get(i).getnum().equals(ca.get(j).getcusnum()))
									{
										ca.get(j).count--;
									}
								}
							}
							oa.remove(i);
							checknum = true;
							JOptionPane.showMessageDialog(null, "주문이 취소되었습니다!", "주문 취소", JOptionPane.INFORMATION_MESSAGE);
						}
					}
					if(!checknum)
					{
						JOptionPane.showMessageDialog(null, "해당 주문은 존재하지 않습니다!", "오류 발생", JOptionPane.ERROR_MESSAGE);
					}
				}
			}
		});
		button.setBounds(230, 175, 130, 37);
		order.add(button);
		
		JPanel customer = new JPanel();
		tabbedPane.addTab("\uACE0\uAC1D\uAD00\uB9AC", null, customer, null);
		customer.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("\uACE0\uAC1D\uBC88\uD638");
		lblNewLabel.setBounds(40, 30, 50, 15);
		customer.add(lblNewLabel);
		
		JLabel label_2 = new JLabel("\uACE0\uAC1D\uBA85");
		label_2.setBounds(40, 55, 50, 15);
		customer.add(label_2);
		
		JLabel label_3 = new JLabel("\uC804\uD654\uBC88\uD638");
		label_3.setBounds(40, 80, 50, 15);
		customer.add(label_3);
		
		JLabel label_4 = new JLabel("\uAC00\uC785\uC77C");
		label_4.setBounds(40, 105, 50, 15);
		customer.add(label_4);
		
		JButton btnNewButton_1 = new JButton("\uACE0\uAC1D \uB4F1\uB85D");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				boolean exception = false;
				if(!cus_date.getText().matches("[0-9|/]*") | !cus_birth.getText().matches("[0-9|/]*"))
				{
					JOptionPane.showMessageDialog(null, "날짜형식에 숫자와 / 외의 다른 문자가 있습니다.", "날짜 형식 예외", JOptionPane.ERROR_MESSAGE);
					exception = true;	
				}
				
				if(!cus_phone.getText().matches("[0-9|-]*"))
				{
					JOptionPane.showMessageDialog(null, "번호형식에 숫자와 - 외의 다른 문자가 있습니다.", "번호 형식 예외", JOptionPane.ERROR_MESSAGE);
					exception = true;	
				}
				
				if(!cus_name.getText().matches("[0-9|a-z|A-Z|ㄱ-ㅎ|ㅏ-ㅣ|가-힝|]*"))
				{
					JOptionPane.showMessageDialog(null, "이름에 특수문자가 있습니다!", "이름 형식 예외", JOptionPane.ERROR_MESSAGE);
					exception = true;
				}
				
				if(cus_name.getText().length() > 10)
				{
					JOptionPane.showMessageDialog(null, "이름이 10자를 넘습니다!", "이름 형식 예외", JOptionPane.ERROR_MESSAGE);
					exception = true;
				}
				
				if(!cus_num.getText().matches("[0-9|a-z|A-Z|ㄱ-ㅎ|ㅏ-ㅣ|가-힝|]*"))
				{
					JOptionPane.showMessageDialog(null, "고객번호에 특수문자가 있습니다!", "고객번호 형식 예외", JOptionPane.ERROR_MESSAGE);
					exception = true;
				}
				
				if(cus_num.getText().length() == 0 || cus_name.getText().length() == 0 || cus_birth.getText().length() == 0 || cus_date.getText().length() == 0 || cus_phone.getText().length() == 0)
				{
					JOptionPane.showMessageDialog(null, "입력되지 않은 정보가 있습니다!", "입력 부재 예외", JOptionPane.ERROR_MESSAGE);
					exception = true;
					
				}
				
				for(int i = 0; i < ca.size(); i++)
				{
					if(ca.get(i).getcusnum().equals(cus_num.getText()))
					{
						JOptionPane.showMessageDialog(null, "중복되는 고객 번호가 존재합니다!", "고객 번호 중복", JOptionPane.ERROR_MESSAGE);
						exception = true;
					}
				}
				
			    if(!exception)
			    {
			    	Customer customer = new Customer(cus_num.getText(), cus_name.getText(),cus_phone.getText(),cus_date.getText(),cus_birth.getText());
			    	ca.add(customer);
			    	JOptionPane.showMessageDialog(null, "등록 성공!", "성공", JOptionPane.INFORMATION_MESSAGE);
			    }
			}
		});
		btnNewButton_1.setBounds(12, 178, 91, 23);
		customer.add(btnNewButton_1);
		
		JButton button_1 = new JButton("\uACE0\uAC1D \uAC80\uC0C9");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String searchnum = cus_num.getText();
				Boolean checknum = false;
				if(ca.isEmpty())
				{
					JOptionPane.showMessageDialog(null, "고객리스트가 존재하지 않습니다!", "고객 없음", JOptionPane.ERROR_MESSAGE);
				}
				else
				{
					if(searchnum.length() == 0)
					{
						JOptionPane.showMessageDialog(null, "고객번호를 입력하세요!", "입력 부재 예외", JOptionPane.ERROR_MESSAGE);
					}
					else
					{
						for(int i = 0; i < ca.size(); i++)
						{
							if(ca.get(i).getcusnum().equals(searchnum))
							{
								String result = new String(String.format("Customer Number : %s\nCustomer Registration Date : %s\nCustomer Name : %s\nCustomer Phone : %s\nCustomer Birthday : %s",ca.get(i).getcusnum(),ca.get(i).getregdate(),ca.get(i).getname(),ca.get(i).getphone(),ca.get(i).getbirth()));
								JOptionPane.showMessageDialog(null, result, "Result", JOptionPane.INFORMATION_MESSAGE);
								checknum = true;
							}
						}
						if(!checknum)
						{
							JOptionPane.showMessageDialog(null, "해당 고객번호는 존재하지 않습니다!", "검색 실패", JOptionPane.ERROR_MESSAGE);
						}
					}
				}
			}
		});
		button_1.setBounds(115, 178, 91, 23);
		customer.add(button_1);
		
		JButton button_2 = new JButton("\uACE0\uAC1D \uC0AD\uC81C");
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(ca.isEmpty())
				{
					JOptionPane.showMessageDialog(null, "고객리스트가 존재하지 않습니다!", "고객 없음", JOptionPane.ERROR_MESSAGE);
				}
				else 
				{
					String searchnum = cus_num.getText();
					if(searchnum.length() == 0)
					{
						JOptionPane.showMessageDialog(null, "고객번호를 입력하세요!", "입력 부재 예외", JOptionPane.ERROR_MESSAGE);
					}
					else
					{
						for(int i = 0; i < ca.size(); i++)
						{
							if(ca.get(i).getcusnum().equals(searchnum))
							{
								ca.remove(i);
								JOptionPane.showMessageDialog(null, "삭제 완료!", "삭제", JOptionPane.INFORMATION_MESSAGE);
							}
						}
					}
				}
			}
		});
		button_2.setBounds(218, 178, 91, 23);
		customer.add(button_2);
		
		cus_num = new JTextField();
		cus_num.setBounds(153, 27, 96, 21);
		customer.add(cus_num);
		cus_num.setColumns(10);
		
		cus_name = new JTextField();
		cus_name.setBounds(153, 52, 96, 21);
		customer.add(cus_name);
		cus_name.setColumns(10);
		
		cus_phone = new JTextField();
		cus_phone.setBounds(153, 77, 96, 21);
		customer.add(cus_phone);
		cus_phone.setColumns(10);
		
		cus_date = new JTextField();
		cus_date.setBounds(153, 102, 96, 21);
		customer.add(cus_date);
		cus_date.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("\uC0DD\uC77C");
		lblNewLabel_2.setBounds(40, 130, 50, 15);
		customer.add(lblNewLabel_2);
		
		cus_birth = new JTextField();
		cus_birth.setBounds(153, 127, 96, 21);
		customer.add(cus_birth);
		cus_birth.setColumns(10);
		
		JButton button_3 = new JButton("\uACE0\uAC1D \uC800\uC7A5");
		button_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(ca.isEmpty())
				{
					JOptionPane.showMessageDialog(null, "고객리스트가 존재하지 않습니다!", "고객 없음", JOptionPane.ERROR_MESSAGE);
				}
				else
				{
					try
					{
						FileWriter fw = new FileWriter("custom.txt");
						BufferedWriter bw = new BufferedWriter(fw);
						for(int i = 0; i < ca.size(); i++)
						{
							String format = new String(String.format("%s %s %s %s %s %d", ca.get(i).getcusnum(), ca.get(i).getregdate(), ca.get(i).getname(), ca.get(i).getphone(), ca.get(i).getbirth(), ca.get(i).count));
							bw.write(format);
							for(int j = 0; j < oa.size(); j++)
							{
								if(ca.get(i).getcusnum().equals(oa.get(j).getnum()))
								{
									String format2 = new String(String.format(" %s %s %s", oa.get(j).getdate(), oa.get(j).getnum(), oa.get(j).getmenu()));
									bw.write(format2);
								}
							}
							bw.newLine();
						}
						bw.close();
					}
					catch(IOException e)
					{
						System.err.println(e);
						System.exit(1);
					}
				}
			}
		});
		button_3.setBounds(318, 178, 91, 23);
		customer.add(button_3);
	}
}
