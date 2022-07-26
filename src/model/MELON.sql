CREATE TABLE MMUSIC(
	MNUM INT PRIMARY KEY,
	MTITLE VARCHAR(100) NOT NULL,
	MARTIST VARCHAR(100) NOT NULL,
	MHIT INT DEFAULT 0
);
CREATE TABLE MUSER(
	UNUM INT PRIMARY KEY,
	USERID VARCHAR(20) NOT NULL,
	UPW VARCHAR(20) NOT NULL,
	UNAME VARCHAR(20) NOT NULL,
	ULASTMUSIC INT DEFAULT 0
);
CREATE TABLE LASTMUSIC(
	LNUM INT PRIMARY KEY,
	MNUM INT DEFAULT 0,
	UNUM INT DEFAULT 0
);

SELECT * FROM USER_TABLES;
SELECT * FROM LASTMUSIC;
SELECT * FROM MUSER;
DROP TABLE MUSER;
DROP TABLE MMUSIC;
DROP TABLE LASTMUSIC;

SELECT * FROM (SELECT MAX(A.LNUM) B, A.MNUM FROM (SELECT * FROM LASTMUSIC WHERE UNUM = 1) A GROUP BY A.MNUM) A,MMUSIC M WHERE A.MNUM=M.MNUM
ORDER BY B DESC;