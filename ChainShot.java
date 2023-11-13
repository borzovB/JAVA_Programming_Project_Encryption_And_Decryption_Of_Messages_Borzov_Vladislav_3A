package com.mycompany.stealthchatnew;

/*Класс, который находит модуль элемента по модуля*/
public class ChainShot { // Объявление класса ChainShot
    private int numerator; // Приватное поле для числителя
    private int denominator; // Приватное поле для знаменателя

    public ChainShot(int numerator, int denominator) { // Конструктор класса с параметрами
        this.numerator = numerator; // Инициализация числителя
        this.denominator = denominator; // Инициализация знаменателя
    }

    public int getMyField() { // Объявление метода getMyField()
        int denominator1 = 0; // Инициализация переменной denominator1
        int numerator1 = 0; // Инициализация переменной numerator1

        if(numerator>denominator){
            denominator1 = Math.floorMod(numerator, denominator); // Вычисление остатка от деления
            numerator1 = denominator;
        }else{
            if (numerator < 0) { // Проверка, если числитель меньше нуля
                denominator1 = Math.floorMod(numerator, denominator); // Вычисление остатка от деления
                numerator1 = denominator; // Присвоение значения числителю
            } else { // В противном случае
                if (numerator < denominator) { // Проверка, если числитель меньше знаменателя

                    denominator1 = denominator; // Присвоение значения знаменателю
                    numerator1 = numerator; // Присвоение значения числителю

                } else { // В противном случае
                    denominator1 = denominator; // Присвоение значения знаменателю
                    numerator1 = numerator; // Присвоение значения числителю
                }
            }
        }

        int[] result = printContinuedFraction(numerator1, denominator1); // Вызов метода printContinuedFraction() и сохранение результата в массив result
        int elements = result.length; // Получение длины массива
        int resultModule = module(result, denominator1, numerator1, elements); // Вызов метода Module() и сохранение результата в resultModule
        return resultModule; // Возврат результата из метода
    }

    public static int[] printContinuedFraction(int numerator, int denominator) { // Объявление статического метода printContinuedFraction() с двумя параметрами
        int[] quotientArray = new int[0]; // Создание пустого массива для хранения частных

        while (denominator != 0) { // Начало цикла, пока знаменатель не равен нулю
            int quotient = numerator / denominator; // Вычисление частного
            int remainder = numerator % denominator; // Вычисление остатка от деления

            // Увеличение размера массива на 1 и добавление частного
            int[] newArray = new int[quotientArray.length + 1]; // Создание нового массива большего размера
            System.arraycopy(quotientArray, 0, newArray, 0, quotientArray.length); // Копирование данных из старого массива в новый
            newArray[quotientArray.length] = quotient; // Добавление частного в конец нового массива
            quotientArray = newArray; // Присвоение нового массива старой переменной

            if (remainder != 0) { // Проверка, если остаток не равен нулю
                numerator = denominator; // Замена числителя знаменателем
                denominator = remainder; // Присвоение нового значения знаменателю
            } else {
                break; // Выход из цикла, если остаток равен нулю
            }
        }

        return quotientArray; // Возврат массива частных из метода
    }

    public static int module(int array[], int num, int num1, int elements) { // Объявление статического метода Module() с четырьмя параметрами
        int[] myArray = new int[array.length]; // Создание нового массива

        int firstX = 0; // Инициализация переменной firstX

        if (array.length == 2) { // Проверка, если длина массива равна 2
            myArray[0] = array[0]; // Присвоение первому элементу массива значения из параметра
            myArray[1] = myArray[0] * array[1] + 1; // Вычисление значения второго элемента массива
            int firstX1 = (int) (Math.pow(-1, elements - 1) * myArray[0]); // Вычисление значения firstX1
            int firstX2 = Math.floorMod(firstX1, num1); // Вычисление остатка от деления
            return firstX2; // Возврат значения firstX2 из метода
        } else { // В противном случае
            myArray[0] = array[0]; // Присвоение первому элементу массива значения из параметра
            myArray[1] = myArray[0] * array[1] + 1; // Вычисление значения второго элемента массива

            for (int i = 2, j = 1, m = 0; i < myArray.length; i++, j++, m++) { // Цикл для вычисления значений остальных элементов массива
                myArray[i] = myArray[j] * array[i] + myArray[m]; // Вычисление значения элемента массива

                if (myArray[i] == num || myArray[i] == num1) { // Проверка, если элемент равен num или num1
                    firstX = myArray[i - 1]; // Присвоение значению firstX предыдущего элемента
                }
            }

            int firstX1 = (int) (Math.pow(-1, elements - 1) * firstX); // Вычисление значения firstX1
            int firstX2 = Math.floorMod(firstX1, num1); // Вычисление остатка от деления
            return firstX2; // Возврат значения firstX2 из метода
        }
    }

}
