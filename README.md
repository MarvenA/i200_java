# Navigatsioonimärkide test
Aine *I200 Programmeerimise algkursus Java baasil* kursuseprojekt.

## *Projekti kirjeldus*
Projekti sisuks on Eesti vetes kasutatavad **navigatsioonimärgid**, mis jagunevad lateraal- ja kardinaalmärkideks.<br>
Tegemist on pildiküsimuse ja nelja valikvastusega.
Märkide pildid, spikker ja muud programmis kasutatavad pildid on salvestatud projekti kausta *Res*, vastused (märkide nimetused) on eraldi tekstifailis.

Programm genereerib juhuvalikuga ühe piltidest ja vastavalt pildile paneb nelja vastuse hulka ka õige. Ülejäänud kolm vastust valitakse listist samuti juhuslikult.<br>
Vastused salvestatakse *radiobutton*-sitesse, mis omakorda pannakse kõik ühte *togglegroup*-i, et välistada korraga mitme valiku tegemist.<br>
Pärast kontrollimist, saab kasutaja tagasisidet, kas valitud vastus oli õige või vale selliselt, et õige vastus kuvatakse rohelisena ja vale punasena.

Programmi kujundus on lihtne, põhilisteks värvideks on türkiissinine ja valge (valge tekst tumedal taustal). Kogu kujunduse osa on eraldi **CSS** *stylesheet*-il, et kood vähem kirju oleks.

Programmil on põhiaknas kolm vaadet ja üks lisa aken:

1. Käivitamisel sissejuhatus ja testi tutvustus
2. Testi tegemise vaade
3. Lõpetamisel kokkuvõte tulemustest
4. Soovi korral on võimalus kõiki märke üle vaadata spikrist, mis avaneb eraldi aknas. Spikris on ära toodud iga märgi seletus.


## *Kasutusjuhend*
1. Käivita programm - avaneb nö. põhiaken, kus on sissejuhatav info;
2. Alustamiseks tuleb klikkida **"Alusta"** - seejärel ilmub küsimus, pilt ja neli valikvastust;
3. Tee oma valik ja kontrolli vastust vajutades **"Kontrolli"** peale. Seejärel näed, kas vastasid õigesti või valesti.
4. Alles peale kontrollimist saad suunduda järgmise küsimuse juurde - **"Järgmine"**
5. Testi saad jätkata seni kaua, kuni ise soovid, lõpetamiseks vajuta **"Lõpeta"** ja ekraanil kuvatakse mitu küsimust läks täppi ja mitu mitte;

Spikrit saad ühe testi jooksul piiluda kolm korda! Spikrit ei ole võimalik kogu testi jooksul avatuna hoida.
