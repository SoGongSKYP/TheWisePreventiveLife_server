# TheWisePreventiveLife_server
WPL 안드로이드 애플리케이션과 오라클 DB를 연결해주는 jsp 서버입니다
## **개발 환경**

- ### local에서의 개발
  : JSP(이클립스-Dynamic Web Project), 오라클(docker를 통한 oracle-xe-11g 설치-Mac 탑재 cmd창) 

- ### 서버 배포
  :AWS EC2(Amazon Linux 64-bit), 오라클(docker를 통한 oracle-xe-11g 설치), local에서 개발한 웹프로젝트의 WAR파일,FileZilla(파일 보내기), Mac 탑재 cmd(ec2 instance와 연결)


## **Oracle DataBase 쿼리문**

1.  환자 정보 저장 데이터베이스 PATIENT

1. 환자 동선 정보 저장 데이터 베이스 PMOVING


```
CREATE TABLE PATIENT(PatientNum VARCHAR2(18) NOT NULL, PatientLocNum      CHAR(4) NOT NULL ,ConfirmDate DATE NOT NULL);
```

```
CREATE UNIQUE INDEX XPKPATIENT ON PATIENT(PatientNum  ASC,PatientLocNum  ASC);
```

```
ALTER TABLE PATIENT  
ADD CONSTRAINT XPKPATIENT PRIMARY KEY (PatientNum,PatientLocNum);
```

```
CREATE TABLE PMOVING(PatientNum VARCHAR2(18) NOT NULL , PatientLocNum CHAR(4)  NOT NULL,VisitDate DATE  NOT NULL,  PointX NUMBER(13,10) NOT NULL, PointY NUMBER(13,10) NOT NULL,address VARCHAR2(150) NOT NULL);
```

```
ALTER TABLE PMOVING
ADD (CONSTRAINT R_1 FOREIGN KEY (PatientNum,PatientLocNum) REFERENCES PATIENT(PatientNum,PatientLocNum));

```

```
CREATE UNIQUE INDEX XPKPMOVING ON PMOVING
(PatientNum  ASC,PatientLocNum  ASC,visitdate ASC,pointx ASC, pointy ASC, address ASC);
```

```
ALTER TABLE PMOVING
ADD CONSTRAINT  XPKPMOVING PRIMARY KEY (PatientNum,PatientLocNum,visitdate,pointx,pointy,address);
```


