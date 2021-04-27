See projekt on mõeldud hambaarstide visiite halduseks.

Infosüsteemi arendus võttis aega umbes 11h, sellest umbes 2h paranduste tegemiseks.
Rakendust käivitades luuakse andmebaasi automaatselt vajalikud tabelid ning sisestatakse neisse data.sql failist  mockandmed.

Kuna töö autor oli enne seda proovitööd vaid põgusalt thymeleafiga kokku puutunud, siis võttis paremate lahenduskäikude otsing veidi aega.
Samuti oleks autor soovinud näiteks uue visiidi registreerimise juurde teha funktsionaalsuse, et igal arstil oleks oma eraldi ajad ja siis kui dentistName backendis ära väärtustatakse, saaks java hashmapist selle järgi ka ainult vastava arsti võimalikud visiidi ajad kuvada. Kahjuks aga tundus, et thymeleaf ei muuda lehe sisu, kui see leht juba laetud on.
Sellest tulenevalt jätsin arstide nimed ja visiitide ajad mitte seotuks.

Töö loomisel autor internetist pikemaid koodijuppe ei kasutanud.

Infosüsteem koosneb kolmest erinevast põhilehest:

 - Visiitide registreerimise leht
 - Registreeritud visiitde vaatamise leht
 - Visiitide algsete mockandmete lisamise leht
 
Lisaks sellele on registreerimise tulemuste edukusest teavitavad lehed.

DentistAppControlleris on järgmised meetodid:

 - showRegisterForm - tõmbab getPossibleDentistVisitData() meetodi abil possible_dentist_visit tabelist kõik andmed sisse ja kuvab need visiidi loomise vormis
 - showNewDataRegisterForm - kuvab uute mockandmete lisamise vormi
 - deleteRegistrationById - kustutab ette antud id järgi vastava registreeritud visiidi
 - showRegistrationModificationById - kuvab visiidi muutmise vormi (sama kui lisamise vorm, aga väljad on täidetud)
 - postRegisterForm - lisab andmebaasi uue visiidi kirje
 - postNewDataRegisterForm - lisab andmebaasi uue mockandmete kirje
 - modifyRegisterForm - muudab ette antud visiidi objekti järgi vastava id-ga objekti andmebaasis
 - searchVisitsByKeyword - otsib märksõna järgi kirjeid registreeritud visiitide tabelist arsti nime, patsiendi nime ja visiidi kuupäeva ning aja alusel. Kui märksõna on tühi, kuvab kõik kirjed.
 - getPossibleDentistVisitData - tõmbab mockandmete tabelist kõik kirjed ning lisab need muutujatena vormidese
 - modifyVisit - loob uue visiidi/muudab ette antud visiiti.
 
Enne andmebaasi kirje loomist/muutmise kontrollitakse, kas vorm on õigesti täidetud.

DentistVisitService failis on järgmised meetodid:
 - addVisit - lisab uue kirje dentist_visit tabelisse
 - putVisit - otsib ette antud dentistVisit objekti id järgi üles andmebaasi objekti, muudab seda ning salvestab uuesti
 - findById - leiab id järgi sobiva visiidi
 - isDateIsAlreadyRegistered - kontrollib, kas ette antud kuupäeva ja aja kohta on juba visiidi registreering tehtud
 - findAllRegistrations - leiab kõik registreeritud visiidid
 - findRegistrationsByKeyword - leiab kõik registreeritud visiidid märksõna alusel
 - kustutab visiidi ette antud id järgi
 
 
PossibleDentistVisitService failis on järgmised meetodid:
 - addData - lisab uued mockandmed possible_dentist_visit tabelisse
 - findAllPossibleVisits - leiab kõik mockandmed

Lisaks on loodud nii registreeritud visiitide kui ka mockandmete tabelite haldamiseks vastavad servicid, repositoryd ja andmete üle kandmiseks vajalikud objektid ning entityd.
