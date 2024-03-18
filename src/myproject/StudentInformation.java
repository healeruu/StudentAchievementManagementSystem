package myproject;

import java.util.ArrayList;

//定义学生类
public class StudentInformation {
	
//	存储学生总人数
	public static ArrayList<StudentInformation> all_student=new ArrayList<StudentInformation>();
//	学科数量,包括学号和姓名
	public static int number=7;
	
	private  String id;//学号
	private  String name;//姓名
	private  int advanced_Mathematics;//高等数学
	private  int linear_Algebra;//线性代数
	private  int discrete_Mathematics;//离散数学
	private  int English;//英语
	private  int Total;//总分
	
	//用于实例化
	public StudentInformation() {}
	
	public StudentInformation(String id,String name,
			int advanced_Mathematics,int linear_algebra,
			int discrete_mathematics,int English,int Total) {
		
		this.id=id;
		this.name=name;
		this.advanced_Mathematics=advanced_Mathematics;
		this.linear_Algebra=linear_algebra;
		this.discrete_Mathematics=discrete_mathematics;
		this.English=English;
		this.Total=Total;
	}
	
	//获得数据
	public String get_Id() {
		return id;
	}
	public String get_Name() {
		return name;
	}
	public int get_Advanced_Mathematics() {
		return advanced_Mathematics;
	}
	public int get_Linear_Algebra() {
		return linear_Algebra;
	}
	public int get_Discrete_Mathematics() {
		return discrete_Mathematics;
	}
	public int get_English() {
		return English;
	}
	public int get_Total() {
		return Total;
	}
	
	//修改数据
	public void set_Id(String id) {
		this.id=id;
	}
	public void set_Name(String name) {
		this.name=name;
	}
	public void set_Advanced_Mathematics(int advanced_Mathematics) {
		this.advanced_Mathematics=advanced_Mathematics;
	}
	public void set_Linear_Algebra(int linear_Algebra) {
		this.linear_Algebra=linear_Algebra;
	}
	public void set_Discrete_Mathematics(int discrete_Mathematics) {
		this.discrete_Mathematics=discrete_Mathematics;
	}
	public void set_English(int English) {
		this.English=English;
	}
	public void set_Total(int Total) {
		this.Total=Total;
	}

	public void mergeSort() {
		mergeSort0(all_student);
	}

//	public static String avg(ArrayList array,String a) {
//
//	}

	//归并算法
	public static void mergeSort0(ArrayList array) {
		if (array == null || array.size() <= 1) {
			return;
		}

		sort0(array, 0, array.size() - 1);
	}

	private static void sort0(ArrayList array, int left, int right) {
		if (left == right) {
			return;
		}
		int mid = left + ((right - left) >> 1);
		// 对左侧子序列进行递归排序
		sort0(array, left, mid);
		// 对右侧子序列进行递归排序
		sort0(array, mid + 1, right);
		// 合并
		merge0(array, left, mid, right);
	}

	private static void merge0(ArrayList array, int left, int mid, int right) {
		//int[] temp = new int[right - left + 1];
		ArrayList temp = new ArrayList();

		for(int j = 0;j < right - left + 1;j++){
			temp.add("");
		}

		int i = 0;
		int p1 = left;
		int p2 = mid + 1;
		// 比较左右两部分的元素，哪个小，把那个元素填入temp中
		while (p1 <= mid && p2 <= right) {
			//temp[i++] = array[p1] < array[p2] ? array[p1++] : array[p2++];
			temp.set(i++,((StudentInformation)array.get(p1)).get_Total() > ((StudentInformation)array.get(p2)).get_Total() ? array.get(p1++) : array.get(p2++));
		}
		// 上面的循环退出后，把剩余的元素依次填入到temp中
		// 以下两个while只有一个会执行
		while (p1 <= mid) {
			//temp[i++] = array[p1++];]
			temp.set(i++,array.get(p1++));
		}
		while (p2 <= right) {
			//temp[i++] = array[p2++];
			temp.set(i++,array.get(p2++));
		}
		// 把最终的排序的结果复制给原数组
		for (i = 0; i < temp.size(); i++) {
			//array[left + i] = temp[i];
			array.set(left + i,temp.get(i));
		}
	}

}