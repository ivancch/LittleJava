# LittleJava
Own simple programming language written in java.

Данный проект является интерпретатором языка “java”. Иными словами - это свой язык программирования написанный на “java”, некое повышение уровня абстракции. 
Возможности: 
Объявление числовых и строковых переменных 
Запись, чтение и подсчёт в шестнадцатеричной системе
Приоритет операций (аналогично с языком “java”)
Операции сравнения в т.ч. строк (на длину и соответствие)
Как строчные, так и блочные комментарии
Ветвление
Два вида цикла
Прерывание цикла (break)
Переход к следующему шагу цикла (continue)
Блоки операторов, операций
Конкатенация строк, переменных
Вывод в отладочную консоль

Реализовано по классической схеме с изменениями, заимствованной из книги Герберта Шилда “Полный справочник по Си” из последней главы “Little C”, а именно пошагово:
Создание сущности “токена” для возможности токенизации текста программы.
Лексический анализ, и непосредственно токенизация прочитанного кода.
Парсинг полученного списка токенов методом рекурсивного спуска, при помощи абстрактного синтаксического дерева, соблюдая условия выполнения циклов, ветвлений и приоритета операций при подсчете выражений.
Выполнение.


Синтаксис языка: 
* Автоматическое объявление двух типов переменных:
    - числовые
    - строковые
* Числа записываются сразу с плавающей точкой в 8 байт (double).
* Интерпретатор пропускает пробельные символы.
* “ “ - объявление строкового выражения
* \+ \- конкатенация строк
* //  - объявление строчного комментария
* /* */  - объявление блочного комментария 
* { } - обозначение блока операций
* #число - запись в шестнадцатеричной системе
* print - вывод в отладочную консоль
* if (условие)- обозначение ветвления
* else - необязательная часть ветвления
* while (условие) - обозначение цикла
* for стартовое значение, условие, итерация - цикл со счётчиком 
* break - прерывание цикла
* continue - переход к следующему шагу цикла

Возможные операции в выражениях, с сохранением приоритетности выполнения, как в классических ЯП:  
    +, -, *, /, (, ), >, <, ==, <=, >=, !=, &&, ||, =   
   
Все реализованные функции описанные выше протестированы и работают должным образом.

Возможные расширения языка, которые на данный момент не реализованы:
* Создание массивов, как одномерных, так и многомерных
* Создание функций, как системных, так и пользовательских
* Возможность подключения библиотек
* Возможность создания своих типов данных
* Введение ООП, с инкапсуляцией, наследованием и полиморфизмом
* Добавление оповещения кодом и текстом ошибки при неудачном парсинге


**Данный проект реализован исключительно в обучающих целях, и на право истинности не претендует.**


Далее реализована небольшая тестовая программа, показывающая как, работают некоторые из функций.

Код тестовой программы:

    word = 2 + 2 * 2 * 2 * 2 + 2  
    word2 = PI + word  
    str = "abc " + 55 + "\n"  
      
    print str  
    print "word = " + word + "\n"  
    print "word2 = " + word2 + "\n"  
    print "1" > "abc"  
      
    if ((2 > 4 || 0 < 12) && word == 20) print " true\n"  
    if (1 <= 2) print "1 = 1"  
    else print "1 != 1"  
    print "\n"  
      
    if (80 < 60 || 70 > 60) {  
        print "true1\n"  
        if (1 == 1) print "true2\n"  
        i = 0  
        print "while\n"  
        while (i < 5) {  
            print "i = " + i + "\n"  
            i = i + 1  
        }  
        print "for\n"  
        for i = 0, i < 5, i = i + 1 {  
            print "i = " + i + "\n"  
        }  
    }  
    else print "false"  
  
  
  
Результат выполнения программы:

    abc 55.0
    word = 20.0
    word2 = 23.141592653589793
    0.0 true
    1 = 1
    true1
    true2
    test while
    i = 0.0
    i = 1.0
    i = 2.0
    i = 3.0
    i = 4.0
    test for
    i = 0.0
    i = 1.0
    i = 2.0
    i = 3.0
    i = 4.0



Токены входящие на парсинг: 

    WORD word
    EQ 
    NUMBER 2
    PLUS 
    NUMBER 2
    STAR 
    NUMBER 2
    STAR 
    NUMBER 2
    STAR 
    NUMBER 2
    PLUS 
    NUMBER 2
    WORD word2
    EQ 
    WORD PI
    PLUS 
    WORD word
    WORD str
    EQ 
    TEXT abc 
    PLUS 
    NUMBER 55
    PLUS 
    TEXT 
    PRINT 
    WORD str
    PRINT 
    TEXT word = 
    PLUS 
    WORD word
    PLUS 
    TEXT 
    PRINT 
    TEXT word2 = 
    PLUS 
    WORD word2
    PLUS 
    TEXT 
    PRINT 
    TEXT 1
    GT 
    TEXT abc
    IF 
    LPAREN 
    LPAREN 
    NUMBER 2
    GT 
    NUMBER 4
    BARBAR 
    NUMBER 0
    LT 
    NUMBER 12
    RPAREN 
    AMPAMP 
    WORD word
    EQEQ 
    NUMBER 20
    RPAREN 
    PRINT 
    TEXT  true
    IF 
    LPAREN 
    NUMBER 1
    LTEQ 
    NUMBER 2
    RPAREN 
    PRINT 
    TEXT 1 = 1
    ELSE 
    PRINT 
    TEXT 1 != 1
    PRINT 
    TEXT 
    IF 
    LPAREN 
    NUMBER 80
    LT 
    NUMBER 60
    BARBAR 
    NUMBER 70
    GT 
    NUMBER 60
    RPAREN 
    LBRACE 
    PRINT 
    TEXT true1
    IF 
    LPAREN 
    NUMBER 1
    EQEQ 
    NUMBER 1
    RPAREN 
    PRINT 
    TEXT true2
    WORD i
    EQ 
    NUMBER 0
    PRINT 
    TEXT test while
    WHILE 
    LPAREN 
    WORD i
    LT 
    NUMBER 5
    RPAREN 
    LBRACE 
    PRINT 
    TEXT i = 
    PLUS 
    WORD i
    PLUS 
    TEXT 
    WORD i
    EQ 
    WORD i
    PLUS 
    NUMBER 1
    RBRACE 
    PRINT 
    TEXT test for
    FOR 
    WORD i
    EQ 
    NUMBER 0
    COMMA 
    WORD i
    LT 
    NUMBER 5
    COMMA 
    WORD i
    EQ 
    WORD i
    PLUS 
    NUMBER 1
    LBRACE 
    PRINT 
    TEXT i = 
    PLUS 
    WORD i
    PLUS 
    TEXT 
    RBRACE 
    RBRACE 
    ELSE 
    PRINT 
    TEXT false



Результат парсинга входящих токенов:

    word = [[2.0 + [[[2.0 * 2.0] * 2.0] * 2.0]] + 2.0]
    word2 = [PI + word]
    str = [[abc  + 55.0] + “\n”]
    print str
    print [[word =  + word] + “\n”]
    print [[word2 =  + word2] + “\n”]
    print [1 > abc]
    if [[[2.0 > 4.0] || [0.0 < 12.0]] && [word == 20.0]] print  true + “\n”
    if [1.0 <= 2.0] print 1 = 1
    else print 1 != 1
    print “\n”
    if [[80.0 < 60.0] || [70.0 > 60.0]] print true1 + “\n”
    if [1.0 == 1.0] print true2 + “\n”
    i = 0.0
    print test while + “\n”
    while [i < 5.0] print [[i =  + i] + “\n”]
    i = [i + 1.0]
    print test for + “\n”
    for i = 0.0, [i < 5.0], i = [i + 1.0] print [[i =  + i] + “\n”]
    else print false
    
