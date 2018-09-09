# Cluster
**ClusterGraphix**
Display of objects and clusters - Grafische Anzeige von Punktemengen und Klassenzentren  

**&copy; Thomas Heym - clusterfreak**

### Version Information
* ClusterCore 1.0.0 (2016-04-10)
	* FuzzyCMeans 1.6.1 (2016-04-10)
	* PossibilisticCMeans 1.2.1 (2016-04-10)
	* Point2D 1.3.1 (2016-04-10)
	* PointPixel 1.0.1 (2016-04-10)
* ClusterGraphix 0.95.3 (2017-08-19)
	* ClusterGraphix 0.95.3 (2017-08-19)
	* ClusterBot 	0.1.1 (2016-04-10)
	* ClusterBotNet 0.1.1 (2017-01-07)
	* ClusterData 0.1.3 (2017-08-19)
	* ClusterFile 0.1.1 (2016-04-10)
	* ClusterQuality 0.1.0 (2016-04-10)
* ClusterTest v0.1.0 (2016-04-10)
	* ClusterTest v0.1.0 (2016-04-10)
	* CoreTest v0.1.0 (2016-04-10)
	* WritePbm v0.1.0 (2016-04-10)
* ClusterGraphix.css v0.0.1 (2017-01-07)

### Bugs
* clusterCenterPointNumber 99999999 when not found

### Ideas
* CTRL + SHIFT + F Source Code Pretty Print
* check if objectMembership complete
* clusterBotNet.clusterBotCenter only modified when realy modified

### Release Information
0.95.3 (2017-08-19)
* lay fInfo over f
* add object on mouseClicked
* cluster default 1
* fuzzyCMeans default true
* rename packages
* bugfix in addPointObject

0.95.2
* deleteCluster() bugfix
* deleteNotAssigned() bugfix
* checkReport() bugfix, design
* pixelMatrix() parameter force
* example2() changed to pixel mode
* paintComponent() objectDesciption for pixel mode
* paintComponent() objects with no membership are black in pixel mode
* ClusterBotNet.clusterCenterNumber(); ClusterBotNet.clusterCenterString()
* ClusterBotNet.clusterCenterPointNumber(); ClusterBotNet.clusterCenterPointString()
* ClusterBot.radius
* ClusterGraphix.css, dataFile() design
* calculate new with STRG-F8
* clusterCircle
* miscData tab, design

0.95.1
* ClusterCore v1.0.0 package
* ClusterTest v0.1.0 package
* String objectDescirption -> int objectMembership
* bugfixes checkReport()
* detailed ClusterBot Info
* no more ClusterBot Lines
* getDesciptionX()
* ClusterBot.addPointPixel()
* ClusterBot.pixelOffset
* ClusterBotNet, ClusterQuality
* developerMode
* Recalculation of all up to 10 steps depending on clusterQuality > 0.1

0.95.0
* rename from ClusterGraphics
* clusterBot-Info at the end of the check report
* rename fiftyFiftyJoker
* clear file chooser all the time
* Core classes
	* ClusterData v0.1.0
		* new initial values
		* extended error handling
	* FuzzyCMeans v1.6.0, PossibilisticCMeans v1.2.0
		* if NaN-Error mik=1.0
		* separate getViPath method
	* ClusterFile v0.1.0
		* stable release
	* ClusterBot v0.1.0
		* add pointPixel[] and centerPixel
	* Point2D v1.3.0
		* new function toPointPixel(int pixelOffset)
	* PointPixel v1.0.0
		* new class for ClusterBot

0.94.9
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

0.94.8
* Error-Button function

0.94.7
* Error-Variable
* quickCheck()

0.94.6
* file import/export
* ClusterBot visualisation
* extended viPath from Possibil and fuzzy
* save and open in xml file
* testfunction for internal data
* status bar
* main-methode
* import/export of pixel objects in pbm format