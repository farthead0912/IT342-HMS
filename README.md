# MediSync
## System Description
MediSync is an all-in-one hospital management system with an AI chatbot for consultations and scheduling.
## List of Features
- [ ] AI Chatbot for Consultations and Scheduling (unfeasible, we don't have an API key for any GenAI chatbots sadly)
- [x] Inventory, Room, and Billing Management
- [x] Role-based Dashboard
- [x] Patient Data and Record Management
- [ ] Medical Second-Opinion Telemedicine Platform 
- [ ] Prescription Information and Order Management
## Links
- [Figma](https://www.figma.com/design/iwIiDmIT4GEHQL0StzGOFt/MediSync---Frontend-Mobile?node-id=0-1&t=xnoxCh4MSV8jfhuL-1)
- [ER Diagram and Schema]()
## Tech Stack
This project uses:
- Spring Framework using Maven for the backend + PostgreSQL for the database, running on Render
- ReactJS for the web frontend
- Android + Kotlin for the backend
## Developers' Profiles
1. **Flores, Adrian Ash D.**
   - GitHub: [farthead0912](https://github.com/farthead0912)
   - BSIT-3
   - A dumb, lazy and silly goober with too much time in his hands. I like reading manga, manhwa, and comics.<br/>I love playing singleplayer story-focused games and I will shill for [OMORI](https://store.steampowered.com/app/1150690/OMORI/), [SIGNALIS](https://store.steampowered.com/app/1262350/SIGNALIS/), [Rain World](https://store.steampowered.com/app/312520/Rain_World/), and [Celeste](https://store.steampowered.com/app/504230/celeste/). Please play these games, they are beautiful pieces of art as a game.<br/>That's all, folks.
2. **Calzada, Earl Owen V.**
   - GitHub: [EarlCalzada](https://github.com/EarlCalzada)
   - BSIT-3
   - I'm a kind person and I like watching anime and playing video games on mobile phone and also I like motorcycles.
3. **Rivera, Benjamin E. Jr.**
   - GitHub: [kwonfir33](https://github.com/kwonfir33)
   - BSIT-3
   - I’m your average kind of guy. I like to play computer games with friends.<br/>I believe in personal growth and always strive to improve myself in small ways every day.<br/>Also, listening to and singing music when alone is something that everyone does.<br/>Lastly, I’m hoping for a brighter future for myself.
## Setup
Since the backend has been deployed to Render, you can instead use this to connect to the backend instead of cloning: ```https://it342-hms-medisync.onrender.com```<br />
For the API calls, you can find the PDF file that lists the API docs inside... somewhere.

### ~First Things First~
~Before running this or cloning, make sure you have the following dependencies and software:~

~1. Git and/or GitHub Desktop (used to clone the repository)~
~2. Visual Studio Code (or other similar IDEs that can run Java applications)~
~3. NPM (this is usually packaged with Node.js)~
~4. XAMPP (or any other web server you can find and run, since the app's database is currently in MySQL)~
~5. Java SDK version 17~

### ~Cloning and Running~

~1. Run ```git clone https://github.com/farthead0912/IT342-HMS.git``` on cmd or GitHub Desktop, whichever you think is best, and make sure you point it to a folder you'd like to store it in.~
~2. Open up XAMPP Control Panel and run both Apache and MySQL modules. Go to a browser of your choice and type this down in the URL box: ```localhost/phpmyadmin```; this should open up PHPMyAdmin, which is where the database is located.~
~3. Create a new database, name it whatever, go to the Import tab in the database, then drag and drop the SQL file provided in the backend folder into the database. This should fill up all the necessary tables and fields with data.~
~4. Open up VSCode (Visual Studio Code), open up the folder then find a subfolder inside it named 'backend'.~
~5. VSCode should have opened the folder, now go to the HmsApplication found in src/main/java/edu/cit/hms then run it by clicking the play button somewhere on the top right of your window, just below the minimize button.~
~6. After all of these, go to localhost:8080 to try the application. If it returns an HTTP code 400, that means I messed up and it didn't properly point to a pseudo-homepage where you can register or log-in. I'm still waiting for the frontend, so please, bear with me.~
