

Tesseract trained data files in the resource folder.
- Default data files are optimized.
- Data files in 'fast' folder offer better speed.
- Data files in 'best' folder offer better accuracy.


<b>Running the application locally</b>

- Copy trained data files from resources and create a directory and paste them. Give that folder path as an environment variable when running application.

- 'mvn clean package'

- 'java -jar ocr-demo-1.0.0.jar -DTESSERACT_DATA_PATH=<i>TESSDATAFOLDERPATH</i>'

- open on browser: http://localhost:8080/