javac -cp . -d . Net.java
javac --module-path ..\Libs\javafx\lib --add-modules=javafx.controls,javafx.fxml -cp . -d . controller.java
javac --module-path ..\Libs\javafx\lib --add-modules=javafx.controls,javafx.fxml -cp . -d . main.java

pause