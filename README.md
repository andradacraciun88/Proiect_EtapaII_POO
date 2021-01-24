# Proiect Energy System Etapa 2

## Despre

Proiectare Orientata pe Obiecte, Seriile CA, CD
2020-2021

<https://ocw.cs.pub.ro/courses/poo-ca-cd/teme/proiect/etapa2>

Student: todo nume, grupa
Craciun Andrada-Sinziana, 324CA
Git: https://github.com/andradacraciun88/Proiect_EtapaII_POO

## Rulare teste

Clasa Test#main
  * ruleaza solutia pe testele din checker/, comparand rezultatele cu cele de referinta
  * ruleaza checkstyle

Detalii despre teste: checker/README

Biblioteci necesare pentru implementare:
* Jackson Core 
* Jackson Databind 
* Jackson Annotations

Tutorial Jackson JSON: 
<https://ocw.cs.pub.ro/courses/poo-ca-cd/laboratoare/tutorial-json-jackson>

[Puteti sa folositi si alte biblioteci si sa editati aceasta sectiune]

## Implementare

### Entitati

Clasele folosite, nu este nevoie sa enumerati proprietati si metode, 
doar sa ziceti rolul lor si de ce le folositi]

-pachetul input contine clasele ce sunt folosite pentru parsarea inputului care
 se ia in format json

-pachetul output contine clasele ce sunt folosite pentru parsarea outputului care
 se scrie in format json

-pachetul entities contine clase in care sunt implementate metode intermediare ce
 ajuta la construirea sistemului de energie
-clasa Simulation contine metodele principale ale programului 
-apelarea acestora are loc in clasa Play si se face intr-o ordine anume pentru a fi in 
concordanta cu actiunile fiecarei entitete
-am implementat metoda bestPrice care intoarce distribuitorul cu cel mai ieftin contract
-metodele update si updateProducer in care se citesc din input modificarile ce au loc la 
inceputul fiecarei luni, atat pentru consumatori cat si pentru distribuitori si producatori
-in clasa Play se desfasoara apelul de metode necesare pentru fiecare runda
-clasa Contract are rolul de a tine o legatura intre distribuitori si consumatori, continand
 campuri atat pentru consumator, distrbuitor cat si pretul si valabilitatea contractului
-in clasa MonthlyStats se retine o lista cu distribuitorii fiecarui producator, 
cat si lunile in care aste doua entitati au colaborat
-am folosit clasa Simulation in care am implementat metodele principale pentru a 
rezolva cerintele(o scurta descrierea a metodelor se afla in comentariile din cod)

-pachetul compare contine clase ce implementeaza un comparator pentru producatori, 
sortandu-i astfel, in functe de strategia aleasa
dupa criteriile cerute

-pachetul sort contine o interfata ce este implementata de clasele SortGreenStrategy, 
SortPriceStrategy si SortQuantityStrategy
-aceste clase sunt implementate pentru design pattern-ul strategy
-in clasa SortStrategyFactory se identifica tipul de strategie 


### Flow

Ce se intampla in fiecare runda (luna), cum comunica entitatile intre ele, ce clasa controleaza flowul etc

-clasa care controleaza flow-ul este Play, metodele fiind implementate in clasa Simulation
-in runda initiala:
*cu ajutorul metodei monthlyStats creez si adaug intr-o lista, pentru fiecare producatori, 
distribuitorii ce ii sunt asignati
-la inceputul primei runde se citesc datele de intrare, distribuitorii isi aleg producatorii,
isi calculeaza costurile contractelor
-se calculeaza costul productiei in functie de producatorii alesi
-consumatorii isi primesc salariile, isi aleg distribuitorii si ii platesc 
-distributorii primesc bani de la consumatori si isi platesc costurile
-se scot din evidenta consumatorii ce au dat faliemnt
*metoda distributorsChooseProducers are rolul de a alege cu ajutorul design pattern-ului 
strategy productorii necesari
pentru fiecare distribuitor. Daca energie data de producator este suficienta pentru distribuitor
, atunci acesta este asignat
acelui distribuitor si adaugat in lista sa de producatori. Daca energia nu este suficienta, 
se alege cu acelasi principiu, inca un alt producator
pana se ajunge la energia necesara
-legaturile dintre cele doua entitati, consumatorul si distribuitorul este tinuta de clasa Contract
-in rundele urmatoare, daca apar update-uri pentru producatorul la care un consumator este abonat:
-cu ajutorul design pattern-ului observer am notificat toti distribuitorii asignati unui producator
atunci cand a avut loc o schimbare, modificand campul update in clasa DistributorInputData
din 0 in 1 
-cu ajutorul metodei deleteIfNotify, de scoat din lista producatorului toti distribuitorii, 
si din lista distribuitorilor, producatorul la care are loc update-ul
-cu ajutorul design pattern-ului observer am notificat toti distribuitorii asignati unui producator
atunci cand a avut loc o schimbare, motificand campul update in clasa DistributorInputData
int 0 in 1
-astfel pentru toti distribuitorii producatorului in cauza se aplica iar strategy pattern-ul pentru 
alegerea producatorilor
-la finalul lunii cand se actualizeaza producatorii se calculeaza si costul productiei
si se retine ce distribuitori a avut fiecare producator si in ce luna

### Elemente de design OOP

e.g Am folosit incapsulare pentru ... Am folosit abstractizare pentru ...

-am folosit interfata SortStrategyFactory care mai apoi a fost implementata de clasele
SortGreenStrategy, SortPriceStrategy si SortQuantityStrategy
cu scopul de a crea design pattern-ul strategy
-am folosit extinderea, respectiv implementarea prin intermediul
claselor date de Java (Observer si Observable) pentru a crea design pattern-ul observer

### Design patterns

Ce design patterns ati folosit, cum le-ati adaptat problemei date

-design pattern-urile folosite sunt strategy pattern si observer.

-strategy pattern a fost folosit pentru a alegerea strategiilor, iar in functie de acestea,
pentru sortarea listei de producatori
-observer pattern a fost folosit pentru notificarea distribuitorilor atunci cand un producator este updatat
-in cazul nostru observatorii sunt distribuitorii, iar subiectul este producatorul
-distribuitorii implementeaza clasa Observer din Java
-producatorii implementeaza clasa Observable din Java 

### Dificultati intalnite, limitari, probleme

e.g. pentru a rezolva problema X de checkstyle am facut ...

-'0.2' is a magic number. Pentru a rezolva acesta eroare am creat un camp 
private final double val pe care l-am initializat cu 0.2.
-Sort' must match pattern '^[a-z]+(\.[a-zA-Z_][a-zA-Z0-9_])$'. [PackageName]
Pentru a rezolva acesta eroare am renumit pachetul cu litere mici.
-Class 'ConsumersInputData' looks like designed for extension (can be subclassed),
but the method 'getCurrentContract' does not have javadoc that 
explains how to do that safely. If class is not designed for extension 
consider making the class 'ConsumersInputData' final or making the method 
'getCurrentContract' static/final/abstract/empty, or adding allowed annotation 
for the method. 
Pentru rezolvarea acestei erori am facut clasa ConsumersInputData final

[optional ## Feedback, comments

