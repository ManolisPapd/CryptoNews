# CryptoNews
crypto prices with discussions from reddit
enable dark mode

Without login
Home page shows all the crypto, can click on page an see reddit discussions

Sign up/Login
Two tabs Home page, Portfolio, Profile (can share portfolio)
Filter which cryptos in what order
Portfolio, add crypto bought when and what price 


Χωρίς σύνδεση
Λίστα με τα νομίσματα στην σειρά
Θα μπορεί να πατήσει πάνω να βλέπει λεπτομέρειες και νέα απο σελίδες (βρες κάποιες)

Δημιουργία Portfolio
1)Επιλέγει από την λίστα ποιο νόμισμα και τα λοιπά δεδομένα
	1.1) Η λίστα θα εμφανίζεται από το api
2) Αφού βάλει όλα τα δεδομένα, στέλνω στο backend το όνομα του νομίσματος και τα δεδομένα
3) Πάνω πάνω θα του εμφανίζεται η χασούρα του σε ευρώ
4) Σε κάθε νόμισμα θα μπορεί να πατάει πάνω και να του εμφαζίνονται νέες λεπτομέρειες και νέα απο σελίδες (βρες κάποιες)

Θα μπορεί να διαγράψει νομίσματα και να τα κάνει update
Με το currentusd(από το api) θα του δείχνουμε πόση χασούρα έχει (αυτό θα το κάνει με price bought - currentusd από το ΤΩΡΙΝΟ api)


Όταν θα κάνει σύνδεση, θα του βγαίνει το εικονίδιο για sign in και θα μπαίνει εικονίδιο προφίλ με λεπτομέρειες για τον λογαριασμό του όπως
	1. όνομα
	2. username
	3. αλλαγή κωδικού
	4. αλλαγή email

Πίνακας Portfolio
(id,fk(id_user)) PK, currency, date bought, price bought

