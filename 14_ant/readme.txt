Создать build.xml файл который позволяет с помощью Ant откомпилировать и упаковать в jar файл проект.
1. Структура проекта
project_name/src - папка с исходным кодом
project_name/test - папка с junit тестами
project_name/build.xml
2. Должны быть реализованы следующие targets
init - создание временных директорий
clean - удаление временных директорий
dist - упаковка в jar
compile - компиляция классов как основных так и тестовых
test - запуск тестов
3. Properties должны читаться из файла project.properties  и если они там отсутствуют то должны браться их build.xml.
Таким образом проект должен работать как с файлом properties так из без него
4. Все targets должны выводить в консоль информацию о начале и завершении своей работы
5. Выбрать 3 любых tasks из уже реализованных в Apache Ant и добавить к себе в про


Создать maven project и перенести в него все предыдущее задания как отдельные модули
Этапы:
1. Создать новый maven проект pom.xml и добавить в него основные описания
2. С помощью maven archetype создать дополнительные модули. Причем в одном случае
 нужно задавать основные параметры в диалоговом режиме, а в последующем через атрибуты командной строки. 
 Каждый модуль  - это отдельная лабораторная работа (можно взять 3 последних)
3. Внести изменения в pom.xml файлы главного проекта и в проекты модулей для связи между ними
4. Использовать properties для указания версий библиотек 
5. Запустить проект на сборку с различными параметрами командной строки и различными фазами
6. Самостоятельно изучить и добавить в проект Apache Maven Site Plugin

http://www.apache-maven.ru/advanced/property.html
http://maven.apache.org/archetype/maven-archetype-plugin/examples/generate-batch.html
https://maven.apache.org/plugins/maven-eclipse-plugin/reactor.html
http://blog.valdaris.com/post/custom-jar/
http://www.javaworld.com/article/2071733/java-app-dev/get-the-most-out-of-maven-2-site-generation.html
https://books.sonatype.com/mvnref-book/reference/site-generation-sect-custom-descript.html
http://habrahabr.ru/post/115657/