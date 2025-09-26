import java.util.Scanner;

public class VolkovLaboratoryWork1 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        // ЗАДАНИЕ 1
        System.out.println("ЗАДАНИЕ 1");
        System.out.println("Числа от 1 до 500:");
        for (int i = 1; i <= 500; i++) {
            if (i % 5 == 0 && i % 7 == 0) {
                System.out.println("fizzbuzz");
            } else if (i % 5 == 0) {
                System.out.println("fizz");
            } else if (i % 7 == 0) {
                System.out.println("buzz");
            } else {
                System.out.println(i);
            }
        }
        System.out.println();
        
        // ЗАДАНИЕ 2
        System.out.println("ЗАДАНИЕ 2");
        String str = "make install";
        String reversed = "";
        
        for (int i = str.length() - 1; i >= 0; i--) {
            reversed += str.charAt(i);
        }
        
        System.out.println("Исходная строка: " + str);
        System.out.println("Перевернутая строка: " + reversed);
        System.out.println();
        
        // ЗАДАНИЕ 3
        System.out.println("ЗАДАНИЕ 3");
        System.out.println("Решение квадратного уравнения ax^2 + bx + c = 0");
        
        System.out.print("Введите коэффициент a: ");
        double a = scanner.nextDouble();
        
        System.out.print("Введите коэффициент b: ");
        double b = scanner.nextDouble();
        
        System.out.print("Введите коэффициент c: ");
        double c = scanner.nextDouble();
        
        double discriminant = b * b - 4 * a * c;
        
        if (discriminant > 0) {
            double x1 = (-b + Math.sqrt(discriminant)) / (2 * a);
            double x2 = (-b - Math.sqrt(discriminant)) / (2 * a);
            System.out.println("Корни уравнения: x1 = " + x1 + ", x2 = " + x2);
        } else if (discriminant == 0) {
            double x = -b / (2 * a);
            System.out.println("Уравнение имеет один корень: x = " + x);
        } else {
            System.out.println("Нет вещественных корней");
        }
        System.out.println();
        
        // ЗАДАНИЕ 4
        System.out.println("ЗАДАНИЕ 4");
        double sum = 0.0;
        double term;
        int n = 2;
        int iterationCount = 0;
        
        System.out.println("Вычисление суммы ряда:");
        do {
            term = 1.0 / (n * n + n - 2);
            if (term < 1e-6) break;
            sum += term;
            n++;
            iterationCount++;
        } while (true);
        
        System.out.println("Сумма ряда: " + sum);
        System.out.println("Вычислено за " + iterationCount + " итераций");
        System.out.println("Последний учтенный член ряда: " + term);
        System.out.println();
        
        // ЗАДАНИЕ 5
        System.out.println("ЗАДАНИЕ 5");
        scanner.nextLine(); // Очистка буфера
        
        System.out.print("Введите строку для проверки на палиндром: ");
        String input = scanner.nextLine();
        
        // Убираем пробелы и приводим к нижнему регистру для универсальности
        String processed = input.replaceAll("\\s+", "").toLowerCase();
        
        boolean isPalindrome = true;
        int left = 0;
        int right = processed.length() - 1;
        
        while (left < right) {
            if (processed.charAt(left) != processed.charAt(right)) {
                isPalindrome = false;
                break;
            }
            left++;
            right--;
        }
        
        System.out.println("Исходная строка: '" + input + "'");
        if (isPalindrome) {
            System.out.println("Результат: строка ЯВЛЯЕТСЯ палиндромом");
        } else {
            System.out.println("Результат: строка НЕ ЯВЛЯЕТСЯ палиндромом");
        }
        
        scanner.close();
    }
}