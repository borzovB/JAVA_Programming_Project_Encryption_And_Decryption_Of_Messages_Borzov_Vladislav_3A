package com.mycompany.stealthchatnew;

/*Основной класс программы*/
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import javax.swing.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.*;
import java.io.*;
import java.util.Random;
import javax.swing.UIManager.LookAndFeelInfo;

public class StealthchatNew extends javax.swing.JFrame implements java.awt.event.ActionListener{
    // Массив доступных внешних видов интерфейса
    private static UIManager.LookAndFeelInfo[] installedLookAndFeels;

    // Индекс текущего выбранного внешнего вида
    private static int currentStyleIndex = 0;

    // Название файла, в котором будет храниться информация о стиле
    private static final String styleFileName = "style.txt";

    // Поле ввода ключа
    javax.swing.JTextField tfKeyword;

    // Кнопка для расшифровки текста
    javax.swing.JButton btnDecifer;

    // Кнопка для шифрования текста
    javax.swing.JButton btnEncrypt;

    // Кнопка для генерации ключей
    javax.swing.JButton keyGeneration;

    // Кнопка для изменения внешнего вида интерфейса
    javax.swing.JButton styleButton;

    // Многострочное текстовое поле для ввода текста
    javax.swing.JTextArea ta;

    // Второе многострочное текстовое поле для вывода текста
    javax.swing.JTextArea ta2;


    public StealthchatNew(){
        /*Метод содержащий графический интерфейс*/
        super("StealthChatNew");
        /*Имя окна*/
        tfKeyword = new javax.swing.JTextField(40);
        /*Создаем поле ввода ключа*/
        Font font = new Font("Arial", Font.BOLD, 14);
        tfKeyword.setForeground(Color.RED);
        /*Устанавливаем размер,цвет и толщину текста в этом окне*/
        /*Эти параметры мы будем использовать для других окон*/
        tfKeyword.setFont(font);

        btnDecifer = new javax.swing.JButton("Расшифровать");
        /*Создаем кнопку для для расшифрования текста*/
        btnDecifer.addActionListener(this);
        btnDecifer.setActionCommand("cmd_decifer");

        Font boldFont = btnDecifer.getFont().deriveFont(Font.BOLD);
        /*Создаем параметор подписи кнопок, его мы применяем ко всем кнопкам*/
        btnDecifer.setFont(boldFont);

        keyGeneration = new javax.swing.JButton("Генерация ключа");
        /*Создадим кпопку для генерации ключа*/
        keyGeneration.addActionListener(this);
        keyGeneration.setActionCommand("cmd_keyGeneration");
        keyGeneration.setFont(boldFont);

        installedLookAndFeels = UIManager.getInstalledLookAndFeels();
        /*Создаем кнопку для изменения фона или стиля приложения*/
        styleButton = new javax.swing.JButton("Изменить стиль");
        styleButton.addActionListener(this);
        styleButton.setActionCommand("cmd_style");
        styleButton.setFont(boldFont);

        // Создаем новую кнопку с текстом "Закрыть".
        JButton closeButton = new JButton("Закрыть");

        // Добавляем обработчика событий для кнопки, в данном случае, this указывает на текущий объект, который обрабатывает события (скорее всего, текущий класс реализует интерфейс ActionListener).
        closeButton.addActionListener(this);
        // Устанавливаем команду (ActionCommand), которая будет связана с этой кнопкой. Это полезно, если у вас есть несколько кнопок, и вы хотите различать их события.
        closeButton.setActionCommand("cmd_close_window");
        // Устанавливаем красный фон для кнопки.
        closeButton.setBackground(Color.RED);
        // Устанавливаем черный цвет текста на кнопке.
        closeButton.setForeground(Color.BLACK);
        // Устанавливаем шрифт для текста на кнопке, boldFont - это уже ранее определенный шрифт (возможно, жирный).
        closeButton.setFont(boldFont);


        /* Создаем кнопку для зашифровывания сообщений */
        btnEncrypt = new javax.swing.JButton("Зашифровать");
        /* Добавляем слушателя событий к кнопке, чтобы реагировать на нажатие */
        btnEncrypt.addActionListener(this);
        /* Устанавливаем команду действия для кнопки. Эта команда будет использоваться
         * для определения, какое действие должно быть выполнено при нажатии кнопки. */
        btnEncrypt.setActionCommand("cmd_encrypt");
        /* Устанавливаем шрифт для текста на кнопке */
        btnEncrypt.setFont(boldFont);


        /* Создаем кнопку для копирования текста в буфер обмена */
        JButton btnTa = new JButton("Скопировать текст в буфер обмена");
        /* Добавляем слушателя событий к кнопке, чтобы реагировать на нажатие */
        btnTa.addActionListener(this);
        /* Устанавливаем команду действия для кнопки. Эта команда будет использоваться
         * для определения, какое действие должно быть выполнено при нажатии кнопки. */
        btnTa.setActionCommand("cmd_tacopy");
        /* Устанавливаем шрифт для текста на кнопке */
        btnTa.setFont(boldFont);
        /* Устанавливаем горизонтальное выравнивание кнопки по центру */
        btnTa.setAlignmentX(java.awt.Component.CENTER_ALIGNMENT);
        /* Устанавливаем максимальную ширину кнопки, но ограничиваем ее по высоте */
        btnTa.setMaximumSize(new Dimension(700, btnTa.getMaximumSize().height));


        /* Создаем кнопку для копирования результата в буфер обмена */
        JButton btnTa2 = new JButton("Скопировать результат в буфер обмена");
        /* Добавляем слушателя событий к кнопке, чтобы реагировать на нажатие */
        btnTa2.addActionListener(this);
        /* Устанавливаем команду действия для кнопки. Эта команда будет использоваться, чтобы определить, какое действие должно быть выполнено при нажатии кнопки. */
        btnTa2.setActionCommand("cmd_ta2copy");
        /* Устанавливаем шрифт для текста на кнопке */
        btnTa2.setFont(boldFont);
        /* Устанавливаем горизонтальное выравнивание кнопки по центру */
        btnTa2.setAlignmentX(java.awt.Component.CENTER_ALIGNMENT);
        /* Устанавливаем максимальную ширину кнопки, ограничивая ее по высоте */
        btnTa2.setMaximumSize(new Dimension(700, btnTa2.getMaximumSize().height));


        /* Создаем кнопку для копирования ключа в буфер обмена */
        JButton btnKey = new JButton("Скопировать ключ в буфер обмена");
        /* Добавляем слушателя событий к кнопке, чтобы реагировать на нажатие */
        btnKey.addActionListener(this);
        /* Устанавливаем команду действия для кнопки. Эта команда будет использоваться, чтобы определить, какое действие должно быть выполнено при нажатии кнопки. */
        btnKey.setActionCommand("cmd_keycopy");
        /* Устанавливаем шрифт для текста на кнопке */
        btnKey.setFont(boldFont);
        /* Устанавливаем горизонтальное выравнивание кнопки по центру */
        btnKey.setAlignmentX(java.awt.Component.CENTER_ALIGNMENT);


        /* Создаем кнопку для очистки поля ключа */
        JButton clearKeyword = new JButton("Очистить поле ключа");
        /* Добавляем слушателя событий к кнопке, чтобы реагировать на нажатие */
        clearKeyword.addActionListener(this);
        /* Устанавливаем команду действия для кнопки. Эта команда будет использоваться, чтобы определить, какое действие должно быть выполнено при нажатии кнопки. */
        clearKeyword.setActionCommand("cmd_clearKeyword");
        /* Устанавливаем шрифт для текста на кнопке */
        clearKeyword.setFont(boldFont);


        /* Создаем кнопку для очистки поля ввода текста */
        JButton clearButtonText = new JButton("Очистить поле ввода текста");
        /* Добавляем слушателя событий к кнопке, чтобы реагировать на нажатие */
        clearButtonText.addActionListener(this);
        /* Устанавливаем команду действия для кнопки. Эта команда будет использоваться, чтобы определить, какое действие должно быть выполнено при нажатии кнопки. */
        clearButtonText.setActionCommand("cmd_clearText");
        /* Устанавливаем шрифт для текста на кнопке */
        clearButtonText.setFont(boldFont);
        /* Устанавливаем горизонтальное выравнивание кнопки по центру */
        clearButtonText.setAlignmentX(java.awt.Component.CENTER_ALIGNMENT);
        /* Устанавливаем максимальную ширину кнопки, ограничивая ее по высоте */
        clearButtonText.setMaximumSize(new Dimension(700, clearButtonText.getMaximumSize().height));


        /* Создаем кнопку для очистки поля вывода результата */
        JButton clearButtonResult = new JButton("Очистить поле вывода результата");
        /* Добавляем слушателя событий к кнопке, чтобы реагировать на нажатие */
        clearButtonResult.addActionListener(this);
        /* Устанавливаем команду действия для кнопки. Эта команда будет использоваться, чтобы определить, какое действие должно быть выполнено при нажатии кнопки. */
        clearButtonResult.setActionCommand("cmd_clearResult");
        /* Устанавливаем шрифт для текста на кнопке */
        clearButtonResult.setFont(boldFont);
        /* Устанавливаем горизонтальное выравнивание кнопки по центру */
        clearButtonResult.setAlignmentX(java.awt.Component.CENTER_ALIGNMENT);
        /* Устанавливаем максимальную ширину кнопки, ограничивая ее по высоте */
        clearButtonResult.setMaximumSize(new Dimension(700, clearButtonResult.getMaximumSize().height));


        /* Создаем кнопку для очистки всех полей */
        JButton clearButtonAllFields = new JButton("Очистить все поля");
        /* Добавляем слушателя событий к кнопке, чтобы реагировать на нажатие */
        clearButtonAllFields.addActionListener(this);
        /* Устанавливаем команду действия для кнопки. Эта команда будет использоваться, чтобы определить, какое действие должно быть выполнено при нажатии кнопки. */
        clearButtonAllFields.setActionCommand("cmd_clearAllFields");
        /* Устанавливаем шрифт для текста на кнопке */
        clearButtonAllFields.setFont(boldFont);
        /* Устанавливаем максимальную ширину кнопки, ограничивая ее по высоте */
        clearButtonAllFields.setMaximumSize(new Dimension(700, clearButtonAllFields.getMaximumSize().height));


        /* Создаем кнопку для вставки текста из буфера обмена в поле ввода текста */
        JButton pasteButton1 = new JButton("Вставить в поле ввода текста из буфера обмена");
        /* Добавляем слушателя событий к кнопке, чтобы реагировать на нажатие */
        pasteButton1.addActionListener(this);
        /* Устанавливаем команду действия для кнопки. Эта команда будет использоваться, чтобы определить, какое действие должно быть выполнено при нажатии кнопки. */
        pasteButton1.setActionCommand("cmd_paste_ta");
        /* Устанавливаем шрифт для текста на кнопке */
        pasteButton1.setFont(boldFont);
        /* Устанавливаем горизонтальное выравнивание кнопки по центру */
        pasteButton1.setAlignmentX(java.awt.Component.CENTER_ALIGNMENT);
        /* Устанавливаем максимальную ширину кнопки, ограничивая ее по высоте */
        pasteButton1.setMaximumSize(new Dimension(700, pasteButton1.getMaximumSize().height));


        /* Создаем кнопку для вставки текста из буфера обмена в поле вывода текста */
        JButton pasteButton2 = new JButton("Вставить в поле вывода текста из буфера обмена");
        /* Добавляем слушателя событий к кнопке, чтобы реагировать на нажатие */
        pasteButton2.addActionListener(this);
        /* Устанавливаем команду действия для кнопки. Эта команда будет использоваться, чтобы определить, какое действие должно быть выполнено при нажатии кнопки. */
        pasteButton2.setActionCommand("cmd_paste_ta2");
        /* Устанавливаем шрифт для текста на кнопке */
        pasteButton2.setFont(boldFont);
        /* Устанавливаем горизонтальное выравнивание кнопки по центру */
        pasteButton2.setAlignmentX(java.awt.Component.CENTER_ALIGNMENT);
        /* Устанавливаем максимальную ширину кнопки, ограничивая ее по высоте */
        pasteButton2.setMaximumSize(new Dimension(700, pasteButton2.getMaximumSize().height));


        /* Создаем кнопку для вставки текста из буфера обмена в поле ввода ключа */
        JButton pasteButton3 = new JButton("Вставить в поле ввода ключа из буфера обмена");
        /* Добавляем слушателя событий к кнопке, чтобы реагировать на нажатие */
        pasteButton3.addActionListener(this);
        /* Устанавливаем команду действия для кнопки. Эта команда будет использоваться, чтобы определить, какое действие должно быть выполнено при нажатии кнопки. */
        pasteButton3.setActionCommand("cmd_paste_key");
        /* Устанавливаем шрифт для текста на кнопке */
        pasteButton3.setFont(boldFont);


        /* Создаем метку (Label) для ввода ключа */
        javax.swing.JLabel lbKeyword = new javax.swing.JLabel("Ввод ключа");
        /* Устанавливаем цвет текста метки в синий */
        lbKeyword.setForeground(Color.BLUE);
        /* Устанавливаем шрифт для текста метки */
        lbKeyword.setFont(font);
        /* Создаем панель (Panel) для группировки элементов интерфейса, связанных с вводом ключа */
        javax.swing.JPanel pKeyword = new javax.swing.JPanel();
        /* Добавляем метку в панель */
        pKeyword.add(lbKeyword);
        /* Добавляем поле для ввода ключа (tfKeyword) в панель */
        pKeyword.add(tfKeyword);
        /* Добавляем поле для ввода ключа (tfKeyword) еще раз (дублирование?) в панель */
        pKeyword.add(tfKeyword); // Возможно, это лишнее дублирование
        /* Добавляем кнопку keyGeneration в панель (предположительно, кнопка для генерации ключа) */
        pKeyword.add(keyGeneration);
        /* Добавляем styleButton в панель (предположительно, еще одна кнопка для выбора стиля ключа) */
        pKeyword.add(styleButton);


        /* Создаем панель для управления элементами интерфейса */
        javax.swing.JPanel pControls = new javax.swing.JPanel();

        /* Добавляем элементы управления (кнопки) в панель */
        pControls.add(closeButton);
        pControls.add(btnDecifer);
        pControls.add(clearKeyword);
        pControls.add(clearButtonAllFields);
        pControls.add(btnEncrypt);
        pControls.add(btnKey);
        pControls.add(pasteButton3);

        /* Создаем текстовую область (TextArea) ta */
        ta = new javax.swing.JTextArea();
        /* Создаем текстовую область (TextArea) ta2 */
        ta2 = new javax.swing.JTextArea();
        /* Устанавливаем цвет текста в текстовых областях на красный */
        ta.setForeground(Color.RED);
        ta2.setForeground(Color.RED);
        /* Устанавливаем шрифт для текста в текстовых областях */
        ta.setFont(font);
        ta2.setFont(font);


        /* Создаем объект JTabbedPane (вкладки) */
        JTabbedPane pane = new JTabbedPane();
        /* Создаем панель encodeControls для размещения элементов управления */
        JPanel encodeControls = new JPanel();
        /* Устанавливаем менеджер компоновки для панели - BoxLayout с вертикальной ориентацией */
        encodeControls.setLayout(new BoxLayout(encodeControls, BoxLayout.PAGE_AXIS));
        /* Добавляем текстовую область (ta) внутрь панели, обернутую в JScrollPane */
        encodeControls.add(new JScrollPane(ta));
        /* Добавляем кнопку для очистки поля ввода текста (clearButtonText) в панель */
        encodeControls.add(clearButtonText);
        /* Добавляем кнопку для копирования текста в буфер обмена (btnTa) в панель */
        encodeControls.add(btnTa);
        /* Добавляем кнопку для вставки текста из буфера обмена в поле ввода текста (pasteButton1) в панель */
        encodeControls.add(pasteButton1);
        /* Добавляем панель encodeControls на вкладку (закладку) с названием "Вводите текст или шифр" */
        pane.add("Вводите текст или шифр", encodeControls);


        /* Создаем панель encodeControls1 для размещения элементов управления на второй вкладке */
        JPanel encodeControls1 = new JPanel();
        /* Устанавливаем менеджер компоновки для панели - BoxLayout с вертикальной ориентацией */
        encodeControls1.setLayout(new BoxLayout(encodeControls1, BoxLayout.PAGE_AXIS));
        /* Добавляем текстовую область (ta2) внутрь панели, обернутую в JScrollPane */
        encodeControls1.add(new JScrollPane(ta2));
        /* Добавляем кнопку для очистки поля вывода результата (clearButtonResult) в панель */
        encodeControls1.add(clearButtonResult);
        /* Добавляем кнопку для копирования текста из поля вывода в буфер обмена (btnTa2) в панель */
        encodeControls1.add(btnTa2);
        /* Добавляем кнопку для вставки текста из буфера обмена в поле вывода текста (pasteButton2) в панель */
        encodeControls1.add(pasteButton2);
        /* Добавляем панель encodeControls1 на вкладку (закладку) с названием "Результат" */
        pane.add("Результат", encodeControls1);

        /* Устанавливаем шрифт для текста на вкладках в JTabbedPane */
        pane.setFont(boldFont);
        /* Добавляем JTabbedPane (вкладки) на основное окно приложения, используя BorderLayout */
        add(pane, BorderLayout.CENTER);

        /* Добавляем панель pKeyword в северную часть окна (наверху) */
        add(pKeyword, java.awt.BorderLayout.NORTH);
        /* Добавляем панель pControls в южную часть окна (внизу) */
        add(pControls, java.awt.BorderLayout.SOUTH);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        /*SetDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); - это команда, которая устанавливает
          реакцию приложения на закрытие окна приложения (JFrame). В данном случае, JFrame.EXIT_ON_CLOSE
          является одним из вариантов констант, предоставляемых классом JFrame для управления поведением
          окна при его закрытии.
         */

        /* Устанавливаем размер окна приложения (ширина: 1401 пиксель, высота: 701 пиксель) */
        setSize(1401, 701);
        /* Устанавливаем минимально допустимый размер окна (ширина: 1400 пикселей, высота: 700 пикселей) */
        setMinimumSize(new Dimension(1400, 700));
        /* Возможно, выполняем загрузку стилей для приложения (метод loadStyle()) */
        loadStyle();


    }

    //Создание слушателя для кнопок
    @Override
    public void actionPerformed(java.awt.event.ActionEvent ae) {
        // Генерируем ключ
        String array = generateKey();

        // Определяем, какое действие было выполнено на основе команды действия
        switch (ae.getActionCommand()) {
            case "cmd_encrypt" -> encryptCheck(); // Вызываем метод для зашифровки текста
            case "cmd_decifer" -> deciferCheck(); // Вызываем метод для дешифровки текста
            case "cmd_clearKeyword" -> tfKeyword.setText(""); // Очищаем поле ключа
            case "cmd_clearText" -> ta.setText(""); // Очищаем поле ввода текста
            case "cmd_clearResult" -> ta2.setText(""); // Очищаем поле вывода результата
            case "cmd_clearAllFields" -> clearText(); // Очищаем все поля
            case "cmd_keyGeneration" -> tfKeyword.setText(array); // Генерируем и устанавливаем ключ
            case "cmd_close_window" -> System.exit(0); // Закрываем окно приложения
            case "cmd_style" -> changeStyle(); // Изменяем стиль приложения
            case "cmd_tacopy" -> {
                String textToCopy = ta.getText();
                copyTextToClipboard(textToCopy); // Копируем текст из поля ввода в буфер обмена
            }
            case "cmd_ta2copy" -> {
                String textToCopy2 = ta2.getText();
                copyTextToClipboard(textToCopy2); // Копируем текст из поля вывода в буфер обмена
            }
            case "cmd_keycopy" -> {
                String textToCopy3 = tfKeyword.getText();
                copyTextToClipboard(textToCopy3); // Копируем ключ в буфер обмена
            }
            case "cmd_paste_ta" -> {
                String clipboardText1 = pasteTextFromClipboard();
                ta.append(clipboardText1); // Вставляем текст из буфера обмена в поле ввода текста
            }
            case "cmd_paste_ta2" -> {
                String clipboardText2 = pasteTextFromClipboard();
                ta2.append(clipboardText2); // Вставляем текст из буфера обмена в поле вывода текста
            }
            case "cmd_paste_key" -> {
                String clipboardText3 = pasteTextFromClipboard();
                tfKeyword.setText(clipboardText3); // Вставляем текст из буфера обмена в поле ключа
            }
        }
    }


    //Сохраняет текущий стиль приложения в файл
    private void saveStyle() {
        try (PrintWriter writer = new PrintWriter(styleFileName)) {
            // Записываем индекс текущего стиля в файл
            writer.println(currentStyleIndex);
        } catch (IOException e) {
            // В случае ошибки ввода/вывода, ничего не предпринимаем, так как это не фатальная ошибка
        }
    }

    //Загружает предыдущий стиль приложения из файла и устанавливает его
    private void loadStyle() {
        try (BufferedReader reader = new BufferedReader(new FileReader(styleFileName))) {
            String line = reader.readLine();
            if (line != null) {
                // Преобразуем строку в целое число (индекс стиля)
                currentStyleIndex = Integer.parseInt(line);

                // Уменьшаем индекс на 1, так как в массиве индексы начинаются с 0
                currentStyleIndex--;

                // Устанавливаем загруженный стиль
                changeStyle();

                // Получаем информацию о текущем стиле
                LookAndFeelInfo currentStyle = installedLookAndFeels[currentStyleIndex];

                try {
                    // Устанавливаем стиль через UIManager
                    UIManager.setLookAndFeel(currentStyle.getClassName());
                } catch (Exception e) {
                    // В случае ошибки при установке стиля, выводим сообщение с ошибкой
                    JOptionPane.showMessageDialog(null, "Error: Ошибка при установке стиля: " + e.getMessage());
                }
            }
        } catch (IOException | NumberFormatException e) {
            // В случае ошибки ввода/вывода или неверного формата данных в файле, выводим сообщение с ошибкой
            JOptionPane.showMessageDialog(null, "Error: Ошибка при чтении файла: " + e.getMessage());
        }
    }

    //Метод для копирования текста в буфер обмена
    private void copyTextToClipboard(String text) {
        // Получаем системный буфер обмена
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();

        // Создаем объект StringSelection, содержащий текст для копирования
        StringSelection selection = new StringSelection(text);

        // Устанавливаем содержимое буфера обмена на текстовое выделение
        clipboard.setContents(selection, null);
    }


    //Генерирует случайный ключ на основе заданных параметров и возвращает его в виде строки.
    public static String generateKey() {
        int minArrayKey = 2; // Минимальный размер матрицы
        int maxArrayKey = 4; // Максимальный размер матрицы
        Random random = new Random(); // Генератор случайных чисел
        int randomArray = random.nextInt(maxArrayKey - minArrayKey + 1) + minArrayKey; // Генерируем случайный размер матрицы
        boolean check = true; // Флаг для проверки условия детерминанта
        int[][] arrayKey = null; // Матрица ключа, изначально пустая

        // Генерируем случайную матрицу до тех пор, пока не выполнится условие детерминанта
        while (check) {
            arrayKey = generateRandomArray(randomArray); // Генерируем случайную матрицу
            AlgebraicCofactorMatrix matrixDeterminant = new AlgebraicCofactorMatrix(arrayKey);
            int conditionDeterminant = matrixDeterminant.calculation(arrayKey);

            if (conditionDeterminant == 0 || conditionDeterminant == 1) {
                check = true;
            } else {
                check = false;
            }
        }

        int lenArray = randomArray * randomArray; // Вычисляем длину массива (количество элементов в матрице)

        String myKey = creatingKey(randomArray, arrayKey); // Создаем ключ на основе матрицы
        return myKey;
    }

    //Генерация многомерного массива
    public static int[][] generateRandomArray(int randomArray) {
        int minImumNumber = 0; // Минимальное значение элемента матрицы
        int maxImumNumber = 148; // Максимальное значение элемента матрицы
        int[][] arrayKey = new int[randomArray][randomArray]; // Создаем массив заданного размера
        Random random = new Random();

        for (int i = 0; i < randomArray; i++) {
            for (int j = 0; j < randomArray; j++) {
                int array = random.nextInt(maxImumNumber - minImumNumber + 1) + minImumNumber;

                if (array == 71) {
                    arrayKey[i][j] = 15; // Если случайное число равно 71, устанавливаем значение 15
                } else {
                    arrayKey[i][j] = array; // В противном случае устанавливаем случайное значение
                }
            }
        }

        return arrayKey; // Возвращаем сгенерированную матрицу
    }

    //Генерация ключа
    public static String creatingKey(int a, int[][] arrayKey) {
        // Создаем одномерный массив для хранения элементов из двумерного массива
        int[][] arrayKey1 = new int[a * a][1];

        for (int i = 0; i < a; i++) {
            for (int j = 0; j < a; j++) {
                // Заполняем одномерный массив значениями из двумерного массива
                arrayKey1[i * a + j][0] = arrayKey[i][j];
            }
        }

        // Создаем объект для преобразования числового кода в символы
        ConvertingNumericCodeIntoCharacters converter = new ConvertingNumericCodeIntoCharacters(arrayKey1, a * a);

        // Получаем символьный массив на основе числового кода
        char[] charArray = converter.сonvertingStringCodeFromNumbersToCharacters();

        // Преобразуем символьный массив в строку и возвращаем ключ
        String str = new String(charArray);
        return str;
    }

    //Метод для вставки текста из буфера обмена
    private String pasteTextFromClipboard() {
        // Получаем доступ к системному буферу обмена
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        try {
            // Получаем данные из буфера обмена
            Transferable data = clipboard.getContents(null);
            // Проверяем, поддерживается ли формат данных DataFlavor для текста
            if (data != null && data.isDataFlavorSupported(DataFlavor.stringFlavor)) {
                // Преобразовываем данные в текстовый формат и возвращаем
                return (String) data.getTransferData(DataFlavor.stringFlavor);
            }
        } catch (Exception ex) {
            // В случае возникновения ошибки, выводим стек трассировки в консоль
            ex.printStackTrace();
        }
        // Возвращаем пустую строку, если не удалось получить текст из буфера обмена
        return "";
    }

    //Очищает текстовые поля: ключа (`tfKeyword`), ввода текста (`ta`) и вывода результата (`ta2`)
    public void clearText() {
        // Очищаем поле ключа
        tfKeyword.setText("");
        // Очищаем поле ввода текста
        ta.setText("");
        // Очищаем поле вывода результата
        ta2.setText("");
    }

    //Меняет стиль пользовательского интерфейса приложения на следующий доступный стиль
    //При этом происходит сохранение выбранного стиля и обновление интерфейса
    private void changeStyle() {
        // Увеличиваем индекс текущего стиля на 1 и берем остаток от деления на количество доступных стилей,
        // чтобы получить следующий стиль или вернуться к началу, если достигнут последний стиль.
        currentStyleIndex = (currentStyleIndex + 1) % installedLookAndFeels.length;
        // Получаем информацию о текущем стиле
        UIManager.LookAndFeelInfo currentStyle = installedLookAndFeels[currentStyleIndex];
        try {
            // Устанавливаем выбранный стиль через UIManager
            UIManager.setLookAndFeel(currentStyle.getClassName());
            // Обновляем все компоненты интерфейса с учетом нового стиля
            SwingUtilities.updateComponentTreeUI(this);
            // Сохраняем текущий стиль
            saveStyle();
        } catch (Exception ex) {
            // В случае ошибки выводим стек трассировки в консоль
            ex.printStackTrace();
        }
    }

    //Метод проверяет корректность ввода ключа
    public boolean checkKeyword(String keyword, String decryptionCipher) {
        // Преобразуем ключ и расшифровывающий шифр в массивы символов
        char[] charArrayDecryptionCipher = decryptionCipher.toCharArray();
        char[] charArrayKeyword = keyword.toCharArray();
        // Вычисляем размерность массива ключа
        int arrayNumKey = (int) Math.sqrt(charArrayKeyword.length);
        // Создаем объект для преобразования символов в числа
        ConvertingCharactersToNumbers convertingCharactersToNumbers = new ConvertingCharactersToNumbers(charArrayKeyword, arrayNumKey, charArrayDecryptionCipher);
        // Получаем матрицу чисел из символов ключа
        int[][] integers = convertingCharactersToNumbers.getArrayCharConvertingCharactersToNumbersForTheDeterminant();
        // Получаем размер алфавита
        int numberInStorage = convertingCharactersToNumbers.getArrayAlphabetSize();
        // Создаем объект для вычисления алгебраического дополнения матрицы
        AlgebraicCofactorMatrix matrixDeterminant = new AlgebraicCofactorMatrix(integers);
        // Вычисляем определитель матрицы
        int conditionDeterminant = matrixDeterminant.calculation(integers);
        // Проверяем определенные условия
        int checkingConverted = checkingConvertedFraction(conditionDeterminant, numberInStorage);
        double squareRoot = Math.sqrt(keyword.length());
        int squareRootAsInt = (int) squareRoot;
        // Возвращаем true, если все условия выполнены
        return (squareRoot == squareRootAsInt && keyword.length() != 1 && conditionDeterminant != 0 && conditionDeterminant != 1 && checkingConverted != 1);
    }

    //Проверка соответствия символов в ключе и тексте с алфавитом
    public boolean checkTextAlphabet(String keyword, String decryptionCipher) {
        // Преобразуем расшифровывающий шифр и текст в массивы символов
        char[] charArrayDecryptionCipher = decryptionCipher.toCharArray();
        char[] charArrayKeyword = keyword.toCharArray();
        // Вычисляем размерность массива ключа
        int arrayNumKey = (int) Math.sqrt(charArrayKeyword.length);
        // Создаем объект для преобразования символов в числа
        ConvertingCharactersToNumbers convertingCharactersToNumbers = new ConvertingCharactersToNumbers(charArrayKeyword, arrayNumKey, charArrayDecryptionCipher);
        // Проверяем текст на соответствие алфавиту, заданному расшифровывающим шифром
        int checkingLine = convertingCharactersToNumbers.сheck(charArrayDecryptionCipher);
        // Возвращаем true, если текст не соответствует алфавиту
        return checkingLine != charArrayDecryptionCipher.length;
    }

    /*Проверяет, содержит ли ключ (keyword) только существующие символы,
    которые могут быть использованы в дешифровке текста (decryptionCipher)*/
    //@param keyword. Ключ, который необходимо проверить
    //@param decryptionCipher. Текст, который будет дешифрован с использованием данного ключа
    //@return true, если все символы в ключе существуют в тексте дешифровки, иначе false
    public boolean checkKeyAlphabet(String keyword, String decryptionCipher) {
        // Преобразование текста дешифровки в массив символов
        char[] charArrayDecryptionCipher = decryptionCipher.toCharArray();
        // Преобразование ключа в массив символов
        char[] charArrayKeyword = keyword.toCharArray();
        // Расчет числа элементов в квадратной матрице для дальнейшей проверки
        int arrayNumKey = (int) Math.sqrt(charArrayKeyword.length);
        // Создание экземпляра класса для преобразования символов в числа и выполнения проверок
        ConvertingCharactersToNumbers convertingCharactersToNumbers = new ConvertingCharactersToNumbers(charArrayKeyword, arrayNumKey, charArrayDecryptionCipher);
        // Выполнение проверки ключа на существующие символы
        int checkingKey = convertingCharactersToNumbers.сheck(charArrayKeyword);
        // Если количество существующих символов в ключе не совпадает с длиной ключа, возвращаем false
        return checkingKey != charArrayKeyword.length;
    }

    //Метод для проверки корректности ввода ключа и текста для зашифровки
    public void encryptCheck() {
        // Получаем ключ и текст из текстовых полей
        String key = tfKeyword.getText(); // Получаем ключ из текстового поля tfKeyword
        String text = ta.getText(); // Получаем текст из текстового поля ta

        // Проверка корректности ключа
        if (!checkKeyword(key, text)) {
            // Если ключ не соответствует тексту, выводим сообщение об ошибке
            JOptionPane.showMessageDialog(null, "Error: Неправильный ввод ключа, введите другой ключ!!!");
        } else {
            // Проверка наличия несуществующих символов в тексте
            if (checkTextAlphabet(key, text)) {
                // Если в тексте есть несуществующие символы, выводим сообщение об ошибке
                JOptionPane.showMessageDialog(null, "Error: Несуществующие символы в строке!!!");
            } else {
                // Проверка наличия несуществующих символов в ключе
                if (checkKeyAlphabet(key, text)) {
                    // Если в ключе есть несуществующие символы, выводим сообщение об ошибке
                    JOptionPane.showMessageDialog(null, "Error: Несуществующие символы в ключе!!!");
                } else {
                    // Если ключ и текст прошли все проверки, шифруем текст
                    String encodedText = encrypt(text, key);
                    // Устанавливаем зашифрованный текст в текстовое поле ta2
                    ta2.setText(encodedText);
                }
            }
        }
    }

    //Метод для проверки корректности ввода ключа и текста для расшифровки
    public void deciferCheck(){
        // Получаем ключ и текст из текстовых полей
        String key = tfKeyword.getText(); // Получаем ключ из текстового поля tfKeyword
        String text = ta.getText(); // Получаем текст из текстового поля ta

        // Проверяем, совпадает ли ключ с текстом (предполагаем, что checkKeyword проверяет корректность ключа)
        if (!checkKeyword(key, text)) {
            // Если ключ не совпадает с текстом, выводим сообщение об ошибке
            JOptionPane.showMessageDialog(null, "Error: Не правильный ввод ключа, введите другой ключ!!!");
        } else {
            // Проверяем, есть ли несуществующие символы в тексте (предполагаем, что checkTextAlphabet выполняет эту проверку)
            if (checkTextAlphabet(key, text)) {
                // Если в тексте есть несуществующие символы, выводим сообщение об ошибке
                JOptionPane.showMessageDialog(null, "Error: Не существующие символы в строке!!!");
            } else {
                // Проверяем, есть ли несуществующие символы в ключе (предполагаем, что checkKeyAlphabet выполняет эту проверку)
                if (checkKeyAlphabet(key, text)) {
                    // Если в ключе есть несуществующие символы, выводим сообщение об ошибке
                    JOptionPane.showMessageDialog(null, "Error: Не существующие символы в ключе!!!");
                } else {
                    // Если ключ и текст прошли все проверки, дешифруем текст с использованием метода decrypt
                    String decodedText = decrypt(text, key);
                    // Выводим дешифрованный текст в текстовое поле ta2
                    ta2.setText(decodedText);
                }
            }
        }
    }

    //Метод для умножения матрицы на число
    public static int[][] multiplicationMatrixByNumber(int[][] array1, int number, int number2) {
        int rows1 = array1.length;
        // Количество строк в Array1
        int[][] result = new int[rows1][rows1];

        // Проходимся по каждому элементу массива
        for (int i = 0; i < rows1; i++) {
            for (int j = 0; j < rows1; j++) {
                // Вычисляем произведение элемента массива на number2 и берем остаток по модулю числа
                result[i][j] = Math.floorMod(array1[i][j] * number2,number);
            }
        }

        return result; // Возвращаем результирующий массив
    }

    // Метод для умножения матрицы на массив и возврата результата в виде массива символов
    public static char[] multiplyingMatrixByMatrix(int[][] array1, int[] array2, int number, int lens) {
        //int lens = Array2.length;
        int[][] arrayMatrix = new int[lens][lens];
        int[][] arrayMatrix1 = new int[lens][lens];
        char []conversionToText = new char[lens];
        char[] arrayChars = new char[lens];
        // Заполняем первый столбец матрицы arrayMatrix значениями из Array2
        for (int i = 0; i < lens; i++) {
            arrayMatrix[i][0] = array2[i];
        }
        //Вызов метода для умножения матриц
        arrayMatrix1 = multiplication(array1, arrayMatrix, number);
        // Преобразуем числовой код в символы и сохраняем результат в conversionToText
        ConvertingNumericCodeIntoCharacters convertingNumericCodeIntoCharacters = new ConvertingNumericCodeIntoCharacters(arrayMatrix1, lens);
        conversionToText = convertingNumericCodeIntoCharacters.сonvertingStringCodeFromNumbersToCharacters();
        return conversionToText;
    }

    // Метод для умножения матриц и взятия остатка
    public static int[][] multiplication(int[][] array1, int[][] array2, int number) {
        int rows1 = array1.length;
        // Количество строк в Array1
        int[][] result = new int[rows1][rows1];
        // Результат умножения матриц
        for (int i = 0; i < rows1; i++) {
            for (int j = 0; j < rows1; j++) {
                int sum = 0;
                for (int k = 0; k < rows1; k++) {
                    // Умножение элементов матрицы Array1 на элементы матрицы Array2
                    sum += array1[i][k] * array2[k][j];

                }
                // Взятие остатка от суммы и сохранение результата в матрице result
                result[i][j] = sum%number;

            }
        }

        return result; // Возвращение результирующей матрицы
    }

    //Метод для зашифровки текста
    public static String encrypt(String decryptionCipher, String keyword) {
        // Вычисляем длину строки decryptionCipher
        int lengthDecryptionCipher = decryptionCipher.length();
        // Создаем массив символов для строки decryptionCipher
        char[] charArrayDecryptionCipher = new char[lengthDecryptionCipher];
        // Заполняем массив charArrayDecryptionCipher символами из decryptionCipher
        for (int i = 0; i < lengthDecryptionCipher; i++) {
            charArrayDecryptionCipher[i] = decryptionCipher.charAt(i);
        }
        int lengthKeyword = keyword.length();
        // Вычисляем длину ключевого слова keyword
        int squareRoot1 = (int) Math.sqrt(lengthKeyword);
        // Вычисляем квадратный корень от длины ключевого слова
        char[] charArrayKeyword = new char[lengthKeyword];
        // Создаем массив символов для ключевого слова keyword
        // Заполняем массив charArrayKeyword символами из keyword
        for (int i = 0; i < lengthKeyword; i++) {
            charArrayKeyword[i] = keyword.charAt(i);
        }
        // Вычисляем размер массива для числовых значений символов ключевого слова
        int arrayNumKey =  (int)Math.sqrt(charArrayKeyword.length);
        // Создаем объект ConvertingCharactersToNumbers для преобразования символов
        // ключевого слова и строки в числа
        ConvertingCharactersToNumbers convertingCharactersToNumbers =new ConvertingCharactersToNumbers(charArrayKeyword, arrayNumKey, charArrayDecryptionCipher);
        // Создаем объект CheckingTheAlphabet для проверки символов в строке
        int[][] intergers = convertingCharactersToNumbers.getArrayCharConvertingCharactersToNumbersForTheDeterminant();
        int[] intergersAlphabetArray = convertingCharactersToNumbers.alphabetArray();
        AlgebraicCofactorMatrix matrixDeterminant = new AlgebraicCofactorMatrix(intergers);
        // Проверяем правильность ввода символов и детерминант матрицы
        int numberInStorage = convertingCharactersToNumbers.getArrayAlphabetSize();
        int conditionDeterminant = matrixDeterminant.calculation(intergers);
        // Создаем массив arrayOfText для хранения чисел, которые будут преобразованы в текст
        int[] arrayOfText = new int[arrayNumKey];

        // Вычисляем остаток от деления длины массива intergersAlphabetArray на ArrayNumKey
        int remains = intergersAlphabetArray.length % arrayNumKey;

        // Вычисляем размерность (dimension) для последующего расшифрования
        int dimension;

        // Если остаток от деления не равен нулю, увеличиваем размерность до ближайшего кратного ArrayNumKey числа
        if (remains != 0) {
            dimension = intergersAlphabetArray.length + (arrayNumKey - remains);
        } else {
            // Иначе, оставляем размерность без изменений
            dimension = intergersAlphabetArray.length;
        }
        int number = lengthDecryptionCipher%squareRoot1;
        int roundedUpInteger = 0;
        int whole = lengthDecryptionCipher/squareRoot1;
        if(number == 0){
            roundedUpInteger = whole;
        }else{
            roundedUpInteger = whole+1;
        }

        String []resultArray = new String [roundedUpInteger];
        // Начинаем итерацию по массиву intergersAlphabetArray с шагом ArrayNumKey
        for (int i = 0, m = 0; i < dimension; i += arrayNumKey, m++) {
            // Вычисляем количество элементов, которые нужно скопировать на текущей итерации
            int elementsToCopy = Math.min(arrayNumKey, dimension - i);

            // Заполняем массив arrayOfText числами из intergersAlphabetArray
            for (int j = 0; j < elementsToCopy; j++) {
                if (i + j >= intergersAlphabetArray.length) {
                    // Если вышли за пределы массива, используем символ с ASCII-кодом 35 (например, '#')
                    arrayOfText[j] = 35;
                } else {
                    // Иначе, копируем значение из intergersAlphabetArray в arrayOfText
                    arrayOfText[j] = intergersAlphabetArray[i + j];
                }
            }

            // Выполняем умножение матриц и получаем массив символов
            char[] arrayMatrix1 = multiplyingMatrixByMatrix(intergers, arrayOfText, numberInStorage, arrayNumKey);

            // Инициализация объекта StringBuilder для построения строки
            StringBuilder stringBuilder = new StringBuilder();

            // Объединение элементов массива в строку с разделителем
            for (char c : arrayMatrix1) {
                stringBuilder.append(c);
            }

            // Получение результирующей строки
            String resultString = stringBuilder.toString();
            resultArray[m] = resultString;

        }
        String resultStrings = resultArray.toString();
        StringBuilder str = new StringBuilder();

        for (String c : resultArray) {
            str.append(c);
        }
        String resultString = str.toString();
        // Получение результирующей строки
        return resultString;

    }

    //Метод для расшифровки текста
    public static String decrypt(String decryptionCipher, String keyword){
        // Вычисляем длину строки decryptionCipher
        int lengthDecryptionCipher = decryptionCipher.length();
        // Создаем массив символов для строки decryptionCipher
        char[] charArrayDecryptionCipher = new char[lengthDecryptionCipher];
        // Заполняем массив charArrayDecryptionCipher символами из decryptionCipher
        for (int i = 0; i < lengthDecryptionCipher; i++) {
            charArrayDecryptionCipher[i] = decryptionCipher.charAt(i);
        }

        int lengthKeyword = keyword.length();
        // Вычисляем длину ключевого слова keyword
        int squareRoot1 = (int) Math.sqrt(lengthKeyword);
        // Вычисляем квадратный корень от длины ключевого слова
        char[] charArrayKeyword = new char[lengthKeyword];
        // Создаем массив символов для ключевого слова keyword
        // Заполняем массив charArrayKeyword символами из keyword
        for (int i = 0; i < lengthKeyword; i++) {
            charArrayKeyword[i] = keyword.charAt(i);
        }
        // Вычисляем размер массива для числовых значений символов ключевого слова
        int arrayNumKey =  (int)Math.sqrt(charArrayKeyword.length);
        // Создаем объект ConvertingCharactersToNumbers для преобразования символов
        // ключевого слова и строки в числа
        ConvertingCharactersToNumbers convertingCharactersToNumbers =new ConvertingCharactersToNumbers(charArrayKeyword, arrayNumKey, charArrayDecryptionCipher);
        // Создаем объект CheckingTheAlphabet для проверки символов в строке
        int[][] intergers = convertingCharactersToNumbers.getArrayCharConvertingCharactersToNumbersForTheDeterminant();
        int[] intergersAlphabetArray = convertingCharactersToNumbers.alphabetArray();
        AlgebraicCofactorMatrix matrixDeterminant = new AlgebraicCofactorMatrix(intergers);
        // Проверяем правильность ввода символов и детерминант матрицы
        int numberInStorage = convertingCharactersToNumbers.getArrayAlphabetSize();
        int conditionDeterminant = matrixDeterminant.calculation(intergers);
        // Создаем объект ChainShot для работы с шифрованными данными, передавая детерминант и число в хранилище
        ChainShot chainShot = new ChainShot(conditionDeterminant, numberInStorage);

        // Получаем значение, используемое в модульной арифметике, из объекта ChainShot
        int theNumberModulo = chainShot.getMyField();

        // Создаем объект AlgebraicCofactorMatrix для работы с алгебраическими дополнениями матрицы, передавая матрицу intergers
        AlgebraicCofactorMatrix algebraicCofactorMatrix = new AlgebraicCofactorMatrix(intergers);

        // Получаем матрицу алгебраических дополнений
        int[][] arraAddition = algebraicCofactorMatrix.getMatrix1(intergers);

        // Вычисляем количество строк и столбцов в матрице arraAddition
        int rows = arraAddition.length;
        int cols = arraAddition[0].length;

        // Получаем результат умножения матрицы на число
        int[][] resultMatrixByNumber = multiplicationMatrixByNumber(arraAddition, numberInStorage, theNumberModulo);

        // Создаем массив arrayOfText1 для хранения чисел, которые будут преобразованы в текст
        int[] arrayOfText1 = new int[arrayNumKey];

        // Вычисляем остаток от деления длины массива intergersAlphabetArray на ArrayNumKey
        int remains1 = intergersAlphabetArray.length % arrayNumKey;

        // Вычисляем размерность (dimension1) для последующего расшифрования
        int dimension1;

        // Если остаток от деления не равен нулю, увеличиваем размерность до ближайшего кратного ArrayNumKey числа
        if (remains1 != 0) {
            dimension1 = intergersAlphabetArray.length + (arrayNumKey - remains1);
        } else {
            // Иначе, оставляем размерность без изменений
            dimension1 = intergersAlphabetArray.length;
        }

        // Вычисляем остаток от деления длины массива intergersAlphabetArray на ArrayNumKey
        int remains = intergersAlphabetArray.length % arrayNumKey;

        // Вычисляем размерность (dimension) для последующего расшифрования
        int dimension;

        // Если остаток от деления не равен нулю, увеличиваем размерность до ближайшего кратного ArrayNumKey числа
        if (remains != 0) {
            dimension = intergersAlphabetArray.length + (arrayNumKey - remains);
        } else {
            // Иначе, оставляем размерность без изменений
            dimension = intergersAlphabetArray.length;
        }
        int number = lengthDecryptionCipher%squareRoot1;
        int roundedUpInteger = 0;
        int whole = lengthDecryptionCipher/squareRoot1;
        if(number == 0){
            roundedUpInteger = whole;
        }else{
            roundedUpInteger = whole+1;
        }
        String []resultArray = new String [roundedUpInteger];
        for (int m = 0, g = 0; m < dimension1; m += arrayNumKey, g++){
            // Вычисляем количество элементов, которые нужно скопировать на текущей итерации
            int elementsToCopy1 = Math.min(arrayNumKey, dimension1 - m);

            // Начинаем цикл для копирования значений из intergersAlphabetArray в arrayOfText1
            for (int l = 0; l < elementsToCopy1; l++) {
                // Проверяем, вышли ли за пределы массива intergersAlphabetArray
                if (m + l >= intergersAlphabetArray.length) {
                    // Если вышли за пределы массива, устанавливаем значение 35 (ASCII-код символа '#')
                    arrayOfText1[l] = 35;
                } else {
                    // Иначе, копируем значение из intergersAlphabetArray в arrayOfText1
                    arrayOfText1[l] = intergersAlphabetArray[m + l];
                }
            }
            char[] arrayMatrix1 = multiplyingMatrixByMatrix(resultMatrixByNumber, arrayOfText1, numberInStorage, arrayNumKey);
            // Инициализация объекта StringBuilder для построения строки
            // Инициализация объекта StringBuilder для построения строки
            StringBuilder stringBuilder = new StringBuilder();

            // Объединение элементов массива в строку с разделителем
            for (char c : arrayMatrix1) {
                stringBuilder.append(c);
            }

            // Получение результирующей строки
            String resultString = stringBuilder.toString();
            resultArray[g] = resultString;

        }

        // Создаем объект StringBuilder для объединения элементов массива
        StringBuilder str = new StringBuilder();

        // Объединение элементов массива в строку с разделителем (пустой строкой)
        for (String c : resultArray) {
            str.append(c);
        }

        // Получение результирующей строки
        String resultString = str.toString();
        return resultString;

    }

    //Проверка правильности ввода ключа
    public static int checkingConvertedFraction(int numerator, int denominator){

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

        int verification = 0;
        if(denominator1 == 1){
            verification = 1;
        }
        return verification;
    }

    /*Класс для запуска программы*/
    public static void main(String[] args) {
        // Создаем экземпляр класса StealthchatNew
        StealthchatNew chatWindow = new StealthchatNew();
        // Устанавливаем видимость окна
        chatWindow.setVisible(true);
    }

}
