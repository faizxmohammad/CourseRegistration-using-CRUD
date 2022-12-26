Hello there , this course registrationprogram, which allows users to register for a course , checkout courses avaliable .
Also contains a admin panel used to control each activity of the course
Database used ORACLE 11G xe edition:

The tables used for the database are:-  

Student table :- 
```

       SID SNAME                              SPHONE
---------- ------------------------------ ----------
         1 faiz                                  123
         2 Mohammed Faiz                       819251
         
  ```     
         
 Registration table :- 
 
 
 ```
        ID R_COURSE                             S_ID      PHONE        CID RDATE     SNAME
---------- ------------------------------ ---------- ---------- ---------- --------- ------------------------------
         1     Java                                1        123          1 26-DEC-22    faiz
         3     Python                                     111222         2 26-DEC-22    fmd
         4 data science                            3     456345          1 26-DEC-22    mohammed
         
         
         
```

 ```
  
  Courses table :- 
  
       CID  CNAME                                CFEE  CDURATION
---------- ------------------------------ ---------- ----------
         1 java                                 4000         30
         3 data science                         6000         45
         2 python                               5000         40


```

```
Certification table :- 

CERTIFICATEID     CERTIFICATENAME               SID
------------- ------------------------------ ----------
            1     java                            1
            
      
  ```
  
  ```
Payment table :- 
//here first and second attributes are installments 

FIRST SECOND        SID
----- ------ ----------
yes   yes            1
yes   no             2


```

Only students who have paid both their installments paid will receive their certificate
            
Student course table:-
```
This table shows how many courses a student has taken (contains student id , course id and course name)

       SID        CID COURSENAME
---------- ---------- ------------------------------
         1          3 python
         1          2 datascience

```

