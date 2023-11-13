package com.mycompany.stealthchatnew;

//Класс который преобразовывает массив из символов в массив кодировки символов
public class ConvertingCharactersToNumbers {
    private char[] arrayChar;  // Массив символов для преобразования
    private int squareRoot;    // Квадратный корень числа символов
    private char[] arrayCharDecryptionCipher;  // Массив символов для дешифровки

    public ConvertingCharactersToNumbers(char[] arrayChar, int squareRoot, char[] arrayCharDecryptionCipher) {
        this.arrayChar = arrayChar;
        this.squareRoot =squareRoot;
        this.arrayCharDecryptionCipher = arrayCharDecryptionCipher;
    }

    // Метод преобразует символьный код из символов в числа, используя массив символов
    public int[][] getArrayCharConvertingCharactersToNumbersForTheDeterminant() {
        Alphabet alphabet = new Alphabet();
        char[] counting = alphabet.getAlphabet();
        int[][] toNumbers = new int[squareRoot][squareRoot];
        int[] toNumbersOne = new int[squareRoot*squareRoot];
        for(int i = 0; i<(squareRoot*squareRoot);i++){
            for(int j = 0;j<counting.length;j++){
                if (arrayChar[i] == counting[j]) {
                    toNumbersOne[i] = j;
                    break; // Если символ найден, можно выйти из внутреннего цикла
                }
            }
        }

        int index = 0; // Инициализируем переменную index для отслеживания текущего индекса в массиве ToNumbersOne
        for (int i = 0; i < squareRoot; i++) { // Внешний цикл для перебора строк массива ToNumbers
            for (int j = 0; j < squareRoot; j++) { // Внутренний цикл для перебора столбцов массива ToNumbers
                toNumbers[i][j] = toNumbersOne[index]; // Присваиваем элементу ToNumbers[i][j] значение из ToNumbersOne[index]
                index++;
                // Увеличиваем индекс, чтобы перейти к следующему значению в ToNumbersOne
            }
        }
        return toNumbers;
    }
    // Метод преобразует символьный код из символов в числа для дешифровки
    public int[] alphabetArray() {
        Alphabet alphabet = new Alphabet();
        char[] counting = alphabet.getAlphabet();
        int n = arrayCharDecryptionCipher.length;
        int[] toNumbersOne = new int[n];
        for(int i = 0; i<(n);i++){
            for(int j = 0;j<counting.length;j++){
                if (arrayCharDecryptionCipher[i] == counting[j]) {
                    toNumbersOne[i] = j;
                    break; // Если символ найден, можно выйти из внутреннего цикла
                }
            }
        }

        return toNumbersOne;
    }
    public int сheck(char[] arrayCharStr){
        /*Определение соответствия символов*/
        int checking = 0;
        Alphabet alphabet = new Alphabet();
        char[] counting = alphabet.getAlphabet();
        /*Проходит по массиву строки или ключа и сравнивает символы в них с символами в библиотеки*/
        for (int i = 0; i < arrayCharStr.length; i++) {
            for (int j = 0; j < counting.length; j++) {
                if (arrayCharStr[i] == counting[j]) {
                    checking++;
                    break;
                    /*Если есть совпадение символов, то считаем его*/
                }
            }
        }
        return checking;
    }

    public int getArrayAlphabetSize() {
        //Вывод длины массива алфавита
        Alphabet alphabet = new Alphabet();
        char[] counting = alphabet.getAlphabet();
        int lenArray = counting.length;

        return lenArray;
    }

}
