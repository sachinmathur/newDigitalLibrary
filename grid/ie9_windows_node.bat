title InternetExplorer9_Windows_Node
cd C:/Users/sachin.mathur/.m2/repository/org/seleniumhq/selenium/selenium-server-standalone/2.47.1
java -Dwebdriver.ie.driver="C:\Users\sachin.mathur\workspace\newDigitalLibrary\drivers\IEDriverServer_x64/IEDriverServer.exe" -jar selenium-server-standalone-2.47.1.jar -role node -hub http://10.97.143.148:4444/grid/register -port 5566
-browser browserName="internet explorer",version=9, platform=WINDOWS,maxinstances=2 -hubhost <10.97.143.148>-host <10.97.143.148> -maxSession 5