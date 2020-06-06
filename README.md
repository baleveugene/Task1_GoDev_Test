#Instructions

<p><b>Quick Start</b></p>
<p>Ensure current JDK is installed (nothing else is required -- other dependencies will automatically download)</p>
<p>To run tests with <b>Chrome</b> run: <b>./gradlew goDevTest</b></p>
<p>To tun tests with <b>MSEdge</b> run: <b>./gradlew goDevTest -Dbrowser=edge</b></p>
<p>To tun tests with <b>Mozilla Firefox</b> run: <b>./gradlew goDevTest -Dbrowser=firefox</b></p>
<p><b>Notes</b></p>
<a href="https://mvnrepository.com/artifact/io.github.bonigarcia/webdrivermanager">WebDriverManager</a> will automatically try to download required OS specific drivers for Chrome or MSEdge or Firefox
Example for running tests from the commandline with INFO logging level: <b>./gradlew goDevTest -Dbrowser=edge --info</b>