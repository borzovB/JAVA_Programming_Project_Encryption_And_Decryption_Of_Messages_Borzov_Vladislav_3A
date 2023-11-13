package com.mycompany.stealthchatnew;

//Класс, который позволяет умножать матрицу на матрицу
public class MultiplicationMatrix {
    private int Array1[][]; // Матрица Array1
    private int Array2[][]; // Матрица Array2
    private int number;     // Число для взятия остатка

    // Конструктор класса, принимает две матрицы и число
    public MultiplicationMatrix(int[][] Array1, int[][] Array2, int number) {
        this.Array1 = Array1;
        this.Array2 = Array2;
        this.number = number;
    }

    // Метод для умножения матриц и взятия остатка
    public int[][] Multiplication() {
        int rows1 = Array1.length;     // Количество строк в Array1

        int[][] result = new int[rows1][rows1]; // Результат умножения матриц

        for (int i = 0; i < rows1; i++) {
            for (int j = 0; j < rows1; j++) {
                int sum = 0;
                for (int k = 0; k < rows1; k++) {
                    // Умножение элементов матрицы Array1 на элементы матрицы Array2
                    sum += Array1[i][k] * Array2[k][j];

                }
                // Взятие остатка от суммы и сохранение результата в матрице result
                result[i][j] = sum%number;

            }
        }

        return result; // Возвращение результирующей матрицы
    }
}
