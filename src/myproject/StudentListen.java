package myproject;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class StudentListen implements ActionListener{
	
	JButton jbutton_1;
	JButton jbutton_2;
	JTextField jtextfield[];
	public StudentListen(JButton jbutton_1,JButton jbutton_2,JTextField jtextfield[]) {
		this.jbutton_1=jbutton_1;
		this.jbutton_2=jbutton_2;
		this.jtextfield=jtextfield;
	}
	
//	点击了 添加学生的提交 按钮
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
//		点击了提交按钮
		if(e.getSource()==jbutton_1) {
//			判断标志
			boolean mark = false;
			
//			判断学号是否重复
			int k=0;
			for(k=0;k<StudentInformation.all_student.size();k++) {
				if(jtextfield[0].getText().equals(StudentInformation.all_student.get(k).get_Id())) {
//					学号重复
					mark = false;
//					提示
					JOptionPane.showMessageDialog(jbutton_1,
							"学号重复！！！\n添加学生失败！！！","消息提示",JOptionPane.WARNING_MESSAGE);
					break;
				}
			}
			if(k==StudentInformation.all_student.size()) {
				mark = true;
			}
//				mark=true则添加学生信息
			if(mark) {
//				计算总分
				int sum=0;
				for(int i=2;i<StudentInformation.number-1;i++) {
					sum += Integer.parseInt(jtextfield[i].getText());//将字符串先转化为整型用于计算
				}
//				System.out.println(sum);
//				实例化一个学生类用于暂时存放信息
				StudentInformation student=new StudentInformation(jtextfield[0].getText(),
						jtextfield[1].getText(),Integer.parseInt(jtextfield[2].getText()),Integer.parseInt(jtextfield[3].getText()),Integer.parseInt(jtextfield[4].getText()),
						Integer.parseInt(jtextfield[5].getText()),sum);
//				将学生对象加入总对象中
				StudentInformation.all_student.add(student);
				JOptionPane.showMessageDialog(jbutton_1, "添加学生成功！！！", "提示",JOptionPane.PLAIN_MESSAGE);

				try (FileWriter out = new FileWriter("studentMessage_2.txt", true)) {
					//将信息写入本地文本
					out.write((jtextfield[0].getText() + " " + jtextfield[1].getText() + " " + Integer.parseInt(jtextfield[2].getText())
							+ " " + Integer.parseInt(jtextfield[3].getText()) + " " + Integer.parseInt(jtextfield[4].getText())
							+ " " + Integer.parseInt(jtextfield[5].getText()) +  " " + sum + "\n"));
				} catch (IOException ex) {
					ex.printStackTrace();
				}

			}
		}else {
//			总分的默认值不清空
			for(int i=0;i<jtextfield.length-1;i++) {
				jtextfield[i].setText("");
			}
		}
	}
}

// 点击了修改学生的修改按钮
class StudentListen_1 implements ActionListener{
	
	JButton jbutton_3;
	JButton jbutton_4;
	JTextField jtextfield[];
	
	public StudentListen_1(JButton jbutton_3,JButton jbutton_4,JTextField jtextfield[]) {
		this.jbutton_3=jbutton_3;
		this.jbutton_4=jbutton_4;
		this.jtextfield=jtextfield;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
//		点击了 修改 按钮
		if(e.getSource()==jbutton_3) {
			for(int i=0;i<StudentInformation.all_student.size();i++) {
//				学号相同则修改学生信息
				if(jtextfield[0].getText().equals(""+StudentInformation.all_student.get(i).get_Id())) {
//					修改信息(学号不可修改,总分重新计算)
					StudentInformation.all_student.get(i).set_Name(jtextfield[1].getText());
					StudentInformation.all_student.get(i).set_Advanced_Mathematics
					(Integer.parseInt(jtextfield[2].getText()));
					StudentInformation.all_student.get(i).set_Linear_Algebra
					(Integer.parseInt(jtextfield[3].getText()));
					StudentInformation.all_student.get(i).set_Discrete_Mathematics
					(Integer.parseInt(jtextfield[4].getText()));
					StudentInformation.all_student.get(i).set_English
					(Integer.parseInt(jtextfield[5].getText()));
					
//					重新计算总分
					int sum=0;
					for(int j=2;j<StudentInformation.number-1;j++) {
						sum += Integer.parseInt(jtextfield[j].getText());
					}
//					添加总分信息
					StudentInformation.all_student.get(i).set_Total(sum);
//					提示
					JOptionPane.showMessageDialog(jbutton_3, "修改学生信息成功！！！", 
							"提示",JOptionPane.PLAIN_MESSAGE);
					return ;//结束
				}
			}
//			未查询到学生，提示
			JOptionPane.showMessageDialog(jbutton_3,"未查询到当前学生！！！\n修改失败！！！",
					"消息提示",JOptionPane.WARNING_MESSAGE);	
		}else {
			jtextfield[0].setText("");
		}
	}

}

//点击了删除学生的删除按钮
class StudentListen_2 implements ActionListener{
	
	JButton jbutton_5;
	JButton jbutton_6;
	JTextField jtextfield_1;
	
	public StudentListen_2(JButton jbutton_5,JButton jbutton_6,JTextField jtextfield_1) {
		this.jbutton_5=jbutton_5;
		this.jbutton_6=jbutton_6;
		this.jtextfield_1=jtextfield_1;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
//		点击了 删除 按钮
		if(e.getSource()==jbutton_5) {
			for(int i=0;i<StudentInformation.all_student.size();i++) {
//				学号相同则删除学生信息
				if(jtextfield_1.getText().equals(""+StudentInformation.all_student.get(i).get_Id())) {
//					删除信息
					StudentInformation.all_student.remove(i);
					JOptionPane.showMessageDialog(jbutton_5, "删除学生信息成功！！！", 
							"提示",JOptionPane.PLAIN_MESSAGE);
					return ;//结束
				}
			}
//			未查询到学生，提示
			JOptionPane.showMessageDialog(jbutton_5,"未查询到当前学生！！！\n删除失败！！！",
					"消息提示",JOptionPane.WARNING_MESSAGE);	
		}else {
			jtextfield_1.setText("");
		}
	}

}



// 查询学生的 查询按钮 的监听
class StudentListen_3 implements ActionListener{
	
	JButton jbutton_8;
	JButton jbutton_9;
	JButton jbutton_10;
	JTextField jtextfield[];
	
	public StudentListen_3(JButton jbutton_8,JButton jbutton_9,JButton jbutton_10,JTextField jtextfield[]) {
		this.jbutton_8=jbutton_8;
		this.jbutton_9=jbutton_9;
		this.jbutton_10=jbutton_10;
		this.jtextfield=jtextfield;
	}
	
//	点击了 查询学生的查询 按钮
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
//		点击了 查询 按钮
		if(e.getSource()==jbutton_8) {
			for(int i=0;i<StudentInformation.all_student.size();i++) {
//				学号相同则输出学生信息
				if(jtextfield[0].getText().equals(""+StudentInformation.all_student.get(i).get_Id())) {
//					输出信息到文本框
					jtextfield[1].setText(StudentInformation.all_student.get(i).get_Name());
					jtextfield[2].setText(""+StudentInformation.all_student.get(i).get_Advanced_Mathematics());
					jtextfield[3].setText(""+StudentInformation.all_student.get(i).get_Linear_Algebra());
					jtextfield[4].setText(""+StudentInformation.all_student.get(i).get_Discrete_Mathematics());
					jtextfield[5].setText(""+StudentInformation.all_student.get(i).get_English());
					jtextfield[6].setText(""+StudentInformation.all_student.get(i).get_Total());
					return ;//结束
				}
			}
//			未查询到学生，提示
			JOptionPane.showMessageDialog(jbutton_8,"未查询到当前学生！！！",
					"消息提示",JOptionPane.WARNING_MESSAGE);	
		}else if(e.getSource()==jbutton_9) {
			jtextfield[0].setText("");
		}else {
//			点击了输出全部按钮
			StudentUI.init_6(jtextfield);
		}
	}

}
