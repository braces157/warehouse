# Project

Small Java Swing desktop application (Maven) — inventory / billing UI.

## Quick start

- Build (uses Maven Wrapper):
  - Unix/macOS: `./mvnw clean package`
  - Windows: `mvnw.cmd clean package`
- Run built jar:
  - `java -jar target/Project-1.0-jar-with-dependencies.jar`

Files:
- [pom.xml](pom.xml)
- [mvnw](mvnw) and [mvnw.cmd](mvnw.cmd) (Maven Wrapper)
- [.mvn/wrapper/maven-wrapper.properties](.mvn/wrapper/maven-wrapper.properties)

Main entry:
- [`braces.Project`](src/main/java/braces/Project.java)

## Important modules / utilities

- Password hashing: [`braces.util.BCrypt`](src/main/java/braces/util/BCrypt.java)
- Image utilities (copy + set icons): [`braces.util.XIcon`](src/main/java/braces/util/XIcon.java)
- JTable export to Excel: [`braces.util.JTableExporter`](src/main/java/braces/util/JTableExporter.java)
- PDF export usage in dialogs: see [`braces.gui.dialog.DetailImportBill`](src/main/java/braces/gui/dialog/DetailImportBill.java) and [`braces.gui.dialog.DetailExportBill`](src/main/java/braces/gui/dialog/DetailExportBill.java)

GUI dialogs (examples):
- Product dialogs: [`braces.gui.dialog.CreateProductDialog`](src/main/java/braces/gui/dialog/CreateProductDialog.java), [`braces.gui.dialog.EditProductDialog`](src/main/java/braces/gui/dialog/EditProductDialog.java)
- Many other dialogs and pages live under `src/main/java/braces/gui` (see files referenced in the build list).

Resources and assets:
- Application resources: `src/main/resources/` (icons & images)
- Extra images folder: [images/](images/) and packaged `src/main/resources/img` used by UI

Build outputs (target):
- Packaged jars and executables: `target/Project-1.0-jar-with-dependencies.jar`, other artifacts under `target/`

## Notes & tips

- Wrapper downloads Maven as configured in [.mvn/wrapper/maven-wrapper.properties](.mvn/wrapper/maven-wrapper.properties). If download/validation fails, inspect that file.
- UI code uses `XIcon.copyTo(...)` to save chosen images into project resources; see [`braces.util.XIcon`](src/main/java/braces/util/XIcon.java).
- Passwords use PBKDF2 (see [`braces.util.BCrypt`](src/main/java/braces/util/BCrypt.java)) — verify iteration/length constants if changing hashing policy.

## Running from IDE

- Import as Maven project (pom.xml) into your IDE (NetBeans/IntelliJ/VS Code Java).
- Ensure JDK matching `maven.compiler.release` in [pom.xml](pom.xml) is used.

For more details inspect the files
