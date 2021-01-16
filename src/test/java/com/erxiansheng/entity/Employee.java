package com.erxiansheng.entity;

public class Employee {
    private String name;
    private int  empNo;
    private int age;
    private double salary;
    private Status status;

    public Employee() {
    }

    public Employee(String name) {
        this.name = name;
    }

    public Employee(String name, int empNo) {
        this.name = name;
        this.empNo = empNo;
    }

    public Employee(String name, int empNo, int age, double salary) {
        this.name = name;
        this.empNo = empNo;
        this.age = age;
        this.salary = salary;
    }

    public Employee(String name, int empNo, int age, double salary, Status status) {
        this.name = name;
        this.empNo = empNo;
        this.age = age;
        this.salary = salary;
        this.status = status;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getEmpNo() {
        return empNo;
    }

    public void setEmpNo(int empNo) {
        this.empNo = empNo;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "name='" + name + '\'' +
                ", empNo=" + empNo +
                ", age=" + age +
                ", salary=" + salary +
                ", status=" + status +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Employee employee = (Employee) o;

        if (empNo != employee.empNo) return false;
        if (age != employee.age) return false;
        if (Double.compare(employee.salary, salary) != 0) return false;
        return name != null ? name.equals(employee.name) : employee.name == null;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = name != null ? name.hashCode() : 0;
        result = 31 * result + empNo;
        result = 31 * result + age;
        temp = Double.doubleToLongBits(salary);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }

    public enum Status {

        FREE,
        BUSY,
        VACATION;
    }
}
