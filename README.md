#Museum
##Basic functionality
Application inputs a basic map of a museum from a text file, with impassable walls, a starting point, exhibitions enumerated A-Z.

The museum map also lists information on each exhibit (painting title and URL.)

The museum map is displayed in the terminal using a simple text diagram.

User may either navigate the museum with wasd controls or jump to an exhibition by entering the exhibition's capital letter. The user will only move when it is permitted, walls are impassable.

User may enter 'f' to view an artwork. The program will load and print an ASCII art version of the painting, using an image whose URL is stored in the map file.

The user is informed if they try to view a space where there is no painting, and they are also informed if the painting URL is for some reason unreachable.

Logging is currently outputted to console. A file starting with 'MuseumHistory' and ending with the timestamp is created containing all of the user's actions.

##Internal
The map file *must* be correctly formatted. Creating a map file which is not fully enclosed by walls may lead to catastrophic failure!

Paintings are loaded in their own thread during map loading.

Unit testing is present, and there is 97% coverage on my painting class and 64% coverage on my map class.

##Future additions
In the future, I may send user inputs to a database instead of a MuseumHistory file may help to do analasys on user activity (such as where users went first, most viewed paintings, etc)

In the future, I made add some checking on the map input files to verify that they are fully usable, such as ensuring that all paintings are reachable with BFS.