CREATE TABLE IF NOT EXISTS users (
  id       INT         NOT NULL PRIMARY KEY AUTO_INCREMENT,
  name     VARCHAR(10) NOT NULL UNIQUE,
  password char        NOT NULL
);

CREATE TABLE IF NOT EXISTS score (
  id             INT       NOT NULL PRIMARY KEY AUTO_INCREMENT,
  userid         INT       NOT NULL,
  score          INT       NOT NULL,
  genre          char      NOT NULL,
  start_datetime TIMESTAMP NOT NULL,
  FOREIGN KEY (userid) REFERENCES users (id)
);

CREATE TABLE IF NOT EXISTS question (
  id          INT  NOT NULL PRIMARY KEY AUTO_INCREMENT,
  genre       char NOT NULL,
  main_text   char NOT NULL,
  first_text  char,
  second_text char,
  answer      char NOT NULL
);

--INSERT INTO question (genre, mainText, firstText, secondText, answer) VALUES ('PostgreSQL', 'データの検索を行うための構文', '', 'user FROM table;', 'select');
--INSERT INTO question (genre, mainText, firstText, secondText, answer) VALUES ('Java', 'Java言語におけるprint文の構文(改行も行う)', '', '(''Hello world.'')', 'System.out.println');
--INSERT INTO question (genre, mainText, firstText, secondText, answer) VALUES ('Python', 'Python言語におけるprint文の構文', '', '(''Hello world.'')', 'print');

--INSERT INTO question (genre, mainText, firstText, secondText, answer) VALUES ('PostgreSQL', 'データの追加を行うための構文', '', 'INTO table (name, age) VALUES (''John'' , 42);', 'insert');
--INSERT INTO question (genre, mainText, firstText, secondText, answer) VALUES ('Java', 'Java言語における変数の宣言(整数値)', '', 'age', 'int');
--INSERT INTO question (genre, mainText, firstText, secondText, answer) VALUES ('Python', 'Python言語における空リストの宣言', 'list = ', '', '[]');

--INSERT INTO question (genre, mainText, firstText, secondText, answer) VALUES ('PostgreSQL', '一時テーブル(temp)を使用する際の構文(3単語)', '', 'temp1;', 'create temp table');
--INSERT INTO question (genre, mainText, firstText, secondText, answer) VALUES ('Java', 'Java言語におけるfor文（変数はiを使用する, 0<=i<10で1つずつインクリメントする、初期構文でiを宣言する）', '', '{ System.out.println(i); }', 'for(int i=0;i<10;i++)');
--INSERT INTO question (genre, mainText, firstText, secondText, answer) VALUES ('Python', 'Python言語のfor文を利用して1~10の総和を求める(0は加算しない)', 'for i ', '', 'in range(1,11)');

--INSERT INTO score (userId, score, genre, startDateTime, endDateTime) VALUES (1, 80, 'Java', to_timestamp('2018-04-19 19:06:30', 'YYYY-MM-DD HH24:MI:SS'), to_timestamp('2018-04-19 19-13-07', 'YYYY-MM-DD HH24:MI:SS'));
