package ru.job4j.map;

import java.util.Calendar;
import java.util.Objects;

/*Общепринятые правила при переопределении метода hashCode():
В самом начале исключаются поля, которые не будут присутствовать в equals.

1. Присвойте результирующей переменной (result) некоторое ненулевое простое число (например, 17)
2. Если поле value имеет тип boolean, вычислите (value ? О : 1)
3. Если поле value имеет тип byte, char, short или int, вычислите (int)value
4. Если поле value имеет тип long, вычислите (int)(value - (value >>> 32))
5. Если поле value имеет тип float, вычислите Float.floatToIntBits(value)
6. Если поле value имеет тип double, вычислите Double.doubleToLongBits(value),
   а затем преобразуйте полученное значение, как указано в п.4
7. Если поле value является ссылкой на объект, вызывайте метод hashCode() этого объекта
8. Если поле value является ссылкой на объект и равно null, используйте число 0
   для представления его хэш-кода
9. Объедините полученные в п. 2 - п. 8 значения следующим образом: 31*result + value
10. Если поле является массивом, примените правило 9 для каждого элемента массива
*/

public class Employee {
    private String name;
    private String position;
    private Calendar birthday;
    private boolean hasAwards;
    private int yearOfService;
    private long socialSecNumber;
    private float overtimePerMonth;
    private double salary;

    public Employee(
            String name, String position, Calendar birthday, boolean hasAwards,
            int yearOfService, long socialSecNumber,
            float overtimePerMonth, double salary) {
        this.name = name;
        this.position = position;
        this.birthday = birthday;
        this.hasAwards = hasAwards;
        this.yearOfService = yearOfService;
        this.socialSecNumber = socialSecNumber;
        this.overtimePerMonth = overtimePerMonth;
        this.salary = salary;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Employee employee = (Employee) obj;
        return Objects.equals(name, employee.name)
                && Objects.equals(position, employee.position)
                && Objects.equals(birthday, employee.birthday)
                && hasAwards == employee.hasAwards
                && yearOfService == employee.yearOfService
                && socialSecNumber == employee.socialSecNumber
                && Float.compare(overtimePerMonth, employee.overtimePerMonth) == 0
                && Double.compare(salary, employee.salary) == 0;
    }

    @Override
    public int hashCode() {
        int rsl = 17;
        rsl += hasAwards ? 1 : 0
                + name.hashCode()
                + position.hashCode()
                + birthday.hashCode()
                + yearOfService
                + (int) (socialSecNumber ^ (socialSecNumber >>> 32))
                + Float.floatToIntBits(overtimePerMonth)
                + Double.doubleToLongBits(salary);
        rsl *= 31;
        return rsl;
    }

}
