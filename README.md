# ColorBusterAB 🎨

**ColorBusterAB** is a Java Swing color-matching puzzle game where players remove groups of matching tiles from a board.  
When tiles are removed, the remaining ones collapse downward — similar to *Candy Crush* logic.  

---

## 🧱 Project Overview

- **Language:** Java  
- **GUI Framework:** Swing  
- **Tests:** JUnit 5  
- **Packages:**  
  - `Model` — Core game logic and tile/board handling  
  - `Images` — Contains tile image assets  

---

## 🧩 Features
- Dynamic 8x8 color grid  
- Automatic collapse of columns after tile removal  
- Randomized board initialization ensuring at least one valid move  
- “Missing image” fallback display in case of missing assets  
- Unit tests verifying board logic, tile image loading, and move detection  

---

## 🧪 Running Tests
Ensure JUnit 5 is in your classpath, then run:

```bash
mvn test

💡 How to Run the Game

Compile and run the Main.java file from the Model package:

javac Model/*.java
java Model.Main

The game window will open showing an 8x8 board of randomly colored tiles.

🧑‍💻 Author
Anissa Braca
