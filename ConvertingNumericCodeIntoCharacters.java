package com.mycompany.stealthchatnew;

/*Класс для преобразования кодировки символов в символы*/
public class ConvertingNumericCodeIntoCharacters {
    private int[][] arrayInt;
    private int squareRoot;
    // Конструктор класса, принимает двумерный массив чисел и квадратный корень числа символов
    public ConvertingNumericCodeIntoCharacters(int[][] arrayInt, int squareRoot) {
        this.arrayInt = arrayInt;
        this.squareRoot = squareRoot;
    }

    public char[] сonvertingStringCodeFromNumbersToCharacters() {
        Alphabet alphabet = new Alphabet();
        char[] counting = alphabet.getAlphabet();
        //Вызываем класс, который хранит символоы и их кодировку
        char[] toNumbersOne = new char[squareRoot];
        //Инициализируем матрицу кодировок и символов
        // Проходим по каждому числу и находим соответствующий символ из массива символов
        for (int i = 0; i < squareRoot; i++) {
            for (int j = 0; j < counting.length; j++) {
                if (arrayInt[i][0] == j) {
                    toNumbersOne[i] = counting[j];
                    break;
                }
            }
        }
        return toNumbersOne;
        /*Возвращаем строку символов*/
    }

}
