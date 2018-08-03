## Graph Diameter App  
![alt text](http://mathworld.wolfram.com/images/eps-gif/RandomGraphs_1000.gif)

Java app that I made for a code challenge. It calculates the diameter of an undirected graph.  

It takes a txt file in the following format:  
A – B  
B – C  
B – D  
D – E  
where “A”, “B”, “C”, “D” and “E” are names of the nodes and “-” depicts a path between the nodes. Node name can be any sequence of upper case letters and numbers without spaces e.g. “A2B3”.  

It then returns the diameter of the graph or if the graph is disconnected Integer.MAX_VALUE 

---
### Prerequisites  
You will need the following installed:  
* [Git](https://git-scm.com/)  
* [Java](http://www.oracle.com/technetwork/java/javase/downloads/jdk10-downloads-4416644.html)  
* [Gradle](https://gradle.org/install/)  

---
### Installation  
`git clone https://github.com/ronaldnwilliams/graph-diameter-app.git`  
`cd graph-diameter-app.git`  

---
### Run  
`gradle run`  
or to run this app with your own txt file that follows the above formatting:  
`gradle run --args '[some file path]'`  

---
### Test  
`gradle test`  
`open build/reports/tests/test/index.html`  
