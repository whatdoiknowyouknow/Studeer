# Studeer
Studeer

StudeerApp : deze klasse communiceert met de gebruiker, door het tonen van de menu's en het aanvaarden de keuze vd gebruiker. Vermits de user input in de uiteindelijke versie grafisch verwerkt zal worden, heb ik in deze versie geen checks ingebouwd of de gebruiker wel een 'correcte' keuze opgeeft.

Challenge: deze klasse is de kleinste eenheid in het programma. Het is simpelweg een combinatie van een vraag met de daarbij horende correcte antwoorden. Ik had ook kunnen kiezen om hier geen aparte klasse voor aan te maken, omdat een vraag en antwoord bijna het typevoorbeeld zijn van waarvoor een Map gebruikt kan worden, maar de meest voorkomende operatie op vraag en antwoord is het random kiezen van een vraag. Uit een map at random iets selecteren lijkt me omslachtig. Ik koos er daarom voor om een aparte klasse te maken voor de combinatie vraag en antwoord, en deze op te slaan in een ArrayList. Verbeter mij gerust indien de redenering niet klopt, of indien een andere collection meer geschikt is. Challenge is een inner class in de klasse Course.

Course. Alle challenges (combinatie vraag en antwoord) worden bijgehouden per onderwerp (Frans, Engels, Statistiek). Hiervoor gebruik ik de klasse Course. Elke course bevat dus een ArrayList van challenges over een bepaald onderwerp. Course is hier een abstracte klasse. Er zijn immers twee soorten 'courses': die waarbij de vragen worden opgehaald uit een bestand (bv. Frans of Engels), en die waarbij het programma zelf vragen geneert (Statistiek). De concrete, extended klassen hiervan zijn CourseWithSavedQuestions (Frans, Engels) en CourseWithAutoGenQuestions (in dit geval Statistiek). Suggesties voor betere namen zijn zeker welkom :s .

QuizMaster. Alle Courses worden bijgehouden in de klasse QuizMaster, die ook met een static method de student ondervraagt, en zijn score bijhoudt. 

Student. Spreekt voor zich.
