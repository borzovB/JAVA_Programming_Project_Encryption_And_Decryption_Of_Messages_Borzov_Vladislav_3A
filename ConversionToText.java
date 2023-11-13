package com.mycompany.stealthchatnew;

//Класс для умножения матрицы расшифровки или найденной матрицы на часть кодировки части строки
public class ConversionToText {
    private int[][] Array1; // Матрица чисел для умножения
    private int[] Array2;   // Массив чисел для умножения
    private int number;     // Число, используемое при умножении
    private int lens;       // Размерность (квадратный корень) матрицы

    public ConversionToText(int[][] Array1, int[] Array2, int number, int lens) {
        // Конструктор класса, принимает матрицу, массив чисел, число и размерность
        this.Array1 = Array1;
        this.Array2 = Array2;
        this.number = number;
        this.lens = lens;
    }
    // Метод для умножения матрицы на массив и возврата результата в виде массива символов
    public char[] MultiplyingMatrixByMatrix() {
        int[][] arrayMatrix = new int[lens][lens];
        int[][] arrayMatrix1 = new int[lens][lens];
        char []conversionToText = new char[lens];
        char[] arrayChars = new char[lens];
        // Заполняем первый столбец матрицы arrayMatrix значениями из Array2
        for (int i = 0; i < lens; i++) {
            arrayMatrix[i][0] = Array2[i];
        }
        // Выполняем умножение матрицы Array1 на arrayMatrix с использованием числа number
        MultiplicationMatrix multiplicationMatrix = new MultiplicationMatrix(Array1, arrayMatrix, number);
        arrayMatrix1 = multiplicationMatrix.Multiplication();
        // Преобразуем числовой код в символы и сохраняем результат в conversionToText
        ConvertingNumericCodeIntoCharacters convertingNumericCodeIntoCharacters = new ConvertingNumericCodeIntoCharacters(arrayMatrix1, lens);
        conversionToText = convertingNumericCodeIntoCharacters.сonvertingStringCodeFromNumbersToCharacters();
        return conversionToText;
    }
}
