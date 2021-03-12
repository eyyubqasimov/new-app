"# Rep_Umb_Carp_Spring" 

Creare un DB con nome udm e una tabella con nome user. Per la struttura della tabella user guardare la classe Entity User.

Per l'esecuzione lanciare Laragon (che permette di startare il DB MySql), lanciare la classe SpringBootHelloWorldApplication.class (per eseguire il web server Tomcat) e dopo eseguire le varie chiamata Postman.

Per creare un utente digitare, creare una chiamata POST: http://localhost:8080/register e nel body inserire ad esempio i seguenti valori: 
{
        "username": "username_prova3",
        "password": "prova",
        "name": "umb_ins",
        "lastname": "umb_ins",
        "email": "username_prova3@gmail.com",
        "phone": 1,
        "province": "ac",
        "age": 11,
        "fiscalcode": "QWE345TRE543ER45R"
    }
 
 Ho considerato anche il caso in cui sto provando a creare un utente con username già presente nel DB:
{
    "timestamp": "2021-03-10T14:45:48.411+00:00",
    "message": "User already found with email: username_prova3@gmail.com",
    "details": "uri=/register"
}
 
 Mentre, per loggarsi al sistema, creare una chiamata POST: http://localhost:8080/login con i seguenti valori nel body: 
{
    "email": "username_prova4@gmail.com",
    "password": "prova_12"
    }

---------------

http://localhost:8080/register
{
        "username": "ProvaUmb",
        "email": "umb@gmail.com",
        "password": "prova12"
}