# Dictionary
## How to install and run
1. Copy file **speech.properties** in folder **resource\lib** to folder Home on your device.
2. Open project with Intellij IDEA.
3. Select *File* -> *Project Structure* -> *Modules* -> *Dependencies*.
4. Add all .jar files in **resource\lib** into *Dependencies* and select *OK*.
5. Select *Edit configurations* -> *DictionaryApplication* -> *Add VM options* -> Paste **--module-path "PATH" --add-modules javafx.controls,javafx.fxml,javafx.graphics** with PATH is the direction to folder **libJavaFX** in **resource\lib**.
6. run *DictionaryApplication*.
