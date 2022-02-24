# bookcollection

Simple RESTful API created for a demonstration

## Running the application:

Build & start the application with one of the following methods:

#### Docker:
`docker build -t bookcollection . && docker run -dp 8080:8080 bookcollection`

#### Gradle build & run (requires JRE 11):
`./gradlew build && java -jar ./build/libs/bookcollection-0.0.1-SNAPSHOT.jar`

#### Gradle application plugin (starts the application in development mode, also requires JRE 11):
`./gradlew run`

The application will start at `https://localhost:8080`.

Above commands should work with Linux / Mac shells & Powershell 7. With earlier versions
of Powershell, please replace `&&` with `;`.
