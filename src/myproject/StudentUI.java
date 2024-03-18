package myproject;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class StudentUI extends Application {
	//	定义容器
	public static JFrame jframe_1 = new JFrame("学生成绩管理系统");
	//	定义面板
	public static JPanel jpanel_1 = new JPanel(new FlowLayout());//设置面板1为流式布局
	public static JPanel jpanel_2 = new JPanel(null);//设置面板2为空布局
	//	设置一个多行文本区用于显示信息
	public static JTextArea j_1 = new JTextArea();

	Stage stage = new Stage();

	LinkedList password = new LinkedList();

	ArrayList li = new ArrayList();

	//File file = new File("Password.txt");

	public static void main(String[] args) {
		Application.launch(args);
	}

	public void start(Stage primaryStage) throws Exception {
		//创建账号和密码标签
		Label l_name = new Label("账号");
		Label l_password = new Label("密码");

		//创建账号和密码输入文本框
		TextField t_name = new TextField();
		t_name.setPromptText("（默认为admin）");
		PasswordField p_password = new PasswordField();
		p_password.setPromptText("（默认为admin）");

		ToggleGroup Group = new ToggleGroup();//单选组
		RadioButton r1 = new RadioButton("学生	");//单选按钮,得放到一个组，不然效果会变成多选
		RadioButton r2 = new RadioButton("老师");
		r1.setToggleGroup(Group);//将1234放入同一个组
		r2.setToggleGroup(Group);
		r2.setSelected(true);

		//创建登录按钮
		Button login = new Button("登录");
		Button zhuCe1 = new Button("注册");

		//创建密码文件对象
		File file = new File("Password.txt");

		//如果txt文本不存在则创造一个对应txt文本文件
		if (!file.exists()) {
			file.createNewFile();
		}

		try {
			//创建一个缓冲方式文本读取
			BufferedReader bufferedReader = new BufferedReader(new FileReader("Password.txt"));
			//判断文本是否为空
			while (bufferedReader.ready()) {
				//读取文本中的账号密码
				password.add(bufferedReader.readLine());
			}
			//关闭流
			bufferedReader.close();
		} catch (Exception e) {
			//上报打印异常
			e.printStackTrace();
		}


		//网格布局
		GridPane gridPane = new GridPane();
		//设置风格为天蓝色
		//gridPane.setStyle("-fx-background-color: deepskyblue");
		//设置标签、输入文本框和登录按钮位置
		gridPane.add(l_name, 0, 0);
		gridPane.add(t_name, 1, 0);
		gridPane.add(l_password, 0, 1);
		gridPane.add(p_password, 1, 1);
		gridPane.add(login, 1, 2);
		gridPane.add(zhuCe1, 2, 2);
		gridPane.add(r1, 0, 2);
		gridPane.add(r2, 1, 2);

		//全部居中
		gridPane.setAlignment(Pos.CENTER);
		//水平间距
		gridPane.setHgap(5);
		//垂直间距
		gridPane.setVgap(15);
		//设置登录左边外边距
		GridPane.setMargin(login, new Insets(0, 0, 0, 120));
		//将girdpane放置在场景中
		Scene s = new Scene(gridPane);
		primaryStage.setScene(s);
		primaryStage.setTitle("登录");
		//设置窗口大小
		primaryStage.setWidth(500);
		primaryStage.setHeight(300);
		//设置窗口不可拉伸
		primaryStage.setResizable(false);
		//展示窗口
		primaryStage.show();

		//回车键按钮事件
		login.setDefaultButton(true);

		//登录按钮点击事件
		login.setOnAction(e -> {
			//获取输入文本框内容
			String name = t_name.getText();
			String Password = p_password.getText();
			//检查账号和密码是否正确

			boolean bl = false;
			Node t = password.getHead();
			for (int i = 0; i < password.getSize(); i++) {
				if (t != null && t.getDate().split(" ")[1].equals(name) && t.getDate().split(" ")[2].equals(Password)) {
					bl = true;
					break;
				}
				t = t.getNext();
			}
			if (bl && r2.isSelected()) {
				//老师用户名密码校验成功后打开主界面
				Platform.runLater(() -> {
					try {
						StudentUI st = new StudentUI();
						st.init_1();
						//C1 open = new C1();
						//open.start(new Stage());
					} catch (Exception exception) {
						exception.printStackTrace();
					}
					//关闭登录窗口
					primaryStage.hide();
				});
			} else if (r1.isSelected()) {
				Platform.runLater(() -> {
					try {
						StudentUI st = new StudentUI();
						st.init_0(name);
						//C1 open = new C1();
						//open.start(new Stage());
					} catch (Exception exception) {
						exception.printStackTrace();
					}
					//关闭登录窗口
					primaryStage.hide();
				});
			} else {
				//检验失败提示错误
				JOptionPane.showMessageDialog(null, "请输入正确的用户名和密码", "用户不存在或者密码错误", JOptionPane.ERROR_MESSAGE);
				//JOptionPane.showMessageDialog(null, "请输入正确的用户名和密码", "用户不存在或者密码错误", JOptionPane.ERROR_MESSAGE);
			}
		});

		zhuCe1.setOnAction(e -> zhuCe());
	}

	private void zhuCe() {
		TextField setId = new TextField();
		TextField setPasswrod = new TextField();

		//创建确定按钮
		Button bt = new Button("确定");
		//创建一个girdpane面板
		GridPane pane = new GridPane();
		//设置行、列之间的垂直和水平间隙
		pane.setHgap(20);
		pane.setVgap(10);

		ToggleGroup Group = new ToggleGroup();//单选组
		RadioButton r1 = new RadioButton("学生	");//单选按钮,得放到一个组，不然效果会变成多选
		RadioButton r2 = new RadioButton("老师");
		r1.setToggleGroup(Group);//将1234放入同一个组
		r2.setToggleGroup(Group);
		r2.setSelected(true);

		//向面板添加带文本的标签并设置位置

		pane.add(new Label("账号："), 0, 2);
		pane.add(setId, 1, 2);
		pane.add(new Label("密码："), 0, 3);
		pane.add(setPasswrod, 1, 3);
		//向面板添加按钮
		pane.add(bt, 2, 4);
		pane.add(r1, 0, 4);
		pane.add(r2, 1, 4);

		//向场景中添加面板
		Scene scene = new Scene(pane, 300, 150);
		//设置窗口的标题
		stage.setTitle("注册");
		stage.setScene(scene);
		stage.show();

		//回车键按钮事件
		bt.setDefaultButton(true);

		//确认按钮点击事件
		bt.setOnAction(e -> {
			//自动关闭流
			try (FileWriter out = new FileWriter("Password.txt", true)) {
				//将信息写入本地文本
				out.write((r1.isSelected() ? "s" : "t") + " " + setId.getText() + " " + setPasswrod.getText() + "\n");
				password.add((r1.isSelected() ? "s" : "t") + " " + setId.getText() + " " + setPasswrod.getText());
			} catch (IOException ex) {
				ex.printStackTrace();
			}
			JOptionPane.showMessageDialog(null, "注册成功！！！", "提示", JOptionPane.PLAIN_MESSAGE);
			//JOptionPane.showMessageDialog(null, "注册成功！！！", "提示",JOptionPane.PLAIN_MESSAGE);
			//展示主窗口
			stage.show();
			//关闭订房窗口
			stage.hide();
		});
	}


	public void init_0(String name) {

//		窗口大小
		jframe_1.setSize(810, 400);
//		空布局
		jframe_1.setLayout(null);
//		窗口不可调整
		jframe_1.setResizable(false);
//		//用户单击窗口的关闭按钮时程序执行的操作
		jframe_1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		菜单栏
		JMenuBar bar = new JMenuBar();
//		一级菜单
		JMenu men_1 = new JMenu("个人信息");
		JMenu men_2 = new JMenu("我的成绩");
//		子菜单
		JMenuItem item_1 = new JMenuItem("学籍信息");

		JMenuItem item_3 = new JMenuItem("全部成绩");

//		定义字体
		Font font = new Font("黑体", Font.PLAIN, 15);

//		设置菜单字体
		men_1.setFont(font);
		men_2.setFont(font);
		item_1.setFont(font);

		item_3.setFont(font);

//		把子菜单添加到对应的主菜单中
		men_1.add(item_1);

		men_2.add(item_3);

		bar.add(men_1);
		bar.add(men_2);
		jframe_1.setJMenuBar(bar);//在窗口中增设菜单条
		jframe_1.add(jpanel_1);
		jframe_1.add(jpanel_2);

//		设置面板位置、大小、颜色
		jpanel_1.setBounds(0, 0, 200, 400);
		jpanel_2.setBounds(210, 0, 600, 400);
		jpanel_1.setBackground(Color.LIGHT_GRAY);
		jpanel_2.setBackground(Color.LIGHT_GRAY);


		//设置文本区不能编辑
		j_1.setEditable(false);
		//将j1作为可滚动面板sp的显示区域
		JScrollPane sp = new JScrollPane(j_1);
		sp.setSize(585, 340);
		StudentUI.jpanel_2.add(sp);

//		窗口居中
		jframe_1.setLocationRelativeTo(null);
		jframe_1.setVisible(true);

		item_1.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
//				调用方法
				StudentUI.init_12(name);
			}
		});

		item_3.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
//				调用方法
				StudentUI.init_13(name);
			}
		});

	}

	//	主界面
	public void init_1() {

//		窗口大小
		jframe_1.setSize(810, 400);
//		空布局
		jframe_1.setLayout(null);
//		窗口不可调整
		jframe_1.setResizable(true);
//		//用户单击窗口的关闭按钮时程序执行的操作
		jframe_1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		菜单栏
		JMenuBar bar = new JMenuBar();
//		一级菜单
		JMenu men_1 = new JMenu("基本操作");
		JMenu men_2 = new JMenu("排序");
//		子菜单
		JMenuItem item_1 = new JMenuItem("添加学生");
		JMenuItem item_2 = new JMenuItem("修改学生");
		JMenuItem item_3 = new JMenuItem("删除学生");
		JMenuItem item_4 = new JMenuItem("查询学生");
		JMenuItem item_7 = new JMenuItem("保存");
		JMenuItem item_8 = new JMenuItem("导入");

		JMenuItem item_5 = new JMenuItem("按总分降序");
		JMenuItem item_6 = new JMenuItem("按某科目降序");
		JMenuItem item_9 = new JMenuItem("学科平均分");
//		定义字体
		Font font = new Font("黑体", Font.PLAIN, 15);

//		设置菜单字体
		men_1.setFont(font);
		men_2.setFont(font);
		item_1.setFont(font);
		item_2.setFont(font);
		item_3.setFont(font);
		item_4.setFont(font);
		item_5.setFont(font);
		item_6.setFont(font);
		item_7.setFont(font);
		item_8.setFont(font);
		item_9.setFont(font);
//		把子菜单添加到对应的主菜单中
		men_1.add(item_1);
		men_1.add(item_2);
		men_1.add(item_3);
		men_1.add(item_4);
		men_1.add(item_7);
		men_1.add(item_8);

		men_2.add(item_5);
		men_2.add(item_6);
		men_2.add(item_9);

		bar.add(men_1);
		bar.add(men_2);
		jframe_1.setJMenuBar(bar);//在窗口中增设菜单条
		jframe_1.add(jpanel_1);
		jframe_1.add(jpanel_2);

//		设置面板位置、大小、颜色
		jpanel_1.setBounds(0, 0, 200, 400);
		jpanel_2.setBounds(210, 0, 600, 400);
		jpanel_1.setBackground(Color.LIGHT_GRAY);
		jpanel_2.setBackground(Color.LIGHT_GRAY);


		//设置文本区不能编辑
		j_1.setEditable(false);
		//将j1作为可滚动面板sp的显示区域
		JScrollPane sp = new JScrollPane(j_1);
		sp.setSize(585, 340);
		StudentUI.jpanel_2.add(sp);

//		窗口居中
		jframe_1.setLocationRelativeTo(null);
//		窗口显示

//		try {
//			StudentUI.init_10();
//		} catch (IOException e1) {
//			// TODO Auto-generated catch block
//			e1.printStackTrace();
//		}
//
		jframe_1.setVisible(true);


//		注册 添加学生 按钮的监听
		item_1.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
//				调用方法
				StudentUI.init_2();
			}
		});
//		注册 修改学生 按钮的监听
		item_2.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				StudentUI.init_3();
			}
		});
//		注册 删除学生 按钮的监听
		item_3.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				StudentUI.init_4();
			}
		});
//		注册 查询学生 按钮的监听
		item_4.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				StudentUI.init_5();
			}
		});
//		按 总分降序按钮 的监听
		item_5.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				StudentUI.init_7();
			}
		});
//		按 某科降序按钮 的监听
		item_6.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String[] options = {"高等数学", "线性代数", "离散数学", "英语"};
				String s = (String) JOptionPane.showInputDialog(null, "请输入你的选项：",
						"提示", JOptionPane.QUESTION_MESSAGE, null, options, null);
				if (s != null) {
					StudentUI.init_8(s);
				}
			}
		});
//		保存 按钮的监听
		item_7.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				StudentUI.init_9();
			}
		});
//		导入 按钮的监听
		item_8.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				try {
					StudentUI.init_10();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
//		查看平均分的监听
		item_9.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				StudentUI.init_11();
			}
		});
	}

	//	添加学生 界面
	public static void init_2() {
//		清空组件
		StudentUI.jpanel_1.removeAll();
//		刷新面板
		StudentUI.jpanel_1.updateUI();
//		字体
//		Font font=new Font("黑体",Font.PLAIN,15);

//		学科名
		String s[] = new String[StudentInformation.number];
		s[0] = " 学       号：";
		s[1] = " 姓       名：";
		s[2] = "高等数学：";
		s[3] = "线性代数：";
		s[4] = "离散数学：";
		s[5] = " 英       语：";
		s[6] = " 总       分：";

//		标签
		JLabel jlabel[] = new JLabel[StudentInformation.number];
//		文本框
		JTextField jtextfield[] = new JTextField[StudentInformation.number];
//		实例化
		for (int i = 0; i < StudentInformation.number; i++) {
			jlabel[i] = new JLabel(s[i]);
//			jlabel[i].setFont(font);
			StudentUI.jpanel_1.add(jlabel[i]);
			jtextfield[i] = new JTextField(12);//指定文本框的长度
			StudentUI.jpanel_1.add(jtextfield[i]);
		}
//		设置文本框默认值(总分自动计算,不可编辑)
		jtextfield[StudentInformation.number - 1].setText("自动计算，无需输入");
		jtextfield[StudentInformation.number - 1].setEditable(false);
//		按钮
		JButton jbutton_1 = new JButton("提交");
		JButton jbutton_2 = new JButton("清除");
//		加入按钮
		StudentUI.jpanel_1.add(jbutton_1);
		StudentUI.jpanel_1.add(jbutton_2);
//		注册监听
		StudentListen e_1 = new StudentListen(jbutton_1, jbutton_2, jtextfield);
		jbutton_1.addActionListener(e_1);
		jbutton_2.addActionListener(e_1);

	}

	//	修改 学生界面
	public static void init_3() {
//		清空组件
		StudentUI.jpanel_1.removeAll();
//		刷新面板
		StudentUI.jpanel_1.updateUI();
//		字体
		Font font = new Font("黑体", Font.PLAIN, 15);
//		学科名
		String s[] = new String[StudentInformation.number];
		s[0] = " 学       号：";
		s[1] = " 姓       名：";
		s[2] = "高等数学：";
		s[3] = "线性代数：";
		s[4] = "离散数学：";
		s[5] = " 英       语：";
		s[6] = " 总       分：";

//		标签
		JLabel jlabel[] = new JLabel[StudentInformation.number];
//		文本框
		JTextField jtextfield[] = new JTextField[StudentInformation.number];
//		按钮
		JButton jbutton_3 = new JButton("修改");
		JButton jbutton_4 = new JButton("清除");
		jbutton_3.setFont(font);
		jbutton_4.setFont(font);

//		实例化
		for (int i = 0, j = 0; i < StudentInformation.number; i++, j++) {
			jlabel[i] = new JLabel(s[i]);
//			jlabel[i].setFont(font);
			StudentUI.jpanel_1.add(jlabel[i]);
			jtextfield[i] = new JTextField(12);
			StudentUI.jpanel_1.add(jtextfield[i]);
//			j控制添加次数防止重复添加
			if (j == 0) {
				JLabel L_1 = new JLabel("学号不能被修改!");
				JLabel L_2 = new JLabel("请在下面输入更新的信息：");
				StudentUI.jpanel_1.add(L_1);
				StudentUI.jpanel_1.add(L_2);
			}
		}
//		设置文本框默认值(总分自动计算,不可编辑)
		jtextfield[StudentInformation.number - 1].setText("自动计算，无需输入");
		jtextfield[StudentInformation.number - 1].setEditable(false);

		StudentUI.jpanel_1.add(jbutton_3);
		StudentUI.jpanel_1.add(jbutton_4);
//		注册监听
		StudentListen_1 e_1 = new StudentListen_1(jbutton_3, jbutton_4, jtextfield);
		jbutton_3.addActionListener(e_1);
		jbutton_4.addActionListener(e_1);
	}

	//	删除 学生界面
	public static void init_4() {
//		清空组件
		StudentUI.jpanel_1.removeAll();
//		刷新面板
		StudentUI.jpanel_1.updateUI();
//		字体
		Font font = new Font("黑体", Font.PLAIN, 15);
//		标签
		JLabel jlabel_1 = new JLabel(" 学       号：");
//		文本框
		JTextField jtextfield_1 = new JTextField(10);
//		添加到面板
		StudentUI.jpanel_1.add(jlabel_1);
		StudentUI.jpanel_1.add(jtextfield_1);

//		按钮
		JButton jbutton_5 = new JButton("删除");
		JButton jbutton_6 = new JButton("清除");
		jbutton_5.setFont(font);
		jbutton_6.setFont(font);
		StudentUI.jpanel_1.add(jbutton_5);
		StudentUI.jpanel_1.add(jbutton_6);

//		注册监听
		StudentListen_2 e_2 = new StudentListen_2(jbutton_5, jbutton_6, jtextfield_1);
		jbutton_5.addActionListener(e_2);
		jbutton_6.addActionListener(e_2);
	}

	//	查询 学生界面
	public static void init_5() {
//		清空组件
		StudentUI.jpanel_1.removeAll();
//		刷新面板
		StudentUI.jpanel_1.updateUI();
//		字体
		Font font = new Font("黑体", Font.PLAIN, 15);
//		学科名
		String s[] = new String[StudentInformation.number];
		s[0] = " 学       号：";
		s[1] = " 姓       名：";
		s[2] = "高等数学：";
		s[3] = "线性代数：";
		s[4] = "离散数学：";
		s[5] = " 英       语：";
		s[6] = " 总       分：";

//		标签
		JLabel jlabel[] = new JLabel[StudentInformation.number];
//		文本框
		JTextField jtextfield[] = new JTextField[StudentInformation.number];
//		按钮
		JButton jbutton_8 = new JButton("查询");
		JButton jbutton_9 = new JButton("清除");
		JButton jbutton_10 = new JButton("输出全部");
		jbutton_8.setFont(font);
		jbutton_9.setFont(font);
		jbutton_10.setFont(font);

//		实例化
		for (int i = 0, j = 0; i < StudentInformation.number; i++, j++) {
			jlabel[i] = new JLabel(s[i]);
//			jlabel[i].setFont(font);
			StudentUI.jpanel_1.add(jlabel[i]);
			jtextfield[i] = new JTextField(12);
			StudentUI.jpanel_1.add(jtextfield[i]);
//			j控制按钮的添加次数防止重复添加
			if (j == 0) {
				StudentUI.jpanel_1.add(jbutton_8);
				StudentUI.jpanel_1.add(jbutton_9);
			}
//			设置文本框不可编辑
			else {
				jtextfield[i].setEditable(false);
			}
		}
		StudentUI.jpanel_1.add(jbutton_10);

//		注册监听
		StudentListen_3 e_3 = new StudentListen_3(jbutton_8, jbutton_9, jbutton_10, jtextfield);
		jbutton_8.addActionListener(e_3);
		jbutton_9.addActionListener(e_3);
		jbutton_10.addActionListener(e_3);
	}

	//	输出全部学生界面
	public static void init_6(JTextField jtextfield[]) {

		//设置容器
		JFrame j = new JFrame("所有学生信息");
		j.setSize(615, 335);
		j.setLayout(null);
		//窗口不能调整
		j.setResizable(false);


		//设置文本区用于显示所有学生信息
		JTextArea j_2 = new JTextArea("文本区可以滑动！！！\n\n关闭当前窗口，再次点击查询所有学生按钮可以刷新！！！\n\n\n");
		//设置文本区不能编辑
		j_2.setEditable(false);
		//将j1作为可滚动面板sp的显示区域
		JScrollPane sp = new JScrollPane(j_2);
		sp.setLocation(0, 0);
		sp.setSize(600, 300);
		j.add(sp);

		//学生当前总人数
		j_2.append("学生当前总人数为：" + StudentInformation.all_student.size() + "\n\n");

		String s[] = new String[StudentInformation.number];
		s[0] = " 学       号：";
		s[1] = " 姓       名：";
		s[2] = "高等数学：";
		s[3] = "线性代数：";
		s[4] = "离散数学：";
		s[5] = " 英       语：";
		s[6] = " 总       分：";
//		将信息添加到滚动文本区
		for (int i = 0; i < StudentInformation.all_student.size(); i++) {
			j_2.append(s[0] + StudentInformation.all_student.get(i).get_Id() + "    ");
			j_2.append(s[1] + StudentInformation.all_student.get(i).get_Name() + "    ");
			j_2.append(s[2] + StudentInformation.all_student.get(i).get_Advanced_Mathematics() + "    ");
			j_2.append(s[3] + StudentInformation.all_student.get(i).get_Linear_Algebra() + "    ");
			j_2.append(s[4] + StudentInformation.all_student.get(i).get_Discrete_Mathematics() + "    ");
			j_2.append(s[5] + StudentInformation.all_student.get(i).get_English() + "    ");
			j_2.append(s[6] + StudentInformation.all_student.get(i).get_Total() + "\n");
		}
//		居中显示
		j.setLocationRelativeTo(null);
//		显示窗口
		j.setVisible(true);
	}

	//	按总分降序排序界面
	public static void init_7() {
//		清空文本区
		StudentUI.j_1.setText("");
//		暂时存储信息
		StudentInformation student = new StudentInformation();
//		//归并算法
		StudentInformation.mergeSort0(StudentInformation.all_student);


		String s[] = new String[StudentInformation.number];
		s[0] = " 学       号：";
		s[1] = " 姓       名：";
		s[2] = "高等数学：";
		s[3] = "线性代数：";
		s[4] = "离散数学：";
		s[5] = " 英       语：";
		s[6] = " 总       分：";

//		将结果展现在文本区
		for (int i = 0; i < StudentInformation.all_student.size(); i++) {
//			添加信息
			j_1.append(s[0] + StudentInformation.all_student.get(i).get_Id() + "    ");
			j_1.append(s[1] + StudentInformation.all_student.get(i).get_Name() + "    ");
			j_1.append(s[2] + StudentInformation.all_student.get(i).get_Advanced_Mathematics() + "    ");
			j_1.append(s[3] + StudentInformation.all_student.get(i).get_Linear_Algebra() + "    ");
			j_1.append(s[4] + StudentInformation.all_student.get(i).get_Discrete_Mathematics() + "    ");
			j_1.append(s[5] + StudentInformation.all_student.get(i).get_English() + "    ");
			j_1.append(s[6] + StudentInformation.all_student.get(i).get_Total() + "\n");
		}
	}

	//	按某科降序排序界面
	public static void init_8(String str) {
//		清空文本区
		StudentUI.j_1.setText("");
//		暂时存储信息
		StudentInformation student = new StudentInformation();

		String s[] = new String[StudentInformation.number];
		s[0] = " 学       号：";
		s[1] = " 姓       名：";
		s[2] = "高等数学：";
		s[3] = "线性代数：";
		s[4] = "离散数学：";
		s[5] = " 英       语：";
		s[6] = " 总       分：";

		if (str.equals("高等数学")) {
//			冒泡排序法
			for (int i = 0; i < StudentInformation.all_student.size() - 1; i++) {
				for (int j = 0; j < StudentInformation.all_student.size() - 1; j++) {
					if (StudentInformation.all_student.get(j).get_Advanced_Mathematics() <
							StudentInformation.all_student.get(j + 1).get_Advanced_Mathematics()) {
						student = StudentInformation.all_student.get(j);
						StudentInformation.all_student.set(j, StudentInformation.all_student.get(j + 1));
						StudentInformation.all_student.set(j + 1, student);
					}
				}
			}
//			将结果展现在文本区
			for (int i = 0; i < StudentInformation.all_student.size(); i++) {
//				添加信息
				j_1.append(s[0] + StudentInformation.all_student.get(i).get_Id() + "    ");
				j_1.append(s[1] + StudentInformation.all_student.get(i).get_Name() + "    ");
				j_1.append(s[2] + StudentInformation.all_student.get(i).get_Advanced_Mathematics() + "    ");
				j_1.append(s[3] + StudentInformation.all_student.get(i).get_Linear_Algebra() + "    ");
				j_1.append(s[4] + StudentInformation.all_student.get(i).get_Discrete_Mathematics() + "    ");
				j_1.append(s[5] + StudentInformation.all_student.get(i).get_English() + "    ");
				j_1.append(s[6] + StudentInformation.all_student.get(i).get_Total() + "\n");
			}
		} else if (str.equals("线性代数")) {
//			冒泡排序法
			for (int i = 0; i < StudentInformation.all_student.size() - 1; i++) {
				for (int j = 0; j < StudentInformation.all_student.size() - 1; j++) {
					if (StudentInformation.all_student.get(j).get_Linear_Algebra() <
							StudentInformation.all_student.get(j + 1).get_Linear_Algebra()) {
						student = StudentInformation.all_student.get(j);
						StudentInformation.all_student.set(j, StudentInformation.all_student.get(j + 1));
						StudentInformation.all_student.set(j + 1, student);
					}
				}
			}
//			将结果展现在文本区
			for (int i = 0; i < StudentInformation.all_student.size(); i++) {
//				添加信息
				j_1.append(s[0] + StudentInformation.all_student.get(i).get_Id() + "    ");
				j_1.append(s[1] + StudentInformation.all_student.get(i).get_Name() + "    ");
				j_1.append(s[2] + StudentInformation.all_student.get(i).get_Advanced_Mathematics() + "    ");
				j_1.append(s[3] + StudentInformation.all_student.get(i).get_Linear_Algebra() + "    ");
				j_1.append(s[4] + StudentInformation.all_student.get(i).get_Discrete_Mathematics() + "    ");
				j_1.append(s[5] + StudentInformation.all_student.get(i).get_English() + "    ");
				j_1.append(s[6] + StudentInformation.all_student.get(i).get_Total() + "\n");
			}
		} else if (str.equals("离散数学")) {
//			冒泡排序法
			for (int i = 0; i < StudentInformation.all_student.size() - 1; i++) {
				for (int j = 0; j < StudentInformation.all_student.size() - 1; j++) {
					if (StudentInformation.all_student.get(j).get_Discrete_Mathematics() <
							StudentInformation.all_student.get(j + 1).get_Discrete_Mathematics()) {
						student = StudentInformation.all_student.get(j);
						StudentInformation.all_student.set(j, StudentInformation.all_student.get(j + 1));
						StudentInformation.all_student.set(j + 1, student);
					}
				}
			}
//			将结果展现在文本区
			for (int i = 0; i < StudentInformation.all_student.size(); i++) {
//				添加信息
				j_1.append(s[0] + StudentInformation.all_student.get(i).get_Id() + "    ");
				j_1.append(s[1] + StudentInformation.all_student.get(i).get_Name() + "    ");
				j_1.append(s[2] + StudentInformation.all_student.get(i).get_Advanced_Mathematics() + "    ");
				j_1.append(s[3] + StudentInformation.all_student.get(i).get_Linear_Algebra() + "    ");
				j_1.append(s[4] + StudentInformation.all_student.get(i).get_Discrete_Mathematics() + "    ");
				j_1.append(s[5] + StudentInformation.all_student.get(i).get_English() + "    ");
				j_1.append(s[6] + StudentInformation.all_student.get(i).get_Total() + "\n");
			}
		} else if (str.equals("英语")) {
//			冒泡排序法
			for (int i = 0; i < StudentInformation.all_student.size() - 1; i++) {
				for (int j = 0; j < StudentInformation.all_student.size() - 1; j++) {
					if (StudentInformation.all_student.get(j).get_English() <
							StudentInformation.all_student.get(j + 1).get_English()) {
						student = StudentInformation.all_student.get(j);
						StudentInformation.all_student.set(j, StudentInformation.all_student.get(j + 1));
						StudentInformation.all_student.set(j + 1, student);
					}
				}
			}
//			将结果展现在文本区
			for (int i = 0; i < StudentInformation.all_student.size(); i++) {
//				添加信息
				j_1.append(s[0] + StudentInformation.all_student.get(i).get_Id() + "    ");
				j_1.append(s[1] + StudentInformation.all_student.get(i).get_Name() + "    ");
				j_1.append(s[2] + StudentInformation.all_student.get(i).get_Advanced_Mathematics() + "    ");
				j_1.append(s[3] + StudentInformation.all_student.get(i).get_Linear_Algebra() + "    ");
				j_1.append(s[4] + StudentInformation.all_student.get(i).get_Discrete_Mathematics() + "    ");
				j_1.append(s[5] + StudentInformation.all_student.get(i).get_English() + "    ");
				j_1.append(s[6] + StudentInformation.all_student.get(i).get_Total() + "\n");
			}
		}
	}

	//	保存学生信息
	public static void init_9() {
		//建立输出
		FileOutputStream out = null;
		try {
			//设置路径文件名,true为 添加方式
			out = new FileOutputStream("studentMessage_1.txt", true);
			String student;
			String explain = "本次保存信息如下：\n";
			byte buf[] = explain.getBytes();
			out.write(buf);
			for (int i = StudentInformation.all_student.size() - 1; i >= 0; i--) {
				//创建缓冲区,写入数据
				student = " 学       号：" + StudentInformation.all_student.get(i).get_Id() + "   ";
				byte buffer[] = student.getBytes();
				out.write(buffer);

				student = " 姓       名：" + StudentInformation.all_student.get(i).get_Name() + "   ";
				byte buffer1[] = student.getBytes();
				out.write(buffer1);

				student = "高等数学：" + StudentInformation.all_student.get(i).get_Advanced_Mathematics() + "   ";
				byte buffer2[] = student.getBytes();
				out.write(buffer2);

				student = "线性代数：" + StudentInformation.all_student.get(i).get_Linear_Algebra() + "   ";
				byte buffer3[] = student.getBytes();
				out.write(buffer3);

				student = "离散数学：" + StudentInformation.all_student.get(i).get_Discrete_Mathematics() + "   ";
				byte buffer4[] = student.getBytes();
				out.write(buffer4);

				student = " 英       语：" + StudentInformation.all_student.get(i).get_English() + "   ";
				byte buffer5[] = student.getBytes();
				out.write(buffer5);

				student = " 总       分：" + StudentInformation.all_student.get(i).get_Total() + "   ";
				byte buffer6[] = student.getBytes();
				out.write(buffer6);
				//换行
				out.write('\n');
			}
			//换行
			out.write('\n');
			out.write('\n');
			JOptionPane.showMessageDialog(null, "保存成功！！！\n保存路径为：D:\\\\studentMessage_1.txt",
					"提示", JOptionPane.PLAIN_MESSAGE);
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			try {
				if (out != null)
					out.close();
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
	}

	//导入学生信息
	public static void init_10() throws IOException {
		//建立输出
		FileReader fr = new FileReader("studentMessage_2.txt");
		BufferedReader br = new BufferedReader(fr);
		String line;
		while ((line = br.readLine()) != null) {
			String[] array = line.split(" ");

			String id = array[0];
			String name = array[1];
			int advanced_Mathematics = Integer.parseInt(array[2]);
			int inear_Algebra = Integer.parseInt(array[3]);
			int discrete_Mathematics = Integer.parseInt(array[4]);
			int English = Integer.parseInt(array[5]);
			int Total = Integer.parseInt(array[6]);
			//创建一个学生对象

			StudentInformation student = new StudentInformation(id, name, advanced_Mathematics, inear_Algebra, discrete_Mathematics, English, Total);
//			将学生对象加入总对象中
			StudentInformation.all_student.add(student);

			//将信息添加到滚动文本区
			//j_1.append(line);

			String s[] = new String[StudentInformation.number];
			s[0] = " 学       号：";
			s[1] = " 姓       名：";
			s[2] = "高等数学：";
			s[3] = "线性代数：";
			s[4] = "离散数学：";
			s[5] = " 英       语：";
			s[6] = " 总       分：";
				/*
			System.out.println(StudentInformation.all_student.size());
				for(int i=StudentInformation.all_student.size()-1;i<StudentInformation.all_student.size();i++) {
//					添加信息

				 */
			int i = StudentInformation.all_student.size() - 1;
			j_1.append(s[0] + StudentInformation.all_student.get(i).get_Id() + "    ");
			j_1.append(s[1] + StudentInformation.all_student.get(i).get_Name() + "    ");
			j_1.append(s[2] + StudentInformation.all_student.get(i).get_Advanced_Mathematics() + "    ");
			j_1.append(s[3] + StudentInformation.all_student.get(i).get_Linear_Algebra() + "    ");
			j_1.append(s[4] + StudentInformation.all_student.get(i).get_Discrete_Mathematics() + "    ");
			j_1.append(s[5] + StudentInformation.all_student.get(i).get_English() + "    ");
			j_1.append(s[6] + StudentInformation.all_student.get(i).get_Total() + "\n");

		}
		br.close();
	}

	//显示各科平均分
	public static void init_11() {
//			清空文本区
		StudentUI.j_1.setText("");
//			暂时存储信息

		String s[] = new String[5];
		s[0] = "高等数学：";
		s[1] = "线性代数：";
		s[2] = "离散数学：";
		s[3] = " 英       语：";
		s[4] = " 总       分：";
		//计算平均分
		int sumA = 0;
		int sumL = 0;
		int sumD = 0;
		int sumE = 0;
		int sumT = 0;
		for (int i = 0; i < StudentInformation.all_student.size(); i++) {
			sumA += StudentInformation.all_student.get(i).get_Advanced_Mathematics();
			sumL += StudentInformation.all_student.get(i).get_Linear_Algebra();
			sumD += StudentInformation.all_student.get(i).get_Discrete_Mathematics();
			sumE += StudentInformation.all_student.get(i).get_English();
			sumT += StudentInformation.all_student.get(i).get_Total();
		}

		j_1.append(s[0] + sumA / StudentInformation.all_student.size() + "   ");
		j_1.append(s[1] + sumL / StudentInformation.all_student.size() + "   ");
		j_1.append(s[2] + sumD / StudentInformation.all_student.size() + "   ");
		j_1.append(s[3] + sumE / StudentInformation.all_student.size() + "   ");
		j_1.append(s[4] + sumT / StudentInformation.all_student.size() + "   ");

	}

	public static void init_12(String name) {
//		清空组件
		StudentUI.jpanel_1.removeAll();
//		刷新面板
		StudentUI.jpanel_1.updateUI();
//		字体
//		Font font=new Font("黑体",Font.PLAIN,15);

		String a = "";
		String c = "";
		try {
			//创建一个缓冲方式文本读取
			BufferedReader bufferedReader = new BufferedReader(new FileReader("studentMessage_2.txt"));
			//判断文本是否为空
			while (bufferedReader.ready()) {
				//读取文本
				a = bufferedReader.readLine();
				if (name.equals(a.split(" ")[0])){
					c = a;
				}
			}
			//关闭流
			bufferedReader.close();
		} catch (Exception e) {
			//上报打印异常
			e.printStackTrace();
		}

//		学院名
		String[] b = new String[5];
		b[0] = "机电工程学院";
		b[1] = "信息与通信学院";
		b[2] = "计算机信息技术学院";
		b[3] = "艺术与设计学院";
		b[4] = "商学院";


//		标签
		JLabel jlabel[] = new JLabel[StudentInformation.number];
//
		StudentUI.jpanel_1.add(new JLabel(" 学       号：" + name));
		StudentUI.jpanel_1.add(new JLabel(" 姓       名：" + c.split(" ")[1]));
		StudentUI.jpanel_1.add(new JLabel(" 年       级：" + "20" + name.substring(0, 2) + "级"));
		StudentUI.jpanel_1.add(new JLabel(" 学       				院：" + b[Integer.parseInt(name.substring(4, 5)) - 1]));
		StudentUI.jpanel_1.add(new JLabel(" 学	生	状	态：" + "在	校"));
		StudentUI.jpanel_1.add(new JLabel(" 学	生	类	型：" + "本科"));
	}

	public static void init_13(String name) {

		StudentUI.j_1.setText("");
//		清空组件
		StudentUI.jpanel_1.removeAll();
//		刷新面板
		StudentUI.jpanel_1.updateUI();
//		字体
//		Font font=new Font("黑体",Font.PLAIN,15);

		String a = "";
		String b = "";
		try {
			//创建一个缓冲方式文本读取
			BufferedReader bufferedReader = new BufferedReader(new FileReader("studentMessage_2.txt"));
			//判断文本是否为空
			while (bufferedReader.ready()) {
				//读取文本
				a = bufferedReader.readLine();
				if (name.equals(a.split(" ")[0])){
					b = a;
				}
			}
			//关闭流
			bufferedReader.close();
		} catch (Exception e) {
			//上报打印异常
			e.printStackTrace();
		}


		String s[] = new String[5];
		s[0] = "高等数学：";
		s[1] = "线性代数：";
		s[2] = "离散数学：";
		s[3] = " 英       语：";
		s[4] = " 总       分：";

		j_1.append(s[0] +  b.split(" ")[2]+ "   ");
		j_1.append(s[1] +  b.split(" ")[3]+ "   ");
		j_1.append(s[2] +  b.split(" ")[4]+ "   ");
		j_1.append(s[3] +  b.split(" ")[5]+ "   ");
		j_1.append(s[4] +  b.split(" ")[6]+ "   ");

	}
}

