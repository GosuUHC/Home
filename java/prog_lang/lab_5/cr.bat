javac -classpath ".\ojdbc11.jar" -d . DBUtil.java
javac --module-path Libs\javafx\lib --add-modules=javafx.controls,javafx.fxml -classpath ".\ojdbc11.jar" -d . Department.java
javac --module-path Libs\javafx\lib --add-modules=javafx.controls,javafx.fxml -classpath . -d . DepartmentDAO.java
javac --module-path Libs\javafx\lib --add-modules=javafx.controls,javafx.fxml -classpath . -d . Controller.java
javac --module-path Libs\javafx\lib --add-modules=javafx.controls,javafx.fxml -classpath ".\ojdbc11.jar" -d . Main.java

java --module-path Libs\javafx\lib --add-modules=javafx.controls,javafx.fxml -classpath ".;.\ojdbc11.jar" lab_5.Main
pause