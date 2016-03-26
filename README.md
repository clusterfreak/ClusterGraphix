# Cluster
**Cluster Graphix**
Display of objects and clusters - Grafische Anzeige von Punktemengen und Klassenzentren  

**&copy; Thomas Heym - clusterfreak** (cF) clstrfrk
![clusterfreak](https://http://clusterfreak.com/favicon.ico "clusterfreak")

### Version Information
* ClusterGraphix v0.95.0 (01-24-2016)
* Cluster core classes
	* ClusterBot v0.1.0 (01-24-2016)
	* ClusterData v0.1.0 (01-24-2016)
	* ClusterFile v0.1.0 (01-24-2016)
	* FuzzyCMeans v1.6.0 (01-24-2016)
	* PossibilisticCMeans v1.2.0 (01-24-2016)
	* Point2D v1.3.0 (01-24-2016)
	* PointPixel v1.0.0 (01-24-2016)
* * *
### Release Information
v0.95.0
* rename from ClusterGraphics
* clusterBot-Info at the end of the check report
* rename fiftyFiftyJoker
* Core classes
	* ClusterData v0.1.0
		* new initial values
		* extended error handling
	* FuzzyCMeans v1.6.0
		* if NaN-Error mik=1.0
	* PossibilisticCMeans v1.2.0, ClusterFile v0.1.0
		* stable release
	* ClusterBot v0.1.0
		* add pointPixel[] and centerPixel
	* Point2D v1.3.0
		* new function toPointPixel(int pixelOffset)
	* PointPixel v1.0.0
		* new class for ClusterBot

v0.94.9
* download function for data file
* UTF-8 text for unix support
* fully integrate Error variable
* colored headUpDisplay for important data
* colum number in data tab misc
* addPointPixelObject
* application icon image
* rename durchlauf to pass
* ClusterFile v0.0.5 (22.09.2015)
	* common functions moved to ClusterData
* ClusterData v0.0.2 (28.12.2015)
* FuzzyCMeans v1.5.5 (28.12.2015)
* Punkt2D v1.2.0 (28.12.2015)
* ClusterBot v0.0.3 (28.12.2015)
* PossibilisticCMeans v1.1.5 (28.12.2015)
* start GitHub with Branch *V0.94.9* on *17.09.2015*

v0.94.8
* Error-Button function

v0.94.7
* Error-Variable
* quickCheck()

v0.94.6
* file import/export
* ClusterBot visualisation
* extended viPath from Possibil and fuzzy
* save and open in xml file
* testfunction for internal data
* status bar
* main-methode
* import/export of pixel objects in pbm format

* * *

### Ishues for GitHub
* the pixel value is not the same all the time, it is decreasing, clusterBot calculation fail, the display is right, but the bot value not, in the topointpixel function
* with possibilistic there is no objectDescription, it is space
* Beim laden ist vi und viPath daten markiert
* Wird File-Validate gewählt, dann wird ggf. die importierte Datei vorgeschlagen. Das würde zu einem Fehler führen.
	* Der Dateiname sollte hier gelöscht werden.
* get initial in verschiedenen int boolean Varianten, probieren, ob die richtige gezogen wird