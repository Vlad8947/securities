# Securities Web-Service

###Функционал
Сервис для хранения данных о ценных бумагах (securities) и 
истории их торгов на бирже (history). Хранение производится в базе данных. 

Выполнены следующие пункты задания:
1. В приложении допускается существование
  ценной бумаги без истории, истории без описания бумаги - нет.
2. Присутствует импорт объектов из приложенных файлов.
3. Имеется API для CRUD операций по объектам.
4. При ручном сохранении ценной бумаги проводится валидация передаваемых
данных в поле name - только кириллица, цифры и пробел.
5. Отдельным методом реализован вывод таблицы с данными из тегов 
(secid, regnumber, name, emitent_title, tradedate, numtrades, open, close).
Предусмотрена возможность задать параметрами столбцы для сортировки и
фильтрацию по полям emitent_title и tradedate.
6. Реализовано хранение и работа с данными в БД (PostreSQL).
7. Реализована страничка UI с выводом таблицы из пункта 5. Имеются поля 
для определения сортировки и фильтрации.

##Архитектура
Данный сервис использует: 
* Java 11
* Spring Boot
* PostgreSQL.<br>

Из дополнительных средств: 
* Lombok для генерации кода
* Jackson-Dataformat-Xml
* Spring Rest Data, чтобы вручную не писать логику CRUD-операций
<br>

##Запуск
Скачайте предоставленный файл "securities-1.0.jar". Для работы программы
необходима Java 11. <br>
Также должна быть установленна БД PostgreSQL. В ней должна быть база
"securities", юзер "postgres" с паролем "0000".<br>
Откройте консоль, перейдите в папку со скаченным файлом "securities-1.0.jar",
введите команду "java -jar securities-1.0.jar". После этого начнёт запуск 
программа. Дождитесь последней строки с cодержанием "Started Main", 
после чего можете начать использование.
<br>

###API
Далее будут расписаны способы взаимодействия с данными
через HTTP запросы. В связи с этим, чтобы не повторять корневой
url самого сервиса множество раз, он не будет расписан в 
примерах, но его употребление обязательно. Если сервис будет
тестироваться на том же устройстве, на котором будет запускаться,
то корневой url будет "http://localhost:8080". Иначе, обратитесь
за дополнительной информацией к системному администратору.

##Securities

**/api/securities/scan**<br>
**Method:** GET<br>
**Description:** При использовании запроса сервис ищет папку 
"securities" с XML-файлами в той же директории, в которой был запущен, ,
названия которых начинаются с "securities_". Сервис сканирует
данные файлы и сохраняет всю информацию о новых ценных бумагах
в базу данных.

**/api/securities/add-from-xml**<br>
**Method:** POST <br>
**Request Body:** test/xml (xml-файл), application/xml (тело запроса
в формате xml) <br>
**Description:** Добавляет данные о ценных бумагах из передаваемого файла
или из тела запроса. Добавление происходит по той же бизнес-логике, 
что и при использовании сканирования (/api/securities/scan).

**/api/securities**<br>
**Method:** GET <br>
**Description:** Получение списка всех ценных бумаг в БД.

**/api/securities**<br>
**Method:** POST <br>
**Request Body:** application/json. 
Тело должно содержать параметры "secId", "shortName" и "name", остальные
не обязательно. <br>
**Description:** Добавляет новую бумагу или изменяет существующую, 
если указать параметр "id". Параметр "name" при
данном виде добавления проходит валидацию на содержание 
только кирилических символов, чисел пробелов.

**/api/securities/id** <br>
где "id" - id бумаги в БД. <br>
**Method:** GET <br>
**Description:** Получение данных бумаги по id.
<br>
<br>

##History
У историй основная API аналогична ценным бумагам, только URL будет незначительно
отличаться. <br>

**/api/history/scan** - сканирует папку "history" на наличие xml файлов
и добавляет из них данные по существующим в БД бумагам.<br>
**/api/history/add-from-xml** - принимает структуру данных для 
добавления в БД.<br>
**/api/history** - GET-запрос даёт список всей истории. POST - 
принимает или изменяет записи.<br>
**/api/history/id** - выдаёт историю по id. <br>

**/api/history/short-info**
**Method:** GET <br>
**Parameters:** secId, emitentTitle, beginDate, endDate.
Параметры дат принимают начальное и конечное значение дат для фильтрации.
Формат дат следующий: yyyy-MM-dd.
**Description:** Получение таблицы данных по тэгам 
(secId, regNumber, name, emitentTitle, tradeDate, numTrades, open, close).

##UI
**/index.html**<br>
Даёт html страницу с таблицей из "/api/history/short-info".
Также имеется форма для выбора фильтраций и сортировок.

