package app;

import entity.Student;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;


public class StudentManager {
    private static ArrayList<Student> sList =  new ArrayList<>();
    private static int id=1;
    public static void app() {
        while (true) {
            System.out.println("菜单：");
            System.out.println("**********************************");
            System.out.println("*              1 插入            *");
            System.out.println("*              2 查找            *");
            System.out.println("*              3 删除            *");
            System.out.println("*              4 修改            *");
            System.out.println("*              5 输出            *");
            System.out.println("*              6 退出            *");
            System.out.println("**********************************");
            System.out.println("请选择操作：");
            Scanner sc = new Scanner(System.in);
            int input = sc.nextInt();
            switch (input) {
                case 1:
                    addStudent(sList);
                    break;
                case 2:
                    searchStudent();
                    break;
                case 3:
                    deleteStudent(sList);
                    break;
                case 4:
                    updateStudent(sList);
                    break;
                case 5:
                    findAllStudent(sList);
                    break;
                case 6:
                    System.out.println("退出系统");
                    System.exit(0);
                default:
                    System.out.println("输入有误，请重新输入！");
                    break;
            }
        }
    }

    public static boolean isUsed(ArrayList<Student> students, Integer sid) {
        for(Student student:students){
            if(sid.equals(student.getId())){
                return true;
            }
        }
        return false;
    }


    public static void addStudent(ArrayList<Student> students) {
        int age = 0;
        String name = null;
        Date birDate = null;
        boolean gender = false;

        Scanner sc = new Scanner(System.in);
        int cnt = 1;
        while (true) {
            Student student = new Student();
            System.out.println("新增第" + cnt++ + "个学生：");
            student.setId(id++);
            System.out.println("(1)学生姓名：");
            student.setName(new Scanner(System.in).nextLine());
            System.out.println("(2)学生生日(如2020-01-01)：");
            SimpleDateFormat sdf =   new SimpleDateFormat( "yyyy-MM-dd" );
            String input = new Scanner(System.in).nextLine();
            Date date = null;
            try {
                date = sdf.parse(input);
            } catch (ParseException e) {
                System.out.println("输入错误，请重新输入");
                continue;
            }
            student.setBirDate(date);
            System.out.println("(3)学生性别(男/女)：");
            input = new Scanner(System.in).nextLine();
            if ("男".equals(input)) {
                student.setGender(true);
            } else if ("女".equals(input)) {
                student.setGender(false);
            } else {
                System.out.println("输入错误，请重新输入");
                continue;
            }
            students.add(student);
            System.out.println("是否继续新增下一位学生(否-请输入0,是-请输入1)");
            int temp = sc.nextInt();
            if (temp == 0) {
                break;
            }
        }
    }

    public static void searchStudent(){
        boolean flag =false;
        Scanner scanner=new Scanner(System.in);
        System.out.println("学生姓名");
        String name=scanner.nextLine();
        for(Student student:sList){
            if(student.getName().equals(name)){
                student.show();
                flag=true;
            }
        }
        if(!flag){
            System.out.println("没有该位学生");
        }
    }

    public static void deleteStudent(ArrayList<Student> students) {
        if (students.size() == 0) {
            System.out.println("没有该位学生");
            return;
        }
        Scanner sc = new Scanner(System.in);
        System.out.println("学生序号：");
        Integer input = sc.nextInt();
        if (!isUsed(students,input)){
            System.out.println("没有该位学生");
            return;
        }
        int index = 0;
        for (int i = 0; i < students.size(); i++) {
            Student s = students.get(i);
            if (s.getId().equals(input)) {
                index = i;
                break;
            }
        }
        students.remove(index);
        System.out.println("删除成功！");

    }


    public static void updateStudent(ArrayList<Student> students) {
        if (students.size() == 0) {
            System.out.println("没有该位学生");
            return;
        }
        Scanner sc = new Scanner(System.in);
        System.out.println("学生序号：");
        Integer input = sc.nextInt();
        if (!isUsed(students,input)){
            System.out.println("没有该位学生");
            return;

        }
        int index = -1;
        for (int i = 0; i < students.size(); i++) {
            Student s = students.get(i);
            if (s.getId().equals(input)) {
                index = i;
                System.out.println("请问你要修改哪一项信息：");
                System.out.println("1  姓名");
                System.out.println("2  生日");
                System.out.println("3  性别");
                System.out.println("0  退出");
                int s1 = sc.nextInt();
                if (s1 == 0) {
                    break;
                } else if (s1 == 1) {
                    System.out.println("(1)学生姓名：");
                    Scanner scc = new Scanner(System.in);
                    String name = scc.nextLine();
                    s.setName(name);
                } else if (s1 == 2) {
                    System.out.println("(2)学生生日(如2020-01-01)：");
                    SimpleDateFormat sdf =   new SimpleDateFormat( "yyyy-MM-dd" );
                    String temp=new Scanner(System.in).nextLine();
                    Date date = null;
                    try {
                        date = sdf.parse(temp);
                    } catch (ParseException e) {
                        System.out.println("输入错误，请重新输入");
                        continue;
                    }
                    s.setBirDate(date);
                } else if (s1 == 3) {
                    System.out.println("(3)学生性别(男/女)：");
                    String temp = new Scanner(System.in).nextLine();
                    if ("男".equals(temp)) {
                        s.setGender(true);
                    } else if ("女".equals(temp)) {
                        s.setGender(false);
                    } else {
                        System.out.println("输入错误，请重新输入。");
                        continue;
                    }
                } else {
                    System.out.println("输入错误");
                    break;
                }
                System.out.println("修改成功！");
                break;
            }
        }

    }


    public static void findAllStudent(ArrayList<Student> students) {
        if (students.size() == 0) {
            System.out.println("没有该位学生");
            return;
        }
        System.out.println("所有学生的信息如下：");
        for(Student student:students){
            student.show();
        }
    }
}
